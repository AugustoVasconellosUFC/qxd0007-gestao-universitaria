package aluno.base;

import cliente.IRHService.Tipo;

public class STA extends Funcionario{

    public STA(String cpf, String nome, int nivel) {
        if(nivel < 1 || nivel > 30)
            cadastroValido = false;
        this.cpf = cpf;
        this.nome = nome;
        this.salario = 1000 + 100 * nivel;
        this.tipo = Tipo.STA;
        diariasMax = 1;
    }
}