function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtIdTurma.value == "") {
        mensagem = mensagem + "Informe o Código da Turma\n";
    }
    if (form.optSala.value == "0") {
        mensagem = mensagem + "Informe a Sala\n";
    }
    if (form.optAula.value == "0") {
        mensagem = mensagem + "Informe a Aula\n";
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