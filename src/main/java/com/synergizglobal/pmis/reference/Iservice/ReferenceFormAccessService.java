package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.ReferenceForms;

public interface ReferenceFormAccessService {

	public List<ReferenceForms> getReferenceForm(ReferenceForms obj) throws Exception;

	public List<ReferenceForms> getReferenceFormssList(ReferenceForms obj) throws Exception;

	public List<ReferenceForms> getModuleNmae() throws Exception;

	public ReferenceForms getReferenceFormDetails(ReferenceForms obj) throws Exception;

	public boolean addReferenceForm(ReferenceForms obj) throws Exception;

	public boolean updateReferenceForm(ReferenceForms obj) throws Exception;

}
