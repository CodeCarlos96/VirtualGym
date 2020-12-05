function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtDataMatricula.value == "") {
        mensagem = mensagem + "Informe a Data da Matricula\n";
    }
    if (form.txtDiaVencimento.value == "") {
        mensagem = mensagem + "Informe o Dia do Vencimento\n";
    }
    if (form.optAluno.value == "0") {
        mensagem = mensagem + "Informe o Aluno\n";
    }
    if (form.optPlano.value == "0") {
        mensagem = mensagem + "Informe o Professor\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}