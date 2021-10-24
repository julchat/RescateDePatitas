
// fetch es un GET por default
fetch("http://localhost:8080/mascotas")
    .then(response => {
        if(response.status >= 400) {
            alert("Hubo un error en el API")
        }
        else {
            return console.log(response.json())
        }
    })
// se ejecuta apenas carga el script