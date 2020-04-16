
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin.ItfConfigBillVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.uapbd.uapbd.IItfconfigbillvoMaintain;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HD01_DELETE extends AbstractPfAction<AggItfConfigBillVO> {

        @Override
        protected CompareAroundProcesser<AggItfConfigBillVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggItfConfigBillVO> processor = new CompareAroundProcesser<AggItfConfigBillVO>(
                                ItfConfigBillVOPluginPoint.SCRIPT_DELETE);
                // TODO 在此处添加前后规则
                return processor;
        }

        @Override
        protected AggItfConfigBillVO[] processBP(Object userObj,
                        AggItfConfigBillVO[] clientFullVOs, AggItfConfigBillVO[] originBills) {
                IItfconfigbillvoMaintain operator = NCLocator.getInstance().lookup(
                                IItfconfigbillvoMaintain.class);
                try {
                        operator.delete(clientFullVOs, originBills);
                } catch (BusinessException e) {
                        ExceptionUtils.wrappBusinessException(e.getMessage());
                }
                return clientFullVOs;
        }

}
