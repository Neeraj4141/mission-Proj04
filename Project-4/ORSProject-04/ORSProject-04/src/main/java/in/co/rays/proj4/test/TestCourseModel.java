package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {
	private static CourseModel model = new CourseModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByName();
		testSearch();
	}

	private static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setName("Physics");
		bean.setDuration("1 year");
		bean.setDescription("kuch ni");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.add(bean);

	}

	private static void testUpdate() throws Exception {
		CourseBean bean = new CourseBean();

		bean.setId(1L);
		bean.setName("Maths");
		bean.setDuration("2 year");
		bean.setDescription("kuch ni");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.update(bean);

	}

	private static void testDelete() throws Exception {
		CourseBean bean = new CourseBean();
		model.delete(2L);

	}

	private static void testFindByPk() throws Exception {
		CourseBean bean = new CourseBean();
		bean = model.findByPk(1L);
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("Course not found");
		}

	}

	private static void testFindByName() throws Exception {
		CourseBean bean = new CourseBean();
		bean = model.findByName("maths");
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("Course not found");
		}

	}

	private static void testSearch() throws Exception {
		CourseBean bean = new CourseBean();

		List<CourseBean> list = model.search(bean, 0, 0);
		Iterator<CourseBean> it = list.iterator();
		while (it.hasNext()) {
			bean.setName("maths");
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}
	}

}
