package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.HospitelBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.HospitelModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "HospitelCtl", urlPatterns = { "/ctl/HospitelCtl" })
public class HospitelCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(HospitelCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("HospitelCtl validate started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("hospitelcode"))) {
			request.setAttribute("hospitelcode",
					PropertyReader.getValue("error.require", "Hospitel Code"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("hospitelname"))) {
			request.setAttribute("hospitelname",
					PropertyReader.getValue("error.require", "Hospitel Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("hospitelname"))) {
			request.setAttribute("hospitelname", "Invalid Hospitel Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactnumber"))) {
			request.setAttribute("contactnumber",
					PropertyReader.getValue("error.require", "Contact Number"));
			pass = false;
		}

		log.debug("HospitelCtl validate ended");

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("HospitelCtl populateBean started");

		HospitelBean bean = new HospitelBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setHospitelcode(DataUtility.getString(request.getParameter("hospitelcode")));
		bean.setHospitelname(DataUtility.getString(request.getParameter("hospitelname")));
		bean.setContactnumber(DataUtility.getString(request.getParameter("contactnumber")));

		populateDTO(bean, request);

		log.debug("HospitelCtl populateBean ended");

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("HospitelCtl doGet started");

		long id = DataUtility.getLong(request.getParameter("id"));

		HospitelModel model = new HospitelModel();

		if (id > 0) {
			try {
				HospitelBean bean = model.findbypk(id);

				System.out.println(id);

				ServletUtility.setBean(bean, request);

			} catch (ApplicationException e) {

				log.error("Error in doGet", e);

				// ServletUtility.handleException(e, request, response);

				ServletUtility.setErrorMessage(
						"Database Server Down...!! Please try Again", request);
			}
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("HospitelCtl doGet ended");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("HospitelCtl doPost started");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		HospitelModel model = new HospitelModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {

			HospitelBean bean = (HospitelBean) populateBean(request);

			try {

				model.add(bean);

				ServletUtility.setBean(bean, request);

				ServletUtility.setSuccessMessage("Hospitel added successfully", request);

			} catch (ApplicationException e) {

				log.error("Error in OP_SAVE", e);

				ServletUtility.handleException(e, request, response);

				return;

			} catch (DuplicateRecordException e) {

				ServletUtility.setBean(bean, request);

				ServletUtility.setErrorMessage("Hospitel Code already exists", request);
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			HospitelBean bean = (HospitelBean) populateBean(request);

			try {

				if (id > 0) {
					model.update(bean);
				}

				ServletUtility.setBean(bean, request);

				ServletUtility.setSuccessMessage("Hospitel updated successfully", request);

			} catch (ApplicationException e) {

				log.error("Error in OP_UPDATE", e);

				ServletUtility.setErrorMessage(
						"Database Server Down...!! Please try Again", request);

			} catch (DuplicateRecordException e) {

				ServletUtility.setBean(bean, request);

				ServletUtility.setErrorMessage("Hospitel Code already exists", request);
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.HOSPITEL_LIST_CTL, request, response);

			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.HOSPITEL_CTL, request, response);

			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("HospitelCtl doPost ended");
	}

	@Override
	protected String getView() {

		return ORSView.HOSPITEL_VIEW;
	}
}