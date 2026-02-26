package com.synergizglobal.wrpmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.wrpmis.Idao.LandAcquisitionProcessDAO;
import com.synergizglobal.wrpmis.model.ForestLand;
import com.synergizglobal.wrpmis.model.GovernmentLand;
import com.synergizglobal.wrpmis.model.LandAcquisitionProcess;
import com.synergizglobal.wrpmis.model.PrivateLand;

@Repository
public class LandAcquisitionProcessDaoImpl implements LandAcquisitionProcessDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    // Insert main record with child records
    @Override
    public boolean insertLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception {

        Connection con = null;
        PreparedStatement psMain = null;
        PreparedStatement psUpdateLaId = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            /* ===============================
               1. INSERT MAIN RECORD (NO la_id)
               =============================== */
            String insertMain =
                "INSERT INTO land_acquisition_process (" +
                "project_id, status, submission_of_proposal_to_GM, approval_of_GM, " +
                "draft_letter_to_con_for_approval_rp, date_of_approval_of_construction_rp, " +
                "date_of_uploading_of_gazette_notification_rp, publication_in_gazette_rp, " +
                "date_of_proposal_to_DC_for_nomination, date_of_nomination_of_competent_authority, " +
                "draft_letter_to_con_for_approval_ca, date_of_approval_of_construction_ca, " +
                "date_of_uploading_of_gazette_notification_ca, publication_in_gazette_ca, " +
                "la_file_type" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            psMain = con.prepareStatement(insertMain, Statement.RETURN_GENERATED_KEYS);

            psMain.setString(1, process.getProjectId());
            psMain.setString(2, process.getStatus());
            psMain.setString(3, process.getSubmissionOfProposalToGM());
            psMain.setString(4, process.getApprovalOfGM());
            psMain.setString(5, process.getDraftLetterToConForApprovalRP());
            psMain.setString(6, process.getDateOfApprovalOfConstructionRP());
            psMain.setString(7, process.getDateOfUploadingOfGazetteNotificationRP());
            psMain.setString(8, process.getPublicationInGazetteRP());
            psMain.setString(9, process.getDateOfProposalToDCForNomination());
            psMain.setString(10, process.getDateOfNominationOfCompetentAuthority());
            psMain.setString(11, process.getDraftLetterToConForApprovalCA());
            psMain.setString(12, process.getDateOfApprovalOfConstructionCA());
            psMain.setString(13, process.getDateOfUploadingOfGazetteNotificationCA());
            psMain.setString(14, process.getPublicationInGazetteCA());
            psMain.setString(15, process.getLaFileType());

            psMain.executeUpdate();

            /* ===============================
               2. GET AUTO-INCREMENT ID
               =============================== */
            rs = psMain.getGeneratedKeys();
            if (rs.next()) {
                long generatedId = rs.getLong(1);
                process.setId(generatedId);
            } else {
                throw new Exception("Failed to get generated ID");
            }

            /* ===============================
               3. GENERATE la_id
               =============================== */
            String laId = generateLaId(con, process.getProjectId());
            process.setLaId(laId);

            /* ===============================
               4. UPDATE la_id IN MAIN TABLE
               =============================== */
            String updateLaId =
                "UPDATE land_acquisition_process SET la_id=? WHERE id=?";

            psUpdateLaId = con.prepareStatement(updateLaId);
            psUpdateLaId.setString(1, laId);
            psUpdateLaId.setLong(2, process.getId());
            psUpdateLaId.executeUpdate();

            /* ===============================
               5. INSERT CHILD TABLES
               =============================== */
            insertPrivateLands(con, process);
            insertGovernmentLands(con, process);
            insertForestLands(con, process);

            con.commit();
            flag = true;

        } catch (Exception e) {
            if (con != null) con.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (psMain != null) psMain.close();
            if (psUpdateLaId != null) psUpdateLaId.close();
            if (con != null) con.close();
        }

        return flag;
    }
    
    private String generateLaId(Connection con, String projectId) throws SQLException {

        String sql =
            "SELECT ISNULL(MAX(CAST(SUBSTRING(la_id, LEN(?)+6, 3) AS INT)), 0) + 1 " +
            "FROM land_acquisition_process WHERE project_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, projectId);
            ps.setString(2, projectId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int nextNo = rs.getInt(1);
                return projectId + "-LAP-" + String.format("%03d", nextNo);
            }
        }
        return null;
    }
 

    private void insertPrivateLands(Connection con, LandAcquisitionProcess process) throws Exception {

        if (process.getPrivateLands() == null || process.getPrivateLands().isEmpty()) {
            System.out.println("No Private Lands to insert");
            return;
        }

        String insertPrivate =
            "INSERT INTO private_land " +
            "(la_id_fk, district, taluka, village, collector, area_required, " +
            "chainage_from, chainage_to, survey_numbers, submission_of_proposal, " +
            "notification_under_20a, notification_under_20e, jm_measurement, " +
            "acquisition_under_20f, grievance_survey_nos) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(insertPrivate)) {

            for (PrivateLand pl : process.getPrivateLands()) {



            	ps.setString(1, process.getLaId());
            	ps.setString(2, pl.getDistrict());
            	ps.setString(3, pl.getTaluka());
            	ps.setString(4, pl.getVillage());
            	ps.setString(5, pl.getCollector());

            	// areaRequired: convert to Double safely
            	if (pl.getAreaRequired() != null && !pl.getAreaRequired().isEmpty()) {
            	    try {
            	        ps.setDouble(6, Double.parseDouble(pl.getAreaRequired()));
            	    } catch (NumberFormatException e) {
            	        ps.setDouble(6, 0.0); // fallback if invalid number
            	    }
            	} else {
            	    ps.setNull(6, java.sql.Types.DOUBLE); // store NULL if empty
            	}

            	ps.setString(7, pl.getChainageFrom());
            	ps.setString(8, pl.getChainageTo());
            	ps.setString(9, pl.getSurveyNumbers());
            	ps.setString(10, pl.getSubmissionOfProposal());
            	ps.setString(11, pl.getNotificationUnder20A());
            	ps.setString(12, pl.getNotificationUnder20E());
            	ps.setString(13, pl.getJmMeasurement());
            	ps.setString(14, pl.getAcquisitionUnder20F());
            	ps.setString(15, pl.getGrievanceSurveyNos());


                ps.addBatch();
            }

            ps.executeBatch();
        }
    }


    private void insertGovernmentLands(Connection con, LandAcquisitionProcess process) throws Exception {

        if (process.getGovernmentLands() == null || process.getGovernmentLands().isEmpty()) {
            System.out.println("No Government Lands to insert");
            return;
        }

        String insertGov =
            "INSERT INTO government_land " +
            "(la_id_fk, district, taluka, village, collector, agency, area_required, " +
            "chainage_from, chainage_to, survey_no, proposal_submission, " +
            "letter_of_payment, amount_demanded, noc, valuation_date, payment, " +
            "payment_date, land_transfer) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(insertGov)) {

            for (GovernmentLand gl : process.getGovernmentLands()) {



            	ps.setString(1, process.getLaId());
            	ps.setString(2, gl.getDistrict());
            	ps.setString(3, gl.getTaluka());
            	ps.setString(4, gl.getVillage());
            	ps.setString(5, gl.getCollector());
            	ps.setString(6, gl.getAgency());

            	// areaRequired: String to Double safely
            	if (gl.getAreaRequired() != null && !gl.getAreaRequired().isEmpty()) {
            	    try {
            	        ps.setDouble(7, Double.parseDouble(gl.getAreaRequired()));
            	    } catch (NumberFormatException e) {
            	        ps.setDouble(7, 0.0); // fallback if invalid
            	    }
            	} else {
            	    ps.setNull(7, java.sql.Types.DOUBLE);
            	}

            	ps.setString(8, gl.getChainageFrom());
            	ps.setString(9, gl.getChainageTo());
            	ps.setString(10, gl.getSurveyNo());
            	ps.setString(11, gl.getProposalSubmission());
            	ps.setString(12, gl.getLetterOfPayment());

            	// amountDemanded: nullable Double
            	if (gl.getAmountDemanded() != null) {
            	    ps.setDouble(13, gl.getAmountDemanded());
            	} else {
            	    ps.setNull(13, java.sql.Types.DOUBLE);
            	}

            	ps.setString(14, gl.getNoc());
            	ps.setString(15, gl.getValuationDate());

            	// payment: nullable Double
            	if (gl.getPayment() != null) {
            	    ps.setDouble(16, gl.getPayment());
            	} else {
            	    ps.setNull(16, java.sql.Types.DOUBLE);
            	}

            	ps.setString(17, gl.getPaymentDate());
            	ps.setString(18, gl.getLandTransfer());


                ps.addBatch();
            }

            ps.executeBatch();
        }
    }


    private void insertForestLands(Connection con, LandAcquisitionProcess process) throws Exception {

        if (process.getForestLands() == null || process.getForestLands().isEmpty()) {
            System.out.println("No Forest Lands to insert");
            return;
        }

        String insertForest =
            "INSERT INTO forest_land " +
            "(la_id_fk, district, taluka, village, collector, area_required, " +
            "chainage_from, chainage_to, submission_of_proposal, physical_verification, " +
            "first_stage_clearance, valuation, payment, payment_date, possession) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(insertForest)) {

            for (ForestLand fl : process.getForestLands()) {



            	ps.setString(1, process.getLaId());
            	ps.setString(2, fl.getDistrict());
            	ps.setString(3, fl.getTaluka());
            	ps.setString(4, fl.getVillage());
            	ps.setString(5, fl.getCollector());

            	// areaRequired: String to Double safely
            	if (fl.getAreaRequired() != null && !fl.getAreaRequired().isEmpty()) {
            	    try {
            	        ps.setDouble(6, Double.parseDouble(fl.getAreaRequired()));
            	    } catch (NumberFormatException e) {
            	        ps.setDouble(6, 0.0); // fallback if invalid
            	    }
            	} else {
            	    ps.setNull(6, java.sql.Types.DOUBLE);
            	}

            	ps.setString(7, fl.getChainageFrom());
            	ps.setString(8, fl.getChainageTo());
            	ps.setString(9, fl.getSubmissionOfProposal());
            	ps.setString(10, fl.getPhysicalVerification());
            	ps.setString(11, fl.getFirstStageClearance());

            	// valuation: nullable Double
            	if (fl.getValuation() != null) {
            	    ps.setDouble(12, fl.getValuation());
            	} else {
            	    ps.setNull(12, java.sql.Types.DOUBLE);
            	}

            	// payment: nullable Double
            	if (fl.getPayment() != null) {
            	    ps.setDouble(13, fl.getPayment());
            	} else {
            	    ps.setNull(13, java.sql.Types.DOUBLE);
            	}

            	ps.setString(14, fl.getPaymentDate());
            	ps.setString(15, fl.getPossession());


                ps.addBatch();
            }

            ps.executeBatch();
        }
    }
    private boolean isPrivateLandEmpty(PrivateLand pl) {
        return isBlank(pl.getDistrict())
            && isBlank(pl.getVillage())
            && isBlank(pl.getSurveyNumbers());
    }
   
    private boolean isGovernmentLandEmpty(GovernmentLand gl) {
        return isBlank(gl.getDistrict())
            && isBlank(gl.getVillage())
            && isBlank(gl.getSurveyNo());
    }
   
    private boolean isForestLandEmpty(ForestLand fl) {
        return isBlank(fl.getDistrict())
            && isBlank(fl.getVillage());
    }   
    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }



    // Update method follows similar approach
    @Override
    public boolean updateLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception {
        Connection con = null;
        boolean flag = false;

        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);

            String updateMain = "UPDATE land_acquisition_process SET project_id=?, status=?, submission_of_proposal_to_GM=?, approval_of_GM=?, draft_letter_to_con_for_approval_rp=?, date_of_approval_of_construction_rp=?, date_of_uploading_of_gazette_notification_rp=?, publication_in_gazette_rp=?, date_of_proposal_to_DC_for_nomination=?, date_of_nomination_of_competent_authority=?, draft_letter_to_con_for_approval_ca=?, date_of_approval_of_construction_ca=?, date_of_uploading_of_gazette_notification_ca=?, publication_in_gazette_ca=?, la_file_type=? WHERE id=?";
            jdbcTemplate.update(updateMain,
            	    process.getProjectId(),                    // 1
            	    process.getStatus(),                       // 2
            	    process.getSubmissionOfProposalToGM(),     // 3
            	    process.getApprovalOfGM(),                 // 4
            	    process.getDraftLetterToConForApprovalRP(),// 5
            	    process.getDateOfApprovalOfConstructionRP(),// 6
            	    process.getDateOfUploadingOfGazetteNotificationRP(),// 7
            	    process.getPublicationInGazetteRP(),       // 8
            	    process.getDateOfProposalToDCForNomination(),// 9
            	    process.getDateOfNominationOfCompetentAuthority(),// 10
            	    process.getDraftLetterToConForApprovalCA(),// 11
            	    process.getDateOfApprovalOfConstructionCA(),// 12
            	    process.getDateOfUploadingOfGazetteNotificationCA(),// 13
            	    process.getPublicationInGazetteCA(),       // 14
            	    process.getLaFileType(),                   // 15
            	    process.getId()                            // 16
            	);

            // Delete old child records and re-insert
            deleteChildLands(con, process.getLaId());

            insertPrivateLands(con, process);
            insertGovernmentLands(con, process);
            insertForestLands(con, process);

            con.commit();
            flag = true;
        } catch (Exception e) {
            if (con != null) con.rollback();
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            if (con != null) con.close();
        }
        return flag;
    }

    private void deleteChildLands(Connection con, String laId) throws Exception {
        String deletePrivate = "DELETE FROM private_land WHERE la_id_fk=?";
        String deleteGov = "DELETE FROM government_land WHERE la_id_fk=?";
        String deleteForest = "DELETE FROM forest_land WHERE la_id_fk=?";
        try (PreparedStatement ps1 = con.prepareStatement(deletePrivate);
             PreparedStatement ps2 = con.prepareStatement(deleteGov);
             PreparedStatement ps3 = con.prepareStatement(deleteForest)) {
            ps1.setString(1, laId);
            ps1.executeUpdate();

            ps2.setString(1, laId);
            ps2.executeUpdate();

            ps3.setString(1, laId);
            ps3.executeUpdate();
        }
    }

    @Override
    public List<LandAcquisitionProcess> getLandAcquisitionList(String project_id, String la_file_type,String searchValue, int start,int length) {
        List<LandAcquisitionProcess> list = new ArrayList<>();
        String sql = "SELECT *,(select project_name from project where project_id=land_acquisition_process.project_id) as project_name FROM land_acquisition_process WHERE 1=1";

        if (project_id != null && !project_id.isEmpty()) sql += " AND project_id = ?";
        if (la_file_type != null && !la_file_type.isEmpty()) sql += " AND la_file_type = ?";
        
        if (searchValue != null && !searchValue.isEmpty()) {
            sql += " AND ("
                 + "CAST(id AS CHAR) LIKE ? OR "
                 + "la_id LIKE ? OR "
                 + "project_id LIKE ? OR "
                 + "status LIKE ? OR "
                 + "submission_of_proposal_to_GM LIKE ? OR "
                 + "approval_of_GM LIKE ? OR "
                 + "draft_letter_to_con_for_approval_rp LIKE ? OR "
                 + "date_of_approval_of_construction_rp LIKE ? OR "
                 + "date_of_uploading_of_gazette_notification_rp LIKE ? OR "
                 + "publication_in_gazette_rp LIKE ? OR "
                 + "date_of_proposal_to_DC_for_nomination LIKE ? OR "
                 + "date_of_nomination_of_competent_authority LIKE ? OR "
                 + "draft_letter_to_con_for_approval_ca LIKE ? OR "
                 + "date_of_approval_of_construction_ca LIKE ? OR "
                 + "date_of_uploading_of_gazette_notification_ca LIKE ? OR "
                 + "publication_in_gazette_ca LIKE ? OR "
                 + "la_file_type LIKE ? OR "
                 + "CAST(created_date AS CHAR) LIKE ? OR "
                 + "CAST(updated_date AS CHAR) LIKE ?"
                 + ")";
        }        

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int idx = 1;
            if (project_id != null && !project_id.isEmpty()) ps.setString(idx++, project_id);
            if (la_file_type != null && !la_file_type.isEmpty()) ps.setString(idx++, la_file_type);
            if (searchValue != null && !searchValue.isEmpty()) {
                String s = "%" + searchValue + "%";

                ps.setString(idx++, s); // id
                ps.setString(idx++, s); // la_id
                ps.setString(idx++, s); // project_id
                ps.setString(idx++, s); // status
                ps.setString(idx++, s); // submission_of_proposal_to_GM
                ps.setString(idx++, s); // approval_of_GM
                ps.setString(idx++, s); // draft_letter_to_con_for_approval_rp
                ps.setString(idx++, s); // date_of_approval_of_construction_rp
                ps.setString(idx++, s); // date_of_uploading_of_gazette_notification_rp
                ps.setString(idx++, s); // publication_in_gazette_rp
                ps.setString(idx++, s); // date_of_proposal_to_DC_for_nomination
                ps.setString(idx++, s); // date_of_nomination_of_competent_authority
                ps.setString(idx++, s); // draft_letter_to_con_for_approval_ca
                ps.setString(idx++, s); // date_of_approval_of_construction_ca
                ps.setString(idx++, s); // date_of_uploading_of_gazette_notification_ca
                ps.setString(idx++, s); // publication_in_gazette_ca
                ps.setString(idx++, s); // la_file_type
                ps.setString(idx++, s); // created_date
                ps.setString(idx++, s); // updated_date
            }           

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                LandAcquisitionProcess dto = new LandAcquisitionProcess();

                dto.setId(rs.getLong("id"));
                dto.setLaId(rs.getString("la_id"));

                // ✅ Correct column names
                dto.setProjectId(rs.getString("project_id"));
                dto.setProjectName(rs.getString("project_name"));
                dto.setStatus(rs.getString("status"));
                dto.setLaFileType(rs.getString("la_file_type"));

                // Railway project declaration
                dto.setSubmissionOfProposalToGM(rs.getString("submission_of_proposal_to_GM"));
                dto.setApprovalOfGM(rs.getString("approval_of_GM"));
                dto.setDraftLetterToConForApprovalRP(
                    rs.getString("draft_letter_to_con_for_approval_rp"));
                dto.setDateOfApprovalOfConstructionRP(
                    rs.getString("date_of_approval_of_construction_rp"));
                dto.setDateOfUploadingOfGazetteNotificationRP(
                    rs.getString("date_of_uploading_of_gazette_notification_rp"));
                dto.setPublicationInGazetteRP(
                    rs.getString("publication_in_gazette_rp"));

                // Nomination of competent authority
                dto.setDateOfProposalToDCForNomination(
                    rs.getString("date_of_proposal_to_DC_for_nomination"));
                dto.setDateOfNominationOfCompetentAuthority(
                    rs.getString("date_of_nomination_of_competent_authority"));
                dto.setDraftLetterToConForApprovalCA(
                    rs.getString("draft_letter_to_con_for_approval_ca"));
                dto.setDateOfApprovalOfConstructionCA(
                    rs.getString("date_of_approval_of_construction_ca"));
                dto.setDateOfUploadingOfGazetteNotificationCA(
                    rs.getString("date_of_uploading_of_gazette_notification_ca"));
                dto.setPublicationInGazetteCA(
                    rs.getString("publication_in_gazette_ca"));

                list.add(dto);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public long getLandAcquisitionCount(String project_id, String la_file_type,String searchValue) {

        long count = 0;

        String sql = "SELECT COUNT(*) FROM land_acquisition_process WHERE 1=1";

        if (project_id != null && !project_id.isEmpty()) {
            sql += " AND project_id = ?";
        }
        if (la_file_type != null && !la_file_type.isEmpty()) {
            sql += " AND la_file_type = ?";
        }
        if (searchValue != null && !searchValue.isEmpty()) {
            sql += " AND ("
                 + "CAST(id AS CHAR) LIKE ? OR "
                 + "la_id LIKE ? OR "
                 + "project_id LIKE ? OR "
                 + "status LIKE ? OR "
                 + "submission_of_proposal_to_GM LIKE ? OR "
                 + "approval_of_GM LIKE ? OR "
                 + "draft_letter_to_con_for_approval_rp LIKE ? OR "
                 + "date_of_approval_of_construction_rp LIKE ? OR "
                 + "date_of_uploading_of_gazette_notification_rp LIKE ? OR "
                 + "publication_in_gazette_rp LIKE ? OR "
                 + "date_of_proposal_to_DC_for_nomination LIKE ? OR "
                 + "date_of_nomination_of_competent_authority LIKE ? OR "
                 + "draft_letter_to_con_for_approval_ca LIKE ? OR "
                 + "date_of_approval_of_construction_ca LIKE ? OR "
                 + "date_of_uploading_of_gazette_notification_ca LIKE ? OR "
                 + "publication_in_gazette_ca LIKE ? OR "
                 + "la_file_type LIKE ? OR "
                 + "CAST(created_date AS CHAR) LIKE ? OR "
                 + "CAST(updated_date AS CHAR) LIKE ?"
                 + ")";
        }       

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int idx = 1;

            if (project_id != null && !project_id.isEmpty()) {
                ps.setString(idx++, project_id);
            }
            if (la_file_type != null && !la_file_type.isEmpty()) {
                ps.setString(idx++, la_file_type);
            }
            if (searchValue != null && !searchValue.isEmpty()) {
                String s = "%" + searchValue + "%";

                ps.setString(idx++, s); // id
                ps.setString(idx++, s); // la_id
                ps.setString(idx++, s); // project_id
                ps.setString(idx++, s); // status
                ps.setString(idx++, s); // submission_of_proposal_to_GM
                ps.setString(idx++, s); // approval_of_GM
                ps.setString(idx++, s); // draft_letter_to_con_for_approval_rp
                ps.setString(idx++, s); // date_of_approval_of_construction_rp
                ps.setString(idx++, s); // date_of_uploading_of_gazette_notification_rp
                ps.setString(idx++, s); // publication_in_gazette_rp
                ps.setString(idx++, s); // date_of_proposal_to_DC_for_nomination
                ps.setString(idx++, s); // date_of_nomination_of_competent_authority
                ps.setString(idx++, s); // draft_letter_to_con_for_approval_ca
                ps.setString(idx++, s); // date_of_approval_of_construction_ca
                ps.setString(idx++, s); // date_of_uploading_of_gazette_notification_ca
                ps.setString(idx++, s); // publication_in_gazette_ca
                ps.setString(idx++, s); // la_file_type
                ps.setString(idx++, s); // created_date
                ps.setString(idx++, s); // updated_date
            }         

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getLong(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    public boolean isProjectAlreadyExists(String projectId) {
        String sql = "SELECT COUNT(*) FROM land_acquisition_process WHERE project_id = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, projectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
