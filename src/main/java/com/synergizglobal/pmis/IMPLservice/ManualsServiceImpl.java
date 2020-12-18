package com.synergizglobal.pmis.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ManualsDao;
import com.synergizglobal.pmis.Iservice.ManualsService;

@Service
public class ManualsServiceImpl implements ManualsService {

	@Autowired
	ManualsDao dao;
}
