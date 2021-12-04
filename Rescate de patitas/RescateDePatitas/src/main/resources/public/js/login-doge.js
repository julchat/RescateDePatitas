
let images = ['https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577754/img/login1_pkgal6.png',
    'https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577761/img/login2_k7ulyb.png',
    'https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577753/img/login3_huxstc.png'];

let index = 0;
const imgElement = document.getElementById('doge');
imgElement.src = images[index];

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function change() {
    imgElement.src = images[1];
    sleep(250).then(() =>{imgElement.src = images[0]})
}

let interval = null

window.onload = function () {
    interval = setInterval(change, 3000);
};

function esconder() {
    window.clearTimeout(interval);
    imgElement.src = images[2];
}

function continuar() {
    imgElement.src = images[0];
    interval = setInterval(change, 3000);
}