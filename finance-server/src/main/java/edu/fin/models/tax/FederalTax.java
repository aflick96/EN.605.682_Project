package edu.fin.models.tax;

import java.util.List;

public class FederalTax {
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

    public static class FilingStatus {
        private Integer standardDeduction;
        private List<IncomeTaxBracket> incomeTaxBrackets;

        public Integer getStandardDeduction() {
            return standardDeduction;
        }

        public void setStandardDeduction(Integer standardDeduction) {
            this.standardDeduction = standardDeduction;
        }

        public List<IncomeTaxBracket> getIncomeTaxBrackets() {
            return incomeTaxBrackets;
        }

        public void setIncomeTaxBrackets(List<IncomeTaxBracket> incomeTaxBrackets) {
            this.incomeTaxBrackets = incomeTaxBrackets;
        }
    }

    public static class IncomeTaxBracket {
        private Double rate;
        private Integer bracket; // Can be null for the highest tax bracket

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public Integer getBracket() {
            return bracket;
        }

        public void setBracket(Integer bracket) {
            this.bracket = bracket;
        }
    }
}
