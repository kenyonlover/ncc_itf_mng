
package nc.vo.uapbd.uapbd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.ISuperVO;
import nccloud.vo.tmpub.precison.PrecisionField;
/**
 * ������
 * @since v3.5.6-1903
 */
public class ItfConfigBillVOConst {
         /**
       * ���ݲ���
       */
      public static String CONST_ACTION_APPROVE = "APPROVE"; // ����

      public static String CONST_ACTION_DELETE = "DELETE"; // ɾ��

      public static String CONST_ACTION_DELVERSION = "DELVERSION"; // ɾ���汾

      public static String CONST_ACTION_SAVE = "SAVE"; // �ύ

      public static String CONST_ACTION_SAVEBASE = "SAVEBASE"; // ����

      public static String CONST_ACTION_UNAPPROVE = "UNAPPROVE"; // ȡ������

      public static String CONST_ACTION_UNSAVEBILL = "UNSAVEBILL"; // �ջ�

      /**
       * ��������
       */
      public static String CONST_BILLTYPE_COST = "HD01";


      /**
       * ����
       */
      // ҵ��ϵͳ�ӿ�����Ӧ�ñ���
      public static String CONST_CODE_APPCODE = "12H10101";

      // ҵ��ϵͳ�ӿ������б�ҳ���������
      public static String CONST_CODE_AREACODE_LIST_SEARCH = "list_query";

      // ҵ��ϵͳ�ӿ������б�ҳ���������
      public static String CONST_CODE_AREACODE_LIST_TABLE = "list_head";

      // ҵ��ϵͳ�ӿ����ÿ�Ƭҳ�����
      public static String CONST_CODE_PAGECODE_COST_CARD = "12H10101_CARD";

      // ҵ��ϵͳ�ӿ������б�ҳ�����
      public static String CONST_CODE_PAGECODE_COST_LIST = "12H10101_LIST";

      /**
       * ģ��
       */
      public static String CONST_MODULE = "uapbd";

      public static String CONST_MODULE_CODE = "12H1";

      /**
       * ҵ�����賣��
       */
      public static String CONST_PFLOW_ISRELOADBILL = "IS_RELOADBILL"; // ȡ������

      /**
       * ������������
       */
      public static String FIELD_GLOBALMNYFIELD = "globalMnyField";

      public static String FIELD_GLOBALRATE = "globalRateField";

      public static String FIELD_GROUPMNY = "groupMnyField";

      public static String FIELD_GROUPRATE = "groupRateField";

      public static String FIELD_MONEY = "mnyField";

      public static String FIELD_ORGMNY = "orgMnyField";

      public static String FIELD_ORGRATE = "orgRateField";

      public static String FIELD_PK_CURRTYPE = "pk_currtype";

      public static String FIELD_PK_GROUP = "pk_group";

      public static String FIELD_PK_ORG = "pk_org";


      /**
       * ��ȡ���徫�ȴ����ֶ���ϸ
       *
       * @return key:����class,value:�����ֶ��б�
       */
      public static Map<String, List<PrecisionField>> getBodyPrecisionFields() {
        Map<String, List<PrecisionField>> map =
            new HashMap<String, List<PrecisionField>>();
        List<PrecisionField> list = new ArrayList<PrecisionField>();
        // �����ֶδ���
        map.put("card_body", list);
        return map;
      }

      /**
       *
       * * ������ͷ���ȴ����ֶ�

       * @return
       */
      public static List<PrecisionField> getHeadPrecisionFields() {
        // �����ֶδ���
        List<PrecisionField> headPrecisionFields = new ArrayList<PrecisionField>();
        return headPrecisionFields;
      }
}
