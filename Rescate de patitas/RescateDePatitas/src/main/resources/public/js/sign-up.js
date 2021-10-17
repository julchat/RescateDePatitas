
function validarContrasenia() {
    const contrasenia = document.getElementById('password').value;
    // verificar la contrasenia por todas las validaciones

    if(contrasenia == ""){
        var contraseniaInvalida = document.getElementById('password');
        contraseniaInvalida.classList.add('is-invalid')
        console.log('Contraseña invalida');
    }
    else {
        var contraseniaValida = document.getElementById('confirmPassword');
        contraseniaValida.classList.add('is-valid')
        console.log('Contrasenia valida');
    }
}

function confirmarContrasenia() {
    const contrasenia = document.getElementById('password').value;
    const confirmacionContrasenia = document.getElementById('confirmPassword').value;
    if(contrasenia == confirmacionContrasenia) {
        var confirmacion = document.getElementById('confirmPassword');
        confirmacion.classList.add('is-valid')
        console.log('Proceda');
    }
    else {
        var confirmacion = document.getElementById('confirmPassword');
        confirmacion.classList.add('is-invalid')
        console.log('Procedan´t');
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


/*const nombreUsuario = document.getElementById('user').value;
const contrasenia = document.getElementById('password').value;
const confirmacionContrasenia = document.getElementById('confirmPassword').value;

if(nombreUsuario == "") {
    console.log('');
}*/




// rates user's password
function scorePassword(pass) {
    var i = pass.length,
        score = 0;
    if (i >= 7) {
        score += /[a-z]/.test(pass) ? 3 : 0;
        score += /[A-Z]/.test(pass) ? 4 : 0;
        score += /\d/.test(pass) ? 1 : 0;
        score += /[^\w\d\s]/.test(pass) ? 1 : 0;
    }
    if (i >= 22 && score >= 9)
        score += 1;
    return score;
}

// adds classes depending on score
function checkPassStrength(pass) {
    var score = scorePassword(pass);
    console.log(score);
    if (score < 1)
        $('#password, #passwordMeter').removeClass().addClass('weak');
    if (score >= 7)
        $('#password, #passwordMeter').removeClass().addClass('good');
    if (score >= 8)
        $('#password, #passwordMeter').removeClass().addClass('better');
    if (score >= 9)
        $('#password, #passwordMeter').removeClass().addClass('strong');
    if (score >= 10)
        $('#password, #passwordMeter').removeClass().addClass('military');
}

