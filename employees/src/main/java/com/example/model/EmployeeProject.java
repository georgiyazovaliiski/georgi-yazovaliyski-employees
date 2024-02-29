package com.example.model;

import java.time.LocalDate;

public class EmployeeProject implements Comparable<EmployeeProject> {

    public EmployeeProject(Integer EmployeeID, Integer ProjectID, LocalDate DateFrom, LocalDate DateTo) {
        this.EmployeeID = EmployeeID;
        this.ProjectID = ProjectID;
        this.DateFrom = DateFrom;
        this.DateTo = DateTo;
    }

    @Override
    public String toString() {
        return "{" +
                " EmployeeID='" + getEmployeeID() + "'" +
                ", ProjectID='" + getProjectID() + "'" +
                ", DateFrom='" + getDateFrom() + "'" +
                ", DateTo='" + getDateTo() + "'" +
                "}";
    }

    public EmployeeProject() {
    }

    private Integer EmployeeID;
    private Integer ProjectID;
    private LocalDate DateFrom;
    private LocalDate DateTo;

    public Integer getEmployeeID() {
        return this.EmployeeID;
    }

    public void setEmployeeID(Integer EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public Integer getProjectID() {
        return this.ProjectID;
    }

    public void setProjectID(Integer ProjectID) {
        this.ProjectID = ProjectID;
    }

    public LocalDate getDateFrom() {
        return this.DateFrom;
    }

    public void setDateFrom(LocalDate DateFrom) {
        this.DateFrom = DateFrom;
    }

    public LocalDate getDateTo() {
        return this.DateTo;
    }

    public void setDateTo(LocalDate DateTo) {
        this.DateTo = DateTo;
    }

    @Override
    public int compareTo(EmployeeProject other) {
        if (!this.DateFrom.equals(other.getDateFrom())) {
            return this.DateFrom.compareTo(other.getDateFrom());
        }

        if (!this.DateTo.equals(other.getDateTo())) {
            return this.DateTo.compareTo(other.getDateTo());
        }

        return this.EmployeeID.compareTo(other.getEmployeeID());
    }
}
