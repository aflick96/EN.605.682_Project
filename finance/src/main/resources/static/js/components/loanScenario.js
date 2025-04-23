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

          const totalPrincipal = parseFloat(
            newTableContainer
              .querySelector("tbody tr:last-child td:nth-child(6)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );
          const totalInterest = parseFloat(
            newTableContainer
              .querySelector("tbody tr:last-child td:nth-child(7)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );

          window.loanBreakdown = {
            totalPrincipal,
            totalInterest,
          };

          renderPieChart();
        }
      })
      .catch((err) => console.error("Failed to update scenario table:", err));
  }

  function renderPieChart() {
    const ctx = document.getElementById("loanBreakdownPie").getContext("2d");
    const data = window.loanBreakdown;
    if (!ctx || !data || !data.totalPrincipal || !data.totalInterest) return;

    if (window.loanPieChart) {
      window.loanPieChart.destroy();
    }

    window.loanPieChart = new Chart(ctx, {
      type: "pie",
      data: {
        labels: ["Total Principal", "Total Interest"],
        datasets: [
          {
            data: [data.totalPrincipal, data.totalInterest],
            backgroundColor: ["#4CAF50", "#FF6384"],
            hoverOffset: 6,
          },
        ],
      },
      options: {
        plugins: {
          title: {
            display: true,
            text: "Loan Payment Breakdown",
          },
        },
      },
    });
  }

  updateScenarioTable();

  monthlyPaymentInput.addEventListener("input", updateScenarioTable);
  interestRateInput.addEventListener("input", updateScenarioTable);
  loanTermInput.addEventListener("input", updateScenarioTable);
}
