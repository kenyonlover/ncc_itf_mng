
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;

/**
 * ��׼������˵�BP
 */
public class AceItfConfigBillVOApproveBP {

        /**
         * ��˶���
         * 
         * @param vos
         * @param script
         * @return
         */
        public AggItfConfigBillVO[] approve(AggItfConfigBillVO[] clientBills,
                        AggItfConfigBillVO[] originBills) {
                for (AggItfConfigBillVO clientBill : clientBills) {
                        clientBill.getParentVO().setStatus(VOStatus.UPDATED);
                }
                BillUpdate<AggItfConfigBillVO> update = new BillUpdate<AggItfConfigBillVO>();
                AggItfConfigBillVO[] returnVos = update.update(clientBills, originBills);
                return returnVos;
        }

}
