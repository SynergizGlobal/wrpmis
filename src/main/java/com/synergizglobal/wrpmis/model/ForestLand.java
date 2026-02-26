package com.synergizglobal.wrpmis.model;

public class ForestLand {

    private String id;
    private String laIdFk;
    private String district;
    private String taluka;
    private String village;
    private String collector;
    private String areaRequired; // DAO uses setDouble
    private String chainageFrom;
    private String chainageTo;
    private String submissionOfProposal;
    private String physicalVerification;
    private String firstStageClearance;
    private Double valuation; // DAO uses setDouble
    private Double payment; // DAO uses setDouble
    private String paymentDate;
    private String possession;

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

    public String getSubmissionOfProposal() {
        return submissionOfProposal;
    }

    public void setSubmissionOfProposal(String submissionOfProposal) {
        this.submissionOfProposal = submissionOfProposal;
    }

    public String getPhysicalVerification() {
        return physicalVerification;
    }

    public void setPhysicalVerification(String physicalVerification) {
        this.physicalVerification = physicalVerification;
    }

    public String getFirstStageClearance() {
        return firstStageClearance;
    }

    public void setFirstStageClearance(String firstStageClearance) {
        this.firstStageClearance = firstStageClearance;
    }

    public Double getValuation() {
        return valuation;
    }

    public void setValuation(Double valuation) {
        this.valuation = valuation;
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

    public String getPossession() {
        return possession;
    }

    public void setPossession(String possession) {
        this.possession = possession;
    }
}
