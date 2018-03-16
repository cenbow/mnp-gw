package miw.sql.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oracle.sql.ARRAY;

public class OracleTypeUtil {
	public static List<String> toStringList(ARRAY oracleObjectArray) throws SQLException {
		List<String> r = new ArrayList<>();
		String[] objArr = (String[]) oracleObjectArray.getArray();
		r.addAll(Arrays.asList(objArr)); // Arrays.asList: Returns a fixed-size list backed by the specified array.
		return r;
	}
}
