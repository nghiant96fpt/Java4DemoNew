package com.fpoly.java4.dao;

import java.util.List;

import com.fpoly.java4.config.EntityManagerConfig;
import com.fpoly.java4.entities.VideoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class VideoDAO {
	/*
	 * Danh sách Tìm theo id Thêm Sửa Xoá Tìm danh sách video theo tên hoặc danh mục
	 */

	public static List<VideoEntity> findAll() {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		String sqlQueryNative = "SELECT * FROM videos";
		Query query = entityManager.createNativeQuery(sqlQueryNative, VideoEntity.class);

		return query.getResultList();
	}

	public static VideoEntity findById(int id) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();
		VideoEntity videoEntity = entityManager.find(VideoEntity.class, id);

		return videoEntity;
	}

	public static boolean insert(VideoEntity videoEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.persist(videoEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean update(VideoEntity videoEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.merge(videoEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean delete(VideoEntity videoEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.remove(videoEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static List<VideoEntity> findByNameOrCatId(String name, int catId) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

//		trim => Loại bỏ khoảng trắng dư thừa
//		split=> Chuyển chuỗi về thành mảng qua 1 đoạn nội dung được cắt ra 
		String[] searchArr = name.trim().split(" ");

//		Cách 1:
//		String sql = "SELECT * FROM videos WHERE";
//		for (String search : searchArr) {
//			sql += " name LIKE '%" + search + "%' OR";
//		}
//		sql += " cat_id=:catId";
//		Query query = entityManager.createNativeQuery(sql, VideoEntity.class);
//		query.setParameter("catId", catId);

//		Cách 2:
		String sql = "SELECT * FROM videos WHERE";
		for (int index = 0; index < searchArr.length; index++) {
			sql += " name LIKE :name" + index + " OR";
		}

//		SELECT * FROM videos WHERE name LIKE :name0 OR name LIKE :name1...

		sql += " cat_id=:catId";
		Query query = entityManager.createNativeQuery(sql, VideoEntity.class);
		query.setParameter("catId", catId);
		for (int index = 0; index < searchArr.length; index++) {
			query.setParameter("name" + index, "%" + searchArr[index] + "%");
		}

		return query.getResultList();
	}
//	bài Lab 1 lập trình java 4

//	lập trình java 4: TH1
//	lab 1 java 4: TH2

// Laptop dell RAM 16 ROM 32 => col name

//	Search => dell ROM 32 RAM 16 => chuyển chuỗi này thành mảng ["dell", "ROM",...]
// duyệt mảng nối vào lệnh sql 

//	SQL WHERE name like %dell% or name like %ROM% .......
}
