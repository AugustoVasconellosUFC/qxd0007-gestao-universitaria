package aluno.manager;

import aluno.base.Funcionario;
import cliente.IRHService;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class RHService implements IRHService {

    private final HashMap<String,Funcionario> funcionariosCpf = new HashMap<>();

    private List<Funcionario> funcionarios;

    @Override
    public boolean cadastrar(Funcionario funcionario) {
        if(!funcionariosCpf.containsKey(funcionario.getCpf()) && funcionario.isCadastroValido()) {
            funcionariosCpf.put(funcionario.getCpf(), funcionario);
            return true;
        } else
            return false;
    }

    @Override
    public boolean remover(String cpf) {
        if(funcionariosCpf.containsKey(cpf)) {
            funcionariosCpf.remove(cpf);
            return true;
        } else
            return false;
    }

    @Override
    public Funcionario obterFuncionario(String cpf) {
        return funcionariosCpf.get(cpf);
    }

    @Override
    public List<Funcionario> getFuncionarios() {
        if(funcionariosCpf.isEmpty())
            return null;
        funcionarios = new ArrayList<>();
        funcionariosCpf.forEach((key, value) -> funcionarios.add(value));
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        return funcionarios;
    }

    @Override
    public List<Funcionario> getFuncionariosPorCategoria(Tipo tipo) {
        if(funcionariosCpf.isEmpty())
            return null;
        funcionarios = new ArrayList<>();
        funcionariosCpf.forEach((key, value) -> {
            if (value.getTipo() == tipo)
                funcionarios.add(value);
        });
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        return funcionarios;
    }

    @Override
    public int getTotalFuncionarios() {
        return funcionariosCpf.size();
    }

    @Override
    public boolean solicitarDiaria(String cpf) {
        return funcionariosCpf.get(cpf).setDiarias(funcionariosCpf.get(cpf).getDiarias() + 1);
    }

    @Override
    public boolean partilharLucros(double valor) {
        if(funcionariosCpf.size() == 0)
            return false;
        else {
            valor /= funcionariosCpf.size();
            double finalValor = valor;
            funcionariosCpf.forEach((key, value) -> value.setParcelaDosLucros(finalValor));
            return true;
        }
    }

    @Override
    public void iniciarMes() {
        funcionariosCpf.forEach((key, value) -> {
            value.setParcelaDosLucros(0);
            value.setDiarias(0);
        });
    }

    @Override
    public Double calcularSalarioDoFuncionario(String cpf) {
        if(!funcionariosCpf.containsKey(cpf))
            return null;
        else
            return funcionariosCpf.get(cpf).getSalario() + funcionariosCpf.get(cpf).getDiarias() * 100 + funcionariosCpf.get(cpf).getParcelaDosLucros();
    }

    @Override
    public double calcularFolhaDePagamento() {
        AtomicReference<Double> folha = new AtomicReference<>((double) 0);
        funcionariosCpf.forEach((key, value) -> {
            Double salario = calcularSalarioDoFuncionario(key);
            if(salario != null)
                folha.updateAndGet(v -> (double) (v + salario));
        });
        return folha.get();
    }
}