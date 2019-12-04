function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.optNome.value == "0") {
        mensagem = mensagem + "Informe o Nome do Exercicio\n";
    }
    if (form.txtSeries.value == "") {
        mensagem = mensagem + "Informe as Series\n";
    }
    if (form.txtPeso.value == "") {
        mensagem = mensagem + "Informe o Peso\n";
    }
    if (form.txtRepeticoes.value == "") {
        mensagem = mensagem + "Informe as Repetições\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}