package com.fpoly.java4.dao;

import com.fpoly.java4.config.EntityManagerConfig;
import com.fpoly.java4.entities.CommentEntity;

import jakarta.persistence.EntityManager;

public class CommentDAO {

	public static boolean insert(CommentEntity commentEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.persist(commentEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
