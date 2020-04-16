
package nc.uapbd.uapbd.itfconfigbillvo.action;

import java.util.Collection;

import nc.bs.logging.Logger;
import nc.vo.uapbd.uapbd.ItfConfigBillVOConst;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.uapbd.itfconfig.ItfConfigBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nc.uapbd.uapbd.itfconfigbillvo.uapbd.util.CommonUtil;

/**
 * ���ӱ���
 * @version  @since v3.5.6-1903
 */
public class ItfConfigBillVOSaveAction implements ICommonAction {

  @Override
  public Object doAction(IRequest paramIRequest) {
    try {
      BillCardOperator billCardOperator = new BillCardOperator();
      // 1����ȡAGGVO ��requestת������VO��
AggItfConfigBillVO vo = billCardOperator.toBill(paramIRequest);
      this.doBefore(vo);
      // 2�����õ��ݵı��涯���ű���savebase�����õ��������
AggItfConfigBillVO rtnObj = this.callActionScript(vo);
      // 3�������ؽ�����������ܣ�����ģ��ת��ǰ��BillCard�����շ��룬��ʾ��ʽ����
      Object billcard = billCardOperator.toCard(rtnObj);
      // 4�����ؽ����ǰ��
      return billcard;
    }
    catch (BusinessException ex) {
      // �����쳣��Ϣ
      Logger.error(ex);
      ExceptionUtils.wrapException(ex);
    }
    return null;
  }

  /**
   * ��������
   *
   * @return
   */
  protected String getActionCode() {
    return ItfConfigBillVOConst.CONST_ACTION_SAVEBASE;
  }

  /**
   * ���ö����ű�
   *
   * @param actionCode
   * @param aggVOs
   * @return
   * @throws BusinessException
   */
  private AggItfConfigBillVO callActionScript(AggItfConfigBillVO... aggVOs)
      throws BusinessException {

    String actionCode = this.getActionCode();
    String billType = CommonUtil.getBillTypeCode();

    CloudPFlowContext context = new CloudPFlowContext();
    context.setActionName(actionCode);
    context.setBillType(billType);
    context.setBillVos(aggVOs);
    Logger.debug("��ʼ���ö����ű� ActionName[" + actionCode + "] BillType[" + billType
        + "]...");

    ICloudScriptPFlowService service =
        ServiceLocator.find(ICloudScriptPFlowService.class);

    Object[] result = service.exeScriptPFlow(context);

    Logger.debug("���ö����ű� ActionName[" + actionCode + "] BillType[" + billType
        + "]����");

    String wheresql =
        "pk_itfconfigbill" + "='"
            + ((AggItfConfigBillVO) result[0]).getPrimaryKey() + "'";
    Collection<AggItfConfigBillVO> bills =
        CommonUtil.getMDQueryService().queryBillOfVOByCond(
            AggItfConfigBillVO.class, wheresql, true, false);
    return bills.toArray(new AggItfConfigBillVO[0])[0];
  }

    /**
    * �ж��������޸�
    *
    * @param vo
    * @throws BusinessException
    */
  private void doBefore(AggItfConfigBillVO vo) throws BusinessException {
    String parentPk = vo.getPrimaryKey();
    // �����Ƿ���������Ϣ�ж����������滹���޸ı���
    if ((parentPk != null) && !"".equals(parentPk)) {
      // ���õ���Ĭ��ֵ
      CircularlyAccessibleValueObject[] allchildren = vo.getAllChildrenVO();
      if (allchildren != null) {
        for (CircularlyAccessibleValueObject obj : allchildren) {
          obj.setAttributeValue("pk_itfconfigbill", parentPk);
        }
      }
    }
    nc.vo.uapbd.itfconfig.ItfConfigBillVO parent = vo.getParentVO();
    String org =
        (String) parent.getAttributeValue(ItfConfigBillVOConst.FIELD_PK_ORG);
    String group = CommonUtil.getGroupByOrg(org);
    if ((group != null) && !"".equals(group)) {
      parent.setAttributeValue(ItfConfigBillVOConst.FIELD_PK_GROUP, group);
    }

  }
}
