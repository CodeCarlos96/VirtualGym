/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Dudu
 */
public class TraduzirExcecao {

    public static String ex(String excecao) {
        if (excecao.contains("for key 'PRIMARY'")) {
            return "Código já cadastrado";
        }
        if (excecao.contains("for key 'nome_UNIQUE'")) {
            return "Nome já cadastrado";
        }
        if (excecao.contains("for key 'cpf_UNIQUE'")) {
            return "CPF já cadastrado";
        }
        if (excecao.contains("delete or update a parent row")) {
            return "Não é possivel deletar. Usado por outro cadastro.";
        }
        return excecao;
    }
}
