package com.mycompany.ql.pham.nhan.trai.giam.v1.src.models;

public class PrisonSummary {
    private String prisonName;
    private int prisonerCount;

    public PrisonSummary(String prisonName, int prisonerCount) {
        this.prisonName = prisonName;
        this.prisonerCount = prisonerCount;
    }

    public String getPrisonName() {
        return prisonName;
    }

    public int getPrisonerCount() {
        return prisonerCount;
    }

    @Override
    public String toString() {
        return "Tên trại: " + prisonName + ", Số lượng phạm nhân: " + prisonerCount;
    }
}
