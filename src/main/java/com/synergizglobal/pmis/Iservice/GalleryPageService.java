package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Structure;

public interface GalleryPageService {

	List<Structure> getGalleryList(Structure obj) throws Exception;

	List<Structure> getMonthList(Structure obj) throws Exception;

	List<Structure> getStructuresList(Structure obj) throws Exception;

	List<Structure> getStructureIdList(Structure obj) throws Exception;

}
