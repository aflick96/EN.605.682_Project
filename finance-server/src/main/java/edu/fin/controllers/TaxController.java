package edu.fin.controllers;

import edu.fin.services.StateTaxService;
import edu.fin.services.FederalTaxService;
import edu.fin.utils.tax.TaxUtil;
import edu.fin.entities.tax.FederalTax;
import edu.fin.entities.tax.StateTax;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {
	private StateTaxService stateTaxService;
	private FederalTaxService federalTaxService;
	private TaxUtil util;
	
	TaxController() {
		this.stateTaxService = new StateTaxService();
		this.federalTaxService = new FederalTaxService();
		this.util = new TaxUtil();
	}
	
	@GetMapping("/all_state_taxes")
	public StateTax getStateTaxes() throws IOException {
		return stateTaxService.getStateTaxes();
	}
	
	@GetMapping("/state")
	public ResponseEntity<?> getStateTax(
			@RequestParam("state") String stateName,
			@RequestParam(value = "filing_status", required=false) String filingStatus,
			@RequestParam(value = "$select", required=false) String select) {
		try {
			StateTax data = stateTaxService.getStateTaxes();
			StateTax.State state = data.getStates().get(stateName.toLowerCase());
			
			if(state == null) {
				return ResponseEntity.status(404).body("State not found");
			}
			
			Map<String, Object> response = new LinkedHashMap<>();
			
			
			if (filingStatus == null || filingStatus.equalsIgnoreCase("single")) {
				Map<String, Object> f_data = util.filterStateFilingStatus(state.getSingle(), select);
				response.put("single", f_data);
			}
			
			if (filingStatus == null || filingStatus.equalsIgnoreCase("married")) {
				Map<String, Object> f_data = util.filterStateFilingStatus(state.getMarried(), select);
				response.put("married", f_data);
			}
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			return ResponseEntity.status(404).body("Error getting requested data");
		}
	}
	
    @GetMapping("/federal")
    public ResponseEntity<?> getFederalTax(
            @RequestParam(value = "filing_status", required = false) String filingStatus,
            @RequestParam(value = "$select", required = false) String select) {
        try {
            FederalTax federalTaxData = federalTaxService.getFederalTaxes();

            FederalTax.FilingStatus filingStatusData = null;

            if (filingStatus == null || filingStatus.equalsIgnoreCase("single")) {
                filingStatusData = federalTaxData.getSingle();
            } else if (filingStatus.equalsIgnoreCase("married")) {
                filingStatusData = federalTaxData.getMarried();
            }

            if (filingStatusData == null) {
                return ResponseEntity.status(404).body("Filing status not found");
            }

            return ResponseEntity.ok(util.filterFederalFilingStatus(filingStatusData, select));

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading federal tax data");
        }
    }
}
