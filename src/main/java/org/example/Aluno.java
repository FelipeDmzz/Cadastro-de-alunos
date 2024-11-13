package org.example;

public class Aluno {
    private String nome;
    private int matricula;
    private String email;
    private String curso;
    private String telefone;
    private String dataNascimento;
    private String turno;
    private String instituicao;
    private String observacao;

    // Adicione este construtor vazio
    public Aluno() {
        // Construtor vazio
    }

    // Mantenha o construtor com parâmetros
    public Aluno(String nome, int matricula, String email, String curso, String telefone,
                 String dataNascimento, String turno, String instituicao, String observacao) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.curso = curso;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.turno = turno;
        this.instituicao = instituicao;
        this.observacao = observacao;
    }

    // Mantenha todos os getters e setters...
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Email: " + email);
        System.out.println("Curso: " + curso);
        System.out.println("Telefone: " + telefone);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("Turno: " + turno);
        System.out.println("Instituição: " + instituicao);
        System.out.println("Observação: " + observacao);
    }
}