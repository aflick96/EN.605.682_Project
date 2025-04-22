package edu.fin.dtos.investment;

import java.time.LocalDate;

public class InvestmentLogRequest {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double expectedAnnualReturn;

    public InvestmentLogRequest() {}

    public InvestmentLogRequest(Long id, String name, LocalDate startDate, LocalDate endDate, Double expectedAnnualReturn) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedAnnualReturn = expectedAnnualReturn;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getExpectedAnnualReturn() { return expectedAnnualReturn; }
    public void setExpectedAnnualReturn(Double expectedAnnualReturn) { this.expectedAnnualReturn = expectedAnnualReturn; }

    @Override
    public String toString() {
        return "InvestmentLog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", expectedAnnualReturn=" + expectedAnnualReturn +
                '}';
    }
}
