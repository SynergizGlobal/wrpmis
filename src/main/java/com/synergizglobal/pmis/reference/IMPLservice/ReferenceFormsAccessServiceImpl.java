package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Iservice.ReferenceFormsAccessDao;
import com.synergizglobal.pmis.reference.Iservice.ReferenceFormsAccessService;
import com.synergizglobal.pmis.reference.model.ReferenceForms;


@Service
public class ReferenceFormsAccessServiceImpl implements ReferenceFormsAccessService{
	@Autowired
	ReferenceFormsAccessDao dao;

	@Override
	public List<ReferenceForms> getReferenceForms() throws Exception {
		return dao.getReferenceForms();
	}

	@Override
	public List<ReferenceForms> getReferencePagesList(ReferenceForms obj) throws Exception {
		return dao.getReferencePagesList(obj);
	}
}
