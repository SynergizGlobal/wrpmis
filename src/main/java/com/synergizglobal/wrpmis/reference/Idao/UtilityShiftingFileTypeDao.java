package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;

public interface UtilityShiftingFileTypeDao {

	public List<Safety> getUtilityShiftingFileTypeList() throws Exception;
	public Safety getUtilityShiftingFileTypeList(Safety obj) throws Exception;

	public boolean addUtilityShiftingFileType(Safety obj) throws Exception;
	public boolean updateUtilityShiftingFileType(Safety obj) throws Exception;
	public boolean deleteUtilityShiftingFileType(Safety obj) throws Exception;
}
