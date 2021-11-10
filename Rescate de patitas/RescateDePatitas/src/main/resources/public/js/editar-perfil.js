
let app = new Vue({
    el: "#appVue",
    data: {
        persona: {}
    },
    created() {
        let idSesion = localStorage.getItem("IDSESION")
        fetch("http://localhost:9000/perfil", {
            method : "get",
            headers: {
                "Authorization": idSesion
            }
        })  .then(response => response.json())
            .then(datos => this.persona = datos)
    }
})