package com.synergizglobal.wrpmis.IMPLdao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.Idao.HomeNewDao;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.Work;
import com.synergizglobal.wrpmis.model.WorkFeatures;

@Repository
public class HomeNewDaoImpl implements HomeNewDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Project> getProjects() throws Exception {
	    List<Project> objsList = null;
	    try {
/*	        String projectsQry =
	            "SELECT " +
	            "    distinct p.project_id, " +
	            "    p.proposed_length, " +
	            "    p.project_name, " +
	            "    p.project_type_id_fk AS project_type_id, " +
	            "    pt.project_type_name, " +
	            "( " +
	                    "SELECT SUM(ISNULL(pcl.commission_completed_length, 0)) " +
	                    "FROM project_commissioned_length pcl " +
	                    "WHERE pcl.project_id_fk = p.project_id " +
	                ") AS project_commissioned_total, " +
	                "( " +
	                    "SELECT SUM(ISNULL(p2.proposed_length,0)) " +
	                    "FROM project p2 " +
	                    "WHERE p2.project_type_id_fk = pt.project_type_id " +
	                ") AS proposed_length_by_type, " +	            
	            "    (SELECT SUM(ISNULL(scope,0)) " +
	            "     FROM p6_activities a2 " +
	            "     INNER JOIN contract c2 ON c2.contract_id = a2.contract_id_fk " +
	            "     WHERE c2.project_id_fk = p.project_id " +
	            "       AND a2.p6_activity_name = 'Track Laying') AS length, " +

	            "    (SELECT SUM(ISNULL(completed,0)) " +
	            "     FROM p6_activities a3 " +
	            "     INNER JOIN contract c3 ON c3.contract_id = a3.contract_id_fk " +
	            "     WHERE c3.project_id_fk = p.project_id " +
	            "       AND a3.p6_activity_name = 'Commissioning') AS commissioned_length, " +

	            "    COUNT(DISTINCT p.project_id) AS total_projects, " +
	            "    COUNT(DISTINCT CASE \n" + 
	            "               WHEN UPPER(LTRIM(RTRIM(p.project_status))) = 'OPEN' \n" + 
	            "               THEN p.project_id \n" + 
	            "             END) AS ongoing_projects, " +

	            "        (SELECT SUM(a1.scope)\n" + 
	            "     FROM project p1\n" + 
	            "     INNER JOIN contract c1 ON c1.project_id_fk = p1.project_id\n" + 
	            "     INNER JOIN p6_activities a1 ON a1.contract_id_fk = c1.contract_id\n" + 
	            "     WHERE a1.p6_activity_name = 'Track Laying'\n" + 
	            "       AND p1.project_type_id_fk = pt.project_type_id\n" + 
	            "    ) AS total_length, " +
	            "    SUM(CASE WHEN a.p6_activity_name IN ('Earthwork cutting','Earthwork filling') THEN ISNULL(a.scope,0) END) AS total_earthwork, " +
	            "    SUM(CASE WHEN a.p6_activity_name = 'Track Laying' THEN ISNULL(a.completed,0) END) AS completed_track, " +
	            "    SUM(CASE WHEN a.p6_activity_name = 'Commissioning' THEN ISNULL(a.completed,0) END) AS commissioned_length2, " +

	            "    SUM(DISTINCT CASE WHEN s.structure_type_fk = 'Major Bridge' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_major_bridges, " +
	            "    COUNT(DISTINCT CASE WHEN s.structure_type_fk = 'Major Bridge' THEN s.structure_id END) AS total_major_bridges, " +

	            "    SUM(DISTINCT CASE WHEN s.structure_type_fk = 'Minor Bridge' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_minor_bridges, " +
	            "    COUNT(DISTINCT CASE WHEN s.structure_type_fk = 'Minor Bridge' THEN s.structure_id END) AS total_minor_bridges, " +

	            "    SUM(DISTINCT CASE WHEN s.structure_type_fk = 'ROB' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_rob, " +
	            "    COUNT(DISTINCT CASE WHEN s.structure_type_fk = 'ROB' THEN s.structure_id END) AS total_rob, " +

	            "    SUM(DISTINCT CASE WHEN s.structure_type_fk = 'RUB' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_rub, " +
	            "    COUNT(DISTINCT CASE WHEN s.structure_type_fk = 'RUB' THEN s.structure_id END) AS total_rub " +

	            "FROM project p " +
	            "INNER JOIN project_type pt ON p.project_type_id_fk = pt.project_type_id " +
	            "LEFT JOIN project_commissioned_length pcl  ON pcl.project_id_fk = p.project_id " +
	            "LEFT JOIN contract c ON c.project_id_fk = p.project_id " +
	            "LEFT JOIN p6_activities a ON c.contract_id = a.contract_id_fk " +
	            "LEFT JOIN structure s ON p.project_id = s.project_id_fk and s.structure_id=a.structure_id_fk  " +

	            "GROUP BY p.project_id,proposed_length, p.project_name,project_type_id, p.project_type_id_fk, pt.project_type_name, p.project_status " +
	            "ORDER BY p.project_id";*/
	    	
	    	String projectsQry ="WITH Base AS (\n" + 
	    			"    SELECT \n" + 
	    			"        project_id,\n" + 
	    			"        project_name,\n" + 
	    			"        project_type_id,\n" + 
	    			"        project_type_name,\n" + 
	    			"        contract_id,\n" + 
	    			"        structure_type,\n" + 
	    			"        structure,\n" + 
	    			"        activity_id,\n" + 
	    			"        remarks,\n" + 
	    			"        unit,\n" + 
	    			"        scope,\n" + 
	    			"        completed,\n" + 
	    			"        weightage,\n" + 
	    			"		project_count,length,commissioned_length,\n" + 
	    			"\n" + 
	    			"        COUNT(activity_id) OVER (\n" + 
	    			"            PARTITION BY contract_id, structure_type, structure\n" + 
	    			"        ) AS activity_count,\n" + 
	    			"\n" + 
	    			"        SUM(weightage) OVER (\n" + 
	    			"            PARTITION BY contract_id, structure_type, structure\n" + 
	    			"        ) AS total_weightage\n" + 
	    			"    FROM projecttypewise_activities_view\n" + 
	    			"),\n" + 
	    			"\n" + 
	    			"Calc AS (\n" + 
	    			"    SELECT *,\n" + 
	    			"        CASE \n" + 
	    			"            WHEN remarks = 'Cumulative' THEN unit\n" + 
	    			"            ELSE 'Nos'\n" + 
	    			"        END AS unit_data,\n" + 
	    			"\n" + 
	    			"        CASE \n" + 
	    			"            WHEN scope IS NULL OR scope = 0 THEN 0\n" + 
	    			"            ELSE (ISNULL(completed, 0) * 1.0 / scope)\n" + 
	    			"                 * (weightage / NULLIF(total_weightage, 0))\n" + 
	    			"        END AS progress_per,\n" + 
	    			"\n" + 
	    			"        CASE \n" + 
	    			"            WHEN remarks = 'Cumulative' THEN scope\n" + 
	    			"            ELSE 1.0 / activity_count\n" + 
	    			"        END AS scope_calc,\n" + 
	    			"\n" + 
	    			"        ISNULL(completed, 0) AS completed_safe\n" + 
	    			"    FROM Base\n" + 
	    			"),\n" + 
	    			"\n" + 
	    			"Structure_Level AS (\n" + 
	    			"    SELECT *,\n" + 
	    			"        SUM(progress_per) OVER (\n" + 
	    			"            PARTITION BY contract_id, structure_type, structure\n" + 
	    			"        ) AS group_progress_sum\n" + 
	    			"    FROM Calc\n" + 
	    			"),\n" + 
	    			"\n" + 
	    			"Structure_Result AS (\n" + 
	    			"    SELECT \n" + 
	    			"        project_id,\n" + 
	    			"        project_name,\n" + 
	    			"        project_type_id,\n" + 
	    			"        project_type_name,\n" + 
	    			"        contract_id,\n" + 
	    			"        structure_type,\n" + 
	    			"        structure,\n" + 
	    			"        remarks,\n" + 
	    			"		project_count,length,commissioned_length,\n" + 
	    			"\n" + 
	    			"        MAX(unit_data) AS unit_data,\n" + 
	    			"        SUM(scope_calc) AS scope_calc,\n" + 
	    			"\n" + 
	    			"        CASE \n" + 
	    			"            WHEN MAX(remarks) = 'Cumulative' \n" + 
	    			"                THEN SUM(completed_safe)\n" + 
	    			"\n" + 
	    			"            WHEN MAX(group_progress_sum) * 100 > 95\n" + 
	    			"                THEN SUM(1.0 / activity_count)\n" + 
	    			"\n" + 
	    			"            ELSE 0\n" + 
	    			"        END AS completed_calc\n" + 
	    			"    FROM Structure_Level\n" + 
	    			"    GROUP BY\n" + 
	    			"        project_id,\n" + 
	    			"        project_name,\n" + 
	    			"        project_type_id,\n" + 
	    			"        project_type_name,\n" + 
	    			"        contract_id,\n" + 
	    			"        structure_type,\n" + 
	    			"        structure,\n" + 
	    			"		project_count,length,commissioned_length,\n" + 
	    			"        remarks\n" + 
	    			")\n" + 
	    			"\n" + 
	    			"SELECT \n" + 
	    			"    project_id,\n" + 
	    			"    project_name,\n" + 
	    			"    project_type_id,\n" + 
	    			"    project_type_name,\n" + 
	    			"    structure_type,\n" + 
	    			"	project_count,length,commissioned_length,\n" + 
	    			"\n" + 
	    			"    CAST(ROUND(SUM(scope_calc), 0) AS VARCHAR(50)) \n" + 
	    			"        + ' ' + MAX(unit_data) AS scope,\n" + 
	    			"\n" + 
	    			"    CAST(ROUND(SUM(completed_calc), 0) AS VARCHAR(50)) \n" + 
	    			"        + ' ' + MAX(unit_data) AS completed\n" + 
	    			"FROM Structure_Result\n" + 
	    			"GROUP BY\n" + 
	    			"    project_id,\n" + 
	    			"    project_name,\n" + 
	    			"    project_type_id,\n" + 
	    			"    project_type_name,\n" + 
	    			"    structure_type,project_count,length,commissioned_length\n" + 
	    			"\n" + 
	    			"ORDER BY project_id\n" + 
	    			" ";	    	
	    	

	        objsList = jdbcTemplate.query(projectsQry, new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));

	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	    return objsList;
	}

	
	

	@Override
	public Project getProjectOverview(Project obj) throws Exception {
		Project projectOverview = new Project();
		
		return projectOverview;
	}
	
	public List<WorkFeatures> getWorkDetails(String work_id_fk) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select id,title_fk,work_id_fk,dashboard_url,soft_delete_status_fk,title_fk as title,work_id,work_short_name "
					+ "from work_details "
					+ "LEFT JOIN work ON work_id_fk = work_id "		
					+ "WHERE work_id_fk = ? and soft_delete_status_fk = ? "
					+ "ORDER BY case when title_fk='Sanctions Details' then 1\r\n" + 
					"when title_fk='Salient Features' then 2 end asc";
			
			objsList = jdbcTemplate.query( qry, new Object[]{work_id_fk,CommonConstants.ACTIVE}, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	public List<WorkFeatures> getWorkSalientFeatures(String work_id_fk) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select id,category_fk,work_id_fk,description,soft_delete_status_fk,category_fk as category,work_id,work_short_name "
					+ "from salient_features "
					+ "LEFT JOIN work ON work_id_fk = work_id "		
					+ "WHERE work_id_fk = ? and soft_delete_status_fk = ? ";
			
			objsList = jdbcTemplate.query( qry, new Object[]{work_id_fk,CommonConstants.ACTIVE}, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	private String getExecutedByList(String work_id) throws Exception {
		List<Work> objsList = new ArrayList<Work>();
		String executedByList = "";
		try {
			String qry ="SELECT executed_by_id_fk,railway_name "
					+ "from work_railway wr "
					+ "left join railway ON executed_by_id_fk = railway_id "
					+ "where executed_by_id_fk is not null and executed_by_id_fk <> '' and work_id_fk = ? ORDER BY (CASE executed_by_id_fk WHEN 'MRVC' THEN 0 WHEN 'CR' THEN 1 WHEN 'WR' THEN 2 else 'Others' end)";
		
			objsList = jdbcTemplate.query( qry, new Object[] {work_id}, new BeanPropertyRowMapper<Work>(Work.class));
			for (Work work : objsList) {
				executedByList = executedByList + ", " + work.getExecuted_by_id_fk();
			}
			if(!StringUtils.isEmpty(executedByList)) {
				executedByList = executedByList.startsWith(",") ? executedByList.substring(1) : executedByList;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return executedByList;
	}


	private String getRailwayAgencyList(String work_id) throws Exception {
		List<Work> objsList = new ArrayList<Work>();
		String railwayAgencyList = "";
		try {
			String qry ="SELECT railway_id_fk,railway_name "
					+ "from work_railway wr "
					+ "left join railway ON railway_id_fk = railway_id "
					+ "where railway_id_fk is not null and railway_id_fk <> '' and work_id_fk = ?";
		
			objsList = jdbcTemplate.query( qry, new Object[] {work_id}, new BeanPropertyRowMapper<Work>(Work.class));
			for (Work work : objsList) {
				railwayAgencyList = railwayAgencyList + ", " + work.getRailway_id_fk();
			}
			
			if(!StringUtils.isEmpty(railwayAgencyList)) {
				railwayAgencyList = railwayAgencyList.startsWith(",") ? railwayAgencyList.substring(1) : railwayAgencyList;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return railwayAgencyList;
		
	}

	@Override
	public List<Project> getprojectTypes() throws Exception {
		List<Project> objsList = null;
		try {
			String projectsQry = "select project_type_id,project_type_name from project_type";
			objsList = jdbcTemplate.query( projectsQry,new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Project> getprojectTypeswithProject() throws Exception {
		List<Project> objsList = null;
		try {
			String projectsQry = "SELECT p.project_id, p.project_name,project_type_id, pt.project_type_name, p.proposed_length, (SELECT SUM(ISNULL(a3.completed,0)) FROM p6_activities a3 INNER JOIN contract c3 ON c3.contract_id = a3.contract_id_fk WHERE c3.project_id_fk = p.project_id AND a3.p6_activity_name = 'Commissioning') AS commissioned_length FROM project p INNER JOIN project_type pt ON p.project_type_id_fk = pt.project_type_id";
			objsList = jdbcTemplate.query( projectsQry,new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}	

	@Override
	public List<Project> getProjectStatsByType() throws Exception {
		List<Project> statsList = null;
		try {
			String qry = "SELECT " +
			    "pt.project_type_id, " +
			    "pt.project_type_name, " +
			    "COUNT(p.project_id) AS total_projects, " +
			    "COUNT(CASE WHEN p.project_status = 'Open' THEN 1 END) AS ongoing_projects, " +
			    "'' AS total_length, " +
			    "'' AS total_earthwork, " +
			    "'' AS completed_length, " +
			    "'' AS completed_track, " +
			    "'' AS major_bridges, " +
			    "'' AS minor_bridges, " +
			    "'' AS rob, " +
			    "'' AS rub " +
			"FROM project_type pt " +
			"LEFT JOIN project p ON pt.project_type_id = p.project_type_id_fk " +
			"GROUP BY pt.project_type_id, pt.project_type_name " +
			"ORDER BY pt.project_type_id";

			statsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<>(Project.class));
		} catch (Exception e) {
			throw new Exception("Error fetching project stats", e);
		}
		return statsList;
	}




	@Override
	public List<Project> getAllLengthsByProjectTypes() throws Exception {
	    List<Project> objsList = null;
	    try {
	        String projectsQry =
	            "SELECT \n" + 
	            "	               distinct project_type_id,project_type_name,\n" + 
	            "	                (SELECT SUM(ISNULL(scope,0)) \n" + 
	            "	                 FROM p6_activities a2 \n" + 
	            "\n" + 
	            "\n" + 
	            "	                 INNER JOIN contract c2 ON c2.contract_id = a2.contract_id_fk \n" + 
	            "\n" + 
	            "					left join project p11 on p11.project_id=c2. project_id_fk\n" + 
	            "					INNER JOIN project_type pt1 ON p11.project_type_id_fk = pt1.project_type_id and pt1.project_type_id=pt.project_type_id\n" + 
	            "\n" + 
	            "	                   AND a2.p6_activity_name = 'Track Laying' ) AS length, \n" + 
	            "\n" + 
	            "\n" + 
	            " (SELECT SUM(ISNULL(completed,0)) \n" + 
	            "	                 FROM p6_activities a3 \n" + 
	            "	                 INNER JOIN contract c2 ON c2.contract_id = a3.contract_id_fk \n" + 
	            "\n" + 
	            "					left join project p11 on p11.project_id=c2. project_id_fk\n" + 
	            "					INNER JOIN project_type pt1 ON p11.project_type_id_fk = pt1.project_type_id and pt1.project_type_id=pt.project_type_id\n" + 
	            "	                   AND a3.p6_activity_name = 'Commissioning') AS commissioned_length, \n" + 
	            "\n" + 
	            "	                COUNT(DISTINCT p.project_id) AS total_projects, \n" + 
	            "	                COUNT(DISTINCT CASE \n" + 
	            "	                           WHEN UPPER(LTRIM(RTRIM(p.project_status))) = 'OPEN' \n" + 
	            "	                           THEN p.project_id \n" + 
	            "	                         END) AS ongoing_projects, \n" + 
	            "\n" + 
	            "	                    (SELECT SUM(a1.scope)\n" + 
	            "	                 FROM project p1\n" + 
	            "	                 INNER JOIN contract c1 ON c1.project_id_fk = p1.project_id\n" + 
	            "	                 INNER JOIN p6_activities a1 ON a1.contract_id_fk = c1.contract_id\n" + 
	            "	                 WHERE a1.p6_activity_name = 'Track Laying'\n" + 
	            "	                   AND p1.project_type_id_fk = pt.project_type_id\n" + 
	            "	                ) AS total_length, \n" + 
	            "	                SUM(CASE WHEN a.p6_activity_name IN ('Earthwork cutting','Earthwork filling') THEN ISNULL(a.scope,0) END) AS total_earthwork, \n" + 
	            "	                SUM(CASE WHEN a.p6_activity_name = 'Track Laying' THEN ISNULL(a.completed,0) END) AS completed_track, \n" + 
	            "	                SUM(CASE WHEN a.p6_activity_name = 'Commissioning' THEN ISNULL(a.completed,0) END) AS commissioned_length2, \n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "	                SUM(DISTINCT CASE WHEN s.structure_type_fk = 'Major Bridge' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_major_bridges, \n" + 
	            "	                COUNT(DISTINCT CASE WHEN s.structure_type_fk = 'Major Bridge' THEN s.structure_id END) AS total_major_bridges,\n" + 
	            "\n" + 
	            "	                SUM( DISTINCT CASE WHEN s.structure_type_fk = 'Minor Bridge' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_minor_bridges, \n" + 
	            "	                COUNT( DISTINCT CASE WHEN s.structure_type_fk = 'Minor Bridge' THEN s.structure_id END) AS total_minor_bridges, \n" + 
	            "\n" + 
	            "	                SUM(DISTINCT CASE WHEN s.structure_type_fk = 'ROB' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_rob, \n" + 
	            "	                COUNT(DISTINCT CASE WHEN s.structure_type_fk = 'ROB' THEN s.structure_id END) AS total_rob, \n" + 
	            "\n" + 
	            "	                SUM(DISTINCT CASE WHEN s.structure_type_fk = 'RUB' AND a.scope = a.completed THEN 1 ELSE 0 END) AS completed_rub, \n" + 
	            "	                COUNT(DISTINCT CASE WHEN s.structure_type_fk = 'RUB' THEN s.structure_id END) AS total_rub \n" + 
	            "\n" + 
	            "\n" + 
	            "\n" + 
	            "	                \n" + 
	            "\n" + 
	            "\n" + 
	            "	            FROM project p \n" + 
	            "	            INNER JOIN project_type pt ON p.project_type_id_fk = pt.project_type_id \n" + 
	            "	            LEFT JOIN contract c ON c.project_id_fk = p.project_id \n" + 
	            "	            LEFT JOIN p6_activities a ON c.contract_id = a.contract_id_fk \n" + 
	            "	            LEFT JOIN structure s ON p.project_id = s.project_id_fk and s.structure_id=a.structure_id_fk  \n" + 
	            "\n" + 
	            "				group by project_type_id,project_type_name\n" + 
	            "\n" + 
	            "";

	        objsList = jdbcTemplate.query(projectsQry, new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));

	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	    return objsList;
	}




	@Override
	public List<Project> getProjectsforHeader() throws Exception {
		List<Project> objsList = null;
		try {
			String projectsQry = "SELECT distinct p.project_id, p.project_name,project_type_id, pt.project_type_name FROM project p INNER JOIN project_type pt ON p.project_type_id_fk = pt.project_type_id";
			objsList = jdbcTemplate.query( projectsQry,new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	
}
