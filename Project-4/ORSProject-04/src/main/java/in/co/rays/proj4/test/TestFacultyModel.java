package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {
	private static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByEmail();
		testSearch();
	}

	private static void testAdd() throws Exception {
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1L);
		bean.setFirstName("Rahul");
		bean.setLastName("Mewada");
		bean.setDob(sdf.parse("12-03-11"));
		bean.setGender("Male");
		bean.setMobileNo("7000964865");
		bean.setEmail("");
		bean.setCollegeId(1L);
		bean.setCourseId(1L);
		bean.setSubjectId(1L);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.add(bean);

	}

	private static void testUpdate() throws Exception {
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1L);
		bean.setFirstName("Rahul");
		bean.setLastName("Mewada");
		bean.setDob(sdf.parse("1999-03-11"));
		bean.setGender("Male");
		bean.setMobileNo("7000964865");
		bean.setEmail("rahul@gmail.com");
		bean.setCollegeId(1L);
		bean.setCourseId(1L);
		bean.setSubjectId(1L);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.update(bean);

	}

	private static void testDelete() throws Exception {
		FacultyBean bean = new FacultyBean();
		bean.setId(2l);
		model.delete(bean);

	}

	private static void testFindByPk() throws Exception {
		FacultyBean bean = new FacultyBean();
		bean = model.findByPk(1l);
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("Faculty not found ");
		}

	}

	private static void testFindByEmail() throws Exception {
		FacultyBean bean = new FacultyBean();
		bean = model.findByEmail("rahul@gmail.com");
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("Faculty not found ");
		}

	}

	private static void testSearch() throws Exception {
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FacultyBean> list = model.search(bean, 0, 0);
		Iterator<FacultyBean> it = list.iterator();
		// bean.setFirstName("rahul");
		// bean.setDob(sdf.parse("1999-03-11"));
		while (it.hasNext()) {

			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}
	}
}
