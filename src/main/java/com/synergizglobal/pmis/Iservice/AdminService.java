package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Admin;

public interface AdminService {

	public Admin getAdminDetails(Admin obj) throws Exception;

	public boolean addAdmin(Admin admin) throws Exception;

	public boolean updateAdmin(Admin admin) throws Exception;

	public List<Admin> getAdminList() throws Exception;

	public List<Admin> getStatus(Admin obj) throws Exception;

}
