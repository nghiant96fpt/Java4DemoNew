package com.fpoly.java4.dao;

import java.util.List;

import com.fpoly.java4.config.EntityManagerConfig;
import com.fpoly.java4.entities.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class UserDAO {
	public static List<UserEntity> findAll() {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		String sqlQueryNative = "SELECT * FROM users";
		Query query = entityManager.createNativeQuery(sqlQueryNative, UserEntity.class);

		return query.getResultList();
	}

	public static UserEntity findByUsername(String username) {
		try {
			EntityManager entityManager = EntityManagerConfig.getEntityManager();

			String sqlQuery = "SELECT * FROM users WHERE username='" + username + "'";

			Query query = entityManager.createNativeQuery(sqlQuery, UserEntity.class);

			return (UserEntity) query.getSingleResult();
		} catch (Exception e) {

			return null;
		}
	}

	public static UserEntity findById(int id) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();
		UserEntity userEntity = entityManager.find(UserEntity.class, id);

		return userEntity;
	}

	public static boolean insert(UserEntity userEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.persist(userEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean update(UserEntity userEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.merge(userEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean delete(UserEntity userEntity) {
		EntityManager entityManager = EntityManagerConfig.getEntityManager();

		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		try {
			entityManager.remove(userEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
