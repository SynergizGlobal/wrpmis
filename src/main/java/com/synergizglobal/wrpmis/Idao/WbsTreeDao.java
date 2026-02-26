package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.WbsTree;

public interface WbsTreeDao {

	List<WbsTree> getLevel4List(WbsTree obj) throws Exception;

	List<WbsTree> getContractsList(WbsTree obj) throws Exception;

}
