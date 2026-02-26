package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.BankNameDao;
import com.synergizglobal.wrpmis.reference.Iservice.BankNameService;
import com.synergizglobal.wrpmis.reference.model.Bank;
@Service
public class BankNameServiceImpl implements BankNameService{

	@Autowired
	BankNameDao dao;

	@Override
	public List<Bank> getBankNamesList() throws Exception {
		return dao.getBankNamesList();
	}

	@Override
	public boolean addBankName(Bank obj) throws Exception {
		return dao.addBankName(obj);
	}

	@Override
	public boolean updateBankName(Bank obj) throws Exception {
		return dao.updateBankName(obj);
	}

	@Override
	public Bank getBankNameDetails(Bank obj) throws Exception {
		return dao.getBankNameDetails(obj);
	}

	@Override
	public boolean deleteBankName(Bank obj) throws Exception {
		return dao.deleteBankName(obj);
	}
}
