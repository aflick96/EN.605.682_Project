package edu.fin.dtos.dashboard;

import java.util.List;

public class ExpenseByCategory {
    private List<String> labels;
    private List<Double> values;

    public ExpenseByCategory() {}

    public ExpenseByCategory(List<String> labels, List<Double> values) {
        this.labels = labels;
        this.values = values;
    }

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
