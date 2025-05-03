function togglePaymentForm(type) {
  const single = document.getElementById("singlePaymentForm");
  const recurring = document.getElementById("recurringPaymentForm");

  if (!single || !recurring) return;

  if (type === "single") {
    single.style.display = "flex";
    recurring.style.display = "none";
  } else {
    single.style.display = "none";
    recurring.style.display = "flex";
  }
}

function hideSpecificDay() {
  const paymentDay = document.getElementById("contributionDay");
  const specificDayContainer = document.getElementById("specificDayContainer");

  if (!paymentDay || !specificDayContainer) return;

  if (paymentDay.value === "SPECIFIC") {
    specificDayContainer.style.display = "flex";
  } else {
    specificDayContainer.style.display = "none";
    document.getElementById("specificDay").value = "";
  }
}
