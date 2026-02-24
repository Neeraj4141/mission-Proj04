package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.VendorBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class VendorModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_vendor");
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

	public long add(VendorBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		VendorBean duplicate = findByCode(bean.getVendorecode());

		if (duplicate != null) {
			throw new DuplicateRecordException("Vendor Code already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_vendor values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getVendorecode());
			pstmt.setString(3, bean.getVendorename());
			pstmt.setString(4, bean.getServicetype());
			pstmt.setString(5, bean.getContactnumber());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in add Vendor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void update(VendorBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_vendor set vendore_code=?, vendore_name=?, service_type=?, contact_number=? where id=?");
			pstmt.setString(1, bean.getVendorecode());
			pstmt.setString(2, bean.getVendorename());
			pstmt.setString(3, bean.getServicetype());
			pstmt.setString(4, bean.getContactnumber());
			pstmt.setLong(5, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Vendor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(VendorBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_vendor where id=?");
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
			throw new ApplicationException("Exception in delete Vendor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public VendorBean findByPk(long pk) throws ApplicationException {

		VendorBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_vendor where id=?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new VendorBean();
				bean.setId(rs.getLong(1));
				bean.setVendorecode(rs.getString(2));
				bean.setVendorename(rs.getString(3));
				bean.setServicetype(rs.getString(4));
				bean.setContactnumber(rs.getString(5));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception in getting Vendor by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public VendorBean findByCode(String code) throws ApplicationException {

		VendorBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_vendor where vendore_code=?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new VendorBean();
				bean.setId(rs.getLong(1));
				bean.setVendorecode(rs.getString(2));
				bean.setVendorename(rs.getString(3));
				bean.setServicetype(rs.getString(4));
				bean.setContactnumber(rs.getString(5));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception in getting Vendor by Code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<VendorBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<VendorBean> search(VendorBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_vendor where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id=" + bean.getId());
			}
			if (bean.getVendorecode() != null && bean.getVendorecode().length() > 0) {
				sql.append(" and vendore_code like '" + bean.getVendorecode() + "%'");
			}
			if (bean.getVendorename() != null && bean.getVendorename().length() > 0) {
				sql.append(" and vendore_name like '" + bean.getVendorename() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		ArrayList<VendorBean> list = new ArrayList<VendorBean>();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new VendorBean();
				bean.setId(rs.getLong(1));
				bean.setVendorecode(rs.getString(2));
				bean.setVendorename(rs.getString(3));
				bean.setServicetype(rs.getString(4));
				bean.setContactnumber(rs.getString(5));
				list.add(bean);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception in search Vendor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}