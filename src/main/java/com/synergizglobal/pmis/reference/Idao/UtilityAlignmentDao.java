package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;

public interface UtilityAlignmentDao {

	public List<Safety> getUtilityAlignmentsList() throws Exception;

	public boolean addUtilityAlignment(Safety obj) throws Exception;
}
