package nc.util.uapbd.restservice;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import nc.vo.pub.BusinessException;

public class ResultUtils {
	
	/**
	 * 接口返回结果
	 * @param status 状态
	 * @param message 消息
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
