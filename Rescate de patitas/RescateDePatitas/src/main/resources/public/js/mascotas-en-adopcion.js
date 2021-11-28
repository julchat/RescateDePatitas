

let app = new Vue({
    el: "#appVue",
    data: {
        publicaciones: [],
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/api/mascotas-en-adopcion", {
            method : "GET",

        })  .then(response => response.json())
            .then(datos => {
                this.publicaciones = datos
            })
    }
})