
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
 * 卡片查询（查询方案）操作
 * @version  @since v3.5.6-1903
 */
public class ItfConfigBillVOQueryCardAction implements ICommonAction {

  @Override
  public Object doAction(IRequest paramIRequest) {
    PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
    try {
      // 查询数据
      Collection<AggItfConfigBillVO> bills = this.queryBills(queryParam);
      if (bills == null) {
        return null;
      }
      // 转换前端需要的billCard
      return this.transBillCardFormVO(queryParam,
          bills.toArray(new AggItfConfigBillVO[0])[0]);
    }
    catch (BusinessException ex) {
      // 处理异常信息
      Logger.error(ex);
      ExceptionUtils.wrapException(ex);
    }
    return null;
  }

  /**
   * 获取查询参数
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
   * 查询业务数据
   *
   * @param queryParam
   * @return
   * @throws MetaDataException
   */
  private Collection<AggItfConfigBillVO> queryBills(PageQueryInfo queryParam)
      throws MetaDataException {
    IMDPersistenceQueryService service = CommonUtil.getMDQueryService();
    // 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
    String wheresql =
        "pk_itfconfigbill" + "='" + queryParam.getPk() + "'";
    Collection<AggItfConfigBillVO> bills =
        service.queryBillOfVOByCond(AggItfConfigBillVO.class, wheresql, true,
            false);
    return bills;
  }

  /**
   * VO 转换
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
