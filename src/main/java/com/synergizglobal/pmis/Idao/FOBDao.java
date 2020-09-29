package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.FOB;

public interface FOBDao {

	List<FOB> getFOBList(FOB obj) throws Exception;

	boolean addFOB(FOB obj) throws Exception;

	FOB getFOB(FOB obj) throws Exception;

	boolean updateFOB(FOB obj) throws Exception;

	boolean deleteFOB(FOB obj) throws Exception;

}
