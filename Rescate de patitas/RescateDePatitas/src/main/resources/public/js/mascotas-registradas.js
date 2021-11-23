
let app = new Vue({
    el: "#vuePet",
    data: {
        mascotas: []
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/api/mascotasUser", {
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

