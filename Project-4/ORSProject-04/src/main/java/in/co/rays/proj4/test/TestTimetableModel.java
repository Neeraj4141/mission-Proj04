package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.model.TimetableModel;

public class TestTimetableModel {
	private static TimetableModel model = new TimetableModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		testSearch();
	}

	private static void testAdd() throws Exception {
		TimetableBean bean = new TimetableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1L);
		bean.setSemester("");
		bean.setDescription("");
		bean.setExamDate(sdf.parse("2023-12-12"));
		bean.setExamTime("");
		bean.setCourseId(1L);
		bean.setSubjectId(1L);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.add(bean);

	}

	private static void testUpdate() throws Exception {
		TimetableBean bean = new TimetableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1L);
		bean.setSemester("");
		bean.setDescription("");
		bean.setExamDate(sdf.parse("2023-12-12"));
		bean.setCourseId(1L);
		bean.setSubjectId(1L);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));
		model.update(bean);

	}

	private static void testDelete() throws Exception {
		TimetableBean bean = new TimetableBean();
		bean.setId(2l);
		model.delete(bean);

	}

	private static void testFindByPk() throws Exception {

		TimetableBean bean = new TimetableBean();
		bean = model.findByPk(1L);
		System.out.print(bean.getId());
		System.out.print("\t" + bean.getSemester());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getExamDate());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getExamDate());
		System.out.print("\t" + bean.getCourseId());
		System.out.print("\t" + bean.getSubjectId());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDatetime());
		System.out.println("\t" + bean.getModifiedDatetime());

	}

	private static void testSearch() throws Exception {

		TimetableBean bean = new TimetableBean();
		List<TimetableBean> list = model.search(bean, 0, 0);
		Iterator<TimetableBean> it = list.iterator();
		while (it.hasNext()) {
			bean.setSemester("");
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}
	}
}
