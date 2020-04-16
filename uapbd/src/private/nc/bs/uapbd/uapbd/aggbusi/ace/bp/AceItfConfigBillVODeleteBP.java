
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;


import nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin.ItfConfigBillVOPluginPoint;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceItfConfigBillVODeleteBP {

        public void delete(AggItfConfigBillVO[] bills) {

                DeleteBPTemplate<AggItfConfigBillVO> bp = new DeleteBPTemplate<AggItfConfigBillVO>(
ItfConfigBillVOPluginPoint.DELETE);
                // ����ִ��ǰ����
                this.addBeforeRule(bp.getAroundProcesser());
                // ����ִ�к�ҵ�����
                this.addAfterRule(bp.getAroundProcesser());
                bp.delete(bills);
        }

        private void addBeforeRule(AroundProcesser<AggItfConfigBillVO> processer) {
                // TODO ǰ����
//              IRule<AggItfConfigBillVO> rule = null;
//              rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//              processer.addBeforeRule(rule);
        }

        /**
         * ɾ����ҵ�����
         *
         * @param processer
         */
        private void addAfterRule(AroundProcesser<AggItfConfigBillVO> processer) {
                // TODO �����

        }
}
