package nc.itf.uapbd.service;

import java.util.HashMap;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public interface ICreateF0BillVO {
	
	AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group) throws BusinessException;

}
