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
@WebServlet("/admin/video-form")
public class VideoFormController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Lay danh sach danh muc tu db thong DAO
//		Gui qua jsp bang setAtribute => key

		List<CategoryEntity> categoryEntities = CategoryDAO.findAll();
		req.setAttribute("categories", categoryEntities);

		try {
			String id = req.getParameter("id");
			VideoEntity videoEntity = VideoDAO.findById(Integer.parseInt(id));

			VideoBean videoBean = new VideoBean();
			videoBean.setId(videoEntity.getId());
			videoBean.setName(videoEntity.getName());
			videoBean.setDesc(videoEntity.getDesc());
			videoBean.setUrl(videoEntity.getVideoURL());
			videoBean.setCategory(videoEntity.getCategoryEntity().getId());
			videoBean.setStatus(videoEntity.getStatus());

			req.setAttribute("video", videoBean);

		} catch (Exception e) {
			// TODO: handle exception
		}

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
			BeanUtils.populate(videoBean, req.getParameterMap());
			videoBean.setImage(req.getPart("image"));
			req.setAttribute("video", videoBean);

			if (videoBean.getErrors().isEmpty()) {
				String fileName = "";
				if (videoBean.getId() == 0 || videoBean.getImage() != null) {
					String contextPath = req.getServletContext().getRealPath("");
					String assetsPath = contextPath + File.separator + "assets" + File.separator + "images";
					File file = new File(assetsPath);
					if (!file.exists()) {
						file.mkdir();
					}

					fileName = System.currentTimeMillis() + "." + videoBean.getImage().getContentType().split("/")[1];
					videoBean.getImage().write(assetsPath + File.separator + fileName);
				}

				VideoEntity videoEntity = new VideoEntity();
				videoEntity.setName(videoBean.getName());
				videoEntity.setDesc(videoBean.getDesc());
				videoEntity.setVideoURL(videoBean.getUrl());
//				Nếu có lưu ảnh sẽ tên ảnh mới
//				Nếu không lưu thì tên ảnh từ DB 
				if (videoBean.getId() == 0) {
					videoEntity.setImage(fileName);
				} else {
					if (!fileName.equals("")) {
						videoEntity.setImage(fileName);
					} else {
						VideoEntity videoEntityDB = VideoDAO.findById(videoBean.getId());
						videoEntity.setImage(videoEntityDB.getImage());
					}
				}
				videoEntity.setViewCount(0);
				videoEntity.setStatus(0);
				CategoryEntity categoryEntity = CategoryDAO.findById(videoBean.getCategory());
				videoEntity.setCategoryEntity(categoryEntity);

				if (videoBean.getId() != 0) {
					videoEntity.setStatus(videoBean.getStatus());
					videoEntity.setId(videoBean.getId());
					VideoDAO.update(videoEntity);
				} else {
					VideoDAO.insert(videoEntity);
				}

				resp.sendRedirect(req.getContextPath() + "/videos");
				return;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		req.getRequestDispatcher("/views/video-form.jsp").forward(req, resp);
	}
}
