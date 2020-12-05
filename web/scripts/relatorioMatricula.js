function validarFormulario(form) {
    let mensagem;
    mensagem = "";
    if (!form.txtStatus1.checked && !form.txtStatus2.checked && !form.txtStatus3.checked) {
        mensagem = mensagem + "Informe pelo menos um status\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}