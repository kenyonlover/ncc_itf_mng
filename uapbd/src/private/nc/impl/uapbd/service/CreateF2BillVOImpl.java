package nc.impl.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.pf.pub.PfDataCache;
import nc.itf.uapbd.service.ICreateF2BillVO;
import nc.pubitf.accperiod.AccountCalendar;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class CreateF2BillVOImpl implements ICreateF2BillVO {

	/**
	 * 收款单
	 * 
	 * @throws BusinessException
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group)
			throws BusinessException {

		// select * from md_class where defaulttablename='ar_gatherbill' or
		// defaulttablename='ar_gatheritem'
		String headclassid = "53e7cc81-a016-4d64-973b-d61fd647c7a2";// 收款单表头元数据ID，需要通过语句查找最新版本元数据
		String bodyclassid = "f559bf45-a0b1-4261-8ada-62245b3ca809";// 收款单表体元数据ID，需要通过语句查找最新版本元数据

		// 组装单据VO
		AggGatheringBillVO billvo = new AggGatheringBillVO();
		try {

			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// 获取国家（中国）
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// 币种

			String itftype = (String) nccBillMap.get("itftype");// 接口类型

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "没有获取到表头信息！");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "没有获取到表体信息！");

			GatheringBillVO headvo = new GatheringBillVO();

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

			for (String key : headMap.keySet()) {
				String existvalue = headMap.get(key);
				if (existvalue == null || "".equals(existvalue))
					continue;
				// 转换字段值
				Object value = TranslateUtils.getTranslateValue(headclassid, pk_group, pk_org, key, existvalue,
						"ARAP_H", itftype);
				headvo.setAttributeValue(key, value);
			}
			headvo.setPk_group(pk_group);// 集团
			headvo.setPk_org(pk_org);// 应付财务组织
			headvo.setPk_org_v(pk_org_v);// 应付财务组织
			headvo.setPk_pcorg(pk_org);// 利润中心
			headvo.setPk_pcorg_v(pk_org_v);// 利润中心
			headvo.setPk_fiorg(pk_org);// 财务组织
			headvo.setPk_fiorg_v(pk_org_v);// 财务组织
			headvo.setSett_org(pk_org);// 结算财务组织
			headvo.setSett_org_v(pk_org_v);
			headvo.setRececountryid(pk_country);// 项目主键 rececountryid收获国
			headvo.setTaxcountryid(pk_country);// 报税国项目主键 taxcountryid

			// 单据来源系统
			headvo.setSrc_syscode(0);// 应收系统

			UFDouble totalnum = UFDouble.ZERO_DBL;// 总数量

			String deptvid = headvo.getPk_deptid_v();
			if (deptvid != null && !deptvid.trim().equals("")) {
				HashMap<String, String> deptinfo = CreateBillUtils.getDocMapByPk("org_dept_v", "pk_vid", deptvid, "部门");
				String deptid = deptinfo.get("pk_dept");
				headvo.setPk_deptid(deptid);
			}

			ArrayList<GatheringBillItemVO> bodyvolist = new ArrayList<GatheringBillItemVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				GatheringBillItemVO bodyvo = new GatheringBillItemVO();

				for (String key : bodymap.keySet()) {
					String existvalue = bodymap.get(key);
					if (existvalue == null || "".equals(existvalue))
						continue;
					// 转换字段值
					Object value = TranslateUtils.getTranslateValue(bodyclassid, pk_group, pk_org, key, existvalue,
							"ARAP_B", itftype);
					bodyvo.setAttributeValue(key, value);
				}

				bodyvo.setPk_group(headvo.getPk_group());// 集团
				bodyvo.setPk_org(headvo.getPk_org());// 应付组织
				bodyvo.setPk_org_v(headvo.getPk_org_v());// 应付组织
				bodyvo.setPk_currtype(pk_currtype);// 币种
				// 往来对象 objtype 往来对象 objtype int 往来对象 3=业务员，2=部门，1=供应商，
//					bodyvo.setObjtype(1);

				// 这里还传递税率过来没用
				// 物料税码税率
//					HashMap<String,String> taxInfo = XnsnServicePub.getTaxInfo(bodyvo.getMaterial());
//					String pk_taxcode = taxInfo.get("pk_taxcode");
//					String taxrate = String.valueOf(taxInfo.get("taxrate"));
//					bodyvo.setTaxcodeid(pk_taxcode);//税码
//					bodyvo.setTaxrate(new UFDouble(taxrate));//税率

				// 项目主键 supplier
//					bodyvo.setSupplier(headvo.getSupplier());

				bodyvolist.add(bodyvo);
			}
//				headvo.setNtotalnum(totalnum);//总数量

			headvo = setHeaderDefault(headvo);
			setBodyDefault(headvo, bodyvolist.toArray(new GatheringBillItemVO[0]));
			billvo.setParentVO(headvo);
			billvo.setChildrenVO(bodyvolist.toArray(new GatheringBillItemVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("转换付款单数据VO出错！" + e.getMessage());
		}

		return billvo;

	}

	/**
	 * 设置表头默认信息
	 * 
	 * @param headerVo
	 * @return
	 * @throws BusinessException
	 */
	private GatheringBillVO setHeaderDefault(GatheringBillVO header) throws BusinessException {
		Integer ZERO = Integer.valueOf(0);
		/* 单据状态为未审核 */
		header.setBillstatus(BillEnumCollection.BillSatus.Save.VALUE);
		header.setEffectstatus(BillEnumCollection.InureSign.NOINURE.VALUE);
		header.setDr(ZERO);
		// 来源系统是外部交换平台
//		header.setSrc_syscode(BillEnumCollection.FromSystem.WBJHPT.VALUE);		
		header.setCreationtime(new UFDateTime());
		header.setCoordflag(null);

		// 设置会计年和会计期间。若根据日期查不到会计期间，则捕获异常，不作处理
		try {
			AccountCalendar ac = AccountCalendar.getInstanceByPk_org(header.getPk_org());
			ac.setDate(header.getBilldate());
			header.setBillyear(ac.getYearVO().getPeriodyear());
			header.setBillperiod(ac.getMonthVO().getAccperiodmth());
		} catch (BusinessException ex) {
		}

		// 设置交易类型pk
		header.setPk_tradetypeid(
				PfDataCache.getBillTypeInfo(header.getPk_group(), header.getPk_tradetype()).getPk_billtypeid());
		header.setPk_fiorg(header.getPk_org());
		header.setPk_fiorg_v(header.getPk_org_v());
		header.setSett_org(header.getPk_org());
		header.setSett_org_v(header.getPk_org_v());
		return header;
	}

	private void setBodyDefault(GatheringBillVO head, GatheringBillItemVO[] items) throws BusinessException {
		int len = items == null ? 0 : items.length;

		for (int i = 0; i < len; i++) {
			setBodyDefault(head, items[i]);
		}
	}

	private void setBodyDefault(GatheringBillVO head, GatheringBillItemVO item) throws BusinessException {
		/* 复制单位 */
		item.setPk_org(head.getPk_org());
		item.setDr(Integer.valueOf(0));
		item.setPk_tradetypeid(
				PfDataCache.getBillTypeInfo(head.getPk_group(), head.getPk_tradetype()).getPk_billtypeid());
		item.setCoordflag(null);
		item.setPk_fiorg(item.getPk_org());
		item.setPk_fiorg_v(item.getPk_org_v());
		item.setSett_org(item.getPk_org());
		item.setSett_org_v(item.getPk_org_v());
	}

}
