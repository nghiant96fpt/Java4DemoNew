package com.fpoly.java4.dao;

import com.fpoly.java4.config.EntityManagerConfig;
import com.fpoly.java4.entities.FavouriteEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class FavouriteDAO {
//	public static boolean insert() {
//
//	}
//
//	public static boolean delete() {
//
//	}

	public static FavouriteEntity checkUserFavVideo(int userId, int videoId) {

//		Tìm 1 dòng trong bảng yêu thích có id user và id video 
//		Nếu có => user có yêu thích video hiện tại 
//		Ngược lại không 

		try {
			EntityManager entityManager = EntityManagerConfig.getEntityManager();
			String sql = "SELECT * FROM favourites WHERE user_id=:userId AND video_id=:videoId";

			Query query = entityManager.createNativeQuery(sql, FavouriteEntity.class);
			query.setParameter("userId", userId);
			query.setParameter("videoId", videoId);
			return (FavouriteEntity) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
