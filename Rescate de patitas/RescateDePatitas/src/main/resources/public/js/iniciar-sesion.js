
let status;

function iniciarSesion(usuarioOemail, contrasenia){

    fetch("http://localhost:4567/patitas/iniciarSesion", {

        method: "POST",
        body: JSON.stringify({
            "usuario_Email":usuarioOemail,
            "contrasenia":contrasenia
        })

    })
        .then(Response => verificarEstado(Response.status))

}

function verificarEstado(status){
    if(status == 200) {
        window.location.href = "home";
    }
    else if(status == 400) {
        alert("La contraseña es incorrecta");
    }
    else {
        alert("404 NOT FOUND");
    }
}


let app = new Vue({
    el: "#appVue",
    data: {
        userName: "",
        userPassword: ""
    },
    methods: {
        iniciarSesion: function () {
            let status;
            fetch("http://localhost:9000/iniciar-sesion", {
                method: "post",
                body: JSON.stringify({
                    userName : this.userName,
                    userPassword : this.userPassword
                })
            })
                .then(response =>{
                    status = response.status
                    return response.json()
                })
                .then(datos => {
                    localStorage.setItem("IDSESION", datos.idSesion)
                    verificarEstado(status)
                })
        }
    }
})



