package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.FOBGallery;

public interface FOBGalleryService {

	List<FOBGallery> getFOBGalleryList(String fob_id) throws Exception;

}
