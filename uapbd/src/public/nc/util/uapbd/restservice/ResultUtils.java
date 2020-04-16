package nc.util.uapbd.restservice;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import nc.vo.pub.BusinessException;

public class ResultUtils {
	
	/**
	 * �ӿڷ��ؽ��
	 * @param status ״̬
	 * @param message ��Ϣ
	 * @return
	 * @throws BusinessException
	 */
	public static String getResult(String status, String message) throws BusinessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		map.put("message", message);
		String remsg = JSON.toJSONString(map);
		return remsg;
	}
}
