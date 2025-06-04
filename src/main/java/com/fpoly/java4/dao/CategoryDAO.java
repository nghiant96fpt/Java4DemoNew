package com.fpoly.java4.dao;

import java.util.List;

import com.fpoly.java4.config.EntityManagerConfig;
import com.fpoly.java4.entities.CategoryEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class CategoryDAO {
	public static List<CategoryEntity> findAll() {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		String sql = "SELECT * FROM categories";

		Query query = entityManager.createNativeQuery(sql, CategoryEntity.class);

		return query.getResultList();
	}

	public static CategoryEntity findById(int id) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		CategoryEntity categoryEntity = entityManager.find(CategoryEntity.class, id);

		return categoryEntity;
	}

	public static boolean insert(CategoryEntity categoryEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		try {
			if (!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}

			entityManager.persist(categoryEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}

		return true;
	}

	public static boolean update(CategoryEntity categoryEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		try {
			if (!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}

			entityManager.merge(categoryEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}

		return true;
	}

	public static boolean delete(CategoryEntity categoryEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		try {
			if (!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}

			entityManager.remove(categoryEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}

		return true;
	}
}
