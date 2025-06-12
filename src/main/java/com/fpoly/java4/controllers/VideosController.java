package com.fpoly.java4.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.VideoEntity;

@WebServlet("/videos")
public class VideosController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Lấy danh sách video từ db ra thông qua DAO
//		Gửi attribute qua jsp 

		List<VideoEntity> videoEntities = VideoDAO.findAll();
		req.setAttribute("videos", videoEntities);

//		Lấy user id từ Cookie ra 

		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			String userId = null;
			for (Cookie cookie : cookies) {

				if (cookie.getName().equals("user_id")) {
					userId = cookie.getValue();
				}
			}
		}

		req.getRequestDispatcher("/views/videos.jsp").forward(req, resp);
	}
}
