package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RRBoundarywallStatusDao {

	TrainingType getRRBoundarywallStatusDetails(TrainingType obj) throws Exception ;

	boolean addRRBoundarywallStatus(TrainingType obj) throws Exception ;

	boolean updateRRBoundarywallStatus(TrainingType obj) throws Exception ;

	boolean deleteRRBoundarywallStatus(TrainingType obj) throws Exception ;

}
