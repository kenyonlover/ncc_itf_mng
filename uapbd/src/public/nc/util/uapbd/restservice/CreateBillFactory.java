package nc.util.uapbd.restservice;

import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uapbd.service.ICreate45BillVO;
import nc.itf.uapbd.service.ICreate46BillVO;
import nc.itf.uapbd.service.ICreate4ABillVO;
import nc.itf.uapbd.service.ICreate4CBillVO;
import nc.itf.uapbd.service.ICreate4DBillVO;
import nc.itf.uapbd.service.ICreate4IBillVO;
import nc.itf.uapbd.service.ICreateF0BillVO;
import nc.itf.uapbd.service.ICreateF1BillVO;
import nc.itf.uapbd.service.ICreateF2BillVO;
import nc.itf.uapbd.service.ICreateF3BillVO;
import nc.itf.uapbd.service.ICreateI9BillVO;
import nc.itf.uapbd.service.ICreateIABillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class CreateBillFactory {
	public static AggregatedValueObject createBill(String nccBillTypeCode,
			HashMap<String, Object> nccBillMap) throws BusinessException {
		AggregatedValueObject aggVO = null;
		String groupId = InvocationInfoProxy.getInstance().getGroupId();
		switch (nccBillTypeCode) {
		case "45":// ���ɹ���ⵥ
			aggVO = NCLocator.getInstance().lookup(ICreate45BillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "46":// ������Ʒ��ⵥ
			aggVO = NCLocator.getInstance().lookup(ICreate46BillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "4A":// ���������ⵥ
			aggVO = NCLocator.getInstance().lookup(ICreate4ABillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "4C":// ������۳��ⵥ
			aggVO = NCLocator.getInstance().lookup(ICreate4CBillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "4D":// �����ϳ��ⵥ
			aggVO = NCLocator.getInstance().lookup(ICreate4DBillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "4I":// ����������ⵥ
			aggVO = NCLocator.getInstance().lookup(ICreate4IBillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "F0":// Ӧ�յ�
			aggVO = NCLocator.getInstance().lookup(ICreateF0BillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "F1":// Ӧ����
			aggVO = NCLocator.getInstance().lookup(ICreateF1BillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "F2":// �տ
			aggVO = NCLocator.getInstance().lookup(ICreateF2BillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "F3":// ���
			aggVO = NCLocator.getInstance().lookup(ICreateF3BillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "I9":// �������-��������
			aggVO = NCLocator.getInstance().lookup(ICreateI9BillVO.class).createBillVO(nccBillMap, groupId);
			break;
		case "IA":// �������-���������
			aggVO = NCLocator.getInstance().lookup(ICreateIABillVO.class).createBillVO(nccBillMap, groupId);
			break;
		default:
			throw new BusinessException("���ݵ������ͱ��롾"+ nccBillTypeCode +"������VO����ҵ��ϵͳ�ӿ��������޴����ͣ����飡");

		}
		return aggVO;
	}
}
