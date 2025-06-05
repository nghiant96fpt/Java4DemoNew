package com.fpoly.java4.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java4.beans.VideoBean;
import com.fpoly.java4.dao.CategoryDAO;
import com.fpoly.java4.entities.CategoryEntity;

@MultipartConfig()
@WebServlet("/video-form")
public class VideoFormController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Lay danh sach danh muc tu db thong DAO
//		Gui qua jsp bang setAtribute => key

		List<CategoryEntity> categoryEntities = CategoryDAO.findAll();
		req.setAttribute("categories", categoryEntities);

		req.getRequestDispatcher("/views/video-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		List<CategoryEntity> categoryEntities = CategoryDAO.findAll();
		req.setAttribute("categories", categoryEntities);

		try {
			VideoBean videoBean = new VideoBean();
//			populate của bean chỉ parse được dữ liệu kiểu chuỗi và số 
			BeanUtils.populate(videoBean, req.getParameterMap());

			videoBean.setImage(req.getPart("image"));

			req.setAttribute("video", videoBean);

		} catch (Exception e) {
			// TODO: handle exception
		}

//		Đặt tên cho các ô input và dropdown 
//		Tạo ra class Bean tương ứng với form thêm video 
//		Trong class Bean thực hiện validate bên trong hàm getErrors 
//		- Tên bắt buộc nhập
//		- Mô tả phải có ít nhất 15 từ 
//		- Bắt buộc chọn ảnh và kích thước ảnh không quá 50kb 
//		- URL phải đúng định dạng
//		- Danh mục bắt buộc chọn 

//		Hiển thị lỗi và nội dung user vừa nhập sau khi ấn submit 

		req.getRequestDispatcher("/views/video-form.jsp").forward(req, resp);
	}
}
