package nc.impl.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.itf.uapbd.service.ICreateIABillVO;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.ia.mia.entity.IABillVO;
import nc.vo.ia.mia.entity.IAHeadVO;
import nc.vo.ia.mia.entity.IAItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.res.NCModule;

public class CreateIABillVOImpl implements ICreateIABillVO {

	/**
	 * �������-���������
	 * 
	 * @throws BusinessException
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group)
			throws BusinessException {

		// select * from md_class where defaulttablename='ia_iabill' or
		// defaulttablename='ia_iabill'
		String headclassid = "ec545ab9-8879-4df2-907d-8ea30c84b5f7";// �����������������ͷԪ����ID����Ҫͨ�����������°汾Ԫ����
		String bodyclassid = "58c068f9-de2a-4c61-ae1b-14bec140ee50";// ���������������Ԫ����ID����Ҫͨ�����������°汾Ԫ����

		// ��װ����VO
		IABillVO billvo = new IABillVO();
		try {
			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// ��ȡ���ң��й���
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// ����

			String itftype = (String) nccBillMap.get("itftype");// �ӿ�����

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "û�л�ȡ����ͷ��Ϣ��");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "û�л�ȡ��������Ϣ��");

			IAHeadVO headvo = new IAHeadVO();

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

			ArrayList<IAItemVO> bodyvolist = new ArrayList<IAItemVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				IAItemVO bodyvo = new IAItemVO();

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
			billvo.setChildrenVO(bodyvolist.toArray(new IAItemVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ת���������������������VO����" + e.getMessage());
		}

		return billvo;

	}

}
