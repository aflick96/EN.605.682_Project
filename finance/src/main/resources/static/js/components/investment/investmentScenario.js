function attachWhatIfInvestment(container) {
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

          const rows = newTableContainer.querySelectorAll("tbody tr");
          const labels = [];
          const endBalances = [];
          const totalContributions = [];
          const totalGrowth = [];

          rows.forEach((row) => {
            const cells = row.querySelectorAll("td");
            labels.push(cells[0]?.textContent);
            totalContributions.push(
              parseFloat(cells[3]?.textContent.replace(/[^0-9.]/g, "") || "0")
            );
            totalGrowth.push(
              parseFloat(cells[5]?.textContent.replace(/[^0-9.]/g, "") || "0")
            );
            endBalances.push(
              parseFloat(cells[6]?.textContent.replace(/[^0-9.]/g, "") || "0")
            );
          });

          renderInvestmentLineChart(
            labels,
            endBalances,
            totalContributions,
            totalGrowth
          );
        }
      })
      .catch((err) => console.error("Failed to update scenario table:", err));
  }

  function renderInvestmentLineChart(
    labels,
    endBalances,
    totalContributions,
    totalGrowth
  ) {
    const ctx = document
      .getElementById("investmentProgressChart")
      .getContext("2d");

    if (window.investmentLineChart) {
      window.investmentLineChart.destroy();
    }

    window.investmentLineChart = new Chart(ctx, {
      type: "line",
      data: {
        labels: labels,
        datasets: [
          {
            label: "End Balance",
            data: endBalances,
            borderColor: "rgba(75, 192, 192, 1)",
            backgroundColor: "rgba(75, 192, 192, 0.2)",
            fill: true,
          },
          {
            label: "Total Contributions",
            data: totalContributions,
            borderColor: "rgba(153, 102, 255, 1)",
            backgroundColor: "rgba(153, 102, 255, 0.2)",
            fill: true,
          },
          {
            label: "Total Growth",
            data: totalGrowth,
            borderColor: "rgba(255, 159, 64, 1)",
            backgroundColor: "rgba(255, 159, 64, 0.2)",
            fill: true,
          },
        ],
      },
      options: {
        responsive: true,
        scales: {
          x: {
            title: {
              display: true,
              text: "Time Periods",
            },
          },
          y: {
            title: {
              display: true,
              text: "Amount ($)",
            },
          },
        },
      },
    });
  }

  updateScenarioTable();

  weeklyInput.addEventListener("input", updateScenarioTable);
  annualReturnInput.addEventListener("input", updateScenarioTable);
}
