package nc.util.uapbd.service;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * ���Թ���
 * @author 30798
 */
public class AssertUtils {
	
	/**
	 * �ַ���Ϊ��
	 * @param str
	 * @param tips
	 * @throws BusinessException
	 */
	public static void stringIsNull(String str,String tips) throws BusinessException {
		if (str == null || "".equals(str.trim().toString())) {
			throw new BusinessException(tips);
		}
	}
	
	/**
	 * MapΪ��
	 * @param map
	 * @param tips
	 * @throws BusinessException
	 */
	public static void mapIsNull(Map map,String tips) throws BusinessException {
		if (map == null || map.size() == 0) {
			throw new BusinessException(tips);
		}
	}
	
	/**
	 * ListΪ��
	 * @param list
	 * @param tips
	 * @throws BusinessException
	 */
	public static void listIsNull(List list,String tips) throws BusinessException {
		if (list == null || list.size() == 0) {
			throw new BusinessException(tips);
		}
	}
}
