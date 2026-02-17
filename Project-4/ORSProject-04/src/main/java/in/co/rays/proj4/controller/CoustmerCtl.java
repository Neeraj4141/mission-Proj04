package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CoustmerBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.CoustmerModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "CoustmerCtl", urlPatterns = { "/ctl/CoustmerCtl" })
public class CoustmerCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(CoustmerCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CoustmerCtl validate started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Customer Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Customer Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "Product"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", "Invalid Mobile No");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("rate"))) {
			request.setAttribute("rate", PropertyReader.getValue("error.require", "Rate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("paymentstatus"))) {
			request.setAttribute("paymentstatus", PropertyReader.getValue("error.require", "Payment Status"));
			pass = false;
		}

		log.debug("CoustmerCtl validate ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("CoustmerCtl populateBean started");

		CoustmerBean bean = new CoustmerBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setProduct(DataUtility.getString(request.getParameter("product")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setMobileno(DataUtility.getString(request.getParameter("mobileno")));
		bean.setRate(DataUtility.getString(request.getParameter("rate")));
		bean.setPaymentstatus(DataUtility.getString(request.getParameter("paymentstatus")));

		populateDTO(bean, request);

		log.debug("CoustmerCtl populateBean ended");
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CoustmerCtl doGet started");
		System.out.println("in do get coustmer");

		long id = DataUtility.getLong(request.getParameter("id"));
		CoustmerModel model = new CoustmerModel();

		if (id > 0) {
			try {
				CoustmerBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			}catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("CoustmerCtl doGet ended");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CoustmerCtl doPost started");
		System.out.println("in do get of coustmer");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		CoustmerModel model = new CoustmerModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {

			CoustmerBean bean = (CoustmerBean) populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Coustmer added successfully", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			CoustmerBean bean = (CoustmerBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Customer updated successfully", request);

			} catch (ApplicationException e) {
				log.error("Error in OP_UPDATE", e);
				ServletUtility.setErrorMessage("Database Server Down...!! Please try Again", request);
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COUSTMER_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COUSTMER_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("CoustmerCtl doPost ended");
	}

	@Override
	protected String getView() {
		return ORSView.COUSTMER_VIEW;
	}
}
