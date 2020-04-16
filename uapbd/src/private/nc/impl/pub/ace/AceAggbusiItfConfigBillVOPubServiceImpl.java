
package nc.impl.pub.ace;

import nc.bs.uapbd.uapbd.aggbusi.ace.bp.AceItfConfigBillVOApproveBP;
import nc.bs.uapbd.uapbd.aggbusi.ace.bp.AceItfConfigBillVODeleteBP;
import nc.bs.uapbd.uapbd.aggbusi.ace.bp.AceItfConfigBillVOInsertBP;
import nc.bs.uapbd.uapbd.aggbusi.ace.bp.AceItfConfigBillVOSendApproveBP;
import nc.bs.uapbd.uapbd.aggbusi.ace.bp.AceItfConfigBillVOUnApproveBP;
import nc.bs.uapbd.uapbd.aggbusi.ace.bp.AceItfConfigBillVOUnSendApproveBP;
import nc.bs.uapbd.uapbd.aggbusi.ace.bp.AceItfConfigBillVOUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceAggbusiItfConfigBillVOPubServiceImpl {
        // ����
        public AggItfConfigBillVO[] pubinsertBills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                try {
                        // ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
                        BillTransferTool<AggItfConfigBillVO> transferTool = new BillTransferTool<AggItfConfigBillVO>(
                                        clientFullVOs);
                        // ����BP
                        AceItfConfigBillVOInsertBP action = new AceItfConfigBillVOInsertBP();
                        AggItfConfigBillVO[] retvos = action.insert(clientFullVOs);
                        // ���췵������
//                      return transferTool.getBillForToClient(retvos);
                        return retvos;
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        // ɾ��
        public void pubdeleteBills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                try {
                        // ����BP
                        new AceItfConfigBillVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // �޸�
        public AggItfConfigBillVO[] pubupdateBills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                try {
                        // ���� + ���ts
                        BillTransferTool<AggItfConfigBillVO> transferTool = new BillTransferTool<AggItfConfigBillVO>(
                                        clientFullVOs);
                        AceItfConfigBillVOUpdateBP bp = new AceItfConfigBillVOUpdateBP();
                        AggItfConfigBillVO[] retvos = bp.update(clientFullVOs, originBills);
                        // ���췵������
//                      return transferTool.getBillForToClient(retvos);
                        return retvos;
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        public AggItfConfigBillVO[] pubquerybills(IQueryScheme queryScheme)
                        throws BusinessException {
                AggItfConfigBillVO[] bills = null;
                try {
                        this.preQuery(queryScheme);
                        BillLazyQuery<AggItfConfigBillVO> query = new BillLazyQuery<AggItfConfigBillVO>(
                                        AggItfConfigBillVO.class);
                        bills = query.query(queryScheme, null);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return bills;
        }

        /**
         * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
         * 
         * @param queryScheme
         */
        protected void preQuery(IQueryScheme queryScheme) {
                // ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
        }

        // �ύ
        public AggItfConfigBillVO[] pubsendapprovebills(
                        AggItfConfigBillVO[] clientFullVOs, AggItfConfigBillVO[] originBills)
                        throws BusinessException {
                AceItfConfigBillVOSendApproveBP bp = new AceItfConfigBillVOSendApproveBP();
                AggItfConfigBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // �ջ�
        public AggItfConfigBillVO[] pubunsendapprovebills(
                        AggItfConfigBillVO[] clientFullVOs, AggItfConfigBillVO[] originBills)
                        throws BusinessException {
                AceItfConfigBillVOUnSendApproveBP bp = new AceItfConfigBillVOUnSendApproveBP();
                AggItfConfigBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // ����
        public AggItfConfigBillVO[] pubapprovebills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AceItfConfigBillVOApproveBP bp = new AceItfConfigBillVOApproveBP();
                AggItfConfigBillVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // ����

        public AggItfConfigBillVO[] pubunapprovebills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AceItfConfigBillVOUnApproveBP bp = new AceItfConfigBillVOUnApproveBP();
                AggItfConfigBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
                return retvos;
        }

}