package com.example.model;

public class CollaborationDuration implements Comparable<CollaborationDuration> {
    Integer ProjectID;
    Long DaysWorked;

    public CollaborationDuration(Integer ProjectID, Long DaysWorked) {
        this.ProjectID = ProjectID;
        this.DaysWorked = DaysWorked;
    }

    public Integer getProjectID() {
        return this.ProjectID;
    }

    public Long getDaysWorked() {
        return this.DaysWorked;
    }

    @Override
    public int compareTo(CollaborationDuration other) {
        return this.DaysWorked != other.getDaysWorked() ? other.getDaysWorked().compareTo(this.DaysWorked)
                : this.ProjectID.compareTo(other.getProjectID());
    }

    @Override
    public String toString() {
        return "{" +
                " ProjectID='" + getProjectID() + "'" +
                ", DaysWorked='" + getDaysWorked() + "'" +
                "}";
    }

}
