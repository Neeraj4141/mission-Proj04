package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {
	private static SubjectModel model = new SubjectModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByName();
		testSearch();
	}

	private static void testAdd() throws Exception {
		SubjectBean bean = new SubjectBean();
		bean.setId(1L);
		bean.setName("Physics");
		bean.setCourseId(1L);
		bean.setDescription("kuch nahi ");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.add(bean);

	}

	private static void testUpdate() throws Exception {
		SubjectBean bean = new SubjectBean();
		bean.setId(1L);
		bean.setName("Physics");
		bean.setCourseId(1L);
		bean.setDescription("kuch nahi ");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.update(bean);

	}

	private static void testDelete() throws Exception {
		SubjectBean bean = new SubjectBean();
		model.delete(2l);

	}

	private static void testFindByPk() throws Exception {
		SubjectBean bean = new SubjectBean();
		bean = model.findByPk(1L);
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
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
		SubjectBean bean = new SubjectBean();
		bean = model.findByName("physics");
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
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
		SubjectBean bean = new SubjectBean();
		List<SubjectBean> list = model.search(bean, 0, 0);
		Iterator<SubjectBean> it = list.iterator();
		while (it.hasNext()) {
		//	bean.setName("physics");
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}
