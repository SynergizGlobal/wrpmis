package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.ArrayList;
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

import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Dashboard;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.reference.Idao.WorkModuleUserAccessDao;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.reference.model.WorkModuleUserAccess;
@Repository
public class WorkModuleUserAccessDaoImpl implements WorkModuleUserAccessDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<WorkModuleUserAccess> getWorkModuleUserAccesssList() throws Exception {
		List<WorkModuleUserAccess> objsList = null;
		try {
			String qry ="select bank_name from bank_name ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));	
			
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public WorkModuleUserAccess getWorkModuleUserAccessDetails(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> objsList = null;
		List<WorkModuleUserAccess> objsList1 = null;
		WorkModuleUserAccess sObj =null;
		try {
			String qry ="select bank_name from bank_name ";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));		
			obj.setWorkModuleUserAccessList1(objsList);
			
			List<WorkModuleUserAccess> tablesList = getTablesList(obj);
			
			obj.setTablesList(tablesList);
			if(tablesList.size() > 0) {
				List<WorkModuleUserAccess> list = getDataDetails( obj);
				obj.setWorkModuleUserAccessList(list);
				String qry1 = "";
				int i = 1;
				for (WorkModuleUserAccess bObj : obj.getWorkModuleUserAccessList()) {
					
					qry1 = qry1 +"select "+bObj.getColumn_name()+" as bank_name_fk,count("+bObj.getColumn_name()+") as count,'"+bObj.getTable_name()+"' as tName from "+bObj.getTable_name()+" where "+bObj.getColumn_name()+" <> '' group by "+bObj.getColumn_name()+"  ";
					if( list.size() >  i) {
						qry1 = qry1 + " UNION ";
						i++;
					}
				}
				objsList1 = jdbcTemplate.query( qry1, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));
				obj.setWorkModuleUserAccessList(objsList1);
				obj.setCountList(objsList1);
				if(objsList1.size() > 0) {
					String qry2 = "select bank_name from bank_name where bank_name NOT IN (?";
		
					Object[] pValues = new Object[objsList1.size()];
					int j =0, p=1;
					for (WorkModuleUserAccess aObj : obj.getWorkModuleUserAccessList()) {
						pValues[j++] = aObj.getBank_name_fk();
						if( objsList1.size() >  p) {
							qry2 = qry2 + ",?";
							p++;
						}
					}
					qry2 = qry2 + ")";
					objsList1 = jdbcTemplate.query( qry2,pValues, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));
					obj.setWorkModuleUserAccessList(objsList1);
				}else {
					 obj.setdList(objsList);
				}
			}else {
				obj.setdList(objsList);
			}
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return obj;
	}

	@Override
	public boolean addWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO bank_name"
					+ "( bank_name) VALUES (:bank_name)";
			
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
	public boolean updateWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			List<WorkModuleUserAccess> tablesList = getTablesList(obj);
			List<WorkModuleUserAccess> list = getDataDetails(obj);
			obj.setWorkModuleUserAccessList(list);

			String disableQry = "Alter Table bank_name NOCHECK Constraint All ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(disableQry, paramSource);	
			
			String  updatereferenceTableQry = "UPDATE bank_name SET bank_name= :bank_name_new WHERE bank_name= :bank_name_old " ;
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(updatereferenceTableQry, paramSource);	
			
			for (WorkModuleUserAccess bObj : obj.getWorkModuleUserAccessList()) {
				
				String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:bank_name_new WHERE "+bObj.getColumn_name()+"= :bank_name_old " ;
				
				 paramSource = new BeanPropertySqlParameterSource(obj);		 
				 namedParamJdbcTemplate.update(updateTableQry, paramSource);	
			}
			String  enableQry =	"Alter Table bank_name CHECK Constraint All";
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


	private List<WorkModuleUserAccess> getTablesList(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> tablesList = null;
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
					"where object_name(fkx.rkeyid)='bank_name' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			tablesList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));	
			for(WorkModuleUserAccess tName : tablesList) {
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


	private List<WorkModuleUserAccess> getDataDetails(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> list = null;
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
					"where object_name(fkx.rkeyid)='bank_name' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}


	@Override
	public boolean deleteWorkModuleUserAccess(WorkModuleUserAccess obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE FROM bank_name WHERE bank_name= :bank_name; ";
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
	public List<WorkModuleUserAccess> getProjectsList(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> objsList = new ArrayList<WorkModuleUserAccess>();
		try {
			String qry = "select distinct project_id as project_id_fk,project_name "
					+ "from project p "
					+ "LEFT JOIN work w on w.project_id_fk = p.project_id "
					+ "where project_id is not null ";

			objsList = jdbcTemplate.query( qry,  new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<WorkModuleUserAccess> getWorksList(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> objsList = new ArrayList<WorkModuleUserAccess>();
		try {
			String qry = "select top 5 work_id as work_id_fk,work_name,work_short_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "where work_id is not null and w.work_id in('P04W01','P04W02','P04W03') ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and p.project_id = ? ";
				arrSize++;
			}	

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			
			qry = qry + " order by work_id asc";

			objsList = jdbcTemplate.query( qry,pValues,  new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<WorkModuleUserAccess> getModulesList(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> objsList = null;
		try {
			String qry = "SELECT distinct module_name_fk from dashboard  " + 
					"where module_name_fk is not null and module_name_fk <> '' and soft_delete_status_fk='Active' and soft_delete_status_fk='Active' and module_name_fk in('Contracts','Utility Shifting','Risk','R & R','Finance','Design','Land Acquisition')";
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<WorkModuleUserAccess> getWorkModuleWiseUsers(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> objsList = new ArrayList<WorkModuleUserAccess>();
		try {
			int arrSize = 0;
			Object[] pValues = new Object[arrSize];
			String qry ="";
			
			if(obj.getModule_name_fk().compareTo("Contracts")==0)
			{
				qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM contractexecutives re "
						+ "LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id "
						+ "left join work w on re.work_id_fk = w.work_id ";
				
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and w.work_id = ? ";
					arrSize++;
				}	
	
				
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
				qry = qry + " GROUP BY work_id_fk,work_short_name order by work_id_fk asc";
			}
			
			if(obj.getModule_name_fk().compareTo("Utility Shifting")==0)
			{
				qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM utility_shifting_executives re "
						+ "LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id "
						+ "left join work w on re.work_id_fk = w.work_id ";
				
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and w.work_id = ? ";
					arrSize++;
				}	
	
				
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
				qry = qry + " GROUP BY work_id_fk,work_short_name order by work_id_fk asc";
			}		
			
			if(obj.getModule_name_fk().compareTo("Risk")==0)
			{
				qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM risk_work_hod re "
						+ "LEFT JOIN [user] u on re.hod_user_id_fk = u.user_id "
						+ "left join work w on re.work_id_fk = w.work_id ";
				
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and w.work_id = ? ";
					arrSize++;
				}	
	
				
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
				qry = qry + " GROUP BY work_id_fk,work_short_name order by work_id_fk asc";
			}
			
			if(obj.getModule_name_fk().compareTo("R & R")==0)
			{
				qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM rr_executives re "
						+ "LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id "
						+ "left join work w on re.work_id_fk = w.work_id ";
				
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and w.work_id = ? ";
					arrSize++;
				}	
	
				
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
				qry = qry + " GROUP BY work_id_fk,work_short_name order by work_id_fk asc";
			}
			
			if(obj.getModule_name_fk().compareTo("Finance")==0)
			{
				qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM finance_executives re "
						+ "LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id "
						+ "left join work w on re.work_id_fk = w.work_id ";
				
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and w.work_id = ? ";
					arrSize++;
				}	
	
				
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
				qry = qry + " GROUP BY work_id_fk,work_short_name order by work_id_fk asc";
			}
			
			if(obj.getModule_name_fk().compareTo("Design")==0)
			{
				qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM designexecutives re "
						+ "LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id "
						+ "left join work w on re.work_id_fk = w.work_id ";
				
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and w.work_id = ? ";
					arrSize++;
				}	
	
				
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
				qry = qry + " GROUP BY work_id_fk,work_short_name order by work_id_fk asc";
			}
			
			if(obj.getModule_name_fk().compareTo("Land Acquisition")==0)
			{
				qry = "SELECT  ISNULL(STRING_AGG((u.user_id) , ','),'') as  user_id FROM land_executives re " + 
						"LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id left join work w on re.work_id_fk = w.work_id  where 0=0 ";
				
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and w.work_id = ? ";
					arrSize++;
				}	
	
				
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
			}			

			objsList = jdbcTemplate.query( qry,pValues,  new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
		
	}


	@Override
	public List<WorkModuleUserAccess> getUsersDetails(WorkModuleUserAccess obj) throws Exception {
		List<WorkModuleUserAccess> objList = null;
		try {
			String qry = "	select distinct user_id,user_name,designation from( " + 
					"	select distinct ur.user_id,ur.user_name,w.work_id,ur.designation from work w " + 
					"	inner join contract c on c.work_id_fk=w.work_id " + 
					"	left join [user] u on c.hod_user_id_fk = u.user_id  " + 
					"	left join [user] ur on u.user_id = ur.reporting_to_id_srfk  " + 
					"	where  ur.user_name is not null) as a where 0=0 ";

			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ? ";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<WorkModuleUserAccess>(WorkModuleUserAccess.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

}




