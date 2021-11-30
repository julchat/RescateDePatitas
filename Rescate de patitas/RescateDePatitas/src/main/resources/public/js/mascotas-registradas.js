const dominio = "http://rescatedepatitasdds.herokuapp.com";

let app = new Vue({
    el: "#appVue",
    data: {
        mascotasRegistradas: [],
    },
    methods: {
        darEnAdopcion: function(id) {
            let idSesion = localStorage.getItem("IDSESION")
            fetch( dominio + "/dar-mascota-adopcion/" + id, {
                method: "GET",
                headers: {
                    "Authorization": idSesion
                },

            }) .finally()
                //.then(window.location.replace("dar-mascota-adopcion/" + id))
        }

    },
    created() {
        let status;
        let datos;
        let idSesion = localStorage.getItem("IDSESION")
        fetch(dominio + "/api/mascotasUser", {
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

