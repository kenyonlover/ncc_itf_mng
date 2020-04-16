
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
   * �������ͱ���
   *
   * @return
   */
  public static String getBillTypeCode() {
    return ItfConfigBillVOConst.CONST_BILLTYPE_COST;
  }


  /**
   * ��ȡ��ǰҵ��ʱ��
   *
   * @return
   */
  public static UFDate getBusiDate() {
    return new UFDate(SessionContext.getInstance().getClientInfo()
        .getBizDateTime());
  }

  /**
   * ��ȡ��ǰҵ������ʱ��
   *
   * @return
   */
  public static UFDateTime getBusiDateTime() {
    return new UFDateTime(SessionContext.getInstance().getClientInfo()
        .getBizDateTime());
  }

  /**
   * ƽ̨�ű�������
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
   * �������
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
   * Ԫ���ݲ�ѯ����
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
   * ��ѯ������֯���ڼ���
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
