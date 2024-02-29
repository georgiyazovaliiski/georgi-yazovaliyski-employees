package com.example.model;

import java.util.Objects;

public class EmployeePair {
    private Integer FirstEmployeeID;
    private Integer SecondEmployeeID;

    public EmployeePair(Integer AID, Integer BID) {
        this.FirstEmployeeID = AID < BID ? AID : BID;
        this.SecondEmployeeID = AID < BID ? BID : AID;
    }

    public Integer getFirstEmployeeID() {
        return this.FirstEmployeeID;
    }

    public Integer getSecondEmployeeID() {
        return this.SecondEmployeeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmployeePair anotherEmployeePair = (EmployeePair) o;
        return (FirstEmployeeID.equals(anotherEmployeePair.getFirstEmployeeID())
                && SecondEmployeeID.equals(anotherEmployeePair.getSecondEmployeeID()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(FirstEmployeeID, SecondEmployeeID);
    }

    @Override
    public String toString() {
        return "{" +
                " FirstEmployeeID='" + getFirstEmployeeID() + "'" +
                ", SecondEmployeeID='" + getSecondEmployeeID() + "'" +
                "}";
    }

}
