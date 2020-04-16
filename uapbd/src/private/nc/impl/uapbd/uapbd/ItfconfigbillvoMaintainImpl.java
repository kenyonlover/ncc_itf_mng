
package nc.impl.uapbd.uapbd;

import nc.impl.pub.ace.AceAggbusiItfConfigBillVOPubServiceImpl;
import nc.itf.uapbd.uapbd.IItfconfigbillvoMaintain ;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.pub.BusinessException;

public class ItfconfigbillvoMaintainImpl extends AceAggbusiItfConfigBillVOPubServiceImpl implements IItfconfigbillvoMaintain  {

        @Override
        public void delete(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggItfConfigBillVO[] insert(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggItfConfigBillVO[] update(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggItfConfigBillVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggItfConfigBillVO[] save(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggItfConfigBillVO[] unsave(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggItfConfigBillVO[] approve(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggItfConfigBillVO[] unapprove(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                return super.pubunapprovebills(clientFullVOs, originBills);
        }

}
