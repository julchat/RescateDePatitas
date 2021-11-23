
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
        userName: "",
        password: "",
        passConf: ""
    },
    methods: {
        registrarse: function () {
            let status;
            let datos;
            fetch("http://localhost:9000/registrarse", {
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
                    userName: this.userName,
                    password: this.password,
                    passConf: this.passConf
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
    }
})

