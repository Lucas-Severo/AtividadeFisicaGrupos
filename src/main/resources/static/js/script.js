const localidadeInput = document.querySelector(".localidade")
const localidades = {}

function extrairLocalidades() {
    const listaLocalidade = document.querySelector("#listaLocalidades select")
    const localidadeOptions = listaLocalidade.children

    for(let localidade of localidadeOptions){
        localidades[localidade.textContent] = localidade.value
        localidade.removeAttribute("value")
    }
}

extrairLocalidades()

function buscarElemento(elemento, texto) {
    const elementos = document.querySelectorAll(elemento)
    for (const elemento of elementos) {
        if(elemento.textContent === texto) {
            return elemento
        }
    }
}

localidadeInput.addEventListener('blur', event => {
    if(event.target.value){
        const id = localidades[event.target.value]
        const elemento = buscarElemento("option", event.target.value)
        elemento.setAttribute("value", id)
        elemento.setAttribute("selected", "")
    }
})

localidadeInput.addEventListener('focus', () => {
    const elementos = document.querySelectorAll("#listaLocalidades option")
    for(let elemento of elementos) {
        elemento.removeAttribute("value")
        elemento.removeAttribute("selected")
    }
})