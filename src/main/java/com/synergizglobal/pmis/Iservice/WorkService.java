package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;

public interface WorkService {

	

	public List<Work> getworkList()throws Exception;

	public Work editWork(String workId,Work work)throws Exception;

	public boolean updateWork(Work work)throws Exception;

	public boolean addWork(Work work)throws Exception;

	public List<Railway> getRailwayList()throws Exception;
}
