function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtNome.value == "") {
        mensagem = mensagem + "Informe o Nome do Exercicio\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}