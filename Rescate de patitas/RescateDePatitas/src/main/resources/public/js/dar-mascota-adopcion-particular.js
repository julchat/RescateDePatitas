
function verificarEstado(status, datos){
    if(status == 200) {
        alert(datos.mensaje);
        window.location.href = "/";
    }
    else {
        alert(datos.mensaje);
    }
}

let app = new Vue({
    el: "#appVue",
    data: {
        // Persona Loggeada
        persona: {},
        formasDeNotificacion: [],
        contactos: [],
        mascota: {},
        caracteristicasMascota: [],

        // Datos del Formulario
        preguntas: [],
        respuestas: [],
    },
    methods: {
        registrarMascotaEnAdopcion: function(id) {
            let status;
            let datos;
            let idSesion = localStorage.getItem("IDSESION");
            fetch("http://localhost:9000/dar-mascota-adopcion/" + id, {
                method: "POST",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    respuestas: this.respuestas
                })
            })
                .then(response => {
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        },
        agregarRespuesta: function() {
            this.respuestas.push(this.respuesta);
        }
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION");
        let status;
        let datos;
        let params = window.location.href.split('/');
        let id = params[4];
        fetch("http://localhost:9000/api/darMascotaEnAdopcionUser/" + id, {
            method : "GET",
            headers: {
                "Authorization": idSesion
            }
        })  .then(response => {
                status = response.status
                datos = response.json()
                return datos
        })
            .then(data => {
                if(status == 200) {
                    this.persona = data.persona
                    this.formasDeNotificacion = data.persona.formasDeNotificacion
                    this.contactos = data.persona.contactos
                    this.mascota = data.mascota
                    this.caracteristicasMascota = data.mascota.caracteristicasMascota
                    this.preguntas = data.preguntas
                    this.activa = true
                    console.log(persona);
                }
                else if(status == 404) {
                    window.location.href = "/no-existe"
                }
                else {
                    window.location.href = "/sin-permisos"
                }
            })
    }
})