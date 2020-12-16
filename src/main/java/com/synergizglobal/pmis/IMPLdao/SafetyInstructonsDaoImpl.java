package com.synergizglobal.pmis.IMPLdao;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.Idao.SafetyInstructonsDao;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.SafetyEquipment;

@Repository
public class SafetyInstructonsDaoImpl implements SafetyInstructonsDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<SafetyEquipment> getSafetyInstructionsList(SafetyEquipment obj) throws Exception {
		List<SafetyEquipment> objsList = null;
		try {
		String qryDetails = "select safety_instructions_id,document_name,document_url from safety_instructions" ;
		objsList = jdbcTemplate.query(qryDetails, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));	
		obj.setSafetyEquipments(objsList);
		
	}catch(Exception e) {
		e.printStackTrace();
		throw new Exception(e.getMessage());
	}
	return objsList;
	}

	@Override
	public boolean updateSafetyInstructions(SafetyEquipment obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		int count = 0;
		try {
				NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				String deleteQry = "DELETE from safety_instructions ";		 
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
			
		if(count > 0) {
			flag = true;
		}
		if(flag) {
			con = dataSource.getConnection();
			String qry = "INSERT INTO safety_instructions"
					+ "(document_name,document_url) VALUES (?,?)";
			stmt = con.prepareStatement(qry);
			int	arraySize = 0;
			if( !StringUtils.isEmpty(obj.getDocument_names()) && obj.getDocument_names().length > 0) {
				obj.setDocument_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getDocument_names()));
				if(arraySize < obj.getDocument_names().length) {
					arraySize = obj.getDocument_names().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getSafetyEquipmentFileNames()) && obj.getSafetyEquipmentFileNames().length > 0) {
				obj.setSafetyEquipmentFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafetyEquipmentFileNames()));
				if(arraySize < obj.getSafetyEquipmentFileNames().length) {
					arraySize = obj.getSafetyEquipmentFileNames().length;
				}
			}
			String saveDirectory = CommonConstants.SAFETY_INSTRUCTIONS__FILE_SAVING_PATH ;
			List<MultipartFile> files = new ArrayList<MultipartFile>();
			for (int i = 0; i < arraySize; i++) {
				    int k = 1;
				    String docFileName = null;
				    String docFileName1 = null;
				    
				    MultipartFile file = obj.getSafetyFile()[i];
					if (null != file && !file.isEmpty()){
						String fileName = file.getOriginalFilename();
						String fileName_new = obj.getDocument_names()[i] +"."+ fileName.split("\\.")[1];
						docFileName1 = fileName_new;
						FileUploads.singleFileSaving(file, saveDirectory, docFileName1);
					} else {
						docFileName  = (obj.getSafetyEquipmentFileNames().length > 0)?obj.getSafetyEquipmentFileNames()[i]:null;
					}
					files.add(file);
					stmt.setString(k++,(obj.getDocument_names().length > 0)?obj.getDocument_names()[i]:null);
					if (null != docFileName1 && !docFileName1.isEmpty()){
						stmt.setString(k++,saveDirectory+docFileName1);
					}else {
						stmt.setString(k++,docFileName);
					}
					
					stmt.addBatch();
			}	
			int[] insertCount = stmt.executeBatch();
		
		}
		}catch(Exception e){ 
				e.printStackTrace();
				throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}
		return flag;
	}
	
	
}

