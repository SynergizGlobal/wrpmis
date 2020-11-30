package com.synergizglobal.pmis.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.BudgetDao;
import com.synergizglobal.pmis.Idao.RiskDao;
import com.synergizglobal.pmis.Iservice.RiskService;

@Service
public class RiskServiceImpl implements RiskService{

	@Autowired
	RiskDao dao;
}
