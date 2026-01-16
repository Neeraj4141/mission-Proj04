package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.co.rays.proj4.bean.PatientBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.util.JDBCDataSource;

public class PatientModel {

	public Integer nextPk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_patient");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			System.out.println("in next pk method");
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting pk ");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}

	public long add(PatientBean bean) throws ApplicationException {
		Connection conn = null;
		int pk = 0;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_patient values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getDoctorname());
			pstmt.setString(3, bean.getPatientname());
			pstmt.setDate(4, new java.sql.Date(bean.getPatientdateofbirth().getTime()));
			pstmt.setString(5, bean.getPatientloginid());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			System.out.println("data added successfully");

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User" + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;

	}

	public void update(PatientBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_patient set doctorname = ?, patientname = ? , patientdateofbirth = ?, patientloginid = ?,  created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getDoctorname());
			pstmt.setString(2, bean.getPatientname());
			pstmt.setDate(3, new java.sql.Date(bean.getPatientdateofbirth().getTime()));
			pstmt.setString(4, bean.getPatientloginid());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			System.out.println("dataUpdate susscessfully");

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("exception : Exception in roll back " + ex.getMessage());

			}
			throw new ApplicationException("exception : Exception in update user " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(PatientBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_patient where id = ?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			System.out.println("data deleted successfully");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Exception in rollback " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete patient " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	

}
