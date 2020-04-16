package nc.vo.uapbd.restservice;


import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class BusinessBillServiceLogVO extends SuperVO{
	
	private static final long serialVersionUID = 3041233484778729130L;
	
	public String pk_log;
	public String sender;
	public String itftype;
	public String orgid;
	public String billtype;
	public String billpk;
	public String billno;
	public String recmsg;
	public UFDateTime rectime;
	public String retflag;
	public String retmsg;
	public UFDateTime rettime;
	public UFDateTime ts;
	public Integer dr;

	public String getPk_log() {
		return pk_log;
	}

	public void setPk_log(String pk_log) {
		this.pk_log = pk_log;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getItftype() {
		return itftype;
	}

	public void setItftype(String itftype) {
		this.itftype = itftype;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getBillpk() {
		return billpk;
	}

	public void setBillpk(String billpk) {
		this.billpk = billpk;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getRecmsg() {
		return recmsg;
	}

	public void setRecmsg(String recmsg) {
		this.recmsg = recmsg;
	}

	public UFDateTime getRectime() {
		return rectime;
	}

	public void setRectime(UFDateTime rectime) {
		this.rectime = rectime;
	}

	public String getRetflag() {
		return retflag;
	}

	public void setRetflag(String retflag) {
		this.retflag = retflag;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public UFDateTime getRettime() {
		return rettime;
	}

	public void setRettime(UFDateTime rettime) {
		this.rettime = rettime;
	}

	public UFDateTime getTs() {
		return ts;
	}

	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getPKFieldName() {
		return "pk_log";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "businessbillservicelog";
	}

}
