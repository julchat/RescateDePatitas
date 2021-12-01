
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
            usuarios: [],
            nuevosAdmins: [],
            viejosAdmins: [],
        },
        methods: {
            crearNuevosAdmins: function() {
                let status;
                let datos;
                fetch("http://localhost:9000/agregar-admin", {
                    method: "POST",
                    body: JSON.stringify({
                        nuevosAdmins: this.nuevosAdmins,
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
            quitarAdmins: function() {
                let status;
                let datos;
                fetch("http://localhost:9000/quitar-admin", {
                    method: "POST",
                    body: JSON.stringify({
                        viejosAdmins: this.viejosAdmins,
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
        fetch("http://localhost:9000/api/agregar-admin", {
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
                    this.usuarios = data
                    console.log(data)
                }
                else {
                    window.location.href = "/sin-permisos"
                }
            })
    }
    })
