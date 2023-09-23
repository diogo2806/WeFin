// Exemplo de script JavaScript
document.addEventListener("DOMContentLoaded", function () {
    // Selecione um elemento pelo ID e adicione um evento de clique
    const myButton = document.getElementById("myButton");
    if (myButton) {
        myButton.addEventListener("click", function () {
            alert("Botão clicado!");
        });
    }
});
