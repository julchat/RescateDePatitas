

let app = new Vue({
        el: "#appVue",
        data: {

            usuarios: [],

        },
        methods: {
        },
        created() {
            let status;
            let datos;
            let idSesion = localStorage.getItem("IDSESION")
            fetch("http://localhost:9000/api/nuevos-admins", {
                method : "GET",
                headers: {
                    "Authorization": idSesion
                }
            })  .then(response => {
                status = response.status
                datos = response.json()
            })
                .then(datos => {
                    if(status == 203) {
                        window.location.href = "/sin-permisos"
                    }
                    else {
                        this.usuarios = datos
                    }
                })
        }
    })
