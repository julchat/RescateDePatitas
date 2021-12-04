
let images = ['https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577754/img/login1_pkgal6.png',
    'https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577761/img/login2_k7ulyb.png',
    'https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577753/img/login3_huxstc.png'];

let index = 0;
let imgElement = document.getElementById('doge');
imgElement.src = "https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577754/img/login1_pkgal6.png";

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function change() {
    imgElement.src = "https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577761/img/login2_k7ulyb.png";
    sleep(250).then(() =>{imgElement.src = "https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577754/img/login1_pkgal6.png"})
}

let interval = null

window.onload = function () {
    interval = setInterval(change, 3000);
};

function esconder() {
    window.clearTimeout(interval);
    imgElement.src = "https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577753/img/login3_huxstc.png";
}

function continuar() {
    imgElement.src = "https://res.cloudinary.com/rescatedepatitas08/image/upload/v1638577754/img/login1_pkgal6.png";
    interval = setInterval(change, 3000);
}