package com.synergizglobal.wrpmis.model;

public class PrivateLand {

    private String id;
    private String laIdFk;
    private String district;
    private String taluka;
    private String village;
    private String collector;
    private String areaRequired; // DAO uses setDouble
    private String chainageFrom;
    private String chainageTo;
    private String surveyNumbers; // DAO expects getSurveyNumbers()
    private String submissionOfProposal;
    private String notificationUnder20A;
    private String notificationUnder20E;
    private String jmMeasurement;
    private String acquisitionUnder20F;
    private String grievanceSurveyNos;

    // Getters / Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLaIdFk() {
        return laIdFk;
    }

    public void setLaIdFk(String laIdFk) {
        this.laIdFk = laIdFk;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getAreaRequired() {
        return areaRequired;
    }

    public void setAreaRequired(String areaRequired) {
        this.areaRequired = areaRequired;
    }

    public String getChainageFrom() {
        return chainageFrom;
    }

    public void setChainageFrom(String chainageFrom) {
        this.chainageFrom = chainageFrom;
    }

    public String getChainageTo() {
        return chainageTo;
    }

    public void setChainageTo(String chainageTo) {
        this.chainageTo = chainageTo;
    }

    public String getSurveyNumbers() {
        return surveyNumbers;
    }

    public void setSurveyNumbers(String surveyNumbers) {
        this.surveyNumbers = surveyNumbers;
    }

    public String getSubmissionOfProposal() {
        return submissionOfProposal;
    }

    public void setSubmissionOfProposal(String submissionOfProposal) {
        this.submissionOfProposal = submissionOfProposal;
    }

    public String getNotificationUnder20A() {
        return notificationUnder20A;
    }

    public void setNotificationUnder20A(String notificationUnder20A) {
        this.notificationUnder20A = notificationUnder20A;
    }

    public String getNotificationUnder20E() {
        return notificationUnder20E;
    }

    public void setNotificationUnder20E(String notificationUnder20E) {
        this.notificationUnder20E = notificationUnder20E;
    }

    public String getJmMeasurement() {
        return jmMeasurement;
    }

    public void setJmMeasurement(String jmMeasurement) {
        this.jmMeasurement = jmMeasurement;
    }

    public String getAcquisitionUnder20F() {
        return acquisitionUnder20F;
    }

    public void setAcquisitionUnder20F(String acquisitionUnder20F) {
        this.acquisitionUnder20F = acquisitionUnder20F;
    }

    public String getGrievanceSurveyNos() {
        return grievanceSurveyNos;
    }

    public void setGrievanceSurveyNos(String grievanceSurveyNos) {
        this.grievanceSurveyNos = grievanceSurveyNos;
    }
}
