/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.data.jdbc.support.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import cat.mnp.om.dao.CatOmBaseMsgDao;

/**
 *
 * @author HP-CAT
 */
public class ComplexSqlStructArrayValue<T> extends AbstractSqlTypeValue {

	private T[] values;
	/**
	 * The object that will do the mapping *
	 */
	private StructMapper<T> mapper;
	/**
	 * The type name of the STRUCT *
	 */
	private String structTypeName;
	/**
	 * The type name of the ARRAY *
	 */
	private String arrayTypeName;

	/**
	 * Constructor that takes a parameter with the array of values passed in to
	 * the statement, a parameter with the {@link StructMapper} to be used plus
	 * the type name of the STRUCT that the array will contain.
	 *
	 * @param values the array containing the values
	 * @param mapper the mapper to create the STRUCT values
	 * @param structTypeName the type name of the STRUCT.
	 */
	public ComplexSqlStructArrayValue(T[] values, StructMapper<T> mapper, String structTypeName) {
		this.values = values;
		this.mapper = mapper;
		this.structTypeName = structTypeName;
	}

	public ComplexSqlStructArrayValue(T value, StructMapper<T> mapper, String structTypeName) {
		this((T[]) Collections.singletonList(value).toArray(), mapper, structTypeName);
	}

	public ComplexSqlStructArrayValue(List<T> valueList, StructMapper<T> mapper, String structTypeName) {
		this((T[]) valueList.toArray(), mapper, structTypeName);
	}

	/**
	 * Constructor that takes a parameter with the array of values passed in to
	 * the statement, a parameter with the {@link StructMapper} to be used plus
	 * the type name of the STRUCT that the array will contain.
	 *
	 * @param values the array containing the values
	 * @param mapper the mapper to create the STRUCT values
	 * @param structTypeName the type name of the STRUCT.
	 * @param arrayTypeName the type name of the ARRAY when this class is used
	 * in a context where the name of the array type is not known.
	 */
	public ComplexSqlStructArrayValue(T[] values, StructMapper<T> mapper, String structTypeName, String arrayTypeName) {
		this(values, mapper, structTypeName);
		this.arrayTypeName = arrayTypeName;
	}

	public ComplexSqlStructArrayValue(T value, StructMapper<T> mapper, String structTypeName, String arrayTypeName) {
		this((T[]) Collections.singletonList(value).toArray(), mapper, structTypeName, arrayTypeName);
	}

	public ComplexSqlStructArrayValue(List<T> valueList, StructMapper<T> mapper, String structTypeName, String arrayTypeName) {
		this((T[]) valueList.toArray(), mapper, structTypeName, arrayTypeName);
	}

	/**
	 * The implementation for this specific type. This method is called
	 * internally by the Spring Framework during the out parameter processing
	 * and it's not accessed by application code directly.
	 *
	 * @see org.springframework.jdbc.core.support.AbstractSqlTypeValue
	 */
	@Override
	public Object createTypeValue(Connection conn, int sqlType, String typeName) throws SQLException {
		if (typeName == null && arrayTypeName == null) {
			throw new InvalidDataAccessApiUsageException("The typeName for the array is null in this context. Consider setting the arrayTypeName.");
		}
		ArrayDescriptor arrayDescriptor = new ArrayDescriptor(typeName != null ? typeName : arrayTypeName, conn);
		STRUCT[] structValues = new STRUCT[values.length];
		for (int i = 0; i < values.length; i++) {
			structValues[i] = mapper.toStruct(values[i], conn, structTypeName);

			// TODO: MIW Debug Sql
			String caller = Thread.currentThread().getStackTrace()[2].getClassName();
			if ("org.springframework.jdbc.core.support.AbstractSqlTypeValue".equals(caller)) {
				logger.warn("Debug Param:\n" + structValues[i].dump());
				// Thread.currentThread().dumpStack();
			}
		}
		ARRAY array = new ARRAY(arrayDescriptor, conn, structValues);
		return array;
	}
	private static final Logger logger = LoggerFactory.getLogger(ComplexSqlStructArrayValue.class);
}
