package nc.impl.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.pf.pub.PfDataCache;
import nc.itf.uapbd.service.ICreateF3BillVO;
import nc.pubitf.accperiod.AccountCalendar;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class CreateF3BillVOImpl implements ICreateF3BillVO {

	/**
	 * ���
	 * 
	 * @throws BusinessException
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group)
			throws BusinessException {

		// select * from md_class where defaulttablename='ap_paybill' or
		// defaulttablename='ap_payitem'
		String headclassid = "6ec38eda-3752-462a-bb29-2add1625f011";// �����ͷԪ����ID����Ҫͨ�����������°汾Ԫ����
		String bodyclassid = "c44b4d2b-e460-47aa-883e-90c10d07f309";// �������Ԫ����ID����Ҫͨ�����������°汾Ԫ����

		// ��װ����VO
		AggPayBillVO billvo = new AggPayBillVO();
		try {

			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// ��ȡ���ң��й���
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// ����

			String itftype = (String) nccBillMap.get("itftype");// �ӿ�����

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "û�л�ȡ����ͷ��Ϣ��");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "û�л�ȡ��������Ϣ��");

			PayBillVO headvo = new PayBillVO();

			// �Ȱ���׼ת�����ٲ��������ֶ�
			// �ȷ�����֯
			String orgcode = headMap.get("pk_org");
			String pk_org = (String) TranslateUtils.getTranslateValue(headclassid, pk_group, "", "pk_org", orgcode, "",
					itftype);
			AssertUtils.stringIsNull(pk_org, "û���ҵ���" + orgcode + "����Ӧ����֯��Ϣ��");
			// ��ȡ�����֯��Ϣ
			HashMap<String, String> orgInfo = CreateBillUtils.getDocMapByPk("org_stockorg", "pk_stockorg", pk_org,
					"�����֯��Ϣ");
			String pk_org_v = orgInfo.get("pk_vid");// ȡ�����֯�汾ID

			for (String key : headMap.keySet()) {
				String existvalue = headMap.get(key);
				if (existvalue == null || "".equals(existvalue))
					continue;
				// ת���ֶ�ֵ
				Object value = TranslateUtils.getTranslateValue(headclassid, pk_group, pk_org, key, existvalue,
						"ARAP_H", itftype);
				headvo.setAttributeValue(key, value);
			}
			headvo.setPk_group(pk_group);// ����
			headvo.setPk_org(pk_org);// Ӧ��������֯
			headvo.setPk_org_v(pk_org_v);// Ӧ��������֯
			headvo.setPk_pcorg(pk_org);// ��������
			headvo.setPk_pcorg_v(pk_org_v);// ��������
			headvo.setPk_fiorg(pk_org);// ������֯
			headvo.setPk_fiorg_v(pk_org_v);// ������֯
			headvo.setSett_org(pk_org);// ���������֯
			headvo.setSett_org_v(pk_org_v);
			headvo.setRececountryid(pk_country);// ��Ŀ���� rececountryid�ջ��
			headvo.setTaxcountryid(pk_country);// ��˰����Ŀ���� taxcountryid

			// ������Դϵͳ
			headvo.setSrc_syscode(1);// Ӧ��ϵͳ

			UFDouble totalnum = UFDouble.ZERO_DBL;// ������

			String deptvid = headvo.getPk_deptid_v();
			if (deptvid != null && !deptvid.trim().equals("")) {
				HashMap<String, String> deptinfo = CreateBillUtils.getDocMapByPk("org_dept_v", "pk_vid", deptvid, "����");
				String deptid = deptinfo.get("pk_dept");
				headvo.setPk_deptid(deptid);
			}

			ArrayList<PayBillItemVO> bodyvolist = new ArrayList<PayBillItemVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				PayBillItemVO bodyvo = new PayBillItemVO();

				for (String key : bodymap.keySet()) {
					String existvalue = bodymap.get(key);
					if (existvalue == null || "".equals(existvalue))
						continue;
					// ת���ֶ�ֵ
					Object value = TranslateUtils.getTranslateValue(bodyclassid, pk_group, pk_org, key, existvalue,
							"ARAP_B", itftype);
					bodyvo.setAttributeValue(key, value);
				}

				bodyvo.setPk_group(headvo.getPk_group());// ����
				bodyvo.setPk_org(headvo.getPk_org());// Ӧ����֯
				bodyvo.setPk_org_v(headvo.getPk_org_v());// Ӧ����֯
				bodyvo.setPk_currtype(pk_currtype);// ����
				// �������� objtype �������� objtype int �������� 3=ҵ��Ա��2=���ţ�1=��Ӧ�̣�
				bodyvo.setObjtype(1);

				// ���ﻹ����˰�ʹ���û��
				// ����˰��˰��
//					HashMap<String,String> taxInfo = XnsnServicePub.getTaxInfo(bodyvo.getMaterial());
//					String pk_taxcode = taxInfo.get("pk_taxcode");
//					String taxrate = String.valueOf(taxInfo.get("taxrate"));
//					bodyvo.setTaxcodeid(pk_taxcode);//˰��
//					bodyvo.setTaxrate(new UFDouble(taxrate));//˰��

				// ��Ŀ���� supplier
//					bodyvo.setSupplier(headvo.getSupplier());

				bodyvolist.add(bodyvo);
			}
//				headvo.setNtotalnum(totalnum);//������

			headvo = setHeaderDefault(headvo);
			setBodyDefault(headvo, bodyvolist.toArray(new PayBillItemVO[0]));
			billvo.setParentVO(headvo);
			billvo.setChildrenVO(bodyvolist.toArray(new PayBillItemVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ת���������VO����" + e.getMessage());
		}

		return billvo;

	}

	/**
	 * ���ñ�ͷĬ����Ϣ
	 * 
	 * @param headerVo
	 * @return
	 * @throws BusinessException
	 */
	private PayBillVO setHeaderDefault(PayBillVO header) throws BusinessException {
		Integer ZERO = Integer.valueOf(0);
		/* ����״̬Ϊδ��� */
		header.setBillstatus(BillEnumCollection.BillSatus.Save.VALUE);
		header.setEffectstatus(BillEnumCollection.InureSign.NOINURE.VALUE);
		header.setDr(ZERO);
		// ��Դϵͳ���ⲿ����ƽ̨
//		header.setSrc_syscode(BillEnumCollection.FromSystem.WBJHPT.VALUE);		
		header.setCreationtime(new UFDateTime());
		header.setCoordflag(null);

		// ���û����ͻ���ڼ䡣���������ڲ鲻������ڼ䣬�򲶻��쳣����������
		try {
			AccountCalendar ac = AccountCalendar.getInstanceByPk_org(header.getPk_org());
			ac.setDate(header.getBilldate());
			header.setBillyear(ac.getYearVO().getPeriodyear());
			header.setBillperiod(ac.getMonthVO().getAccperiodmth());
		} catch (BusinessException ex) {
		}

		// ���ý�������pk
		header.setPk_tradetypeid(
				PfDataCache.getBillTypeInfo(header.getPk_group(), header.getPk_tradetype()).getPk_billtypeid());
		return header;
	}

	private void setBodyDefault(PayBillVO head, PayBillItemVO[] items) throws BusinessException {
		int len = items == null ? 0 : items.length;

		for (int i = 0; i < len; i++) {
			setBodyDefault(head, items[i]);
		}
	}

	private void setBodyDefault(PayBillVO head, PayBillItemVO item) throws BusinessException {
		/* ���Ƶ�λ */
		item.setPk_org(head.getPk_org());
		item.setDr(Integer.valueOf(0));
		item.setPk_tradetypeid(
				PfDataCache.getBillTypeInfo(head.getPk_group(), head.getPk_tradetype()).getPk_billtypeid());
		item.setCoordflag(null);
	}

}
