
package nc.bs.uapbd.uapbd.aggbusi.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ��׼���ݵ���չ�����
 *
 */
public enum ItfConfigBillVOPluginPoint implements IPluginPoint {
        /**
         * ����
         */
        APPROVE,
        /**
         * ����
         */
        SEND_APPROVE,

        /**
         * ȡ�����
         */
        UNAPPROVE,

        /**
         * �ջ�
         */
        UNSEND_APPROVE,
        /**
         * ɾ��
         */
        DELETE,
        /**
         * ����
         */
        INSERT,
        /**
         * ����
         */
        UPDATE,
        /**
         * �ű�ɾ��
         */
        SCRIPT_DELETE,
        /**
         * �ű�����
         */
        SCRIPT_INSERT,
        /**
         * �ű�����
         */
        SCRIPT_UPDATE;

        @Override
        public String getComponent() {
                return "uapbd";
        }

        @Override
        public String getModule() {
                return "uapbd";
        }

        @Override
        public String getPoint() {
                return this.getClass().getName() + "." + this.name();
        }

}
