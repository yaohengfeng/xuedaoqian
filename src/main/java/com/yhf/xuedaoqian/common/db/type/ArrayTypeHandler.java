package com.yhf.xuedaoqian.common.db.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrayTypeHandler extends BaseTypeHandler<Object[]> {

	private static Logger log = LoggerFactory.getLogger(ArrayTypeHandler.class);

	// 字符串类型
	private static final String TYPE_NAME_VARCHAR = "varchar";

	// int类型
	private static final String TYPE_NAME_INTEGER = "integer";
	
	// long类型
	private static final String TYPE_NAME_LONG = "bigint";

	// 布尔类型
	private static final String TYPE_NAME_BOOLEAN = "boolean";

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
                                    Object[] parameter, JdbcType jdbcType) throws SQLException {

		String typeName = null;

		if (parameter instanceof Integer[]) {
			typeName = TYPE_NAME_INTEGER;
		} else if (parameter instanceof Long[]) {
			typeName = TYPE_NAME_LONG;
		} else if (parameter instanceof String[]) {
			typeName = TYPE_NAME_VARCHAR;
		} else if (parameter instanceof Boolean[]) {
			typeName = TYPE_NAME_BOOLEAN;
		}

		if (typeName == null) {
			log.error("ArrayTypeHandler目前不支持类型为{}的数组", parameter.getClass()
					.getName());
			throw new TypeException(
					"ArrayTypeHandler parameter typeName error, your type is "
							+ parameter.getClass().getName());
		}

		Connection conn = ps.getConnection();
		Array array = conn.createArrayOf(typeName, parameter);
		ps.setArray(i, array);
	}

	@Override
	public Object[] getNullableResult(ResultSet rs, String columnName)
			throws SQLException {

		return getArray(rs.getArray(columnName));
	}

	@Override
	public Object[] getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {

		return getArray(rs.getArray(columnIndex));
	}

	@Override
	public Object[] getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {

		return getArray(cs.getArray(columnIndex));
	}

	/**
	 * 获取数组值
	 * @param array
	 * @return
	 * @author WanWei
	 * @date 2015-10-19 下午7:50:33
	 */
	private Object[] getArray(Array array) {

		if (array == null) {
			return null;
		}

		try {
			return (Object[]) array.getArray();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}
}
