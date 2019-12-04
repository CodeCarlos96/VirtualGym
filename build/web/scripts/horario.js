function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtHoraInicio.value == "") {
        mensagem = mensagem + "Informe o Horário de Inicio\n";
    }
    if (form.txtHoraFim.value == "") {
        mensagem = mensagem + "Informe o Horário de Fim\n";
    }else if(form.txtHoraFim.value <= form.txtHoraInicio.value){
        mensagem = mensagem + "Horário de Fim Não Permitido\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}