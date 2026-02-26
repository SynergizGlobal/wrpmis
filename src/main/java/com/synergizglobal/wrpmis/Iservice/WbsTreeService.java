package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.WbsTree;

public interface WbsTreeService {

	List<WbsTree> getLevel4List(WbsTree obj) throws Exception;

	List<WbsTree> getContractsList(WbsTree obj) throws Exception;

}
