package com.fpoly.java4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java4.utils.Constants;
import com.fpoly.java4.utils.Utils;

@WebServlet("/add-comment")
public class CommentController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Lấy cookie user_id ra 
//		Kiểm tra nếu không tồn tại user_id == null
//		=> thực hiện chuyển về trang đăng nhập 

		String userId = Utils.getCookieByName(req.getCookies(), Constants.Cookie.USER_ID);
//		Chưa đăng nhập
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
	}
}
