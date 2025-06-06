package com.fpoly.java4.controllers;

import java.io.File;
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
import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.CategoryEntity;
import com.fpoly.java4.entities.VideoEntity;

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

			if (videoBean.getErrors().isEmpty()) {
				String contextPath = req.getServletContext().getRealPath("");
				String assetsPath = contextPath + File.separator + "assets" + File.separator + "images";
				File file = new File(assetsPath);
				if (!file.exists()) {
					file.mkdir();
				}

				String fileName = System.currentTimeMillis() + "."
						+ videoBean.getImage().getContentType().split("/")[1];
				videoBean.getImage().write(assetsPath + File.separator + fileName);

//				Convert Bean to Entity 
				VideoEntity videoEntity = new VideoEntity();
				videoEntity.setName(videoBean.getName());
				videoEntity.setDesc(videoBean.getDesc());
				videoEntity.setVideoURL(videoBean.getUrl());
				videoEntity.setImage(fileName);
				videoEntity.setViewCount(0);
				videoEntity.setStatus(0);
				CategoryEntity categoryEntity = CategoryDAO.findById(videoBean.getCategory());
				videoEntity.setCategoryEntity(categoryEntity);
				VideoDAO.insert(videoEntity);

				resp.sendRedirect(req.getContextPath() + "/videos");
				return;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		req.getRequestDispatcher("/views/video-form.jsp").forward(req, resp);
	}
}
