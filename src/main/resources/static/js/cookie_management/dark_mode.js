/**
 * @file
 * Enables/Disables "dark mode", and keeps track of user choice
 * via cookies.
 */

/**
 * Enables, or disables, "dark mode" of the web page. Uses a cookie
 * to remember user's choice.
 */
function toggleDarkMode() {
    var option = readCookie("darkmode");
    var optionReverse = "true";
    var stylesheet = document.getElementById("sitestyle");

    var styleName = "/css/general_dark";
    if (option === "true") {
        optionReverse = "false";
        styleName = "/css/general_light";
    }

    stylesheet.href = styleName + '.css';
    createCookie("darkmode", optionReverse);
    console.log("OPTION (SAVE): " + optionReverse);
}

/**
 * Conducts the initialization of the web page in terms of either
 * enabling or disabling "dark mode".
 */
function initializeDarkMode() {
    var option = readCookie("darkmode");
    console.log("OPTION (LOAD): " + option);

    var head = document.head;
    var link = document.createElement("link");
    link.type = "text/css";
    link.rel = "stylesheet";
    link.id = "sitestyle";

    var styleName = "/css/general_light";
    if (option === "true") {
        console.log("OPTION (LOAD - PASS): " + option);
        styleName = "/css/general_dark";
    }
    console.log("STYLE: " + styleName);
    link.href = styleName + ".css";
    head.appendChild(link);
}

initializeDarkMode();
