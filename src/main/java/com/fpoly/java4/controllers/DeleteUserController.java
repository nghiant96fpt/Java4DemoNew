package com.fpoly.java4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.entities.UserEntity;

@WebServlet("/delete-user")
public class DeleteUserController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			lấy được giá trị của id từ form gửi qua 
			String id = req.getParameter("id");

//			Lấy thông tin user entity từ db 
			UserEntity userEntity = UserDAO.findById(Integer.parseInt(id));

//			Xoá user ở db 
			UserDAO.delete(userEntity);
		} catch (Exception e) {
			// TODO: handle exception
		}

		resp.sendRedirect(req.getContextPath() + "/users");
	}
}
