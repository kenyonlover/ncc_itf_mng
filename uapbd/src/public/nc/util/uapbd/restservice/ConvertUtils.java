package nc.util.uapbd.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.ServiceUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.uapbd.itfconfig.ItfConfigItemVO;

/**
 * 转换工具
 * 
 * @author 30798
 */
public class ConvertUtils {

	/**
	 * 获取接口转换配置
	 * 
	 * @param itftype 接口类型
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, AggItfConfigBillVO> getConvertConfig(String itftype) throws BusinessException {
		String getItftype = "select pk_itfconfigbill from uapbd_itfconfigbill where code = '" + itftype + "' and dr=0";
		List<Map<String, String>> list = (List<Map<String, String>>) ServiceUtils.getBaseDAO().executeQuery(getItftype,
				new MapListProcessor());
		AssertUtils.listIsNull(list, "传入参数中的【itftype = " + itftype + "】在业务系统接口配置中未配置此类型！");

		ArrayList<String> pkList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			pkList.add(list.get(i).get("pk_itfconfigbill"));
		}

		HashMap<String, AggItfConfigBillVO> map = new HashMap<String, AggItfConfigBillVO>();

		BillQuery<AggItfConfigBillVO> billquery = new BillQuery<AggItfConfigBillVO>(AggItfConfigBillVO.class);
		AggItfConfigBillVO[] billvos = billquery.query(pkList.toArray(new String[0]));

		for (AggItfConfigBillVO billvo : billvos) {
			if (billvo.getParentVO().getAttributeValue("nccbilltypecode") == null) {
				throw new BusinessException("业务系统接口配置中【itftype = " + itftype + "】对应的NCC单据类型编码为空,请检查！");
			}
			map.put(billvo.getParentVO().getAttributeValue("nccbilltypecode").toString(), billvo);
		}

		return map;
	}

	/**
	 * 通过业务系统接口配置对照将业务单据数据转换为NCC单据数据
	 * 
	 * @param busiBillMap
	 * @param translateConfigMap
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, HashMap<String, Object>> itfBillToNCCBillWithConvertConfig(
			HashMap<String, Object> busiBillMap, HashMap<String, AggItfConfigBillVO> translateConfigMap)
			throws BusinessException {
		
		String itftype = (String) busiBillMap.get("itftype");// 接口类型
		
		HashMap<String, HashMap<String, Object>> retMap = new HashMap<String, HashMap<String,Object>>();//返回数据
		
		for (Entry<String, AggItfConfigBillVO> entry : translateConfigMap.entrySet()) {
			String nccbilltypecode = entry.getKey();// NCC单据类型
			HashMap<String, Object> busiBillMapClone = new HashMap<String, Object>();//翻译后的单据数据
			busiBillMapClone.putAll(busiBillMap);

			AggItfConfigBillVO billVO = entry.getValue();// NCC单据对应配置表
			ItfConfigItemVO[] childrenVO = (ItfConfigItemVO[]) billVO.getChildrenVO();

			HashMap<String, String> oldHeadMap = (HashMap<String, String>) busiBillMapClone.get("head");// 业务系统表头数据
			List<HashMap<String, String>> oldBodyList = (List<HashMap<String, String>>) busiBillMapClone.get("body");// 业务系统表体数据

			HashMap<String, String> headMap = itfBillToNCCBill(oldHeadMap, "HEAD", itftype, childrenVO);// 翻译业务系统表头数据

			ArrayList<HashMap<String, String>> bodyList = new ArrayList<HashMap<String, String>>();// 所有表体数据
			
			// 翻译业务系统表体数据
			for (HashMap<String, String> oldBodyMap : oldBodyList) {
				HashMap<String, String> bodyMap = itfBillToNCCBill(oldBodyMap, "BODY", itftype, childrenVO);// 单条表体数据
				bodyList.add(bodyMap);
			}
			
			busiBillMapClone.put("head", headMap);
			busiBillMapClone.put("body", bodyList);

			retMap.put(nccbilltypecode, busiBillMapClone);
		}
		return null;
	}
	
	/**
	 * 通过将业务系统字段与NCC字段对比，将业务系统的Map数据转换为NCC系统的Map数据
	 * @param oldMap 业务系统的Map数据
	 * @param position 字段位置
	 * @param itftype 接口类型
	 * @param itfConfigItemVOs 配置表
	 * @return
	 * @throws BusinessException 
	 */
	private static HashMap<String, String> itfBillToNCCBill(HashMap<String, String> oldMap, String position, String itftype, ItfConfigItemVO[] itfConfigItemVOs) throws BusinessException{
		HashMap<String, String> newMap = new HashMap<String, String>();
		
		// 翻译业务系统表头数据
		for (String oldBusiField : oldMap.keySet()) {
			// 遍历配置表
			for (ItfConfigItemVO bodyVO : itfConfigItemVOs) {
				String fieldposition = bodyVO.getFieldposition();// 表头表体
				String busifieldcode = bodyVO.getBusifieldcode();// 业务系统字段
				String nccfieldcode = bodyVO.getNccfieldcode();// NCC字段
				UFBoolean isdefault = bodyVO.getIsdefault();// 是否取默认值
				String defaultvalue = bodyVO.getDefaultvalue();// 默认值
				if (position.equals(fieldposition)) {
					AssertUtils.stringIsNull(nccfieldcode, "业务单据接口配置：【itftype = " + itftype + "】【fieldposition = "
							+ fieldposition + "】中存在NCC字段编码为空的表体行！");
					if (isdefault.booleanValue() && !newMap.containsKey(nccfieldcode)) {
						AssertUtils.stringIsNull(defaultvalue,
								"业务单据接口配置：【itftype = " + itftype + "】【fieldposition = " + fieldposition
										+ "】【nccfieldcode = " + nccfieldcode + "】的行取默认值时默认值为空！");
						newMap.put(nccfieldcode, defaultvalue);
					} else {
						if (oldBusiField.equals(busifieldcode)) {
							AssertUtils.stringIsNull(busifieldcode,
									"业务单据接口配置：【itftype = " + itftype + "】【fieldposition = " + fieldposition
											+ "】【nccfieldcode = " + nccfieldcode + "】的行业务系统字段编码为空！");
							newMap.put(nccfieldcode, oldMap.get(busifieldcode));
						} else {
							continue;
						}
					}
				} else {
					continue;
				}
			}
		}
		
		return newMap;
	}
}
