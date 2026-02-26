package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface LeftMenuService {

	List<TrainingType> getLeftMenuList(TrainingType obj) throws Exception;

	List<TrainingType> getStatusFilterList(TrainingType obj) throws Exception;

	List<TrainingType> getParentFilterList(TrainingType obj) throws Exception;

	boolean addLeftMenu(TrainingType obj) throws Exception;

	boolean updateLeftMenu(TrainingType obj) throws Exception;

	boolean deleteLeftMenu(TrainingType obj) throws Exception;

	List<TrainingType> getParentList() throws Exception;

	List<TrainingType> getStatusList() throws Exception;

}
