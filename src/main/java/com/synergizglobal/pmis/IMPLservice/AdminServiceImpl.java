package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.AdminDao;
import com.synergizglobal.pmis.Iservice.AdminService;
import com.synergizglobal.pmis.model.Admin;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao dao;

	@Override
	public Admin getAdminDetails(Admin obj) throws Exception {
		return dao.getAdminDetails(obj);
	}

	@Override
	public boolean addAdmin(Admin admin) throws Exception {
		return dao.addAdmin(admin);
	}

	@Override
	public boolean updateAdmin(Admin admin) throws Exception {
		return dao.updateAdmin(admin);
	}

	@Override
	public List<Admin> getAdminList() throws Exception {
		return dao.getAdminList();
	}

	@Override
	public List<Admin> getStatus(Admin obj) throws Exception {
		return dao.getStatus(obj);
	}
}
