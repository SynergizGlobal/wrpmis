package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;

public interface WorkDao {

	public List<Work> getworkList() throws Exception;

	public Work editWork(String workId,Work work)throws Exception;

	public boolean updateWork(Work work)throws Exception;

	public boolean addWork(Work work)throws Exception;

	public List<Railway> getRailwayList()throws Exception;

}
