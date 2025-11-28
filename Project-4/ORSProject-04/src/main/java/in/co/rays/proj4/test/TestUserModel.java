package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {
	public static UserModel model = new UserModel();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException, ParseException {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		testFindByName();

	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserBean bean = new UserBean();
		bean.setFirstName("Harshit");
		bean.setLastName("Mewada");
		bean.setLogin("harshit@gmail.com");
		bean.setPassword("harshit@123");
		bean.setDob(sdf.parse("1997-05-12"));
		bean.setMobileno("7566407602");
		bean.setRole_id(3L);
		bean.setGender("Male");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));

		model.add(bean);

	}

	private static void testUpdate() throws ParseException, ApplicationException, DuplicateRecordException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserBean bean = new UserBean();
		bean.setId(2);
		bean.setFirstName("Rohit");
		bean.setLastName("Mewada");
		bean.setLogin("rohit@gmail.com");
		bean.setPassword("rohit@123");
		bean.setDob(sdf.parse("1997-05-12"));
		bean.setMobileno("7049603932");
		bean.setRole_id(2L);
		bean.setGender("Male");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));

		model.update(bean);

	}

	private static void testDelete() throws ApplicationException {

		UserBean bean = new UserBean();
		bean.setId(3L);
		model.delete(bean);

	}

	private static void testFindByPk() throws ApplicationException {

		UserBean bean = new UserBean();
		bean = model.findBypk(2);
		if (bean != null) {

			System.out.println(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileno());
			System.out.print("\t" + bean.getRole_id());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("User not Found");
		}
	}
	private static void testFindByName() throws ApplicationException {

		UserBean bean = new UserBean();
		bean = model.findByLogin("neeraj@gmail.com");
		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileno());
			System.out.print("\t" + bean.getRole_id());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("User not Found");
		}
	}

}
