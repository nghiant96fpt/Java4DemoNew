package com.fpoly.java4.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerConfig {
	private static EntityManager manager;

	public static EntityManager getEntityManager() {

		if (manager == null) {
			EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");
			manager = managerFactory.createEntityManager();
		}

		return manager;
	}
}
