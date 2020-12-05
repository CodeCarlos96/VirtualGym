function validarFormulario(form) {
    var mensagem;
    mensagem = "";
    if (form.txtDataAvaliacao.value == "") {
        mensagem = mensagem + "Informe a Data da Avaliação\n";
    }
    if (form.txtDataReavaliacao.value == "") {
        mensagem = mensagem + "Informe a Data da Reavaliação\n";
    }
    if (form.txtAltura.value == "") {
        mensagem = mensagem + "Informe a Altura\n";
    }
    if (form.txtPeso.value == "") {
        mensagem = mensagem + "Informe o Peso\n";
    }
    if (form.txtPerimetroTorax.value == "") {
        mensagem = mensagem + "Informe o Perimetro do Torax\n";
    }
    if (form.txtPerimetroQuadril.value == "") {
        mensagem = mensagem + "Informe o Perimetro do Quadril\n";
    }
    if (form.txtPerimetroAbdomen.value == "") {
        mensagem = mensagem + "Informe o Perimetro do Abdomen\n";
    }
    if (form.txtPerimetroCintura.value == "") {
        mensagem = mensagem + "Informe o Perimetro da Cintura\n";
    }
    if (form.txtPerimetroAntebracoDireito.value == "") {
        mensagem = mensagem + "Informe o Perimetro do Antebraço Direito\n";
    }
    if (form.txtPerimetroAntebracoEsquerdo.value == "") {
        mensagem = mensagem + "Informe o Perimetro do  Antebraço Esquerdo\n";
    }
    if (form.txtPerimetroBracoDireito.value == "") {
        mensagem = mensagem + "Informe o Perimetro do Braço Direito\n";
    }
    if (form.txtPerimetroBracoEsquerdo.value == "") {
        mensagem = mensagem + "Informe o Perimetro do Braço Esquerdo\n";
    }
    if (form.txtPerimetroCoxaDireita.value == "") {
        mensagem = mensagem + "Informe o Perimetro da Coxa Direita\n";
    }
    if (form.txtPerimetroCoxaEsquerda.value == "") {
        mensagem = mensagem + "Informe o Perimetro da  Coxa Esquerda\n";
    }
    if (form.txtPerimetroPanturrilhaDireita.value == "") {
        mensagem = mensagem + "Informe o Perimetro da Panturrilha Direita\n";
    }
    if (form.txtPerimetroPanturrilhaEsquerda.value == "") {
        mensagem = mensagem + "Informe o Perimetro da Panturrilha Esquerda\n";
    }
    if (form.txtDobraSubescapular.value == "") {
        mensagem = mensagem + "Informe a Dobra Subescapular\n";
    }
    if (form.txtDobraTricipital.value == "") {
        mensagem = mensagem + "Informe a Dobra Tricipital\n";
    }
    if (form.txtDobraPeitoral.value == "") {
        mensagem = mensagem + "Informe a Dobra Peitoral\n";
    }
    if (form.txtDobraAbdominal.value == "") {
        mensagem = mensagem + "Informe a Dobra Abdominal\n";
    }
    if (form.txtDobraSuprailiaca.value == "") {
        mensagem = mensagem + "Informe a Dobra Supra-ilíaca\n";
    }
    if (form.txtDobraCoxaDireita.value == "") {
        mensagem = mensagem + "Informe a Dobra da Coxa Direita\n";
    }
    if (form.txtDobraCoxaEsquerda.value == "") {
        mensagem = mensagem + "Informe a Dobra da Coxa Esquerda\n";
    }
    if (form.optProfessor.value == "0") {
        mensagem = mensagem + "Informe o Professor\n";
    }
    if (form.optAluno.value == "0") {
        mensagem = mensagem + "Informe o Aluno\n";
    }
    if (mensagem == "") {
        return true;
    } else {
        alert(mensagem);
        return false;
    }
}