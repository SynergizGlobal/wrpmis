package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.FOB;

public interface FOBService {

	List<FOB> getFOBList(FOB obj) throws Exception;

	boolean addFOB(FOB obj) throws Exception;

	FOB getFOB(FOB obj) throws Exception;

	boolean updateFOB(FOB obj) throws Exception;

	boolean deleteFOB(FOB obj) throws Exception;

}
