package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

public interface WorkDao {

	public Work getWork(String workId,Work work)throws Exception;

	public boolean updateWork(Work work)throws Exception;

	public boolean addWork(Work work)throws Exception;

	public List<Railway> getRailwayList()throws Exception;
	public List<Work> getWorkStatusList(Work obj)throws Exception;


	public List<Railway> getExcecuteList()throws Exception;

	public boolean deleteRow(String workId, Work work)throws Exception;

	public List<Year> getYearList()throws Exception;
	
	public List<Work> getWorkList(Work work)throws Exception;
	/**
	public int getTotalRecords(Work obj, String searchParameter) throws Exception;

	public List<Work> getWorksList(Work obj, int startIndex, int offset, String searchParameter) throws Exception;
	 * @param obj 
*/

	public List<Work> getWorkRevisionsList(Work obj) throws Exception;

	public List<Work> getWorkFileTypes() throws Exception;

	public List<Work> getWorksList(Work obj) throws Exception;

	public List<Work> getWorktProjectsList(Work obj) throws Exception;

	public List<Work> getUnitsList() throws Exception;

	public List<Work> getWorkTypeList() throws Exception;

}
