
package nc.uapbd.uapbd.itfconfigbillvo.action;
import java.util.Collection;

import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.uapbd.itfconfig.AggItfConfigBillVO;
import nc.vo.uapbd.itfconfig.ItfConfigBillVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nc.uapbd.uapbd.itfconfigbillvo.uapbd.bean.PageQueryInfo;
import nc.uapbd.uapbd.itfconfigbillvo.uapbd.util.CommonUtil;

/**
 * ��Ƭ��ѯ����ѯ����������
 * @version  @since v3.5.6-1903
 */
public class ItfConfigBillVOQueryCardAction implements ICommonAction {

  @Override
  public Object doAction(IRequest paramIRequest) {
    PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
    try {
      // ��ѯ����
      Collection<AggItfConfigBillVO> bills = this.queryBills(queryParam);
      if (bills == null) {
        return null;
      }
      // ת��ǰ����Ҫ��billCard
      return this.transBillCardFormVO(queryParam,
          bills.toArray(new AggItfConfigBillVO[0])[0]);
    }
    catch (BusinessException ex) {
      // �����쳣��Ϣ
      Logger.error(ex);
      ExceptionUtils.wrapException(ex);
    }
    return null;
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
  private Collection<AggItfConfigBillVO> queryBills(PageQueryInfo queryParam)
      throws MetaDataException {
    IMDPersistenceQueryService service = CommonUtil.getMDQueryService();
    // ע�⣺ҵ���ṩ��ѯ������Ԫ���ݲ�ѯĬ�ϲ�ѯ��dr=1�������ˣ�
    String wheresql =
        "pk_itfconfigbill" + "='" + queryParam.getPk() + "'";
    Collection<AggItfConfigBillVO> bills =
        service.queryBillOfVOByCond(AggItfConfigBillVO.class, wheresql, true,
            false);
    return bills;
  }

  /**
   * VO ת��
   *
   * @param queryParam
   * @param bill
   * @return
   */
  private BillCard transBillCardFormVO(PageQueryInfo queryParam,
      AggItfConfigBillVO bill) {
    BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
    return operator.toCard(bill);
  }
}
