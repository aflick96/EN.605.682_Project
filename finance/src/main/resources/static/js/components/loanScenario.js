function attachWhatIfLoan(container) {
  const monthlyPaymentInput = container.querySelector("#monthlyPaymentInput");
  const interestRateInput = container.querySelector("#interestRateInput");
  const loanTermInput = container.querySelector("#loanTermInput");
  const scenarioTableContainer = container.querySelector(
    "#scenarioTableContainer"
  );
  const loanItemIdField = container.querySelector("#loanItemId");

  if (
    !monthlyPaymentInput ||
    !interestRateInput ||
    !loanTermInput ||
    !scenarioTableContainer ||
    !loanItemIdField
  ) {
    return;
  }

  const loanItemId = loanItemIdField.value;
  const baseContextPath = container.getAttribute("data-context") || "";

  function updateScenarioTable() {
    const monthlyPaymentValue = monthlyPaymentInput.value || 0;
    const interestRateValue = interestRateInput.value || 0;
    const loanTermValue = loanTermInput.value || 0;

    const fetchUrl = `${baseContextPath}/loans/what-if-loan-table?loanItemId=${loanItemId}&monthlyPayment=${monthlyPaymentValue}&interestRate=${interestRateValue}&loanTerm=${loanTermValue}`;

    fetch(fetchUrl)
      .then((response) => response.text())
      .then((html) => {
        const parser = new DOMParser();
        const doc = parser.parseFromString(html, "text/html");
        const newTableContainer = doc.querySelector("#scenarioTableContainer");
        if (newTableContainer) {
          scenarioTableContainer.innerHTML = newTableContainer.innerHTML;
        }
      })
      .catch((err) => console.error("Failed to update scenario table:", err));
  }

  monthlyPaymentInput.addEventListener("input", updateScenarioTable);
  interestRateInput.addEventListener("input", updateScenarioTable);
  loanTermInput.addEventListener("input", updateScenarioTable);
}
