var dominio = "http://rescatedepatitasdds.herokuapp.com";
let app = new Vue({
    el: "#vuePet",
    data: {
        mascotas: []
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/mascotasUser", {
            method : "GET",
            headers: {
                "Authorization": idSesion
            }
        })  .then(response => response.json())
            .then(datos => {
                this.mascotas = datos
                console.log(this.mascotas)
            })
    }
})

