package aluno.base;

import cliente.IRHService.Tipo;

public class Terceirizado extends Funcionario{

    public Terceirizado(String cpf, String nome, boolean insalubre) {
        this.cpf = cpf;
        this.nome = nome;
        if(insalubre)
            this.salario = 1500;
        else
            this.salario = 1000;
        this.tipo = Tipo.TERC;
        diariasMax = 0;
    }
}