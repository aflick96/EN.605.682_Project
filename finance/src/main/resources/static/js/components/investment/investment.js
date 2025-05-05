// Show single or recurring contribution form based on user selection
function toggleContributionForm(type) {
  const single = document.getElementById("singleContributionForm");
  const recurring = document.getElementById("recurringContributionForm");

  if (!single || !recurring) return;

  if (type === "single") {
    single.style.display = "flex";
    recurring.style.display = "none";
  } else {
    single.style.display = "none";
    recurring.style.display = "flex";
  }
}

// hide the specific day input if the selected payment day is not "SPECIFIC"
function hideSpecificDay() {
  const paymentDay = document.getElementById("contributionDay");
  const specificDayContainer = document.getElementById("specificDayContainer");

  if (!paymentDay || !specificDayContainer) return;

  if (paymentDay.value === "SPECIFIC") {
    specificDayContainer.style.display = "flex";
  } else {
    specificDayContainer.style.display = "none";
    document.getElementById("specificDay").value = "";
  }
}

// remove an existing contribution
function deleteContribution(index) {
  const row = document.getElementById(`contribution-${index}`);
  const amountInput = document.getElementById(`amount-${index}`);

  if (!row || !amountInput) return;

  amountInput.value = "0";
  row.style.display = "none";
}

// Set date contstraints for investment contributions to the start and end dates of the investment item
function setupInvestmentDateConstraints() {
  // Single contribution form
  const singleForm = document.getElementById("investmentContributionForm");
  if (singleForm) {
    const startDate = singleForm.dataset.start;
    const endDate = singleForm.dataset.end;

    const contributionDateInput = document.getElementById("contribution-date");
    if (contributionDateInput && startDate && endDate) {
      contributionDateInput.setAttribute("min", startDate);
      contributionDateInput.setAttribute("max", endDate);
    }
  }
  // Recurring contribution form
  const recurringForm = document.getElementById(
    "investmentRecurringContributionForm"
  );
  if (recurringForm) {
    const startDate = recurringForm.dataset.start;
    const endDate = recurringForm.dataset.end;
    const recurringStartInput = document.getElementById("startDate");
    const recurringEndInput = document.getElementById("endDate");
    if (recurringStartInput && recurringEndInput && startDate && endDate) {
      const minMonth = startDate.slice(0, 7);
      const maxMonth = endDate.slice(0, 7);
      recurringStartInput.setAttribute("min", minMonth);
      recurringStartInput.setAttribute("max", maxMonth);
      recurringEndInput.setAttribute("min", startDate);
      recurringEndInput.setAttribute("max", endDate);
    }
  }
}
