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
import nc.itf.uapbd.service.ICreate4IBillVO;
import nc.pubitf.scmf.ic.mbatchcode.IBatchcodePubService;
import nc.util.uapbd.service.AssertUtils;
import nc.util.uapbd.service.CreateBillUtils;
import nc.util.uapbd.service.TranslateUtils;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.general.define.ICBillFlag;
import nc.vo.ic.general.define.ICBillHeadVO;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.m4i.entity.GeneralOutBodyVO;
import nc.vo.ic.m4i.entity.GeneralOutHeadVO;
import nc.vo.ic.m4i.entity.GeneralOutVO;
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
 * ����������ⵥ
 */
public class Create4IBillVOImpl implements ICreate4IBillVO {

	/**
	 * �����ۺ�VO<br>
	 * 
	 * @throws BusinessException
	 */
	@Override
	public AggregatedValueObject createBillVO(HashMap<String, Object> nccBillMap, String pk_group)
			throws BusinessException {

		// select * from md_class where defaulttablename='ic_generalout_h' or
		// defaulttablename='ic_generalout_b'
		String headclassid = "eecd0e42-aeea-4a3c-919d-def114512fad";// ����������ⵥ����ͷԪ����ID����Ҫͨ�����������°汾Ԫ����
		String bodyclassid = "38a3af5d-3c07-42a9-8524-c78c9c4e66ec";// ����������ⵥ����Ԫ����ID����Ҫͨ�����������°汾Ԫ����
		// ��װ����VO
		GeneralOutVO billvo = new GeneralOutVO();
		try {

			String itftype = (String) nccBillMap.get("itftype");// �ӿ�����

			HashMap<String, String> headMap = (HashMap<String, String>) nccBillMap.get("head");
			AssertUtils.mapIsNull(headMap, "û�л�ȡ����ͷ��Ϣ��");

			ArrayList<HashMap<String, String>> bodyList = (ArrayList<HashMap<String, String>>) nccBillMap.get("body");
			AssertUtils.listIsNull(bodyList, "û�л�ȡ��������Ϣ��");

			GeneralOutHeadVO headvo = new GeneralOutHeadVO();

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
				Object value = TranslateUtils.getTranslateValue(headclassid, pk_group, pk_org, key, existvalue, "4I_H",
						itftype);
				headvo.setAttributeValue(key, value);
			}
			headvo.setPk_group(pk_group);// ����
			headvo.setPk_org(pk_org);// �����֯
			headvo.setPk_org_v(pk_org_v);// �����֯

			headvo.setCorpoid(pk_org);// ��˾
			headvo.setCorpvid(pk_org_v);// ��˾

			headvo.setCreationtime(new UFDateTime());// ����ʱ��

			headvo.setDmakedate(headvo.getDbilldate());// �Ƶ�����
			headvo.setFbillflag(2);
			;// ����״̬,1=ɾ����2=���ɣ�3=ǩ�֣�4=��ˣ�5=����У�6=��˲�ͨ����7=�ѵ���״̬

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
				String pk_billtypecode = CreateBillUtils.getDocByPk("bd_billtype", "pk_billtypecode", "pk_billtypeid",
						ctrantypeid, " ���������");
				headvo.setVtrantypecode(pk_billtypecode);// ��������ͱ���
			}

			ArrayList<GeneralOutBodyVO> bodyvolist = new ArrayList<GeneralOutBodyVO>();

			for (int j = 0; j < bodyList.size(); j++) {

				HashMap<String, String> bodymap = (HashMap<String, String>) bodyList.get(j);
				GeneralOutBodyVO bodyvo = new GeneralOutBodyVO();

				for (String key : bodymap.keySet()) {
					String existvalue = bodymap.get(key);
					if (existvalue == null || "".equals(existvalue))
						continue;
					// ת���ֶ�ֵ
					Object value = TranslateUtils.getTranslateValue(bodyclassid, pk_group, pk_org, key, existvalue,
							"4I_B", itftype);
					bodyvo.setAttributeValue(key, value);
				}

				bodyvo.setPk_group(headvo.getPk_group());// ����
				bodyvo.setPk_org(headvo.getPk_org());// �����֯
				bodyvo.setPk_org_v(headvo.getPk_org_v());// �����֯

				// ��ȡ����λ
				String cunitid = CreateBillUtils.getPk_measdoc(bodyvo.getCmaterialoid());
				bodyvo.setCunitid(cunitid);// ����λ
				String measrate = "1.00/1.00";
				bodyvo.setCastunitid(cunitid);// ��λ
				bodyvo.setVchangerate(measrate);// ������

				bodyvo.setCbodytranstypecode(headvo.getVtrantypecode());// ���������
				bodyvo.setCbodywarehouseid(headvo.getCwarehouseid());// ���ֿ�

				bodyvo.setCmaterialvid(bodyvo.getCmaterialoid());// ���ϰ汾

				bodyvo.setCorpoid(headvo.getCorpoid());// ��˾
				bodyvo.setCorpvid(headvo.getCorpvid());// ��˾
				String crowno = String.valueOf((j + 1) * 10);// �к�
				bodyvo.setCrowno(crowno);// �к�
				bodyvo.setDbizdate(headvo.getDbilldate());// �������
				totalnum = totalnum.add(bodyvo.getNassistnum());// ������
				bodyvolist.add(bodyvo);
			}
			headvo.setNtotalnum(totalnum);// ������

			billvo.setParentVO(headvo);
			billvo.setChildrenVO(bodyvolist.toArray(new GeneralOutBodyVO[0]));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ת������������ⵥ����VO����" + e.getMessage());
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
		new CheckMnyUtil().checkMny(vo);
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
