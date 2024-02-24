const logoPage = document.getElementById("logo-page");
const contactsPage = document.getElementById("contacts-page");
const currentClockSpeed = document.getElementById("currentClockSpeed");
const currentProcCount = document.getElementById("currentProcCount");
const currentTotalStorage = document.getElementById("currentTotalStorage");
const currentDiskCount = document.getElementById("currentDiskCount");
const firstControl = document.getElementById("first-control");
const secondControl = document.getElementById("second-control");
const cloudLeft = document.getElementById("cloud-left");
const cloudRight = document.getElementById("cloud-right");
const days = document.getElementById("uptime-days");
const hours = document.getElementById("uptime-hours");
const minutes = document.getElementById("uptime-minutes");
const seconds = document.getElementById("uptime-seconds");

const html = document.getElementsByTagName("html")[0];

let usageXHR = new XMLHttpRequest();
let infoXHR = new XMLHttpRequest();

let currentPage = 1;

/**
 * Initializes uptime, labels and chart values
 */
function indexInitialization() {
    showCards();

    sendUsageRequest();
    setInterval(sendInfoRequest, 1000);

    firstControl.addEventListener("click", (event) => {
        changePage(event.target)
    });

    secondControl.addEventListener("click", (event) => {
        changePage(event.target)
    });
}

/**
 * Changes cards opacity with a random sequence
 */
function showCards() {
    contactsPage.style.visibility = "hidden";

    const cards = document.getElementsByClassName("card");
    const versionLabel = document.getElementById("project-version");
    const randomSequenceArray = getRandomSequenceArray();

    for (let i = 0; i < cards.length; i++) {
        setTimeout(() => {
            cards[randomSequenceArray[i]].style.opacity = "1";

            if (randomSequenceArray[i] === 4) {
                versionLabel.style.opacity = "1";
            }
        }, 80 * i);
    }
}


/**
 * Generates random sequence
 */
function getRandomSequenceArray() {
    const buffer = [];
    const maxNumber = 5;

    while (buffer.length < maxNumber) {
        const randomNumber = Math.floor(Math.random() * maxNumber);

        if (!buffer.includes(randomNumber)) {
            buffer.push(randomNumber);
        }
    }

    return buffer;
}

/**
 * Sending ajax request to receive usage info
 */
function sendUsageRequest() {
    usageXHR.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const response = JSON.parse(this.response);

            labelsTick(response);
            chartTick(response);

            sendUsageRequest();
        }
    };

    usageXHR.open("GET", "/api/usage");
    usageXHR.send();
}


/**
 * Sending ajax request to receive info about server
 */
function sendInfoRequest() {
    infoXHR.onreadystatechange = function () {
        if ((this.readyState === 4) && (this.status === 200)) {
            let response = JSON.parse(this.response);

            currentClockSpeed.textContent = response.processor.clockSpeed;
            currentProcCount.textContent = response.machine.procCount;
            currentTotalStorage.textContent = response.storage.total;
            currentDiskCount.textContent = response.storage.diskCount;

            days.textContent = response.uptime.days;
            hours.textContent = response.uptime.hours;
            minutes.textContent = response.uptime.minutes;
            seconds.textContent = response.uptime.seconds;
        }
    }

    infoXHR.open("GET", "/api/info");
    infoXHR.send();
}

/**
 * Changes page
 *
 * @param element
 */
function changePage(element) {
    const controlId = String(element.id);

    if (controlId === "first-control" && currentPage > 1) {
        currentPage -= 1;
    } else if (controlId === "second-control" && currentPage < 2) {
        currentPage += 1;
    }

    setCloudAnimation(currentPage);
    setPageVisibility(currentPage);
    setControlOpacity(currentPage);
}

/**
 * Changes page visibility
 *
 * @param newPage
 */
function setPageVisibility(newPage) {
    switch (newPage) {
        case 1: {
            logoPage.style.visibility = "";
            contactsPage.style.visibility = "hidden";
            break;
        }
        case 2: {
            logoPage.style.visibility = "hidden";
            contactsPage.style.visibility = "";
            break;
        }
    }
}

/**
 * Animates clouds
 *
 * @param newSquareScale
 */
function setCloudAnimation(newSquareScale) {
    switch (newSquareScale) {
        case 1: {
            cloudLeft.style.animation = "fade-in-cloud-left 0.3s forwards";
            cloudRight.style.animation = "fade-in-cloud-right 0.3s forwards";
            break;
        }
        case 2: {
            cloudLeft.style.animation = "fade-out-cloud-left 0.3s forwards";
            cloudRight.style.animation = "fade-out-cloud-right 0.3s forwards";
            break;
        }
    }
}

/**
 * Changes opacity of control
 *
 * @param newSquareScale
 */
function setControlOpacity(newSquareScale) {
    switch (newSquareScale) {
        case 1: {
            firstControl.style.opacity = "0.5";
            secondControl.style.opacity = "1";
            break;
        }
        case 2: {
            firstControl.style.opacity = "1";
            secondControl.style.opacity = "0.5";
            break;
        }
    }
}

indexInitialization();