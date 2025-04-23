document.addEventListener("DOMContentLoaded", function () {
  const ctx = document.getElementById("netWorthChart").getContext("2d");

  const chart = new Chart(ctx, {
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

  //   if (!window.netWorthData) {
  //     console.error("No net worth data found.");
  //     return;
  //   }

  //   const ctx = document.getElementById("netWorthChart").getContext("2d");

  //   new Chart(ctx, {
  //     type: "line",
  //     data: {
  //       labels: window.netWorthData.labels,
  //       datasets: [
  //         {
  //           label: "Net Worth Over Time",
  //           data: window.netWorthData.values,
  //           borderColor: "green",
  //           backgroundColor: "rgba(0, 128, 0, 0.1)",
  //           fill: true,
  //           tension: 0.3,
  //           pointRadius: 3,
  //           pointHoverRadius: 6,
  //         },
  //       ],
  //     },
  //     options: {
  //       responsive: true,
  //       plugins: {
  //         title: {
  //           display: true,
  //           text: "Net Worth Over Time",
  //           font: {
  //             size: 20,
  //           },
  //         },
  //         tooltip: {
  //           callbacks: {
  //             label: function (context) {
  //               return "$" + context.formattedValue;
  //             },
  //           },
  //         },
  //       },
  //       scales: {
  //         y: {
  //           title: {
  //             display: true,
  //             text: "Net Worth ($)",
  //           },
  //           beginAtZero: false,
  //         },
  //         x: {
  //           type: "time",
  //           time: {
  //             unit: "month",
  //             displayFormats: {
  //               month: "MMM yyyy", // Jan 2024
  //             },
  //           },
  //           title: {
  //             display: true,
  //             text: "Month",
  //           },
  //         },
  //       },
  //     },
  //   });
});
