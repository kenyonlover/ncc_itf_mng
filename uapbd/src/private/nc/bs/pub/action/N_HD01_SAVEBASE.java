
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin.ItfConfigBillVOPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.itf.uapbd.uapbd.IItfconfigbillvoMaintain;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HD01_SAVEBASE extends AbstractPfAction<AggItfConfigBillVO> {

        @Override
        protected CompareAroundProcesser<AggItfConfigBillVO> getCompareAroundProcesserWithRules(
                        Object userObj) {
                CompareAroundProcesser<AggItfConfigBillVO> processor = null;
                AggItfConfigBillVO[] clientFullVOs = (AggItfConfigBillVO[]) this.getVos();
                if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                                .getPrimaryKey())) {
                        processor = new CompareAroundProcesser<AggItfConfigBillVO>(
                                        ItfConfigBillVOPluginPoint.SCRIPT_UPDATE);
                } else {
                        processor = new CompareAroundProcesser<AggItfConfigBillVO>(
                                        ItfConfigBillVOPluginPoint.SCRIPT_INSERT);
                }
                // TODO 在此处添加前后规则
                IRule<AggItfConfigBillVO> rule = null;

                return processor;
        }

        @Override
        protected AggItfConfigBillVO[] processBP(Object userObj,
                        AggItfConfigBillVO[] clientFullVOs, AggItfConfigBillVO[] originBills) {

                AggItfConfigBillVO[] bills = null;
            try {
              IItfconfigbillvoMaintain operator =
                  NCLocator.getInstance().lookup(IItfconfigbillvoMaintain.class);
              if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
                  .getPrimaryKey())) {
                bills = operator.update(clientFullVOs, originBills);
              }
              else {
                bills = operator.insert(clientFullVOs, originBills);
              }
            }
            catch (BusinessException e) {
              ExceptionUtils.wrappBusinessException(e.getMessage());
            }
            return bills;
        }
}
