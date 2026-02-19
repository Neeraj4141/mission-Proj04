package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.DepartmentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.DepartmentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "DepartmentCtl", urlPatterns = { "/ctl/DepartmentCtl" })
public class DepartmentCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("departmentcode"))) {
			request.setAttribute("departmentcode",
					PropertyReader.getValue("error.require", "Department Code"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("departmentname"))) {
			request.setAttribute("departmentname",
					PropertyReader.getValue("error.require", "Department Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("headname"))) {
			request.setAttribute("headname",
					PropertyReader.getValue("error.require", "Head Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("departmentstatus"))) {
			request.setAttribute("departmentstatus",
					PropertyReader.getValue("error.require", "Department Status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		DepartmentBean bean = new DepartmentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDepartmentcode(DataUtility.getString(request.getParameter("departmentcode")));
		bean.setDepartmentname(DataUtility.getString(request.getParameter("departmentname")));
		bean.setHeadname(DataUtility.getString(request.getParameter("headname")));
		bean.setDepartmentstatus(DataUtility.getString(request.getParameter("departmentstatus")));

		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		DepartmentModel model = new DepartmentModel();

		if (id > 0) {
			try {
				DepartmentBean bean = model.findByPk(id);
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

		DepartmentModel model = new DepartmentModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			DepartmentBean bean = (DepartmentBean) populateBean(request);

			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Department Code already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			DepartmentBean bean = (DepartmentBean) populateBean(request);
			System.out.println(op);

			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Department Code already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.DEPARTMENT_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.DEPARTMENT_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.DEPARTMENT_VIEW;
	}
}
