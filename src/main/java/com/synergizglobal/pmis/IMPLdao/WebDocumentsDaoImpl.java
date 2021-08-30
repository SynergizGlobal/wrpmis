package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.WebDocumentsDao;
import com.synergizglobal.pmis.model.WebDocuments;

@Repository
public class WebDocumentsDaoImpl implements WebDocumentsDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<WebDocuments> getWebDocumentTypes(WebDocuments obj) throws Exception {
		List<WebDocuments> objsList = null;
		try {
			String categoriesQry ="select type from web_documents_type";
			
			objsList = jdbcTemplate.query( categoriesQry, new BeanPropertyRowMapper<WebDocuments>(WebDocuments.class));	
			for (WebDocuments doc : objsList) {
				if(!StringUtils.isEmpty(doc.getType())) {
					String documentType = doc.getType();
					String modified_type = documentType.replaceAll("-", " ").toLowerCase();
					doc.setModified_type("web-documents/"+modified_type);
				}
			}
						
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<WebDocuments> getWebDocuments(WebDocuments obj) throws Exception {
		List<WebDocuments> objsList = null;
		try {
			String categoriesQry ="select id as category_id,category from web_documents_category where type_fk = ?";
			
			String docsQry ="select wd.id as web_document_id,title,file_name,category_id_fk,DATE_FORMAT(date_of_issue,'%d-%m-%Y') as date_of_issue,DATE_FORMAT(date_of_issue,'%d-%b-%y') as date_of_issue_ddmmmyy,DATE_FORMAT(upload_date,'%d-%b-%y') as upload_date,uploaded_by,"
					+ "wdc.id as category_id,type_fk,type_fk as type,category "
					+ "from web_documents wd "
					+ "LEFT JOIN web_documents_category wdc ON wd.category_id_fk = wdc.id "
					+ "where category_id_fk = ?" ;
			
			objsList = jdbcTemplate.query( categoriesQry,new Object[] {obj.getType()}, new BeanPropertyRowMapper<WebDocuments>(WebDocuments.class));	
			for (WebDocuments doc : objsList) {
				List<WebDocuments> docsList = jdbcTemplate.query( docsQry, new Object[] {doc.getCategory_id()}, new BeanPropertyRowMapper<WebDocuments>(WebDocuments.class));
				doc.setWebDocumentsList(docsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addWebDocument(WebDocuments obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO web_documents"
					+ "(title,file_name,category_id_fk,date_of_issue,uploaded_by)"
					+ "VALUES"
					+ "(:title,:file_name,:category_id_fk,:date_of_issue,:uploaded_by)";
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
	public List<WebDocuments> getWebDocumentCategoriesList(WebDocuments obj) throws Exception {
		List<WebDocuments> objsList = null;
		try {
			
			String categoriesQry ="select id as category_id,category from web_documents_category where type_fk = ?";			
			objsList = jdbcTemplate.query( categoriesQry,new Object[] {obj.getType()}, new BeanPropertyRowMapper<WebDocuments>(WebDocuments.class));	
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean updateWebDocument(WebDocuments obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "UPDATE web_documents"
					+ " SET title=:title,file_name=:file_name,category_id_fk=:category_id_fk,date_of_issue=:date_of_issue,uploaded_by=:uploaded_by "
					+ "WHERE id=:web_document_id";
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
	public boolean deleteWebDocument(WebDocuments obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "DELETE FROM web_documents "
					+ "WHERE id=:web_document_id";
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

}
