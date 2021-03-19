package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.ReferenceForms;

public interface ReferenceFormsAccessDao {

	public List<ReferenceForms> getReferenceForms() throws Exception;

	public List<ReferenceForms> getReferencePagesList(ReferenceForms obj) throws Exception;

}
