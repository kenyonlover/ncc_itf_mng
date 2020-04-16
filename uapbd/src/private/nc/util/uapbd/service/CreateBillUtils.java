package nc.util.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.vo.pub.BusinessException;

/**
 * 创建单据工具集
 * 
 * @author 30798
 */
public class CreateBillUtils {

	private static BaseDAO getDao() {
		return ServiceUtils.getBaseDAO();
	}

	/**
	 * 获取国家
	 * 
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public static String getPk_Countryzone(String code) throws BusinessException {
		String sql = "select pk_country from bd_countryzone where code = '" + code + "' ";
		String docId = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(docId, "获取国家出错！SQL：" + sql + "，错误信息：没有获取到数据！");
		return docId;
	}

	/**
	 * 获取币种
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public static String getPk_Currtype(String code) throws BusinessException {
		String sql = "select pk_currtype from bd_currtype where code = '" + code + "'";
		String docId = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(docId, "获取币种出错！SQL：" + sql + "，错误信息：没有获取到数据！");
		return docId;
	}
	
	/**
	 * 获取主计量单位
	 * @param pk_material
	 * @return
	 * @throws BusinessException
	 */
	public static String getPk_measdoc(String pk_material) throws BusinessException {
		String sql = "select pk_measdoc from bd_material where pk_material ='" + pk_material + "'";
		String docId = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(docId, "获取主计量单位出错！SQL：" + sql + "，错误信息：没有获取到数据！");
		return docId;
	}
	
	/**
	 * 获取物料税类
	 */
	public static HashMap<String, String> getTaxInfo(String pk_material) throws BusinessException {
		String sql = "select * from bd_taxrate where pk_taxcode = "
					+ " (select pk_taxcode from bd_taxcode where mattaxes = "
					+ " (select pk_mattaxes from bd_mattaxes where pk_mattaxes = "
					+ " (select pk_mattaxes from bd_material where pk_material = '" + pk_material + "')))";

		HashMap<String, String> map = (HashMap<String, String>) getDao().executeQuery(sql, new MapProcessor());
		AssertUtils.mapIsNull(map, "获取物料税类出错！SQL：" + sql + "，错误信息：没有获取到数据！");
		return map;
	}
	
	/**
	 * 根据主键查询数据
	 * @param table 表
	 * @param doc 要查询的字段
	 * @param pkKey 主键
	 * @param pkValue 值
	 * @param tableName 表名
	 * @return
	 * @throws BusinessException
	 */
	public static String getDocByPk(String table, String doc, String pkKey, String pkValue, String tableName) throws BusinessException {
		String sql = "";
		String docValue = "";
		try {
			sql = "select " + doc + " from " + table + " where " + pkKey + " = '" + pkValue + "'";
			docValue = (String) getDao().executeQuery(sql, new ColumnProcessor());
			AssertUtils.stringIsNull(docValue, "没有获取到数据！");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取"+tableName+"出错！SQL：" + sql + "，错误信息：" + e.getMessage());
		}
		return docValue;
	}
	
	/**
	 * 根据主键查询Map数据
	 * @param table 表
	 * @param pkKey 主键
	 * @param pkValue 值
	 * @param tableName 表名
	 * @return
	 * @throws BusinessException
	 */
	public static HashMap<String, String> getDocMapByPk(String table, String pkKey, String pkValue, String tableName) throws BusinessException {
		HashMap<String, String> docInfo = null;
		String sql = "";
		try {
			sql = "select * from " + table + " where " + pkKey + " = '" + pkValue + "'";
			ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) getDao().executeQuery(sql, new MapListProcessor());
			if (list != null && list.size() > 0) {
				docInfo = list.get(0);
			} else {
				throw new BusinessException("没有获取到数据！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取"+tableName+"出错！SQL：" + sql + "，错误信息：" + e.getMessage());
		}
		return docInfo;
	}
}
