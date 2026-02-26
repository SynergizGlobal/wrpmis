package com.synergizglobal.wrpmis.model;

import java.util.ArrayList;
import java.util.List;

public class LandAcquisitionProcess {
	
	public LandAcquisitionProcess() {}

    private String laId; // DAO expects/get/set String laId (generated key)
    private String projectId;
    private String projectName;
    private String workId;
    private String status;
    private String laFileType;
    
    private String project_id,project_id_fk,project_name;
    

    // Declaration of railway project
    private String submissionOfProposalToGM;
    private String approvalOfGM;
    private String draftLetterToConForApprovalRP;
    private String dateOfApprovalOfConstructionRP;
    private String dateOfUploadingOfGazetteNotificationRP;
    private String publicationInGazetteRP;

    // Nomination of competent authority
    private String dateOfProposalToDCForNomination;
    private String dateOfNominationOfCompetentAuthority;
    private String draftLetterToConForApprovalCA;
    private String dateOfApprovalOfConstructionCA;
    private String dateOfUploadingOfGazetteNotificationCA;
    private String publicationInGazetteCA;
    private Long id;

    private List<PrivateLand> privateLands;
    private List<GovernmentLand> governmentLands;
    private List<ForestLand> forestLands;

    // Getters / Setters

    public String getLaId() {
        return laId;
    }

    public void setLaId(String laId) {
        this.laId = laId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLaFileType() {
        return laFileType;
    }

    public void setLaFileType(String laFileType) {
        this.laFileType = laFileType;
    }

    public String getSubmissionOfProposalToGM() {
        return submissionOfProposalToGM;
    }

    public void setSubmissionOfProposalToGM(String submissionOfProposalToGM) {
        this.submissionOfProposalToGM = submissionOfProposalToGM;
    }

    public String getApprovalOfGM() {
        return approvalOfGM;
    }

    public void setApprovalOfGM(String approvalOfGM) {
        this.approvalOfGM = approvalOfGM;
    }

    public String getDraftLetterToConForApprovalRP() {
        return draftLetterToConForApprovalRP;
    }

    public void setDraftLetterToConForApprovalRP(String draftLetterToConForApprovalRP) {
        this.draftLetterToConForApprovalRP = draftLetterToConForApprovalRP;
    }

    public String getDateOfApprovalOfConstructionRP() {
        return dateOfApprovalOfConstructionRP;
    }

    public void setDateOfApprovalOfConstructionRP(String dateOfApprovalOfConstructionRP) {
        this.dateOfApprovalOfConstructionRP = dateOfApprovalOfConstructionRP;
    }

    public String getDateOfUploadingOfGazetteNotificationRP() {
        return dateOfUploadingOfGazetteNotificationRP;
    }

    public void setDateOfUploadingOfGazetteNotificationRP(String dateOfUploadingOfGazetteNotificationRP) {
        this.dateOfUploadingOfGazetteNotificationRP = dateOfUploadingOfGazetteNotificationRP;
    }

    public String getPublicationInGazetteRP() {
        return publicationInGazetteRP;
    }

    public void setPublicationInGazetteRP(String publicationInGazetteRP) {
        this.publicationInGazetteRP = publicationInGazetteRP;
    }

    public String getDateOfProposalToDCForNomination() {
        return dateOfProposalToDCForNomination;
    }

    public void setDateOfProposalToDCForNomination(String dateOfProposalToDCForNomination) {
        this.dateOfProposalToDCForNomination = dateOfProposalToDCForNomination;
    }

    public String getDateOfNominationOfCompetentAuthority() {
        return dateOfNominationOfCompetentAuthority;
    }

    public void setDateOfNominationOfCompetentAuthority(String dateOfNominationOfCompetentAuthority) {
        this.dateOfNominationOfCompetentAuthority = dateOfNominationOfCompetentAuthority;
    }

    public String getDraftLetterToConForApprovalCA() {
        return draftLetterToConForApprovalCA;
    }

    public void setDraftLetterToConForApprovalCA(String draftLetterToConForApprovalCA) {
        this.draftLetterToConForApprovalCA = draftLetterToConForApprovalCA;
    }

    public String getDateOfApprovalOfConstructionCA() {
        return dateOfApprovalOfConstructionCA;
    }

    public void setDateOfApprovalOfConstructionCA(String dateOfApprovalOfConstructionCA) {
        this.dateOfApprovalOfConstructionCA = dateOfApprovalOfConstructionCA;
    }

    public String getDateOfUploadingOfGazetteNotificationCA() {
        return dateOfUploadingOfGazetteNotificationCA;
    }

    public void setDateOfUploadingOfGazetteNotificationCA(String dateOfUploadingOfGazetteNotificationCA) {
        this.dateOfUploadingOfGazetteNotificationCA = dateOfUploadingOfGazetteNotificationCA;
    }

    public String getPublicationInGazetteCA() {
        return publicationInGazetteCA;
    }

    public void setPublicationInGazetteCA(String publicationInGazetteCA) {
        this.publicationInGazetteCA = publicationInGazetteCA;
    }

    public List<PrivateLand> getPrivateLands() {
        return privateLands;
    }

    public void setPrivateLands(List<PrivateLand> privateLands) {
        this.privateLands = privateLands;
    }

    public List<GovernmentLand> getGovernmentLands() {
        return governmentLands;
    }

    public void setGovernmentLands(List<GovernmentLand> governmentLands) {
        this.governmentLands = governmentLands;
    }

    public List<ForestLand> getForestLands() {
        return forestLands;
    }

    public void setForestLands(List<ForestLand> forestLands) {
        this.forestLands = forestLands;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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
}
