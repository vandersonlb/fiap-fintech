package br.com.fintech.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.Conta;
import br.com.fintech.bean.GrupoCategoria;
import br.com.fintech.bean.Investimento;
import br.com.fintech.bean.Tipo;
import br.com.fintech.bean.Transacao;
import br.com.fintech.bean.Usuario;

public class TesteBean {

  public static void main(String[] args) {
    Calendar calendario = Calendar.getInstance();

    Tipo tipo1 = new Tipo(1, "Entrada");
    Tipo tipo2 = new Tipo(1, "Saída");
    Tipo tipo3 = new Tipo(1, "Aporte");
    Tipo tipo4 = new Tipo(1, "Resgate");

    List<Categoria> rendas = new ArrayList<Categoria>();
    rendas.add(new Categoria(1, "Bônus"));
    rendas.add(new Categoria(2, "Empréstimos"));
    rendas.add(new Categoria(3, "Outras rendas"));

    List<Categoria> gastos = new ArrayList<Categoria>();
    gastos.add(new Categoria(6, "Contas residenciais"));
    gastos.add(new Categoria(7, "Educação"));
    gastos.add(new Categoria(8, "Mercado"));

    GrupoCategoria grupoRenda = new GrupoCategoria(1, "Renda", rendas);
    GrupoCategoria grupoGasto = new GrupoCategoria(2, "Gastos Essenciais", gastos);

    calendario.set(2022, Calendar.OCTOBER, 1);
    Calendar dataNasc = calendario;

    Usuario pedro = new Usuario(63636363512L, "Pedro Alcântara Machado", dataNasc, "p.machado@hotmail.com",
        "119657533321", "123456");

    List<Conta> contas = new ArrayList<Conta>();
    contas.add(new Conta(123456, pedro.getNumCPF(), "Conta Pessoal", 15.55f));
    contas.add(new Conta(654321, pedro.getNumCPF(), "Conta Profissional", 150000.55f));
    pedro.setContas(contas);

    List<Investimento> investimentos = new ArrayList<Investimento>();
    investimentos.add(new Investimento(123456, 10, "Casa 2023", 0, 15.55F));
    investimentos.add(new Investimento(123456, 11, "Reserva Emergência", 1000, 0));
    contas.get(0).setInvestimentos(investimentos);

    System.out.println(pedro);

    calendario.set(2022, Calendar.OCTOBER, 1);
    Calendar dataTrans = calendario;

    Transacao trans1 = new Transacao(contas.get(0), 1212, "Salário", null, tipo1, 1500.55F, dataTrans,
        grupoRenda.getCategorias().get(2), "nada");
    System.out.println(trans1);

  }
}
