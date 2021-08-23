package aluno.base;

import cliente.IRHService.Tipo;

public class Funcionario {

    protected String nome;

    protected String cpf;

    protected double salario;

    protected Tipo tipo;

    protected int diarias;

    protected int diariasMax;

    protected double parcelaDosLucros;

    protected boolean cadastroValido = true;

    public boolean setDiarias(int diarias) {
        if(diarias >= 0 && diarias <= diariasMax) {
            this.diarias = diarias;
            return true;
        } else
            return false;
    }

    public void setParcelaDosLucros(double parcelaDosLucros) {
        this.parcelaDosLucros = parcelaDosLucros;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalario() {
        return salario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getDiarias() {
        return diarias;
    }

    public double getParcelaDosLucros() {
        return parcelaDosLucros;
    }

    public boolean isCadastroValido() {
        return cadastroValido;
    }
}