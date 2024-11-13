package org.example;

public class Estagio {
    private int id;
    private String local;
    private String supervisor;
    private String horarios;
    private String instituicao;
    private String endereco;
    private String periodo;
    private int alunoMatricula;

    // Construtor vazio
    public Estagio() {
    }

    // Construtor com todos os campos
    public Estagio(int id, String local, String supervisor, String horarios,
                   String instituicao, String endereco, String periodo, int alunoMatricula) {
        this.id = id;
        this.local = local;
        this.supervisor = supervisor;
        this.horarios = horarios;
        this.instituicao = instituicao;
        this.endereco = endereco;
        this.periodo = periodo;
        this.alunoMatricula = alunoMatricula;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(int alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    // Método para exibir informações (opcional)
    public void exibirInformacoes() {
        System.out.println("ID: " + id);
        System.out.println("Local: " + local);
        System.out.println("Supervisor: " + supervisor);
        System.out.println("Horários: " + horarios);
        System.out.println("Instituição: " + instituicao);
        System.out.println("Endereço: " + endereco);
        System.out.println("Período: " + periodo);
        System.out.println("Matrícula do Aluno: " + alunoMatricula);
    }
}