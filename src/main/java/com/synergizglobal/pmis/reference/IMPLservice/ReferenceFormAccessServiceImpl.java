package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.ReferenceFormAccessDao;
import com.synergizglobal.pmis.reference.Iservice.ReferenceFormAccessService;
import com.synergizglobal.pmis.reference.model.ReferenceForms;

@Service
public class ReferenceFormAccessServiceImpl implements ReferenceFormAccessService{

	@Autowired
	ReferenceFormAccessDao dao;
	
	@Override
	public List<ReferenceForms> getReferenceForm(ReferenceForms obj) throws Exception {
		return dao.getReferenceForm(obj);
	}

	@Override
	public List<ReferenceForms> getReferenceFormssList(ReferenceForms obj) throws Exception {
		return dao.getReferenceFormssList(obj);
	}

	@Override
	public List<ReferenceForms> getModuleNmae() throws Exception {
		return dao.getModuleNmae();
	}

	@Override
	public ReferenceForms getReferenceFormDetails(ReferenceForms obj) throws Exception {
		return dao.getReferenceFormDetails(obj);
	}

	@Override
	public boolean addReferenceForm(ReferenceForms obj) throws Exception {
		return dao.addReferenceForm(obj);
	}

	@Override
	public boolean updateReferenceForm(ReferenceForms obj) throws Exception {
		return dao.updateReferenceForm(obj);
	}

	
}
