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
public class FieldPositionVO extends MDEnum{
	public FieldPositionVO(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final FieldPositionVO ��ͷ = MDEnum.valueOf(FieldPositionVO.class, String.valueOf("HEAD"));
	
	
	public static final FieldPositionVO ���� = MDEnum.valueOf(FieldPositionVO.class, String.valueOf("BODY"));
	

}