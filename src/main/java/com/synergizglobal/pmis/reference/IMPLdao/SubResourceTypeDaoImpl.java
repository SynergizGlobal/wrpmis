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
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.reference.Idao.SubResourceTypeDao;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Repository
public class SubResourceTypeDaoImpl implements SubResourceTypeDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<TrainingType> getRiskSubResourceType() throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select resource_type from resource_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TrainingType> getRiskResourceType() throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select resource_type from resource_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public TrainingType getSubResourceTypeDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		List<TrainingType> objsList1 = null;
		TrainingType sObj =null; 
		try {
			String qry ="select id, resource_type_fk, STRING_AGG(sub_resource_type,'') as sub_resource_type from sub_resource_type group by resource_type_fk,id ";
			
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
					
					qry1 = qry1 +"select "+bObj.getColumn_name()+" as sub_resource_type,count("+bObj.getColumn_name()+") as count,'"+bObj.getTable_name()+"' as tName from "+bObj.getTable_name()+" where "+bObj.getColumn_name()+" <> '' group by "+bObj.getColumn_name()+"  ";
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
					  String qry2 = "select id, resource_type_fk, sub_resource_type from sub_resource_type where sub_resource_type NOT IN (?";
	
						int j =0, p=1;
						for (TrainingType aObj : obj.getdList()) {
							pValues[j++] = aObj.getResource_type();
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
			String qry = "select  object_name(fkx.fkeyid) as tName,\r\n" + 
					"        \r\n" + 
					"        col_name(fkx.fkeyid, fkx.fkey) as COLUMN_NAME,\r\n" + 
					"		'' as CONSTRAINT_NAME,\r\n" + 
					"        object_name(fkx.rkeyid) as REFERENCED_TABLE_NAME,\r\n" + 
					"        col_name(fkx.rkeyid, fkx.rkey) as REFERENCED_COLUMN_NAME\r\n" + 
					"from sysforeignkeys fkx \r\n" + 
					"inner join sys.tables t on object_name(fkx.rkeyid)=t.name \r\n" + 
					"inner join sys.schemas s on s.schema_id=t.schema_id \r\n" + 
					"where object_name(fkx.rkeyid)='sub_resource_type' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			tablesList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
			for(TrainingType tName : tablesList) {
				 String name = tName.gettName();
				 name = name.replaceAll("[_]", " ");  
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
			String qry = "select  object_name(fkx.fkeyid) as TABLE_NAME,\r\n" + 
					"        \r\n" + 
					"        col_name(fkx.fkeyid, fkx.fkey) as COLUMN_NAME,\r\n" + 
					"		'' as CONSTRAINT_NAME,\r\n" + 
					"        object_name(fkx.rkeyid) as REFERENCED_TABLE_NAME,\r\n" + 
					"        col_name(fkx.rkeyid, fkx.rkey) as REFERENCED_COLUMN_NAME\r\n" + 
					"from sysforeignkeys fkx \r\n" + 
					"inner join sys.tables t on object_name(fkx.rkeyid)=t.name \r\n" + 
					"inner join sys.schemas s on s.schema_id=t.schema_id \r\n" + 
					"where object_name(fkx.rkeyid)='sub_resource_type' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}

	@Override
	public boolean addsubResourceType(Risk obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			int size = 0,size2 = 0,size3=0,count=0;
			String location  = obj.getResource_type_fk();
			String resource_type  = obj.getSub_resource_type();
			String [] locationArr = new String [0];
			String [] resource_typeArr = new String [0];
			String [] resource_typeArr1 = new String [0];
			String [] value_old = new String [0];
			if(!StringUtils.isEmpty(location) && location.contains(",")) {
				locationArr = location.split(",");
				size = locationArr.length;
				resource_typeArr = resource_type.split(",_,");
				size2 = locationArr.length;
				
			}else if(!StringUtils.isEmpty(resource_type) && resource_type.contains(",")) {
				resource_typeArr1 = resource_type.split(",");
				size3 = resource_typeArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(resource_type) ) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(locationArr.length > 0) {
					String [] subName = null;
					obj.setResource_type_fk(locationArr[i]);
					if((!StringUtils.isEmpty(resource_typeArr1) && resource_typeArr1.length > 0) 
							|| (!StringUtils.isEmpty(resource_typeArr) && resource_typeArr.length > 0)) {
						if(resource_typeArr[i].contains(",")) {
							subName = resource_typeArr[i].split(",");
							size3 = (resource_typeArr1.length > 0) ? resource_typeArr1.length : subName.length ;
						}else {
							subName = resource_typeArr; size3 = 1;
							String sub_name = resource_typeArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setSub_resource_type(subName[j].replaceAll("&", ","));
						String insertQry = "INSERT INTO sub_resource_type"
								+ "( resource_type_fk, sub_resource_type) VALUES (:resource_type_fk, :sub_resource_type)";
						BeanPropertySqlParameterSource	paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(resource_typeArr1) && resource_typeArr1.length > 0) {
							obj.setSub_resource_type(resource_typeArr1[j].replaceAll("&", ","));
						}else {
							obj.setSub_resource_type(resource_type.replaceAll("&", ","));
						}
						String insertQry = "INSERT INTO sub_resource_type"
								+ "( resource_type_fk, sub_resource_type) VALUES (:resource_type_fk, :sub_resource_type)";
						BeanPropertySqlParameterSource	paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
					}
			     }
			}
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
	public boolean updatesubResourceType(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			List<TrainingType> tablesList = getTablesList(obj);
			List<TrainingType> list = getDataDetails(obj);
			obj.setdList(list);

			String disableQry = "Alter Table sub_resource_type NOCHECK Constraint All ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(disableQry, paramSource);	
			
			String deleteQry = "delete from sub_resource_type where resource_type_fk= :resource_type_fk_old";
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(deleteQry, paramSource);	
			int size = 0,size2 = 0,size3=0;
			String location  = obj.getResource_type_fk_new();
			String resource_type  = obj.getSub_resource_type_new();
			String [] locationArr = new String [0];
			String [] resource_typeArr = new String [0];
			String [] resource_typeArr1 = new String [0];
			String [] value_old = new String [0];
			if(!StringUtils.isEmpty(location) && location.contains(",")) {
				locationArr = location.split(",");
				size = locationArr.length;
				resource_typeArr = resource_type.split(",_,");
				size2 = locationArr.length;
				
			}else if(!StringUtils.isEmpty(resource_type) && resource_type.contains(",")) {
				resource_typeArr1 = resource_type.split(",");
				size3 = resource_typeArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(resource_type) ) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(locationArr.length > 0) {
					String [] subName = null;
					obj.setResource_type_fk_new(locationArr[i]);
					if((!StringUtils.isEmpty(resource_typeArr1) && resource_typeArr1.length > 0) 
							|| (!StringUtils.isEmpty(resource_typeArr) && resource_typeArr.length > 0)) {
						if(resource_typeArr[i].contains(",")) {
							subName = resource_typeArr[i].split(",");
							size3 = (resource_typeArr1.length > 0) ? resource_typeArr1.length : subName.length ;
						}else {
							subName = resource_typeArr; size3 = 1;
							String sub_name = resource_typeArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setValue_old(obj.getResource_type_fk());
						obj.setSub_resource_type_new(subName[j].replaceAll("&", ","));
						String insertQry = "INSERT INTO sub_resource_type"
								+ "( resource_type_fk, sub_resource_type) VALUES (:resource_type_fk_new, :sub_resource_type_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
						for (TrainingType bObj : obj.getdList()) {
							String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:sub_resource_type_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
							
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 namedParamJdbcTemplate.update(updateTableQry, paramSource);		
						}
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(resource_typeArr1) && resource_typeArr1.length > 0) {
							obj.setSub_resource_type_new(resource_typeArr1[j].replaceAll("&", ","));
						}else {
							obj.setValue_old(obj.getResource_type_fk());
							obj.setSub_resource_type_new(resource_type.replaceAll("&", ","));
						}
						String insertQry = "INSERT INTO sub_resource_type"
								+ "( resource_type_fk, sub_resource_type) VALUES (:resource_type_fk_new, :sub_resource_type_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
						for (TrainingType bObj : obj.getdList()) {
							String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:value_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
							
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 namedParamJdbcTemplate.update(updateTableQry, paramSource);		
						}
					}
			     }
			
			}

			
			String  enableQry =	"Alter Table sub_resource_type CHECK Constraint All";
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
	public boolean deletesubResourceType(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE from sub_resource_type WHERE id= :id; ";
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

	@Override
	public List<TrainingType> getResourceType(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT sub_resource_type  from sub_resource_type where resource_type_fk= '"+ obj.getResource_type_fk()+"'";
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
}
