
let app = new Vue({
    el: "#appVue",
    data: {
        nombre: "",
        apellido: ""
    },
    created: {
        sesion: function sesion(){
            let idSesion = localStorage.getItem("IDSESION")
            fetch("http://localhost:9000/perfil", {
                method : "get",
                headers: {
                    "Authorization": idSesion
                }
            })
                .then(response => response.json())
                .then(persona => {
                    this.nombre = persona.nombre,
                    this.apellido = persona.apellido})
        }
    }
})