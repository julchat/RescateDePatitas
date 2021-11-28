
function verificarEstado(status, datos){
    if(status == 200) {
        alert(datos.mensaje);
        window.location.reload();
    }
    else {
        alert(datos.mensaje);
    }
}

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: [],
    },
    methods: {
        verDetalles: function(id) {
            window.location.href = "/publicaciones-pendientes/" + id;
        },
        aprobarPublicacion: function(id) {
            let idSesion = localStorage.getItem("IDSESION")
            fetch("http://localhost:9000/aprobar-publicacion", {
                method: "POST",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    publicacionID: id
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => {
                    verificarEstado(status, datos)
                })
        },
        rechazarPublicacion: function() {
            let idSesion = localStorage.getItem("IDSESION")
            fetch("http://localhost:9000/rechazar-publicacion", {
                method: "POST",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    publicacionID: this.publicacionID
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => {
                    verificarEstado(status, datos)
                })
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