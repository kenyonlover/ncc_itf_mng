
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceItfConfigBillVOUnApproveBP {

        public AggItfConfigBillVO[] unApprove(AggItfConfigBillVO[] clientBills,
                        AggItfConfigBillVO[] originBills) {
                for (AggItfConfigBillVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggItfConfigBillVO> update = new BillUpdate<AggItfConfigBillVO>();
                AggItfConfigBillVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }
}
