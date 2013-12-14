package com.fatec.lentu.dao;

import java.sql.SQLException;
import java.util.List;

import com.fatec.lentu.model.Pertence;

public interface LentUDao<T> {

	public void persist(T object ) throws SQLException;
	public List<Pertence> loadAll() throws SQLException;
	public Pertence load(Long id) throws SQLException;
	public boolean delete(T object) throws SQLException;
	public boolean delete(Long id) throws SQLException;
	
}
