const dominio = "http://rescatedepatitasdds.herokuapp.com";

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: [],
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/mascotas-perdidas", {
            method : "GET",

        })  .then(response => response.json())
            .then(datos => {
                this.publicaciones = datos
            })
    }
})