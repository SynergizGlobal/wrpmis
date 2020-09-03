package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.TableauDashboard;


public interface HomeDao {	
	public List<TableauDashboard> getDashboardsList() throws Exception;
	public List<Forms> getFormsList(String base) throws Exception;
}
