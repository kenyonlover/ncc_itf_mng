
package nc.uapbd.uapbd.itfconfigbillvo.action;

import java.util.Collection;

import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.uapbd.uapbd.ItfConfigBillVOConst;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.uapbd.itfconfig.ItfConfigBillVO;
import nc.vo.pf.pub.util.SQLUtil;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nc.uapbd.uapbd.itfconfigbillvo.uapbd.bean.PageQueryInfo;
import nc.uapbd.uapbd.itfconfigbillvo.uapbd.util.CommonUtil;

/**
 * ɾ����֧��������
 * @version  @since v3.5.6-1903
 */
public class ItfConfigBillVODeleteAction implements ICommonAction {

  @Override
  public Object doAction(IRequest paramIRequest) {
    PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
    try {
      // 1������ǰ�˴��ݵ�pks��ѯ����,��ȡAGGVO
      AggItfConfigBillVO[] bills = this.queryBills(queryParam);
      // 2�����õ��ݵı��涯���ű���delete�����õ��������
      this.callActionScript(bills);
      // 3������Null ��ǰ��
      return null;
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
    return ItfConfigBillVOConst.CONST_ACTION_DELETE;
  }

  /**
   * ���ö����ű�
   *
   * @param actionCode
   * @param aggVOs
   * @return
   * @throws BusinessException
   */
  private Object callActionScript(AggItfConfigBillVO... aggVOs)
      throws BusinessException {

    if ((aggVOs == null) || (aggVOs.length == 0)) {
      return null;
    }

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

    return result;
  }

  /**
   * ��ȡ��ѯ����
   *
   * @param paramIRequest
   * @return
   */
  private PageQueryInfo getQueryParam(IRequest paramIRequest) {
    String strRead = paramIRequest.read();
    PageQueryInfo queryParam =
        JsonFactory.create().fromJson(strRead, PageQueryInfo.class);
    return queryParam;
  }

  /**
   * ��ѯҵ������
   *
   * @param queryParam
   * @return
   * @throws MetaDataException
   */
  private AggItfConfigBillVO[] queryBills(PageQueryInfo queryParam)
      throws MetaDataException {
    // 1�����ݲ�����ѯ���
    IMDPersistenceQueryService service = CommonUtil.getMDQueryService();
    String wheresql =
        SQLUtil.buildSqlForIn( "pk_itfconfigbill",
            queryParam.getPks());
    Collection<AggItfConfigBillVO> bills =
        service.queryBillOfVOByCond(AggItfConfigBillVO.class, wheresql, true,
            false);
    if ((bills == null) || (bills.size() == 0)) {
      return null;
    }
    return bills.toArray(new AggItfConfigBillVO[0]);
  }
}
