document.addEventListener('DOMContentLoaded', function() {
    function showModal() {
        const modalId = event.currentTarget.getAttribute('data-modal-target');
        const modal = document.querySelector(`.modal[data-modal-id="${modalId}"]`);
        if (!modal) return;
        
        const fetchUrl = event.currentTarget.getAttribute("data-fetch-url");
        if (fetchUrl) {
            const modalContent = modal.querySelector(".modal-content-body");
            modalContent.innerHTML = "<p>Loading...</p>";

            fetch(fetchUrl)
                .then(response => response.text())
                .then(html => {
                    modalContent.innerHTML = html;
                })
                .catch(error => {
                    console.error("Error loading modal content: ", error);
                    modalContent.innerHTML = "<p>Failed to load content</p>";
                });
        }
        
        modal.style.display = 'flex';   
    }

    function hideModal() {
        const modal = event.currentTarget.closest('.modal');
        if (modal) {
            modal.style.display = 'none';

            const modalContent = modal.querySelector(".modal-content-body");
            if (modalContent) {
                modalContent.innerHTML = "";
            }
        }
    }

    document.querySelectorAll("[data-modal-target]").forEach(button => {
        button.addEventListener('click', showModal);
    });

    document.querySelectorAll(".modal-close").forEach(button => {
        button.addEventListener('click', hideModal);
    });

    window.addEventListener("click", function(event) {
        const modal = this.document.querySelector(".modal[data-modal-id]");
        if (modal && event.target === modal) {
            modal.style.display = 'none';
            const modalContent = modal.querySelector(".modal-content-body");
            if (modalContent) {
                modalContent.innerHTML = "";
            }
        }
    });
});