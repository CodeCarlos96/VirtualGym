function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtNome.value == "") {
        mensagem = mensagem + "Informe o Nome\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}