var dominio = "http://rescatedepatitasdds.herokuapp.com";
let app = new Vue({
    el: "#appVue",
    data: {
        tipoRol: ""
    },
    methods: {
        cerrarSesion: function() {
            let idSesion = localStorage.getItem("IDSESION")
            localStorage.removeItem("IDSESION")
            fetch(dominio + "/logout", {
                headers: {
                    "Authorization": idSesion
                }
            })
                .then(datos => window.location.href = "/")
        },
        mascotasRegistradas: function() {
            let idSesion = localStorage.getItem("IDSESION")
            fetch(dominio + "/mascotas-registradas", {
                redirect: "follow",
                method: "GET",
                headers: {
                    "Authorization": idSesion
                },
            })
        }
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/user", {
            method : "GET",
            headers: {
                "Authorization": idSesion
            }
        })  .then(response => response.json())
            .then(datos => this.tipoRol = datos.mensaje)
    }
})
