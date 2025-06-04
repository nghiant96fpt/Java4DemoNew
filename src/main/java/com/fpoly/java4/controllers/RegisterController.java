package com.fpoly.java4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java4.beans.UserBean;
import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.entities.UserEntity;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		lấy giá trị của query params key == "id" 

		String id = req.getParameter("id");
//		Nếu thêmg thì id  == null 
//		sử dụng DAO lấy thông tin của UserEntity ra 
		try {
			UserEntity userEntity = UserDAO.findById(Integer.parseInt(id));

//			Để hiển thị entity lên giao diện
//			Chuyển đổi dữ liệu từ Entity thành Bean 
//			Dùng setAttrbute để gửi bean qua jsp 

			UserBean userBean = new UserBean();
			userBean.setId(userEntity.getId());
			userBean.setUsername(userEntity.getUsername());
			userBean.setPassword(userEntity.getPassword());
			userBean.setName(userEntity.getName());
			userBean.setEmail(userEntity.getEmail());

//			Gán thêm 1 params vào Bean 
//			Dùng params mới thêm đó để kiểm tra 

			req.setAttribute("user", userBean);

//			Làm sao để hiển thị chỗ button
//			Nếu có id truyền trên url => Sửa thông tin
//			Ngược lại là => Đăng ký

		} catch (Exception e) {
			// TODO: handle exception
		}

		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		try {

			UserBean userBean = new UserBean();
			BeanUtils.populate(userBean, req.getParameterMap());

			req.setAttribute("user", userBean);

			if (userBean.getErrors().isEmpty()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setUsername(userBean.getUsername());
				userEntity.setPassword(userBean.getPassword());
				userEntity.setName(userBean.getName());
				userEntity.setEmail(userBean.getEmail());
				userEntity.setRole(1);
				userEntity.setStatus(1);

				if (userBean.getId() != 0) {
//					Sửa 
					userEntity.setId(userBean.getId());
					UserDAO.update(userEntity);
				} else {
					UserDAO.insert(userEntity);
				}

				resp.sendRedirect(req.getContextPath() + "/users");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}
}
