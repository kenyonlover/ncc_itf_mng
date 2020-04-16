
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;

import nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin.ItfConfigBillVOPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;

/**
 * 标准单据新增BP
 */
public class AceItfConfigBillVOInsertBP {

        public AggItfConfigBillVO[] insert(AggItfConfigBillVO[] bills) {

                InsertBPTemplate<AggItfConfigBillVO> bp = new InsertBPTemplate<AggItfConfigBillVO>(
ItfConfigBillVOPluginPoint.INSERT);
                this.addBeforeRule(bp.getAroundProcesser());
                this.addAfterRule(bp.getAroundProcesser());
                return bp.insert(bills);

        }

        /**
         * 新增后规则
         *
         * @param processor
         */
        private void addAfterRule(AroundProcesser<AggItfConfigBillVO> processor) {
                IRule<AggItfConfigBillVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("HD01");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
                                .setCodeItem("billno");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
                processor.addAfterRule(rule);
        }

        /**
         * 新增前规则
         *
         * @param processor
         */
        private void addBeforeRule(AroundProcesser<AggItfConfigBillVO> processer) {
                IRule<AggItfConfigBillVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
                processer.addBeforeRule(rule);
                rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("HD01");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setCodeItem("billno");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
                processer.addBeforeRule(rule);
        }
}
