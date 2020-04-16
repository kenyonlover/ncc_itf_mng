
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
        // 新增
        public AggItfConfigBillVO[] pubinsertBills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                try {
                        // 数据库中数据和前台传递过来的差异VO合并后的结果
                        BillTransferTool<AggItfConfigBillVO> transferTool = new BillTransferTool<AggItfConfigBillVO>(
                                        clientFullVOs);
                        // 调用BP
                        AceItfConfigBillVOInsertBP action = new AceItfConfigBillVOInsertBP();
                        AggItfConfigBillVO[] retvos = action.insert(clientFullVOs);
                        // 构造返回数据
//                      return transferTool.getBillForToClient(retvos);
                        return retvos;
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
                return null;
        }

        // 删除
        public void pubdeleteBills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                try {
                        // 调用BP
                        new AceItfConfigBillVODeleteBP().delete(clientFullVOs);
                } catch (Exception e) {
                        ExceptionUtils.marsh(e);
                }
        }

        // 修改
        public AggItfConfigBillVO[] pubupdateBills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                try {
                        // 加锁 + 检查ts
                        BillTransferTool<AggItfConfigBillVO> transferTool = new BillTransferTool<AggItfConfigBillVO>(
                                        clientFullVOs);
                        AceItfConfigBillVOUpdateBP bp = new AceItfConfigBillVOUpdateBP();
                        AggItfConfigBillVO[] retvos = bp.update(clientFullVOs, originBills);
                        // 构造返回数据
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
         * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
         * 
         * @param queryScheme
         */
        protected void preQuery(IQueryScheme queryScheme) {
                // 查询之前对queryScheme进行加工，加入自己的逻辑
        }

        // 提交
        public AggItfConfigBillVO[] pubsendapprovebills(
                        AggItfConfigBillVO[] clientFullVOs, AggItfConfigBillVO[] originBills)
                        throws BusinessException {
                AceItfConfigBillVOSendApproveBP bp = new AceItfConfigBillVOSendApproveBP();
                AggItfConfigBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
                return retvos;
        }

        // 收回
        public AggItfConfigBillVO[] pubunsendapprovebills(
                        AggItfConfigBillVO[] clientFullVOs, AggItfConfigBillVO[] originBills)
                        throws BusinessException {
                AceItfConfigBillVOUnSendApproveBP bp = new AceItfConfigBillVOUnSendApproveBP();
                AggItfConfigBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
                return retvos;
        };

        // 审批
        public AggItfConfigBillVO[] pubapprovebills(AggItfConfigBillVO[] clientFullVOs,
                        AggItfConfigBillVO[] originBills) throws BusinessException {
                for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
                        clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
                }
                AceItfConfigBillVOApproveBP bp = new AceItfConfigBillVOApproveBP();
                AggItfConfigBillVO[] retvos = bp.approve(clientFullVOs, originBills);
                return retvos;
        }

        // 弃审

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