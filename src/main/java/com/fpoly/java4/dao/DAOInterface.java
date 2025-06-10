package com.fpoly.java4.dao;

import java.util.List;

public interface DAOInterface<T> {
	public List<T> findAll();

	public T findById();
}
