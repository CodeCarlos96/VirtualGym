function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtDataEntrada.value == "") {
        mensagem = mensagem + "Informe a Data e Horário de Entrada\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}