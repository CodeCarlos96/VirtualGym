function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtNome.value == "") {
        mensagem = mensagem + "Informe o Nome do Plano\n";
    }
    if (form.txtTaxaAdesao.value == "") {
        mensagem = mensagem + "Informe a Taxa de Adesão\n";
    }
    if (form.txtParcelas.value == "") {
        mensagem = mensagem + "Informe as Parcelas\n";
    }
    if (form.txtValor.value == "") {
        mensagem = mensagem + "Informe o Valor Total do Plano\n";
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