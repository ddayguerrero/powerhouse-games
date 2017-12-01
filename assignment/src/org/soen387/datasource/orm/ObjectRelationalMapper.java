package org.soen387.datasource.orm;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Data Mapper for database tables
 * @author Darrel Guerrero
 *
 * @param <T> Corresponding data object
 */
public interface ObjectRelationalMapper<T> {
	
	public T mapRow(ResultSet set);
	public ArrayList<T> mapMultiple(ResultSet set);
}
