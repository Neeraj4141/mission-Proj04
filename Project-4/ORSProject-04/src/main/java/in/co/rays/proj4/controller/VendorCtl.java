package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.VendorBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.VendorModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "VendorCtl", urlPatterns = { "/ctl/VendorCtl" })
public class VendorCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("vendorecode"))) {
			request.setAttribute("vendorecode",
					PropertyReader.getValue("error.require", "Vendor Code"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("vendorename"))) {
			request.setAttribute("vendorename",
					PropertyReader.getValue("error.require", "Vendor Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("servicetype"))) {
			request.setAttribute("servicetype",
					PropertyReader.getValue("error.require", "Service Type"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactnumber"))) {
			request.setAttribute("contactnumber",
					PropertyReader.getValue("error.require", "Contact Number"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		VendorBean bean = new VendorBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setVendorecode(DataUtility.getString(request.getParameter("vendorecode")));
		bean.setVendorename(DataUtility.getString(request.getParameter("vendorename")));
		bean.setServicetype(DataUtility.getString(request.getParameter("servicetype")));
		bean.setContactnumber(DataUtility.getString(request.getParameter("contactnumber")));

		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		VendorModel model = new VendorModel();

		if (id > 0) {
			try {
				VendorBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		VendorModel model = new VendorModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			VendorBean bean = (VendorBean) populateBean(request);

			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Vendor Code already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			VendorBean bean = (VendorBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Vendor Code already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.VENDOR_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.VENDOR_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.VENDOR_VIEW;
	}
}