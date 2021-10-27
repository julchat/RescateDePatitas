
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
}


document.addEventListener('submit', validarRegistro);

function validarRegistro() {
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
}