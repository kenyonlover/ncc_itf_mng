package nc.itf.uapbd.service;

import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public interface IBusinessBillLog {
	/**
	 * �������������־
	 * @param vo
	 * @return ������־������
	 * @throws BusinessException
	 */
	public String saveWithVO_RequiresNew(SuperVO vo) throws BusinessException;
	/**
	 * �������������־
	 * @param vo
	 * @return ���³ɹ�����
	 * @throws BusinessException
	 */
	public int updateWithVO_RequiresNew(SuperVO vo) throws BusinessException;
}
