package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {

	private static MarksheetModel model = new MarksheetModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByRollNo();
		testSearch();

	}

	private static void testAdd() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		bean.setRollNo("123457");
		bean.setStudentId(2L);
		bean.setPhysics(43);
		bean.setChemistry(53);
		bean.setMaths(89);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));

		model.add(bean);
	}

	private static void testUpdate() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		bean.setId(1L);
		bean.setRollNo("123456");
		bean.setStudentId(1L);
		bean.setPhysics(45);
		bean.setChemistry(70);
		bean.setMaths(76);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));

		model.update(bean);

	}

	private static void testDelete() throws Exception {
		MarksheetBean bean = new MarksheetBean();
		bean.setId(2L);
		model.delete(bean);

	}

	private static void testFindByPk() throws Exception {

		MarksheetBean bean = new MarksheetBean();
		bean = model.findByPk(1L);
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("User not find");
		}

	}

	private static void testFindByRollNo() throws Exception {
		MarksheetBean bean = new MarksheetBean();
		bean = model.findByRollNo("123457");
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("User not find");
		}

	}

	private static void testSearch() throws Exception {
		MarksheetBean bean = new MarksheetBean();
		bean.setName("mohit");
		List<MarksheetBean> list = model.search(bean, 0, 0);
		Iterator<MarksheetBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}

}
