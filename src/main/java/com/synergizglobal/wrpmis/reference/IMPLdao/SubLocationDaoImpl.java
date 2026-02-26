package com.synergizglobal.wrpmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.wrpmis.reference.Idao.SubLocationDao;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Repository
public class SubLocationDaoImpl implements SubLocationDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<TrainingType> getSubLocationsList() throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select id, rr_location_fk, rr_sub_location from rr_sub_location ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TrainingType> getLocationList() throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select rr_location from rr_location ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public TrainingType getSubLocationDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		List<TrainingType> objsList1 = null;
		TrainingType sObj =null;
		try {
			String qry ="select STRING_AGG(rr_sub_location, '?') as rr_sub_location,STRING_AGG(id,',') as id,rr_location_fk from rr_sub_location group by rr_location_fk ";
			
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
					
					qry1 = qry1 +"select "+bObj.getColumn_name()+" as rr_sub_location,count("+bObj.getColumn_name()+") as count,'"+bObj.getTable_name()+"' as tName from "+bObj.getTable_name()+" where "+bObj.getColumn_name()+" <> '' group by "+bObj.getColumn_name()+"  ";
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
					  String qry2 = "select rr_sub_location,rr_location_fk from rr_sub_location where rr_sub_location NOT IN (?";
	
						int j =0, p=1;
						for (TrainingType aObj : obj.getdList()) {
							pValues[j++] = aObj.getRr_sub_location();
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
					"where object_name(fkx.rkeyid)='rr_sub_location' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			tablesList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
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
					"where object_name(fkx.rkeyid)='rr_sub_location' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}

	@Override
	public boolean addSubLocation(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			int size = 0,size2 = 0,size3=0;
			String location  = obj.getRr_location_fk();
			String sub_location  = obj.getRr_sub_location();
			//sub_location = sub_location.replaceFirst("^", "").replaceAll(",,", ",");
			String [] locationArr = new String [0];
			String [] sub_locationArr = new String [0];
			String [] sub_locationArr1 = new String [0];
			if(!StringUtils.isEmpty(location) && location.contains(",")) {
				locationArr = location.split(",");
				size = locationArr.length;
				sub_locationArr = sub_location.split(",_,");
				size2 = locationArr.length;
				
			}else if(!StringUtils.isEmpty(sub_location) && sub_location.contains(",")) {
				sub_locationArr1 = sub_location.split(",");
				size3 = sub_locationArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(sub_location)) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(locationArr.length > 0) {
					String [] subName = null;
					obj.setRr_location_fk(locationArr[i]);
					if((!StringUtils.isEmpty(sub_locationArr1) && sub_locationArr1.length > 0) 
							|| (!StringUtils.isEmpty(sub_locationArr) && sub_locationArr.length > 0)) {
						if(sub_locationArr[i].contains(",")) {
							subName = sub_locationArr[i].split(",");
							size3 = (sub_locationArr1.length > 0) ? sub_locationArr1.length : subName.length ;
						}else {
							subName = sub_locationArr; size3 = 1;
							String sub_name = sub_locationArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setRr_sub_location(subName[j].replaceAll("&", ","));
						String insertQry = "INSERT INTO rr_sub_location"
								+ "( rr_location_fk, rr_sub_location) VALUES (:rr_location_fk, :rr_sub_location)";
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(sub_locationArr1) && sub_locationArr1.length > 0) {
							obj.setRr_sub_location(sub_locationArr1[j].replaceAll("&", ","));
						}else {
							obj.setRr_sub_location(sub_location.replaceAll("&", ","));
						}
						String insertQry = "INSERT INTO rr_sub_location"
								+ "( rr_location_fk, rr_sub_location) VALUES (:rr_location_fk, :rr_sub_location)";
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
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
	public boolean updateSubLocation(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			List<TrainingType> tablesList = getTablesList(obj);
			List<TrainingType> list = getDataDetails(obj);
			obj.setdList(list);

			String disableQry = "Alter Table rr_sub_location NOCHECK Constraint All ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(disableQry, paramSource);	
			
			String deleteQry = "delete from rr_sub_location where rr_location_fk= :rr_location_fk_old";
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(deleteQry, paramSource);	
			int size = 0,size2 = 0,size3=0;
			String location  = obj.getRr_location_fk_new();
			String sub_location  = obj.getRr_sub_location_new();
			//sub_location = sub_location.replaceFirst("^!,", "").replaceAll(",,", ",");
			String [] locationArr = new String [0];
			String [] sub_locationArr = new String [0];
			String [] sub_locationArr1 = new String [0];
			String [] value_old = new String [0];
			if(!StringUtils.isEmpty(location) && location.contains(",")) {
				locationArr = location.split(",");
				size = locationArr.length;
				sub_locationArr = sub_location.split(",_,");
				size2 = locationArr.length;
				
			}else if(!StringUtils.isEmpty(sub_location) && sub_location.contains(",")) {
				sub_locationArr1 = sub_location.split(",");
				size3 = sub_locationArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(sub_location) ) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(locationArr.length > 0) {
					String [] subName = null;
					obj.setRr_location_fk_new(locationArr[i]);
					if((!StringUtils.isEmpty(sub_locationArr1) && sub_locationArr1.length > 0) 
							|| (!StringUtils.isEmpty(sub_locationArr) && sub_locationArr.length > 0)) {
						if(sub_locationArr[i].contains(",")) {
							subName = sub_locationArr[i].split(",");
							size3 = (sub_locationArr1.length > 0) ? sub_locationArr1.length : subName.length ;
						}else {
							subName = sub_locationArr; size3 = 1;
							String sub_name = sub_locationArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setValue_old(obj.getRr_location_fk_old());
						obj.setRr_sub_location_new(subName[j].replaceAll("&", ","));
						String insertQry = "INSERT INTO rr_sub_location"
								+ "( rr_location_fk, rr_sub_location) VALUES (:rr_location_fk_new, :rr_sub_location_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
						for (TrainingType bObj : obj.getdList()) {
							 String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:rr_sub_location_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 namedParamJdbcTemplate.update(updateTableQry, paramSource);	
						}
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(sub_locationArr1) && sub_locationArr1.length > 0) {
							obj.setRr_sub_location_new(sub_locationArr1[j].replaceAll("&", ","));
						}else {
							obj.setValue_old(obj.getRr_location_fk_old());
							obj.setRr_sub_location_new(sub_location.replaceAll("&", ","));
						}
						String insertQry = "INSERT INTO rr_sub_location"
								+ "( rr_location_fk, rr_sub_location) VALUES (:rr_location_fk_new, :rr_sub_location_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
						for (TrainingType bObj : obj.getdList()) {
							 String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:rr_sub_location_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 namedParamJdbcTemplate.update(updateTableQry, paramSource);	
						}
					}
			     }
			
			}
			
			String  enableQry =	"Alter Table rr_sub_location CHECK Constraint All";
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
	public boolean deleteSubLocation(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE from rr_sub_location WHERE rr_location_fk= :rr_location_fk; ";
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
	public List<TrainingType> getSubLocations(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT rr_sub_location  from rr_sub_location where rr_location_fk= '"+ obj.getRr_location_fk()+"'";
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
}
