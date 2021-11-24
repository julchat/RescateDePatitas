const navtoggle = document.querySelector(".toggle")
const navmenu = document.querySelector(".nav-menu")

navtoggle.addEventListener("click", () => {
    navmenu.classList.toggle("nav-menu_visible")
});