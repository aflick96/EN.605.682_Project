function renderNetWorthChart() {
  const canvas = document.getElementById("netWorthChart");
  if (!canvas) return;

  const oldChart = Chart.getChart(canvas);
  if (oldChart) oldChart.destroy();

  window.netWorthChart = new Chart(canvas, {
    type: "line",
    data: {
      labels: window.netWorthBreakdown.labels,
      datasets: [
        {
          label: "Net Worth",
          data: window.netWorthBreakdown.netWorth,
          borderColor: "green",
          fill: false,
          tension: 0.3,
        },
        {
          label: "Income",
          data: window.netWorthBreakdown.income,
          borderColor: "blue",
          fill: false,
          tension: 0.3,
        },
        {
          label: "Expenses",
          data: window.netWorthBreakdown.expenses,
          borderColor: "red",
          fill: false,
          tension: 0.3,
        },
        {
          label: "Investments",
          data: window.netWorthBreakdown.investments,
          borderColor: "purple",
          fill: false,
          tension: 0.3,
        },
        {
          label: "Loans",
          data: window.netWorthBreakdown.loans,
          borderColor: "orange",
          fill: false,
          tension: 0.3,
        },
      ],
    },
    options: {
      plugins: {
        title: {
          display: true,
          text: "Net Worth Breakdown Over Time",
        },
      },
      scales: {
        y: {
          beginAtZero: false,
          title: {
            display: true,
            text: "$",
          },
        },
        x: {
          title: {
            display: true,
            text: "Month",
          },
        },
      },
    },
  });
}

document.addEventListener("DOMContentLoaded", () => {
  if (window.netWorthBreakdown) renderNetWorthChart();
});
