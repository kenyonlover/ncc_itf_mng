
package nc.bs.uapbd.uapbd.aggbusi.ace.bp;

import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceItfConfigBillVOSendApproveBP {
        /**
         * ������
         *
         * @param vos
         *            ����VO����
         * @param script
         *            ���ݶ����ű�����
         * @return �����ĵ���VO����
         */

        public AggItfConfigBillVO[] sendApprove(AggItfConfigBillVO[] clientBills,
                        AggItfConfigBillVO[] originBills) {
                for (AggItfConfigBillVO clientFullVO : clientBills) {
                        clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
                                        BillStatusEnum.COMMIT.value());
                        clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
                }
                // ���ݳ־û�
                AggItfConfigBillVO[] returnVos = new BillUpdate<AggItfConfigBillVO>().update(
                                clientBills, originBills);
                return returnVos;
        }
}
