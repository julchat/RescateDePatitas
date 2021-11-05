let status;
/*
function validarContrasenia() {
    const contrasenia = document.getElementById('password').value;
    const user = document.getElementById('user').value;
    // verificar la contrasenia por todas las validaciones
    var mayusTest = /^[A-Z]$/;
    var minusTest = /^[a-z]$/;
    var numTest = /^[0-9]$/;
    var regularExpression = /^(?=.*[0-9])[a-zA-Z0-9]{8,64}$/;

    if(contrasenia == ""){
        var contraseniaInvalida = document.getElementById('password');
        contraseniaInvalida.classList.add('is-invalid')
        window.alert("La contraseña no puede estar vacía.");
    }
    else if(!regularExpression.test(contrasenia)) {
        window.alert("La contraseña necesita al menos una Mayúscula, minúscula y un número." +
            "También necesita tener un largo mayor a 8 caracteres y menor a 64 caracteres.");
    }
    else if(contrasenia == user) {
        window.alert("La contraseña tiene que ser diferente al usuario.");
    }

    else {
        var contraseniaValida = document.getElementById('confirmPassword');
        contraseniaValida.classList.add('is-valid')
        console.log('Proceda')
    }
}

function confirmarContrasenia() {
    const contrasenia = document.getElementById('password').value;
    const confirmacionContrasenia = document.getElementById('confirmPassword').value;
    if(contrasenia == confirmacionContrasenia) {
        var confirmacion = document.getElementById('confirmPassword');
    }
    else {
        var confirmacion = document.getElementById('confirmPassword');
        window.alert("Las contraseñas no coinciden.");
    }
}*/


function verificarEstado(status, datos){
    if(status == 200) {
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


/*
document.addEventListener('submit', validarRegistro);

function validarRegistro() {
    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
                window.alert("Faltan rellenar datos")
            }
            form.classList.add('was-validated')
            window.alert("Se ha creado correctamente.")
        }, false)
    })
}*/