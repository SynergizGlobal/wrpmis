package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Design;

public interface DesignService {

	public List<Design> structureList()throws Exception;

	public List<Design> design(Design obj)throws Exception;

	public List<Design> drawingTypeList()throws Exception;

	public List<Design> getDepartmentList()throws Exception;

	public Design getDesignDetails(Design obj)throws Exception;

	
	

}
