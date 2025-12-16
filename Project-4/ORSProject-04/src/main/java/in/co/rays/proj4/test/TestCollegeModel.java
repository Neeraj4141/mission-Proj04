package in.co.rays.proj4.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {

	private static CollegeModel model = new CollegeModel();

	public static void main(String[] args) throws SQLException, DuplicateRecordException, ApplicationException {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByName();
		testSearch();

	}

	private static void testAdd() throws ApplicationException {
		CollegeBean bean = new CollegeBean();

		bean.setName("IPS");
		bean.setAddress(" Rajendra nagar");
		bean.setState("MP");
		bean.setCity("Indore");
		bean.setPhoneNo("9827736628");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));

		try {
			model.add(bean);
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	private static void testUpdate() throws SQLException, DuplicateRecordException, ApplicationException {
		CollegeBean bean = new CollegeBean();

		bean.setId(3L);
		bean.setName("IPS");
		bean.setAddress("Rajendra nagar Squre");
		bean.setState("MP");
		bean.setCity("Indore");
		bean.setPhoneNo("9999887800");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.update(bean);

	}

	private static void testDelete() throws SQLException, ApplicationException {
		CollegeBean bean = new CollegeBean();
		bean.setId(3L);
		model.delete(bean);

	}

	private static void testFindByPk() throws SQLException, ApplicationException {

		CollegeBean bean = new CollegeBean();
		bean = model.findByPk(2);
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("User not find");
		}

	}

	private static void testFindByName() throws SQLException, ApplicationException {
		CollegeBean bean = new CollegeBean();
		bean = model.findByName("DAVV");
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("User not find");
		}

	}

	private static void testSearch() throws SQLException, ApplicationException {
		CollegeBean bean = new CollegeBean();
		// bean.setName("davv");

		List<CollegeBean> list = model.search(bean, 0, 0);
		Iterator<CollegeBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

}
