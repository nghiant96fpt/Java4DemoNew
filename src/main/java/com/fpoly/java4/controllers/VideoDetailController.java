package com.fpoly.java4.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.VideoEntity;

@WebServlet("/video-detail")
public class VideoDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		id? => query params ở url ?id=122

//		Lấy id từ url ra
//		Sau đó lấy thông tin video từ db thông id lấy được

//		Kiểm tra id có null không? parse id ra kiểu số 

		try {
			String id = req.getParameter("id");
			VideoEntity videoEntity = VideoDAO.findById(Integer.parseInt(id));

			req.setAttribute("video", videoEntity);

		} catch (Exception e) {
			// TODO: handle exception
		}

		req.getRequestDispatcher("/views/video-detail.jsp").forward(req, resp);
	}
}
