function renderExpenseByCategoryChart() {
  const canvas = document.getElementById("expenseByCategoryChart");
  if (!canvas) return;

  const oldChart = Chart.getChart(canvas);
  if (oldChart) oldChart.destroy();

  window.expenseByCategoryChart = new Chart(canvas, {
    type: "pie",
    data: {
      labels: window.expenseByCategory.labels,
      datasets: [
        {
          data: window.expenseByCategory.values,
          backgroundColor: [
            "#36A2EB",
            "#FF6384",
            "#FFCE56",
            "#4BC0C0",
            "#9966FF",
            "#FF9F40",
          ],
        },
      ],
    },
    options: {
      responsive: true,
      plugins: {
        title: {
          display: true,
          text: "Expense By Category",
        },
      },
    },
  });
}

document.addEventListener("DOMContentLoaded", function () {
  if (window.expenseByCategory) renderExpenseByCategoryChart();
});
