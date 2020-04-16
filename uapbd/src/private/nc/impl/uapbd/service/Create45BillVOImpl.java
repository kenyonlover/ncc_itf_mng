package nc.impl.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.bs.ic.general.plugins.CheckMnyUtil;
import nc.bs.ic.general.plugins.CheckScaleUtil;
import nc.bs.ic.pub.env.ICBSContext;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.uapbd.service.ICreate45BillVO;
import nc.pubitf.scmf.ic.mbatchcode.IBatchcodePubService;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.general.define.ICBillFlag;
import nc.vo.ic.general.define.ICBillHeadVO;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;

/**
 * 库存产成品入库单
 */
public class Create45BillVOImpl implements ICreate45BillVO {

	/**
	 * 创建聚合VO<br>
	 * 下列字段请在配置界面配置：<br>
	 * <table border="1">
	 * <tr>
	 * <td>业务流程</td>
	 * <td>Cxx-Pu</td>
	 * </tr>
	 * <tr>
	 * <td>交易类型</td>
	 * <td>45-01</td>
	 * </tr>
	 * </table>
	 * 
	 * @throws BusinessException 
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group) throws BusinessException {

		// select * from md_class where defaulttablename='ic_purchasein_h' or
		// defaulttablename='ic_purchasein_b'
		String headclassid = "108cea13-88ce-430c-a772-1b9ed397d401";// 采购入库单表头元数据ID，需要通过语句查找最新版本元数据
		String bodyclassid = "de6a25f4-4009-4181-8f68-a0edc10b3f03";// 采购入库单表体元数据ID，需要通过语句查找最新版本元数据
		// 组装单据VO
		PurchaseInVO billvo = new PurchaseInVO();
		try {
			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// 获取国家（中国）
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// 币种

			String itftype = (String) nccBillMap.get("itftype");// 接口类型

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "没有获取到表头信息！");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "没有获取到表体信息！");

			PurchaseInHeadVO headvo = new PurchaseInHeadVO();

			// 先按标准转换，再补充其它字段
			// 先翻译组织
			String orgcode = headMap.get("pk_org");
			String pk_org = (String) TranslateUtils.getTranslateValue(headclassid, pk_group, "", "pk_org", orgcode, "",
					itftype);
			AssertUtils.stringIsNull(pk_org, "没有找到【" + orgcode + "】对应的组织信息！");
			// 获取库存组织信息
			HashMap<String, String> orgInfo = CreateBillUtils.getDocMapByPk("org_stockorg", "pk_stockorg", pk_org,
					"库存组织信息");
			String pk_org_v = orgInfo.get("pk_vid");// 取库存组织版本ID
			String pk_financeorg = orgInfo.get("pk_financeorg");// 取对应的财务组织
			// 获取财务组织信息
			HashMap<String, String> financeorginfo = CreateBillUtils.getDocMapByPk("org_financeorg", "pk_financeorg",
					pk_financeorg, "财务组织信息");
			String pk_financeorg_v = financeorginfo.get("pk_vid");// 财务组织版本
			// 获取成本域信息
			HashMap<String, String> costregioninfo = CreateBillUtils.getDocMapByPk("org_costregion", "pk_org",
					pk_financeorg, "成本域");
			String pk_costregion = costregioninfo.get("pk_costregion");
			for (String key : headMap.keySet()) {
				String existvalue = headMap.get(key);
				if (existvalue == null || "".equals(existvalue))
					continue;
				// 转换字段值
				Object value = TranslateUtils.getTranslateValue(headclassid, pk_group, pk_org, key, existvalue, "45_H",
						itftype);
				headvo.setAttributeValue(key, value);
			}
			headvo.setPk_group(pk_group);// 集团
			headvo.setPk_org(pk_org);// 库存组织
			headvo.setPk_org_v(pk_org_v);// 库存组织
			headvo.setCpurorgoid(pk_org);// 采购组织
			headvo.setCpurorgvid(pk_org_v);// 采购组织
			headvo.setCfanaceorgoid(pk_financeorg);// 结算财务组织
			headvo.setCfanaceorgvid(pk_financeorg_v);// 结算财务组织
			headvo.setCpayfinorgoid(pk_financeorg);// 应付财务组织
			headvo.setCpayfinorgvid(pk_financeorg_v);// 应付财务组织
			headvo.setCcostdomainid(pk_costregion);// 结算成本域
			headvo.setCorpoid(pk_org);// 公司
			headvo.setCorpvid(pk_org_v);// 公司
			headvo.setBitinbill(UFBoolean.FALSE);// 进口入库单
			headvo.setBtriatradeflag(UFBoolean.FALSE);// 三角贸易
			headvo.setCreationtime(new UFDateTime());// 创建时间
			headvo.setCrececountryid(pk_country);// 收货国
			headvo.setCsendcountryid(pk_country);// 发货国
			headvo.setCtaxcountryid(pk_country);// 报税国
			headvo.setDmakedate(headvo.getDbilldate());// 制单日期
			headvo.setFbillflag(2);// 单据状态,1=删除，2=自由，3=签字，4=审核，5=审核中，6=审核不通过，7=已调差状态
			headvo.setFbuysellflag(2);// 购销类型,1=国内销售，2=国内采购，3=出口，4=进口，5=不区分
			headvo.setFreplenishflag(UFBoolean.FALSE);// 采购退库
			headvo.setIprintcount(0);// 打印次数

			UFDouble totalnum = UFDouble.ZERO_DBL;// 总数量

			String deptid = headvo.getCdptid();
			if (deptid != null && !deptid.trim().equals("")) {
				HashMap<String, String> deptinfo = CreateBillUtils.getDocMapByPk("org_dept", "pk_dept", deptid, "部门");
				String deptvid = deptinfo.get("pk_vid");
				headvo.setCdptvid(deptvid);
			}

			String ctrantypeid = headvo.getCtrantypeid();
			if (ctrantypeid != null && !ctrantypeid.trim().equals("")) {
				String pk_billtypecode = CreateBillUtils.getDocByPk("bd_billtype", "pk_billtypecode", "pk_billtypeid",
						ctrantypeid, " 出入库类型");
				headvo.setVtrantypecode(pk_billtypecode);// 出入库类型编码
			}

			ArrayList<PurchaseInBodyVO> bodyVOList = new ArrayList<PurchaseInBodyVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				PurchaseInBodyVO bodyvo = new PurchaseInBodyVO();

				for (String key : bodymap.keySet()) {
					String existvalue = bodymap.get(key);
					if (existvalue == null || "".equals(existvalue))
						continue;
					// 转换字段值
					Object value = TranslateUtils.getTranslateValue(bodyclassid, pk_group, pk_org, key, existvalue,
							"45_B", itftype);
					bodyvo.setAttributeValue(key, value);
				}

				bodyvo.setPk_group(headvo.getPk_group());// 集团
				bodyvo.setPk_org(headvo.getPk_org());// 库存组织
				bodyvo.setPk_org_v(headvo.getPk_org_v());// 库存组织
				bodyvo.setBassetcard(UFBoolean.FALSE);// 已生成设备卡片
				bodyvo.setBbarcodeclose(UFBoolean.FALSE);// 单据行是否条码关闭
				bodyvo.setBfixedasset(UFBoolean.FALSE);// 已转固
				bodyvo.setBonroadflag(UFBoolean.FALSE);// 是否在途
				bodyvo.setBopptaxflag(UFBoolean.FALSE);// 逆向征税标志
				bodyvo.setBorrowinflag(UFBoolean.FALSE);// 借入转采购
				bodyvo.setBsourcelargess(UFBoolean.FALSE);// 上游赠品行
				// 获取主单位
				String cunitid = CreateBillUtils.getPk_measdoc(bodyvo.getCmaterialoid());
				bodyvo.setCunitid(cunitid);// 主单位
				// 获取辅计量信息
				// HashMap<String,String> convertinfo =
				// XnsnServicePub.getMaterialconvert(bodyvo.getCmaterialoid());
				// String pk_castuntid = convertinfo.get("pk_measdoc");
				// String measrate = convertinfo.get("measrate");
				String measrate = "1/1";
				bodyvo.setCastunitid(cunitid);// 单位
				bodyvo.setVchangerate(measrate);// 换算率
				bodyvo.setVqtunitrate(bodyvo.getVchangerate());// 报价换算率
				bodyvo.setCbodytranstypecode(headvo.getVtrantypecode());// 出入库类型
				bodyvo.setCbodywarehouseid(headvo.getCwarehouseid());// 库存仓库
				bodyvo.setCcurrencyid(pk_currtype);// 本位币
				bodyvo.setCfanaceorgoid(headvo.getCfanaceorgoid());// 结算财务组织
				bodyvo.setCmaterialvid(bodyvo.getCmaterialoid());// 物料版本
				bodyvo.setCorigcurrencyid(pk_currtype);// 币种
				bodyvo.setCorpoid(headvo.getCorpoid());// 公司
				bodyvo.setCorpvid(headvo.getCorpvid());// 公司
				bodyvo.setCqtunitid(bodyvo.getCastunitid());// 报价单位
				bodyvo.setCreqstoorgoid(headvo.getPk_org());// 需求库存组织
				bodyvo.setCreqstoorgvid(headvo.getPk_org_v());// 需求库存组织
				String crowno = String.valueOf((j + 1) * 10);// 行号
				bodyvo.setCrowno(crowno);// 行号
				bodyvo.setCsrcmaterialoid(bodyvo.getCmaterialoid());// 来源物料
				bodyvo.setCsrcmaterialvid(bodyvo.getCmaterialvid());// 来源物料
				bodyvo.setCvendorid(headvo.getCvendorid());// 供应商
				bodyvo.setDbizdate(headvo.getDbilldate());// 入库日期
				bodyvo.setFlargess(UFBoolean.FALSE);// 赠品
				bodyvo.setNchangestdrate(new UFDouble(1));// 折本汇率
				bodyvo.setNitemdiscountrate(new UFDouble(100));// 折扣

				// 物料税码税率
				HashMap<String, String> taxInfo = CreateBillUtils.getTaxInfo(bodyvo.getCmaterialoid());
				String pk_taxcode = taxInfo.get("pk_taxcode");
				String taxrate = String.valueOf(taxInfo.get("taxrate"));
				bodyvo.setCtaxcodeid(pk_taxcode);// 税码
				bodyvo.setNtaxrate(new UFDouble(taxrate));// 税率

				bodyvo.setFtaxtypeflag(1);// 扣税类别，1=应税外加，0=应税内含?

				// bodyvo.setNassistnum(new UFDouble(1));//实收数量
				// bodyvo.setNnum(new UFDouble(1));//实收主数量
				// 应收主数量项目主键 nshouldnum
				bodyvo.setNshouldnum(bodyvo.getNshouldassistnum());
				// 实收主数量项目主键 nnum
				bodyvo.setNnum(bodyvo.getNassistnum());

				totalnum = totalnum.add(bodyvo.getNassistnum());// 总数量

				bodyvo.setNmny(bodyvo.getNorigmny());// 本币无税金额
				bodyvo.setNnetprice(bodyvo.getNqtprice());// 本币无税净价
				// bodyvo.setNorigmny(new UFDouble(1));//无税金额
				bodyvo.setNorignetprice(bodyvo.getNqtprice());// 主无税净价
				bodyvo.setNorigprice(bodyvo.getNqtprice());// 主无税单价
				bodyvo.setNorigtaxmny(bodyvo.getNorigmny());// 价税合计
				bodyvo.setNorigtaxnetprice(bodyvo.getNqtprice());// 主含税净价
				bodyvo.setNorigtaxprice(bodyvo.getNqtprice());
				;// 主含税单价
				bodyvo.setNprice(bodyvo.getNqtprice());// 主本币无税单价
				bodyvo.setNqtnetprice(bodyvo.getNqtprice());// 本币无税净价
				bodyvo.setNqtorignetprice(bodyvo.getNqtprice());// 无税净价
				bodyvo.setNqtorigprice(bodyvo.getNqtprice());// 无税单价
				bodyvo.setNqtorigtaxnetprice(bodyvo.getNqtprice());// 含税净价
				bodyvo.setNqtorigtaxprice(bodyvo.getNqtprice());// 含税单价
				// bodyvo.setNqtprice(new UFDouble(1));//本币无税单价
				bodyvo.setNqttaxnetprice(bodyvo.getNqtprice());// 本币含税净价
				bodyvo.setNqttaxprice(bodyvo.getNqtprice());// 本币含税单价
				bodyvo.setNqtunitnum(bodyvo.getNassistnum());// 报价数量

				bodyvo.setNtax(new UFDouble(0));// 税额
				bodyvo.setNtaxmny(bodyvo.getNorigmny());// 本币价税合计
				bodyvo.setNtaxnetprice(bodyvo.getNqtprice());// 主本币含税净价
				bodyvo.setNtaxprice(bodyvo.getNqtprice());// 主本币含税单价
				bodyvo.setNcalcostmny(bodyvo.getNorigmny());// 计成本金额
				bodyvo.setNcaltaxmny(new UFDouble(0));// 计税金额

				bodyVOList.add(bodyvo);
			}
			headvo.setNtotalnum(totalnum);// 总数量
			
			billvo.setParentVO(headvo);
			billvo.setChildrenVO(bodyVOList.toArray(new PurchaseInBodyVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("转换库存产成品入库单VO出错：" + e.getMessage());
		}
		// 查看外部交换平台代码
		// 检查是否允许保存
		this.checkCanInster(billvo);
		processBeforeSave(billvo);
		return billvo;

	}

	/**
	 * 检验vo是否可更新
	 * 
	 * @param vo
	 */
	protected void checkCanInster(AggregatedValueObject vo) {
		new CheckMnyUtil().checkMny(vo);
		new CheckScaleUtil().checkScale(vo);
	}

	/**
	 * 单据保存前处理
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	protected void processBeforeSave(ICBillVO vo) throws BusinessException {

		if (null == vo)
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008001_0",
					"04008001-0139")/* @res "单据不能为空" */);

		// 表头组织翻译后没有赋值，需要重表体字段重新取一边
		if (StringUtil.isSEmptyOrNull(vo.getParentVO().getPk_org()))
			vo.getParentVO().setPk_org(vo.getBodys()[0].getPk_org());
		if (StringUtil.isSEmptyOrNull(vo.getParentVO().getPk_org_v()))
			vo.getParentVO().setPk_org_v(vo.getBodys()[0].getPk_org_v());
		if (StringUtil.isSEmptyOrNull(vo.getParentVO().getCwarehouseid()))
			vo.getParentVO().setCwarehouseid(vo.getBodys()[0].getCbodywarehouseid());

		ICBSContext context = new ICBSContext();
		this.headVOProcess(vo.getHead(), context);
		this.bodyVOProcess(vo, context);
	}
	
	/**
	   * 表头处理，处理默认值，公司，单据日期，单据状态，打印次数，集团
	   * 
	   * @param vo
	   */
	  private void headVOProcess(ICBillHeadVO vo, ICBSContext context) {
	    vo.setStatus(VOStatus.NEW);
	    // 集团
	    if (StringUtil.isSEmptyOrNull(vo.getPk_group()))
	      vo.setPk_group(context.getPk_group());
	    // 打印次数
	    if (vo.getIprintcount() == null)
	      vo.setIprintcount(Integer.valueOf(0));
	    // 单据状态
	    if (vo.getFbillflag() == null)
	      vo.setFbillflag((Integer) ICBillFlag.FREE.value());
	    // 单据日期
	    if (vo.getDbilldate() == null)
	      vo.setDbilldate(context.getBizDate());
	    // 公司
	    if (StringUtil.isSEmptyOrNull(vo.getCorpoid())
	        || StringUtil.isSEmptyOrNull(vo.getCorpvid())) {
	      vo.setCorpoid(context.getOrgInfo().getCorpIDByCalBodyID(vo.getPk_org()));
	      vo.setCorpvid(context.getOrgInfo().getCorpVIDByCalBodyID(vo.getPk_org()));
	    }

	    if (StringUtil.isSEmptyOrNull(vo.getCtrantypeid())) {
	      // uap不支持单据类型的翻译，暂时以交易类型code查询id的方式补交易类型
	      String vtrantypecode = vo.getVtrantypecode();
	      Map<String, String> map =
	          PfServiceScmUtil.getTrantypeidByCode(new String[] {
	            vtrantypecode
	          });
	      vo.setCtrantypeid(map == null ? null : map.get(vtrantypecode));
	    }

	  }

	  /**
	   * 单据表体处理
	   * 
	   * @param vo
	   * @param context
	   * @throws BusinessException
	   */
	  private void bodyVOProcess(ICBillVO vo, ICBSContext context)
	      throws BusinessException {
	    ICBillBodyVO[] vos = vo.getBodys();
	    if (ValueCheckUtil.isNullORZeroLength(vos))
	      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
	          .getStrByID("4008001_0", "04008001-0141")/*@res "单据表体不能为空"*/);

	    VORowNoUtils.setVOsRowNoByRule(vos, ICPubMetaNameConst.CROWNO);// 行号处理

	    ICBillHeadVO head = vo.getHead();
	    Map<String, BatchcodeVO> batchmap = this.getBatchcodeVO(vos);
	    for (ICBillBodyVO body : vos) {
	      body.setStatus(VOStatus.NEW);
	      if (StringUtil.isSEmptyOrNull(body.getCmaterialoid())
	          || StringUtil.isSEmptyOrNull(body.getCmaterialvid()))
	        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
	            .getStrByID("4008001_0", "04008001-0142")/*@res "单据表体物料不能为空"*/);

	      body.setBbarcodeclose(UFBoolean.FALSE);// 单据行是否条码关闭
	      body.setBonroadflag(UFBoolean.FALSE);// 是否在途
	      if (body.getNnum()!=null &&body.getNassistnum()!=null&&body.getDbizdate() == null)
	        body.setDbizdate(context.getBizDate());// 业务日期
	      // 辅单位
	      if (StringUtil.isSEmptyOrNull(body.getCastunitid()))
	        body.setCastunitid(context.getInvInfo()
	            .getInvBasVO(body.getCmaterialvid()).getPk_stockmeas());

	      // 有批次号但无批次主键时， 需要补全批次主键，有必要时(保质期管理)补全生产日期和失效日期
	      if (!StringUtils.isEmpty(body.getVbatchcode())
	          && StringUtils.isEmpty(body.getPk_batchcode())) {
	        BatchcodeVO batchvo =
	            batchmap.get(body.getCmaterialvid() + body.getVbatchcode());
	        if (batchvo != null) {
	          body.setPk_batchcode(batchvo.getPk_batchcode());
	          body.setDproducedate(batchvo.getDproducedate());
	          body.setDvalidate(batchvo.getDvalidate());
	        }
	      }
	      bodyVOCopyFromHeadVO(body, head);
	    }
	  }
	  
	  /**
	   * 根据表头设置表体默认值，表体集团，库存组织，公司，仓库，交易类型
	   * 
	   * @param body
	   * @param head
	   */
	  private void bodyVOCopyFromHeadVO(ICBillBodyVO body, ICBillHeadVO head) {
	    body.setPk_group(head.getPk_group());
	    body.setPk_org(head.getPk_org());
	    body.setPk_org_v(head.getPk_org_v());
	    body.setCorpoid(head.getCorpoid());
	    body.setCorpvid(head.getCorpvid());
	    body.setCbodywarehouseid(head.getCwarehouseid());
	    body.setCbodytranstypecode(head.getVtrantypecode());
	  }
	
	
	  /**
	   * 获取批次号档案
	   * 
	   * @param vos
	   * @return Map<String(cmaterialvid+vbatchcode), BatchcodeVO批次档案>
	   */
	private Map<String, BatchcodeVO> getBatchcodeVO(ICBillBodyVO[] vos) {
	    List<String> cmaterialvidList = new ArrayList<String>();
	    List<String> vbatchcodeList = new ArrayList<String>();
	    Set<String> materialbatch = new HashSet<String>();
	    for (ICBillBodyVO body : vos) {
	      if (body.getCmaterialvid() != null && body.getVbatchcode() != null) {
	        if (materialbatch.contains(body.getCmaterialvid()
	            + body.getVbatchcode())) {
	          continue;
	        }
	        cmaterialvidList.add(body.getCmaterialvid());
	        vbatchcodeList.add(body.getVbatchcode());
	        materialbatch.add(body.getCmaterialvid() + body.getVbatchcode());
	      }
	    }
	    if (materialbatch.size() == 0) {
	      return new HashMap<String, BatchcodeVO>();
	    }
	    IBatchcodePubService batchservice =
	        NCLocator.getInstance().lookup(IBatchcodePubService.class);
	    BatchcodeVO[] batchvos = null;
	    try {
	      batchvos =
	          batchservice.queryBatchVOs(cmaterialvidList.toArray(new String[0]),
	              vbatchcodeList.toArray(new String[0]));
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }

	    if (batchvos == null || batchvos.length == 0) {
	      return new HashMap<String, BatchcodeVO>();
	    }
	    Map<String, BatchcodeVO> batchmap = new HashMap<String, BatchcodeVO>();
	    for (BatchcodeVO batchvo : batchvos) {
	      batchmap
	          .put(batchvo.getCmaterialvid() + batchvo.getVbatchcode(), batchvo);
	    }
	    return batchmap;
	  }

}
