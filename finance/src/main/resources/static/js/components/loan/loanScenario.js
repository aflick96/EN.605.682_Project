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

          const rows = newTableContainer.querySelectorAll("tbody tr");
          const labels = [];
          const endBalances = [];
          const totalInterest = [];
          const totalPaid = [];

          rows.forEach((row) => {
            const cells = row.querySelectorAll("td");
            labels.push(cells[0]?.textContent);
            totalPaid.push(
              parseFloat(cells[8]?.textContent.replace(/[^0-9.]/g, "") || "0")
            );
            totalInterest.push(
              parseFloat(cells[7]?.textContent.replace(/[^0-9.]/g, "") || "0")
            );
            endBalances.push(
              parseFloat(cells[10]?.textContent.replace(/[^0-9.]/g, "") || "0")
            );
          });

          const totalPrincipal = parseFloat(
            newTableContainer
              .querySelector("tbody tr:last-child td:nth-child(7)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );
          const totalInterestValue = parseFloat(
            newTableContainer
              .querySelector("tbody tr:last-child td:nth-child(8)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );

          const formatter = new Intl.NumberFormat("en-US", {
            style: "currency",
            currency: "USD",
            minimumFractionDigits: 2,
            maximumFractionDigits: 2,
          });

          const dateFormatter = new Intl.DateTimeFormat("en-US", {
            year: "numeric",
            month: "short",
            timeZone: "UTC",
          });

          const lastRow = rows[rows.length - 1];
          const lastPrincipal = parseFloat(
            lastRow
              ?.querySelector("td:nth-child(7)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );
          const lastInterest = parseFloat(
            lastRow
              ?.querySelector("td:nth-child(8)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );
          const lastPaid = parseFloat(
            lastRow
              ?.querySelector("td:nth-child(9)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );
          const lastEndBalance = parseFloat(
            lastRow
              ?.querySelector("td:nth-child(11)")
              ?.textContent.replace(/[^0-9.]/g, "") || "0"
          );

          const lastPaymentDate = lastRow
            ?.querySelector("td:nth-child(1)")
            ?.textContent.trim();

          console.log(lastPaymentDate);

          document.getElementById("summaryTotalPrincipal").textContent =
            formatter.format(lastPrincipal);
          document.getElementById("summaryTotalInterest").textContent =
            formatter.format(lastInterest);
          document.getElementById("summaryTotalPaid").textContent =
            formatter.format(lastPaid);
          document.getElementById("summaryEndBalance").textContent =
            formatter.format(lastEndBalance);
          document.getElementById("summaryLastPaymentDate").textContent =
            dateFormatter.format(new Date(lastPaymentDate));

          window.loanBreakdown = {
            totalPrincipal,
            totalInterestValue,
          };

          const updatedLoanTerm =
            newTableContainer.getAttribute("data-loan-term");
          if (updatedLoanTerm !== null) {
            loanTermInput.value = updatedLoanTerm;
          }

          renderPieChart();
          renderLoanLineChart(labels, endBalances, totalInterest, totalPaid);
        }
      })
      .catch((err) => console.error("Failed to update scenario table:", err));
  }

  function renderPieChart() {
    const ctx = document.getElementById("loanBreakdownPie").getContext("2d");
    const data = window.loanBreakdown;
    if (!ctx || !data || !data.totalPrincipal || !data.totalInterestValue)
      return;

    if (window.loanPieChart) {
      window.loanPieChart.destroy();
    }

    window.loanPieChart = new Chart(ctx, {
      type: "pie",
      data: {
        labels: ["Total Principal", "Total Interest"],
        datasets: [
          {
            data: [data.totalPrincipal, data.totalInterestValue],
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

  function renderLoanLineChart(labels, endBalances, totalInterest, totalPaid) {
    const ctx = document.getElementById("loanProgressChart").getContext("2d");

    if (window.loanLineChart) {
      window.loanLineChart.destroy(); // avoid duplicate charts
    }

    window.loanLineChart = new Chart(ctx, {
      type: "line",
      data: {
        labels: labels,
        datasets: [
          {
            label: "End Balance",
            data: endBalances,
            borderColor: "green",
            fill: false,
            tension: 0.3,
          },
          {
            label: "Total Interest",
            data: totalInterest,
            borderColor: "red",
            fill: false,
            tension: 0.3,
          },
          {
            label: "Total Paid",
            data: totalPaid,
            borderColor: "blue",
            fill: false,
            tension: 0.3,
          },
        ],
      },
      options: {
        plugins: {
          title: {
            display: true,
            text: "Loan Progress Over Time",
          },
        },
        scales: {
          x: {
            title: {
              display: true,
              text: "Month",
            },
          },
          y: {
            title: {
              display: true,
              text: "Amount ($)",
            },
            beginAtZero: true,
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
