
package nc.vo.uapbd.itfconfig;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.uapbd.itfconfig.ItfConfigBillVO;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.uapbd.itfconfig.ItfConfigBillVO")
public class AggItfConfigBillVO extends AbstractBill {

@Override
public IBillMeta getMetaData() {
IBillMeta billMeta = BillMetaFactory.getInstance().getBillMeta(
AggItfConfigBillVOMeta.class);
return billMeta;
}

@Override
public ItfConfigBillVO getParentVO() {
return (ItfConfigBillVO) this.getParent();
}

@Override
public String getPrimaryKey() {
return super.getPrimaryKey();
}
}