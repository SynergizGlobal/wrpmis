package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.RandR;

public interface RandRDao {
	public List<RandR> getRandRList() throws Exception;
	
	public List<RandR> getRandRSatausList() throws Exception;
	
	public RandR getRandR(String randRId) throws Exception;
	
	public boolean updateRandR(RandR obj) throws Exception;
	
	public boolean updateRandRStatus(RandR obj) throws Exception;
}
