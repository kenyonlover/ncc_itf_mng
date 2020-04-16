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
	
	// ҵ�񵥾���NCC���Ƶ���
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
			// ��ת�����ַ���
			// String strJson = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(json.toString());
			String strJson = StringEscapeUtils.unescapeJava(json.toString());
			AssertUtils.stringIsNull(strJson, "�ӿڴ������Ϊ��,���������");
			
			// �˴��û�û����ͬ��������ʹ�ù̶��û�creater
			String getUser = "select suser.user_code,org.pk_group,suser.cuserid,org.code from sm_user suser,org_group org where suser.pk_group=org.pk_group and suser.user_code='"+creater+"'";
			List<Map<String, String>> userList = (List<Map<String, String>>) getDao().executeQuery(getUser, new MapListProcessor());
			AssertUtils.listIsNull(userList, "NCC�û���δ�ҵ��û�,����");
			
			HashMap<String, Object> busiBillMap = (HashMap<String, Object>) JSON.parse(strJson);
			AssertUtils.mapIsNull(busiBillMap, "�ӿڴ������ת������Ϊ�գ�");
			
			// ע���¼��Ϣ
			ncRegister(userList.get(0).get("user_code"), userList.get(0).get("cuserid"), userList.get(0).get("pk_group"));
			
			HashSet<String> busiIdSet = new HashSet<String>();
			
			String opration = (String) busiBillMap.get("opration");// ��������
			String sender = (String) busiBillMap.get("sender");// ���ͷ�
			String itftype = (String) busiBillMap.get("itftype");// �ӿ�����

			// ����У��
			AssertUtils.stringIsNull(opration, "��������е�oprationΪ�գ�");
			if (!("Add".equals(opration) || "Delete".equals(opration) || "Update".equals(opration))) {
				throw new BusinessException("��������е�opration���Ϸ���");
			}
			AssertUtils.stringIsNull(itftype, "��������е�itftypeΪ�գ�");
			
			if (!"U9".equals(sender)) {
				throw new BusinessException("��������е�sender�Ƿ���");
			}
			
			String getItfType = "select pk_itfconfigbill from uapbd_itfconfigbill where code = '"+itftype+"' and dr=0";
			List<Map<String, String>> itftypeList = (List<Map<String, String>>) new BaseDAO().executeQuery(getItfType, new MapListProcessor());
			AssertUtils.listIsNull(itftypeList, "��������еġ�itftype = " + itftype + "����ҵ��ϵͳ�ӿ�������δ���ô����ͣ�");
			
			Map<String, String> busiHeadMap = (Map<String, String>) busiBillMap.get("head");
			AssertUtils.mapIsNull(busiHeadMap, "��������е�headΪ�գ�");
			String pk_org = busiHeadMap.get("pk_org");
			String billtype = busiHeadMap.get("billtype");
			String pk_head = busiHeadMap.get("pk_head");
			String billno = busiHeadMap.get("billno");
			AssertUtils.stringIsNull(pk_org, "��������еġ�head.pk_org��Ϊ�գ�");
			AssertUtils.stringIsNull(billtype, "��������еġ�head.billtype��Ϊ�գ�");
			AssertUtils.stringIsNull(pk_head, "��������еġ�head.pk_head��Ϊ�գ�");
			AssertUtils.stringIsNull(billno, "��������еġ�head.billno��Ϊ�գ�");
			
			// �������������ظ�
			boolean islock = PKLock.getInstance().addDynamicLock(pk_org + billtype + pk_head);
			if (!islock) {
				throw new BusinessException(billno + "����ʧ�ܣ��������ڴ�����......");
			}

			// ������־vo
			logvo.setSender(sender); // ���ͷ�
			logvo.setItftype(itftype);// �ӿ�����
			logvo.setOrgid(pk_org);// ��˾
			logvo.setBilltype(billtype);// ҵ��ϵͳ��������
			logvo.setBillpk(pk_head);// ҵ�񵥾�����
			logvo.setBillno(billno);// ҵ�񵥾ݺ�
			logvo.setRecmsg(strJson); // ��������
			logvo.setRectime(new UFDateTime()); // ����ʱ��
			logvo.setDr(0);
			// ������־
			String pk_log = billLog.saveWithVO_RequiresNew(logvo);
			logvo.setPk_log(pk_log);

			// ͨ��itftype���Ҷ��ձ�,�������ݵ�busiBillMapת��ΪNCC��Map����
			HashMap<String, AggItfConfigBillVO> translateConfigMap = ConvertUtils.getConvertConfig(itftype);
			HashMap<String, HashMap<String, Object>> NCCBillMap = ConvertUtils.itfBillToNCCBillWithConvertConfig(busiBillMap, translateConfigMap);
			
			// ��NCC��Map����ת��ΪaggVO
			HashMap<String, AggregatedValueObject> billVOMap = new HashMap<String, AggregatedValueObject>();
			for (Entry<String, HashMap<String, Object>> entry : NCCBillMap.entrySet()) {
				String nccbilltypecode = entry.getKey();
				HashMap<String, Object> nccBillMap = entry.getValue();
				AggregatedValueObject aggVO = CreateBillFactory.createBill(nccbilltypecode, nccBillMap);
				billVOMap.put(nccbilltypecode, aggVO);
			}

			HashMap<String, HashMap<String, AggregatedValueObject>> voOprationMap = new HashMap<String, HashMap<String, AggregatedValueObject>>();
			// Add��ʾ����
			if ("Add".equals(opration)) {
				voOprationMap.put("Add", billVOMap);
			}
			// Delete��ʾɾ��
			else if ("Delete".equals(opration)) {
				voOprationMap.put("Delete", billVOMap);
			}
			// Update��ʾ�޸�
			else if ("Update".equals(opration)) {
				voOprationMap.put("Delete", billVOMap);
				voOprationMap.put("Add", billVOMap);
			}

			NCLocator.getInstance().lookup(IBusinessBillSave.class).saveBill(voOprationMap);
			msg = ResultUtils.getResult("0", "sucess");

			// ��־
			logvo.setRetflag("Y"); // �ɹ�״̬
			logvo.setRettime(new UFDateTime()); // ����ʱ��
			billLog.updateWithVO_RequiresNew(logvo);// ������־
			
		} catch (Exception e) {
			e.printStackTrace();
			//��־�Ĵ���ֱ��ȫ�ŵ�finally����
			String message = "";
			if (e.getMessage() == null || e.getMessage().trim().equals("")) {
				message = "ʧ��";
			} else {
				message = e.getMessage();
			}
			// ��ȡ���ؽ��
			msg = ResultUtils.getResult("1", message);
			String retmsg = msg;
			if (retmsg.length() > 1200) {
				retmsg = retmsg.substring(0, 1200);
			}

			// ��д��־
			logvo.setRetmsg(retmsg);
			logvo.setRetflag("N"); // �ɹ�״̬
			logvo.setRettime(new UFDateTime()); // ����ʱ��
			if (logvo.getPrimaryKey() != null)
				billLog.updateWithVO_RequiresNew(logvo);// ������־
			else {
				logvo.setRecmsg(json.toJSONString()); // ��������
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
	 * ģ���½
	 * @param userCode
	 * @param userid
	 * @param groupid
	 * @param datalist 
	 */
	private void ncRegister(String userCode, String userid, String groupid) {
		String NCPORTAL = "1";
		byte[] origin = NCPORTAL.getBytes();
		//������֤Ҫ�õ�id
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
