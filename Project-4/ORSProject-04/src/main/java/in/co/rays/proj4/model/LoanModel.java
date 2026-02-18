package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.LoanBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class LoanModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_loan");
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

	public long add(LoanBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_loan values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setDate(2, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(3, bean.getCustmerId());
			pstmt.setInt(4, bean.getLoanAmount());
			pstmt.setInt(5, bean.getInterst());
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
			throw new ApplicationException("Exception : Exception in add Loan");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(LoanBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_loan set st_dob = ?, st_customerid = ?, st_loanamount = ?, st_intrest = ? where id = ?");
			pstmt.setDate(1, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(2, bean.getCustmerId());
			pstmt.setInt(3, bean.getLoanAmount());
			pstmt.setInt(4, bean.getInterst());
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
			throw new ApplicationException("Exception in updating Loan ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(LoanBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_loan where id = ?");
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
			throw new ApplicationException("Exception : Exception in delete Loan");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public LoanBean findByPk(long pk) throws ApplicationException {

		LoanBean bean = null;
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from st_loan where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new LoanBean();
				bean.setId(rs.getLong(1));
				bean.setDob(rs.getDate(2));
				bean.setCustmerId(rs.getString(3));
				bean.setLoanAmount(rs.getInt(4));
				bean.setInterst(rs.getInt(5));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Loan by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<LoanBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<LoanBean> search(LoanBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_loan where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getCustmerId() != null && bean.getCustmerId().length() > 0) {
				sql.append(" and st_customerid like '" + bean.getCustmerId() + "%'");
			}
			if (bean.getLoanAmount() != null && bean.getLoanAmount() > 0) {
				sql.append(" and st_loanamount = " + bean.getLoanAmount());
			}
			if (bean.getInterst() != null && bean.getInterst() > 0) {
				sql.append(" and st_interst = " + bean.getInterst());
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		Connection conn = null;
		ArrayList<LoanBean> list = new ArrayList<LoanBean>();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new LoanBean();
				bean.setId(rs.getLong(1));
				bean.setDob(rs.getDate(2));
				bean.setCustmerId(rs.getString(3));
				bean.setLoanAmount(rs.getInt(4));
				bean.setInterst(rs.getInt(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Loan");
		} finally {
			if (conn != null) {
				JDBCDataSource.closeConnection(conn);
			}
		}
		return list;
	}
}
