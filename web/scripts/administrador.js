function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtIdAdministrador.value == "") {
        mensagem = mensagem + "Informe o Código do Administrador\n";
    }
    if (form.txtNome.value == "") {
        mensagem = mensagem + "Informe o Nome do Administrador\n";
    }
    if (form.txtEmail.value == "") {
        mensagem = mensagem + "Informe o Email\n";
    } else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form.txtEmail.value)) {
        mensagem = mensagem + "Email Inválido\n";
    }
    if (form.txtCpf.value == "") {
        mensagem = mensagem + "Informe o CPF\n";
    } else if (form.txtCpf.value.length != 11) {
        mensagem = mensagem + "CPF Inválido\n";
    }
    if (form.txtRg.value == "") {
        mensagem = mensagem + "Informe o RG\n";
    }
    if (form.txtDataNascimento.value == "") {
        mensagem = mensagem + "Informe a Data de Nascimento\n";
    }
    if (form.txtTelefone.value == "") {
        mensagem = mensagem + "Informe o Telefone\n";
    }
    if (form.txtLogradouro.value == "") {
        mensagem = mensagem + "Informe o Logradouro\n";
    }
    if (form.txtNumero.value == "") {
        mensagem = mensagem + "Informe o Numero do Endereço\n";
    }
    if (form.txtBairro.value == "") {
        mensagem = mensagem + "Informe o Bairro\n";
    }
    if (form.txtCidade.value == "") {
        mensagem = mensagem + "Informe a Cidade\n";
    }
    if (form.txtUf.value == "") {
        mensagem = mensagem + "Informe a UF\n";
    }
    if (form.txtCep.value == "") {
        mensagem = mensagem + "Informe o CEP\n";
    }
    if (form.txtDataAdmissao.value == "") {
        mensagem = mensagem + "Informe a Data de Admissão\n";
    }
    if (form.txtSenha.value == "") {
        mensagem = mensagem + "Informe a Senha\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}

