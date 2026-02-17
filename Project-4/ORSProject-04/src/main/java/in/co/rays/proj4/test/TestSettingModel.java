package in.co.rays.proj4.test;

import in.co.rays.proj4.bean.SettingBean;

import in.co.rays.proj4.model.SettingModel;

public class TestSettingModel {
	public static void main(String[] args) throws Exception {
		// testAdd();
		testUpdate();

	}

	private static void testAdd() throws Exception {

		SettingBean bean = new SettingBean();
		SettingModel model = new SettingModel();

		bean.setSettingName("Laptop");
		bean.setSettingType("add memory");
		bean.setSettingstatus("progress");
		bean.setSettingValue("1200");

		model.add(bean);

	}

	private static void testUpdate() throws Exception {

		SettingBean bean = new SettingBean();
		SettingModel model = new SettingModel();

		bean.setSettingName("Laptop");
		bean.setSettingType("add memory");
		bean.setSettingstatus("progress");
		bean.setSettingValue("1500");
		bean.setId(1);

		model.update(bean);

	}
}
