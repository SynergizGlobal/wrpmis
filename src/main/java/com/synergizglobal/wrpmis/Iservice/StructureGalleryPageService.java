package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.Structure;

public interface StructureGalleryPageService {

	List<Structure> getGalleryList(Structure obj) throws Exception;

	List<Structure> getMonthList(Structure obj) throws Exception;

	List<Structure> getStructuresList(Structure obj) throws Exception;

	List<Structure> getStructureIdList(Structure obj) throws Exception;

	Structure getWorkShortName(Structure obj) throws Exception;

}
