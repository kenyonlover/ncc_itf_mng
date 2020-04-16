package nc.vo.uapbd.itfconfig;

import nc.vo.pub.*;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加类的描述信息
 * </p>
 *  创建日期:2020-4-10
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class ItfConfigBillVO extends nc.vo.pub.SuperVO{
	
    private java.lang.String pk_itfconfigbill;
    private java.lang.String code;
    private java.lang.String name;
    private java.lang.Integer rowno;
    private java.lang.String remark;
    private java.lang.String busibilltype;
    private java.lang.String busibilltypecode;
    private java.lang.String nccbilltype;
    private java.lang.String nccbilltypecode;
    private java.lang.String pk_group;
    private java.lang.String pk_org;
    private java.lang.String pk_org_v;
    private java.lang.String creator;
    private nc.vo.pub.lang.UFDateTime creationtime;
    private java.lang.String modifier;
    private nc.vo.pub.lang.UFDateTime modifiedtime;
    private nc.vo.pub.lang.UFDateTime maketime;
    private nc.vo.pub.lang.UFDateTime lastmaketime;
    private java.lang.String billno;
    private java.lang.String busitype;
    private java.lang.String billmaker;
    private java.lang.String approver;
    private java.lang.Integer approvestatus;
    private java.lang.String approvenote;
    private nc.vo.pub.lang.UFDateTime approvedate;
    private java.lang.String transtype;
    private java.lang.String billtype;
    private java.lang.String transtypepk;
    private java.lang.String srcbilltype;
    private java.lang.String srcbillid;
    private java.lang.Integer emendenum;
    private java.lang.String billversionpk;
    private java.lang.String def1;
    private java.lang.String def2;
    private java.lang.String def3;
    private java.lang.String def4;
    private java.lang.String def5;
    private java.lang.String def6;
    private java.lang.String def7;
    private java.lang.String def8;
    private java.lang.String def9;
    private java.lang.String def10;
    private java.lang.String def11;
    private java.lang.String def12;
    private java.lang.String def13;
    private java.lang.String def14;
    private java.lang.String def15;
    private java.lang.String def16;
    private java.lang.String def17;
    private java.lang.String def18;
    private java.lang.String def19;
    private java.lang.String def20;
    private java.lang.Integer dr = 0;
    private nc.vo.pub.lang.UFDateTime ts;    
	
    private nc.vo.uapbd.itfconfig.ItfConfigItemVO[] itfconfigitemvo;
	
    public static final String PK_ITFCONFIGBILL = "pk_itfconfigbill";
    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String ROWNO = "rowno";
    public static final String REMARK = "remark";
    public static final String BUSIBILLTYPE = "busibilltype";
    public static final String BUSIBILLTYPECODE = "busibilltypecode";
    public static final String NCCBILLTYPE = "nccbilltype";
    public static final String NCCBILLTYPECODE = "nccbilltypecode";
    public static final String PK_GROUP = "pk_group";
    public static final String PK_ORG = "pk_org";
    public static final String PK_ORG_V = "pk_org_v";
    public static final String CREATOR = "creator";
    public static final String CREATIONTIME = "creationtime";
    public static final String MODIFIER = "modifier";
    public static final String MODIFIEDTIME = "modifiedtime";
    public static final String MAKETIME = "maketime";
    public static final String LASTMAKETIME = "lastmaketime";
    public static final String BILLNO = "billno";
    public static final String BUSITYPE = "busitype";
    public static final String BILLMAKER = "billmaker";
    public static final String APPROVER = "approver";
    public static final String APPROVESTATUS = "approvestatus";
    public static final String APPROVENOTE = "approvenote";
    public static final String APPROVEDATE = "approvedate";
    public static final String TRANSTYPE = "transtype";
    public static final String BILLTYPE = "billtype";
    public static final String TRANSTYPEPK = "transtypepk";
    public static final String SRCBILLTYPE = "srcbilltype";
    public static final String SRCBILLID = "srcbillid";
    public static final String EMENDENUM = "emendenum";
    public static final String BILLVERSIONPK = "billversionpk";
    public static final String DEF1 = "def1";
    public static final String DEF2 = "def2";
    public static final String DEF3 = "def3";
    public static final String DEF4 = "def4";
    public static final String DEF5 = "def5";
    public static final String DEF6 = "def6";
    public static final String DEF7 = "def7";
    public static final String DEF8 = "def8";
    public static final String DEF9 = "def9";
    public static final String DEF10 = "def10";
    public static final String DEF11 = "def11";
    public static final String DEF12 = "def12";
    public static final String DEF13 = "def13";
    public static final String DEF14 = "def14";
    public static final String DEF15 = "def15";
    public static final String DEF16 = "def16";
    public static final String DEF17 = "def17";
    public static final String DEF18 = "def18";
    public static final String DEF19 = "def19";
    public static final String DEF20 = "def20";

	/**
	 * 属性 pk_itfconfigbill的Getter方法.属性名：pk_itfconfigbill
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_itfconfigbill () {
		return pk_itfconfigbill;
	}   
	/**
	 * 属性pk_itfconfigbill的Setter方法.属性名：pk_itfconfigbill
	 * 创建日期:2020-4-10
	 * @param newPk_itfconfigbill java.lang.String
	 */
	public void setPk_itfconfigbill (java.lang.String newPk_itfconfigbill ) {
	 	this.pk_itfconfigbill = newPk_itfconfigbill;
	} 	 
	
	/**
	 * 属性 code的Getter方法.属性名：编码
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getCode () {
		return code;
	}   
	/**
	 * 属性code的Setter方法.属性名：编码
	 * 创建日期:2020-4-10
	 * @param newCode java.lang.String
	 */
	public void setCode (java.lang.String newCode ) {
	 	this.code = newCode;
	} 	 
	
	/**
	 * 属性 name的Getter方法.属性名：名称
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getName () {
		return name;
	}   
	/**
	 * 属性name的Setter方法.属性名：名称
	 * 创建日期:2020-4-10
	 * @param newName java.lang.String
	 */
	public void setName (java.lang.String newName ) {
	 	this.name = newName;
	} 	 
	
	/**
	 * 属性 rowno的Getter方法.属性名：行号
	 *  创建日期:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getRowno () {
		return rowno;
	}   
	/**
	 * 属性rowno的Setter方法.属性名：行号
	 * 创建日期:2020-4-10
	 * @param newRowno java.lang.Integer
	 */
	public void setRowno (java.lang.Integer newRowno ) {
	 	this.rowno = newRowno;
	} 	 
	
	/**
	 * 属性 remark的Getter方法.属性名：备注
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getRemark () {
		return remark;
	}   
	/**
	 * 属性remark的Setter方法.属性名：备注
	 * 创建日期:2020-4-10
	 * @param newRemark java.lang.String
	 */
	public void setRemark (java.lang.String newRemark ) {
	 	this.remark = newRemark;
	} 	 
	
	/**
	 * 属性 busibilltype的Getter方法.属性名：业务单据类型
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusibilltype () {
		return busibilltype;
	}   
	/**
	 * 属性busibilltype的Setter方法.属性名：业务单据类型
	 * 创建日期:2020-4-10
	 * @param newBusibilltype java.lang.String
	 */
	public void setBusibilltype (java.lang.String newBusibilltype ) {
	 	this.busibilltype = newBusibilltype;
	} 	 
	
	/**
	 * 属性 busibilltypecode的Getter方法.属性名：业务单据类型编码
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusibilltypecode () {
		return busibilltypecode;
	}   
	/**
	 * 属性busibilltypecode的Setter方法.属性名：业务单据类型编码
	 * 创建日期:2020-4-10
	 * @param newBusibilltypecode java.lang.String
	 */
	public void setBusibilltypecode (java.lang.String newBusibilltypecode ) {
	 	this.busibilltypecode = newBusibilltypecode;
	} 	 
	
	/**
	 * 属性 nccbilltype的Getter方法.属性名：NCC单据类型
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccbilltype () {
		return nccbilltype;
	}   
	/**
	 * 属性nccbilltype的Setter方法.属性名：NCC单据类型
	 * 创建日期:2020-4-10
	 * @param newNccbilltype java.lang.String
	 */
	public void setNccbilltype (java.lang.String newNccbilltype ) {
	 	this.nccbilltype = newNccbilltype;
	} 	 
	
	/**
	 * 属性 nccbilltypecode的Getter方法.属性名：NCC单据类型编码
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccbilltypecode () {
		return nccbilltypecode;
	}   
	/**
	 * 属性nccbilltypecode的Setter方法.属性名：NCC单据类型编码
	 * 创建日期:2020-4-10
	 * @param newNccbilltypecode java.lang.String
	 */
	public void setNccbilltypecode (java.lang.String newNccbilltypecode ) {
	 	this.nccbilltypecode = newNccbilltypecode;
	} 	 
	
	/**
	 * 属性 pk_group的Getter方法.属性名：集团
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return pk_group;
	}   
	/**
	 * 属性pk_group的Setter方法.属性名：集团
	 * 创建日期:2020-4-10
	 * @param newPk_group java.lang.String
	 */
	public void setPk_group (java.lang.String newPk_group ) {
	 	this.pk_group = newPk_group;
	} 	 
	
	/**
	 * 属性 pk_org的Getter方法.属性名：组织
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return pk_org;
	}   
	/**
	 * 属性pk_org的Setter方法.属性名：组织
	 * 创建日期:2020-4-10
	 * @param newPk_org java.lang.String
	 */
	public void setPk_org (java.lang.String newPk_org ) {
	 	this.pk_org = newPk_org;
	} 	 
	
	/**
	 * 属性 pk_org_v的Getter方法.属性名：组织版本
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v () {
		return pk_org_v;
	}   
	/**
	 * 属性pk_org_v的Setter方法.属性名：组织版本
	 * 创建日期:2020-4-10
	 * @param newPk_org_v java.lang.String
	 */
	public void setPk_org_v (java.lang.String newPk_org_v ) {
	 	this.pk_org_v = newPk_org_v;
	} 	 
	
	/**
	 * 属性 creator的Getter方法.属性名：创建人
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getCreator () {
		return creator;
	}   
	/**
	 * 属性creator的Setter方法.属性名：创建人
	 * 创建日期:2020-4-10
	 * @param newCreator java.lang.String
	 */
	public void setCreator (java.lang.String newCreator ) {
	 	this.creator = newCreator;
	} 	 
	
	/**
	 * 属性 creationtime的Getter方法.属性名：创建时间
	 *  创建日期:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime () {
		return creationtime;
	}   
	/**
	 * 属性creationtime的Setter方法.属性名：创建时间
	 * 创建日期:2020-4-10
	 * @param newCreationtime nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime (nc.vo.pub.lang.UFDateTime newCreationtime ) {
	 	this.creationtime = newCreationtime;
	} 	 
	
	/**
	 * 属性 modifier的Getter方法.属性名：修改人
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getModifier () {
		return modifier;
	}   
	/**
	 * 属性modifier的Setter方法.属性名：修改人
	 * 创建日期:2020-4-10
	 * @param newModifier java.lang.String
	 */
	public void setModifier (java.lang.String newModifier ) {
	 	this.modifier = newModifier;
	} 	 
	
	/**
	 * 属性 modifiedtime的Getter方法.属性名：修改时间
	 *  创建日期:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime () {
		return modifiedtime;
	}   
	/**
	 * 属性modifiedtime的Setter方法.属性名：修改时间
	 * 创建日期:2020-4-10
	 * @param newModifiedtime nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime (nc.vo.pub.lang.UFDateTime newModifiedtime ) {
	 	this.modifiedtime = newModifiedtime;
	} 	 
	
	/**
	 * 属性 maketime的Getter方法.属性名：制单时间
	 *  创建日期:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getMaketime () {
		return maketime;
	}   
	/**
	 * 属性maketime的Setter方法.属性名：制单时间
	 * 创建日期:2020-4-10
	 * @param newMaketime nc.vo.pub.lang.UFDateTime
	 */
	public void setMaketime (nc.vo.pub.lang.UFDateTime newMaketime ) {
	 	this.maketime = newMaketime;
	} 	 
	
	/**
	 * 属性 lastmaketime的Getter方法.属性名：最后修改时间
	 *  创建日期:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getLastmaketime () {
		return lastmaketime;
	}   
	/**
	 * 属性lastmaketime的Setter方法.属性名：最后修改时间
	 * 创建日期:2020-4-10
	 * @param newLastmaketime nc.vo.pub.lang.UFDateTime
	 */
	public void setLastmaketime (nc.vo.pub.lang.UFDateTime newLastmaketime ) {
	 	this.lastmaketime = newLastmaketime;
	} 	 
	
	/**
	 * 属性 billno的Getter方法.属性名：单据号
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBillno () {
		return billno;
	}   
	/**
	 * 属性billno的Setter方法.属性名：单据号
	 * 创建日期:2020-4-10
	 * @param newBillno java.lang.String
	 */
	public void setBillno (java.lang.String newBillno ) {
	 	this.billno = newBillno;
	} 	 
	
	/**
	 * 属性 busitype的Getter方法.属性名：业务类型
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusitype () {
		return busitype;
	}   
	/**
	 * 属性busitype的Setter方法.属性名：业务类型
	 * 创建日期:2020-4-10
	 * @param newBusitype java.lang.String
	 */
	public void setBusitype (java.lang.String newBusitype ) {
	 	this.busitype = newBusitype;
	} 	 
	
	/**
	 * 属性 billmaker的Getter方法.属性名：制单人
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBillmaker () {
		return billmaker;
	}   
	/**
	 * 属性billmaker的Setter方法.属性名：制单人
	 * 创建日期:2020-4-10
	 * @param newBillmaker java.lang.String
	 */
	public void setBillmaker (java.lang.String newBillmaker ) {
	 	this.billmaker = newBillmaker;
	} 	 
	
	/**
	 * 属性 approver的Getter方法.属性名：审批人
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getApprover () {
		return approver;
	}   
	/**
	 * 属性approver的Setter方法.属性名：审批人
	 * 创建日期:2020-4-10
	 * @param newApprover java.lang.String
	 */
	public void setApprover (java.lang.String newApprover ) {
	 	this.approver = newApprover;
	} 	 
	
	/**
	 * 属性 approvestatus的Getter方法.属性名：审批状态
	 *  创建日期:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getApprovestatus () {
		return approvestatus;
	}   
	/**
	 * 属性approvestatus的Setter方法.属性名：审批状态
	 * 创建日期:2020-4-10
	 * @param newApprovestatus java.lang.Integer
	 */
	public void setApprovestatus (java.lang.Integer newApprovestatus ) {
	 	this.approvestatus = newApprovestatus;
	} 	 
	
	/**
	 * 属性 approvenote的Getter方法.属性名：审批批语
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getApprovenote () {
		return approvenote;
	}   
	/**
	 * 属性approvenote的Setter方法.属性名：审批批语
	 * 创建日期:2020-4-10
	 * @param newApprovenote java.lang.String
	 */
	public void setApprovenote (java.lang.String newApprovenote ) {
	 	this.approvenote = newApprovenote;
	} 	 
	
	/**
	 * 属性 approvedate的Getter方法.属性名：审批时间
	 *  创建日期:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getApprovedate () {
		return approvedate;
	}   
	/**
	 * 属性approvedate的Setter方法.属性名：审批时间
	 * 创建日期:2020-4-10
	 * @param newApprovedate nc.vo.pub.lang.UFDateTime
	 */
	public void setApprovedate (nc.vo.pub.lang.UFDateTime newApprovedate ) {
	 	this.approvedate = newApprovedate;
	} 	 
	
	/**
	 * 属性 transtype的Getter方法.属性名：交易类型
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTranstype () {
		return transtype;
	}   
	/**
	 * 属性transtype的Setter方法.属性名：交易类型
	 * 创建日期:2020-4-10
	 * @param newTranstype java.lang.String
	 */
	public void setTranstype (java.lang.String newTranstype ) {
	 	this.transtype = newTranstype;
	} 	 
	
	/**
	 * 属性 billtype的Getter方法.属性名：单据类型
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBilltype () {
		return billtype;
	}   
	/**
	 * 属性billtype的Setter方法.属性名：单据类型
	 * 创建日期:2020-4-10
	 * @param newBilltype java.lang.String
	 */
	public void setBilltype (java.lang.String newBilltype ) {
	 	this.billtype = newBilltype;
	} 	 
	
	/**
	 * 属性 transtypepk的Getter方法.属性名：交易类型pk
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTranstypepk () {
		return transtypepk;
	}   
	/**
	 * 属性transtypepk的Setter方法.属性名：交易类型pk
	 * 创建日期:2020-4-10
	 * @param newTranstypepk java.lang.String
	 */
	public void setTranstypepk (java.lang.String newTranstypepk ) {
	 	this.transtypepk = newTranstypepk;
	} 	 
	
	/**
	 * 属性 srcbilltype的Getter方法.属性名：来源单据类型
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getSrcbilltype () {
		return srcbilltype;
	}   
	/**
	 * 属性srcbilltype的Setter方法.属性名：来源单据类型
	 * 创建日期:2020-4-10
	 * @param newSrcbilltype java.lang.String
	 */
	public void setSrcbilltype (java.lang.String newSrcbilltype ) {
	 	this.srcbilltype = newSrcbilltype;
	} 	 
	
	/**
	 * 属性 srcbillid的Getter方法.属性名：来源单据id
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getSrcbillid () {
		return srcbillid;
	}   
	/**
	 * 属性srcbillid的Setter方法.属性名：来源单据id
	 * 创建日期:2020-4-10
	 * @param newSrcbillid java.lang.String
	 */
	public void setSrcbillid (java.lang.String newSrcbillid ) {
	 	this.srcbillid = newSrcbillid;
	} 	 
	
	/**
	 * 属性 emendenum的Getter方法.属性名：修订枚举
	 *  创建日期:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getEmendenum () {
		return emendenum;
	}   
	/**
	 * 属性emendenum的Setter方法.属性名：修订枚举
	 * 创建日期:2020-4-10
	 * @param newEmendenum java.lang.Integer
	 */
	public void setEmendenum (java.lang.Integer newEmendenum ) {
	 	this.emendenum = newEmendenum;
	} 	 
	
	/**
	 * 属性 billversionpk的Getter方法.属性名：单据版本pk
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBillversionpk () {
		return billversionpk;
	}   
	/**
	 * 属性billversionpk的Setter方法.属性名：单据版本pk
	 * 创建日期:2020-4-10
	 * @param newBillversionpk java.lang.String
	 */
	public void setBillversionpk (java.lang.String newBillversionpk ) {
	 	this.billversionpk = newBillversionpk;
	} 	 
	
	/**
	 * 属性 def1的Getter方法.属性名：自定义项1
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef1 () {
		return def1;
	}   
	/**
	 * 属性def1的Setter方法.属性名：自定义项1
	 * 创建日期:2020-4-10
	 * @param newDef1 java.lang.String
	 */
	public void setDef1 (java.lang.String newDef1 ) {
	 	this.def1 = newDef1;
	} 	 
	
	/**
	 * 属性 def2的Getter方法.属性名：自定义项2
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef2 () {
		return def2;
	}   
	/**
	 * 属性def2的Setter方法.属性名：自定义项2
	 * 创建日期:2020-4-10
	 * @param newDef2 java.lang.String
	 */
	public void setDef2 (java.lang.String newDef2 ) {
	 	this.def2 = newDef2;
	} 	 
	
	/**
	 * 属性 def3的Getter方法.属性名：自定义项3
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef3 () {
		return def3;
	}   
	/**
	 * 属性def3的Setter方法.属性名：自定义项3
	 * 创建日期:2020-4-10
	 * @param newDef3 java.lang.String
	 */
	public void setDef3 (java.lang.String newDef3 ) {
	 	this.def3 = newDef3;
	} 	 
	
	/**
	 * 属性 def4的Getter方法.属性名：自定义项4
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef4 () {
		return def4;
	}   
	/**
	 * 属性def4的Setter方法.属性名：自定义项4
	 * 创建日期:2020-4-10
	 * @param newDef4 java.lang.String
	 */
	public void setDef4 (java.lang.String newDef4 ) {
	 	this.def4 = newDef4;
	} 	 
	
	/**
	 * 属性 def5的Getter方法.属性名：自定义项5
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef5 () {
		return def5;
	}   
	/**
	 * 属性def5的Setter方法.属性名：自定义项5
	 * 创建日期:2020-4-10
	 * @param newDef5 java.lang.String
	 */
	public void setDef5 (java.lang.String newDef5 ) {
	 	this.def5 = newDef5;
	} 	 
	
	/**
	 * 属性 def6的Getter方法.属性名：自定义项6
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef6 () {
		return def6;
	}   
	/**
	 * 属性def6的Setter方法.属性名：自定义项6
	 * 创建日期:2020-4-10
	 * @param newDef6 java.lang.String
	 */
	public void setDef6 (java.lang.String newDef6 ) {
	 	this.def6 = newDef6;
	} 	 
	
	/**
	 * 属性 def7的Getter方法.属性名：自定义项7
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef7 () {
		return def7;
	}   
	/**
	 * 属性def7的Setter方法.属性名：自定义项7
	 * 创建日期:2020-4-10
	 * @param newDef7 java.lang.String
	 */
	public void setDef7 (java.lang.String newDef7 ) {
	 	this.def7 = newDef7;
	} 	 
	
	/**
	 * 属性 def8的Getter方法.属性名：自定义项8
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef8 () {
		return def8;
	}   
	/**
	 * 属性def8的Setter方法.属性名：自定义项8
	 * 创建日期:2020-4-10
	 * @param newDef8 java.lang.String
	 */
	public void setDef8 (java.lang.String newDef8 ) {
	 	this.def8 = newDef8;
	} 	 
	
	/**
	 * 属性 def9的Getter方法.属性名：自定义项9
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef9 () {
		return def9;
	}   
	/**
	 * 属性def9的Setter方法.属性名：自定义项9
	 * 创建日期:2020-4-10
	 * @param newDef9 java.lang.String
	 */
	public void setDef9 (java.lang.String newDef9 ) {
	 	this.def9 = newDef9;
	} 	 
	
	/**
	 * 属性 def10的Getter方法.属性名：自定义项10
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef10 () {
		return def10;
	}   
	/**
	 * 属性def10的Setter方法.属性名：自定义项10
	 * 创建日期:2020-4-10
	 * @param newDef10 java.lang.String
	 */
	public void setDef10 (java.lang.String newDef10 ) {
	 	this.def10 = newDef10;
	} 	 
	
	/**
	 * 属性 def11的Getter方法.属性名：自定义项11
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef11 () {
		return def11;
	}   
	/**
	 * 属性def11的Setter方法.属性名：自定义项11
	 * 创建日期:2020-4-10
	 * @param newDef11 java.lang.String
	 */
	public void setDef11 (java.lang.String newDef11 ) {
	 	this.def11 = newDef11;
	} 	 
	
	/**
	 * 属性 def12的Getter方法.属性名：自定义项12
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef12 () {
		return def12;
	}   
	/**
	 * 属性def12的Setter方法.属性名：自定义项12
	 * 创建日期:2020-4-10
	 * @param newDef12 java.lang.String
	 */
	public void setDef12 (java.lang.String newDef12 ) {
	 	this.def12 = newDef12;
	} 	 
	
	/**
	 * 属性 def13的Getter方法.属性名：自定义项13
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef13 () {
		return def13;
	}   
	/**
	 * 属性def13的Setter方法.属性名：自定义项13
	 * 创建日期:2020-4-10
	 * @param newDef13 java.lang.String
	 */
	public void setDef13 (java.lang.String newDef13 ) {
	 	this.def13 = newDef13;
	} 	 
	
	/**
	 * 属性 def14的Getter方法.属性名：自定义项14
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef14 () {
		return def14;
	}   
	/**
	 * 属性def14的Setter方法.属性名：自定义项14
	 * 创建日期:2020-4-10
	 * @param newDef14 java.lang.String
	 */
	public void setDef14 (java.lang.String newDef14 ) {
	 	this.def14 = newDef14;
	} 	 
	
	/**
	 * 属性 def15的Getter方法.属性名：自定义项15
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef15 () {
		return def15;
	}   
	/**
	 * 属性def15的Setter方法.属性名：自定义项15
	 * 创建日期:2020-4-10
	 * @param newDef15 java.lang.String
	 */
	public void setDef15 (java.lang.String newDef15 ) {
	 	this.def15 = newDef15;
	} 	 
	
	/**
	 * 属性 def16的Getter方法.属性名：自定义项16
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef16 () {
		return def16;
	}   
	/**
	 * 属性def16的Setter方法.属性名：自定义项16
	 * 创建日期:2020-4-10
	 * @param newDef16 java.lang.String
	 */
	public void setDef16 (java.lang.String newDef16 ) {
	 	this.def16 = newDef16;
	} 	 
	
	/**
	 * 属性 def17的Getter方法.属性名：自定义项17
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef17 () {
		return def17;
	}   
	/**
	 * 属性def17的Setter方法.属性名：自定义项17
	 * 创建日期:2020-4-10
	 * @param newDef17 java.lang.String
	 */
	public void setDef17 (java.lang.String newDef17 ) {
	 	this.def17 = newDef17;
	} 	 
	
	/**
	 * 属性 def18的Getter方法.属性名：自定义项18
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef18 () {
		return def18;
	}   
	/**
	 * 属性def18的Setter方法.属性名：自定义项18
	 * 创建日期:2020-4-10
	 * @param newDef18 java.lang.String
	 */
	public void setDef18 (java.lang.String newDef18 ) {
	 	this.def18 = newDef18;
	} 	 
	
	/**
	 * 属性 def19的Getter方法.属性名：自定义项19
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef19 () {
		return def19;
	}   
	/**
	 * 属性def19的Setter方法.属性名：自定义项19
	 * 创建日期:2020-4-10
	 * @param newDef19 java.lang.String
	 */
	public void setDef19 (java.lang.String newDef19 ) {
	 	this.def19 = newDef19;
	} 	 
	
	/**
	 * 属性 def20的Getter方法.属性名：自定义项20
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef20 () {
		return def20;
	}   
	/**
	 * 属性def20的Setter方法.属性名：自定义项20
	 * 创建日期:2020-4-10
	 * @param newDef20 java.lang.String
	 */
	public void setDef20 (java.lang.String newDef20 ) {
	 	this.def20 = newDef20;
	} 	 
	
	/**
	 * 属性 itfconfigitemvo的Getter方法.属性名：ItfConfigItemVO
	 *  创建日期:2020-4-10
	 * @return nc.vo.uapbd.itfconfig.ItfConfigItemVO[]
	 */
	public nc.vo.uapbd.itfconfig.ItfConfigItemVO[] getItfconfigitemvo () {
		return itfconfigitemvo;
	}   
	/**
	 * 属性itfconfigitemvo的Setter方法.属性名：ItfConfigItemVO
	 * 创建日期:2020-4-10
	 * @param newItfconfigitemvo nc.vo.uapbd.itfconfig.ItfConfigItemVO[]
	 */
	public void setItfconfigitemvo (nc.vo.uapbd.itfconfig.ItfConfigItemVO[] newItfconfigitemvo ) {
	 	this.itfconfigitemvo = newItfconfigitemvo;
	} 	 
	
	/**
	 * 属性 dr的Getter方法.属性名：dr
	 *  创建日期:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return dr;
	}   
	/**
	 * 属性dr的Setter方法.属性名：dr
	 * 创建日期:2020-4-10
	 * @param newDr java.lang.Integer
	 */
	public void setDr (java.lang.Integer newDr ) {
	 	this.dr = newDr;
	} 	 
	
	/**
	 * 属性 ts的Getter方法.属性名：ts
	 *  创建日期:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return ts;
	}   
	/**
	 * 属性ts的Setter方法.属性名：ts
	 * 创建日期:2020-4-10
	 * @param newTs nc.vo.pub.lang.UFDateTime
	 */
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
	 	this.ts = newTs;
	} 	 
	
	
	/**
	  * <p>取得父VO主键字段.
	  * <p>
	  * 创建日期:2020-4-10
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>取得表主键.
	  * <p>
	  * 创建日期:2020-4-10
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_itfconfigbill";
	}
    
	/**
	 * <p>返回表名称
	 * <p>
	 * 创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uapbd_itfconfigbill";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uapbd_itfconfigbill";
	}    
    
    /**
	  * 按照默认方式创建构造子.
	  *
	  * 创建日期:2020-4-10
	  */
     public ItfConfigBillVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.uapbd.itfconfig.ItfConfigBillVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("uapbd.ItfConfigBillVO");
		
   	}
     
}