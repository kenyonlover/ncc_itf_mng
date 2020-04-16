package nc.impl.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.itf.uapbd.service.ICreateI9BillVO;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mi9.entity.I9HeadVO;
import nc.vo.ia.mi9.entity.I9ItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.res.NCModule;

public class CreateI9BillVOImpl implements ICreateI9BillVO {

	/**
	 * 存货核算-入库调整单
	 * 
	 * @throws BusinessException
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group)
			throws BusinessException {

		// select * from md_class where defaulttablename='ia_i9bill' or
		// defaulttablename='ia_i9bill_b'
		String headclassid = "fe2e1732-842b-4bd3-a562-4ab675019579";// 入库调整单表头元数据ID，需要通过语句查找最新版本元数据
		String bodyclassid = "f34b3cbf-c806-4a96-818a-e17f1ce82112";// 入库调整单表体元数据ID，需要通过语句查找最新版本元数据

		// 组装单据VO
		I9BillVO billvo = new I9BillVO();
		try {

			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// 获取国家（中国）
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// 币种

			String itftype = (String) nccBillMap.get("itftype");// 接口类型

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "没有获取到表头信息！");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "没有获取到表体信息！");

			I9HeadVO headvo = new I9HeadVO();

			// 先按标准转换，再补充其它字段
			// 先翻译组织
			String orgcode = headMap.get("pk_org");
			String pk_org = (String) TranslateUtils.getTranslateValue(headclassid, pk_group, "", "pk_org", orgcode, "",
					itftype);
			AssertUtils.stringIsNull(pk_org, "没有找到【" + orgcode + "】对应的组织信息！");
			// 获取财务组织信息
			HashMap<String, String> financeorginfo = CreateBillUtils.getDocMapByPk("org_financeorg", "pk_financeorg",
					pk_org, "财务组织信息");
			String pk_financeorg = financeorginfo.get("pk_financeorg");
			// 通过财务组织信息查找财务核算帐簿
			HashMap<String, String> accountingbookorginfo = CreateBillUtils.getDocMapByPk("org_accountingbook",
					"pk_relorg", pk_financeorg, "财务核算帐簿");
			String pk_accountingbook = accountingbookorginfo.get("pk_accountingbook");

			for (String key : headMap.keySet()) {
				String existvalue = headMap.get(key);
				if (existvalue == null || "".equals(existvalue))
					continue;
				// 转换字段值
				Object value = TranslateUtils.getTranslateValue(headclassid, pk_group, pk_org, key, existvalue,
						"IAInOut", itftype);
				headvo.setAttributeValue(key, value);
			}
			headvo.setPk_group(pk_group);// 集团
			headvo.setPk_org(pk_org);// 应付财务组织

			// csrcmodulecode 来源模块编码
			headvo.setCsrcmodulecode(NCModule.OT.getSystemCode());
			headvo.setPk_book(pk_accountingbook);
			// 会计期间 项目主键 caccountperiod
			headvo.setCaccountperiod(headvo.getDbilldate().toString().substring(0, 7));

			UFDouble totalnum = UFDouble.ZERO_DBL;// 总数量

			// 项目主键 cdeptvid
			String deptvid = headvo.getCdeptvid();
			if (deptvid != null && !deptvid.trim().equals("")) {
				HashMap<String, String> deptinfo = CreateBillUtils.getDocMapByPk("org_dept_v", "pk_vid", deptvid, "部门");
				String deptid = deptinfo.get("pk_dept");
				headvo.setCdeptid(deptid);
			}

			ArrayList<I9ItemVO> bodyvolist = new ArrayList<I9ItemVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				I9ItemVO bodyvo = new I9ItemVO();

				for (String key : bodymap.keySet()) {
					String existvalue = bodymap.get(key);
					if (existvalue == null || "".equals(existvalue))
						continue;
					// 转换字段值
					Object value = TranslateUtils.getTranslateValue(bodyclassid, pk_group, pk_org, key, existvalue,
							"IAInOut_B", itftype);
					bodyvo.setAttributeValue(key, value);
				}

				bodyvo.setPk_group(headvo.getPk_group());// 集团
				bodyvo.setPk_org(headvo.getPk_org());// 组织

				// 本位币
				bodyvo.setCcurrencyid(pk_currtype);// 币种
				// caccountperiod会计期间
				headvo.setCaccountperiod(headvo.getDbilldate().toString().substring(0, 7));

				// 这里还传递税率过来没用
				// 物料税码税率
//					HashMap<String,String> taxInfo = XnsnServicePub.getTaxInfo(bodyvo.getMaterial());
//					String pk_taxcode = taxInfo.get("pk_taxcode");
//					String taxrate = String.valueOf(taxInfo.get("taxrate"));
//					bodyvo.setTaxcodeid(pk_taxcode);//税码
//					bodyvo.setTaxrate(new UFDouble(taxrate));//税率

				//// 物料编码版本 配置表只是配置了oid 为了效率每配置版本cinventoryvid
				bodyvo.setCinventoryvid(bodyvo.getCinventoryid());
				bodyvo.setPk_book(headvo.getPk_book());

				// 项目主键 supplier
//					bodyvo.setSupplier(headvo.getSupplier());

				bodyvolist.add(bodyvo);
			}
//				headvo.setNtotalnum(totalnum);//总数量

			billvo.setParentVO(headvo);
			billvo.setChildrenVO(bodyvolist.toArray(new I9ItemVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("转换存货核算-入库调整单数据VO出错！" + e.getMessage());
		}

		return billvo;

	}

}
