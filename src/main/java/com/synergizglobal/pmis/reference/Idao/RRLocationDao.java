package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.Risk;

public interface RRLocationDao {

	Risk getRRLocationDetails(Risk obj);

	boolean addRRLocation(Risk obj);

	boolean deleteRRLocation(Risk obj);

	boolean updateRRLocation(Risk obj);

}
