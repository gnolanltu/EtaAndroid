package com.riis.simple.etaandroid;

public class UrlStringBuilder {

    public String getRoutesUrl(int company) {
        return "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/" + company + "/routes";
    }

    public String getStopsUrl(int companyNumber, String routeId, String direction, String daysActive) {
        return "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/" +
                companyNumber + "/routes/" + routeId + "/" + direction + "/" + daysActive + "/1/stops";
    }
}
