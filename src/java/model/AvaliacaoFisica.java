
package model;

import dao.AvaliacaoFisicaDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AvaliacaoFisica {
    private int idAvaliacaoFisica;
    private Date dataAvaliacao;
    private Date dataReavaliacao;
    private float peso;
    private float altura;
    private float perimetroTorax;
    private float perimetroQuadril;
    private float perimetroAbdomen;
    private float perimetroCintura;
    private float perimetroAntebracoDireito;
    private float perimetroAntebracoEsquerdo;
    private float perimetroBracoDireito;
    private float perimetroBracoEsquerdo;
    private float perimetroCoxaDireita;
    private float perimetroCoxaEsquerda;
    private float perimetroPanturrilhaDireita;
    private float perimetroPanturrilhaEsquerda;
    private float dobraSubescapular;
    private float dobraTricipital;
    private float dobraPeitoral;
    private float dobraAbdominal;
    private float dobraSuprailiaca;
    private float dobraCoxaDireita;
    private float dobraCoxaEsquerda;
    
    private Aluno aluno;
    private int idAluno;
    private Professor professor;
    private int idProfessor;

    public AvaliacaoFisica(int idAvaliacaoFisica, Date dataAvaliacao, Date dataReavaliacao, float peso, float altura, float perimetroTorax, float perimetroQuadril, float perimetroAbdomen, float perimetroCintura, float perimetroAntebracoDireito, float perimetroAntebracoEsquerdo, float perimetroBracoDireito, float perimetroBracoEsquerdo, float perimetroCoxaDireita, float perimetroCoxaEsquerda, float perimetroPanturrilhaDireita, float perimetroPanturrilhaEsquerda, float dobraSubescapular, float dobraTricipital, float dobraPeitoral, float dobraAbdominal, float dobraSuprailiaca, float dobraCoxaDireita, float dobraCoxaEsquerda, Aluno aluno, Professor professor) {
        this.idAvaliacaoFisica = idAvaliacaoFisica;
        this.dataAvaliacao = dataAvaliacao;
        this.dataReavaliacao = dataReavaliacao;
        this.peso = peso;
        this.altura = altura;
        this.perimetroTorax = perimetroTorax;
        this.perimetroQuadril = perimetroQuadril;
        this.perimetroAbdomen = perimetroAbdomen;
        this.perimetroCintura = perimetroCintura;
        this.perimetroAntebracoDireito = perimetroAntebracoDireito;
        this.perimetroAntebracoEsquerdo = perimetroAntebracoEsquerdo;
        this.perimetroBracoDireito = perimetroBracoDireito;
        this.perimetroBracoEsquerdo = perimetroBracoEsquerdo;
        this.perimetroCoxaDireita = perimetroCoxaDireita;
        this.perimetroCoxaEsquerda = perimetroCoxaEsquerda;
        this.perimetroPanturrilhaDireita = perimetroPanturrilhaDireita;
        this.perimetroPanturrilhaEsquerda = perimetroPanturrilhaEsquerda;
        this.dobraSubescapular = dobraSubescapular;
        this.dobraTricipital = dobraTricipital;
        this.dobraPeitoral = dobraPeitoral;
        this.dobraAbdominal = dobraAbdominal;
        this.dobraSuprailiaca = dobraSuprailiaca;
        this.dobraCoxaDireita = dobraCoxaDireita;
        this.dobraCoxaEsquerda = dobraCoxaEsquerda;
        this.aluno = aluno;
        this.professor = professor;
    }

    

    public int getIdAvaliacaoFisica() {
        return idAvaliacaoFisica;
    }

    public void setIdAvaliacaoFisica(int idAvaliacaoFisica) {
        this.idAvaliacaoFisica = idAvaliacaoFisica;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Date getDataReavaliacao() {
        return dataReavaliacao;
    }

    public void setDataReavaliacao(Date dataReavaliacao) {
        this.dataReavaliacao = dataReavaliacao;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPerimetroTorax() {
        return perimetroTorax;
    }

    public void setPerimetroTorax(float perimetroTorax) {
        this.perimetroTorax = perimetroTorax;
    }

    public float getPerimetroQuadril() {
        return perimetroQuadril;
    }

    public void setPerimetroQuadril(float perimetroQuadril) {
        this.perimetroQuadril = perimetroQuadril;
    }

    public float getPerimetroAbdomen() {
        return perimetroAbdomen;
    }

    public void setPerimetroAbdomen(float perimetroAbdomen) {
        this.perimetroAbdomen = perimetroAbdomen;
    }

    public float getPerimetroCintura() {
        return perimetroCintura;
    }

    public void setPerimetroCintura(float perimetroCintura) {
        this.perimetroCintura = perimetroCintura;
    }

    public float getPerimetroAntebracoDireito() {
        return perimetroAntebracoDireito;
    }

    public void setPerimetroAntebracoDireito(float perimetroAntebracoDireito) {
        this.perimetroAntebracoDireito = perimetroAntebracoDireito;
    }

    public float getPerimetroAntebracoEsquerdo() {
        return perimetroAntebracoEsquerdo;
    }

    public void setPerimetroAntebracoEsquerdo(float perimetroAntebracoEsquerdo) {
        this.perimetroAntebracoEsquerdo = perimetroAntebracoEsquerdo;
    }

    public float getPerimetroBracoDireito() {
        return perimetroBracoDireito;
    }

    public void setPerimetroBracoDireito(float perimetroBracoDireito) {
        this.perimetroBracoDireito = perimetroBracoDireito;
    }

    public float getPerimetroBracoEsquerdo() {
        return perimetroBracoEsquerdo;
    }

    public void setPerimetroBracoEsquerdo(float perimetroBracoEsquerdo) {
        this.perimetroBracoEsquerdo = perimetroBracoEsquerdo;
    }

    public float getPerimetroCoxaDireita() {
        return perimetroCoxaDireita;
    }

    public void setPerimetroCoxaDireita(float perimetroCoxaDireita) {
        this.perimetroCoxaDireita = perimetroCoxaDireita;
    }

    public float getPerimetroCoxaEsquerda() {
        return perimetroCoxaEsquerda;
    }

    public void setPerimetroCoxaEsquerda(float perimetroCoxaEsquerda) {
        this.perimetroCoxaEsquerda = perimetroCoxaEsquerda;
    }

    public float getPerimetroPanturrilhaDireita() {
        return perimetroPanturrilhaDireita;
    }

    public void setPerimetroPanturrilhaDireita(float perimetroPanturrilhaDireita) {
        this.perimetroPanturrilhaDireita = perimetroPanturrilhaDireita;
    }

    public float getPerimetroPanturrilhaEsquerda() {
        return perimetroPanturrilhaEsquerda;
    }

    public void setPerimetroPanturrilhaEsquerda(float perimetroPanturrilhaEsquerda) {
        this.perimetroPanturrilhaEsquerda = perimetroPanturrilhaEsquerda;
    }

    public float getDobraSubescapular() {
        return dobraSubescapular;
    }

    public void setDobraSubescapular(float dobraSubescapular) {
        this.dobraSubescapular = dobraSubescapular;
    }

    public float getDobraTricipital() {
        return dobraTricipital;
    }

    public void setDobraTricipital(float dobraTricipital) {
        this.dobraTricipital = dobraTricipital;
    }

    public float getDobraPeitoral() {
        return dobraPeitoral;
    }

    public void setDobraPeitoral(float dobraPeitoral) {
        this.dobraPeitoral = dobraPeitoral;
    }

    public float getDobraAbdominal() {
        return dobraAbdominal;
    }

    public void setDobraAbdominal(float dobraAbdominal) {
        this.dobraAbdominal = dobraAbdominal;
    }

    public float getDobraSuprailiaca() {
        return dobraSuprailiaca;
    }

    public void setDobraSuprailiaca(float dobraSuprailiaca) {
        this.dobraSuprailiaca = dobraSuprailiaca;
    }

    public float getDobraCoxaDireita() {
        return dobraCoxaDireita;
    }

    public void setDobraCoxaDireita(float dobraCoxaDireita) {
        this.dobraCoxaDireita = dobraCoxaDireita;
    }

    public float getDobraCoxaEsquerda() {
        return dobraCoxaEsquerda;
    }

    public void setDobraCoxaEsquerda(float dobraCoxaEsquerda) {
        this.dobraCoxaEsquerda = dobraCoxaEsquerda;
    }

    public Aluno getAluno() throws ClassNotFoundException, SQLException {
        if((this.idAluno != 0) && (this.aluno == null)){
            this.aluno = Aluno.obterAluno(this.idAluno);
        }
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public Professor getProfessor() throws ClassNotFoundException, SQLException {
        if((this.idProfessor != 0) && (this.professor == null)){
            this.professor = Professor.obterProfessor(this.idProfessor);
        }
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public static AvaliacaoFisica obterAvaliacaoFisica(int idAvaliacaoFisica) throws ClassNotFoundException, SQLException{
        return AvaliacaoFisicaDAO.obterAvaliacaoFisica(idAvaliacaoFisica);
    }
    
    public static List<AvaliacaoFisica> obterAvaliacoesFisicas() throws ClassNotFoundException, SQLException{
        return AvaliacaoFisicaDAO.obterAvaliacoesFisicas();
    }
    
    public void gravar() throws SQLException, ClassNotFoundException{
        AvaliacaoFisicaDAO.gravar(this);
    }
    
    public void editar() throws SQLException, ClassNotFoundException{
        AvaliacaoFisicaDAO.editar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        AvaliacaoFisicaDAO.excluir(this);
    }
}
