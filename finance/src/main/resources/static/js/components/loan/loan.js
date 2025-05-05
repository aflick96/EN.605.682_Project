// Show single or recurring payment form based on user selection
function togglePaymentForm(type) {
  const single = document.getElementById("singlePaymentForm");
  const recurring = document.getElementById("recurringPaymentForm");

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
  const paymentDay = document.getElementById("paymentDay");
  const specificDayContainer = document.getElementById("specificDayContainer");

  if (!paymentDay || !specificDayContainer) return;

  if (paymentDay.value === "SPECIFIC") {
    specificDayContainer.style.display = "flex";
  } else {
    specificDayContainer.style.display = "none";
    document.getElementById("specificDay").value = "";
  }
}

// remove an existing payment
function deletePayment(index) {
  const row = document.getElementById(`payment-${index}`);
  const amountInput = document.getElementById(`amount-${index}`);

  if (!row || !amountInput) return;

  amountInput.value = "0";
  row.style.display = "none";
}

// Set date constraints for loan payments to the start and end dates of the loan item
function setupLoanPaymentDateConstraints() {
  // Single payment form
  const singleForm = document.getElementById("loanPaymentCreateForm");
  if (singleForm) {
    const startDate = singleForm.dataset.start;
    const endDate = singleForm.dataset.end;

    const paymentDateInput = document.getElementById("paymentDate");
    if (paymentDateInput && startDate && endDate) {
      paymentDateInput.setAttribute("min", startDate);
      paymentDateInput.setAttribute("max", endDate);
    }
  }

  // Recurring payment form
  const recurringForm = document.getElementById("loanRecurringPaymentForm");
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
      recurringEndInput.setAttribute("min", minMonth);
      recurringEndInput.setAttribute("max", maxMonth);
    }
  }
}
