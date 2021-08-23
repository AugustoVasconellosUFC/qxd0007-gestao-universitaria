package aluno.base;

import cliente.IRHService.Tipo;

public class Professor extends Funcionario {

    public Professor(String cpf, String nome, char classe) {
        if(classe < 'A' || classe > 'E')
            throw new IllegalArgumentException("classe deve ser A, B, C, D ou E");
        this.cpf = cpf;
        this.nome = nome;
        this.salario = 3000 + (2000 * (classe - 'A'));
        this.tipo = Tipo.PROF;
        diariasMax = 3;
    }
}