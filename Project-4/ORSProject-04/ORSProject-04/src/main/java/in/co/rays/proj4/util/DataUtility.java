package in.co.rays.proj4.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtility {
	public static final String APP_DATE_FORMATE = "yyyy-MM-dd";
	public static final String APP_TIME_FORMATE = "dd-MM-yyyy HH:mm:ss";

	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMATE);
	private static final SimpleDateFormat timeformatter = new SimpleDateFormat(APP_TIME_FORMATE);

	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}
		return date;
	}

	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	public static int getInt(String val) {
		if (DataValidator.isinteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}
}
