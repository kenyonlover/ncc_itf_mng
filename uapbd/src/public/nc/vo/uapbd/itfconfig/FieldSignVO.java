package nc.vo.uapbd.itfconfig;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 *   �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 *  ��������:2020-4-10
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class FieldSignVO extends MDEnum{
	public FieldSignVO(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final FieldSignVO �������� = MDEnum.valueOf(FieldSignVO.class, String.valueOf("BILLID"));
	
	
	public static final FieldSignVO ���ݺ� = MDEnum.valueOf(FieldSignVO.class, String.valueOf("BILLNO"));
	
	
	public static final FieldSignVO �������� = MDEnum.valueOf(FieldSignVO.class, String.valueOf("BILLTYPE"));
	
	
	public static final FieldSignVO ��˾ = MDEnum.valueOf(FieldSignVO.class, String.valueOf("CORP"));
	

}