package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.FOBGallery;

public interface FOBGalleryService {

	List<FOBGallery> getFOBGalleryList(String fob_id) throws Exception;

}
