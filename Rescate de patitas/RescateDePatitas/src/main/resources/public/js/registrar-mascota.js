
function hideUser() {
    document.getElementById('registro').style.display = 'none';
    document.getElementById('petRegister').style.display = 'block';
}

function showUser() {
    document.getElementById('registro').style.display = 'block';
    document.getElementById('petRegister').style.display = 'none';
}



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
        contactoNombre: "",
        contactoApellido: "",
        contactoEmail: "",
        contactoTelefono: "",
        contactoNotificacionSms: "",
        contactoNotificacionEmail: "",
        contactoNotificacionWpp: "",

        // Usuario
        userName: "",
        password: "",
        passConf: "",

        // Mascota
        tipoAnimal: "",
        sexoMascota: "",
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

        // Persona Loggeada
        persona: {},
        formasDeNotificacion: [],
        contactos: [],

        // Para verificar que una persona haya iniciado sesion, por defecto nadie inició
        activa: false,
        domicilio: false
    },
    methods: {
        registrarMascotaSoloMascotaUserYDomicilio: function() {
            let idSesion = localStorage.getItem("IDSESION");
            let status;
            let datos;
            fetch("http://localhost:9000/registrar-mascota", {
                method: "POST",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    provincia: this.provincia,
                    localidad: this.localidad,
                    codigoPostal: this.codigoPostal,
                    calle: this.calle,
                    numeracion: this.numeracion,
                    departamento: this.departamento,
                    piso: this.piso,
                    tipoAnimal: this.tipoAnimal,
                    sexoMascota: this.sexoMascota,
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
        registrarMascotaSoloMascotaUser: function() {
            let idSesion = localStorage.getItem("IDSESION");
            let status;
            let datos;
            fetch("http://localhost:9000/registrar-mascota", {
                method: "POST",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    tipoAnimal: this.tipoAnimal,
                    sexoMascota: this.sexoMascota,
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
        registrarMascotaYPersona: function() {
            let status;
            let datos;
            fetch("http://localhost:9000/registrar-mascota", {
                method: "POST",
                body: JSON.stringify({
                    nombre: this.nombre,
                    apellido: this.apellido,
                    fechaDeNacimiento: this.fechaDeNacimiento,
                    email: this.email,
                    tipoDoc: this.tipoDoc,
                    nroDocumento: this.nroDocumento,
                    telefono: this.telefono,
                    notificacionSms: this.notificacionSms,
                    notificacionEmail: this.notificacionEmail,
                    notificacionWpp: this.notificacionWpp,
                    contactoNombre: this.contactoNombre,
                    contactoApellido: this.contactoApellido,
                    contactoEmail: this.contactoEmail,
                    contactoTelefono: this.contactoTelefono,
                    contactoNotificacionSms: this.contactoNotificacionSms,
                    contactoNotificacionEmail: this.contactoNotificacionEmail,
                    contactoNotificacionWpp: this.contactoNotificacionWpp,
                    provincia: this.provincia,
                    localidad: this.localidad,
                    codigoPostal: this.codigoPostal,
                    calle: this.calle,
                    numeracion: this.numeracion,
                    departamento: this.departamento,
                    piso: this.piso,
                    tipoAnimal: this.tipoAnimal,
                    sexoMascota: this.sexoMascota,
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
        registroCompleto: function() {
            let status;
            let datos;
            fetch("http://localhost:9000/registrar-mascota", {
                method: "POST",
                body: JSON.stringify({
                    nombre: this.nombre,
                    apellido: this.apellido,
                    fechaDeNacimiento: this.fechaDeNacimiento,
                    email: this.email,
                    tipoDoc: this.tipoDoc,
                    nroDocumento: this.nroDocumento,
                    telefono: this.telefono,
                    notificacionSms: this.notificacionSms,
                    notificacionEmail: this.notificacionEmail,
                    notificacionWpp: this.notificacionWpp,
                    contactoNombre: this.contactoNombre,
                    contactoApellido: this.contactoApellido,
                    contactoEmail: this.contactoEmail,
                    contactoTelefono: this.contactoTelefono,
                    contactoNotificacionSms: this.contactoNotificacionSms,
                    contactoNotificacionEmail: this.contactoNotificacionEmail,
                    contactoNotificacionWpp: this.contactoNotificacionWpp,
                    userName: this.userName,
                    password: this.password,
                    passConf: this.passConf,
                    provincia: this.provincia,
                    localidad: this.localidad,
                    codigoPostal: this.codigoPostal,
                    calle: this.calle,
                    numeracion: this.numeracion,
                    departamento: this.departamento,
                    piso: this.piso,
                    tipoAnimal: this.tipoAnimal,
                    sexoMascota: this.sexoMascota,
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
                if(datos.domicilio.calle != null && datos.domicilio.provincia != null && datos.domicilio.localidad != null) {
                    this.domicilio = true
                }
            })
    }
})