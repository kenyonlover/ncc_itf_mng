package nc.impl.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pf.pub.PfDataCache;
import nc.itf.uapbd.service.ICreateF0BillVO;
import nc.pubitf.accperiod.AccountCalendar;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * Ӧ�յ�
 */
public class CreateF0BillVOImpl implements ICreateF0BillVO {

	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group) throws BusinessException {
		// �ж�Ӧ�տ����Ƿ�Ϊ�գ����Ϊ�������ŵ���ֻ��Ӧ����
		Object customer = ((Map) ((List) nccBillMap.get("body")).get(0)).get("customer");
		if (customer == null || customer.toString().length() == 0) {
			return null;
		}

		// select * from md_class where defaulttablename='ar_recbill' or
		// defaulttablename='ar_recitem'
		String headclassid = "ca162462-078f-46e8-af2d-05c4580c8af7";// Ӧ�յ���ͷԪ����ID����Ҫͨ�����������°汾Ԫ����
		String bodyclassid = "daa74132-56df-48c5-beba-b27b669ae088";// Ӧ�յ�����Ԫ����ID����Ҫͨ�����������°汾Ԫ����

		// ��װ����VO
		AggReceivableBillVO billvo = new AggReceivableBillVO();
		try {

			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// ��ȡ���ң��й���
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// ����

			String itftype = (String) nccBillMap.get("itftype");// �ӿ�����

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "û�л�ȡ����ͷ��Ϣ��");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "û�л�ȡ��������Ϣ��");

			ReceivableBillVO headvo = new ReceivableBillVO();

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
			headvo.setPk_org(pk_org);// Ӧ�ղ�����֯
			headvo.setPk_org_v(pk_org_v);// Ӧ�ղ�����֯
//				headvo.setPk_pcorg(pk_org);//��������
//				headvo.setPk_pcorg_v(pk_org_v);//��������
			headvo.setPk_fiorg(pk_org);// ������֯
			headvo.setPk_fiorg_v(pk_org_v);// ������֯
			headvo.setSett_org(pk_org);// ���������֯
			headvo.setSett_org_v(pk_org_v);
			headvo.setRececountryid(pk_country);// �ջ��
			headvo.setTaxcountryid(pk_country);// ��˰��

			// ������Դϵͳ
			headvo.setSrc_syscode(0);// Ӧ��ϵͳ

			UFDouble totalnum = UFDouble.ZERO_DBL;// ������

			String deptvid = headvo.getPk_deptid_v();
			if (deptvid != null && !deptvid.trim().equals("")) {
				HashMap<String, String> deptinfo = CreateBillUtils.getDocMapByPk("org_dept_v", "pk_vid", deptvid, "����");
				String deptid = deptinfo.get("pk_dept");
				headvo.setPk_deptid(deptid);
			}

			ArrayList<ReceivableBillItemVO> bodyvolist = new ArrayList<ReceivableBillItemVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				ReceivableBillItemVO bodyvo = new ReceivableBillItemVO();

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
				// settlecurr �տ����
				bodyvo.setSettlecurr(pk_currtype);// �տ����

				// �������� 0=�ͻ���2=���ţ�3=ҵ��Ա����
				bodyvo.setObjtype(0);

				// ���ﻹ����˰�ʹ���û��
				// ����˰��˰��
				HashMap<String, String> taxInfo = CreateBillUtils.getTaxInfo(bodyvo.getMaterial());
				String pk_taxcode = taxInfo.get("pk_taxcode");
				String taxrate = String.valueOf(taxInfo.get("taxrate"));
				bodyvo.setTaxcodeid(pk_taxcode);// ˰��
				bodyvo.setTaxrate(new UFDouble(taxrate));// ˰��

				// ��Ŀ���� customer
				// bodyvo.setCustomer(headvo.getCustomer());//�ͻ�

				bodyvolist.add(bodyvo);
			}
//				headvo.setNtotalnum(totalnum);//������

			headvo = setHeaderDefault(headvo);
			headvo.setBusidate(headvo.getBilldate());
			setBodyDefault(headvo, bodyvolist.toArray(new ReceivableBillItemVO[0]));
			billvo.setParentVO(headvo);
			ReceivableBillItemVO[] bodyvos = bodyvolist.toArray(new ReceivableBillItemVO[0]);
			for (ReceivableBillItemVO item : bodyvos) {
				item.setBusidate(headvo.getBusidate());
				item.setBilldate(headvo.getBilldate());
			}
			billvo.setChildrenVO(bodyvos);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ת��Ӧ�յ�����VO������" + e.getMessage());
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
	private ReceivableBillVO setHeaderDefault(ReceivableBillVO header) throws BusinessException {
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

	private void setBodyDefault(ReceivableBillVO head, ReceivableBillItemVO[] items) throws BusinessException {
		int len = items == null ? 0 : items.length;

		for (int i = 0; i < len; i++) {
			setBodyDefault(head, items[i]);
		}
	}

	private void setBodyDefault(ReceivableBillVO head, ReceivableBillItemVO item) throws BusinessException {
		/* ���Ƶ�λ */
		item.setPk_org(head.getPk_org());
		item.setDr(Integer.valueOf(0));
		item.setPk_tradetypeid(
				PfDataCache.getBillTypeInfo(head.getPk_group(), head.getPk_tradetype()).getPk_billtypeid());
		item.setCoordflag(null);
	}

}