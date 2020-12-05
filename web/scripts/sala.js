
function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtNome.value == "") {
        mensagem = mensagem + "Informe o Nome da Sala\n";
    }
    if (form.txtCapacidadeSala.value == "") {
        mensagem = mensagem + "Informe a Capacidade Total da Sala\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}
   