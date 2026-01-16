package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.PatientBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.PatientModel;

public class TestPatientModel {

	public static PatientModel model = new PatientModel();

	public static void main(String[] args) throws ParseException, ApplicationException {

		// testAdd();
		//testUpdate();
		testDelete();
	}

	public static void testAdd() throws ParseException, ApplicationException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		PatientBean bean = new PatientBean();
		bean.setDoctorname("Dr.Deepak ");
		bean.setPatientname("Lucky");
		bean.setPatientdateofbirth(sdf.parse("2000-12-15"));
		bean.setPatientloginid("lucky@gmail.com");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testUpdate() throws ParseException, ApplicationException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		PatientBean bean = new PatientBean();
		bean.setId(1);
		bean.setDoctorname("Dr.SN Gupta");
		bean.setPatientname("Neeraj Mewada");
		bean.setPatientdateofbirth(sdf.parse("2003-12-15"));
		bean.setPatientloginid("neeraj@gmail.com");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.update(bean);
	}

	public static void testDelete() throws ApplicationException {
		PatientBean bean = new PatientBean();
		bean.setId(2);
		model.delete(bean);
	}
	

}
