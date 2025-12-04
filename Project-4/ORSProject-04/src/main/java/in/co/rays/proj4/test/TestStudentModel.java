package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {
	private static StudentModel model = new StudentModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByEmail();
		testSearch();

	}

	private static void testAdd() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();
		bean.setFirstName("Neeraj");
		bean.setLastName("Chawda");
		bean.setDob(sdf.parse("2003-12-15"));
		bean.setGender("Male");
		bean.setMobileNo("988766666");
		bean.setEmail("neeraj@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.add(bean);

	}

	private static void testUpdate() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();
		bean.setId(2L);
		bean.setFirstName("Sumit");
		bean.setLastName("Chouhan");
		bean.setDob(sdf.parse("2003-11-12"));
		bean.setGender("Male");
		bean.setMobileNo("988766666");
		bean.setEmail("sumit@gmail.com");
		bean.setCollegeId(2);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.update(bean);

	}

	private static void testDelete() throws Exception {
		StudentBean bean = new StudentBean();
		bean.setId(3);
		model.delete(bean);
	}

	private static void testFindByPk() throws Exception {

		StudentBean bean = new StudentBean();
		bean = model.findByPk(1L);
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("Student not found");
		}

	}

	private static void testFindByEmail() throws Exception {
		StudentBean bean = new StudentBean();
		bean = model.findByEmail("sumit@gmail.com");
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("Student not found");
		}

	}

	private static void testSearch() throws Exception {
		StudentBean bean = new StudentBean();
		// bean.setFirstName("mohit");
		List<StudentBean> list = model.search(bean, 0, 0);
		Iterator<StudentBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}
	}
}
