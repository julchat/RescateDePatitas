const dominio = "http://rescatedepatitasdds.herokuapp.com";

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: [],
    },
    methods: {
        estoyPerdido: function(id) {
            window.location.href = "mascotas-perdidas-pesado/estoy-perdido/" + id;
        },
        notificarRescatista: function(id) {
            window.location.href = "mascotas-perdidas-pesado/notificar-rescatista/" + id;
        }
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/mascotas-perdidas", {
            method : "GET",

        })  .then(response => response.json())
            .then(datos => {
                this.publicaciones = datos
            })
    }
})