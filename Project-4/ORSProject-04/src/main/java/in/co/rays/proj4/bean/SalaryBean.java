package in.co.rays.proj4.bean;

import java.util.Date;

public class SalaryBean extends BaseBean {

	private String salaryslipcode;
	private String employeename;
	private String basicsalary;
	private String bonus;
	private Date salarydate;

	public String getSalaryslipcode() {
		return salaryslipcode;
	}

	public void setSalaryslipcode(String salaryslipcode) {
		this.salaryslipcode = salaryslipcode;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getBasicsalary() {
		return basicsalary;
	}

	public void setBasicsalary(String basicsalary) {
		this.basicsalary = basicsalary;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public Date getSalarydate() {
		return salarydate;
	}

	public void setSalarydate(Date salarydate) {
		this.salarydate = salarydate;
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
