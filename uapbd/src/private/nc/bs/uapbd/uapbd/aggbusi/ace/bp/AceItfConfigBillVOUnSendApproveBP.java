
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceItfConfigBillVOUnSendApproveBP {

        public AggItfConfigBillVO[] unSend(AggItfConfigBillVO[] clientBills,
                        AggItfConfigBillVO[] originBills) {
                // 把VO持久化到数据库中
                this.setHeadVOStatus(clientBills);
                BillUpdate<AggItfConfigBillVO> update = new BillUpdate<AggItfConfigBillVO>();
                AggItfConfigBillVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

        private void setHeadVOStatus(AggItfConfigBillVO[] clientBills) {
                for (AggItfConfigBillVO clientBill : clientBills) {
                        clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.FREE.value());
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
        }
}
