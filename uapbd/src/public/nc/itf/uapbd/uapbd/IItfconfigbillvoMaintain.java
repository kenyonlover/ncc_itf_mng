
package nc.itf.uapbd.uapbd;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.pub.BusinessException;

public interface IItfconfigbillvoMaintain {

        public void delete(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException;

        public AggItfConfigBillVO[] insert(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException;

        public AggItfConfigBillVO[] update(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException;

        public AggItfConfigBillVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggItfConfigBillVO[] save(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException;

        public AggItfConfigBillVO[] unsave(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException;

        public AggItfConfigBillVO[] approve(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException;

        public AggItfConfigBillVO[] unapprove(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException;
}
