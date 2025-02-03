package edu.fin.controllers.utils;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class UserControllerUtil {
    private static final List<String> US_STATES = Arrays.asList(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
            "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
            "VA", "WA", "WV", "WI", "WY"
        );
    
    public boolean isValidState(String state) {
    	return US_STATES.contains(state.toUpperCase());
    }
}
