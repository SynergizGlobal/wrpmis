package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.reference.Idao.RiskAreaDao;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Repository
public class RiskAreaDaoImpl implements RiskAreaDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Risk> getRiskAreasList() throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select area,area_short_name, item_no from risk_area ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addRiskArea(Risk obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO risk_area"
					+ "( area,area_short_name, item_no) VALUES (:area,:area_short_name, :item_no)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public TrainingType getRiskAreaDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		List<TrainingType> objsList1 = null;
		TrainingType sObj =null;
		try {
			String qry ="select area,area_short_name, item_no from risk_area order by item_no ";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
			obj.setdList1(objsList);
			
			List<TrainingType> tablesList = getTablesList(obj);
			obj.setTablesList(tablesList);
			if(tablesList.size() > 0) {
				List<TrainingType> list = getDataDetails( obj);
				obj.setdList(list);
				String qry1 = "";
				int i = 1;
				for (TrainingType bObj : obj.getdList()) {
					
					qry1 = qry1 +"select "+bObj.getColumn_name()+" as area,count("+bObj.getColumn_name()+") as count,'"+bObj.getTable_name()+"' as tName from "+bObj.getTable_name()+" where "+bObj.getColumn_name()+" <> '' group by "+bObj.getColumn_name()+"  ";
					if( list.size() >  i) {
						qry1 = qry1 + " UNION ";
						i++;
					}
				}
				objsList1 = jdbcTemplate.query( qry1, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));
				obj.setdList(objsList1);
				obj.setCountList(objsList1);
				if(objsList1.size() > 0) {
					Object[] pValues  = new Object[objsList1.size()];
					  String qry2 = "select area,area_short_name, item_no from risk_area where area NOT IN (?";
	
						int j =0, p=1;
						for (TrainingType aObj : obj.getdList()) {
							pValues[j++] = aObj.getArea();
							if( objsList1.size() >  p) {
								qry2 = qry2 + ",?";
								p++;
							}
						}
						qry2 = qry2 + ")";
						objsList1 = jdbcTemplate.query( qry2,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));
						obj.setdList(objsList1);
				}else {
					 obj.setdList(objsList);
				}
			}else {
				obj.setdList(objsList);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return obj;
	}
	
	private List<TrainingType> getTablesList(TrainingType obj) throws Exception {
		List<TrainingType> tablesList = null;
		try {
			String qry = "select t1.name as tname,(select c2.name from sys.columns c2 where c2.object_id=t1.object_id\r\n" + 
					"and c2.name like CONCAT('%',(SELECT c.name FROM\r\n" + 
					"sys.columns c\r\n" + 
					"left join sys.tables t on t.object_id = c.object_id\r\n" + 
					"left join sys.schemas s on s.schema_id = t.schema_id\r\n" + 
					"WHERE t.name = 'risk_area' and s.name = 'dbo'),'%')) as COLUMN_NAME,'' as CONSTRAINT_NAME,\r\n" + 
					"'risk_area' as REFERENCED_TABLE_NAME,\r\n" + 
					"(select c2.name from sys.columns c2 where c2.object_id = (select object_id from sys.tables t2\r\n" + 
					"left join sys.schemas s2 on s2.schema_id = t2.schema_id\r\n" + 
					"WHERE t2.name = 'risk_area' and s2.name = 'dbo')) as REFERENCED_COLUMN_NAME\r\n" + 
					"from sys.tables t1 where t1.object_id in\r\n" + 
					"(select c1.object_id from sys.columns c1 where c1.name like CONCAT('%',(SELECT c.name FROM\r\n" + 
					"sys.columns c\r\n" + 
					"left join sys.tables t on t.object_id = c.object_id\r\n" + 
					"left join sys.schemas s on s.schema_id = t.schema_id\r\n" + 
					"WHERE t.name = 'risk_area' and s.name = 'dbo'),'%'))\r\n" + 
					"and t1.name !=  'risk_area'";
			
			tablesList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
			for(TrainingType tName : tablesList) {
				 String name = tName.gettName();
				 if(!name.equals("risk")) {
					 String result = name.split("risk_")[1];
					 name = result.replaceAll("[_]", " ");  
				 }
				 String regex = "\\b(.)(.*?)\\b";
			     String captilizedName = Pattern.compile(regex).matcher(name).replaceAll(
			            matche -> matche.group(1).toUpperCase() + matche.group(2)
			     );
			     tName.setCaptiliszedTableName(captilizedName);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return tablesList;
	}


	private List<TrainingType> getDataDetails(TrainingType obj) throws Exception {
		List<TrainingType> list = null;
		try {
			String qry = "select t1.name as tname,(select c2.name from sys.columns c2 where c2.object_id=t1.object_id\r\n" + 
					"and c2.name like CONCAT('%',(SELECT c.name FROM\r\n" + 
					"sys.columns c\r\n" + 
					"left join sys.tables t on t.object_id = c.object_id\r\n" + 
					"left join sys.schemas s on s.schema_id = t.schema_id\r\n" + 
					"WHERE t.name = 'risk_area' and s.name = 'dbo'),'%')) as COLUMN_NAME,'' as CONSTRAINT_NAME,\r\n" + 
					"'risk_area' as REFERENCED_TABLE_NAME,\r\n" + 
					"(select c2.name from sys.columns c2 where c2.object_id = (select object_id from sys.tables t2\r\n" + 
					"left join sys.schemas s2 on s2.schema_id = t2.schema_id\r\n" + 
					"WHERE t2.name = 'risk_area' and s2.name = 'dbo')) as REFERENCED_COLUMN_NAME\r\n" + 
					"from sys.tables t1 where t1.object_id in\r\n" + 
					"(select c1.object_id from sys.columns c1 where c1.name like CONCAT('%',(SELECT c.name FROM\r\n" + 
					"sys.columns c\r\n" + 
					"left join sys.tables t on t.object_id = c.object_id\r\n" + 
					"left join sys.schemas s on s.schema_id = t.schema_id\r\n" + 
					"WHERE t.name = 'risk_area' and s.name = 'dbo'),'%'))\r\n" + 
					"and t1.name !=  'risk_area' ";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}




	@Override
	public boolean updateRiskArea(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			List<TrainingType> tablesList = getTablesList(obj);
			List<TrainingType> list = getDataDetails(obj);
			obj.setdList(list);

			String disableQry = "SET foreign_key_checks = 0 ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(disableQry, paramSource);	
			
			String  updatereferenceTableQry = "UPDATE risk_area SET area= :value_new,area_short_name= :area_short_name_new,item_no= :item_no_new WHERE area= :value_old " ;
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(updatereferenceTableQry, paramSource);	
			
			for (TrainingType bObj : obj.getdList()) {
				
				String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:value_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
				
				 paramSource = new BeanPropertySqlParameterSource(obj);		 
				 namedParamJdbcTemplate.update(updateTableQry, paramSource);	
			}
			String  enableQry =	"SET foreign_key_checks = 1";
			paramSource = new BeanPropertySqlParameterSource(obj);	
			namedParamJdbcTemplate.update(enableQry, paramSource);
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean deleteRiskArea(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE from risk_area WHERE area= :area; ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			 count = namedParamJdbcTemplate.update(deleteQry, paramSource);
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return flag;
	}
}


