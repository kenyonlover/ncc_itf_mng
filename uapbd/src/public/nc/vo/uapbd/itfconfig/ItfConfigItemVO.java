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
public class ItfConfigItemVO extends nc.vo.pub.SuperVO{
	
    private java.lang.String pk_itfconfigbill;
    private java.lang.String pk_itfconfigitem;
    private java.lang.Integer rowno;
    private java.lang.String pk_group;
    private java.lang.String pk_org;
    private java.lang.String pk_org_v;
    private java.lang.String busifieldcode;
    private java.lang.String busifieldname;
    private java.lang.String fieldposition;
    private java.lang.String nccfieldcode;
    private java.lang.String nccfieldname;
    private java.lang.String fieldsign;
    private nc.vo.pub.lang.UFBoolean isdefault;
    private java.lang.String defaultvalue;
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
	
	
    public static final String PK_ITFCONFIGBILL = "pk_itfconfigbill";
    public static final String PK_ITFCONFIGITEM = "pk_itfconfigitem";
    public static final String ROWNO = "rowno";
    public static final String PK_GROUP = "pk_group";
    public static final String PK_ORG = "pk_org";
    public static final String PK_ORG_V = "pk_org_v";
    public static final String BUSIFIELDCODE = "busifieldcode";
    public static final String BUSIFIELDNAME = "busifieldname";
    public static final String FIELDPOSITION = "fieldposition";
    public static final String NCCFIELDCODE = "nccfieldcode";
    public static final String NCCFIELDNAME = "nccfieldname";
    public static final String FIELDSIGN = "fieldsign";
    public static final String ISDEFAULT = "isdefault";
    public static final String DEFAULTVALUE = "defaultvalue";
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
	 * ���� pk_itfconfigbill��Getter����.��������parentPK
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_itfconfigbill () {
		return pk_itfconfigbill;
	}   
	/**
	 * ����pk_itfconfigbill��Setter����.��������parentPK
	 * ��������:2020-4-10
	 * @param newPk_itfconfigbill java.lang.String
	 */
	public void setPk_itfconfigbill (java.lang.String newPk_itfconfigbill ) {
	 	this.pk_itfconfigbill = newPk_itfconfigbill;
	} 	 
	
	/**
	 * ���� pk_itfconfigitem��Getter����.��������pk_itfconfigitem
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_itfconfigitem () {
		return pk_itfconfigitem;
	}   
	/**
	 * ����pk_itfconfigitem��Setter����.��������pk_itfconfigitem
	 * ��������:2020-4-10
	 * @param newPk_itfconfigitem java.lang.String
	 */
	public void setPk_itfconfigitem (java.lang.String newPk_itfconfigitem ) {
	 	this.pk_itfconfigitem = newPk_itfconfigitem;
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
	 * ���� busifieldcode��Getter����.��������ҵ���ֶα���
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusifieldcode () {
		return busifieldcode;
	}   
	/**
	 * ����busifieldcode��Setter����.��������ҵ���ֶα���
	 * ��������:2020-4-10
	 * @param newBusifieldcode java.lang.String
	 */
	public void setBusifieldcode (java.lang.String newBusifieldcode ) {
	 	this.busifieldcode = newBusifieldcode;
	} 	 
	
	/**
	 * ���� busifieldname��Getter����.��������ҵ���ֶ�����
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusifieldname () {
		return busifieldname;
	}   
	/**
	 * ����busifieldname��Setter����.��������ҵ���ֶ�����
	 * ��������:2020-4-10
	 * @param newBusifieldname java.lang.String
	 */
	public void setBusifieldname (java.lang.String newBusifieldname ) {
	 	this.busifieldname = newBusifieldname;
	} 	 
	
	/**
	 * ���� fieldposition��Getter����.���������ֶ�λ��
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getFieldposition () {
		return fieldposition;
	}   
	/**
	 * ����fieldposition��Setter����.���������ֶ�λ��
	 * ��������:2020-4-10
	 * @param newFieldposition java.lang.String
	 */
	public void setFieldposition (java.lang.String newFieldposition ) {
	 	this.fieldposition = newFieldposition;
	} 	 
	
	/**
	 * ���� nccfieldcode��Getter����.��������NCC�ֶα���
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccfieldcode () {
		return nccfieldcode;
	}   
	/**
	 * ����nccfieldcode��Setter����.��������NCC�ֶα���
	 * ��������:2020-4-10
	 * @param newNccfieldcode java.lang.String
	 */
	public void setNccfieldcode (java.lang.String newNccfieldcode ) {
	 	this.nccfieldcode = newNccfieldcode;
	} 	 
	
	/**
	 * ���� nccfieldname��Getter����.��������NCC�ֶ�����
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccfieldname () {
		return nccfieldname;
	}   
	/**
	 * ����nccfieldname��Setter����.��������NCC�ֶ�����
	 * ��������:2020-4-10
	 * @param newNccfieldname java.lang.String
	 */
	public void setNccfieldname (java.lang.String newNccfieldname ) {
	 	this.nccfieldname = newNccfieldname;
	} 	 
	
	/**
	 * ���� fieldsign��Getter����.���������ֶα��
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getFieldsign () {
		return fieldsign;
	}   
	/**
	 * ����fieldsign��Setter����.���������ֶα��
	 * ��������:2020-4-10
	 * @param newFieldsign java.lang.String
	 */
	public void setFieldsign (java.lang.String newFieldsign ) {
	 	this.fieldsign = newFieldsign;
	} 	 
	
	/**
	 * ���� isdefault��Getter����.���������Ƿ�ȡĬ��ֵ
	 *  ��������:2020-4-10
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getIsdefault () {
		return isdefault;
	}   
	/**
	 * ����isdefault��Setter����.���������Ƿ�ȡĬ��ֵ
	 * ��������:2020-4-10
	 * @param newIsdefault nc.vo.pub.lang.UFBoolean
	 */
	public void setIsdefault (nc.vo.pub.lang.UFBoolean newIsdefault ) {
	 	this.isdefault = newIsdefault;
	} 	 
	
	/**
	 * ���� defaultvalue��Getter����.��������Ĭ��ֵ
	 *  ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDefaultvalue () {
		return defaultvalue;
	}   
	/**
	 * ����defaultvalue��Setter����.��������Ĭ��ֵ
	 * ��������:2020-4-10
	 * @param newDefaultvalue java.lang.String
	 */
	public void setDefaultvalue (java.lang.String newDefaultvalue ) {
	 	this.defaultvalue = newDefaultvalue;
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
		return "pk_itfconfigbill";
	}   
    
	/**
	  * <p>ȡ�ñ�����.
	  * <p>
	  * ��������:2020-4-10
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_itfconfigitem";
	}
    
	/**
	 * <p>���ر�����
	 * <p>
	 * ��������:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uapbd_itfconfigitem";
	}    
	
	/**
	 * <p>���ر�����.
	 * <p>
	 * ��������:2020-4-10
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uapbd_itfconfigitem";
	}    
    
    /**
	  * ����Ĭ�Ϸ�ʽ����������.
	  *
	  * ��������:2020-4-10
	  */
     public ItfConfigItemVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.uapbd.itfconfig.ItfConfigItemVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("uapbd.ItfConfigItemVO");
		
   	}
     
}