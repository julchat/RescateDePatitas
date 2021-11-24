var dominio = "http://rescatedepatitasdds.herokuapp.com";
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

        // Mascota
        tipoPerro: "",
        tipoGato: "",
        sexoHembra: "",
        sexoMacho: "",
        nombreMascota: "",
        apodoMascota: "",
        edadMascota: "",
        descripcionMascota: "",

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
        registrarMascotaUser: function() {
            let idSesion = localStorage.getItem("IDSESION");
            let status;
            let datos;
            fetch(dominio + "/registrar-mascota/registrado", {
                method: "POST",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    tipoPerro: this.tipoPerro,
                    tipoGato: this.tipoGato,
                    sexoHembra: this.sexoHembra,
                    sexoMacho: this.sexoMacho,
                    nombreMascota: this.nombreMascota,
                    apodoMascota: this.apodoMascota,
                    edadMascota: this.edadMascota,
                    descripcionMascota: this.descripcionMascota
                })
            })
                .then(response => {
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        },
        registrarMascota: function() {
            let status;
            let datos;
            fetch(dominio + "/registrar-mascota", {
                method: "POST",
                body: JSON.stringify({
                    tipoPerro: this.tipoPerro,
                    tipoGato: this.tipoGato,
                    sexoHembra: this.sexoHembra,
                    sexoMacho: this.sexoMacho,
                    nombreMascota: this.nombreMascota,
                    apodoMascota: this.apodoMascota,
                    edadMascota: this.edadMascota,
                    descripcionMascota: this.descripcionMascota
                })
            })
                .then(response => {
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        }
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/perfil", {
            method : "get",
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