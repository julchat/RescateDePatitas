
let app = new Vue({
    el: "#appVue",
    data: {
        mascotasRegistradas: [],
    },
    methods: {
        darEnAdopcion: function(id) {
            window.location.href = "dar-mascota-adopcion/" + id;
        }

    },
    created() {
        let status;
        let datos;
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/api/mascotasUser", {
            method : "GET",
            headers: {
                "Authorization": idSesion
            }
        })
            .then(response => {
                status = response.status
                datos = response.json()
                return datos
            })
            .then(data => {
                if(status = 200) {
                    this.mascotasRegistradas = data
                    console.log(this.mascotasRegistradas)
                }
                else {
                    alert(data.mensaje);
                }
            })

    }
})

