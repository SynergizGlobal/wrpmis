package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

public interface WorkService {

	public Work getWork(String workId,Work work)throws Exception;

	public boolean updateWork(Work work)throws Exception;

	public boolean addWork(Work work)throws Exception;

	public List<Railway> getRailwayList()throws Exception;

	public List<Railway> getExcecuteList()throws Exception;

	public boolean deleteRow(String workId,Work work)throws Exception;

	public List<Year> getYearList()throws Exception;

	public List<Work> getWorkList(Work work) throws Exception;

	/**public int getTotalRecords(Work obj, String searchParameter) throws Exception;

	public List<Work> getWorksList(Work obj, int startIndex, int offset, String searchParameter) throws Exception;
	*/
}
