package miw.sql.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class OracleArrayValue<T> extends AbstractSqlTypeValue {
	private List<T> values;

	public OracleArrayValue(List<T> values) {
		this.values = values;
	}

	public Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {
		oracle.jdbc.OracleConnection wrappedConnection = con.unwrap(oracle.jdbc.OracleConnection.class);
		con = wrappedConnection;
		ArrayDescriptor desc = new ArrayDescriptor(typeName, con);
		return new ARRAY(desc, con, (T[]) values.toArray(new Object[values.size()])); // TODO: need verify
	}
}
