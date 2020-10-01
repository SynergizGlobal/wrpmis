package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

public interface WorkDao {

	public List<Work> getworkList() throws Exception;

	public Work getWork(String workId,Work work)throws Exception;

	public boolean updateWork(Work work)throws Exception;

	public boolean addWork(Work work)throws Exception;

	public List<Railway> getRailwayList()throws Exception;

	public List<Railway> getExcecuteList()throws Exception;

	public boolean deleteRow(String workId, Work work)throws Exception;

	public List<Year> getYearList()throws Exception;
	
	public List<Work> getWorkList(Work work)throws Exception;


}
