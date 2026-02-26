package com.synergizglobal.wrpmis.model;

public class GovernmentLand {

    private String id;
    private String laIdFk;
    private String district;
    private String taluka;
    private String village;
    private String collector;
    private String agency;
    private String areaRequired; // DAO uses setDouble
    private String chainageFrom;
    private String chainageTo;
    private String surveyNo; // DAO calls getSurveyNo()
    private String proposalSubmission;
    private String letterOfPayment;
    private Double amountDemanded; // DAO uses setDouble
    private String noc;
    private String valuationDate;
    private Double payment; // DAO uses setDouble
    private String paymentDate;
    private String landTransfer;

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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
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

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getProposalSubmission() {
        return proposalSubmission;
    }

    public void setProposalSubmission(String proposalSubmission) {
        this.proposalSubmission = proposalSubmission;
    }

    public String getLetterOfPayment() {
        return letterOfPayment;
    }

    public void setLetterOfPayment(String letterOfPayment) {
        this.letterOfPayment = letterOfPayment;
    }

    public Double getAmountDemanded() {
        return amountDemanded;
    }

    public void setAmountDemanded(Double amountDemanded) {
        this.amountDemanded = amountDemanded;
    }

    public String getNoc() {
        return noc;
    }

    public void setNoc(String noc) {
        this.noc = noc;
    }

    public String getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(String valuationDate) {
        this.valuationDate = valuationDate;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getLandTransfer() {
        return landTransfer;
    }

    public void setLandTransfer(String landTransfer) {
        this.landTransfer = landTransfer;
    }
}
