function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtIdFichaTreino.value == "") {
        mensagem = mensagem + "Informe o Código da Ficha de Treino\n";
    }
    if (form.txtDataInicio.value == "") {
        mensagem = mensagem + "Informe a Data de Inicio\n";
    }
    if (form.txtDataReavaliacao.value == "") {
        mensagem = mensagem + "Informe a Data de Reavaliação\n";
    }
    if (form.optAluno.value == "0") {
        mensagem = mensagem + "Informe o Aluno\n";
    }
    if (form.optProfessor.value == "0") {
        mensagem = mensagem + "Informe o Professor\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}