document.addEventListener("DOMContentLoaded", function () {
  function showModal() {
    const modalId = event.currentTarget.getAttribute("data-modal-target");
    const modal = document.querySelector(`.modal[data-modal-id="${modalId}"]`);
    if (!modal) return;

    const modalContent = modal.querySelector(".modal-content");
    const modalSize =
      event.currentTarget.getAttribute("data-modal-size") || "medium";
    modalContent.className = `modal-content ${modalSize}`;

    const fetchUrl = event.currentTarget.getAttribute("data-fetch-url");
    if (fetchUrl) {
      const modalContent = modal.querySelector(".modal-content-body");
      modalContent.innerHTML = "<p>Loading...</p>";

      fetch(fetchUrl)
        .then((response) => response.text())
        .then((html) => {
          const modalContentBody = modal.querySelector(".modal-content-body");
          modalContent.innerHTML = html;

          if (modalContentBody.querySelector("#investmentLogId")) {
            attachWhatIfInvestment(modalContentBody);
          } else if (modalContentBody.querySelector("#loanItemId")) {
            attachWhatIfLoan(modalContentBody);
          }

          // if (typeof attachWhatIfScenario === "function") {
          //   attachWhatIfScenario(modalContentBody);
          // }
        })
        .catch((error) => {
          console.error("Error loading modal content: ", error);
          modalContent.innerHTML = "<p>Failed to load content</p>";
        });
    }

    modal.style.display = "flex";
  }

  function hideModal() {
    const modal = event.currentTarget.closest(".modal");
    if (modal) {
      modal.style.display = "none";

      const modalContent = modal.querySelector(".modal-content");
      const modalContentBody = modal.querySelector(".modal-content-body");
      modalContent.className = "modal-content";

      if (modalContentBody) {
        modalContentBody.innerHTML = "";
      }
    }
  }

  document.querySelectorAll("[data-modal-target]").forEach((button) => {
    button.addEventListener("click", showModal);
  });

  document.querySelectorAll(".modal-close").forEach((button) => {
    button.addEventListener("click", hideModal);
  });

  window.addEventListener("click", function (event) {
    document.querySelectorAll(".modal[data-modal-id]").forEach((modal) => {
      if (event.target === modal) {
        modal.style.display = "none";
        const modalContent = modal.querySelector(".modal-content");
        modalContent.className = "modal-content";

        const modalBody = modal.querySelector(".modal-content-body");
        if (modalBody) {
          modalBody.innerHTML = "";
        }
      }
    });
  });
});
