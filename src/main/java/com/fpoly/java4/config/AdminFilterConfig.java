package com.fpoly.java4.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java4.utils.Constants;
import com.fpoly.java4.utils.Utils;

@WebFilter(filterName = "adminFilter", urlPatterns = { "/admin/*" })
public class AdminFilterConfig implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

//		Lấy cookie ra kiểm tra 

//		Nếu tồn tại user id và user role và role == 0
//		Thì cho đi tiếp
//		Ngược lại chuyển về trang login 

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String userId = Utils.getCookieByName(req.getCookies(), Constants.Cookie.USER_ID);
		String userRole = Utils.getCookieByName(req.getCookies(), Constants.Cookie.USER_ROLE);

		if (userId != null && userRole != null && userRole.equals("0")) {
//			Đi đến servlet 
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
