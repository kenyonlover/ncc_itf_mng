package nc.itf.uapbd.service;

import java.util.HashMap;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public interface IBusinessBillSave {

	public void saveBill(HashMap<String, HashMap<String, AggregatedValueObject>> voOprationMap) throws BusinessException;
	
}
