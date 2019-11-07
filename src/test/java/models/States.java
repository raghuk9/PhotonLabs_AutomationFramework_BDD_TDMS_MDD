package models;

import java.util.HashMap;
import java.util.Map;

public class States {

    private static Map<String, String> stateList = null;

    private static void initializeStates() {
        stateList = new HashMap<String, String>();
        stateList.put("AL", "Alabama");
        stateList.put("AK", "Alaska");
        stateList.put("AZ", "Arizona");
        stateList.put("AR", "Arkansas");
        stateList.put("CA", "California");
        stateList.put("CO", "Colorado");
        stateList.put("CT", "Connecticut");
        stateList.put("DE", "Delaware");
        stateList.put("DC", "District of Columbia");
        stateList.put("FL", "Florida");
        stateList.put("GA", "Georgia");
        stateList.put("HI", "Hawaii");
        stateList.put("ID", "Idaho");
        stateList.put("IL", "Illinois");
        stateList.put("IN", "Indiana");
        stateList.put("IW", "Iowa");
        stateList.put("KS", "Kansas");
        stateList.put("KY", "Kentucky");
        stateList.put("LA", "Louisiana");
        stateList.put("ME", "Maine");
        stateList.put("MD", "Maryland");
        stateList.put("MA", "Massachusetts");
        stateList.put("MI", "Michigan");
        stateList.put("MN", "Minnesota");
        stateList.put("MS", "Mississippi");
        stateList.put("MO", "Missouri");
        stateList.put("MT", "Montana");
        stateList.put("NE", "Nebraska");
        stateList.put("NV", "Nevada");
        stateList.put("NH", "New Hampshire");
        stateList.put("NJ", "New Jersy");
        stateList.put("NM", "New Mexico");
        stateList.put("NY", "New York");
        stateList.put("NC", "North Carolina");
        stateList.put("NO", "North Dakota");
        stateList.put("OH", "Ohio");
        stateList.put("OK", "Oklahoma");
        stateList.put("OR", "Oregon");
        stateList.put("PA", "Pennsylvania");
        stateList.put("RI", "Rhode Island");
        stateList.put("SC", "South Carolina");
        stateList.put("SD", "South Dakota");
        stateList.put("TN", "Tennessee");
        stateList.put("TX", "Texas");
        stateList.put("UT", "Utah");
        stateList.put("VT", "Vermont");
        stateList.put("VA", "Virginia");
        stateList.put("WA", "Washington");
        stateList.put("WV", "West Virginia");
        stateList.put("WI", "Wisconsin");
        stateList.put("WY", "Wyoming");
    }

    public static String getStateName(String key) throws Exception {
        if (stateList == null) {
            initializeStates();
        }
        if (stateList.containsKey(key)) {
            return stateList.get(key);
        } else {
            throw new Exception("No State value found for key : " + key);
        }
    }
}
