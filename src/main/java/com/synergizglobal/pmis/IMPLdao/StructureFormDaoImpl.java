package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.StructureFormDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.Structure;
@Repository
public class StructureFormDaoImpl implements StructureFormDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	MessagesDao messagesDao;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public List<Structure> getWorkStatusList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.work_status_fk,structure_id FROM structure s  " + 
					"left join structure_contract_responsible_people cp on cp.structure_id_fk = s.structure_id "+
					"where s.work_status_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and s.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and cp.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  cp.responsible_people_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY s.work_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();
		
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getWorksListForFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.work_id_fk,w.work_short_name,work_name FROM structure s " + 
					"left join structure_contract_responsible_people cp on s.structure_id = cp.structure_id_fk " + 
					"left join  work w on s.work_id_fk = w.work_id "+
					"where s.work_id_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and s.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and cp.contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  cp.responsible_people_id_fk = ? ";
				arrSize++;
			}	
			qry = qry + "GROUP BY s.work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();
			
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getContractsListForFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT cp.contract_id_fk ,c.contract_short_name,c.contract_name FROM structure_contract_responsible_people cp " + 
					"left join structure s on cp.structure_id_fk = s.structure_id " + 
					"left join contract c on cp.contract_id_fk = c.contract_id "+
					"where cp.contract_id_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and s.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and cp.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  cp.responsible_people_id_fk = ? ";
				arrSize++;
			}	
			qry = qry + "GROUP BY cp.contract_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();
			
			}	
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(Structure obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			
			String qry ="select count(*) from (select structure_id  " + 
					"from structure s " + 
					"left join structure_contract_responsible_people cp on s.structure_id =  cp.structure_id_fk " + 
					"left join work w on s.work_id_fk = w.work_id " + 
					"left join contract c on cp.contract_id_fk = c.contract_id  " 
					+"where structure_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and s.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and cp.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}				
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  cp.responsible_people_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (cp.contract_id_fk like ? or c.contract_short_name like ? or s.work_id_fk like ? or w.work_short_name like ?  "
						+ "or s.work_status_fk like ? or structure_type_fk like ? or structure like ? or structure_name like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			qry = qry + " group by  IFNULL(cp.contract_id_fk,structure_id), structure_id) as total_records ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				pValues[i++] = obj.getStructure_type_fk();
			}				
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();
			
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<Structure> getStructuresList(Structure obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Structure> objsList = null;
		try {
			String qry ="SELECT structure_id,s.work_id_fk,w.work_short_name,work_name,cp.contract_id_fk,c.contract_name,c.contract_short_name,s.structure_name, " + 
					"s.structure,s.work_status_fk,structure_type_fk FROM structure s " + 
					"left join structure_contract_responsible_people cp on s.structure_id =  cp.structure_id_fk " + 
					"left join work w on s.work_id_fk = w.work_id " + 
					"left join contract c on cp.contract_id_fk = c.contract_id  " + 
					"where structure_id is not null " ;
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and s.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and cp.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  cp.responsible_people_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (cp.contract_id_fk like ? or c.contract_short_name like ? or s.work_id_fk like ? or w.work_short_name like ?  "
						+ "or s.work_status_fk like ? or structure_type_fk like ? or structure like ? or structure_name like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + "group by  IFNULL(cp.contract_id_fk,structure_id), structure_id  ORDER BY structure_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				pValues[i++] = obj.getStructure_type_fk();
			}			
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();
			
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructureDetailsLocations(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select fob_details_location from fob_details_location ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Structure>(Structure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructureDetailsTypes(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select fob_details_type from fob_details_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Structure>(Structure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Structure getStructuresFormDetails(Structure obj) throws Exception {
		Structure structure = null;
		try {
			String qry = "select structure_id, s.work_id_fk, structure_type_fk, structure,w.work_name,p.project_name,w.work_short_name,w.project_id_fk, "
					+ "s.work_status_fk,s.structure_name,cast(s.latitude as CHAR) as latitude,cast(s.longitude as CHAR) as longitude,  DATE_FORMAT(s.target_date,'%d-%m-%Y') AS target_date, "
					+ "s.estimated_cost,s.completion_cost,s.completion_cost_units,DATE_FORMAT(s.commissioning_date,'%d-%m-%Y') AS commissioning_date,DATE_FORMAT(s.actual_completion_date,'%d-%m-%Y') AS actual_completion_date, s.estimated_cost_units,"
					+ " DATE_FORMAT(s.construction_start_date,'%d-%m-%Y') AS construction_start_date, DATE_FORMAT(s.revised_completion,'%d-%m-%Y') AS revised_completion, s.remarks "
					+ "from structure s " + 
					"left join work w on s.work_id_fk = w.work_id " +
					"left join project p on w.project_id_fk = p.project_id where structure_id is not null" ; 
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			structure = (Structure)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));	
			if(!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getStructure_id())) {
				if(!StringUtils.isEmpty(structure)) {
					String executivesQry ="select DISTINCT structure_id_fk as structure_id,contract_id_fk,contract_short_name from structure_contract_responsible_people sp left join contract c on c.contract_id = sp.contract_id_fk where  structure_id_fk = ? ";
					List<Structure> executivesList = jdbcTemplate.query( executivesQry,new Object[] {structure.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
					structure.setExecutivesList(executivesList);
					for(Structure peopleList : structure.getExecutivesList()) {
						String peopleQry ="select id, structure_id_fk, contract_id_fk,designation,user_name, responsible_people_id_fk from structure_contract_responsible_people sp left join user u on u.user_id=sp.responsible_people_id_fk where contract_id_fk = ? and  structure_id_fk = ? ";
						List<Structure> peopleLists = jdbcTemplate.query( peopleQry,new Object[] {peopleList.getContract_id_fk(),peopleList.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
						peopleList.setResponsiblePeopleLists(peopleLists);
					}
				}
				if(!StringUtils.isEmpty(structure)) {
					String detailsQry ="select id, structure_id_fk, structure_detail, value as structure_value from structure_details sd where  structure_id_fk = ? ";
					detailsQry = detailsQry + " and structure_detail in('Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos') order by field(structure_detail,'Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos')";

					List<Structure> detailsList = jdbcTemplate.query( detailsQry,new Object[] {structure.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
					if(StringUtils.isEmpty(detailsList) || detailsList.size() == 0) {
						detailsQry = "select structure_detail from structure_details sd " ;
						detailsQry = detailsQry + " where structure_detail in('Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos') "
								+ "group by structure_detail order by field(structure_detail,'Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos')";
						
						detailsList = jdbcTemplate.query(detailsQry, new Object[] {}, new BeanPropertyRowMapper<Structure>(Structure.class));
					}
					structure.setStructureDetailsList(detailsList);
				}
				
				if(!StringUtils.isEmpty(structure)) {
						String detailsQry ="select id, structure_id_fk, structure_detail, value as structure_value from structure_details sd where  structure_id_fk = ? "
								+ "and structure_detail not in('Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos') " ;
						List<Structure> detailsList = jdbcTemplate.query( detailsQry,new Object[] {structure.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
						structure.setStructureDetailsList1(detailsList);
				}
				
				if(!StringUtils.isEmpty(structure)) {
					String documentsQry ="select id as structure_file_id, structure_id_fk, name, attachment,structure_file_type_fk,created_date from structure_documents d where  structure_id_fk = ? ";
					List<Structure> documentsList = jdbcTemplate.query( documentsQry,new Object[] {structure.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
					structure.setDocumentsList(documentsList);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return structure;
	}

	@Override
	public boolean addStructureForm(Structure obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);		
			String structure_id= null;
			String qry = "INSERT INTO structure"
					+ "(structure,structure_name,work_id_fk,structure_type_fk,target_date,construction_start_date,actual_completion_date,commissioning_date,"
					+ "estimated_cost,completion_cost,work_status_fk,latitude,longitude,remarks,revised_completion,estimated_cost_units,completion_cost_units) "
					+ "VALUES "
					+ "(:structure,:structure_name,:work_id_fk,:structure_type_fk,:target_date,:construction_start_date,:actual_completion_date,:commissioning_date,:" 
					+ "estimated_cost,:completion_cost,:work_status_fk,:latitude,:longitude,:remarks,:revised_completion,:estimated_cost_units,:completion_cost_units)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);			
			if(count > 0) {
				structure_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setStructure_id(structure_id);
				flag = true;
			}
			if(flag) {
				if(flag && !StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0) {
					obj.setStructure_details(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_details()));
				}
				if(flag && !StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0) {
					obj.setStructure_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_values()));
				}
				
				String[] fobDetailNames = obj.getStructure_details();
				String[] fobDetailValues = obj.getStructure_values();
				
				String qryFOBDetail = "INSERT INTO structure_details (structure_id_fk,structure_detail,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail,
		            new BatchPreparedStatementSetter() {			                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {	
		                	int k = 1;
							ps.setString(k++, obj.getStructure_id());
							ps.setString(k++, fobDetailNames.length > 0 ?fobDetailNames[i]:null);
							ps.setString(k++, fobDetailValues.length > 0 ?fobDetailValues[i]:null);
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                	int arraySize = 0;
		    				if(!StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0) {
		    					obj.setStructure_details(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_details()));
		    					if(arraySize < obj.getStructure_details().length) {
		    						arraySize = obj.getStructure_details().length;
		    					}
		    				}
		    				if(!StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0) {
		    					obj.setStructure_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_values()));
		    					if(arraySize < obj.getStructure_values().length) {
		    						arraySize = obj.getStructure_values().length;
		    					}
		    				}
		                    return arraySize;
		                }
		            });
				
					//String file_insert_qry = "INSERT into  fob_files ( fob_id_fk, attachment,created_date,fob_file_type_fk,name) VALUES (:fob_id,:attachment,:created_date,:fob_file_type_fk,:name)";
					String document_insert_qry = "INSERT into  structure_documents ( structure_id_fk, attachment,structure_file_type_fk,name,created_date) VALUES (:structure_id,:attachment,:structure_file_type_fk,:name,CURDATE())";

					int arraySize =0, docArrSize = 0;
				/*	if (!StringUtils.isEmpty(obj.getFileNamesStructure()) && obj.getFileNamesStructure().length > 0) {
						obj.setFileNamesStructure(CommonMethods.replaceEmptyByNullInSringArray(obj.getFileNamesStructure()));
						if (docArrSize < obj.getFileNamesStructure().length) {
							docArrSize = obj.getFileNamesStructure().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getStructureDoc_file_types()) && obj.getStructureDoc_file_types().length > 0) {
						obj.setStructureDoc_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureDoc_file_types()));
						if (docArrSize < obj.getStructureDoc_file_types().length) {
							docArrSize = obj.getStructureDoc_file_types().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getDocumentNamesStructure()) && obj.getDocumentNamesStructure().length > 0) {
						obj.setDocumentNamesStructure(CommonMethods.replaceEmptyByNullInSringArray(obj.getDocumentNamesStructure()));
						if (docArrSize < obj.getDocumentNamesStructure().length) {
							docArrSize = obj.getDocumentNamesStructure().length;
						}
					}*/
					
					if (!StringUtils.isEmpty(obj.getStructureFileNames()) && obj.getStructureFileNames().length > 0) {
						obj.setStructureFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureFileNames()));
						if (docArrSize < obj.getStructureFileNames().length) {
							docArrSize = obj.getStructureFileNames().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getStructure_file_types()) && obj.getStructure_file_types().length > 0) {
						obj.setStructure_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_types()));
						if (docArrSize < obj.getStructure_file_types().length) {
							docArrSize = obj.getStructure_file_types().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getStructureDocumentNames()) && obj.getStructureDocumentNames().length > 0) {
						obj.setStructureDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureDocumentNames()));
						if (docArrSize < obj.getStructureDocumentNames().length) {
							docArrSize = obj.getStructureDocumentNames().length;
						}
					}
					
					/*if (!StringUtils.isEmpty(obj.getStructure_file_types()) && obj.getStructure_file_types().length > 0) {
						obj.setStructure_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_types()));
						if (arraySize < obj.getStructure_file_types().length) {
							arraySize = obj.getStructure_file_types().length;
						}
					}*/
					for (int i = 0; i < docArrSize; i++) {
						if (!StringUtils.isEmpty(obj.getStructureFiles()) && obj.getStructureFiles().length > 0) {
							MultipartFile multipartFile = obj.getStructureFiles()[i];
							if ((null != multipartFile && !multipartFile.isEmpty()) && !StringUtils.isEmpty(obj.getStructure_file_types()[i]) && !StringUtils.isEmpty(obj.getStructureDocumentNames()[i]))  {
								String saveDirectory =  CommonConstants2.STRUCTURE_FILE_SAVING_PATH ;
								String fileName = obj.getStructureFileNames()[i];
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								Structure fileObj = new Structure();
								fileObj.setAttachment(fileName);
								
								String fob_file_type_fk = obj.getStructure_file_types()[i];
								String name = obj.getStructureDocumentNames()[i];
								
								fileObj.setStructure_id(obj.getStructure_id());
								fileObj.setStructure_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(document_insert_qry, paramSource);
							}
						}
					}
				/*	for (int i = 0; i < arraySize; i++) {
						if (!StringUtils.isEmpty(obj.getStructureFiles()) && obj.getStructureFiles().length > 0) {
							MultipartFile multipartFile = obj.getStructureFiles()[i];
							if ((null != multipartFile && !multipartFile.isEmpty())) {
								String saveDirectory = CommonConstants2.FOB_GALLERY_SAVING_PATH + obj.getStructure_id() + File.separator;
								String fileName = obj.getStructureFileNames()[i];
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								FOB fileObj = new FOB();
								fileObj.setAttachment(fileName);
								//fileObj.setStructure_file_type_fk(obj.getStructure_file_types()[i]);
								String created_date = null;
								if (!StringUtils.isEmpty(obj.getCreated_dates()) && obj.getCreated_dates().length > 0) {
									created_date = obj.getCreated_dates()[i];
								}
								if(!StringUtils.isEmpty(created_date)) {
									created_date = DateParser.parse(created_date);
								}else {
									SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
									created_date = sqlDate.format(new Date());
								}
								
								String fob_file_type_fk = obj.getStructure_file_types()[i];
								String name = obj.getStructureDocumentNames()[i];
								
								fileObj.setCreated_date(created_date);
								fileObj.setStructure_id(obj.getStructure_id());
								fileObj.setStructure_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(file_insert_qry, paramSource);
							}
						}
					}
					*/
					
					/********************************************************************/
					
					
					/*if(!StringUtils.isEmpty(obj.getContracts_id_fk())) {
						for(int k =0; k<obj.getContracts_id_fk().length; k++) {
							String contarctId = obj.getContracts_id_fk()[k];
							String qry3 = "INSERT into fob_contract (fob_id_fk,contract_id_fk) VALUES (:fob_id_fk,:contract_id_fk)";
							if(contarctId.contains(",")) {
								String[] ids = contarctId.split(",");					
								for (int i = 0; i < ids.length; i++) {
									FOB fileObj = new FOB();
									fileObj.setContract_id_fk(!StringUtils.isEmpty(ids[i])?ids[i]:null);
									fileObj.setStructure_id_fk(obj.getStructure_id());
									paramSource = new BeanPropertySqlParameterSource(fileObj);
									namedParamJdbcTemplate.update(qry3, paramSource);
								}			
							}else {
								FOB fileObj = new FOB();
								fileObj.setContract_id_fk(contarctId);
								fileObj.setStructure_id_fk(obj.getStructure_id());
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(qry3, paramSource);
							}
						}	
					}*/
					
					if(!StringUtils.isEmpty(obj.getContracts_id_fk())  && obj.getContracts_id_fk().length > 0) {
						String qry3 = "INSERT into structure_contract_responsible_people (structure_id_fk,contract_id_fk,responsible_people_id_fk) VALUES (:structure_id_fk,:contract_id_fk,:responsible_people_id_fk)";
						int len = obj.getContracts_id_fk().length;
						
						int size = 0;
						if(!StringUtils.isEmpty(obj.getContracts_id_fk()) && obj.getContracts_id_fk().length > 0) {
	    					obj.setContracts_id_fk(CommonMethods.replaceEmptyByNullInSringArray(obj.getContracts_id_fk()));
	    					if(size < obj.getContracts_id_fk().length) {
	    						size = obj.getContracts_id_fk().length;
	    					}
	    				}
						if(size == 1 ) {
				    		String joined = String.join(",", obj.getResponsible_people_id_fks());
				    		String[] strArray = new String[] {joined};
				    		obj.setResponsible_people_id_fks(strArray);
				    	}
						if(!StringUtils.isEmpty(obj.getResponsible_people_id_fks()) && obj.getResponsible_people_id_fks().length > 0) {
	    					obj.setResponsible_people_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getResponsible_people_id_fks()));
	    					if(size < obj.getResponsible_people_id_fks().length) {
	    						size = obj.getResponsible_people_id_fks().length;
	    					}
	    				}
						for (int i = 0; i < size; i++){
							List<String> executives = null;
							if(!StringUtils.isEmpty(obj.getResponsible_people_id_fks()[i])){
								if(obj.getResponsible_people_id_fks()[i].contains(",")) {
									executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[i].split(",")));
								}else {
									executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[i]));
								}
								for(String eObj : executives) {
									if(!eObj.equals("null") && !StringUtils.isEmpty(obj.getContracts_id_fk()[i]) &&  !StringUtils.isEmpty(eObj)) {
										Structure fileObj = new Structure();
										fileObj.setStructure_id_fk(obj.getStructure_id());
										fileObj.setContract_id_fk(obj.getContracts_id_fk()[i]);
										fileObj.setResponsible_people_id_fk(eObj);
										paramSource = new BeanPropertySqlParameterSource(fileObj);
										namedParamJdbcTemplate.update(qry3, paramSource);
									}
								}
							}
						
						}			
			
					}	
					
					/********************************************************************************/
					
				/*	if(!StringUtils.isEmpty(obj.getResponsible_people_id_fk())) {
						String userIds[] = new String[0];
						if(obj.getResponsible_people_id_fk().contains(",")) {
							userIds = obj.getResponsible_people_id_fk().split(",");
						}else {
							userIds = new String[]{obj.getResponsible_people_id_fk()};
						}
						String messageType = "FOB";
						String redirect_url = "/get-fob?fob_id="+obj.getStructure_id();
						String message = "New FOB "+obj.getStructure_name() + " & "+ obj.getStructure_id() +" is added under contract(s) "+obj.getContract_name()+" on PMIS ";
						 
						Messages msgObj = new Messages();
						msgObj.setUser_ids(userIds);
						msgObj.setMessage_type(messageType);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage(message);
						messagesDao.addMessages(msgObj,namedParamJdbcTemplate);
					}*/
					/********************************************************************************/
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getUser_id());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("Works");
					formHistory.setForm_name("Add Structure Form");
					formHistory.setForm_action_type("Add");
					formHistory.setForm_details("Structure for "+obj.getWork_id_fk() + " Added");
					formHistory.setWork(obj.getWork_id_fk());
					//formHistory.setContract(obj.getContract_id_fk());
					
					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					/********************************************************************************/
				
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateStructureForm(Structure obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);		
			String structure_id= null;
			String qry = "UPDATE structure set "
					+ "structure =:structure,structure_name =:structure_name,structure_type_fk =:structure_type_fk,target_date =:target_date,"
					+ "construction_start_date =:construction_start_date,actual_completion_date =:actual_completion_date,commissioning_date =:commissioning_date,"
					+ "estimated_cost =:estimated_cost,completion_cost =:completion_cost,work_status_fk =:work_status_fk,latitude =:latitude,longitude =:longitude"
					+ ",remarks =:remarks,revised_completion =:revised_completion,estimated_cost_units =:estimated_cost_units,completion_cost_units =:completion_cost_units "
					+ " where structure_id= :structure_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			if(flag) {
				
				String deleteQry = "DELETE from structure_details where structure_id_fk = :structure_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
				if(flag && !StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0) {
					obj.setStructure_details(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_details()));
				}
				if(flag && !StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0) {
					obj.setStructure_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_values()));
				}
				
				String[] fobDetailNames = obj.getStructure_details();
				String[] fobDetailValues = obj.getStructure_values();
				
				String qryFOBDetail = "INSERT INTO structure_details (structure_id_fk,structure_detail,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail,
		            new BatchPreparedStatementSetter() {			                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {	
		                	int k = 1;
							ps.setString(k++, obj.getStructure_id());
							ps.setString(k++, fobDetailNames.length > 0 ?fobDetailNames[i]:null);
							ps.setString(k++, fobDetailValues.length > 0 ?fobDetailValues[i]:null);
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                	int arraySize = 0;
		    				if(!StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0) {
		    					obj.setStructure_details(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_details()));
		    					if(arraySize < obj.getStructure_details().length) {
		    						arraySize = obj.getStructure_details().length;
		    					}
		    				}
		    				if(!StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0) {
		    					obj.setStructure_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_values()));
		    					if(arraySize < obj.getStructure_values().length) {
		    						arraySize = obj.getStructure_values().length;
		    					}
		    				}
		                    return arraySize;
		                }
		            });
					String docDeleteQry = "DELETE from structure_documents where structure_id_fk = :structure_id";		 
					paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(docDeleteQry, paramSource);
					
					//String file_insert_qry = "INSERT into  fob_files ( fob_id_fk, attachment,created_date,fob_file_type_fk,name) VALUES (:fob_id,:attachment,:created_date,:fob_file_type_fk,:name)";
					String document_insert_qry = "INSERT into  structure_documents ( structure_id_fk, attachment,structure_file_type_fk,name,created_date) VALUES (:structure_id,:attachment,:structure_file_type_fk,:name,CURDATE())";

					int arraySize =0, docArrSize = 0;
				/*	if (!StringUtils.isEmpty(obj.getFileNamesStructure()) && obj.getFileNamesStructure().length > 0) {
						obj.setFileNamesStructure(CommonMethods.replaceEmptyByNullInSringArray(obj.getFileNamesStructure()));
						if (docArrSize < obj.getFileNamesStructure().length) {
							docArrSize = obj.getFileNamesStructure().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getStructureDoc_file_types()) && obj.getStructureDoc_file_types().length > 0) {
						obj.setStructureDoc_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureDoc_file_types()));
						if (docArrSize < obj.getStructureDoc_file_types().length) {
							docArrSize = obj.getStructureDoc_file_types().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getDocumentNamesStructure()) && obj.getDocumentNamesStructure().length > 0) {
						obj.setDocumentNamesStructure(CommonMethods.replaceEmptyByNullInSringArray(obj.getDocumentNamesStructure()));
						if (docArrSize < obj.getDocumentNamesStructure().length) {
							docArrSize = obj.getDocumentNamesStructure().length;
						}
					}*/
					
					if (!StringUtils.isEmpty(obj.getStructureFileNames()) && obj.getStructureFileNames().length > 0) {
						obj.setStructureFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureFileNames()));
						if (docArrSize < obj.getStructureFileNames().length) {
							docArrSize = obj.getStructureFileNames().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getStructure_file_types()) && obj.getStructure_file_types().length > 0) {
						obj.setStructure_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_types()));
						if (docArrSize < obj.getStructure_file_types().length) {
							docArrSize = obj.getStructure_file_types().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getStructureDocumentNames()) && obj.getStructureDocumentNames().length > 0) {
						obj.setStructureDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureDocumentNames()));
						if (docArrSize < obj.getStructureDocumentNames().length) {
							docArrSize = obj.getStructureDocumentNames().length;
						}
					}
					
					/*if (!StringUtils.isEmpty(obj.getStructure_file_types()) && obj.getStructure_file_types().length > 0) {
						obj.setStructure_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_types()));
						if (arraySize < obj.getStructure_file_types().length) {
							arraySize = obj.getStructure_file_types().length;
						} 
					}*/
					for (int i = 0; i < docArrSize; i++) {
						if (!StringUtils.isEmpty(obj.getStructureFiles()) && obj.getStructureFiles().length > 0) {
							MultipartFile multipartFile = obj.getStructureFiles()[i];
							if (((null != multipartFile && !multipartFile.isEmpty()) || (!StringUtils.isEmpty(obj.getStructureFileNames()[i]))) 
									&& !StringUtils.isEmpty(obj.getStructure_file_types()[i]) && !StringUtils.isEmpty(obj.getStructureDocumentNames()[i]))  {
								String saveDirectory =  CommonConstants2.STRUCTURE_FILE_SAVING_PATH ;
								String fileName = obj.getStructureFileNames()[i];
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								Structure fileObj = new Structure();
								fileObj.setAttachment(fileName);
								
								String fob_file_type_fk = obj.getStructure_file_types()[i];
								String name = obj.getStructureDocumentNames()[i];
								
								fileObj.setStructure_id(obj.getStructure_id());
								fileObj.setStructure_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(document_insert_qry, paramSource);
							}
						}
					}
				/*	for (int i = 0; i < arraySize; i++) {
						if (!StringUtils.isEmpty(obj.getStructureFiles()) && obj.getStructureFiles().length > 0) {
							MultipartFile multipartFile = obj.getStructureFiles()[i];
							if ((null != multipartFile && !multipartFile.isEmpty())) {
								String saveDirectory = CommonConstants2.FOB_GALLERY_SAVING_PATH + obj.getStructure_id() + File.separator;
								String fileName = obj.getStructureFileNames()[i];
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								FOB fileObj = new FOB();
								fileObj.setAttachment(fileName);
								//fileObj.setStructure_file_type_fk(obj.getStructure_file_types()[i]);
								String created_date = null;
								if (!StringUtils.isEmpty(obj.getCreated_dates()) && obj.getCreated_dates().length > 0) {
									created_date = obj.getCreated_dates()[i];
								}
								if(!StringUtils.isEmpty(created_date)) {
									created_date = DateParser.parse(created_date);
								}else {
									SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
									created_date = sqlDate.format(new Date());
								}
								
								String fob_file_type_fk = obj.getStructure_file_types()[i];
								String name = obj.getStructureDocumentNames()[i];
								
								fileObj.setCreated_date(created_date);
								fileObj.setStructure_id(obj.getStructure_id());
								fileObj.setStructure_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(file_insert_qry, paramSource);
							}
						}
					}
					*/
					
					/********************************************************************/
					
					
					/*if(!StringUtils.isEmpty(obj.getContracts_id_fk())) {
						for(int k =0; k<obj.getContracts_id_fk().length; k++) {
							String contarctId = obj.getContracts_id_fk()[k];
							String qry3 = "INSERT into fob_contract (fob_id_fk,contract_id_fk) VALUES (:fob_id_fk,:contract_id_fk)";
							if(contarctId.contains(",")) {
								String[] ids = contarctId.split(",");					
								for (int i = 0; i < ids.length; i++) {
									FOB fileObj = new FOB();
									fileObj.setContract_id_fk(!StringUtils.isEmpty(ids[i])?ids[i]:null);
									fileObj.setStructure_id_fk(obj.getStructure_id());
									paramSource = new BeanPropertySqlParameterSource(fileObj);
									namedParamJdbcTemplate.update(qry3, paramSource);
								}			
							}else {
								FOB fileObj = new FOB();
								fileObj.setContract_id_fk(contarctId);
								fileObj.setStructure_id_fk(obj.getStructure_id());
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(qry3, paramSource);
							}
						}	
					}*/
					String conDeleteQry = "DELETE from structure_contract_responsible_people where structure_id_fk = :structure_id";		 
					paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(conDeleteQry, paramSource);
					
					if(!StringUtils.isEmpty(obj.getContracts_id_fk()) && obj.getContracts_id_fk().length > 0) {
						String qry3 = "INSERT into structure_contract_responsible_people (structure_id_fk,contract_id_fk,responsible_people_id_fk) VALUES (:structure_id_fk,:contract_id_fk,:responsible_people_id_fk)";
						int len = obj.getContracts_id_fk().length;
						
						int size = 0;
						if(!StringUtils.isEmpty(obj.getContracts_id_fk()) && obj.getContracts_id_fk().length > 0) {
	    					obj.setContracts_id_fk(CommonMethods.replaceEmptyByNullInSringArray(obj.getContracts_id_fk()));
	    					if(size < obj.getContracts_id_fk().length) {
	    						size = obj.getContracts_id_fk().length;
	    					}
	    				}
						if(size == 1 ) {
				    		String joined = String.join(",", obj.getResponsible_people_id_fks());
				    		String[] strArray = new String[] {joined};
				    		obj.setResponsible_people_id_fks(strArray);
				    	}
						if(!StringUtils.isEmpty(obj.getResponsible_people_id_fks()) && obj.getResponsible_people_id_fks().length > 0) {
	    					obj.setResponsible_people_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getResponsible_people_id_fks()));
	    					if(size < obj.getResponsible_people_id_fks().length) {
	    						size = obj.getResponsible_people_id_fks().length;
	    					}
	    				}
						for (int i = 0; i < size; i++){
							List<String> executives = null;
							if(!StringUtils.isEmpty(obj.getResponsible_people_id_fks()[i])){
								if(obj.getResponsible_people_id_fks()[i].contains(",")) {
									executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[i].split(",")));
								}else {
									executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[i]));
								}
								for(String eObj : executives) {
									if(!eObj.equals("null") && !StringUtils.isEmpty(obj.getContracts_id_fk()[i]) &&  !StringUtils.isEmpty(eObj)) {
										Structure fileObj = new Structure();
										fileObj.setStructure_id_fk(obj.getStructure_id());
										fileObj.setContract_id_fk(obj.getContracts_id_fk()[i]);
										fileObj.setResponsible_people_id_fk(eObj);
										paramSource = new BeanPropertySqlParameterSource(fileObj);
										namedParamJdbcTemplate.update(qry3, paramSource);
									}
								}
							}
						
						}			
			
					}	
					
					/********************************************************************************/
					
				/*	if(!StringUtils.isEmpty(obj.getResponsible_people_id_fk())) {
						String userIds[] = new String[0];
						if(obj.getResponsible_people_id_fk().contains(",")) {
							userIds = obj.getResponsible_people_id_fk().split(",");
						}else {
							userIds = new String[]{obj.getResponsible_people_id_fk()};
						}
						String messageType = "FOB";
						String redirect_url = "/get-fob?fob_id="+obj.getStructure_id();
						String message = "New FOB "+obj.getStructure_name() + " & "+ obj.getStructure_id() +" is added under contract(s) "+obj.getContract_name()+" on PMIS ";
						 
						Messages msgObj = new Messages();
						msgObj.setUser_ids(userIds);
						msgObj.setMessage_type(messageType);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage(message);
						messagesDao.addMessages(msgObj,namedParamJdbcTemplate);
					}*/
					/********************************************************************************/
				
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getUser_id());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("Works");
					formHistory.setForm_name("Update Structure Form");
					formHistory.setForm_action_type("Update");
					formHistory.setForm_details("Structure for "+obj.getWork_id_fk() + " Updated");
					formHistory.setWork(obj.getWork_id_fk());
					//formHistory.setContract(obj.getContract_id_fk());
					
					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					/********************************************************************************/
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
}
