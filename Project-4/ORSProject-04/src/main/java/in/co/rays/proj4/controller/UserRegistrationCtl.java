package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends BaseCtl {
	public static final String OP_SIGN_UP = "Sign Up";

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmpassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileno(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setRole_id(RoleBean.STUDENT);

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forword(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		UserModel model = new UserModel();
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Registration Successfull ", request);

			} catch (ApplicationException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.SetErrorMessage("Login Is Already Exiest", request);

			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			ServletUtility.forword(getView(), request, response);
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;
		}

	}

	@Override
	public String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}
