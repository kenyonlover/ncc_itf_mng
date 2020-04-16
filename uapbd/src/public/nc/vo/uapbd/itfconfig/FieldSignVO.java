package nc.vo.uapbd.itfconfig;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>此处简要描述此枚举的功能 </b>
 * <p>
 *   此处添加该枚举的描述信息
 * </p>
 *  创建日期:2020-4-10
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class FieldSignVO extends MDEnum{
	public FieldSignVO(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final FieldSignVO 单据主键 = MDEnum.valueOf(FieldSignVO.class, String.valueOf("BILLID"));
	
	
	public static final FieldSignVO 单据号 = MDEnum.valueOf(FieldSignVO.class, String.valueOf("BILLNO"));
	
	
	public static final FieldSignVO 单据类型 = MDEnum.valueOf(FieldSignVO.class, String.valueOf("BILLTYPE"));
	
	
	public static final FieldSignVO 公司 = MDEnum.valueOf(FieldSignVO.class, String.valueOf("CORP"));
	

}