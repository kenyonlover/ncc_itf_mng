package nc.util.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.vo.pub.BusinessException;

/**
 * �������ݹ��߼�
 * 
 * @author 30798
 */
public class CreateBillUtils {

	private static BaseDAO getDao() {
		return ServiceUtils.getBaseDAO();
	}

	/**
	 * ��ȡ����
	 * 
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public static String getPk_Countryzone(String code) throws BusinessException {
		String sql = "select pk_country from bd_countryzone where code = '" + code + "' ";
		String docId = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(docId, "��ȡ���ҳ���SQL��" + sql + "��������Ϣ��û�л�ȡ�����ݣ�");
		return docId;
	}

	/**
	 * ��ȡ����
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public static String getPk_Currtype(String code) throws BusinessException {
		String sql = "select pk_currtype from bd_currtype where code = '" + code + "'";
		String docId = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(docId, "��ȡ���ֳ���SQL��" + sql + "��������Ϣ��û�л�ȡ�����ݣ�");
		return docId;
	}
	
	/**
	 * ��ȡ��������λ
	 * @param pk_material
	 * @return
	 * @throws BusinessException
	 */
	public static String getPk_measdoc(String pk_material) throws BusinessException {
		String sql = "select pk_measdoc from bd_material where pk_material ='" + pk_material + "'";
		String docId = (String) getDao().executeQuery(sql, new ColumnProcessor());
		AssertUtils.stringIsNull(docId, "��ȡ��������λ����SQL��" + sql + "��������Ϣ��û�л�ȡ�����ݣ�");
		return docId;
	}
	
	/**
	 * ��ȡ����˰��
	 */
	public static HashMap<String, String> getTaxInfo(String pk_material) throws BusinessException {
		String sql = "select * from bd_taxrate where pk_taxcode = "
					+ " (select pk_taxcode from bd_taxcode where mattaxes = "
					+ " (select pk_mattaxes from bd_mattaxes where pk_mattaxes = "
					+ " (select pk_mattaxes from bd_material where pk_material = '" + pk_material + "')))";

		HashMap<String, String> map = (HashMap<String, String>) getDao().executeQuery(sql, new MapProcessor());
		AssertUtils.mapIsNull(map, "��ȡ����˰�����SQL��" + sql + "��������Ϣ��û�л�ȡ�����ݣ�");
		return map;
	}
	
	/**
	 * ����������ѯ����
	 * @param table ��
	 * @param doc Ҫ��ѯ���ֶ�
	 * @param pkKey ����
	 * @param pkValue ֵ
	 * @param tableName ����
	 * @return
	 * @throws BusinessException
	 */
	public static String getDocByPk(String table, String doc, String pkKey, String pkValue, String tableName) throws BusinessException {
		String sql = "";
		String docValue = "";
		try {
			sql = "select " + doc + " from " + table + " where " + pkKey + " = '" + pkValue + "'";
			docValue = (String) getDao().executeQuery(sql, new ColumnProcessor());
			AssertUtils.stringIsNull(docValue, "û�л�ȡ�����ݣ�");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ"+tableName+"����SQL��" + sql + "��������Ϣ��" + e.getMessage());
		}
		return docValue;
	}
	
	/**
	 * ����������ѯMap����
	 * @param table ��
	 * @param pkKey ����
	 * @param pkValue ֵ
	 * @param tableName ����
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
				throw new BusinessException("û�л�ȡ�����ݣ�");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ"+tableName+"����SQL��" + sql + "��������Ϣ��" + e.getMessage());
		}
		return docInfo;
	}
}
