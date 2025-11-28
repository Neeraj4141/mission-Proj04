package in.co.rays.proj4.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DublicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel {

	public int nextPk(UserBean bean) {
		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public void add(UserBean bean) throws ApplicationException, DublicateRecordException {
		int pk = 0;
		Connection conn = null;

		UserBean exiestbean = findByLogin(bean.getLogin());
		if (exiestbean != null) {
			throw new DublicateRecordException("Login already exiest");

		}
		try {
			pk = nextPk(bean);
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileno());
			pstmt.setLong(8, bean.getRole_id());
			pstmt.setString(9, bean.getGender());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println(" Data Inserted Succesfully ======> " + i);

			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add user " + e);

		}
		JDBCDataSource.closeConnection(conn);

	}

	public void update(UserBean bean) throws ApplicationException, DublicateRecordException {
		Connection conn = null;

		UserBean exiestbean = findByLogin(bean.getLogin());
		if (exiestbean != null && bean.getId() != exiestbean.getId()) {
			throw new DublicateRecordException("Login already exiest");

		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name = ?, last_name = ?, login = ?, password = ?, dob = ?, mobile_no = ?, role_id = ?, gender = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?  where id = ?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileno());
			pstmt.setLong(7, bean.getRole_id());
			pstmt.setString(8, bean.getGender());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
			int i = pstmt.executeUpdate();

			conn.commit();
			System.out.println("Data update successfully ======> " + i);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				throw new ApplicationException("Exception : Delete Roll Back Exception " + ex.getMessage());

			}
			throw new ApplicationException("Exception in updating user " + e);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(long id) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data Deleted successfully " + i);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				throw new ApplicationException("Exception :  add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete user  " + e);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public UserBean findBypk(long pk) throws ApplicationException {

		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id= ?");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileno(rs.getString(7));
				bean.setRole_id(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting user by pk " + e);

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	public UserBean findByLogin(String login) throws ApplicationException {
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select* from st_user where login = ?");
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileno(rs.getString(7));
				bean.setRole_id(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting user by login " + e);

		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		return bean;

	}

	public UserBean authenticate(String login, String password) throws ApplicationException {

		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ? and password = ?");
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileno(rs.getString(7));
				bean.setRole_id(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting user by authenticate " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

}
