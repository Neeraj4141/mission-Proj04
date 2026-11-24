package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.CJCommunicationsException;

import in.co.rays.proj4.bean.HospitelBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseDownException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class HospitelModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(
					"select max(id) from st_hospitel");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

			rs.close();
			pstmt.close();

			System.out.println("in next pk method");

		} catch (CJCommunicationsException e) {

			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");

		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting pk ");

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public long add(HospitelBean bean)
			throws ApplicationException, DuplicateRecordException {

		HospitelModel hmodel = new HospitelModel();

		HospitelBean existbean = hmodel.findbycode(bean.getHospitelcode());

		if (existbean != null) {
			throw new DuplicateRecordException("Hospitel Code already exists");
		}

		Connection conn = null;
		int pk = 0;

		try {

			pk = nextPk();

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"insert into st_hospitel values(?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getHospitelcode());
			pstmt.setString(3, bean.getHospitelname());
			pstmt.setString(4, bean.getContactnumber());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

			System.out.println("data added successfully");

		} catch (CJCommunicationsException e) {

			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {

				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}

			throw new ApplicationException(
					"Exception : Exception in add Hospitel " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(HospitelBean bean)
			throws ApplicationException, DuplicateRecordException {

		HospitelModel hmodel = new HospitelModel();

		HospitelBean existbean = hmodel.findbycode(bean.getHospitelcode());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Hospitel Code already exists");
		}

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE st_hospitel SET hospitelcode=?, hospitelname=?, contactnumber=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? WHERE id=?");

			pstmt.setString(1, bean.getHospitelcode());
			pstmt.setString(2, bean.getHospitelname());
			pstmt.setString(3, bean.getContactnumber());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			pstmt.executeUpdate();

			pstmt.close();

			conn.commit();

			System.out.println("dataUpdate successfully");

		} catch (CJCommunicationsException e) {

			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {

				throw new ApplicationException(
						"Exception : Exception in roll back " + ex.getMessage());
			}

			throw new ApplicationException(
					"Exception : Exception in update Hospitel " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(HospitelBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"delete from st_hospitel where id = ?");

			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			pstmt.close();

			System.out.println("data deleted successfully");

		} catch (CJCommunicationsException e) {

			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {

				throw new ApplicationException(
						"Exception : Exception in rollback " + ex.getMessage());
			}

			throw new ApplicationException(
					"Exception : Exception in delete Hospitel " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}
	}

	public HospitelBean findbycode(String hospitelcode)
			throws ApplicationException {

		Connection conn = null;
		HospitelBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(
					"select * from st_hospitel where hospitelcode = ?");

			pstmt.setString(1, hospitelcode);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new HospitelBean();

				bean.setId(rs.getLong(1));
				bean.setHospitelcode(rs.getString(2));
				bean.setHospitelname(rs.getString(3));
				bean.setContactnumber(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}

			rs.close();
			pstmt.close();

		} catch (CJCommunicationsException e) {

			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");

		} catch (Exception e) {

			throw new ApplicationException(
					"Exception : Exception in Hospitel by getting code " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public HospitelBean findbypk(long pk) throws ApplicationException {

		Connection conn = null;
		HospitelBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(
					"select * from st_hospitel where id = ?");

			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new HospitelBean();

				bean.setId(rs.getLong(1));
				bean.setHospitelcode(rs.getString(2));
				bean.setHospitelname(rs.getString(3));
				bean.setContactnumber(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}

			rs.close();
			pstmt.close();

		} catch (CJCommunicationsException e) {

			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");

		} catch (Exception e) {

			throw new ApplicationException(
					"Exception : Exception in Hospitel by getting id " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<HospitelBean> list() throws ApplicationException {

		return search(null, 0, 0);
	}

	public List<HospitelBean> search(HospitelBean bean, int pageNo, int pageSize)
			throws ApplicationException {

		Connection conn = null;

		ArrayList<HospitelBean> list = new ArrayList<HospitelBean>();

		StringBuffer sql = new StringBuffer(
				"select * from st_hospitel where 1 = 1");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getHospitelcode() != null
					&& bean.getHospitelcode().length() > 0) {

				sql.append(" and hospitelcode like '"
						+ bean.getHospitelcode() + "%'");
			}

			if (bean.getHospitelname() != null
					&& bean.getHospitelname().length() > 0) {

				sql.append(" and hospitelname like '"
						+ bean.getHospitelname() + "%'");
			}

			if (bean.getContactnumber() != null
					&& bean.getContactnumber().length() > 0) {

				sql.append(" and contactnumber like '"
						+ bean.getContactnumber() + "%'");
			}
		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new HospitelBean();

				bean.setId(rs.getLong(1));
				bean.setHospitelcode(rs.getString(2));
				bean.setHospitelname(rs.getString(3));
				bean.setContactnumber(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

				list.add(bean);
			}

			rs.close();
			pstmt.close();

		} catch (CJCommunicationsException e) {

			e.printStackTrace();
			throw new DatabaseDownException("Database Server Down!!!");

		} catch (Exception e) {

			throw new ApplicationException(
					"Exception : Exception in search Hospitel " + e.getMessage());

		} finally {

			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}
}