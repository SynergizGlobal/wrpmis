package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.WbsTree;

public interface WbsTreeService {

	List<WbsTree> getLevel4List(WbsTree obj) throws Exception;

	List<WbsTree> getContractsList(WbsTree obj) throws Exception;

}
