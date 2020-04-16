
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;


import nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin.ItfConfigBillVOPluginPoint;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceItfConfigBillVODeleteBP {

        public void delete(AggItfConfigBillVO[] bills) {

                DeleteBPTemplate<AggItfConfigBillVO> bp = new DeleteBPTemplate<AggItfConfigBillVO>(
ItfConfigBillVOPluginPoint.DELETE);
                // 增加执行前规则
                this.addBeforeRule(bp.getAroundProcesser());
                // 增加执行后业务规则
                this.addAfterRule(bp.getAroundProcesser());
                bp.delete(bills);
        }

        private void addBeforeRule(AroundProcesser<AggItfConfigBillVO> processer) {
                // TODO 前规则
//              IRule<AggItfConfigBillVO> rule = null;
//              rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//              processer.addBeforeRule(rule);
        }

        /**
         * 删除后业务规则
         *
         * @param processer
         */
        private void addAfterRule(AroundProcesser<AggItfConfigBillVO> processer) {
                // TODO 后规则

        }
}
