function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.optNome.value == "0") {
        mensagem = mensagem + "Informe o Nome do Exercicio\n";
    }
    if (form.txtTempo.value == "") {
        mensagem = mensagem + "Informe o Tempo\n";
    }
    if (form.txtDistancia.value == "") {
        mensagem = mensagem + "Informe a Distância\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}