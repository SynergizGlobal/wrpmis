package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.FOBGallery;

public interface FOBGalleryDao {

	List<FOBGallery> getFOBGalleryList(String fob_id) throws Exception;

}
