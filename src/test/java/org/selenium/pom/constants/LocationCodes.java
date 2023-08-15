package org.selenium.pom.constants;

import java.util.Map;

public class LocationCodes {
	
	public static String getCountryCode(String countryName){
        Map<String, String> countryMap = Map.of(
        		"United Kingdom", "GB",
        		"India", "IN",
        		"United States", "US"
        );
        return countryMap.get(countryName);
    }

    public static String getStateCode(String stateName){
        Map<String, String> stateMap = Map.of(
        		"Maharashtra", "MH",
        		"California", "CA"
        );
        return stateMap.get(stateName);
    }

}
