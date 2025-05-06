package edu.fin.services;

import edu.fin.dtos.income.IncomeByPayFrequencyDetail;
import edu.fin.entities.income.IncomeLog;
import edu.fin.entities.income.enums.PayFrequency;
import edu.fin.entities.user.User;
import edu.fin.repositories.income.IncomeLogRepository;
import edu.fin.repositories.user.UserRepository;
import edu.fin.utils.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IncomeByPayFrequencyService {
    
    private final FederalTaxService fed_tax_service;
    private final StateTaxService state_tax_service;
    private final UserService user_service;

    @Autowired
    private IncomeLogRepository incomeLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Util util;

    public IncomeByPayFrequencyService(FederalTaxService fed_tax_service, StateTaxService state_tax_service, UserService user_service) {
        this.fed_tax_service = fed_tax_service;
        this.state_tax_service = state_tax_service;
        this.user_service = user_service;
    }

    public List<IncomeByPayFrequencyDetail> calculatePayDetail(IncomeLog incomeLog) {
        List<IncomeByPayFrequencyDetail> payDetails = new ArrayList<>();
        LocalDate payDate = incomeLog.getStartDate();
        LocalDate endDate = incomeLog.getEndDate();
        double grossIncome = incomeLog.getAmount();
        int payPeriods = incomeLog.getAnnualPayFrequency().getPeriods();
        double perPeriodGrossIncome = grossIncome / payPeriods;

        User user = user_service.getUserById(incomeLog.getUser().getId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String filingStatus = user.getFilingStatus().toLowerCase();
        String state = user.getState().toLowerCase();
        int dependents = user.getDependents();

        double federalTax = fed_tax_service.calculateFederalTax(grossIncome, filingStatus);
        double stateTax = state_tax_service.calculateStateTax(grossIncome, state, filingStatus, dependents);
        double perPeriodFederalTax = federalTax / payPeriods;
        double perPeriodStateTax = stateTax / payPeriods;

        List<LocalDate> payDates = generatePayDates(payDate, endDate, incomeLog.getAnnualPayFrequency());

        for (LocalDate date : payDates) {
            double additionalIncome = incomeLog.getAdditionalIncomes().stream().mapToDouble(ai -> ai.getAmount()).sum();
            double pretaxDeductions = incomeLog.getPretaxDeductions().stream().mapToDouble(pd -> pd.getAmount()).sum();
            double taxableIncome = perPeriodGrossIncome + additionalIncome - pretaxDeductions;
            double posttaxDeductions = incomeLog.getPosttaxDeductions().stream().mapToDouble(pt -> pt.getAmount()).sum();
            double netIncome = taxableIncome - perPeriodFederalTax - perPeriodStateTax - posttaxDeductions;

            payDetails.add(new IncomeByPayFrequencyDetail(date, perPeriodGrossIncome, additionalIncome, pretaxDeductions, taxableIncome, perPeriodFederalTax, perPeriodStateTax, posttaxDeductions, netIncome));
        }

        return payDetails;
    }

    public Map<LocalDate, Double> getIncomeByDate(Long uid) {
        User user = userRepository.findById(uid).orElse(null);
        if (user == null) return Collections.emptyMap();

        Map<LocalDate, Double> incomeMap = new TreeMap<>();

        List<IncomeLog> incomeLogs = incomeLogRepository.findByUserId(uid);
        incomeLogs.forEach(log -> {
            List<IncomeByPayFrequencyDetail> details = calculatePayDetail(log);
            details.forEach(detail -> {
                LocalDate date = util.getFirstOfMonth(detail.getPayDate());
                incomeMap.put(date, incomeMap.getOrDefault(date, 0.0) + detail.getNetIncome());
            });
        });

        return incomeMap;
    }

    //Generate pay dates based on pay frequency
    private List<LocalDate> generatePayDates(LocalDate start, LocalDate end, PayFrequency frequency) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;
        
        while (!current.isAfter(end)) {
            dates.add(current);
            switch (frequency) {
                case WEEKLY: current = current.plusWeeks(1); break;
                case BIWEEKLY: current = current.plusWeeks(2); break;
                case SEMIMONTHLY: current = current.plusDays(15); break;
                case MONTHLY: current = current.plusMonths(1); break;
            }
        }

        return dates;
    }
}
