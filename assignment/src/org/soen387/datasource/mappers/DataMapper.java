package org.soen387.datasource.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Data Mapper for database tables
 * @author Darrel Guerrero
 *
 * @param <T> Corresponding data object
 */
public interface DataMapper<T> {
	
	public T mapRow(ResultSet set);
	public ArrayList<T> mapMultiple(ResultSet set);
}
