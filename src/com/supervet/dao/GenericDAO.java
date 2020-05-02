package com.supervet.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public List<T> getAll() throws SQLException, ClassNotFoundException;

	public T getById(int id) throws SQLException, ClassNotFoundException;

	public void insert(T value) throws SQLException, ClassNotFoundException;

	public void edit(T value) throws SQLException, ClassNotFoundException;

	public void delete(int id) throws SQLException, ClassNotFoundException;
	
	public int count() throws SQLException, ClassNotFoundException;
	
	public int lastId() throws SQLException, ClassNotFoundException;
}
