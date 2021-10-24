
/*
fetch('/mascotas-perdidas')
    .then(response => {
        if(response.status >= 400) {
            alert("Hay un ERROR.")
        }
        else {
            return response.json()
        }
    })
    .then(mascotas => {
        document.getElementById("app").innerHTML += '<table id="tablita" class="table table-striped table-bordered"></table>'
        mascotas.forEach(mascota => document.getElementById("tablita").innerHTML += "<tr><td>" + mascota.tipo_animal + "</td></tr>")
    })


fetch('http://localhost:9000/mascotas-perdidas')
    .then(response => {
        console.log(response);
        return response.json();
    })
    .then(mascotaObtenida => {
            this.mascotas = mascotaObtenida
            console.log(mascotaObtenida);
        })
    .catch(err => {
        alert('Anda todo para el orto')
        console.log("Error Reading data " + err);
});*/



    console.log("inside handleGetJson");
    fetch('http://localhost:9000/mascotas-perdidas', {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then(mascotaObtenida => {
            this.mascotas = mascotaObtenida
            console.log(mascotaObtenida);
        })
        .catch(err => {
            alert('Anda todo para el orto')
            console.log("Error Reading data " + err);
        });




function mostrarMascota() {
    var app = new Vue({
        el: "#app",
        data: {
            // mascotasHardcode: [{id: 1, nombre: "Juan", raza: "PEPE"}, {id:2, nombre:"Carlos", raza:"PIPO"}],
            mascotas: []
        },
        created () {
            const urlParams = new URLSearchParams(window.location.search);

            fetch('/mascotas-perdidas')
                .then(response => {
                    if(response.status >= 400) {
                        alert("Hay un ERROR.")
                    }
                    else {
                        return response.json()
                    }
                })
                .then(mascotas => {
                    document.getElementById("app").innerHTML += '<table id="tablita" class="table table-striped table-bordered"></table>'
                    mascotas.forEach(mascota => document.getElementById("tablita").innerHTML += "<tr><td>" + mascota.tipo_animal + "</td></tr>")
                })
        }
    })
}