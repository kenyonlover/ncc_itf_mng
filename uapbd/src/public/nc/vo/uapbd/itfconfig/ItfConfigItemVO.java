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
	 * 属性 pk_itfconfigbill的Getter方法.属性名：parentPK
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_itfconfigbill () {
		return pk_itfconfigbill;
	}   
	/**
	 * 属性pk_itfconfigbill的Setter方法.属性名：parentPK
	 * 创建日期:2020-4-10
	 * @param newPk_itfconfigbill java.lang.String
	 */
	public void setPk_itfconfigbill (java.lang.String newPk_itfconfigbill ) {
	 	this.pk_itfconfigbill = newPk_itfconfigbill;
	} 	 
	
	/**
	 * 属性 pk_itfconfigitem的Getter方法.属性名：pk_itfconfigitem
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getPk_itfconfigitem () {
		return pk_itfconfigitem;
	}   
	/**
	 * 属性pk_itfconfigitem的Setter方法.属性名：pk_itfconfigitem
	 * 创建日期:2020-4-10
	 * @param newPk_itfconfigitem java.lang.String
	 */
	public void setPk_itfconfigitem (java.lang.String newPk_itfconfigitem ) {
	 	this.pk_itfconfigitem = newPk_itfconfigitem;
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
	 * 属性 busifieldcode的Getter方法.属性名：业务字段编码
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusifieldcode () {
		return busifieldcode;
	}   
	/**
	 * 属性busifieldcode的Setter方法.属性名：业务字段编码
	 * 创建日期:2020-4-10
	 * @param newBusifieldcode java.lang.String
	 */
	public void setBusifieldcode (java.lang.String newBusifieldcode ) {
	 	this.busifieldcode = newBusifieldcode;
	} 	 
	
	/**
	 * 属性 busifieldname的Getter方法.属性名：业务字段名称
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getBusifieldname () {
		return busifieldname;
	}   
	/**
	 * 属性busifieldname的Setter方法.属性名：业务字段名称
	 * 创建日期:2020-4-10
	 * @param newBusifieldname java.lang.String
	 */
	public void setBusifieldname (java.lang.String newBusifieldname ) {
	 	this.busifieldname = newBusifieldname;
	} 	 
	
	/**
	 * 属性 fieldposition的Getter方法.属性名：字段位置
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getFieldposition () {
		return fieldposition;
	}   
	/**
	 * 属性fieldposition的Setter方法.属性名：字段位置
	 * 创建日期:2020-4-10
	 * @param newFieldposition java.lang.String
	 */
	public void setFieldposition (java.lang.String newFieldposition ) {
	 	this.fieldposition = newFieldposition;
	} 	 
	
	/**
	 * 属性 nccfieldcode的Getter方法.属性名：NCC字段编码
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccfieldcode () {
		return nccfieldcode;
	}   
	/**
	 * 属性nccfieldcode的Setter方法.属性名：NCC字段编码
	 * 创建日期:2020-4-10
	 * @param newNccfieldcode java.lang.String
	 */
	public void setNccfieldcode (java.lang.String newNccfieldcode ) {
	 	this.nccfieldcode = newNccfieldcode;
	} 	 
	
	/**
	 * 属性 nccfieldname的Getter方法.属性名：NCC字段名称
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getNccfieldname () {
		return nccfieldname;
	}   
	/**
	 * 属性nccfieldname的Setter方法.属性名：NCC字段名称
	 * 创建日期:2020-4-10
	 * @param newNccfieldname java.lang.String
	 */
	public void setNccfieldname (java.lang.String newNccfieldname ) {
	 	this.nccfieldname = newNccfieldname;
	} 	 
	
	/**
	 * 属性 fieldsign的Getter方法.属性名：字段标记
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getFieldsign () {
		return fieldsign;
	}   
	/**
	 * 属性fieldsign的Setter方法.属性名：字段标记
	 * 创建日期:2020-4-10
	 * @param newFieldsign java.lang.String
	 */
	public void setFieldsign (java.lang.String newFieldsign ) {
	 	this.fieldsign = newFieldsign;
	} 	 
	
	/**
	 * 属性 isdefault的Getter方法.属性名：是否取默认值
	 *  创建日期:2020-4-10
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getIsdefault () {
		return isdefault;
	}   
	/**
	 * 属性isdefault的Setter方法.属性名：是否取默认值
	 * 创建日期:2020-4-10
	 * @param newIsdefault nc.vo.pub.lang.UFBoolean
	 */
	public void setIsdefault (nc.vo.pub.lang.UFBoolean newIsdefault ) {
	 	this.isdefault = newIsdefault;
	} 	 
	
	/**
	 * 属性 defaultvalue的Getter方法.属性名：默认值
	 *  创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getDefaultvalue () {
		return defaultvalue;
	}   
	/**
	 * 属性defaultvalue的Setter方法.属性名：默认值
	 * 创建日期:2020-4-10
	 * @param newDefaultvalue java.lang.String
	 */
	public void setDefaultvalue (java.lang.String newDefaultvalue ) {
	 	this.defaultvalue = newDefaultvalue;
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
		return "pk_itfconfigbill";
	}   
    
	/**
	  * <p>取得表主键.
	  * <p>
	  * 创建日期:2020-4-10
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_itfconfigitem";
	}
    
	/**
	 * <p>返回表名称
	 * <p>
	 * 创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "uapbd_itfconfigitem";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:2020-4-10
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "uapbd_itfconfigitem";
	}    
    
    /**
	  * 按照默认方式创建构造子.
	  *
	  * 创建日期:2020-4-10
	  */
     public ItfConfigItemVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.uapbd.itfconfig.ItfConfigItemVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("uapbd.ItfConfigItemVO");
		
   	}
     
}