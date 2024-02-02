package com.synergizglobal.pmis.IMPLdao;

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

import com.synergizglobal.pmis.Idao.HomeNewDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.WorkFeatures;

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
			String projectsQry = "select project_id,project_name from project where project_id not in('P06','P07')";
			objsList = jdbcTemplate.query( projectsQry,new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));	
			/*for (Project project : objsList) {
				if(project.getProject_id().equals("P08")) {
					projectsQry = "select project_id,project_name from project where project_id in('P06','P07')";
					List<Project> cr_wr_projects = jdbcTemplate.query( projectsQry,new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));	
					project.setCr_wr_projects(cr_wr_projects);
				}
			}*/
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Project getProjectOverview(Project obj) throws Exception {
		Project projectOverview = new Project();
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String projectQry = "select project_id,pink_book_item_number,project_name,plan_head_number,remarks,project_status,attachment,benefits,round((cumulative_expenditure/sanctioned_cost)*100,2) as financial_progress,case when project_id='P04' then '33' else  round((actual_progress/sanctioned_cost)*100,2) end as physical_progress from project_view where project_id = ?";
			
			String projectDetailsQry = "select sum(wr.sanctioned_estimated_cost) as sanctioned_estimated_cost,max(wr.sanctioned_year_fk) as sanctioned_year_fk,"
					+ "sum(wr.completion_cost) as completion_cost,max(wr.year_of_completion) as year_of_completion, "
					+ "(SELECT (CASE WHEN MONTH(wr.projected_completion) >= 4 THEN concat(YEAR(wr.projected_completion), '-',SUBSTRING(cast(YEAR(wr.projected_completion)+1 as varchar),3,2)) ELSE concat(YEAR(wr.projected_completion)-1,'-', SUBSTRING(cast(YEAR(wr.projected_completion) as varchar),3,2)) END) AS financial_year) as projected_completion_year," 
					//+ "max(wr.projected_completion) as projected_completion_year,"
					+ "(select round(sum(sanctioned_completion_cost/10000000),2) from work_view where project_id_fk=?)as latest_revised_cost " 
					+ "from work wr where wr.project_id_fk = ? GROUP BY projected_completion,wr.project_id_fk";
			
			String workQry = "select *,round((cumulative_expenditure/sanctioned_cost)*100,2) as financial_progress,round(actual_progress*100,2) as physical_progress,case when isnull(latest_revised_cost,0)>0 then round((latest_revised_cost/10000000),2) else round((sanctioned_estimated_cost/10000000),2) end as latest_revised_cost,work_id as work_id_fk  from work_view where project_id_fk = ?";
			
			
			//String workQry = "select work_id,work_short_name,(select work_id_fk as work_id from dashboard where soft_delete_status_fk = 'Active' and work_id_fk = work_id offset 0 rows  fetch next 1 rows only) as work_id_fk from work where project_id_fk = ?";
			
			
			String projectGalleryQry = "select id,file_name,project_id_fk,created_date,created_by from project_gallery where project_id_fk = ? ";
			
			String projectDocumentsQry = "select id, project_id_fk, attachment, project_file_type_fk,FORMAT(created_date,'dd-MM-yyyy') AS created_date from project_files where project_id_fk = ? ";
			
			String workDocumentsQry = "select id, work_id_fk, attachment, work_file_type_fk,FORMAT(created_date,'dd-MM-yyyy') AS created_date from work_files where work_id_fk = ? ";
			
			List<Project> objsList = jdbcTemplate.query( projectQry,new Object[] {obj.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));
			
			
			
			for (Project project : objsList) {		
				List<Work> worksList = new ArrayList<Work>();
				/*if(project.getProject_id().equals("P08")) {
					String projectsQry = "select project_id,project_name from project where project_id in('P06','P07')";
					List<Project> cr_wr_projects = jdbcTemplate.query( projectsQry,new Object[] {}, new BeanPropertyRowMapper<Project>(Project.class));	
					for (Project project_temp : cr_wr_projects) {
						Work work = new Work();
						work.setWork_id(project_temp.getProject_id());
						work.setWork_short_name(project_temp.getProject_name());
						String work_id_for_dashboard = null;
						if(project_temp.getProject_id().equals("P06")) {
							work_id_for_dashboard = "P06W01";
						}
						if(project_temp.getProject_id().equals("P07")) {
							work_id_for_dashboard = "P07W01";
						}
						if(!StringUtils.isEmpty(work_id_for_dashboard)) {
							String qry = "select work_id_fk as work_id from dashboard where soft_delete_status_fk = 'Active' and work_id_fk = ? order by work_id_fk offset 0 rows  fetch next 1 rows only";
							String work_id_fk = jdbcTemplate.queryForObject( qry,new Object[] {work_id_for_dashboard}, (String.class));	
							if(!StringUtils.isEmpty(work_id_fk)) {
								work.setWork_id_fk(work_id_fk);
							}
						}						
						worksList.add(work);
					}
				}*/
				
				List<Project> projectInfoList = jdbcTemplate.query( projectDetailsQry, new Object[] {project.getProject_id(),project.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));
				for (Project projectInfo : projectInfoList) {
					if(!StringUtils.isEmpty(projectInfo)) {
						String sanctioned_estimated_cost = projectInfo.getSanctioned_estimated_cost();
						if(!StringUtils.isEmpty(sanctioned_estimated_cost)) {
							sanctioned_estimated_cost = numberFormatter.format(Double.parseDouble(sanctioned_estimated_cost));
						}
						project.setSanctioned_estimated_cost(sanctioned_estimated_cost);
						project.setSanctioned_year_fk(projectInfo.getSanctioned_year_fk());
						
						String completion_cost = projectInfo.getCompletion_cost();
						if(!StringUtils.isEmpty(completion_cost)) {
							completion_cost = numberFormatter.format(Double.parseDouble(completion_cost));
						}
						
						project.setCompletion_cost(completion_cost);
						project.setYear_of_completion(projectInfo.getYear_of_completion());
						
						String latest_revised_cost = projectInfo.getLatest_revised_cost();
						if(!StringUtils.isEmpty(latest_revised_cost)) {
							latest_revised_cost = numberFormatter.format(Double.parseDouble(latest_revised_cost));
						}
						
						project.setProjected_completion_year(projectInfo.getProjected_completion_year());
						project.setLatest_revised_cost(latest_revised_cost);
					}
				}
				List<Work> worksInfo = jdbcTemplate.query( workQry, new Object[] {project.getProject_id()}, new BeanPropertyRowMapper<Work>(Work.class));
				
				if(!StringUtils.isEmpty(worksInfo) && worksInfo.size() > 0) {
					worksList.addAll(worksInfo);
				}
				//project.setWorksInfo(worksList);
				
				for (Work work : worksList) {
					work.setRailwayAgency(getRailwayAgencyList(work.getWork_id()));
					work.setExecutedBy(getExecutedByList(work.getWork_id()));
					List<Work> workDocs = jdbcTemplate.query( workDocumentsQry, new Object[] {work.getWork_id()}, new BeanPropertyRowMapper<Work>(Work.class));
				    work.setWorkDocs(workDocs);
				    
				    List<WorkFeatures> workDetailsList = getWorkDetails(work.getWork_id());
				    work.setWorkDetails(workDetailsList);
				    List<WorkFeatures> workSalientFeaturesList = getWorkSalientFeatures(work.getWork_id());
				    work.setWorkSalientFeatures(workSalientFeaturesList);
				}
				project.setWorksInfo(worksList);
				
				List<Project> projectGallery = jdbcTemplate.query( projectGalleryQry, new Object[] {project.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));
				
				List<Project> projectDocs = jdbcTemplate.query( projectDocumentsQry, new Object[] {project.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));


				project.setProjectGallery(projectGallery);
				project.setProjectDocs(projectDocs);
				
				projectOverview = project;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
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
	
}
