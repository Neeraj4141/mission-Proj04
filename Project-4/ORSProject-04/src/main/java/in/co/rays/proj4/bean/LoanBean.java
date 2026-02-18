package in.co.rays.proj4.bean;

import java.util.Date;

public class LoanBean extends BaseBean {

	private Date dob;
	private String custmerId;
	private Integer loanAmount;
	private Integer Interst;

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCustmerId() {
		return custmerId;
	}

	public void setCustmerId(String custmerId) {
		this.custmerId = custmerId;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getInterst() {
		return Interst;
	}

	public void setInterst(Integer interst) {
		Interst = interst;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
