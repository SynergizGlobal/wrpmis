package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Safety;

public interface DesignDao {

	public List<Design> structureList()throws Exception;

	public List<Design> design(Design obj)throws Exception;

	public List<Design> drawingTypeList()throws Exception;

	public List<Design> getDepartmentList()throws Exception;

	public Design getDesignDetails(Design obj)throws Exception;

	public List<Design> getContractList()throws Exception;

	public List<Design> getPreparedByList()throws Exception;

	public List<Design> getRevisionStatuses()throws Exception;

	public boolean addDesign(Design obj)throws Exception;

}
