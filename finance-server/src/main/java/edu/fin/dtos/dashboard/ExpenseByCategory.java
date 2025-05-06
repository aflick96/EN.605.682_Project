/*
 * ExpenseByCategory.java
 * 
 * This class represents the breakdown of expenses by category for a user. It contains a list of labels (categories) and a list of values (amounts spent in each category).
 */

package edu.fin.dtos.dashboard;

import java.util.List;

public class ExpenseByCategory {
    private List<String> labels;
    private List<Double> values;

    // constructors
    public ExpenseByCategory() {}
    public ExpenseByCategory(List<String> labels, List<Double> values) {
        this.labels = labels;
        this.values = values;
    }

    // getters and setters
    public List<String> getLabels() { return labels; }
    public void setLabels(List<String> labels) { this.labels = labels; }

    public List<Double> getValues() { return values; }
    public void setValues(List<Double> values) { this.values = values; }

    @Override
    public String toString() {
        return "ExpenseByCategory{" +
                "labels=" + labels +
                ", values=" + values +
                '}';
    }
}
