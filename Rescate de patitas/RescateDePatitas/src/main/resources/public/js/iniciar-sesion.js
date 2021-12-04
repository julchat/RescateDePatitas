
let status;

function verificarEstado(status, datos){
    if(status == 200) {
        alert("Bienvenido a Rescate de Patitas...");
        window.location.href = "home";
    }
    else {
        alert(datos.mensaje);
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
            let datos;
            fetch("http://localhost:9000/iniciar-sesion", {
                method: "post",
                body: JSON.stringify({
                    userName : this.userName,
                    userPassword : this.userPassword
                })
            })
                .then(response =>{
                    status = response.status
                    datos = response.json()
                    return datos
                })
                .then(datos => {
                    localStorage.setItem("IDSESION", datos.idSesion)
                    verificarEstado(status, datos)
                })
        }
    }
})

