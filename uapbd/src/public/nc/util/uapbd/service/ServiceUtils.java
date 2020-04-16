package nc.util.uapbd.service;

import nc.bs.dao.BaseDAO;

public class ServiceUtils {
	
	private static BaseDAO dao;
	
	public static BaseDAO getBaseDAO() {
		if(dao == null) {
			dao = new BaseDAO();
		}
		return dao;
	}
	
}
