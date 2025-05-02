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
        .then((response) => {
          if (response.status === 204) {
            const clientMessage = response.headers.get("X-Client-Message");
            if (clientMessage) {
              showClientMessageOverlay(clientMessage);
            }
            return Promise.reject();
          }
          return response.text();
        })
        .then((html) => {
          const modalContentBody = modal.querySelector(".modal-content-body");
          modalContent.innerHTML = html;
          modal.style.display = "flex";

          if (modalContentBody.querySelector("#investmentLogId")) {
            attachWhatIfInvestment(modalContentBody);
          } else if (modalContentBody.querySelector("#loanItemId")) {
            attachWhatIfLoan(modalContentBody);
          }
        })
        .catch((error) => {
          console.error("Error loading modal content: ", error);
        });
    }
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

  function showClientMessageOverlay(message) {
    const existingOverlay = document.querySelector(".client-message-overlay");
    if (existingOverlay) existingOverlay.remove();
    const overlay = document.createElement("div");
    overlay.className = "client-message-overlay";
    overlay.innerHTML = `<div class="client-message">${message}</div>`;
    document.body.appendChild(overlay);

    setTimeout(() => {
      overlay.remove();
    }, 3000);
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
