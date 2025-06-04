package com.fpoly.java4.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.entities.UserEntity;

@WebServlet("/users")
public class UsersController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<UserEntity> userEntities = UserDAO.findAll();

		req.setAttribute("users", userEntities);

		req.getRequestDispatcher("/views/users.jsp").forward(req, resp);
	}

}
