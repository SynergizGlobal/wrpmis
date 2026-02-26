package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.ReferenceForms;

public interface ReferenceFormsAccessService {

	public List<ReferenceForms> getReferenceForms() throws Exception;

	public List<ReferenceForms> getReferencePagesList(ReferenceForms obj) throws Exception;

}
