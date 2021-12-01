

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: [],
    },
    methods: {
        estoyPerdido: function(id) {
            window.location.href = "estoy-perdido/" + id;
        },
        notificarRescatista: function(id) {
            window.location.href = "notificar-rescatista/" + id;
        }
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/api/mascotas-perdidas", {
            method : "GET",

        })  .then(response => response.json())
            .then(datos => {
                this.publicaciones = datos
            })
    }
})