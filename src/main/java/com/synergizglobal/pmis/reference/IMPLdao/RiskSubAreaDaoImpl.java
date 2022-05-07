package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.reference.Idao.RiskSubAreaDao;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Repository
public class RiskSubAreaDaoImpl implements RiskSubAreaDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Risk> getRiskSubAreasList() throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select sub_area, risk_area_fk, item_no from risk_sub_area ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addRiskSubArea(Risk obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			int size = 0,size2 = 0,size3=0;
			String area  = obj.getRisk_area_fk();
			String sub_area  = obj.getSub_area();
			String item_no  = obj.getItem_no();
			//sub_area = sub_area.replaceFirst("^", "").replaceAll(",,", ",");
			String [] areaArr = new String [0];
			String [] Sub_areaArr = new String [0];
			String [] itemNoArr = new String [0];
			String [] Sub_areaArr1 = new String [0];
			if(!StringUtils.isEmpty(area) && area.contains(",")) {
				areaArr = area.split(",");
				size = areaArr.length;
				Sub_areaArr = sub_area.split(",_,");
				itemNoArr = item_no.split(",_,");
				size2 = areaArr.length;
				
			}else if(!StringUtils.isEmpty(sub_area) && sub_area.contains(",")) {
				itemNoArr = item_no.split(",");
				Sub_areaArr1 = sub_area.split(",");
				size3 = Sub_areaArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(sub_area)) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(areaArr.length > 0) {
					String [] subName = null;
					String [] itemNo = null;
					obj.setRisk_area_fk(areaArr[i]);
					if((!StringUtils.isEmpty(sub_area) && Sub_areaArr1.length > 0) 
							|| (!StringUtils.isEmpty(sub_area) && Sub_areaArr.length > 0)) {
						if(Sub_areaArr[i].contains(",")) {
							subName = Sub_areaArr[i].split(",");
							itemNo = itemNoArr[i].split(",");
							size3 = (Sub_areaArr1.length > 0) ? Sub_areaArr1.length : subName.length ;
						}else {
							subName = Sub_areaArr;itemNo = itemNoArr; size3 = 1;
							String sub_name = Sub_areaArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setSub_area(subName[j].replaceAll("&", ","));
						obj.setItem_no(itemNo[j]);
						String insertQry = "INSERT INTO risk_sub_area"
								+ "( sub_area, risk_area_fk, item_no) VALUES (:sub_area, :risk_area_fk, :item_no)";
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);		
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(sub_area) && Sub_areaArr1.length > 0) {
							obj.setSub_area(Sub_areaArr1[j].replaceAll("&", ","));
							obj.setItem_no(itemNoArr[j]);
						}else {
							obj.setSub_area(sub_area.replaceAll("&", ","));
							obj.setItem_no(item_no);
						}
						String insertQry = "INSERT INTO risk_sub_area"
								+ "( sub_area, risk_area_fk, item_no) VALUES (:sub_area, :risk_area_fk, :item_no)";
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
	public TrainingType getRiskSubAreaDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		List<TrainingType> objsList1 = null;
		TrainingType sObj =null;
		try {
			String qry ="select risk_area_fk,group_concat(sub_area SEPARATOR '_') as sub_area,group_concat(item_no) as item_no from risk_sub_area group by risk_area_fk ";
			
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
					
					qry1 = qry1 +"select "+bObj.getColumn_name()+" as `sub_area`,count("+bObj.getColumn_name()+") as count,'"+bObj.getTable_name()+"' as tName from "+bObj.getTable_name()+" where "+bObj.getColumn_name()+" <> '' group by "+bObj.getColumn_name()+"  ";
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
					  String qry2 = "select sub_area,risk_area_fk, item_no from risk_sub_area where `sub_area` NOT IN (?";
	
						int j =0, p=1;
						for (TrainingType aObj : obj.getdList()) {
							pValues[j++] = aObj.getSub_area();
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
					"FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = 'risk_sub_area' and TABLE_SCHEMA = 'pmis' group by TABLE_NAME";
			
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
					"FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = 'risk_sub_area' and COLUMN_NAME <> 'user_role_fk' and TABLE_SCHEMA = 'pmis' ";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}



	@Override
	public List<TrainingType> getRiskAreaList(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select sub_area, risk_area_fk, item_no from risk_sub_area where sub_area <> '' ";
			if(!StringUtils.isEmpty(obj.getSub_area())) {
				qry = qry + " sub_area = "+obj.getSub_area()+" ";
			}
			
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean updateRiskSubArea(TrainingType obj) throws Exception {
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
			
			String deleteQry = "delete from risk_sub_area where `risk_area_fk`= :risk_area_fk_old";
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(deleteQry, paramSource);	
			
			int size = 0,size2 = 0,size3=0;
			String area  = obj.getRisk_area_fk_new();
			String sub_area  = obj.getSub_area_new();
			String item_no  = obj.getItem_no_new();
			String value_old = obj.getValue_old();
			//sub_area = sub_area.replaceFirst("^", "").replaceAll(",,", ",");
			String [] areaArr = new String [0];
			String [] Sub_areaArr = new String [0];
			String [] itemNoArr = new String [0];
			String [] Sub_areaArr1 = new String [0];
			String [] value_oldArr1 = new String [0];
			if(!StringUtils.isEmpty(area) && area.contains(",")) {
				areaArr = area.split(",");
				size = areaArr.length;
				Sub_areaArr = sub_area.split(",_,");
				itemNoArr = item_no.split(",_,");
				size2 = areaArr.length;
				
			}else if(!StringUtils.isEmpty(sub_area) && sub_area.contains(",")) {
				itemNoArr = item_no.split(",");
				Sub_areaArr1 = sub_area.split(",");
				size3 = Sub_areaArr1.length;
				size = 1;
			}else if(!StringUtils.isEmpty(sub_area)) {
				size = 1;
				size3 = 1;
			}
			for(int i =0;i< size; i++) {
				 if(areaArr.length > 0) {
					String [] subName = null;
					String [] itemNo = null;
					obj.setRisk_area_fk_new(areaArr[i]);
					if((!StringUtils.isEmpty(sub_area) && Sub_areaArr1.length > 0) 
							|| (!StringUtils.isEmpty(sub_area) && Sub_areaArr.length > 0)) {
						if(Sub_areaArr[i].contains(",")) {
							subName = Sub_areaArr[i].split(",");
							itemNo = itemNoArr[i].split(",");
							size3 = (Sub_areaArr1.length > 0) ? Sub_areaArr1.length : subName.length ;
						}else {
							subName = Sub_areaArr;itemNo = itemNoArr; size3 = 1;
							String sub_name = Sub_areaArr[i];
							subName[0] = sub_name;
						}
					}
					for(int j =0;j< size3; j++) {
						obj.setSub_area_new(subName[j].replaceAll("&", ","));
						obj.setItem_no_new(itemNo[j]);
						String insertQry = "INSERT INTO risk_sub_area"
								+ "( sub_area, risk_area_fk, item_no) VALUES (:sub_area_new, :risk_area_fk_new, :item_no_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);
						String oldVal =  value_old;
						if(value_old.contains(",")) {
							value_oldArr1 = value_old.split(",") ;
							try {
								oldVal = value_oldArr1[j];
							}catch(Exception e) {
								oldVal = null;
							}
						}
						for (TrainingType bObj : obj.getdList()) {
							 String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:sub_area_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
							 obj.setValue_old(oldVal);
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 namedParamJdbcTemplate.update(updateTableQry, paramSource);	
						}
					}
				 }else {
					for(int j =0;j< size3; j++) {
						if(!StringUtils.isEmpty(sub_area) && Sub_areaArr1.length > 0) {
							obj.setSub_area_new(Sub_areaArr1[j].replaceAll("&", ","));
							obj.setItem_no_new(itemNoArr[j]);
						}else {
							obj.setSub_area_new(sub_area.replaceAll("&", ","));
							obj.setItem_no_new(item_no);
						}
						String insertQry = "INSERT INTO risk_sub_area"
								+ "( sub_area, risk_area_fk, item_no) VALUES (:sub_area_new, :risk_area_fk_new, :item_no_new)";
						paramSource = new BeanPropertySqlParameterSource(obj);		 
						count = namedParamJdbcTemplate.update(insertQry, paramSource);	
						String oldVal =  value_old;
						if(value_old.contains(",")) {
							value_oldArr1 = value_old.split(",") ;
							try {
								oldVal = value_oldArr1[j];
							}catch(Exception e) {
								oldVal = "no val";
							}
						}
						for (TrainingType bObj : obj.getdList()) {
							
							String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:sub_area_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
							 obj.setValue_old(oldVal);
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
	public boolean deleteRiskSubArea(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE from risk_sub_area WHERE `sub_area`= :sub_area; ";
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
	public List<TrainingType> getSubAreaDetails(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT sub_area,item_no  from risk_sub_area where risk_area_fk= '"+ obj.getRisk_area_fk()+"'";
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
}




