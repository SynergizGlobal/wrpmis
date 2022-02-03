package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRBoundarywallStatusDao {

	TrainingType getRRBoundarywallStatusDetails(TrainingType obj) throws Exception ;

	boolean addRRBoundarywallStatus(TrainingType obj) throws Exception ;

	boolean updateRRBoundarywallStatus(TrainingType obj) throws Exception ;

	boolean deleteRRBoundarywallStatus(TrainingType obj) throws Exception ;

}
