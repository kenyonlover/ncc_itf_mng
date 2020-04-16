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
 * 翻译工具集
 * @author 30798
 */
public class TranslateUtils {
	
	private static BaseDAO getDao() {
		return ServiceUtils.getBaseDAO();
	}
	
	/**
	 * 获取翻译后的数据
	 * @param classId 元数据ID
	 * @param pk_group 集团
	 * @param pk_org 组织
	 * @param fieldKey 字段key
	 * @param fieldValue 字段值
	 * @param fieldPosition 字段位置（单据类型_表头/表体，eg:45_H）
	 * @param itfType 接口类型
	 * @return
	 * @throws BusinessException
	 */
	public static Object getTranslateValue(String classId, String pk_group, String pk_org, String fieldKey, String fieldValue, String fieldPosition, String itfType) throws BusinessException {
		Object transValue = null;//翻译后的值
		try {
			
			String dataType = getKeyProperty(classId, fieldKey);// 获取元数据字段数据类型属性，同时元数据中不存在该字段的，也会通过此返回null，过滤掉后面的转换
			if (dataType == null || dataType.equals("")) {
				return fieldValue;
			}
			
			HashMap<String, String> dataTypeMap = getDataType(dataType, fieldKey, fieldPosition);// 返回字段数据类型对应的元数据信息
			// 如果没有获取到，直接返回传入的字段值
			if(dataTypeMap == null) {
				return fieldValue;
			}
			// 针对常用摘要的特殊处理，不进行翻译
			else if("07a55a1c-ef34-4607-8230-18053e94f27f".equals(dataTypeMap.get("id"))) {
				return fieldValue;
			}
			else {
				String defaulttablename = dataTypeMap.get("defaulttablename");//表名
				String fullclassname = dataTypeMap.get("fullclassname");//类全路径名称
				String datatypeid = dataTypeMap.get("id");//classID
	
				if (defaulttablename != null && !defaulttablename.trim().equals("")) {
					if ("bd_accasoa".equals(defaulttablename)) {// 科目
						IGeneralAccessor accesssor = GeneralAccessorFactory.getAccessor(dataType);
						IBDData accor = accesssor.getDocByCode(pk_org, fieldValue);
						return accor.getPk();
					}
	
					IBean bean = MDBaseQueryFacade.getInstance().getBeanByID(datatypeid);// 获取字段类型的元数据相关信息，如defaulttablename,fullclassname等
					String codeKey = "";
					String groupKey = "";
					String idKey = "";
					String nameKey = "";
					String orgKey = "";
					if (bean != null) {// 如果为空，按基础数据处理
						// 取表字段
						Map<String, String> name_attr_map = ((IBusinessEntity) bean).getBizInterfaceMapInfo(IBDObject.class.getName());
						codeKey = name_attr_map.get("code");// defaulttablename对应的编码字段
						idKey = name_attr_map.get("id");// defaulttablename对应的主键字段
						nameKey = name_attr_map.get("name");// defaulttablename对应的name字段
						orgKey = name_attr_map.get("pk_org");// defaulttablename对应的组织字段
						groupKey = name_attr_map.get("pk_group");// defaulttablename对应的集团字段
	
					}
	
					// 转换档案的SQL语句，需要注意档案层次：全局、集团、组织
					// 还需要处理自定义字段引用字段
					String valuesql = "";
					if (defaulttablename.equals("bd_defdoc")) {// 如果元数据引用的是自定义档案
						String doclistcode = dataTypeMap.get("name").replace("Defdoc-", "");
						if (orgKey == null) {
							valuesql = "select " + idKey + "," + groupKey + " pk_org from " + defaulttablename
									+ " where nvl(dr,0)=0 and  pk_defdoclist = (select pk_defdoclist from bd_defdoclist where code = '"
									+ doclistcode + "') and code = '" + fieldValue + "' ";
						} else
							valuesql = "select " + idKey + "," + orgKey + " from " + defaulttablename
									+ " where nvl(dr,0)=0 and  pk_defdoclist = (select pk_defdoclist from bd_defdoclist where code = '"
									+ doclistcode + "') and code = '" + fieldValue + "' ";
					} else if ("7717f1a3-b777-454e-abb5-ef8705a0f0c2".equals(dataTypeMap.get("id"))) {// 当为业务流程时
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
						throw new BusinessException(valuesql + "未找到对应值,请检查！");
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
						// 如果组织内没找到则查找集团
						for (int i = 0; i < relist.size(); i++) {
							if (pk_group.equals(relist.get(i).get("pk_org"))) {
								return relist.get(i).get(idKey);
							}else if (pk_group.equals(relist.get(i).get("pk_group"))) {
								return relist.get(i).get(idKey);
							}
						}
						throw new BusinessException(valuesql + "未找到对应值,请检查！");
	
					}
				} else {
					if (fullclassname.equals("nc.vo.pub.lang.UFBoolean")) {// 如果是UFBoolean型，默认成N
						if (fieldValue == null || fieldValue.toString().trim().equals("")) {
							fieldValue = "N";
						}
					} 
					/*else if (fullclassname.equals("nc.vo.pub.lang.UFDouble")) {
						// 处理存货核算采取8位四舍五入，再按设置的位数四舍五入问题，统一处理
						UFDouble d = new UFDouble(fieldValue == null ? "0" : fieldValue);
						int a = (d + "").length() - (d + "").indexOf(".") - 1;
						if (a == 8) {
							fieldValue = d.setScale(6, UFDouble.ROUND_HALF_UP).toString();
						}
					}*/
					// exvalue=null的，如果是UFBoolean型，这里exvalue=N，否则直接返回空
					if (fieldValue == null || fieldValue.trim().equals("")) {
						return null;
					}
					// 利用反射赋值
					Class clazz = Class.forName(fullclassname);
					Constructor<String> constructor = clazz.getConstructor(String.class);// 获取有参构造
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
					"转换数据出错！字段名称【" + name + "】，字段：NC【" + fieldKey + "】，YT【" + busifieldcode + "】，原因【" + e.getMessage() + "】");
		}
		
		return transValue;
		
	}
	
	/**
	 * 获取对应页签的元数据字段属性
	 * @param classid
	 * @param key
	 * @return
	 * @throws BusinessException
	 */
	public static String getKeyProperty(String classid, String key) throws BusinessException {
		String sql = "select datatype from md_property where classid = '" + classid + "' and name = '" + key + "' ";
		String datatype = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(datatype, "获取字段数据类型出错！SQL：" + sql + "，错误信息：没有获取到数据！");
		return datatype;
	}
	
	/**
	 * 获取字段数据类型对应的元数据信息
	 * @param dataType 元数据字段属性
	 * @param fieldKey 字段
	 * @param fieldPosition 字段位置
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
					// 如果是自定义项，通过自定义项引用转换
					// classtype = 56是用户自定义项
					if (classtype != null && new Integer(classtype).intValue() == 56) {
						AssertUtils.stringIsNull(fieldPosition, "字段" + fieldKey + "为用户自定义属性，转换时需要传入字段位置【bd_userdefrule.code】的值！");
						// 取自定义项序号
						String propindex = fieldKey.replaceAll(".*[^\\d](?=(\\d+))", "");
						if (propindex != null && !propindex.trim().equals("")) {
							// 根据自定义项序号，取到引用自定义项的数据类型元数据
							String defsql = "select classid from bd_userdefitem where pk_userdefrule in (select pk_userdefrule from bd_userdefrule where code ='" + fieldPosition + "') and propindex = " + propindex + "";
							Object type = getDao().executeQuery(defsql, new ColumnProcessor());
							if (type != null && !type.toString().equals("")) {// 如果引用了，用引用档案的数据类型classid，再次查找对应的数据类型
								map = getDataType(type.toString(), fieldKey, fieldPosition);
							} else {
								// 如果没有引用，按其它方式继续处理
								String returntype = map.get("returntype");// 枚举型，基本类型如：String、UFBoolean等，returntype是空，枚举还需要根据returntype去查找md_class找出相关信息
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
						String returntype = map.get("returntype");// 枚举型，基本类型如：String、UFBoolean等，returntype是空，枚举还需要根据returntype去查找md_class找出相关信息
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
			throw new BusinessException("获取字段数据类型对应的元数据出错：" + e.getMessage());
		}
		return map;
	}
}
