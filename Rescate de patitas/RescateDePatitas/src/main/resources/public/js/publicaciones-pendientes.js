

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: []
    },
    methods: {
        verPublicacion: function(id) {

        }
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
        })
            .then(response => {
                status = response.status
                datos = response.json()
                return datos
            })
            .then(data => {
                if(status == 200) {
                    this.publicaciones = data
                    console.log(data)
                }
                else {
                    window.location.href = "/sin-permisos"
                }
            })
    }
})