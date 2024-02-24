const processorTriangle = document.getElementById("processor-triangle");
const ramTriangle = document.getElementById("ram-triangle");
const storageTriangle = document.getElementById("storage-triangle");
const chartBody = document.getElementById("chart-body");
const processorRectangle = document.getElementById("processor-rectangle");
const ramRectangle = document.getElementById("ram-rectangle");
const storageRectangle = document.getElementById("storage-rectangle");

let chartElement;

/**
 * Initializes labels and datasets
 */
function chartInitialization() {
    const ctx = chartBody.getContext("2d");

    const createDataset = (borderColor, backgroundColor) => ({
        borderWidth: 1.5,
        borderColor,
        pointRadius: 2,
        pointHoverRadius: 3,
        pointBackgroundColor: "rgba(255, 255, 255, 1)",
        pointHoverBackgroundColor: `rgba(${backgroundColor}, 1)`,
        backgroundColor: `rgba(${backgroundColor}, 0.3)`,
        data: Array(20).fill(0),
    });

    const dataLight = {
        data: {
            labels: Array(20).fill(""),
            datasets: [
                createDataset("rgba(89, 101, 249, 1)", "230, 232, 254"),
                createDataset("rgba(255, 89, 89, 1)", "249, 226, 226"),
                createDataset("rgba(8, 193, 141, 1)", "212, 242, 225"),
            ],
        },
    };

    const dataDark = {
        data: {
            labels: Array(20).fill(""),
            datasets: Array(3).fill(
                createDataset("rgba(188, 188, 188, 1)", "121, 121, 121")
            ),
        },
    };

    const options = {
        type: "line",
        options: {
            maintainAspectRatio: false,
            legend: {display: false},
            elements: {line: {tension: 0}},
            scales: {
                yAxes: [{ticks: {display: false, suggestedMin: 0, suggestedMax: 100}, gridLines: {drawTicks: false}}],
                xAxes: [{ticks: {display: false}, gridLines: {drawTicks: false}}],
            },
            animation: {duration: 150},
        },
    };

    const html = document.getElementsByTagName("html")[0];
    chartElement = new Chart(ctx, Object.assign(html.getAttribute("theme") === "light" ? dataLight : dataDark, options));

    [processorRectangle, ramRectangle, storageRectangle].forEach((element) => {
        element.addEventListener("click", (event) => hideDataset(event.target));
    });
}

function chartTick(usageData) {
    chartElement.data.datasets.forEach((dataset, i) => {
        const usageDataArray = Object.values(usageData);
        dataset.data = [...dataset.data.slice(1), usageDataArray[i]];
    });

    chartElement.update();
}

function hideDataset(element) {
    const elementId = element.id;
    const datasetIndex = {"processor-rectangle": 0, "ram-rectangle": 1, "storage-rectangle": 2}[elementId];

    if (datasetIndex !== undefined) {
        const triangle = [processorTriangle, ramTriangle, storageTriangle][datasetIndex];
        const datasetMeta = chart.getDatasetMeta(datasetIndex);
        toggleGraphAnimation(triangle, datasetMeta);
        chart.update();
    }
}

function toggleGraphAnimation(triangle, datasetMeta) {
    triangle.style.animation = datasetMeta.hidden
        ? "fade-in-triangle 0.5s forwards"
        : "fade-out-triangle 0.5s forwards";
    datasetMeta.hidden = !datasetMeta.hidden;
}

chartInitialization();