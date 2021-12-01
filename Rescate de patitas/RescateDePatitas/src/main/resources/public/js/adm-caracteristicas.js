
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
        caracteristicasVisibles: [],
        caracteristicasAQuitar: [],
        caracteristicas: [],
        caracteristicaNueva: "",
    },
    methods: {
        agregarCaracteristica: function() {
            let status;
            let datos;
            fetch("http://localhost:9000/agregar-caracteristica", {
                method: "POST",
                body: JSON.stringify({
                    caracteristicaNueva: this.caracteristicaNueva,
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
        mostrarCaracteristicas: function() {
            let status;
            let datos;
            fetch("http://localhost:9000/visibilizar-caractersticas", {
                method: "POST",
                body: JSON.stringify({
                    caracteristicasVisibles: this.caracteristicasVisibles,
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
        quitarCaracteristicas: function() {
            let status;
            let datos;
            fetch("http://localhost:9000/ocultar-caracteristicas", {
                method: "POST",
                body: JSON.stringify({
                    caracteristicasAQuitar: this.caracteristicasAQuitar,
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
        fetch("http://localhost:9000/api/cambiar-caracteristicas", {
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
                    this.caracteristicas = data
                    console.log(data)
                }
                else {
                    window.location.href = "/sin-permisos"
                }
            })
    }
})