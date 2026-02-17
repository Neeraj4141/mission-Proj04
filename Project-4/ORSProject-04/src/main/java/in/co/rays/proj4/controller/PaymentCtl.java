package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.PaymentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.PaymentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "PaymentCtl", urlPatterns = { "/ctl/PaymentCtl" })
public class PaymentCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(PaymentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("PaymentCtl preload started");
		log.debug("PaymentCtl preload ended");
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("PaymentCtl validate started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("bookingId"))) {
			request.setAttribute("bookingId",
					PropertyReader.getValue("error.require", "Booking Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount",
					PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("paymentMode"))) {
			request.setAttribute("paymentMode",
					PropertyReader.getValue("error.require", "Payment Mode"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("paymentStatus"))) {
			request.setAttribute("paymentStatus",
					PropertyReader.getValue("error.require", "Payment Status"));
			pass = false;
		}

		log.debug("PaymentCtl validate ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("PaymentCtl populateBean started");

		PaymentBean bean = new PaymentBean();

		bean.setPaymentId(DataUtility.getLong(request.getParameter("paymentId")));
		bean.setBookingId(DataUtility.getLong(request.getParameter("bookingId")));
		bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
		bean.setPaymentMode(DataUtility.getString(request.getParameter("paymentMode")));
		bean.setPaymentStatus(DataUtility.getString(request.getParameter("paymentStatus")));

		populateDTO(bean, request);

		log.debug("PaymentCtl populateBean ended");
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("PaymentCtl doGet started");

		long id = DataUtility.getLong(request.getParameter("id"));
		PaymentModel model = new PaymentModel();

		if (id > 0) {
			try {
				PaymentBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error("Error in doGet", e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("PaymentCtl doGet ended");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("PaymentCtl doPost started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		PaymentModel model = new PaymentModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {

			PaymentBean bean = (PaymentBean) populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Payment added successfully", request);

			} catch (ApplicationException e) {
				log.error("Error in OP_SAVE", e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			PaymentBean bean = (PaymentBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Payment updated successfully", request);

			} catch (ApplicationException e) {
				log.error("Error in OP_UPDATE", e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PAYMENT_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PAYMENT_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("PaymentCtl doPost ended");
	}

	@Override
	protected String getView() {
		log.debug("PaymentCtl getView called");
		return ORSView.PAYMENT_VIEW;
	}
}
