package nc.impl.uapbd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.ia.mi9.II9Maintain;
import nc.itf.ia.mia.IIAMaintain;
import nc.itf.uap.pf.IPFBusiAction;
import nc.itf.uapbd.service.IBusinessBillSave;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.util.uapbd.service.ServiceUtils;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.payable.AggPayableBillVO;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mia.entity.IABillVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.ic.m46.entity.FinProdInVO;
import nc.vo.ic.m4a.entity.GeneralInVO;
import nc.vo.ic.m4c.entity.SaleOutVO;
import nc.vo.ic.m4d.entity.MaterialOutVO;
import nc.vo.ic.m4i.entity.GeneralOutVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class BusinessBillSaveImpl implements IBusinessBillSave {

	private BaseDAO getDao() {
		return ServiceUtils.getBaseDAO();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveBill(HashMap<String, HashMap<String, AggregatedValueObject>> voOprationMap)
			throws BusinessException {
		// 先处理删除的
		HashMap<String, AggregatedValueObject> del_billvomap = voOprationMap.get("Delete");
		if (del_billvomap != null && del_billvomap.size() > 0) {
			for (Entry<String, AggregatedValueObject> entry : del_billvomap.entrySet()) {
				String pk_billtypecode = entry.getKey();
				AggregatedValueObject avo = entry.getValue();
				if (avo == null) {
					continue;
				}
				if ("45".equals(pk_billtypecode)) {// 库存采购入库单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid from ic_purchasein_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到库存采购入库的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<PurchaseInVO> billquery = new BillQuery<PurchaseInVO>(PurchaseInVO.class);
					PurchaseInVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cgeneralhid") });
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("CANCELSIGN", "45",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "45", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);

				} else if ("46".equals(pk_billtypecode)) {// 库存产成品入库
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid   from ic_finprodin_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到库存产成品入库的旧有数据故无法删除，请查看proc是否正确，云天主键为:" + ytid);
					}
					BillQuery<FinProdInVO> billquery = new BillQuery<FinProdInVO>(FinProdInVO.class);
					FinProdInVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cgeneralhid") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("CANCELSIGN", "46",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "46", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("4A".equals(pk_billtypecode)) {// 库存其他入库单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid from ic_generalin_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到库存其他入库单的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<GeneralInVO> billquery = new BillQuery<GeneralInVO>(GeneralInVO.class);
					GeneralInVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cgeneralhid") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("CANCELSIGN", "4A",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "4A", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("4C".equals(pk_billtypecode)) {// 库存销售出库
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid from ic_saleout_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到库存销售出库的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<SaleOutVO> billquery = new BillQuery<SaleOutVO>(SaleOutVO.class);
					SaleOutVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cgeneralhid") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("CANCELSIGN", "4C",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "4C", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("4D".equals(pk_billtypecode)) {// 库存材料出库
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid  from ic_material_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到库存材料出库的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<MaterialOutVO> billquery = new BillQuery<MaterialOutVO>(MaterialOutVO.class);
					MaterialOutVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cgeneralhid") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("CANCELSIGN", "4D",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "4D", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("4I".equals(pk_billtypecode)) {// 库存其他出库单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select cgeneralhid from ic_generalout_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到库存其他出库单的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<GeneralOutVO> billquery = new BillQuery<GeneralOutVO>(GeneralOutVO.class);
					GeneralOutVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cgeneralhid") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("CANCELSIGN", "4I",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "4I", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("F0".equals(pk_billtypecode)) {// 应收
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select  pk_recbill from ar_recbill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "' and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到应收单的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<AggReceivableBillVO> billquery = new BillQuery<AggReceivableBillVO>(
							AggReceivableBillVO.class);
					AggReceivableBillVO[] delbillvos = billquery
							.query(new String[] { op_list.get(0).get("pk_recbill") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("UNAPPROVE", "F0",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "F0", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("F1".equals(pk_billtypecode)) {// 应付单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select  pk_payablebill  from ap_payablebill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "'  and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到应付单的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<AggPayableBillVO> billquery = new BillQuery<AggPayableBillVO>(AggPayableBillVO.class);
					AggPayableBillVO[] delbillvos = billquery
							.query(new String[] { op_list.get(0).get("pk_payablebill") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("UNAPPROVE", "F1",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "F1", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("F2".equals(pk_billtypecode)) {// 应收模块的收款单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select pk_gatherbill from ar_gatherbill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "' and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到应付模块的付款单的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<AggGatheringBillVO> billquery = new BillQuery<AggGatheringBillVO>(
							AggGatheringBillVO.class);
					AggGatheringBillVO[] delbillvos = billquery
							.query(new String[] { op_list.get(0).get("pk_gatherbill") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("UNAPPROVE", "F2",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "F2", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("F3".equals(pk_billtypecode)) {// 应付模块的付款单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select pk_paybill from ap_paybill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "' and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到应付模块的付款单的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<AggPayBillVO> billquery = new BillQuery<AggPayBillVO>(AggPayBillVO.class);
					AggPayBillVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("pk_paybill") });

					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("UNAPPROVE", "F3",
							null, delbillvos[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("DELETE", "F3", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
				} else if ("I9".equals(pk_billtypecode)) {// 存货核算入库调整
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cbillid   from ia_i9bill where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到存货核算的入库调整的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<I9BillVO> billquery = new BillQuery<I9BillVO>(I9BillVO.class);
					I9BillVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cbillid") });
					// 没有脚本，此单据
					NCLocator.getInstance().lookup(II9Maintain.class).deleteI9(delbillvos);
				} else if ("IA".equals(pk_billtypecode)) {// 存货核算出库调整
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cbillid   from ia_iabill where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list == null || op_list.size() == 0) {
						throw new BusinessException("没有查找到存货核算的入库调整的旧有数据故无法删除，请查看opration是否正确，云天主键为:" + ytid);
					}
					BillQuery<IABillVO> billquery = new BillQuery<IABillVO>(IABillVO.class);
					IABillVO[] delbillvos = billquery.query(new String[] { op_list.get(0).get("cbillid") });
					// 没有脚本，此单据
					NCLocator.getInstance().lookup(IIAMaintain.class).deleteIA(delbillvos);
				} else {
					throw new BusinessException("未找到对应单据类型的删除处理,暂不支持!");
				}
			}
		}

		// 新增
		HashMap<String, AggregatedValueObject> add_billvomap = voOprationMap.get("Add");
		if (add_billvomap != null && add_billvomap.size() > 0) {
			for (Entry<String, AggregatedValueObject> entry : add_billvomap.entrySet()) {
				String pk_billtypecode = entry.getKey();
				AggregatedValueObject avo = entry.getValue();
				if (avo == null) {
					continue;
				}
				if ("45".equals(pk_billtypecode)) {// 采购入库库存
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid from ic_purchasein_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("库存采购入库单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("WRITE", "45", null,
							avo, null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SIGN", "45", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
					continue;
				} else if ("46".equals(pk_billtypecode)) {// 库存产成品入库
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid   from ic_finprodin_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("库存产成品入库单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("WRITE", "46", null,
							avo, null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SIGN", "46", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
					continue;
				} else if ("4A".equals(pk_billtypecode)) {// 库存其他入库单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid from ic_generalin_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("库存其他入库单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("WRITE", "4A", null,
							avo, null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SIGN", "4A", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
					continue;
				} else if ("4C".equals(pk_billtypecode)) {// 库存销售出库
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid from ic_saleout_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("库存销售出库单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("WRITE", "4C", null,
							avo, null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SIGN", "4C", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
					continue;
				} else if ("4D".equals(pk_billtypecode)) {// 库存材料出库
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cgeneralhid  from ic_material_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("库存材料出库单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("WRITE", "4D", null,
							avo, null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SIGN", "4D", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
					continue;
				} else if ("4I".equals(pk_billtypecode)) {// 库存其他出库单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select cgeneralhid from ic_generalout_h where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("库存其他出库单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("WRITE", "4I", null,
							avo, null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SIGN", "4I", null,
							(AggregatedValueObject) ((Object[]) obj)[0], null, null);
					continue;
				} else if ("F0".equals(pk_billtypecode)) {// 应收
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select  pk_recbill from ar_recbill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "' and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("应收单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SAVE", "F0", null,
							avo, null, null);
					Object obj2 = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("COMMIT", "F0",
							null, (AggregatedValueObject) ((Object[]) obj)[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("APPROVE", "F0", null,
							(AggregatedValueObject) ((Object[]) obj2)[0], null, null);
					continue;
				} else if ("F1".equals(pk_billtypecode)) {// 应付单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select  pk_payablebill  from ap_payablebill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "' and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("应付单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SAVE", "F1", null,
							avo, null, null);
					Object obj2 = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("COMMIT", "F1",
							null, (AggregatedValueObject) ((Object[]) obj)[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("APPROVE", "F1", null,
							(AggregatedValueObject) ((Object[]) obj2)[0], null, null);
					continue;
				} else if ("F2".equals(pk_billtypecode)) {// 应收模块的收款单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select pk_gatherbill from ar_gatherbill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "' and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("收款单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SAVE", "F2", null,
							avo, null, null);
					Object obj2 = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("COMMIT", "F2",
							null, (AggregatedValueObject) ((Object[]) obj)[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("APPROVE", "F2", null,
							(AggregatedValueObject) ((Object[]) obj2)[0], null, null);
					continue;
				} else if ("F3".equals(pk_billtypecode)) {// 应付模块的付款单
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("def1").toString();
					String ytid = avo.getParentVO().getAttributeValue("def3").toString();
					String sql = " select pk_paybill from ap_paybill where dr=0 and pk_org = '" + pk_org
							+ "' and def1 = '" + ytdjlx + "' and def3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("付款单已存在，不能重复传送！");
					}
					Object obj = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("SAVE", "F3", null,
							avo, null, null);
					Object obj2 = NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("COMMIT", "F3",
							null, (AggregatedValueObject) ((Object[]) obj)[0], null, null);
					NCLocator.getInstance().lookup(IPFBusiAction.class).processAction("APPROVE", "F3", null,
							(AggregatedValueObject) ((Object[]) obj2)[0], null, null);
					continue;
				} else if ("I9".equals(pk_billtypecode)) {// 存货核算入库调整
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cbillid   from ia_i9bill where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("入库调整单已存在，不能重复传送！");
					}
					// 没有脚本，此单据
					NCLocator.getInstance().lookup(II9Maintain.class).insertI9(new I9BillVO[] { (I9BillVO) avo });
					continue;
				} else if ("IA".equals(pk_billtypecode)) {// 存货核算出库调整
					String pk_org = avo.getParentVO().getAttributeValue("pk_org").toString();
					String ytdjlx = avo.getParentVO().getAttributeValue("vdef1").toString();
					String ytid = avo.getParentVO().getAttributeValue("vdef3").toString();
					String sql = " select  cbillid   from ia_iabill where dr=0 and pk_org = '" + pk_org
							+ "' and vdef1 = '" + ytdjlx + "' and vdef3='" + ytid + "' ";
					List<Map<String, String>> op_list = (List<Map<String, String>>) getDao().executeQuery(sql,
							new MapListProcessor());
					if (op_list != null && op_list.size() > 0) {
						throw new BusinessException("出库调整单已存在，不能重复传送！");
					}
					// 没有脚本，此单据
					NCLocator.getInstance().lookup(IIAMaintain.class).insertIA(new IABillVO[] { (IABillVO) avo });
					continue;
				} else {
					throw new BusinessException("未找到对应单据类型的新增处理,暂不支持");
				}

			}
		}
	}

}
