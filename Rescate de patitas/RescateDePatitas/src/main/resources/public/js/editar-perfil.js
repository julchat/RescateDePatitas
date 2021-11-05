
let app = new Vue({
    el: "#appVue",
    data: {
        nombre: ""
    },
    created: {
        sesion: function sesion(){
            let idSesion = localStorage.getItem("IDSESION")
            fetch("http://localhost:9000/editar-perfil", {
                method : "get",
                headers: {
                    "Authorization": idSesion
                }
            })
                .then(response => response.json())
                .then(objeto => {
                    this.nombre = objeto.nombre})
        }
    }
})