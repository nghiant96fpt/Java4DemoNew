package com.fpoly.java4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.entities.UserEntity;
import com.fpoly.java4.utils.Constants;
import com.fpoly.java4.utils.Utils;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId = Utils.getCookieByName(req.getCookies(), Constants.Cookie.USER_ID);
		String userRole = Utils.getCookieByName(req.getCookies(), Constants.Cookie.USER_ROLE);

		if (userId != null && userRole != null) {
			if (userRole.equals("0")) {
				resp.sendRedirect(req.getContextPath() + "/users");
				return;
			}

			resp.sendRedirect(req.getContextPath() + "/videos");
			return;
		}

		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		UserEntity userEntity = UserDAO.findByUsername(username);

		if (userEntity == null) {
			req.setAttribute("msg", "Sai tên tài khoản hoặc mật khẩu");
		} else if (!password.equals(userEntity.getPassword())) {
			req.setAttribute("msg", "Sai tên tài khoản hoặc mật khẩu");
		} else {
			req.setAttribute("msg", "Đăng nhập thành công");
//			Đăng nhập thành công lưu user id vào Cookie 
//			name => key, value => giá trị cần lưu 
//			Khởi tạo cookie có key và value tương ứng 
			Cookie cookie = new Cookie(Constants.Cookie.USER_ID, String.valueOf(userEntity.getId()));
//			Cài đặt thời gian hết hạn cho cookie 
//			7 ngày 
			cookie.setMaxAge(60 * 60 * 24 * 7);
//			Lưu cookie vào browser 
			resp.addCookie(cookie);

//			Lưu thêm vai trò của user vào cookie 
			Cookie cookieRole = new Cookie(Constants.Cookie.USER_ROLE, String.valueOf(userEntity.getRole()));
			cookieRole.setMaxAge(60 * 60 * 24 * 7);
			resp.addCookie(cookieRole);

//			Chuyển qua trang danh sách user 
			resp.sendRedirect(req.getContextPath() + "/users");
			return;
		}

		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
}
