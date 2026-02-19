package in.co.rays.proj4.bean;

public class DepartmentBean extends BaseBean {

	private String departmentcode;
	private String departmentname;
	private String headname;
	private String departmentstatus;

	public String getDepartmentcode() {
		return departmentcode;
	}

	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getHeadname() {
		return headname;
	}

	public void setHeadname(String headname) {
		this.headname = headname;
	}

	public String getDepartmentstatus() {
		return departmentstatus;
	}

	public void setDepartmentstatus(String departmentstatus) {
		this.departmentstatus = departmentstatus;
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
