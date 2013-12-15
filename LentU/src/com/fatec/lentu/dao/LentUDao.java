package com.fatec.lentu.dao;

import java.sql.SQLException;
import java.util.List;

public interface LentUDao<T> {

	public void persist(T object ) throws SQLException;
	public List<T> loadAll() throws SQLException;
	public T load(Long id) throws SQLException;
	public boolean delete(T object) throws SQLException;
	public boolean delete(Long id) throws SQLException;
	
}