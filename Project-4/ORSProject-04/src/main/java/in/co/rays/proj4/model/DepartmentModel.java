package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.DepartmentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class DepartmentModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_department");
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

	public long add(DepartmentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		// Duplicate check by departmentcode
		DepartmentBean existBean = findByCode(bean.getDepartmentcode());
		if (existBean != null) {
			throw new DuplicateRecordException("Department Code already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_department values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getDepartmentcode());
			pstmt.setString(3, bean.getDepartmentname());
			pstmt.setString(4, bean.getHeadname());
			pstmt.setString(5, bean.getDepartmentstatus());

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
			throw new ApplicationException("Exception : Exception in add Department");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(DepartmentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		// Duplicate check while update
		DepartmentBean existBean = findByCode(bean.getDepartmentcode());
		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Department Code already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_department set st_departmentcode=?, st_departmentname=?, st_headname=?, st_departmentstatus=? where id=?");

			pstmt.setString(1, bean.getDepartmentcode());
			pstmt.setString(2, bean.getDepartmentname());
			pstmt.setString(3, bean.getHeadname());
			pstmt.setString(4, bean.getDepartmentstatus());
			pstmt.setLong(5, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

			System.out.println("DATA UPDATE");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Department ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(DepartmentBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_department where id = ?");
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
			throw new ApplicationException("Exception : Exception in delete Department");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public DepartmentBean findByPk(long pk) throws ApplicationException {

		DepartmentBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_department where id = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new DepartmentBean();
				bean.setId(rs.getLong(1));
				bean.setDepartmentcode(rs.getString(2));
				bean.setDepartmentname(rs.getString(3));
				bean.setHeadname(rs.getString(4));
				bean.setDepartmentstatus(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Department by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public DepartmentBean findByCode(String code) throws ApplicationException {

		DepartmentBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_department where st_departmentcode = ?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new DepartmentBean();
				bean.setId(rs.getLong(1));
				bean.setDepartmentcode(rs.getString(2));
				bean.setDepartmentname(rs.getString(3));
				bean.setHeadname(rs.getString(4));
				bean.setDepartmentstatus(rs.getString(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Department by code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<DepartmentBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<DepartmentBean> search(DepartmentBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_department where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getDepartmentcode() != null && bean.getDepartmentcode().length() > 0) {
				sql.append(" and st_departmentcode like '" + bean.getDepartmentcode() + "%'");
			}
			if (bean.getDepartmentname() != null && bean.getDepartmentname().length() > 0) {
				sql.append(" and st_departmentname like '" + bean.getDepartmentname() + "%'");
			}
			if (bean.getHeadname() != null && bean.getHeadname().length() > 0) {
				sql.append(" and st_headname like '" + bean.getHeadname() + "%'");
			}
			if (bean.getDepartmentstatus() != null && bean.getDepartmentstatus().length() > 0) {
				sql.append(" and st_departmentstatus like '" + bean.getDepartmentstatus() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = null;
		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new DepartmentBean();
				bean.setId(rs.getLong(1));
				bean.setDepartmentcode(rs.getString(2));
				bean.setDepartmentname(rs.getString(3));
				bean.setHeadname(rs.getString(4));
				bean.setDepartmentstatus(rs.getString(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Department");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
