export function toggleDarkMode() {
    console.log("Test");
    if (document.documentElement.getAttribute("data-bs-theme") === "light") {
        setTheme("dark");
    } else {
        setTheme("light");
    }
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
    document.documentElement.setAttribute("data-bs-theme", theme);
    document.body.className = theme + "-mode";
}

if (localStorage.getItem("theme") === null) {
    setTheme("light");
} else {
    setTheme(localStorage.getItem("theme"));
}
