function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtIdAula.value == "") {
        mensagem = mensagem + "Informe o Código da Aula\n";
    }
    if (form.txtNome.value == "") {
        mensagem = mensagem + "Informe o Nome da Sala\n";
    }
    if (form.txtValorAula.value == "") {
        mensagem = mensagem + "Informe o Valor da Aula\n";
    }
    if (form.txtTaxaJuros.value == "") {
        mensagem = mensagem + "Informe a Taxa de Juros\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}