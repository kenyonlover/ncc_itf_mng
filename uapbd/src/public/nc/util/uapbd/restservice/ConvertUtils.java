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
 * ת������
 * 
 * @author 30798
 */
public class ConvertUtils {

	/**
	 * ��ȡ�ӿ�ת������
	 * 
	 * @param itftype �ӿ�����
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, AggItfConfigBillVO> getConvertConfig(String itftype) throws BusinessException {
		String getItftype = "select pk_itfconfigbill from uapbd_itfconfigbill where code = '" + itftype + "' and dr=0";
		List<Map<String, String>> list = (List<Map<String, String>>) ServiceUtils.getBaseDAO().executeQuery(getItftype,
				new MapListProcessor());
		AssertUtils.listIsNull(list, "��������еġ�itftype = " + itftype + "����ҵ��ϵͳ�ӿ�������δ���ô����ͣ�");

		ArrayList<String> pkList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			pkList.add(list.get(i).get("pk_itfconfigbill"));
		}

		HashMap<String, AggItfConfigBillVO> map = new HashMap<String, AggItfConfigBillVO>();

		BillQuery<AggItfConfigBillVO> billquery = new BillQuery<AggItfConfigBillVO>(AggItfConfigBillVO.class);
		AggItfConfigBillVO[] billvos = billquery.query(pkList.toArray(new String[0]));

		for (AggItfConfigBillVO billvo : billvos) {
			if (billvo.getParentVO().getAttributeValue("nccbilltypecode") == null) {
				throw new BusinessException("ҵ��ϵͳ�ӿ������С�itftype = " + itftype + "����Ӧ��NCC�������ͱ���Ϊ��,���飡");
			}
			map.put(billvo.getParentVO().getAttributeValue("nccbilltypecode").toString(), billvo);
		}

		return map;
	}

	/**
	 * ͨ��ҵ��ϵͳ�ӿ����ö��ս�ҵ�񵥾�����ת��ΪNCC��������
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
		
		String itftype = (String) busiBillMap.get("itftype");// �ӿ�����
		
		HashMap<String, HashMap<String, Object>> retMap = new HashMap<String, HashMap<String,Object>>();//��������
		
		for (Entry<String, AggItfConfigBillVO> entry : translateConfigMap.entrySet()) {
			String nccbilltypecode = entry.getKey();// NCC��������
			HashMap<String, Object> busiBillMapClone = new HashMap<String, Object>();//�����ĵ�������
			busiBillMapClone.putAll(busiBillMap);

			AggItfConfigBillVO billVO = entry.getValue();// NCC���ݶ�Ӧ���ñ�
			ItfConfigItemVO[] childrenVO = (ItfConfigItemVO[]) billVO.getChildrenVO();

			HashMap<String, String> oldHeadMap = (HashMap<String, String>) busiBillMapClone.get("head");// ҵ��ϵͳ��ͷ����
			List<HashMap<String, String>> oldBodyList = (List<HashMap<String, String>>) busiBillMapClone.get("body");// ҵ��ϵͳ��������

			HashMap<String, String> headMap = itfBillToNCCBill(oldHeadMap, "HEAD", itftype, childrenVO);// ����ҵ��ϵͳ��ͷ����

			ArrayList<HashMap<String, String>> bodyList = new ArrayList<HashMap<String, String>>();// ���б�������
			
			// ����ҵ��ϵͳ��������
			for (HashMap<String, String> oldBodyMap : oldBodyList) {
				HashMap<String, String> bodyMap = itfBillToNCCBill(oldBodyMap, "BODY", itftype, childrenVO);// ������������
				bodyList.add(bodyMap);
			}
			
			busiBillMapClone.put("head", headMap);
			busiBillMapClone.put("body", bodyList);

			retMap.put(nccbilltypecode, busiBillMapClone);
		}
		return null;
	}
	
	/**
	 * ͨ����ҵ��ϵͳ�ֶ���NCC�ֶζԱȣ���ҵ��ϵͳ��Map����ת��ΪNCCϵͳ��Map����
	 * @param oldMap ҵ��ϵͳ��Map����
	 * @param position �ֶ�λ��
	 * @param itftype �ӿ�����
	 * @param itfConfigItemVOs ���ñ�
	 * @return
	 * @throws BusinessException 
	 */
	private static HashMap<String, String> itfBillToNCCBill(HashMap<String, String> oldMap, String position, String itftype, ItfConfigItemVO[] itfConfigItemVOs) throws BusinessException{
		HashMap<String, String> newMap = new HashMap<String, String>();
		
		// ����ҵ��ϵͳ��ͷ����
		for (String oldBusiField : oldMap.keySet()) {
			// �������ñ�
			for (ItfConfigItemVO bodyVO : itfConfigItemVOs) {
				String fieldposition = bodyVO.getFieldposition();// ��ͷ����
				String busifieldcode = bodyVO.getBusifieldcode();// ҵ��ϵͳ�ֶ�
				String nccfieldcode = bodyVO.getNccfieldcode();// NCC�ֶ�
				UFBoolean isdefault = bodyVO.getIsdefault();// �Ƿ�ȡĬ��ֵ
				String defaultvalue = bodyVO.getDefaultvalue();// Ĭ��ֵ
				if (position.equals(fieldposition)) {
					AssertUtils.stringIsNull(nccfieldcode, "ҵ�񵥾ݽӿ����ã���itftype = " + itftype + "����fieldposition = "
							+ fieldposition + "���д���NCC�ֶα���Ϊ�յı����У�");
					if (isdefault.booleanValue() && !newMap.containsKey(nccfieldcode)) {
						AssertUtils.stringIsNull(defaultvalue,
								"ҵ�񵥾ݽӿ����ã���itftype = " + itftype + "����fieldposition = " + fieldposition
										+ "����nccfieldcode = " + nccfieldcode + "������ȡĬ��ֵʱĬ��ֵΪ�գ�");
						newMap.put(nccfieldcode, defaultvalue);
					} else {
						if (oldBusiField.equals(busifieldcode)) {
							AssertUtils.stringIsNull(busifieldcode,
									"ҵ�񵥾ݽӿ����ã���itftype = " + itftype + "����fieldposition = " + fieldposition
											+ "����nccfieldcode = " + nccfieldcode + "������ҵ��ϵͳ�ֶα���Ϊ�գ�");
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
