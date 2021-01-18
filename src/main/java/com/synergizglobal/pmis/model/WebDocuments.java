package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class WebDocuments {
	private String id,title,file_name,category_id_fk,upload_date,uploaded_by,type_fk,category,type,web_document_id,category_id;
	
	private List<WebDocuments> webDocumentsList;
	
	private MultipartFile webDocument;

	public List<WebDocuments> getWebDocumentsList() {
		return webDocumentsList;
	}

	public void setWebDocumentsList(List<WebDocuments> webDocumentsList) {
		this.webDocumentsList = webDocumentsList;
	}

	public MultipartFile getWebDocument() {
		return webDocument;
	}

	public void setWebDocument(MultipartFile webDocument) {
		this.webDocument = webDocument;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getCategory_id_fk() {
		return category_id_fk;
	}

	public void setCategory_id_fk(String category_id_fk) {
		this.category_id_fk = category_id_fk;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public String getUploaded_by() {
		return uploaded_by;
	}

	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}

	public String getType_fk() {
		return type_fk;
	}

	public void setType_fk(String type_fk) {
		this.type_fk = type_fk;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getWeb_document_id() {
		return web_document_id;
	}

	public void setWeb_document_id(String web_document_id) {
		this.web_document_id = web_document_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
