package edu.fin.dtos.dashboard;

import java.time.LocalDate;

public class NetWorth {
    private LocalDate date;
    private Double netWorth;
    
    public NetWorth() {}
    
    public NetWorth(LocalDate date, Double netWorth) {
        this.date = date;
        this.netWorth = netWorth;
    }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getNetWorth() { return netWorth; }
    public void setNetWorth(Double netWorth) { this.netWorth = netWorth; }	
    
    @Override
    public String toString() {
        return "NetWorthOvertime{" +
                "date='" + date + '\'' +
                ", netWorth=" + netWorth +
                '}';
    }   
    
}
