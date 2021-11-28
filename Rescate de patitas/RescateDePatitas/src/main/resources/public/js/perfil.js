
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
        // Persona
        nombre: "",
        apellido: "",
        fechaDeNacimiento: "",
        email: "",
        tipoDoc: "",
        nroDocumento: "",
        telefono: "",
        notificacionSms: "",
        notificacionEmail: "",
        notificacionWpp: "",

        // Domicilio
        provincia: "",
        localidad: "",
        codigoPostal: "",
        calle: "",
        numeracion: "",
        departamento: "",
        piso: "",

        // Datos de la Persona
        persona: {},
        formasDeNotificacion: [],
        contactos: [],

        // Para verificar que una persona haya iniciado sesion, por defecto nadie inició
        activa: false
    },
    methods: {
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/api/perfil", {
            method : "GET",
            headers: {
                "Authorization": idSesion
            }
        })  .then(response => response.json())
            .then(datos => {
                this.persona = datos
                this.formasDeNotificacion = datos.formasDeNotificacion
                this.contactos = datos.contactos
                this.activa = true
            })
    }
})