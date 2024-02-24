"use strict";

const processorLabels = [
    document.getElementById("processor-hundreds"),
    document.getElementById("processor-tens"),
    document.getElementById("processor-ones")
];

const storageLabels = [
    document.getElementById("storage-hundreds"),
    document.getElementById("storage-tens"),
    document.getElementById("storage-ones")
];

const ramLabels = [
    document.getElementById("ram-hundreds"),
    document.getElementById("ram-tens"),
    document.getElementById("ram-ones")
];

/**
 * Updates labels values
 *
 * @param {*} usageData usage value
 */
function labelsTick(usageData) {
    // WTF is this?
    const usageDataArray = Object.values(usageData);
    const labelArrays = [processorLabels, ramLabels, storageLabels];

    for (let i = 0; i < labelArrays.length; i++) {
        formatLabels(labelArrays[i], usageDataArray[i]);
    }
}

/**
 * Formats labels with usage data
 * @param labelArray HTML span array
 * @param usageData usage data
 */
function formatLabels(labelArray, usageData) {
    const theme = html.getAttribute("theme") === "light" ? "light" : "dark";
    const usageDataString = String(usageData).padStart(3, '0');

    const getColor = (theme, value) => {
        return value === "0" ? (theme === "light" ? "rgba(188, 188, 188, 1)" : "rgba(121, 121, 121, 1)") : (theme === "light" ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)");
    }

    for (let i = 0; i < labelArray.length; i++) {
        labelArray[i].innerHTML = usageDataString[i];
        labelArray[i].style.color = getColor(theme, usageDataString[i]);
    }
}