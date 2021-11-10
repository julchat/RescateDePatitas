
let app = new Vue({
    el: "#appVue",
    data: {

    },
    methods: {
        editarPerfil: function () {
            let idSesion = localStorage.getItem("IDSESION")
            fetch("http://localhost:9000/perfil", {
                headers: {
                    "Authorization": idSesion
                }
            })  .then(response => response.json())
                .then()


        },
        cerrarSesion: function() {
            let idSesion = localStorage.getItem("IDSESION")
            fetch("http://localhost:9000/logout", {
                headers: {
                    "Authorization": idSesion
                }
            })
                .then(datos => window.location.href = "/")
        }
    }
})



