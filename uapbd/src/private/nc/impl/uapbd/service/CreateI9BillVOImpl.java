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
	 * �������-��������
	 * 
	 * @throws BusinessException
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group)
			throws BusinessException {

		// select * from md_class where defaulttablename='ia_i9bill' or
		// defaulttablename='ia_i9bill_b'
		String headclassid = "fe2e1732-842b-4bd3-a562-4ab675019579";// ����������ͷԪ����ID����Ҫͨ�����������°汾Ԫ����
		String bodyclassid = "f34b3cbf-c806-4a96-818a-e17f1ce82112";// ������������Ԫ����ID����Ҫͨ�����������°汾Ԫ����

		// ��װ����VO
		I9BillVO billvo = new I9BillVO();
		try {

			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// ��ȡ���ң��й���
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// ����

			String itftype = (String) nccBillMap.get("itftype");// �ӿ�����

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "û�л�ȡ����ͷ��Ϣ��");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "û�л�ȡ��������Ϣ��");

			I9HeadVO headvo = new I9HeadVO();

			// �Ȱ���׼ת�����ٲ��������ֶ�
			// �ȷ�����֯
			String orgcode = headMap.get("pk_org");
			String pk_org = (String) TranslateUtils.getTranslateValue(headclassid, pk_group, "", "pk_org", orgcode, "",
					itftype);
			AssertUtils.stringIsNull(pk_org, "û���ҵ���" + orgcode + "����Ӧ����֯��Ϣ��");
			// ��ȡ������֯��Ϣ
			HashMap<String, String> financeorginfo = CreateBillUtils.getDocMapByPk("org_financeorg", "pk_financeorg",
					pk_org, "������֯��Ϣ");
			String pk_financeorg = financeorginfo.get("pk_financeorg");
			// ͨ��������֯��Ϣ���Ҳ�������ʲ�
			HashMap<String, String> accountingbookorginfo = CreateBillUtils.getDocMapByPk("org_accountingbook",
					"pk_relorg", pk_financeorg, "��������ʲ�");
			String pk_accountingbook = accountingbookorginfo.get("pk_accountingbook");

			for (String key : headMap.keySet()) {
				String existvalue = headMap.get(key);
				if (existvalue == null || "".equals(existvalue))
					continue;
				// ת���ֶ�ֵ
				Object value = TranslateUtils.getTranslateValue(headclassid, pk_group, pk_org, key, existvalue,
						"IAInOut", itftype);
				headvo.setAttributeValue(key, value);
			}
			headvo.setPk_group(pk_group);// ����
			headvo.setPk_org(pk_org);// Ӧ��������֯

			// csrcmodulecode ��Դģ�����
			headvo.setCsrcmodulecode(NCModule.OT.getSystemCode());
			headvo.setPk_book(pk_accountingbook);
			// ����ڼ� ��Ŀ���� caccountperiod
			headvo.setCaccountperiod(headvo.getDbilldate().toString().substring(0, 7));

			UFDouble totalnum = UFDouble.ZERO_DBL;// ������

			// ��Ŀ���� cdeptvid
			String deptvid = headvo.getCdeptvid();
			if (deptvid != null && !deptvid.trim().equals("")) {
				HashMap<String, String> deptinfo = CreateBillUtils.getDocMapByPk("org_dept_v", "pk_vid", deptvid, "����");
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
					// ת���ֶ�ֵ
					Object value = TranslateUtils.getTranslateValue(bodyclassid, pk_group, pk_org, key, existvalue,
							"IAInOut_B", itftype);
					bodyvo.setAttributeValue(key, value);
				}

				bodyvo.setPk_group(headvo.getPk_group());// ����
				bodyvo.setPk_org(headvo.getPk_org());// ��֯

				// ��λ��
				bodyvo.setCcurrencyid(pk_currtype);// ����
				// caccountperiod����ڼ�
				headvo.setCaccountperiod(headvo.getDbilldate().toString().substring(0, 7));

				// ���ﻹ����˰�ʹ���û��
				// ����˰��˰��
//					HashMap<String,String> taxInfo = XnsnServicePub.getTaxInfo(bodyvo.getMaterial());
//					String pk_taxcode = taxInfo.get("pk_taxcode");
//					String taxrate = String.valueOf(taxInfo.get("taxrate"));
//					bodyvo.setTaxcodeid(pk_taxcode);//˰��
//					bodyvo.setTaxrate(new UFDouble(taxrate));//˰��

				//// ���ϱ���汾 ���ñ�ֻ��������oid Ϊ��Ч��ÿ���ð汾cinventoryvid
				bodyvo.setCinventoryvid(bodyvo.getCinventoryid());
				bodyvo.setPk_book(headvo.getPk_book());

				// ��Ŀ���� supplier
//					bodyvo.setSupplier(headvo.getSupplier());

				bodyvolist.add(bodyvo);
			}
//				headvo.setNtotalnum(totalnum);//������

			billvo.setParentVO(headvo);
			billvo.setChildrenVO(bodyvolist.toArray(new I9ItemVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ת���������-������������VO����" + e.getMessage());
		}

		return billvo;

	}

}
