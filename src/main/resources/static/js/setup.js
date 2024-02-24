"use strict";

const lightTheme = document.getElementById("light-theme");
const darkTheme = document.getElementById("dark-theme");
const submit = document.getElementById("submit");
const lightThemeSquare = document.getElementById("light-theme-square");
const darkThemeSquare = document.getElementById("dark-theme-square");
const serverName = document.getElementById("server-name");
const port = document.getElementById("port");

let setupXHR = new XMLHttpRequest();

/**
 * Initializes dom objects
 */
function setupInitialization() {
    setAlertStyle("light");

    lightTheme.addEventListener("click", changeTheme);
    darkTheme.addEventListener("click", changeTheme);
    submit.addEventListener("click", sendSetupRequest);

    port.addEventListener("input", () => {
        port.value = port.value.replace(/\D/g, '');

        if (port.value.length > 5) {
            port.value = port.value.slice(0, 5);
        } else if (port.value > 65535) {
            port.value = 65535;
        }
    });
}

/**
 * Changes theme
 */
function changeTheme(element) {
    if (String(element.id) === "light-theme") {
        html.setAttribute("theme", "light");
        setAlertStyle("light");

        lightThemeSquare.style.animation = "fade-in-square 0.5s forwards";
        darkThemeSquare.style.animation = "fade-out-square 0.5s forwards";

        background.setOptions
        ({
            highlightColor: 0xCAC7E8,
            midtoneColor: 0xBBB7ED,
            lowlightColor: 0xE4E3EF,
            baseColor: 0xE4E3EF
        });
    } else {
        html.setAttribute("theme", "dark");
        setAlertStyle("dark");

        darkThemeSquare.style.visibility = "visible";

        lightThemeSquare.style.animation = "fade-out-square 0.5s forwards";
        darkThemeSquare.style.animation = "fade-in-square 0.5s forwards";

        background.setOptions
        ({
            highlightColor: 0x797979,
            midtoneColor: 0xFFFFFF,
            lowlightColor: 0xBCBCBC,
            baseColor: 0xBCBCBC
        });
    }
}

/**
 * Changes alert style
 *
 * @param styleName
 */
function setAlertStyle(styleName) {
    const links = document.querySelectorAll('link[title="light"], link[title="dark"]');

    links.forEach(link => {
        link.disabled = (link.getAttribute("title") !== styleName);
    });
}

/**
 * Sends settings request
 */
function sendSetupRequest() {
    setupXHR.open("POST", "/api/setup");
    setupXHR.setRequestHeader("Content-Type", "application/json");

    setupXHR.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                submit.value = "LOADING";
                window.location = "http://" + window.location.hostname + ":" + port.value;
            } else {
                dhtmlx.message({
                    text: "Fill the form correctly",
                    type: ("")
                });
            }
        }
    }

    if (port.value !== 8080 && port.value !== 80) {
        setupXHR.send(JSON.stringify({
            "serverName": serverName.value,
            "theme": html.getAttribute("theme"),
            "port": port.value
        }));
    } else {
        dhtmlx.message({
            text: "Choose other port",
            type: ("")
        });
    }
}

setupInitialization();