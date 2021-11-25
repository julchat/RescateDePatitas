

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: []
    },
    methods: {
    },
    created() {
        let status;
        let datos;
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/api/publicaciones", {
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
                    this.publicaciones = datos
                }
            })
    }
})