package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.Risk;

public interface RRLocationService {

	Risk getRRLocationDetails(Risk obj) throws Exception;

	boolean addRRLocation(Risk obj) throws Exception;

	boolean deleteRRLocation(Risk obj) throws Exception;

	boolean updateRRLocation(Risk obj) throws Exception;

}
