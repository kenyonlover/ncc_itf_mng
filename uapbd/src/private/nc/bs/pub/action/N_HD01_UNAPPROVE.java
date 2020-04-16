
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin.ItfConfigBillVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.uapbd.uapbd.IItfconfigbillvoMaintain;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HD01_UNAPPROVE extends AbstractPfAction<AggItfConfigBillVO> {

        @Override
        protected CompareAroundProcesser<AggItfConfigBillVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggItfConfigBillVO> processor = new CompareAroundProcesser<AggItfConfigBillVO>(
                                ItfConfigBillVOPluginPoint.UNAPPROVE);
                // TODO 在此处添加前后规则
                processor.addBeforeRule(new UnapproveStatusCheckRule());

                return processor;
        }

        @Override
        protected AggItfConfigBillVO[] processBP(Object userObj,
                        AggItfConfigBillVO[] clientFullVOs, AggItfConfigBillVO[] originBills) {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AggItfConfigBillVO[] bills = null;
                try {
                        IItfconfigbillvoMaintain operator = NCLocator.getInstance()
                                        .lookup(IItfconfigbillvoMaintain.class);
                        bills = operator.unapprove(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return bills;
        }

}
