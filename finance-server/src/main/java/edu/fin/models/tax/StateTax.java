package edu.fin.models.tax;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class StateTax {
    private Map<String, State> states = new HashMap<>();

    public Map<String, State> getStates() {
        return states;
    }

    public void setStates(Map<String, State> states) {
        this.states = states;
    }
    
    @JsonAnySetter
    public void addState(String stateName, State state) {
    	this.states.put(stateName, state);
    }

    public static class State {
        private FilingStatus single;
        private FilingStatus married;

        public FilingStatus getSingle() {
            return single;
        }

        public void setSingle(FilingStatus single) {
            this.single = single;
        }

        public FilingStatus getMarried() {
            return married;
        }

        public void setMarried(FilingStatus married) {
            this.married = married;
        }
    }
}
