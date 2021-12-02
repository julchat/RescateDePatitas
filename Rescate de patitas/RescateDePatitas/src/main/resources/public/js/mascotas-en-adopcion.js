const dominio = "http://rescatedepatitasdds.herokuapp.com";

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: [],
    },
    methods: {
        estoyEnAdopcion: function(id) {
            window.location.href = "mascotas-en-adopcion-pesado/estoy-en-adopcion/" + id;
        },
        notificarDuenio: function(id) {
            window.location.href = "mascotas-en-adopcion-pesado/notificar-duenio/" + id;
        }
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/mascotas-en-adopcion", {
            method : "GET",

        })  .then(response => response.json())
            .then(datos => {
                this.publicaciones = datos
            })
    }
})