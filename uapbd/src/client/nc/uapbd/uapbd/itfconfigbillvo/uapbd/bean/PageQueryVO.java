
package nc.uapbd.uapbd.itfconfigbillvo.uapbd.bean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * ���ݷ�ҳ��ѯ���
 */
public class PageQueryVO implements Serializable {

  /**
   * ÿҳ�ĵ�����
   */
  public static final String RECORD_IN_PAGE = "recordInPage";

  private static final long serialVersionUID = 761548308850627487L;

  /**
   * ��ǰҳ�ĵ���ʵ�����飨ֻ�е�һ������ʵ���б��壩
   */
  private IBill[] currentPageBills;

  /**
   * ��ǰ��ѯ���������е�������
   */
  private String[] pks;

  /**
   * ���ݷ�ҳ��ѯ������캯��
   *
   * @param pks ��ǰ��ѯ���������е�������
   * @param bills ��ǰҳ�ĵ���ʵ�����飨ֻ�е�һ������ʵ���б��壩
   */
  public PageQueryVO(String[] pks, IBill[] bills) {
    this.pks = pks;
    this.currentPageBills = bills;
  }

  /**
   * ��ȡ��ǰҳ�ĵ���ʵ�����飨ֻ�е�һ������ʵ���б��壩
   *
   * @return ��ǰҳ�ĵ���ʵ�����飨ֻ�е�һ������ʵ���б��壩
   */
  public IBill[] getCurrentPageBills() {
    return this.currentPageBills;
  }

  /**
   * ��ȡ��ǰ��ѯ���������е�������
   *
   * @return ��ǰ��ѯ���������е�������
   */
  public String[] getPks() {
    return this.pks;
  }

  /**
   * ת��Ϊƽ̨�ķ�ҳVO
   *
   * @return ƽ̨�ķ�ҳVO
   */
  public PaginationQueryVO toPaginationQueryVO() {
    PaginationQueryVO vo = new PaginationQueryVO();
    Map<String, Object> billmap = new HashMap<String, Object>();
    for (IBill bill : this.currentPageBills) {
      billmap.put(bill.getPrimaryKey(), bill);
    }
    vo.setAllpks(this.pks);
    vo.setBillmap(billmap);
    return vo;
  }

}