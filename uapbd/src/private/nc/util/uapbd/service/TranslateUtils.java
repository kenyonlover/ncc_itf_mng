package nc.util.uapbd.service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.md.model.IBusinessEntity;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.meta.IBDObject;
import nc.vo.pub.BusinessException;

/**
 * ���빤�߼�
 * @author 30798
 */
public class TranslateUtils {
	
	private static BaseDAO getDao() {
		return ServiceUtils.getBaseDAO();
	}
	
	/**
	 * ��ȡ����������
	 * @param classId Ԫ����ID
	 * @param pk_group ����
	 * @param pk_org ��֯
	 * @param fieldKey �ֶ�key
	 * @param fieldValue �ֶ�ֵ
	 * @param fieldPosition �ֶ�λ�ã���������_��ͷ/���壬eg:45_H��
	 * @param itfType �ӿ�����
	 * @return
	 * @throws BusinessException
	 */
	public static Object getTranslateValue(String classId, String pk_group, String pk_org, String fieldKey, String fieldValue, String fieldPosition, String itfType) throws BusinessException {
		Object transValue = null;//������ֵ
		try {
			
			String dataType = getKeyProperty(classId, fieldKey);// ��ȡԪ�����ֶ������������ԣ�ͬʱԪ�����в����ڸ��ֶεģ�Ҳ��ͨ���˷���null�����˵������ת��
			if (dataType == null || dataType.equals("")) {
				return fieldValue;
			}
			
			HashMap<String, String> dataTypeMap = getDataType(dataType, fieldKey, fieldPosition);// �����ֶ��������Ͷ�Ӧ��Ԫ������Ϣ
			// ���û�л�ȡ����ֱ�ӷ��ش�����ֶ�ֵ
			if(dataTypeMap == null) {
				return fieldValue;
			}
			// ��Գ���ժҪ�����⴦�������з���
			else if("07a55a1c-ef34-4607-8230-18053e94f27f".equals(dataTypeMap.get("id"))) {
				return fieldValue;
			}
			else {
				String defaulttablename = dataTypeMap.get("defaulttablename");//����
				String fullclassname = dataTypeMap.get("fullclassname");//��ȫ·������
				String datatypeid = dataTypeMap.get("id");//classID
	
				if (defaulttablename != null && !defaulttablename.trim().equals("")) {
					if ("bd_accasoa".equals(defaulttablename)) {// ��Ŀ
						IGeneralAccessor accesssor = GeneralAccessorFactory.getAccessor(dataType);
						IBDData accor = accesssor.getDocByCode(pk_org, fieldValue);
						return accor.getPk();
					}
	
					IBean bean = MDBaseQueryFacade.getInstance().getBeanByID(datatypeid);// ��ȡ�ֶ����͵�Ԫ���������Ϣ����defaulttablename,fullclassname��
					String codeKey = "";
					String groupKey = "";
					String idKey = "";
					String nameKey = "";
					String orgKey = "";
					if (bean != null) {// ���Ϊ�գ����������ݴ���
						// ȡ���ֶ�
						Map<String, String> name_attr_map = ((IBusinessEntity) bean).getBizInterfaceMapInfo(IBDObject.class.getName());
						codeKey = name_attr_map.get("code");// defaulttablename��Ӧ�ı����ֶ�
						idKey = name_attr_map.get("id");// defaulttablename��Ӧ�������ֶ�
						nameKey = name_attr_map.get("name");// defaulttablename��Ӧ��name�ֶ�
						orgKey = name_attr_map.get("pk_org");// defaulttablename��Ӧ����֯�ֶ�
						groupKey = name_attr_map.get("pk_group");// defaulttablename��Ӧ�ļ����ֶ�
	
					}
	
					// ת��������SQL��䣬��Ҫע�⵵����Σ�ȫ�֡����š���֯
					// ����Ҫ�����Զ����ֶ������ֶ�
					String valuesql = "";
					if (defaulttablename.equals("bd_defdoc")) {// ���Ԫ�������õ����Զ��嵵��
						String doclistcode = dataTypeMap.get("name").replace("Defdoc-", "");
						if (orgKey == null) {
							valuesql = "select " + idKey + "," + groupKey + " pk_org from " + defaulttablename
									+ " where nvl(dr,0)=0 and  pk_defdoclist = (select pk_defdoclist from bd_defdoclist where code = '"
									+ doclistcode + "') and code = '" + fieldValue + "' ";
						} else
							valuesql = "select " + idKey + "," + orgKey + " from " + defaulttablename
									+ " where nvl(dr,0)=0 and  pk_defdoclist = (select pk_defdoclist from bd_defdoclist where code = '"
									+ doclistcode + "') and code = '" + fieldValue + "' ";
					} else if ("7717f1a3-b777-454e-abb5-ef8705a0f0c2".equals(dataTypeMap.get("id"))) {// ��Ϊҵ������ʱ
						valuesql = "select " + idKey + "," + groupKey + " pk_org from " + defaulttablename
								+ " where nvl(dr,0)=0 and  " + codeKey + " = '" + fieldValue + "' ";
					} else {
						if (orgKey == null) {
							valuesql = "select " + idKey + "," + groupKey + " pk_org from " + defaulttablename
									+ " where nvl(dr,0)=0 and  " + codeKey + " = '" + fieldValue + "' ";
						} else
							valuesql = "select " + idKey + "," + orgKey + " from " + defaulttablename
									+ " where nvl(dr,0)=0 and  " + codeKey + " = '" + fieldValue + "' ";
					}
					List<Map<String, String>> relist = (List<Map<String, String>>) getDao().executeQuery(valuesql, new MapListProcessor());
					if (relist == null || relist.size() == 0) {
						throw new BusinessException(valuesql + "δ�ҵ���Ӧֵ,���飡");
					} else if (relist.size() == 1) {
						return relist.get(0).get(idKey);
					} else if (relist.size() > 1) {
						for (int i = 0; i < relist.size(); i++) {
							String getbill_org = "select code from Org_Orgs where pk_org = '" + pk_org + "'";
							String getdoc_org = "select code from Org_Orgs where pk_org = '" + relist.get(i).get("pk_org") + "'";
							String bill_org = (String) getDao().executeQuery(getbill_org, new ColumnProcessor());
							String doc_org = (String) getDao().executeQuery(getdoc_org, new ColumnProcessor());
							if (bill_org.equals(doc_org)) {
								return relist.get(i).get(idKey);
							}
						}
						// �����֯��û�ҵ�����Ҽ���
						for (int i = 0; i < relist.size(); i++) {
							if (pk_group.equals(relist.get(i).get("pk_org"))) {
								return relist.get(i).get(idKey);
							}else if (pk_group.equals(relist.get(i).get("pk_group"))) {
								return relist.get(i).get(idKey);
							}
						}
						throw new BusinessException(valuesql + "δ�ҵ���Ӧֵ,���飡");
	
					}
				} else {
					if (fullclassname.equals("nc.vo.pub.lang.UFBoolean")) {// �����UFBoolean�ͣ�Ĭ�ϳ�N
						if (fieldValue == null || fieldValue.toString().trim().equals("")) {
							fieldValue = "N";
						}
					} 
					/*else if (fullclassname.equals("nc.vo.pub.lang.UFDouble")) {
						// �����������ȡ8λ�������룬�ٰ����õ�λ�������������⣬ͳһ����
						UFDouble d = new UFDouble(fieldValue == null ? "0" : fieldValue);
						int a = (d + "").length() - (d + "").indexOf(".") - 1;
						if (a == 8) {
							fieldValue = d.setScale(6, UFDouble.ROUND_HALF_UP).toString();
						}
					}*/
					// exvalue=null�ģ������UFBoolean�ͣ�����exvalue=N������ֱ�ӷ��ؿ�
					if (fieldValue == null || fieldValue.trim().equals("")) {
						return null;
					}
					// ���÷��丳ֵ
					Class clazz = Class.forName(fullclassname);
					Constructor<String> constructor = clazz.getConstructor(String.class);// ��ȡ�вι���
					Object obj = constructor.newInstance(String.valueOf(fieldValue));
					transValue = obj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			String sql = "select to_char(wm_concat(nccfieldname)) nccfieldname from uapbd_itfconfigitem where pk_itfconfigbill in "
					+ "(select pk_itfconfigbill from uapbd_itfconfigbill where dr=0 and code='" + itfType + "')" + " and nccfieldcode = '"
					+ fieldKey + "'";
			String name = (String) getDao().executeQuery(sql, new ColumnProcessor());
			String keysql = "select to_char(wm_concat(distinct busifieldcode)) busifieldcode from uapbd_itfconfigitem where pk_itfconfigbill in "
					+ "(select pk_itfconfigbill from pk_itfconfigbill where dr=0 and code='" + itfType + "')" + " and nccfieldcode = '"
					+ fieldKey + "'";
			String busifieldcode = (String) getDao().executeQuery(keysql, new ColumnProcessor());
			throw new BusinessException(
					"ת�����ݳ����ֶ����ơ�" + name + "�����ֶΣ�NC��" + fieldKey + "����YT��" + busifieldcode + "����ԭ��" + e.getMessage() + "��");
		}
		
		return transValue;
		
	}
	
	/**
	 * ��ȡ��Ӧҳǩ��Ԫ�����ֶ�����
	 * @param classid
	 * @param key
	 * @return
	 * @throws BusinessException
	 */
	public static String getKeyProperty(String classid, String key) throws BusinessException {
		String sql = "select datatype from md_property where classid = '" + classid + "' and name = '" + key + "' ";
		String datatype = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(datatype, "��ȡ�ֶ��������ͳ���SQL��" + sql + "��������Ϣ��û�л�ȡ�����ݣ�");
		return datatype;
	}
	
	/**
	 * ��ȡ�ֶ��������Ͷ�Ӧ��Ԫ������Ϣ
	 * @param dataType Ԫ�����ֶ�����
	 * @param fieldKey �ֶ�
	 * @param fieldPosition �ֶ�λ��
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> getDataType(String dataType, String fieldKey, String fieldPosition) throws BusinessException {
		HashMap<String, String> map = null;
		try {
			String sql = "select * from md_class where id = '" + dataType + "'";
			ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) getDao().executeQuery(sql, new MapListProcessor());
			if (list != null && list.size() > 0) {
				map = list.get(0);
				if (map != null) {
					String classtype = String.valueOf(map.get("classtype"));
					// ������Զ����ͨ���Զ���������ת��
					// classtype = 56���û��Զ�����
					if (classtype != null && new Integer(classtype).intValue() == 56) {
						AssertUtils.stringIsNull(fieldPosition, "�ֶ�" + fieldKey + "Ϊ�û��Զ������ԣ�ת��ʱ��Ҫ�����ֶ�λ�á�bd_userdefrule.code����ֵ��");
						// ȡ�Զ��������
						String propindex = fieldKey.replaceAll(".*[^\\d](?=(\\d+))", "");
						if (propindex != null && !propindex.trim().equals("")) {
							// �����Զ�������ţ�ȡ�������Զ��������������Ԫ����
							String defsql = "select classid from bd_userdefitem where pk_userdefrule in (select pk_userdefrule from bd_userdefrule where code ='" + fieldPosition + "') and propindex = " + propindex + "";
							Object type = getDao().executeQuery(defsql, new ColumnProcessor());
							if (type != null && !type.toString().equals("")) {// ��������ˣ������õ�������������classid���ٴβ��Ҷ�Ӧ����������
								map = getDataType(type.toString(), fieldKey, fieldPosition);
							} else {
								// ���û�����ã���������ʽ��������
								String returntype = map.get("returntype");// ö���ͣ����������磺String��UFBoolean�ȣ�returntype�ǿգ�ö�ٻ���Ҫ����returntypeȥ����md_class�ҳ������Ϣ
								if (returntype != null && !returntype.toString().trim().equals("")) {
									String sql2 = "select * from md_class where id = '" + returntype + "'";
									ArrayList<HashMap<String, String>> list2 = (ArrayList<HashMap<String, String>>) getDao().executeQuery(sql2, new MapListProcessor());
									if (list2 != null && list2.size() > 0) {
										map = list2.get(0);
									}
								}
							}
						}
					} else {
						String returntype = map.get("returntype");// ö���ͣ����������磺String��UFBoolean�ȣ�returntype�ǿգ�ö�ٻ���Ҫ����returntypeȥ����md_class�ҳ������Ϣ
						if (returntype != null && !returntype.toString().trim().equals("")) {
							String sql2 = "select * from md_class where id = '" + returntype + "'";
							ArrayList<HashMap<String, String>> list2 = (ArrayList<HashMap<String, String>>) getDao().executeQuery(sql2, new MapListProcessor());
							if (list2 != null && list2.size() > 0) {
								map = list2.get(0);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ�ֶ��������Ͷ�Ӧ��Ԫ���ݳ���" + e.getMessage());
		}
		return map;
	}
}
