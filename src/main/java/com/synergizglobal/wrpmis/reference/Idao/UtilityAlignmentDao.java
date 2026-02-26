package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;

public interface UtilityAlignmentDao {

	public List<Safety> getUtilityAlignmentsList() throws Exception;

	public boolean addUtilityAlignment(Safety obj) throws Exception;
}
