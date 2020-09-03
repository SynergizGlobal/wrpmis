package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.TableauDashboard;

public interface HomeService {

	public List<TableauDashboard> getDashboardsList() throws Exception ;
	
	public List<Forms> getFormsList(String base) throws Exception;
	
}
