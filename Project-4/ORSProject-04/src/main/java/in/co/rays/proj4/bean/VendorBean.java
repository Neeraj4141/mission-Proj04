package in.co.rays.proj4.bean;

public class VendorBean extends BaseBean {

	private String vendorecode;
	private String vendorename;
	private String servicetype;
	private String contactnumber;

	public String getVendorecode() {
		return vendorecode;
	}

	public void setVendorecode(String vendorecode) {
		this.vendorecode = vendorecode;
	}

	public String getVendorename() {
		return vendorename;
	}

	public void setVendorename(String vendorename) {
		this.vendorename = vendorename;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
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
