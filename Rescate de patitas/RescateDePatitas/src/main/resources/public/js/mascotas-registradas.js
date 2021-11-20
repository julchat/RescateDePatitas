
let app = new Vue({
    el: "#appVue",
    data: {
        mascotas: [{}]
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/mascotas", {
            method : "get",
            headers: {
                "Authorization": idSesion
            }
        })  .then(response => response.json())
            .then(datos => {this.mascotas = datos
                    console.log(datos)})
    }
})