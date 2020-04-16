package nc.impl.uapbd.service;

import org.apache.log4j.Logger;

import nc.bs.dao.BaseDAO;
import nc.itf.uapbd.service.IBusinessBillLog;
import nc.util.uapbd.service.ServiceUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public class BusinessBillLogImpl implements IBusinessBillLog {
	
	Logger loger = Logger.getLogger(BusinessBillLogImpl.class);
	
	private BaseDAO getDao() {
		return ServiceUtils.getBaseDAO();
	}

	@Override
	public String saveWithVO_RequiresNew(SuperVO vo) throws BusinessException {
		String pk_log = "";
		try {
			pk_log = getDao().insertVO(vo);
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("【" + vo.getAttributeValue("billno") + "】插入日志错误：" + e.getMessage());
		}
		return pk_log;
	}

	@Override
	public int updateWithVO_RequiresNew(SuperVO vo) throws BusinessException {
		int num = 0;
		try {
			num = getDao().updateVO(vo);
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("【" + vo.getAttributeValue("billno") + "】更新日志错误：" + e.getMessage());
		}
		return num;
	}

}
