package com.op.bootfaces.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getUserInfo() {
		HttpSession session = getSession();
		return session != null ? (String) session.getAttribute("user") : null;
	}

	public static String getUserRole() {
		HttpSession session = getSession();
		return session != null ? (String) session.getAttribute("role") : null;
	}
}
