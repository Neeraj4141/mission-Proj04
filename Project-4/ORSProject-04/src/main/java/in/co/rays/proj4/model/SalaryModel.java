package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.SalaryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SalaryModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_salary");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(SalaryBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		SalaryBean duplicate = findByCode(bean.getSalaryslipcode());
		if (duplicate != null) {
			throw new DuplicateRecordException("Salary Slip Code already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_salary values(?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getSalaryslipcode());
			pstmt.setString(3, bean.getEmployeename());
			pstmt.setString(4, bean.getBasicsalary());
			pstmt.setString(5, bean.getBonus());
			pstmt.setDate(6, new Date(bean.getSalarydate().getTime()));

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in add Salary");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(SalaryBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		SalaryBean duplicate = findByCode(bean.getSalaryslipcode());

		// Check duplicate but ignore same record id
		if (duplicate != null && duplicate.getId() != bean.getId()) {
			throw new DuplicateRecordException("Salary Slip Code already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_salary set st_salaryslipcode=?, st_employeename=?, st_basicsalary=?, st_bonus=?, st_salarydate=? where id=?");

			pstmt.setString(1, bean.getSalaryslipcode());
			pstmt.setString(2, bean.getEmployeename());
			pstmt.setString(3, bean.getBasicsalary());
			pstmt.setString(4, bean.getBonus());
			pstmt.setDate(5, new Date(bean.getSalarydate().getTime()));
			pstmt.setLong(6, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Salary");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(SalaryBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_salary where id=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in delete Salary");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public SalaryBean findByPk(long pk) throws ApplicationException {

		SalaryBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_salary where id=?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SalaryBean();
				bean.setId(rs.getLong(1));
				bean.setSalaryslipcode(rs.getString(2));
				bean.setEmployeename(rs.getString(3));
				bean.setBasicsalary(rs.getString(4));
				bean.setBonus(rs.getString(5));
				bean.setSalarydate(rs.getDate(6));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception in getting Salary by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public SalaryBean findByCode(String code) throws ApplicationException {

		SalaryBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_salary where st_salaryslipcode=?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SalaryBean();
				bean.setId(rs.getLong(1));
				bean.setSalaryslipcode(rs.getString(2));
				bean.setEmployeename(rs.getString(3));
				bean.setBasicsalary(rs.getString(4));
				bean.setBonus(rs.getString(5));
				bean.setSalarydate(rs.getDate(6));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception in getting Salary by Code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<SalaryBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<SalaryBean> search(SalaryBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_salary where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getSalaryslipcode() != null && bean.getSalaryslipcode().length() > 0) {
				sql.append(" and st_salaryslipcode like '" + bean.getSalaryslipcode() + "%'");
			}
			if (bean.getEmployeename() != null && bean.getEmployeename().length() > 0) {
				sql.append(" and st_employeename like '" + bean.getEmployeename() + "%'");
			}
			if (bean.getBasicsalary() != null && bean.getBasicsalary().length() > 0) {
				sql.append(" and st_basicsalary like '" + bean.getBasicsalary() + "%'");
			}
			if (bean.getBonus() != null && bean.getBonus().length() > 0) {
				sql.append(" and st_bonus like '" + bean.getBonus() + "%'");
			}
			if (bean.getSalarydate() != null) {
				sql.append(" and st_salarydate = '" + new java.sql.Date(bean.getSalarydate().getTime()) + "'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = null;
		ArrayList<SalaryBean> list = new ArrayList<SalaryBean>();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SalaryBean();
				bean.setId(rs.getLong(1));
				bean.setSalaryslipcode(rs.getString(2));
				bean.setEmployeename(rs.getString(3));
				bean.setBasicsalary(rs.getString(4));
				bean.setBonus(rs.getString(5));
				bean.setSalarydate(rs.getDate(6));
				list.add(bean);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Salary");
		} finally {
			if (conn != null) {
				JDBCDataSource.closeConnection(conn);
			}
		}
		return list;
	}
}