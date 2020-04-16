
package nc.vo.uapbd.itfconfig;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggItfConfigBillVOMeta extends AbstractBillMeta{

public AggItfConfigBillVOMeta(){
this.init();
}

private void init() {
this.setParent(nc.vo.uapbd.itfconfig.ItfConfigBillVO.class);
        this.addChildren(nc.vo.uapbd.itfconfig.ItfConfigItemVO.class);
}
}