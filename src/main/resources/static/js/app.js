document.addEventListener("DOMContentLoaded", () => {
  const searchBar = document.getElementById("searchBar");

  searchBar.addEventListener("input", () => {
    const query = searchBar.value.toLowerCase();
    const cards = document.querySelectorAll(".employee-card");

    cards.forEach((card) => {
      const name = card.querySelector("h3").innerText.toLowerCase();
      const email = card.querySelector("p:nth-of-type(2)").innerText.toLowerCase();
      if (name.includes(query) || email.includes(query)) {
        card.style.display = "block";
      } else {
        card.style.display = "none";
      }
    });
  });

  document.querySelectorAll(".delete-btn").forEach((btn) =>
    btn.addEventListener("click", (e) => {
      const card = e.target.closest(".employee-card");
      if (confirm("Are you sure you want to delete this employee?")) {
        card.remove();
      }
    })
  );
});
