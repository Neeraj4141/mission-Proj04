package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.SalaryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.SalaryModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "SalaryCtl", urlPatterns = { "/ctl/SalaryCtl" })
public class SalaryCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("salaryslipcode"))) {
			request.setAttribute("salaryslipcode",
					PropertyReader.getValue("error.require", "Salary Slip Code"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("employeename"))) {
			request.setAttribute("employeename",
					PropertyReader.getValue("error.require", "Employee Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("basicsalary"))) {
			request.setAttribute("basicsalary",
					PropertyReader.getValue("error.require", "Basic Salary"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("bonus"))) {
			request.setAttribute("bonus",
					PropertyReader.getValue("error.require", "Bonus"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("salarydate"))) {
			request.setAttribute("salarydate",
					PropertyReader.getValue("error.require", "Salary Date"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		SalaryBean bean = new SalaryBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSalaryslipcode(DataUtility.getString(request.getParameter("salaryslipcode")));
		bean.setEmployeename(DataUtility.getString(request.getParameter("employeename")));
		bean.setBasicsalary(DataUtility.getString(request.getParameter("basicsalary")));
		bean.setBonus(DataUtility.getString(request.getParameter("bonus")));
		bean.setSalarydate(DataUtility.getDate(request.getParameter("salarydate")));

		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		SalaryModel model = new SalaryModel();

		if (id > 0) {
			try {
				SalaryBean bean = model.findByPk(id);
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

		SalaryModel model = new SalaryModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			SalaryBean bean = (SalaryBean) populateBean(request);

			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Salary Slip Code already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			SalaryBean bean = (SalaryBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Salary Slip Code already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SALARY_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SALARY_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.SALARY_VIEW;
	}
}