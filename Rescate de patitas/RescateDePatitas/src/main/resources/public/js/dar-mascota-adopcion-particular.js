const dominio = "http://rescatedepatitasdds.herokuapp.com";

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
            fetch(dominio + "/dar-mascota-adopcion/" + id, {
                method: "POST",
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
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/darMascotaEnAdopcionUser", {
            method : "GET",
            headers: {
                "Authorization": idSesion
            }
        })  .then(response => response.json())
            .then(datos => {
                this.persona = datos.persona
                this.formasDeNotificacion = datos.persona.formasDeNotificacion
                this.contactos = datos.persona.contactos
                this.mascota = datos.mascota
                this.caracteristicasMascota = datos.mascota.caracteristicasMascota
                this.preguntas = datos.preguntas
                this.activa = true
                if(datos.persona.domicilio.calle != null && datos.persona.domicilio.provincia != null && datos.persona.domicilio.localidad != null) {
                    this.domicilio = true
                }
            })
    }
})