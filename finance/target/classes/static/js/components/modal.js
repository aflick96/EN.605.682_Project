document.addEventListener('DOMContentLoaded', function() {
    function showModal() {
        const modalId = event.currentTarget.getAttribute('data-modal-target');
        const modal = document.querySelector(`.modal[data-modal-id="${modalId}"]`);
        if (modal) {
            modal.style.display = 'flex';
        }   
    }

    function hideModal() {
        const modal = event.currentTarget.closest('.modal');
        if (modal) {
            modal.style.display = 'none';
        }
    }

    document.querySelectorAll("[data-modal-target]").forEach(button => {
        button.addEventListener('click', showModal);
    });

    document.querySelectorAll(".modal-close").forEach(button => {
        button.addEventListener('click', hideModal);
    });
});