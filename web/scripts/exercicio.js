function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtIdExercicio.value == "") {
        mensagem = mensagem + "Informe o Código do Exercicio\n";
    }
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