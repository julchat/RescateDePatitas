
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


(function () {
    'use strict'

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
})()




// check password strength on key up
$('#password').keyup(function() {
    var pass = $(this).val();
    var cacheResult = checkPassStrength(pass);
})

// on blur makes passwordMeter border same colour as not focused
$('#password').blur(function() {
    $('#passwordMeter').addClass('blur');
})

// on focus removes class that makes passwordMeter border same colour as not focused
$('#password').focus(function() {
    if ($('#passwordMeter').hasClass('blur'))
        $('#passwordMeter').removeClass('blur');
})



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



//Problem: Hints are shown even when form is valid
//Solution: Hide and show them at appropriate times
var $password = $("#password");
var $confirmPassword = $("#confirmPassword");

//Hide hints
$("form span").hide();

function isPasswordValid() {
    return $password.val().length > 8;
}

function arePasswordsMatching() {
    return $password.val() === $confirmPassword.val();
}

function canSubmit() {
    return isPasswordValid() && arePasswordsMatching();
}

function passwordEvent(){
    //Find out if password is valid
    if(isPasswordValid()) {
        //Hide hint if valid
        $password.next().hide();
    } else {
        //else show hint
        $password.next().show();
    }
}

function confirmPasswordEvent() {
    //Find out if password and confirmation match
    if(arePasswordsMatching()) {
        //Hide hint if match
        $confirmPassword.next().hide();
    } else {
        //else show hint
        $confirmPassword.next().show();
    }
}

function enableSubmitEvent() {
    $("#submit").prop("disabled", !canSubmit());
}

//When event happens on password input
$password.focus(passwordEvent).keyup(passwordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);

//When event happens on confirmation input
$confirmPassword.focus(confirmPasswordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);

enableSubmitEvent();