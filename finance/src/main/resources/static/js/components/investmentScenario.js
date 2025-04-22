function attachWhatIfScenario(container) {
  const weeklyInput = container.querySelector("#weeklyContributionInput");
  const annualReturnInput = container.querySelector("#annualReturnInput");
  const scenarioTableContainer = container.querySelector(
    "#scenarioTableContainer"
  );
  const investmentLogIdField = container.querySelector("#investmentLogId");

  if (
    !weeklyInput ||
    !annualReturnInput ||
    !scenarioTableContainer ||
    !investmentLogIdField
  ) {
    return;
  }

  const investmentLogId = investmentLogIdField.value;
  const baseContextPath = container.getAttribute("data-context") || "";

  function updateScenarioTable() {
    const weeklyContributionValue = weeklyInput.value || 0;
    const annualReturnValue = annualReturnInput.value || 0;

    const fetchUrl = `${baseContextPath}/investment/what-if-investment-table?investmentLogId=${investmentLogId}&weeklyContribution=${weeklyContributionValue}&annualReturn=${annualReturnValue}`;

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

  weeklyInput.addEventListener("input", updateScenarioTable);
  annualReturnInput.addEventListener("input", updateScenarioTable);
}
