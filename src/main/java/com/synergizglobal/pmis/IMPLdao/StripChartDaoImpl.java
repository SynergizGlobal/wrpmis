package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StripChartDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class StripChartDaoImpl implements StripChartDao {
	@Autowired
	DataSource dataSource;
	
}
