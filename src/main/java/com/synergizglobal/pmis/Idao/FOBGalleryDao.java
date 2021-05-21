package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.FOBGallery;

public interface FOBGalleryDao {

	List<FOBGallery> getFOBGalleryList(String fob_id) throws Exception;

}
