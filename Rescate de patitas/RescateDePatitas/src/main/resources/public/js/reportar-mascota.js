
function hideTransito() {
    document.getElementById('transito').style.display = 'none';
    document.getElementById('publicar').style.display = 'block';
}

function showTransito() {
    document.getElementById('transito').style.display = 'block';
    document.getElementById('publicar').style.display = 'none';
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
    el: "#repVue",
    data: {
        // Persona Loggeada
        persona: {},
        formasDeNotificacion: [],
        contactos: [],

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

        // Mascota
        tipoAnimal: "",
        sexoMascota: "",
        tamanioMascota: "",
        descripcionMascota: "",
        radioKM: "",

        // Domicilio
        provincia: "",
        localidad: "",
        codigoPostal: "",
        calle: "",
        numeracion: "",
        departamento: "",
        piso: "",

        // Para verificar que una persona haya iniciado sesion, por defecto nadie inició
        activa: false,
        domicilio: false,
        transito: ""
    },
    methods: {
        crearPublicacionMascotaPerdidaUserNoDomicilio: function () {
            let idSesion = localStorage.getItem("IDSESION");
            let status;
            let datos;
            fetch("http://localhost:9000/reportar-mascota", {
                method: "post",
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
                    tamanioMascota: this.tamanioMascota,
                    descripcionMascota: this.descripcionMascota,
                    transito: this.transito
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        },
        crearPublicacionMascotaPerdidaUserNoDomicilioHogar: function () {
            let idSesion = localStorage.getItem("IDSESION");
            let status;
            let datos;
            fetch("http://localhost:9000/reportar-mascota", {
                method: "post",
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
                    tamanioMascota: this.tamanioMascota,
                    descripcionMascota: this.descripcionMascota,
                    radioKM: this.radioKM,
                    transito: this.transito
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        },
        crearPublicacionMascotaPerdidaUserDomicilio: function () {
            let idSesion = localStorage.getItem("IDSESION");
            let status;
            let datos;
            fetch("http://localhost:9000/reportar-mascota", {
                method: "post",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    tipoAnimal: this.tipoAnimal,
                    sexoMascota: this.sexoMascota,
                    tamanioMascota: this.tamanioMascota,
                    descripcionMascota: this.descripcionMascota,
                    transito: this.transito
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        },crearPublicacionMascotaPerdidaUserDomicilioHogar: function () {
            let idSesion = localStorage.getItem("IDSESION");
            let status;
            let datos;
            fetch("http://localhost:9000/reportar-mascota", {
                method: "post",
                headers: {
                    "Authorization": idSesion
                },
                body: JSON.stringify({
                    tipoAnimal: this.tipoAnimal,
                    sexoMascota: this.sexoMascota,
                    tamanioMascota: this.tamanioMascota,
                    descripcionMascota: this.descripcionMascota,
                    radioKM: this.radioKM,
                    transito: this.transito
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        },
        crearPublicacionMascotaPerdidaCompleto: function () {
            let status;
            let datos;
            fetch("http://localhost:9000/reportar-mascota", {
                method: "post",
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
                    tamanioMascota: this.tamanioMascota,
                    descripcionMascota: this.descripcionMascota,
                    transito: this.transito
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => verificarEstado(status, datos))
        },
        crearPublicacionMascotaPerdidaCompletoHogar: function () {
            let status;
            let datos;
            fetch("http://localhost:9000/reportar-mascota", {
                method: "post",
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
                    tamanioMascota: this.tamanioMascota,
                    descripcionMascota: this.descripcionMascota,
                    radioKM: this.radioKM,
                    transito: this.transito
                })
            })
                .then(response =>{
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



