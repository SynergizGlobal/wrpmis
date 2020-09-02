package com.synergizglobal.pmis.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.util.StringUtils;

public class CommonMethods {
	public static boolean queryLogger(QyeryLogger obj,Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			String qry = "insert into query_logger(form_name,`table_name`,query_type_id_fk,data_fields,where_condition,created_by)values(?,?,?,?,?,?)";
			
			statement = connection.prepareStatement(qry);
			int c = 1;
			statement.setString(c++, !StringUtils.isEmpty(obj.getFormName())?obj.getFormName():null);
			statement.setString(c++, !StringUtils.isEmpty(obj.getTableName())?obj.getTableName():null);
			statement.setString(c++, !StringUtils.isEmpty(obj.getQueryTypeId())?obj.getQueryTypeId():null);
			statement.setString(c++, !StringUtils.isEmpty(obj.getDataFields())?obj.getDataFields():null);
			statement.setString(c++, !StringUtils.isEmpty(obj.getWhereCondition())?obj.getWhereCondition():null);
			statement.setString(c++, !StringUtils.isEmpty(obj.getCreatedBy())?obj.getCreatedBy():null);
			
			int count = statement.executeUpdate();
			if(count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return flag;
	}
	
	public static boolean queryLoggerBatch(List<QyeryLogger> objList,Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			String qry = "insert into query_logger(form_name,`table_name`,query_type_id_fk,data_fields,where_condition,created_by)values(?,?,?,?,?,?)";
			
			statement = connection.prepareStatement(qry);
			for (QyeryLogger obj : objList) {
				int c = 1;
				statement.setString(c++, !StringUtils.isEmpty(obj.getFormName())?obj.getFormName():null);
				statement.setString(c++, !StringUtils.isEmpty(obj.getTableName())?obj.getTableName():null);
				statement.setString(c++, !StringUtils.isEmpty(obj.getQueryTypeId())?obj.getQueryTypeId():null);
				statement.setString(c++, !StringUtils.isEmpty(obj.getDataFields())?obj.getDataFields():null);
				statement.setString(c++, !StringUtils.isEmpty(obj.getWhereCondition())?obj.getWhereCondition():null);
				statement.setString(c++, !StringUtils.isEmpty(obj.getCreatedBy())?obj.getCreatedBy():null);
				statement.addBatch();
			}
			
			int[] c = statement.executeBatch();
			if(c.length > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return flag;
	}
}
