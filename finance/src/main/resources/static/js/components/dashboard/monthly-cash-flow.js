function renderMonthlyCashFlowChart() {
  const canvas = document.getElementById("monthlyCashFlowChart");

  const oldChart = Chart.getChart(canvas);
  if (oldChart) oldChart.destroy();

  const { labels, income, expenses, netCashFlow } = window.monthlyCashFlowData;

  window.monthlyCashFlowChart = new Chart(canvas, {
    type: "bar",
    data: {
      labels: labels,
      datasets: [
        {
          label: "Income",
          data: income,
          backgroundColor: "rgba(75, 192, 192, 0.7)",
        },
        {
          label: "Expenses",
          data: expenses,
          backgroundColor: "rgba(255, 99, 132, 0.7)",
        },
        {
          label: "Net Cash Flow",
          data: netCashFlow,
          backgroundColor: "rgba(153, 102, 255, 0.7)",
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        title: {
          display: true,
          text: "Monthly Income vs Expenses",
          font: {
            size: 20,
          },
        },
        tooltip: {
          callbacks: {
            label: function (context) {
              const value = context.parsed.y || 0;
              return `$${value.toLocaleString(undefined, {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
              })}`;
            },
          },
        },
      },
      scales: {
        x: {
          stacked: false,
          ticks: {
            maxRotation: 45,
            minRotation: 45,
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

document.addEventListener("DOMContentLoaded", () => {
  if (window.monthlyCashFlowData) renderMonthlyCashFlowChart();
});
