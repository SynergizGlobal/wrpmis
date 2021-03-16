package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.ReferenceForms;

public interface ReferenceFormsAccessService {

	public List<ReferenceForms> getReferenceForms() throws Exception;

	public List<ReferenceForms> getReferencePagesList(ReferenceForms obj) throws Exception;

}
