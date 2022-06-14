package com.synergizglobal.pmis.model;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class RandRMain {
private String rr_id, work_id,work_id_fk,work_short_name,work_name, identification_no,project_id, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, type_of_structure_wall, type_of_structure_floor, carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, physical_verification, verification_by, approval_by_committee, rr_approval_status_by_mrvc, estimation_amount, estimate_approval_date, letter_to_mmrda, estimates_by_mmrda, payment_to_mmrda, alternate_housing_allotment,
relocation, encroachment_removal, boundary_wall_status, boundary_wall_doc, handed_over_to_execution, occupier_name_during_verification,stage,id, rr_id_fk, name_of_activity, 
year_of_establishment, monthly_turnover_amount, monthly_turnover_amount_units, number_of_employees, remarks,
 employee_name, employee_age, employee_gender, employee_literacy, employee_travel_time, employee_salary, employee_salary_units, employee_nature_of_work,
 occupancy_status, gender, tenure_status, caste, mother_tongue, type_of_family, family_size, employee_attended,family_income_amount_units,
number_of_married_couple, family_income_amount, vulnerable_category,project_id_fk,project_name,structure,work_code,
residential_name, residential_relation_with_head, residential_age, rr_location_fk,maritua_status, rr_sub_location,rr_tenure_status,residential_gender, 
residential_maritual_status, residential_education, residential_employment, residential_salary, unit, value,com_carpet_area,com_remarks,estimated_by_mmrda_amount_units,estimation_amount_units,
residential_salary_units,created_by_user_id_fk,modified_by,modified_date,user_id,user_name,designation,user_type_fk,user_role_code,executive_user_id_fk,rr_data_id, uploaded_file, status, uploaded_by_user_id_fk, uploaded_on,mail_body_header;

private List<RandRMain> residentialList,commercialList,comList,comFamList,resList,resFamList,report1List,report2List;
private MultipartFile RandRFile;
private String [] values,genders,ids, rr_id_fks, employee_names, employee_ages, employee_genders, employee_literacys,employee_salary_unitsss, employee_travel_times
, employee_salarys, employee_salary_unitss, employee_nature_of_works,
residential_names, residential_relation_with_heads, residential_ages, residential_genders, residential_maritual_statuss,employee_attendeds, residential_educations, residential_employments
, residential_salarys, residential_salary_unitss;

public String getProject_id() {
	return project_id;
}

public void setProject_id(String project_id) {
	this.project_id = project_id;
}

public List<RandRMain> getReport1List() {
	return report1List;
}

public void setReport1List(List<RandRMain> report1List) {
	this.report1List = report1List;
}

public List<RandRMain> getReport2List() {
	return report2List;
}

public void setReport2List(List<RandRMain> report2List) {
	this.report2List = report2List;
}

public String getWork_code() {
	return work_code;
}

public void setWork_code(String work_code) {
	this.work_code = work_code;
}

public String getRr_data_id() {
	return rr_data_id;
}

public void setRr_data_id(String rr_data_id) {
	this.rr_data_id = rr_data_id;
}

public String getUploaded_file() {
	return uploaded_file;
}

public void setUploaded_file(String uploaded_file) {
	this.uploaded_file = uploaded_file;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getUploaded_by_user_id_fk() {
	return uploaded_by_user_id_fk;
}

public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
	this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
}

public String getUploaded_on() {
	return uploaded_on;
}

public void setUploaded_on(String uploaded_on) {
	this.uploaded_on = uploaded_on;
}

public List<RandRMain> getComList() {
	return comList;
}

public void setComList(List<RandRMain> comList) {
	this.comList = comList;
}

public List<RandRMain> getComFamList() {
	return comFamList;
}

public void setComFamList(List<RandRMain> comFamList) {
	this.comFamList = comFamList;
}

public List<RandRMain> getResList() {
	return resList;
}

public void setResList(List<RandRMain> resList) {
	this.resList = resList;
}

public List<RandRMain> getResFamList() {
	return resFamList;
}

public void setResFamList(List<RandRMain> resFamList) {
	this.resFamList = resFamList;
}

public MultipartFile getRandRFile() {
	return RandRFile;
}

public void setRandRFile(MultipartFile randRFile) {
	RandRFile = randRFile;
}

public String getExecutive_user_id_fk() {
	return executive_user_id_fk;
}

public void setExecutive_user_id_fk(String executive_user_id_fk) {
	this.executive_user_id_fk = executive_user_id_fk;
}

public String getUser_type_fk() {
	return user_type_fk;
}

public void setUser_type_fk(String user_type_fk) {
	this.user_type_fk = user_type_fk;
}

public String getUser_role_code() {
	return user_role_code;
}

public void setUser_role_code(String user_role_code) {
	this.user_role_code = user_role_code;
}

public String getUser_id() {
	return user_id;
}

public void setUser_id(String user_id) {
	this.user_id = user_id;
}

public String getUser_name() {
	return user_name;
}

public void setUser_name(String user_name) {
	this.user_name = user_name;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public String getEstimated_by_mmrda_amount_units() {
	return estimated_by_mmrda_amount_units;
}

public void setEstimated_by_mmrda_amount_units(String estimated_by_mmrda_amount_units) {
	this.estimated_by_mmrda_amount_units = estimated_by_mmrda_amount_units;
}

public String getEstimation_amount_units() {
	return estimation_amount_units;
}

public void setEstimation_amount_units(String estimation_amount_units) {
	this.estimation_amount_units = estimation_amount_units;
}

public String[] getEmployee_attendeds() {
	return employee_attendeds;
}

public void setEmployee_attendeds(String[] employee_attendeds) {
	this.employee_attendeds = employee_attendeds;
}

public String[] getResidential_salarys() {
	return residential_salarys;
}

public void setResidential_salarys(String[] residential_salarys) {
	this.residential_salarys = residential_salarys;
}

public String[] getEmployee_salary_unitsss() {
	return employee_salary_unitsss;
}

public void setEmployee_salary_unitsss(String[] employee_salary_unitsss) {
	this.employee_salary_unitsss = employee_salary_unitsss;
}

public String getFamily_income_amount_units() {
	return family_income_amount_units;
}

public void setFamily_income_amount_units(String family_income_amount_units) {
	this.family_income_amount_units = family_income_amount_units;
}

public String getEmployee_attended() {
	return employee_attended;
}

public void setEmployee_attended(String employee_attended) {
	this.employee_attended = employee_attended;
}

public String getCom_carpet_area() {
	return com_carpet_area;
}

public void setCom_carpet_area(String com_carpet_area) {
	this.com_carpet_area = com_carpet_area;
}

public String getCom_remarks() {
	return com_remarks;
}

public void setCom_remarks(String com_remarks) {
	this.com_remarks = com_remarks;
}

public List<RandRMain> getResidentialList() {
	return residentialList;
}

public void setResidentialList(List<RandRMain> residentialList) {
	this.residentialList = residentialList;
}

public List<RandRMain> getCommercialList() {
	return commercialList;
}

public void setCommercialList(List<RandRMain> commercialList) {
	this.commercialList = commercialList;
}

public String getStructure() {
	return structure;
}

public void setStructure(String structure) {
	this.structure = structure;
}

public String getMaritua_status() {
	return maritua_status;
}

public void setMaritua_status(String maritua_status) {
	this.maritua_status = maritua_status;
}

public String[] getValues() {
	return values;
}

public void setValues(String[] values) {
	this.values = values;
}

public String[] getGenders() {
	return genders;
}

public void setGenders(String[] genders) {
	this.genders = genders;
}

public String[] getIds() {
	return ids;
}

public void setIds(String[] ids) {
	this.ids = ids;
}

public String[] getRr_id_fks() {
	return rr_id_fks;
}

public void setRr_id_fks(String[] rr_id_fks) {
	this.rr_id_fks = rr_id_fks;
}

public String[] getEmployee_names() {
	return employee_names;
}

public void setEmployee_names(String[] employee_names) {
	this.employee_names = employee_names;
}

public String[] getEmployee_ages() {
	return employee_ages;
}

public void setEmployee_ages(String[] employee_ages) {
	this.employee_ages = employee_ages;
}

public String[] getEmployee_genders() {
	return employee_genders;
}

public void setEmployee_genders(String[] employee_genders) {
	this.employee_genders = employee_genders;
}

public String[] getEmployee_literacys() {
	return employee_literacys;
}

public void setEmployee_literacys(String[] employee_literacys) {
	this.employee_literacys = employee_literacys;
}

public String[] getEmployee_travel_times() {
	return employee_travel_times;
}

public void setEmployee_travel_times(String[] employee_travel_times) {
	this.employee_travel_times = employee_travel_times;
}

public String[] getEmployee_salarys() {
	return employee_salarys;
}

public void setEmployee_salarys(String[] employee_salarys) {
	this.employee_salarys = employee_salarys;
}

public String[] getEmployee_salary_unitss() {
	return employee_salary_unitss;
}

public void setEmployee_salary_unitss(String[] employee_salary_unitss) {
	this.employee_salary_unitss = employee_salary_unitss;
}

public String[] getEmployee_nature_of_works() {
	return employee_nature_of_works;
}

public void setEmployee_nature_of_works(String[] employee_nature_of_works) {
	this.employee_nature_of_works = employee_nature_of_works;
}

public String[] getResidential_names() {
	return residential_names;
}

public void setResidential_names(String[] residential_names) {
	this.residential_names = residential_names;
}

public String[] getResidential_relation_with_heads() {
	return residential_relation_with_heads;
}

public void setResidential_relation_with_heads(String[] residential_relation_with_heads) {
	this.residential_relation_with_heads = residential_relation_with_heads;
}

public String[] getResidential_ages() {
	return residential_ages;
}

public void setResidential_ages(String[] residential_ages) {
	this.residential_ages = residential_ages;
}

public String[] getResidential_genders() {
	return residential_genders;
}

public void setResidential_genders(String[] residential_genders) {
	this.residential_genders = residential_genders;
}

public String[] getResidential_maritual_statuss() {
	return residential_maritual_statuss;
}

public void setResidential_maritual_statuss(String[] residential_maritual_statuss) {
	this.residential_maritual_statuss = residential_maritual_statuss;
}

public String[] getResidential_educations() {
	return residential_educations;
}

public void setResidential_educations(String[] residential_educations) {
	this.residential_educations = residential_educations;
}

public String[] getResidential_employments() {
	return residential_employments;
}

public void setResidential_employments(String[] residential_employments) {
	this.residential_employments = residential_employments;
}



public String[] getResidential_salary_unitss() {
	return residential_salary_unitss;
}

public void setResidential_salary_unitss(String[] residential_salary_unitss) {
	this.residential_salary_unitss = residential_salary_unitss;
}

public String getUnit() {
	return unit;
}

public void setUnit(String unit) {
	this.unit = unit;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

public String getProject_id_fk() {
	return project_id_fk;
}

public void setProject_id_fk(String project_id_fk) {
	this.project_id_fk = project_id_fk;
}

public String getProject_name() {
	return project_name;
}

public void setProject_name(String project_name) {
	this.project_name = project_name;
}

public String getWork_short_name() {
	return work_short_name;
}

public void setWork_short_name(String work_short_name) {
	this.work_short_name = work_short_name;
}

public String getWork_name() {
	return work_name;
}

public void setWork_name(String work_name) {
	this.work_name = work_name;
}

public String getWork_id_fk() {
	return work_id_fk;
}

public void setWork_id_fk(String work_id_fk) {
	this.work_id_fk = work_id_fk;
}

public String getRr_id() {
	return rr_id;
}

public void setRr_id(String rr_id) {
	this.rr_id = rr_id;
}

public String getWork_id() {
	return work_id;
}

public void setWork_id(String work_id) {
	this.work_id = work_id;
}

public String getIdentification_no() {
	return identification_no;
}

public void setIdentification_no(String identification_no) {
	this.identification_no = identification_no;
}

public String getMap_sr_no() {
	return map_sr_no;
}

public void setMap_sr_no(String map_sr_no) {
	this.map_sr_no = map_sr_no;
}

public String getLocation_name() {
	return location_name;
}

public void setLocation_name(String location_name) {
	this.location_name = location_name;
}

public String getSub_location_name() {
	return sub_location_name;
}

public void setSub_location_name(String sub_location_name) {
	this.sub_location_name = sub_location_name;
}

public String getPhase() {
	return phase;
}

public void setPhase(String phase) {
	this.phase = phase;
}

public String getStructure_id() {
	return structure_id;
}

public void setStructure_id(String structure_id) {
	this.structure_id = structure_id;
}

public String getType_of_structure_roof() {
	return type_of_structure_roof;
}

public void setType_of_structure_roof(String type_of_structure_roof) {
	this.type_of_structure_roof = type_of_structure_roof;
}

public String getType_of_structure_wall() {
	return type_of_structure_wall;
}

public void setType_of_structure_wall(String type_of_structure_wall) {
	this.type_of_structure_wall = type_of_structure_wall;
}

public String getType_of_structure_floor() {
	return type_of_structure_floor;
}

public void setType_of_structure_floor(String type_of_structure_floor) {
	this.type_of_structure_floor = type_of_structure_floor;
}

public String getCarpet_area() {
	return carpet_area;
}

public void setCarpet_area(String carpet_area) {
	this.carpet_area = carpet_area;
}

public String getYear_of_construction() {
	return year_of_construction;
}

public void setYear_of_construction(String year_of_construction) {
	this.year_of_construction = year_of_construction;
}

public String getName_of_the_owner() {
	return name_of_the_owner;
}

public void setName_of_the_owner(String name_of_the_owner) {
	this.name_of_the_owner = name_of_the_owner;
}

public String getType_of_use() {
	return type_of_use;
}

public void setType_of_use(String type_of_use) {
	this.type_of_use = type_of_use;
}

public String getDocument_type() {
	return document_type;
}

public void setDocument_type(String document_type) {
	this.document_type = document_type;
}

public String getDocument_no() {
	return document_no;
}

public void setDocument_no(String document_no) {
	this.document_no = document_no;
}

public String getPhysical_verification() {
	return physical_verification;
}

public void setPhysical_verification(String physical_verification) {
	this.physical_verification = physical_verification;
}

public String getVerification_by() {
	return verification_by;
}

public void setVerification_by(String verification_by) {
	this.verification_by = verification_by;
}

public String getApproval_by_committee() {
	return approval_by_committee;
}

public void setApproval_by_committee(String approval_by_committee) {
	this.approval_by_committee = approval_by_committee;
}

public String getRr_approval_status_by_mrvc() {
	return rr_approval_status_by_mrvc;
}

public void setRr_approval_status_by_mrvc(String rr_approval_status_by_mrvc) {
	this.rr_approval_status_by_mrvc = rr_approval_status_by_mrvc;
}

public String getEstimation_amount() {
	return estimation_amount;
}

public void setEstimation_amount(String estimation_amount) {
	this.estimation_amount = estimation_amount;
}

public String getEstimate_approval_date() {
	return estimate_approval_date;
}

public void setEstimate_approval_date(String estimate_approval_date) {
	this.estimate_approval_date = estimate_approval_date;
}

public String getLetter_to_mmrda() {
	return letter_to_mmrda;
}

public void setLetter_to_mmrda(String letter_to_mmrda) {
	this.letter_to_mmrda = letter_to_mmrda;
}

public String getEstimates_by_mmrda() {
	return estimates_by_mmrda;
}

public void setEstimates_by_mmrda(String estimates_by_mmrda) {
	this.estimates_by_mmrda = estimates_by_mmrda;
}

public String getPayment_to_mmrda() {
	return payment_to_mmrda;
}

public void setPayment_to_mmrda(String payment_to_mmrda) {
	this.payment_to_mmrda = payment_to_mmrda;
}

public String getAlternate_housing_allotment() {
	return alternate_housing_allotment;
}

public void setAlternate_housing_allotment(String alternate_housing_allotment) {
	this.alternate_housing_allotment = alternate_housing_allotment;
}

public String getRelocation() {
	return relocation;
}

public void setRelocation(String relocation) {
	this.relocation = relocation;
}

public String getEncroachment_removal() {
	return encroachment_removal;
}

public void setEncroachment_removal(String encroachment_removal) {
	this.encroachment_removal = encroachment_removal;
}

public String getBoundary_wall_status() {
	return boundary_wall_status;
}

public void setBoundary_wall_status(String boundary_wall_status) {
	this.boundary_wall_status = boundary_wall_status;
}

public String getBoundary_wall_doc() {
	return boundary_wall_doc;
}

public void setBoundary_wall_doc(String boundary_wall_doc) {
	this.boundary_wall_doc = boundary_wall_doc;
}

public String getHanded_over_to_execution() {
	return handed_over_to_execution;
}

public void setHanded_over_to_execution(String handed_over_to_execution) {
	this.handed_over_to_execution = handed_over_to_execution;
}

public String getOccupier_name_during_verification() {
	return occupier_name_during_verification;
}

public void setOccupier_name_during_verification(String occupier_name_during_verification) {
	this.occupier_name_during_verification = occupier_name_during_verification;
}

public String getStage() {
	return stage;
}

public void setStage(String stage) {
	this.stage = stage;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getRr_id_fk() {
	return rr_id_fk;
}

public void setRr_id_fk(String rr_id_fk) {
	this.rr_id_fk = rr_id_fk;
}

public String getName_of_activity() {
	return name_of_activity;
}

public void setName_of_activity(String name_of_activity) {
	this.name_of_activity = name_of_activity;
}

public String getYear_of_establishment() {
	return year_of_establishment;
}

public void setYear_of_establishment(String year_of_establishment) {
	this.year_of_establishment = year_of_establishment;
}

public String getMonthly_turnover_amount() {
	return monthly_turnover_amount;
}

public void setMonthly_turnover_amount(String monthly_turnover_amount) {
	this.monthly_turnover_amount = monthly_turnover_amount;
}

public String getMonthly_turnover_amount_units() {
	return monthly_turnover_amount_units;
}

public void setMonthly_turnover_amount_units(String monthly_turnover_amount_units) {
	this.monthly_turnover_amount_units = monthly_turnover_amount_units;
}

public String getNumber_of_employees() {
	return number_of_employees;
}

public void setNumber_of_employees(String number_of_employees) {
	this.number_of_employees = number_of_employees;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public String getEmployee_name() {
	return employee_name;
}

public void setEmployee_name(String employee_name) {
	this.employee_name = employee_name;
}

public String getEmployee_age() {
	return employee_age;
}

public void setEmployee_age(String employee_age) {
	this.employee_age = employee_age;
}

public String getEmployee_gender() {
	return employee_gender;
}

public void setEmployee_gender(String employee_gender) {
	this.employee_gender = employee_gender;
}

public String getEmployee_literacy() {
	return employee_literacy;
}

public void setEmployee_literacy(String employee_literacy) {
	this.employee_literacy = employee_literacy;
}

public String getEmployee_travel_time() {
	return employee_travel_time;
}

public void setEmployee_travel_time(String employee_travel_time) {
	this.employee_travel_time = employee_travel_time;
}

public String getEmployee_salary() {
	return employee_salary;
}

public void setEmployee_salary(String employee_salary) {
	this.employee_salary = employee_salary;
}

public String getEmployee_salary_units() {
	return employee_salary_units;
}

public void setEmployee_salary_units(String employee_salary_units) {
	this.employee_salary_units = employee_salary_units;
}

public String getEmployee_nature_of_work() {
	return employee_nature_of_work;
}

public void setEmployee_nature_of_work(String employee_nature_of_work) {
	this.employee_nature_of_work = employee_nature_of_work;
}

public String getOccupancy_status() {
	return occupancy_status;
}

public void setOccupancy_status(String occupancy_status) {
	this.occupancy_status = occupancy_status;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getTenure_status() {
	return tenure_status;
}

public void setTenure_status(String tenure_status) {
	this.tenure_status = tenure_status;
}

public String getCaste() {
	return caste;
}

public void setCaste(String caste) {
	this.caste = caste;
}

public String getMother_tongue() {
	return mother_tongue;
}

public void setMother_tongue(String mother_tongue) {
	this.mother_tongue = mother_tongue;
}

public String getType_of_family() {
	return type_of_family;
}

public void setType_of_family(String type_of_family) {
	this.type_of_family = type_of_family;
}

public String getFamily_size() {
	return family_size;
}

public void setFamily_size(String family_size) {
	this.family_size = family_size;
}

public String getNumber_of_married_couple() {
	return number_of_married_couple;
}

public void setNumber_of_married_couple(String number_of_married_couple) {
	this.number_of_married_couple = number_of_married_couple;
}

public String getFamily_income_amount() {
	return family_income_amount;
}

public void setFamily_income_amount(String family_income_amount) {
	this.family_income_amount = family_income_amount;
}

public String getVulnerable_category() {
	return vulnerable_category;
}

public void setVulnerable_category(String vulnerable_category) {
	this.vulnerable_category = vulnerable_category;
}

public String getResidential_name() {
	return residential_name;
}

public void setResidential_name(String residential_name) {
	this.residential_name = residential_name;
}

public String getResidential_relation_with_head() {
	return residential_relation_with_head;
}

public void setResidential_relation_with_head(String residential_relation_with_head) {
	this.residential_relation_with_head = residential_relation_with_head;
}

public String getResidential_age() {
	return residential_age;
}

public void setResidential_age(String residential_age) {
	this.residential_age = residential_age;
}

public String getRr_location_fk() {
	return rr_location_fk;
}

public void setRr_location_fk(String rr_location_fk) {
	this.rr_location_fk = rr_location_fk;
}

public String getRr_sub_location() {
	return rr_sub_location;
}

public void setRr_sub_location(String rr_sub_location) {
	this.rr_sub_location = rr_sub_location;
}

public String getRr_tenure_status() {
	return rr_tenure_status;
}

public void setRr_tenure_status(String rr_tenure_status) {
	this.rr_tenure_status = rr_tenure_status;
}

public String getResidential_gender() {
	return residential_gender;
}

public void setResidential_gender(String residential_gender) {
	this.residential_gender = residential_gender;
}

public String getResidential_maritual_status() {
	return residential_maritual_status;
}

public void setResidential_maritual_status(String residential_maritual_status) {
	this.residential_maritual_status = residential_maritual_status;
}

public String getResidential_education() {
	return residential_education;
}

public void setResidential_education(String residential_education) {
	this.residential_education = residential_education;
}

public String getResidential_employment() {
	return residential_employment;
}

public void setResidential_employment(String residential_employment) {
	this.residential_employment = residential_employment;
}

public String getResidential_salary() {
	return residential_salary;
}

public void setResidential_salary(String residential_salary) {
	this.residential_salary = residential_salary;
}

public String getResidential_salary_units() {
	return residential_salary_units;
}

public void setResidential_salary_units(String residential_salary_units) {
	this.residential_salary_units = residential_salary_units;
}

public String getModified_by() {
	return modified_by;
}

public void setModified_by(String modified_by) {
	this.modified_by = modified_by;
}

public String getModified_date() {
	return modified_date;
}

public void setModified_date(String modified_date) {
	this.modified_date = modified_date;
}

public String getCreated_by_user_id_fk() {
	return created_by_user_id_fk;
}

public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
	this.created_by_user_id_fk = created_by_user_id_fk;
}

public boolean checkNullOrEmpty() throws IllegalAccessException {
	boolean flag = true;
	try {
		for (Field f : getClass().getDeclaredFields())
	        if (!StringUtils.isEmpty(f.get(this)))
	        	flag = false;
	} catch (Exception e) {
		
	}
    
    return flag;            
}

public String getMail_body_header() {
	return mail_body_header;
}

public void setMail_body_header(String mail_body_header) {
	this.mail_body_header = mail_body_header;
}

}
