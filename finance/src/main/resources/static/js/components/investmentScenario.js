// investmentScenario.js

// We'll define a function that attaches scenario logic to the newly-loaded snippet
function attachWhatIfScenario(container) {
  // Look for your input, table container, etc., inside 'container'
  const weeklyInput = container.querySelector("#weeklyContributionInput");
  if (!weeklyInput) {
    // This snippet might not be the what-if scenario form at all
    return;
  }

  console.log("attachWhatIfScenario called");

  const scenarioTableContainer = container.querySelector(
    "#scenarioTableContainer"
  );
  const investmentLogIdField = container.querySelector("#investmentLogId");
  if (!investmentLogIdField || !scenarioTableContainer) return;

  const investmentLogId = investmentLogIdField.value;

  weeklyInput.addEventListener("input", function () {
    const value = weeklyInput.value || 0;
    const baseContextPath = container.getAttribute("data-context") || "";

    // Build the fetch URL:
    const fetchUrl = `${baseContextPath}/investment/what-if-investment-table?investmentLogId=${investmentLogId}&weeklyContribution=${value}`;

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
  });
}
