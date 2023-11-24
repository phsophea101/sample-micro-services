package com.example.gateway.enums;

public enum DiscoveryServiceStatusType {
    PUBLISHED("PUBLISHED"),
    TESTED("TESTED"),
    INACTIVE("INACTIVE");

    private String statusName;

    DiscoveryServiceStatusType(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
