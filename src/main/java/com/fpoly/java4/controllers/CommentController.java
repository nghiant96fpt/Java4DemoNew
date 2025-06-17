package com.fpoly.java4.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fpoly.java4.dao.CommentDAO;
import com.fpoly.java4.dao.CommentImageDAO;
import com.fpoly.java4.dao.UserDAO;
import com.fpoly.java4.dao.VideoDAO;
import com.fpoly.java4.entities.CommentEntity;
import com.fpoly.java4.entities.CommentImageEntity;
import com.fpoly.java4.entities.UserEntity;
import com.fpoly.java4.entities.VideoEntity;
import com.fpoly.java4.utils.Constants;
import com.fpoly.java4.utils.Utils;

@MultipartConfig()
@WebServlet("/add-comment")
public class CommentController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		// Lấy cookie user_id ra
//		Kiểm tra nếu không tồn tại user_id == null
//		=> thực hiện chuyển về trang đăng nhập 

		String userId = Utils.getCookieByName(req.getCookies(), Constants.Cookie.USER_ID);
//		Chưa đăng nhập
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
//		Thực hiện lấy các dữ liệu từ form truyền qua: video_id, content, image 

		String videoID = req.getParameter("videoId");
		String content = req.getParameter("content");
		Collection<Part> parts = req.getParts();

//		Thực hiện lưu ảnh vào project 

//		mảng để lưu tên tất cả hình ảnh đã được lưu vào project 
		ArrayList<String> imageNameList = new ArrayList<String>();

		for (Part part : parts) {
			if (part.getContentType() != null && part.getContentType().startsWith("image/")) {
//				Lưu ảnh
				String contextPath = req.getServletContext().getRealPath("");
				String assetsPath = contextPath + File.separator + "assets" + File.separator + "images";
				File file = new File(assetsPath);
				if (!file.exists()) {
					file.mkdir();
				}

				String fileName = System.currentTimeMillis() + "." + part.getContentType().split("/")[1];
				part.write(assetsPath + File.separator + fileName);
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO: handle exception
				}
				imageNameList.add(fileName);
			}
		}

//		Lấy được user entity và video entity ?

		try {
//			lấy entity từ DB thông qua id 
			UserEntity userEntity = UserDAO.findById(Integer.parseInt(userId));
			VideoEntity videoEntity = VideoDAO.findById(Integer.parseInt(videoID));

//			Gán giá trị vào entity 
			CommentEntity commentEntity = new CommentEntity();
			commentEntity.setContent(content);
			commentEntity.setUserEntity(userEntity);
			commentEntity.setVideoEntity(videoEntity);

			CommentDAO.insert(commentEntity); // => true || false
//			làm sao lấy ra comment entity mới nhất vừa được thêm để thêm ảnh ?

			CommentEntity commentEntityNew = CommentDAO.getNewComment();

//			for duyệt qua tất cả tên hình ảnh đã lưu
//			Sau đó lưu từng tên ảnh tương ứng vào db với comment Entity
//			vừa tìm 

			for (String imageName : imageNameList) {
				CommentImageEntity commentImageEntity = new CommentImageEntity();
				commentImageEntity.setImage(imageName);
				commentImageEntity.setCommentEntity(commentEntityNew);

				CommentImageDAO.insert(commentImageEntity);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		resp.sendRedirect(req.getContextPath() + "/video-detail?id=" + videoID);
	}
}
