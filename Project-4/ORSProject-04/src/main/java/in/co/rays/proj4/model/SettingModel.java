package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import in.co.rays.proj4.bean.SettingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseDownException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SettingModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_setting");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(SettingBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		SettingBean duplicate = findByName(bean.getSettingName());

		if (duplicate != null) {
			throw new DuplicateRecordException("Setting already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_setting values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getSettingName());
			pstmt.setString(3, bean.getSettingValue());
			pstmt.setString(4, bean.getSettingType());
			pstmt.setString(5, bean.getSettingstatus());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			System.out.println("DATA ADDED");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Setting");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(SettingBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_setting set setting_name = ?, setting_value = ?, setting_type = ?, setting_status = ? where id = ?");
			pstmt.setString(1, bean.getSettingName());
			pstmt.setString(2, bean.getSettingValue());
			pstmt.setString(3, bean.getSettingType());
			pstmt.setString(4, bean.getSettingstatus());
			pstmt.setLong(5, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			System.out.println("DATA UPDATE");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Setting ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(SettingBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_setting where id = ?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Setting");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public SettingBean findByPk(long pk) throws ApplicationException {

		SettingBean bean = null;
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from st_setting where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SettingBean();
				bean.setId(rs.getLong(1));
				bean.setSettingName(rs.getString(2));
				bean.setSettingValue(rs.getString(3));
				bean.setSettingType(rs.getString(4));
				bean.setSettingstatus(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (CJCommunicationsException e) {
			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Setting by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public SettingBean findByName(String name) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_setting where setting_name = ?");
		SettingBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SettingBean();
				bean.setId(rs.getLong(1));
				bean.setSettingName(rs.getString(2));
				bean.setSettingValue(rs.getString(3));
				bean.setSettingType(rs.getString(4));
				bean.setSettingstatus(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Setting by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<SettingBean> list() throws ApplicationException, CommunicationsException {
		return search(null, 0, 0);
	}

	public List<SettingBean> search(SettingBean bean, int pageNo, int pageSize)
			throws ApplicationException, CommunicationsException {

		StringBuffer sql = new StringBuffer("select * from st_setting where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getSettingName() != null && bean.getSettingName().length() > 0) {
				sql.append(" and setting_name like '" + bean.getSettingName() + "%'");
			}
			if (bean.getSettingValue() != null && bean.getSettingValue().length() > 0) {
				sql.append(" and setting_value like '" + bean.getSettingValue() + "%'");
			}
			if (bean.getSettingType() != null && bean.getSettingType().length() > 0) {
				sql.append(" and setting_type like '" + bean.getSettingType() + "%'");
			}
			if (bean.getSettingstatus() != null && bean.getSettingstatus().length() > 0) {
				sql.append(" and setting_status like '" + bean.getSettingstatus() + "%'");
			}

		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = null;
		ArrayList<SettingBean> list = new ArrayList<SettingBean>();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SettingBean();
				bean.setId(rs.getLong(1));
				bean.setSettingName(rs.getString(2));
				bean.setSettingValue(rs.getString(3));
				bean.setSettingType(rs.getString(4));
				bean.setSettingstatus(rs.getString(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Setting");
		} finally {
			if (conn != null) {
				JDBCDataSource.closeConnection(conn);

			}
		}
		return list;
	}
}
