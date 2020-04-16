package nc.vo.uapbd.itfconfig;

import nc.vo.pub.*;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�������������Ϣ
 * </p>
 *  ��������:2020-4-10
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
	 * ���� pk_itfconfigbill��Getter����.��������pk_itfconfigbill
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_itfconfigbill () {
		return pk_itfconfigbill;
	}   
	/**
	 * ����pk_itfconfigbill��Setter����.��������pk_itfconfigbill
	 * ��������:2020-4-10
	 * @param newPk_itfconfigbill java.lang.String
	 */
	public void setPk_itfconfigbill (java.lang.String newPk_itfconfigbill ) {
	 	this.pk_itfconfigbill = newPk_itfconfigbill;
	} 	 
	
	/**
	 * ���� code��Getter����.������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getCode () {
		return code;
	}   
	/**
	 * ����code��Setter����.������������
	 * ��������:2020-4-10
	 * @param newCode java.lang.String
	 */
	public void setCode (java.lang.String newCode ) {
	 	this.code = newCode;
	} 	 
	
	/**
	 * ���� name��Getter����.������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getName () {
		return name;
	}   
	/**
	 * ����name��Setter����.������������
	 * ��������:2020-4-10
	 * @param newName java.lang.String
	 */
	public void setName (java.lang.String newName ) {
	 	this.name = newName;
	} 	 
	
	/**
	 * ���� rowno��Getter����.���������к�
	 *  ��������:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getRowno () {
		return rowno;
	}   
	/**
	 * ����rowno��Setter����.���������к�
	 * ��������:2020-4-10
	 * @param newRowno java.lang.Integer
	 */
	public void setRowno (java.lang.Integer newRowno ) {
	 	this.rowno = newRowno;
	} 	 
	
	/**
	 * ���� remark��Getter����.����������ע
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getRemark () {
		return remark;
	}   
	/**
	 * ����remark��Setter����.����������ע
	 * ��������:2020-4-10
	 * @param newRemark java.lang.String
	 */
	public void setRemark (java.lang.String newRemark ) {
	 	this.remark = newRemark;
	} 	 
	
	/**
	 * ���� busibilltype��Getter����.��������ҵ�񵥾�����
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusibilltype () {
		return busibilltype;
	}   
	/**
	 * ����busibilltype��Setter����.��������ҵ�񵥾�����
	 * ��������:2020-4-10
	 * @param newBusibilltype java.lang.String
	 */
	public void setBusibilltype (java.lang.String newBusibilltype ) {
	 	this.busibilltype = newBusibilltype;
	} 	 
	
	/**
	 * ���� busibilltypecode��Getter����.��������ҵ�񵥾����ͱ���
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusibilltypecode () {
		return busibilltypecode;
	}   
	/**
	 * ����busibilltypecode��Setter����.��������ҵ�񵥾����ͱ���
	 * ��������:2020-4-10
	 * @param newBusibilltypecode java.lang.String
	 */
	public void setBusibilltypecode (java.lang.String newBusibilltypecode ) {
	 	this.busibilltypecode = newBusibilltypecode;
	} 	 
	
	/**
	 * ���� nccbilltype��Getter����.��������NCC��������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccbilltype () {
		return nccbilltype;
	}   
	/**
	 * ����nccbilltype��Setter����.��������NCC��������
	 * ��������:2020-4-10
	 * @param newNccbilltype java.lang.String
	 */
	public void setNccbilltype (java.lang.String newNccbilltype ) {
	 	this.nccbilltype = newNccbilltype;
	} 	 
	
	/**
	 * ���� nccbilltypecode��Getter����.��������NCC�������ͱ���
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccbilltypecode () {
		return nccbilltypecode;
	}   
	/**
	 * ����nccbilltypecode��Setter����.��������NCC�������ͱ���
	 * ��������:2020-4-10
	 * @param newNccbilltypecode java.lang.String
	 */
	public void setNccbilltypecode (java.lang.String newNccbilltypecode ) {
	 	this.nccbilltypecode = newNccbilltypecode;
	} 	 
	
	/**
	 * ���� pk_group��Getter����.������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return pk_group;
	}   
	/**
	 * ����pk_group��Setter����.������������
	 * ��������:2020-4-10
	 * @param newPk_group java.lang.String
	 */
	public void setPk_group (java.lang.String newPk_group ) {
	 	this.pk_group = newPk_group;
	} 	 
	
	/**
	 * ���� pk_org��Getter����.����������֯
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return pk_org;
	}   
	/**
	 * ����pk_org��Setter����.����������֯
	 * ��������:2020-4-10
	 * @param newPk_org java.lang.String
	 */
	public void setPk_org (java.lang.String newPk_org ) {
	 	this.pk_org = newPk_org;
	} 	 
	
	/**
	 * ���� pk_org_v��Getter����.����������֯�汾
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v () {
		return pk_org_v;
	}   
	/**
	 * ����pk_org_v��Setter����.����������֯�汾
	 * ��������:2020-4-10
	 * @param newPk_org_v java.lang.String
	 */
	public void setPk_org_v (java.lang.String newPk_org_v ) {
	 	this.pk_org_v = newPk_org_v;
	} 	 
	
	/**
	 * ���� creator��Getter����.��������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getCreator () {
		return creator;
	}   
	/**
	 * ����creator��Setter����.��������������
	 * ��������:2020-4-10
	 * @param newCreator java.lang.String
	 */
	public void setCreator (java.lang.String newCreator ) {
	 	this.creator = newCreator;
	} 	 
	
	/**
	 * ���� creationtime��Getter����.������������ʱ��
	 *  ��������:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime () {
		return creationtime;
	}   
	/**
	 * ����creationtime��Setter����.������������ʱ��
	 * ��������:2020-4-10
	 * @param newCreationtime nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime (nc.vo.pub.lang.UFDateTime newCreationtime ) {
	 	this.creationtime = newCreationtime;
	} 	 
	
	/**
	 * ���� modifier��Getter����.���������޸���
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getModifier () {
		return modifier;
	}   
	/**
	 * ����modifier��Setter����.���������޸���
	 * ��������:2020-4-10
	 * @param newModifier java.lang.String
	 */
	public void setModifier (java.lang.String newModifier ) {
	 	this.modifier = newModifier;
	} 	 
	
	/**
	 * ���� modifiedtime��Getter����.���������޸�ʱ��
	 *  ��������:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime () {
		return modifiedtime;
	}   
	/**
	 * ����modifiedtime��Setter����.���������޸�ʱ��
	 * ��������:2020-4-10
	 * @param newModifiedtime nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime (nc.vo.pub.lang.UFDateTime newModifiedtime ) {
	 	this.modifiedtime = newModifiedtime;
	} 	 
	
	/**
	 * ���� maketime��Getter����.���������Ƶ�ʱ��
	 *  ��������:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getMaketime () {
		return maketime;
	}   
	/**
	 * ����maketime��Setter����.���������Ƶ�ʱ��
	 * ��������:2020-4-10
	 * @param newMaketime nc.vo.pub.lang.UFDateTime
	 */
	public void setMaketime (nc.vo.pub.lang.UFDateTime newMaketime ) {
	 	this.maketime = newMaketime;
	} 	 
	
	/**
	 * ���� lastmaketime��Getter����.������������޸�ʱ��
	 *  ��������:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getLastmaketime () {
		return lastmaketime;
	}   
	/**
	 * ����lastmaketime��Setter����.������������޸�ʱ��
	 * ��������:2020-4-10
	 * @param newLastmaketime nc.vo.pub.lang.UFDateTime
	 */
	public void setLastmaketime (nc.vo.pub.lang.UFDateTime newLastmaketime ) {
	 	this.lastmaketime = newLastmaketime;
	} 	 
	
	/**
	 * ���� billno��Getter����.�����������ݺ�
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBillno () {
		return billno;
	}   
	/**
	 * ����billno��Setter����.�����������ݺ�
	 * ��������:2020-4-10
	 * @param newBillno java.lang.String
	 */
	public void setBillno (java.lang.String newBillno ) {
	 	this.billno = newBillno;
	} 	 
	
	/**
	 * ���� busitype��Getter����.��������ҵ������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusitype () {
		return busitype;
	}   
	/**
	 * ����busitype��Setter����.��������ҵ������
	 * ��������:2020-4-10
	 * @param newBusitype java.lang.String
	 */
	public void setBusitype (java.lang.String newBusitype ) {
	 	this.busitype = newBusitype;
	} 	 
	
	/**
	 * ���� billmaker��Getter����.���������Ƶ���
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBillmaker () {
		return billmaker;
	}   
	/**
	 * ����billmaker��Setter����.���������Ƶ���
	 * ��������:2020-4-10
	 * @param newBillmaker java.lang.String
	 */
	public void setBillmaker (java.lang.String newBillmaker ) {
	 	this.billmaker = newBillmaker;
	} 	 
	
	/**
	 * ���� approver��Getter����.��������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getApprover () {
		return approver;
	}   
	/**
	 * ����approver��Setter����.��������������
	 * ��������:2020-4-10
	 * @param newApprover java.lang.String
	 */
	public void setApprover (java.lang.String newApprover ) {
	 	this.approver = newApprover;
	} 	 
	
	/**
	 * ���� approvestatus��Getter����.������������״̬
	 *  ��������:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getApprovestatus () {
		return approvestatus;
	}   
	/**
	 * ����approvestatus��Setter����.������������״̬
	 * ��������:2020-4-10
	 * @param newApprovestatus java.lang.Integer
	 */
	public void setApprovestatus (java.lang.Integer newApprovestatus ) {
	 	this.approvestatus = newApprovestatus;
	} 	 
	
	/**
	 * ���� approvenote��Getter����.����������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getApprovenote () {
		return approvenote;
	}   
	/**
	 * ����approvenote��Setter����.����������������
	 * ��������:2020-4-10
	 * @param newApprovenote java.lang.String
	 */
	public void setApprovenote (java.lang.String newApprovenote ) {
	 	this.approvenote = newApprovenote;
	} 	 
	
	/**
	 * ���� approvedate��Getter����.������������ʱ��
	 *  ��������:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getApprovedate () {
		return approvedate;
	}   
	/**
	 * ����approvedate��Setter����.������������ʱ��
	 * ��������:2020-4-10
	 * @param newApprovedate nc.vo.pub.lang.UFDateTime
	 */
	public void setApprovedate (nc.vo.pub.lang.UFDateTime newApprovedate ) {
	 	this.approvedate = newApprovedate;
	} 	 
	
	/**
	 * ���� transtype��Getter����.����������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTranstype () {
		return transtype;
	}   
	/**
	 * ����transtype��Setter����.����������������
	 * ��������:2020-4-10
	 * @param newTranstype java.lang.String
	 */
	public void setTranstype (java.lang.String newTranstype ) {
	 	this.transtype = newTranstype;
	} 	 
	
	/**
	 * ���� billtype��Getter����.����������������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBilltype () {
		return billtype;
	}   
	/**
	 * ����billtype��Setter����.����������������
	 * ��������:2020-4-10
	 * @param newBilltype java.lang.String
	 */
	public void setBilltype (java.lang.String newBilltype ) {
	 	this.billtype = newBilltype;
	} 	 
	
	/**
	 * ���� transtypepk��Getter����.����������������pk
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTranstypepk () {
		return transtypepk;
	}   
	/**
	 * ����transtypepk��Setter����.����������������pk
	 * ��������:2020-4-10
	 * @param newTranstypepk java.lang.String
	 */
	public void setTranstypepk (java.lang.String newTranstypepk ) {
	 	this.transtypepk = newTranstypepk;
	} 	 
	
	/**
	 * ���� srcbilltype��Getter����.����������Դ��������
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getSrcbilltype () {
		return srcbilltype;
	}   
	/**
	 * ����srcbilltype��Setter����.����������Դ��������
	 * ��������:2020-4-10
	 * @param newSrcbilltype java.lang.String
	 */
	public void setSrcbilltype (java.lang.String newSrcbilltype ) {
	 	this.srcbilltype = newSrcbilltype;
	} 	 
	
	/**
	 * ���� srcbillid��Getter����.����������Դ����id
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getSrcbillid () {
		return srcbillid;
	}   
	/**
	 * ����srcbillid��Setter����.����������Դ����id
	 * ��������:2020-4-10
	 * @param newSrcbillid java.lang.String
	 */
	public void setSrcbillid (java.lang.String newSrcbillid ) {
	 	this.srcbillid = newSrcbillid;
	} 	 
	
	/**
	 * ���� emendenum��Getter����.���������޶�ö��
	 *  ��������:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getEmendenum () {
		return emendenum;
	}   
	/**
	 * ����emendenum��Setter����.���������޶�ö��
	 * ��������:2020-4-10
	 * @param newEmendenum java.lang.Integer
	 */
	public void setEmendenum (java.lang.Integer newEmendenum ) {
	 	this.emendenum = newEmendenum;
	} 	 
	
	/**
	 * ���� billversionpk��Getter����.�����������ݰ汾pk
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBillversionpk () {
		return billversionpk;
	}   
	/**
	 * ����billversionpk��Setter����.�����������ݰ汾pk
	 * ��������:2020-4-10
	 * @param newBillversionpk java.lang.String
	 */
	public void setBillversionpk (java.lang.String newBillversionpk ) {
	 	this.billversionpk = newBillversionpk;
	} 	 
	
	/**
	 * ���� def1��Getter����.���������Զ�����1
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef1 () {
		return def1;
	}   
	/**
	 * ����def1��Setter����.���������Զ�����1
	 * ��������:2020-4-10
	 * @param newDef1 java.lang.String
	 */
	public void setDef1 (java.lang.String newDef1 ) {
	 	this.def1 = newDef1;
	} 	 
	
	/**
	 * ���� def2��Getter����.���������Զ�����2
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef2 () {
		return def2;
	}   
	/**
	 * ����def2��Setter����.���������Զ�����2
	 * ��������:2020-4-10
	 * @param newDef2 java.lang.String
	 */
	public void setDef2 (java.lang.String newDef2 ) {
	 	this.def2 = newDef2;
	} 	 
	
	/**
	 * ���� def3��Getter����.���������Զ�����3
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef3 () {
		return def3;
	}   
	/**
	 * ����def3��Setter����.���������Զ�����3
	 * ��������:2020-4-10
	 * @param newDef3 java.lang.String
	 */
	public void setDef3 (java.lang.String newDef3 ) {
	 	this.def3 = newDef3;
	} 	 
	
	/**
	 * ���� def4��Getter����.���������Զ�����4
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef4 () {
		return def4;
	}   
	/**
	 * ����def4��Setter����.���������Զ�����4
	 * ��������:2020-4-10
	 * @param newDef4 java.lang.String
	 */
	public void setDef4 (java.lang.String newDef4 ) {
	 	this.def4 = newDef4;
	} 	 
	
	/**
	 * ���� def5��Getter����.���������Զ�����5
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef5 () {
		return def5;
	}   
	/**
	 * ����def5��Setter����.���������Զ�����5
	 * ��������:2020-4-10
	 * @param newDef5 java.lang.String
	 */
	public void setDef5 (java.lang.String newDef5 ) {
	 	this.def5 = newDef5;
	} 	 
	
	/**
	 * ���� def6��Getter����.���������Զ�����6
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef6 () {
		return def6;
	}   
	/**
	 * ����def6��Setter����.���������Զ�����6
	 * ��������:2020-4-10
	 * @param newDef6 java.lang.String
	 */
	public void setDef6 (java.lang.String newDef6 ) {
	 	this.def6 = newDef6;
	} 	 
	
	/**
	 * ���� def7��Getter����.���������Զ�����7
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef7 () {
		return def7;
	}   
	/**
	 * ����def7��Setter����.���������Զ�����7
	 * ��������:2020-4-10
	 * @param newDef7 java.lang.String
	 */
	public void setDef7 (java.lang.String newDef7 ) {
	 	this.def7 = newDef7;
	} 	 
	
	/**
	 * ���� def8��Getter����.���������Զ�����8
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef8 () {
		return def8;
	}   
	/**
	 * ����def8��Setter����.���������Զ�����8
	 * ��������:2020-4-10
	 * @param newDef8 java.lang.String
	 */
	public void setDef8 (java.lang.String newDef8 ) {
	 	this.def8 = newDef8;
	} 	 
	
	/**
	 * ���� def9��Getter����.���������Զ�����9
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef9 () {
		return def9;
	}   
	/**
	 * ����def9��Setter����.���������Զ�����9
	 * ��������:2020-4-10
	 * @param newDef9 java.lang.String
	 */
	public void setDef9 (java.lang.String newDef9 ) {
	 	this.def9 = newDef9;
	} 	 
	
	/**
	 * ���� def10��Getter����.���������Զ�����10
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef10 () {
		return def10;
	}   
	/**
	 * ����def10��Setter����.���������Զ�����10
	 * ��������:2020-4-10
	 * @param newDef10 java.lang.String
	 */
	public void setDef10 (java.lang.String newDef10 ) {
	 	this.def10 = newDef10;
	} 	 
	
	/**
	 * ���� def11��Getter����.���������Զ�����11
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef11 () {
		return def11;
	}   
	/**
	 * ����def11��Setter����.���������Զ�����11
	 * ��������:2020-4-10
	 * @param newDef11 java.lang.String
	 */
	public void setDef11 (java.lang.String newDef11 ) {
	 	this.def11 = newDef11;
	} 	 
	
	/**
	 * ���� def12��Getter����.���������Զ�����12
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef12 () {
		return def12;
	}   
	/**
	 * ����def12��Setter����.���������Զ�����12
	 * ��������:2020-4-10
	 * @param newDef12 java.lang.String
	 */
	public void setDef12 (java.lang.String newDef12 ) {
	 	this.def12 = newDef12;
	} 	 
	
	/**
	 * ���� def13��Getter����.���������Զ�����13
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef13 () {
		return def13;
	}   
	/**
	 * ����def13��Setter����.���������Զ�����13
	 * ��������:2020-4-10
	 * @param newDef13 java.lang.String
	 */
	public void setDef13 (java.lang.String newDef13 ) {
	 	this.def13 = newDef13;
	} 	 
	
	/**
	 * ���� def14��Getter����.���������Զ�����14
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef14 () {
		return def14;
	}   
	/**
	 * ����def14��Setter����.���������Զ�����14
	 * ��������:2020-4-10
	 * @param newDef14 java.lang.String
	 */
	public void setDef14 (java.lang.String newDef14 ) {
	 	this.def14 = newDef14;
	} 	 
	
	/**
	 * ���� def15��Getter����.���������Զ�����15
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef15 () {
		return def15;
	}   
	/**
	 * ����def15��Setter����.���������Զ�����15
	 * ��������:2020-4-10
	 * @param newDef15 java.lang.String
	 */
	public void setDef15 (java.lang.String newDef15 ) {
	 	this.def15 = newDef15;
	} 	 
	
	/**
	 * ���� def16��Getter����.���������Զ�����16
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef16 () {
		return def16;
	}   
	/**
	 * ����def16��Setter����.���������Զ�����16
	 * ��������:2020-4-10
	 * @param newDef16 java.lang.String
	 */
	public void setDef16 (java.lang.String newDef16 ) {
	 	this.def16 = newDef16;
	} 	 
	
	/**
	 * ���� def17��Getter����.���������Զ�����17
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef17 () {
		return def17;
	}   
	/**
	 * ����def17��Setter����.���������Զ�����17
	 * ��������:2020-4-10
	 * @param newDef17 java.lang.String
	 */
	public void setDef17 (java.lang.String newDef17 ) {
	 	this.def17 = newDef17;
	} 	 
	
	/**
	 * ���� def18��Getter����.���������Զ�����18
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef18 () {
		return def18;
	}   
	/**
	 * ����def18��Setter����.���������Զ�����18
	 * ��������:2020-4-10
	 * @param newDef18 java.lang.String
	 */
	public void setDef18 (java.lang.String newDef18 ) {
	 	this.def18 = newDef18;
	} 	 
	
	/**
	 * ���� def19��Getter����.���������Զ�����19
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef19 () {
		return def19;
	}   
	/**
	 * ����def19��Setter����.���������Զ�����19
	 * ��������:2020-4-10
	 * @param newDef19 java.lang.String
	 */
	public void setDef19 (java.lang.String newDef19 ) {
	 	this.def19 = newDef19;
	} 	 
	
	/**
	 * ���� def20��Getter����.���������Զ�����20
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDef20 () {
		return def20;
	}   
	/**
	 * ����def20��Setter����.���������Զ�����20
	 * ��������:2020-4-10
	 * @param newDef20 java.lang.String
	 */
	public void setDef20 (java.lang.String newDef20 ) {
	 	this.def20 = newDef20;
	} 	 
	
	/**
	 * ���� itfconfigitemvo��Getter����.��������ItfConfigItemVO
	 *  ��������:2020-4-10
	 * @return nc.vo.uapbd.itfconfig.ItfConfigItemVO[]
	 */
	public nc.vo.uapbd.itfconfig.ItfConfigItemVO[] getItfconfigitemvo () {
		return itfconfigitemvo;
	}   
	/**
	 * ����itfconfigitemvo��Setter����.��������ItfConfigItemVO
	 * ��������:2020-4-10
	 * @param newItfconfigitemvo nc.vo.uapbd.itfconfig.ItfConfigItemVO[]
	 */
	public void setItfconfigitemvo (nc.vo.uapbd.itfconfig.ItfConfigItemVO[] newItfconfigitemvo ) {
	 	this.itfconfigitemvo = newItfconfigitemvo;
	} 	 
	
	/**
	 * ���� dr��Getter����.��������dr
	 *  ��������:2020-4-10
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return dr;
	}   
	/**
	 * ����dr��Setter����.��������dr
	 * ��������:2020-4-10
	 * @param newDr java.lang.Integer
	 */
	public void setDr (java.lang.Integer newDr ) {
	 	this.dr = newDr;
	} 	 
	
	/**
	 * ���� ts��Getter����.��������ts
	 *  ��������:2020-4-10
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return ts;
	}   
	/**
	 * ����ts��Setter����.��������ts
	 * ��������:2020-4-10
	 * @param newTs nc.vo.pub.lang.UFDateTime
	 */
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
	 	this.ts = newTs;
	} 	 
	
	
	/**
	  * <p>ȡ�ø�VO�����ֶ�.
	  * <p>
	  * ��������:2020-4-10
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>ȡ�ñ�����.
	  * <p>
	  * ��������:2020-4-10
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_itfconfigbill";
	}
    
	/**
	 * <p>���ر�����
	 * <p>
	 * ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uapbd_itfconfigbill";
	}    
	
	/**
	 * <p>���ر�����.
	 * <p>
	 * ��������:2020-4-10
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uapbd_itfconfigbill";
	}    
    
    /**
	  * ����Ĭ�Ϸ�ʽ����������.
	  *
	  * ��������:2020-4-10
	  */
     public ItfConfigBillVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.uapbd.itfconfig.ItfConfigBillVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("uapbd.ItfConfigBillVO");
		
   	}
     
}