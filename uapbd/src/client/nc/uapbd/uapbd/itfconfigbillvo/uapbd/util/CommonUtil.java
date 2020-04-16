
package nc.uapbd.uapbd.itfconfigbillvo.uapbd.util;


import java.util.HashMap;

import nc.itf.uapbd.uapbd.IItfconfigbillvoMaintain;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.uapbd.uapbd.ItfConfigBillVOConst;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nccloud.base.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;

public class CommonUtil {

  private static IItfconfigbillvoMaintain maintain = null;

  private static IMDPersistenceQueryService persistenceQueryService = null;

  private static ICloudScriptPFlowService PFlowService = null;

  /**
   * 单据类型编码
   *
   * @return
   */
  public static String getBillTypeCode() {
    return ItfConfigBillVOConst.CONST_BILLTYPE_COST;
  }


  /**
   * 获取当前业务时间
   *
   * @return
   */
  public static UFDate getBusiDate() {
    return new UFDate(SessionContext.getInstance().getClientInfo()
        .getBizDateTime());
  }

  /**
   * 获取当前业务日期时间
   *
   * @return
   */
  public static UFDateTime getBusiDateTime() {
    return new UFDateTime(SessionContext.getInstance().getClientInfo()
        .getBizDateTime());
  }

  /**
   * 平台脚本服务类
   *
   * @return
   */
  public static synchronized ICloudScriptPFlowService getCPFlowService() {
    if (CommonUtil.PFlowService == null) {
      CommonUtil.PFlowService =
          ServiceLocator.find(ICloudScriptPFlowService.class);
    }
    return CommonUtil.PFlowService;
  }

  /**
   * 管理服务
   *
   * @return
   */
  public static IItfconfigbillvoMaintain getMaintainService() {
    if (CommonUtil.maintain == null) {
      CommonUtil.maintain = ServiceLocator.find(IItfconfigbillvoMaintain.class);
    }
    return CommonUtil.maintain;
  }

  /**
   * 元数据查询服务
   *
   * @return
   */
  public static IMDPersistenceQueryService getMDQueryService() {
    if (CommonUtil.persistenceQueryService == null) {
      CommonUtil.persistenceQueryService =
          ServiceLocator.find(IMDPersistenceQueryService.class);
    }
    return CommonUtil.persistenceQueryService;
  }


  /**
   * 查询财务组织所在集团
   *
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getGroupByOrg(String pk_org) throws BusinessException {
    IOrgUnitPubService_C orgUnitQryService =
        ServiceLocator.find(IOrgUnitPubService_C.class);
    OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] {
      pk_org
    }, new String[] {
      OrgVO.PK_GROUP
    });
    if ((orgVOs == null) || (orgVOs.length <= 0)) {
      return null;
    }
    return (String) orgVOs[0].getAttributeValue(OrgVO.PK_GROUP);
  }



}
