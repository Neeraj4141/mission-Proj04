package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {
	public static RoleModel model = new RoleModel();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindBypk();
		// testFindByName();
		testSearch();

	}

	private static void testAdd() throws ApplicationException, DuplicateRecordException {

		RoleBean bean = new RoleBean();

		bean.setName("neeraj");
		bean.setDiscription("mewada");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));

		long pk = model.add(bean);
		System.out.println(" Data Upadte Successfully = " + pk);
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {

		RoleBean bean = new RoleBean();

		bean.setId(1);
		bean.setName("admin");
		bean.setDiscription("Neeraj");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getDate()));
		bean.setModifiedDatetime(new Timestamp(new Date().getDate()));

		model.update(bean);

		System.out.println("Data Updated Successfully ");

	}

	public static void testDelete() throws ApplicationException {
		RoleBean bean = new RoleBean();
		bean.setId(5);
		model.delete(bean);
		System.out.println("role deleted successfully ");
	}

	private static void testFindBypk() throws ApplicationException {

		RoleBean bean = new RoleBean();

		bean = model.findByPk(2);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDiscription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("Role Not Found");
		}
	}

	public static void testFindByName() throws ApplicationException {

		RoleBean bean = new RoleBean();

		bean = model.findByName("admin");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDiscription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	public static void testSearch() throws ApplicationException {

		RoleBean bean = new RoleBean();
		bean.setId(2);
		bean.setName("student");
		bean.setDiscription("admin");
		List<RoleBean> list = model.search(bean, 0, 0);

		Iterator<RoleBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDiscription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}
}
