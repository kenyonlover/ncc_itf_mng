
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;

import nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin.ItfConfigBillVOPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;

/**
 * �޸ı����BP
 *
 */
public class AceItfConfigBillVOUpdateBP {

        public AggItfConfigBillVO[] update(AggItfConfigBillVO[] bills,
                        AggItfConfigBillVO[] originBills) {
                // �����޸�ģ��
                UpdateBPTemplate<AggItfConfigBillVO> bp = new UpdateBPTemplate<AggItfConfigBillVO>(
ItfConfigBillVOPluginPoint.UPDATE);
                // ִ��ǰ����
                this.addBeforeRule(bp.getAroundProcesser());
                // ִ�к����
                this.addAfterRule(bp.getAroundProcesser());
                return bp.update(bills, originBills);
        }

        private void addAfterRule(CompareAroundProcesser<AggItfConfigBillVO> processer) {
                // TODO �����
                IRule<AggItfConfigBillVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("HD01");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
                                .setCodeItem("billno");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
                processer.addAfterRule(rule);

        }

        private void addBeforeRule(CompareAroundProcesser<AggItfConfigBillVO> processer) {
                // TODO ǰ����
                IRule<AggItfConfigBillVO> rule = null;
                rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
                processer.addBeforeRule(rule);
                nc.impl.pubapp.pattern.rule.ICompareRule<AggItfConfigBillVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
                ((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
                                .setCbilltype("HD01");
                ((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
                                .setCodeItem("billno");
                ((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
                                .setGroupItem("pk_group");
                ((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
                                .setOrgItem("pk_org");
                processer.addBeforeRule(ruleCom);
        }

}
