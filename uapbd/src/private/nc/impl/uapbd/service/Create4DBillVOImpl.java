package nc.impl.uapbd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.ic.general.plugins.CheckMnyUtil;
import nc.bs.ic.general.plugins.CheckScaleUtil;
import nc.bs.ic.pub.env.ICBSContext;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.uapbd.service.ICreate4DBillVO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.pubitf.scmf.ic.mbatchcode.IBatchcodePubService;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.general.define.ICBillFlag;
import nc.vo.ic.general.define.ICBillHeadVO;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.m4d.entity.MaterialOutBodyVO;
import nc.vo.ic.m4d.entity.MaterialOutHeadVO;
import nc.vo.ic.m4d.entity.MaterialOutVO;
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
 * �����ϳ��ⵥ
 */
public class Create4DBillVOImpl implements ICreate4DBillVO {

	/**
	 * �����ۺ�VO<br>
	 * �����ֶ��������ý������ã�<br>
	 * <table border="1">
	 * <tr>
	 * <td>ҵ������</td>
	 * <td> </td>
	 * </tr>
	 * <tr>
	 * <td>��������</td>
	 * <td> </td>
	 * </tr>
	 * </table>
	 * 
	 * @throws BusinessException 
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group)
			throws BusinessException {

		// select * from md_class where defaulttablename='ic_material_h' or
		// defaulttablename='ic_material_b'
		String headclassid = "1050e13a-a996-4b75-8abb-5a9eceab7ab1";// ���ϳ��ⵥ��ͷԪ����ID����Ҫͨ�����������°汾Ԫ����
		String bodyclassid = "389694b9-6026-48a0-b2e0-b7f98194a8bc";// ���ϳ��ⵥ����Ԫ����ID����Ҫͨ�����������°汾Ԫ����
		// ��װ����VO
		MaterialOutVO billvo = new MaterialOutVO();
		try {

			String pk_country = CreateBillUtils.getPk_Countryzone("CN");// ��ȡ���ң��й���
			String pk_currtype = CreateBillUtils.getPk_Currtype("CNY");// ����

			String itftype = (String) nccBillMap.get("itftype");// �ӿ�����

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "û�л�ȡ����ͷ��Ϣ��");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "û�л�ȡ��������Ϣ��");

			MaterialOutHeadVO headvo = new MaterialOutHeadVO();

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
			String pk_financeorg = orgInfo.get("pk_financeorg");// ȡ��Ӧ�Ĳ�����֯
			// ��ȡ������֯��Ϣ
			HashMap<String, String> financeorginfo = CreateBillUtils.getDocMapByPk("org_financeorg", "pk_financeorg",
					pk_financeorg, "������֯��Ϣ");
			String pk_financeorg_v = financeorginfo.get("pk_vid");// ������֯�汾
			// ��ȡ�ɱ�����Ϣ
			HashMap<String, String> costregioninfo = CreateBillUtils.getDocMapByPk("org_costregion", "pk_org",
					pk_financeorg, "�ɱ���");
			String pk_costregion = costregioninfo.get("pk_costregion");
			for (String key : headMap.keySet()) {
				String existvalue = headMap.get(key);
				if (existvalue == null || "".equals(existvalue))
					continue;
				// ת���ֶ�ֵ
				Object value = TranslateUtils.getTranslateValue(headclassid, pk_group, pk_org, key, existvalue, "4D_H",
						itftype);
				headvo.setAttributeValue(key, value);
			}
			headvo.setPk_group(pk_group);// ����
			headvo.setPk_org(pk_org);// �����֯
			headvo.setPk_org_v(pk_org_v);// �����֯
			// ���òֿ�����
			headvo.setCdrawwarehouseid(headvo.getCwarehouseid());
			// ��Ŀ���� cdrawcalbodyoid
			headvo.setCdrawcalbodyoid(pk_org);// ���ÿ����֯
			headvo.setCdrawcalbodyvid(pk_org_v);// ���ÿ����֯
			headvo.setCfanaceorgoid(pk_financeorg);// ���������֯
			headvo.setCfanaceorgvid(pk_financeorg_v);// ���������֯

			// ccostdomainoid
			headvo.setCcostdomainoid(pk_costregion);// ����ɱ���
			headvo.setCorpoid(pk_org);// ��˾
			headvo.setCorpvid(pk_org_v);// ��˾

//				headvo.setCbiztype(biztype);//ҵ������
			headvo.setCreationtime(new UFDateTime());// ����ʱ��

//				headvo.setVtrantypecode(trantypecode);//��������ͱ���
//				headvo.setCtrantypeid(trantypeid);//���������
			headvo.setDmakedate(headvo.getDbilldate());// �Ƶ�����
			headvo.setFbillflag(2);// ����״̬,1=ɾ����2=���ɣ�3=ǩ�֣�4=��ˣ�5=����У�6=��˲�ͨ����7=�ѵ���״̬

			headvo.setIprintcount(0);// ��ӡ����

			UFDouble totalnum = UFDouble.ZERO_DBL;// ������

			String deptid = headvo.getCdptid();
			if (deptid != null && !deptid.trim().equals("")) {
				HashMap<String, String> deptinfo = CreateBillUtils.getDocMapByPk("org_dept", "pk_dept", deptid, "����");
				String deptvid = deptinfo.get("pk_vid");
				headvo.setCdptvid(deptvid);
			}
			String ctrantypeid = headvo.getCtrantypeid();
			if (ctrantypeid != null && !ctrantypeid.trim().equals("")) {
				String pk_billtypecode = (String) new BaseDAO().executeQuery(
						"select pk_billtypecode from bd_billtype where pk_billtypeid = '" + ctrantypeid + "'",
						new ColumnProcessor());
				headvo.setVtrantypecode(pk_billtypecode);// ��������ͱ���
			}
			String cdptvid = headvo.getCdptvid();
			if (cdptvid != null && !cdptvid.trim().equals("")) {
				String pk_billtypecode = CreateBillUtils.getDocByPk("bd_billtype", "pk_billtypecode", "pk_billtypeid",
						ctrantypeid, " ���������");
				headvo.setVtrantypecode(pk_billtypecode);// ��������ͱ���
			}

			ArrayList<MaterialOutBodyVO> bodyvolist = new ArrayList<MaterialOutBodyVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				MaterialOutBodyVO bodyvo = new MaterialOutBodyVO();

				for (String key : bodymap.keySet()) {
					String existvalue = bodymap.get(key);
					if (existvalue == null || "".equals(existvalue))
						continue;
					// ת���ֶ�ֵ
					Object value = TranslateUtils.getTranslateValue(bodyclassid, pk_group, pk_org, key, existvalue,
							"4D_B", itftype);
					bodyvo.setAttributeValue(key, value);
				}

				bodyvo.setPk_group(headvo.getPk_group());// ����
				bodyvo.setPk_org(headvo.getPk_org());// �����֯
				bodyvo.setPk_org_v(headvo.getPk_org_v());// �����֯
				bodyvo.setBassetcard(UFBoolean.FALSE);// �������豸��Ƭ
				bodyvo.setBbarcodeclose(UFBoolean.FALSE);// �������Ƿ�����ر�
//					bodyvo.setBfixedasset(UFBoolean.FALSE);//��ת��
				bodyvo.setBonroadflag(UFBoolean.FALSE);// �Ƿ���;
//					bodyvo.setBopptaxflag(UFBoolean.FALSE);//������˰��־
//					bodyvo.setBorrowinflag(UFBoolean.FALSE);//����ת�ɹ�
//					bodyvo.setBsourcelargess(UFBoolean.FALSE);//������Ʒ��
				// ��ȡ����λ
				String cunitid = CreateBillUtils.getPk_measdoc(bodyvo.getCmaterialoid());
				bodyvo.setCunitid(cunitid);// ����λ
				String measrate = "1.00/1.00";
				bodyvo.setCastunitid(cunitid);// ��λ
				bodyvo.setVchangerate(measrate);// ������
//					bodyvo.setVqtunitrate(bodyvo.getVchangerate());//���ۻ�����
				bodyvo.setCbodytranstypecode(headvo.getVtrantypecode());// ���������
				bodyvo.setCbodywarehouseid(headvo.getCwarehouseid());// ���ֿ�
				bodyvo.setCcurrencyid(pk_currtype);// ��λ��
//					bodyvo.setCfanaceorgoid(headvo.getCfanaceorgoid());//���������֯
				bodyvo.setCmaterialvid(bodyvo.getCmaterialoid());// ���ϰ汾
				// ccurrencyid ��λ��
				bodyvo.setCcurrencyid(pk_currtype);// ����
				bodyvo.setCorpoid(headvo.getCorpoid());// ��˾
				bodyvo.setCorpvid(headvo.getCorpvid());// ��˾

				// cioliabilityoid ������������ �ȿ��Բ����ã���ǰ�����ĵ���Ϊ�գ���Ҫ��ʱ������
				String crowno = String.valueOf((j + 1) * 10);// �к�
				bodyvo.setCrowno(crowno);// �к�

				// ��ǰ�����ĵ���Ϊ�գ���Ҫ��ʱ������
//					bodyvo.setCsrcmaterialoid(bodyvo.getCmaterialoid());//��Դ����
//					bodyvo.setCsrcmaterialvid(bodyvo.getCmaterialvid());//��Դ����
//					bodyvo.setCvendorid(headvo.getCvendorid());//��Ӧ��
				// ��������ͨ������
//					bodyvo.setDbizdate(headvo.getDbilldate());//�������

				// ����˰��˰��
//					HashMap<String, String> taxInfo = CreateBillUtils.getTaxInfo(bodyvo.getCmaterialoid());
//					String pk_taxcode = taxInfo.get("pk_taxcode");
//					String taxrate = String.valueOf(taxInfo.get("taxrate"));
//					bodyvo.setCtaxcodeid(pk_taxcode);//˰��
//					bodyvo.setNtaxrate(new UFDouble(taxrate));//˰��
//					
//					bodyvo.setFtaxtypeflag(1);//��˰���1=Ӧ˰��ӣ�0=Ӧ˰�ں�?

//					bodyvo.setNassistnum(new UFDouble(1));//ʵ������
//					bodyvo.setNnum(new UFDouble(1));//ʵ��������
				// Ӧ����������Ŀ���� nshouldnum
				bodyvo.setNshouldnum(bodyvo.getNshouldassistnum());
				// ʵ����������Ŀ���� nnum
				bodyvo.setNnum(bodyvo.getNassistnum());

				totalnum = totalnum.add(bodyvo.getNassistnum());// ������
				// ?????������

				bodyvolist.add(bodyvo);
			}
			headvo.setNtotalnum(totalnum);// ������

			billvo.setParentVO(headvo);
			billvo.setChildrenVO(bodyvolist.toArray(new MaterialOutBodyVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ת�������ϳ�������VO����" + e.getMessage());
		}
		// �鿴�ⲿ����ƽ̨����
		// ����Ƿ�������
		this.checkCanInster(billvo);
		processBeforeSave(billvo);
		return billvo;

	}

	/**
	 * ����vo�Ƿ�ɸ���
	 * 
	 * @param vo
	 */
	protected void checkCanInster(AggregatedValueObject vo) {
		new CheckMnyUtil().checkMny(vo);// У�鵥�� ����*����=���
		new CheckScaleUtil().checkScale(vo);
	}

	/**
	 * ���ݱ���ǰ����
	 * 
	 * @param vo
	 */
	protected void processBeforeSave(ICBillVO vo) throws BusinessException {

		if (null == vo)
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008001_0",
					"04008001-0139")/* @res "���ݲ���Ϊ��" */);

		// this.checkNullValue(vo);
		// ��ͷ��֯�����û�и�ֵ����Ҫ�ر����ֶ�����ȡһ��
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
	 * ��ͷ��������Ĭ��ֵ����˾���������ڣ�����״̬����ӡ����������
	 * 
	 * @param vo
	 */
	private void headVOProcess(ICBillHeadVO vo, ICBSContext context) {
		vo.setStatus(VOStatus.NEW);
		// ����
		if (StringUtil.isSEmptyOrNull(vo.getPk_group()))
			vo.setPk_group(context.getPk_group());
		// ��ӡ����
		if (vo.getIprintcount() == null)
			vo.setIprintcount(Integer.valueOf(0));
		// ����״̬
		if (vo.getFbillflag() == null)
			vo.setFbillflag((Integer) ICBillFlag.FREE.value());
		// ��������
		if (vo.getDbilldate() == null)
			vo.setDbilldate(context.getBizDate());
		// ��˾
		if (StringUtil.isSEmptyOrNull(vo.getCorpoid()) || StringUtil.isSEmptyOrNull(vo.getCorpvid())) {
			vo.setCorpoid(context.getOrgInfo().getCorpIDByCalBodyID(vo.getPk_org()));
			vo.setCorpvid(context.getOrgInfo().getCorpVIDByCalBodyID(vo.getPk_org()));
		}

		if (StringUtil.isSEmptyOrNull(vo.getCtrantypeid())) {
			// uap��֧�ֵ������͵ķ��룬��ʱ�Խ�������code��ѯid�ķ�ʽ����������
			String vtrantypecode = vo.getVtrantypecode();
			Map<String, String> map = PfServiceScmUtil.getTrantypeidByCode(new String[] { vtrantypecode });
			vo.setCtrantypeid(map == null ? null : map.get(vtrantypecode));
		}

	}

	/**
	 * ���ݱ��崦��
	 * 
	 * @param vo
	 * @param context
	 * @throws BusinessException
	 */
	private void bodyVOProcess(ICBillVO vo, ICBSContext context) throws BusinessException {
		ICBillBodyVO[] vos = vo.getBodys();
		if (ValueCheckUtil.isNullORZeroLength(vos))
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008001_0",
					"04008001-0141")/* @res "���ݱ��岻��Ϊ��" */);

		VORowNoUtils.setVOsRowNoByRule(vos, ICPubMetaNameConst.CROWNO);// �кŴ���

		ICBillHeadVO head = vo.getHead();
		Map<String, BatchcodeVO> batchmap = this.getBatchcodeVO(vos);
		for (ICBillBodyVO body : vos) {
			body.setStatus(VOStatus.NEW);
			if (StringUtil.isSEmptyOrNull(body.getCmaterialoid()) || StringUtil.isSEmptyOrNull(body.getCmaterialvid()))
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008001_0",
						"04008001-0142")/* @res "���ݱ������ϲ���Ϊ��" */);

			body.setBbarcodeclose(UFBoolean.FALSE);// �������Ƿ�����ر�
			body.setBonroadflag(UFBoolean.FALSE);// �Ƿ���;
			if (body.getNnum() != null && body.getNassistnum() != null && body.getDbizdate() == null)
				body.setDbizdate(context.getBizDate());// ҵ������
			// ����λ
			if (StringUtil.isSEmptyOrNull(body.getCastunitid()))
				body.setCastunitid(context.getInvInfo().getInvBasVO(body.getCmaterialvid()).getPk_stockmeas());

			// �����κŵ�����������ʱ�� ��Ҫ��ȫ�����������б�Ҫʱ(�����ڹ���)��ȫ�������ں�ʧЧ����
			if (!StringUtils.isEmpty(body.getVbatchcode()) && StringUtils.isEmpty(body.getPk_batchcode())) {
				BatchcodeVO batchvo = batchmap.get(body.getCmaterialvid() + body.getVbatchcode());
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
	 * ���ݱ�ͷ���ñ���Ĭ��ֵ�����弯�ţ������֯����˾���ֿ⣬��������
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
	 * ��ȡ���κŵ���
	 * 
	 * @param vos
	 * @return Map<String(cmaterialvid+vbatchcode), BatchcodeVO���ε���>
	 */
	private Map<String, BatchcodeVO> getBatchcodeVO(ICBillBodyVO[] vos) {
		List<String> cmaterialvidList = new ArrayList<String>();
		List<String> vbatchcodeList = new ArrayList<String>();
		Set<String> materialbatch = new HashSet<String>();
		for (ICBillBodyVO body : vos) {
			if (body.getCmaterialvid() != null && body.getVbatchcode() != null) {
				if (materialbatch.contains(body.getCmaterialvid() + body.getVbatchcode())) {
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
		IBatchcodePubService batchservice = NCLocator.getInstance().lookup(IBatchcodePubService.class);
		BatchcodeVO[] batchvos = null;
		try {
			batchvos = batchservice.queryBatchVOs(cmaterialvidList.toArray(new String[0]),
					vbatchcodeList.toArray(new String[0]));
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}

		if (batchvos == null || batchvos.length == 0) {
			return new HashMap<String, BatchcodeVO>();
		}
		Map<String, BatchcodeVO> batchmap = new HashMap<String, BatchcodeVO>();
		for (BatchcodeVO batchvo : batchvos) {
			batchmap.put(batchvo.getCmaterialvid() + batchvo.getVbatchcode(), batchvo);
		}
		return batchmap;
	}

}
