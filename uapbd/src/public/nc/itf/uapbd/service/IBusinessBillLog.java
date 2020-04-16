package nc.itf.uapbd.service;

import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public interface IBusinessBillLog {
	/**
	 * 另起事务插入日志
	 * @param vo
	 * @return 插入日志的主键
	 * @throws BusinessException
	 */
	public String saveWithVO_RequiresNew(SuperVO vo) throws BusinessException;
	/**
	 * 另起事务更新日志
	 * @param vo
	 * @return 更新成功数量
	 * @throws BusinessException
	 */
	public int updateWithVO_RequiresNew(SuperVO vo) throws BusinessException;
}
