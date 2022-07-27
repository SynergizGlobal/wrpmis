package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.reference.Idao.WebDocumentCategoryDao;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Repository
public class WebDocumentCategoryDaoImpl implements WebDocumentCategoryDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public TrainingType getWebDocumentsCategoryDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		List<TrainingType> objsList1 = null;
		TrainingType sObj =null;
		try {
			String qry ="select STRING_AGG(id) id, type_fk, STRING_AGG(category) category from web_documents_category  group by type_fk";
			
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
					
					qry1 = qry1 +"select "+bObj.getColumn_name()+" as category,count("+bObj.getColumn_name()+") as count,'"+bObj.getTable_name()+"' as tName from "+bObj.getTable_name()+" where "+bObj.getColumn_name()+" <> '' group by "+bObj.getColumn_name()+"  ";
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
					  String qry2 = "select id, type_fk, category from web_documents_category where id NOT IN (?";
	
						int j =0, p=1;
						for (TrainingType aObj : obj.getdList()) {
							pValues[j++] = aObj.getCategory();
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
			String qry = "SELECT TABLE_NAME as tName,COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME " + 
					"FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = 'web_documents_category' and TABLE_SCHEMA = 'pmis' group by TABLE_NAME";
			
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
			String qry = "SELECT TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME " + 
					"FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = 'web_documents_category' and TABLE_SCHEMA = 'pmis'";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}
	@Override
	public boolean addWebDocumentsCategory(Risk obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			int size = 0,size2 = 0,size3=0;
			String area  = obj.getType_fk();
			String sub_area  = obj.getCategory();
			//sub_area = sub_area.replaceFirst("^", "").replaceAll(",,", ",");
			String [] areaArr = new String [0];
			String [] CategoryArr = new String [0];
			String [] CategoryArr1 = new String [0];
			if(!StringUtils.isEmpty(area) && area.contains(",")) {
				areaArr = area.split(",");
				size = areaArr.length;
				CategoryArr = sub_area.split(",_,");
				size2 = areaArr.length;
				
			}else if(!StringUtils.isEmpty(sub_area) && sub_area.contains(",")) {
				CategoryArr1 = sub_area.split(",");
				size3 = CategoryArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(sub_area)) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(areaArr.length > 0) {
					String [] subName = null;
					obj.setType_fk(areaArr[i]);
					if((!StringUtils.isEmpty(sub_area) && CategoryArr1.length > 0) 
							|| (!StringUtils.isEmpty(sub_area) && CategoryArr.length > 0)) {
						if(CategoryArr[i].contains(",")) {
							subName = CategoryArr[i].split(",");
							size3 = (CategoryArr1.length > 0) ? CategoryArr1.length : subName.length ;
						}else {
							subName = CategoryArr; size3 = 1;
							String sub_name = CategoryArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setCategory(subName[j].replaceAll("&", ","));
						String insertQry = "INSERT INTO web_documents_category"
								+ "( type_fk, category) VALUES (:type_fk, :category)";
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(sub_area) && CategoryArr1.length > 0) {
							obj.setCategory(CategoryArr1[j].replaceAll("&", ","));
						}else {
							obj.setCategory(sub_area.replaceAll("&", ","));
						}
						String insertQry = "INSERT INTO web_documents_category"
								+ "( type_fk, category) VALUES (:type_fk, :category)";
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
	public boolean updateWebDocumentsCategory(TrainingType obj) throws Exception {
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
			
			String deleteQry = "delete from web_documents_category where type_fk= :type_fk_old";
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(deleteQry, paramSource);	
			
			
			int size = 0,size2 = 0,size3=0;
			String area  = obj.getType_fk_new();
			String sub_area  = obj.getCategory_new();
			String value_old  = obj.getValue_old();
			//sub_area = sub_area.replaceFirst("^", "").replaceAll(",,", ",");
			String [] areaArr = new String [0];
			String [] CategoryArr = new String [0];
			String [] CategoryArr1 = new String [0];
			String [] value_oldArr1 = new String [0];
			if(!StringUtils.isEmpty(area) && area.contains(",")) {
				areaArr = area.split(",");
				size = areaArr.length;
				CategoryArr = sub_area.split(",_,");
				size2 = areaArr.length;
				
			}else if(!StringUtils.isEmpty(sub_area) && sub_area.contains(",")) {
				CategoryArr1 = sub_area.split(",");
				
				size3 = CategoryArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(sub_area)) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(areaArr.length > 0) {
					String [] subName = null;
					obj.setType_fk_new(areaArr[i]);
					if((!StringUtils.isEmpty(sub_area) && CategoryArr1.length > 0) 
							|| (!StringUtils.isEmpty(sub_area) && CategoryArr.length > 0)) {
						if(CategoryArr[i].contains(",")) {
							subName = CategoryArr[i].split(",");
							size3 = (CategoryArr1.length > 0) ? CategoryArr1.length : subName.length ;
						}else {
							subName = CategoryArr; size3 = 1;
							String sub_name = CategoryArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setCategory_new(subName[j].replaceAll("&", ","));
						String insertQry = "INSERT INTO web_documents_category"
								+ "( type_fk, category) VALUES (:type_fk_new, :category_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);	
						KeyHolder keyHolder = new GeneratedKeyHolder();
						count = namedParamJdbcTemplate.update(insertQry, paramSource,keyHolder);
						 String id = null;
							if(count > 0) {
								id = String.valueOf(keyHolder.getKey().intValue());
								 flag = true;
							}
							obj.setId(id);
							String idOld =  value_old;
							if(value_old.contains(",")) {
								value_oldArr1 = value_old.split(",") ;
								try {
									idOld = value_oldArr1[j];
								}catch(Exception e) {
									idOld = "no val";
								}
							}
						for (TrainingType bObj : obj.getdList()) {
							String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:id WHERE "+bObj.getColumn_name()+"= :value_old " ;
							obj.setValue_old(idOld);
							paramSource = new BeanPropertySqlParameterSource(obj);		 
							namedParamJdbcTemplate.update(updateTableQry, paramSource);	
						}
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(sub_area) && CategoryArr1.length > 0) {
							obj.setCategory_new(CategoryArr1[j].replaceAll("&", ","));
						}else {
							obj.setCategory_new(sub_area.replaceAll("&", ","));
						}
						String insertQry = "INSERT INTO web_documents_category"
								+ "( type_fk, category) VALUES (:type_fk_new, :category_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);	
						KeyHolder keyHolder = new GeneratedKeyHolder();
						count = namedParamJdbcTemplate.update(insertQry, paramSource,keyHolder);
						 String id = null;
							if(count > 0) {
								id = String.valueOf(keyHolder.getKey().intValue());
								 flag = true;
							}
							obj.setId(id);	
							String idOld =  value_old;
							if(value_old.contains(",")) {
								value_oldArr1 = value_old.split(",") ;
								try {
									idOld = value_oldArr1[j];
								}catch(Exception e) {
									idOld = null;
								}
							}
						for (TrainingType bObj : obj.getdList()) {
							
							String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:id WHERE "+bObj.getColumn_name()+"= :value_old " ;
							obj.setValue_old(idOld);
							paramSource = new BeanPropertySqlParameterSource(obj);		 
							namedParamJdbcTemplate.update(updateTableQry, paramSource);	
						}
					}
			     }
			
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
	public boolean deleteWebDocumentsCategory(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE from web_documents_category WHERE type_fk= :type_fk; ";
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
	public List<TrainingType> getDocumentType(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select type as type_fk from web_documents_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TrainingType> getWebcategory(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT category  from web_documents_category where type_fk= '"+ obj.getType_fk()+"'";
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
}
