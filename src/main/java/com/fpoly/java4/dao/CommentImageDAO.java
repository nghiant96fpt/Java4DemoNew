package com.fpoly.java4.dao;

import com.fpoly.java4.config.EntityManagerConfig;
import com.fpoly.java4.entities.CommentImageEntity;

import jakarta.persistence.EntityManager;

public class CommentImageDAO {

	public static boolean insert(CommentImageEntity imageEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.persist(imageEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
