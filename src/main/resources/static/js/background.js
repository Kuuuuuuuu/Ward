"use strict";

function backgroundInitialization() {
    const html = document.getElementsByTagName("html")[0];
    const theme = html.getAttribute("theme");
    const options = {
        light: {
            highlightColor: 0xCAC7E8,
            midtoneColor: 0xBBB7ED,
            lowlightColor: 0xE4E3EF,
            baseColor: 0xE4E3EF,
        },
        dark: {
            highlightColor: 0x797979,
            midtoneColor: 0xFFFFFF,
            lowlightColor: 0xBCBCBC,
            baseColor: 0xBCBCBC,
        },
    };

    let background = VANTA.FOG({el: "#background", blurFactor: 0.40, zoom: 1.50});
    background.setOptions(options[theme]);
}

backgroundInitialization();