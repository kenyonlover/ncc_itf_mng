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
public class FieldPositionVO extends MDEnum{
	public FieldPositionVO(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final FieldPositionVO 表头 = MDEnum.valueOf(FieldPositionVO.class, String.valueOf("HEAD"));
	
	
	public static final FieldPositionVO 表体 = MDEnum.valueOf(FieldPositionVO.class, String.valueOf("BODY"));
	

}