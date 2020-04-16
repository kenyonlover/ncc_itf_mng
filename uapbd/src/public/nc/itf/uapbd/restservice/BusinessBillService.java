package nc.itf.uapbd.restservice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONString;

import com.alibaba.fastjson.JSON;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.server.ISecurityTokenCallback;
import nc.bs.uap.lock.PKLock;
import nc.itf.uapbd.service.IBusinessBillLog;
import nc.itf.uapbd.service.IBusinessBillSave;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.util.uapbd.restservice.CreateBillFactory;
import nc.util.uapbd.restservice.ResultUtils;
import nc.util.uapbd.restservice.ConvertUtils;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.ServiceUtils;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.uapbd.restservice.BusinessBillServiceLogVO;
import uap.ws.rest.resource.AbstractUAPRestResource;

@Path("businessBill")
public class BusinessBillService extends AbstractUAPRestResource {
	
	private BaseDAO getDao() {
		return ServiceUtils.getBaseDAO();
	}
	
	// 业务单据在NCC的制单人
	private final String creater = "busicreater";

	@SuppressWarnings("unchecked")
	@POST
	@Path("opration")
	@Consumes("application/json")
	@Produces("application/json")
	public String saveNcBills(JSONString json) throws BusinessException {
		String msg = "";
		IBusinessBillLog billLog = NCLocator.getInstance().lookup(IBusinessBillLog.class);
		BusinessBillServiceLogVO logvo = new BusinessBillServiceLogVO();
		try {
			// 反转义后的字符串
			// String strJson = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(json.toString());
			String strJson = StringEscapeUtils.unescapeJava(json.toString());
			AssertUtils.stringIsNull(strJson, "接口传入参数为空,请检查参数！");
			
			// 此处用户没有做同步，建议使用固定用户creater
			String getUser = "select suser.user_code,org.pk_group,suser.cuserid,org.code from sm_user suser,org_group org where suser.pk_group=org.pk_group and suser.user_code='"+creater+"'";
			List<Map<String, String>> userList = (List<Map<String, String>>) getDao().executeQuery(getUser, new MapListProcessor());
			AssertUtils.listIsNull(userList, "NCC用户里未找到用户,请检查");
			
			HashMap<String, Object> busiBillMap = (HashMap<String, Object>) JSON.parse(strJson);
			AssertUtils.mapIsNull(busiBillMap, "接口传入参数转换单据为空！");
			
			// 注册登录信息
			ncRegister(userList.get(0).get("user_code"), userList.get(0).get("cuserid"), userList.get(0).get("pk_group"));
			
			HashSet<String> busiIdSet = new HashSet<String>();
			
			String opration = (String) busiBillMap.get("opration");// 操作类型
			String sender = (String) busiBillMap.get("sender");// 发送方
			String itftype = (String) busiBillMap.get("itftype");// 接口类型

			// 数据校验
			AssertUtils.stringIsNull(opration, "传入参数中的opration为空！");
			if (!("Add".equals(opration) || "Delete".equals(opration) || "Update".equals(opration))) {
				throw new BusinessException("传入参数中的opration不合法！");
			}
			AssertUtils.stringIsNull(itftype, "传入参数中的itftype为空！");
			
			if (!"U9".equals(sender)) {
				throw new BusinessException("传入参数中的sender非法！");
			}
			
			String getItfType = "select pk_itfconfigbill from uapbd_itfconfigbill where code = '"+itftype+"' and dr=0";
			List<Map<String, String>> itftypeList = (List<Map<String, String>>) new BaseDAO().executeQuery(getItfType, new MapListProcessor());
			AssertUtils.listIsNull(itftypeList, "传入参数中的【itftype = " + itftype + "】在业务系统接口配置中未配置此类型！");
			
			Map<String, String> busiHeadMap = (Map<String, String>) busiBillMap.get("head");
			AssertUtils.mapIsNull(busiHeadMap, "传入参数中的head为空！");
			String pk_org = busiHeadMap.get("pk_org");
			String billtype = busiHeadMap.get("billtype");
			String pk_head = busiHeadMap.get("pk_head");
			String billno = busiHeadMap.get("billno");
			AssertUtils.stringIsNull(pk_org, "传入参数中的【head.pk_org】为空！");
			AssertUtils.stringIsNull(billtype, "传入参数中的【head.billtype】为空！");
			AssertUtils.stringIsNull(pk_head, "传入参数中的【head.pk_head】为空！");
			AssertUtils.stringIsNull(billno, "传入参数中的【head.billno】为空！");
			
			// 加锁处理，避免重复
			boolean islock = PKLock.getInstance().addDynamicLock(pk_org + billtype + pk_head);
			if (!islock) {
				throw new BusinessException(billno + "加锁失败，数据正在处理中......");
			}

			// 创建日志vo
			logvo.setSender(sender); // 发送方
			logvo.setItftype(itftype);// 接口类型
			logvo.setOrgid(pk_org);// 公司
			logvo.setBilltype(billtype);// 业务系统单据类型
			logvo.setBillpk(pk_head);// 业务单据主键
			logvo.setBillno(billno);// 业务单据号
			logvo.setRecmsg(strJson); // 接收数据
			logvo.setRectime(new UFDateTime()); // 接收时间
			logvo.setDr(0);
			// 保存日志
			String pk_log = billLog.saveWithVO_RequiresNew(logvo);
			logvo.setPk_log(pk_log);

			// 通过itftype查找对照表,并将传递的busiBillMap转换为NCC的Map数据
			HashMap<String, AggItfConfigBillVO> translateConfigMap = ConvertUtils.getConvertConfig(itftype);
			HashMap<String, HashMap<String, Object>> NCCBillMap = ConvertUtils.itfBillToNCCBillWithConvertConfig(busiBillMap, translateConfigMap);
			
			// 将NCC的Map数据转换为aggVO
			HashMap<String, AggregatedValueObject> billVOMap = new HashMap<String, AggregatedValueObject>();
			for (Entry<String, HashMap<String, Object>> entry : NCCBillMap.entrySet()) {
				String nccbilltypecode = entry.getKey();
				HashMap<String, Object> nccBillMap = entry.getValue();
				AggregatedValueObject aggVO = CreateBillFactory.createBill(nccbilltypecode, nccBillMap);
				billVOMap.put(nccbilltypecode, aggVO);
			}

			HashMap<String, HashMap<String, AggregatedValueObject>> voOprationMap = new HashMap<String, HashMap<String, AggregatedValueObject>>();
			// Add表示新增
			if ("Add".equals(opration)) {
				voOprationMap.put("Add", billVOMap);
			}
			// Delete表示删除
			else if ("Delete".equals(opration)) {
				voOprationMap.put("Delete", billVOMap);
			}
			// Update表示修改
			else if ("Update".equals(opration)) {
				voOprationMap.put("Delete", billVOMap);
				voOprationMap.put("Add", billVOMap);
			}

			NCLocator.getInstance().lookup(IBusinessBillSave.class).saveBill(voOprationMap);
			msg = ResultUtils.getResult("0", "sucess");

			// 日志
			logvo.setRetflag("Y"); // 成功状态
			logvo.setRettime(new UFDateTime()); // 返回时间
			billLog.updateWithVO_RequiresNew(logvo);// 更新日志
			
		} catch (Exception e) {
			e.printStackTrace();
			//日志的处理直接全放到finally即可
			String message = "";
			if (e.getMessage() == null || e.getMessage().trim().equals("")) {
				message = "失败";
			} else {
				message = e.getMessage();
			}
			// 获取返回结果
			msg = ResultUtils.getResult("1", message);
			String retmsg = msg;
			if (retmsg.length() > 1200) {
				retmsg = retmsg.substring(0, 1200);
			}

			// 回写日志
			logvo.setRetmsg(retmsg);
			logvo.setRetflag("N"); // 成功状态
			logvo.setRettime(new UFDateTime()); // 返回时间
			if (logvo.getPrimaryKey() != null)
				billLog.updateWithVO_RequiresNew(logvo);// 更新日志
			else {
				logvo.setRecmsg(json.toJSONString()); // 接收数据
				logvo.setDr(0);
				billLog.saveWithVO_RequiresNew(logvo);
			}
			return msg;
		}
		return msg;
	}
	
	
	@Override
	public String getModule() {
		return "uapbd";
	}

	/**
	 * 模拟登陆
	 * @param userCode
	 * @param userid
	 * @param groupid
	 * @param datalist 
	 */
	private void ncRegister(String userCode, String userid, String groupid) {
		String NCPORTAL = "1";
		byte[] origin = NCPORTAL.getBytes();
		//保存验证要用到id
		InvocationInfoProxy.getInstance().setUserCode(userCode);
		InvocationInfoProxy.getInstance().setUserId(userid);
		InvocationInfoProxy.getInstance().setGroupId(groupid);
		//InvocationInfoProxy.getInstance().setBizDateTime(bizdate.getMillis());
		
		ISecurityTokenCallback sc = NCLocator.getInstance().lookup(ISecurityTokenCallback.class);
		byte[] annonyTokens = sc.token(origin, userCode.getBytes());
		sc.restore(annonyTokens);
		sc.token("WSSystem".getBytes(), "WSSystem".getBytes());
	}

}
