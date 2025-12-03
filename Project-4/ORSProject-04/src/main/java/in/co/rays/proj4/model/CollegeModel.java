package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel {

	public Integer nextPk() throws SQLException {
		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id)from st_college");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;

	}

	public void add(CollegeBean bean) throws SQLException, DuplicateRecordException, ApplicationException {
		int pk = 0;
		Connection conn = null;

		CollegeBean exiestbean = findByPk(bean.getId());
		if (exiestbean != null) {
			throw new DuplicateRecordException("college already exiest ");

		}
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_college values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

			System.out.println("Data Inserted Successfully " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception :  rollback Exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add college");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(CollegeBean bean) throws SQLException, DuplicateRecordException, ApplicationException {
		Connection conn = null;
		CollegeBean exiestbean = findByName(bean.getName());
		if (exiestbean != null) {
			if (exiestbean != null && bean.getId() != exiestbean.getId()) {
				throw new DuplicateRecordException("Colloge is alredy exiest");
			}
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_college set name = ?, address = ?, state = ?, city = ?, phone_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?  where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("Data updated Successfully " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : rollback Exception");
			}
			throw new ApplicationException("Exception : Exception in update college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(CollegeBean bean) throws SQLException, ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_college where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("data deleted successfully " + i);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : rollback Exception");
			}
			throw new ApplicationException("Exception : Exception in delete college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public CollegeBean findByPk(long id) throws SQLException, ApplicationException {
		Connection conn = null;
		CollegeBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_college where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting college by pk " + e.getMessage());

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	public CollegeBean findByName(String name) throws SQLException, ApplicationException {
		Connection conn = null;
		CollegeBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_college where name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : exception in college by getting name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	public List list() throws SQLException, ApplicationException {
		return search(null, 0, 0);

	}

	public List search(CollegeBean bean, int pageNo, int pageSize) throws SQLException, ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_college where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		Connection conn = null;
		List list = new ArrayList();

		System.out.println("sql ==>> " + sql.toString());
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in seach college");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;

	}

}
