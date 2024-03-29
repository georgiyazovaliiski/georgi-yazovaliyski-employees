package com.example.dto;

import java.util.TreeSet;

import com.example.model.CollaborationDuration;
import com.example.model.EmployeePair;

public class CollaboratorDTO {
    String ErrorMessage;
    EmployeePair Employees;
    CollaborationDuration[] Collaborations;

    public CollaboratorDTO(EmployeePair Employees, TreeSet<CollaborationDuration> Collaborations) {
        this.Employees = Employees;
        this.Collaborations = Collaborations.toArray(new CollaborationDuration[Collaborations.size()]);
    }

    public CollaboratorDTO(String ErrorMessage) {
        this.Employees = null;
        this.Collaborations = null;
        this.ErrorMessage = ErrorMessage;
    }

    public EmployeePair getEmployees() {
        return this.Employees;
    }

    public void setEmployees(EmployeePair Employees) {
        this.Employees = Employees;
    }

    public CollaborationDuration[] getCollaborations() {
        return this.Collaborations;
    }

    public void setCollaborations(CollaborationDuration[] Collaborations) {
        this.Collaborations = Collaborations;
    }

    public String getErrorMessage() {
        return this.ErrorMessage;
    }

    @Override
    public String toString() {
        return "{" +
                " Employees='" + getEmployees() + "'" +
                ", Collaborations='" + CollaborationsToString() + "'" +
                "}";
    }

    private String CollaborationsToString() {
        String StringifiedCollabs = "";
        for (CollaborationDuration collaborationDuration : Collaborations) {
            String StringifiedCollab = "{ Project = " + collaborationDuration.getProjectID() + ", Days Worked = "
                    + collaborationDuration.getDaysWorked() + " }";
            StringifiedCollabs += StringifiedCollab;
        }
        return StringifiedCollabs;
    }

}
