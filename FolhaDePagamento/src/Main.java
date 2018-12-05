import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Empresa.agenda.add(new Agenda("semanalmente", new int[]{5,10,15,20}));
        Empresa.agenda.add(new Agenda("bi-semanalmente", new int[]{5,15}));
        Empresa.agenda.add(new Agenda("mensalmente", new int[]{20}));

        while (true) {

            System.out.println("\nFolha de Pagamento\n" +
                    "(1) Adicionar empregado\n" +
                    "(2) Remover empregado\n" +
                    "(3) Lançar um cartão de ponto\n" +
                    "(4) Lançar um resultado de venda\n" +
                    "(5) Lançar uma taxa de serviço\n" +
                    "(6) Alterar detalhes de um empregado\n" +
                    "(7) Rodar folha de pagamento\n" +
                    "(8) Desfazer\n" +
                    "(9) Refazer\n" +
                    "(10) Agenda de pagamento\n" +
                    "(11) Criação de novas agendas de pagamento\n" +
                    "(12) Empregados\n" +
                    "(0) Sair");

            int opc = input.nextInt();

            Empresa empresa = new Empresa();
            switch (opc) {
                case 1: // Adicionar empregado
                    Transacao.novaTransacao(true);
                    empresa.adicionarEmpregado();
                    break;
                case 2: // Remover empregado
                    Transacao.novaTransacao(true);
                    empresa.removerEmpregado();
                    break;
                case 3: // Cartão de ponto
                    Transacao.novaTransacao(true);
                    CartaoDePonto cartaoDePonto = new CartaoDePonto();
                    cartaoDePonto.lancarCartaoDePonto();
                    break;
                case 4: // Resultado de venda
                    Transacao.novaTransacao(true);
                    Venda venda = new Venda();
                    venda.lancarResultadoDeVenda();
                    break;
                case 5: // Taxa de serviço
                    Transacao.novaTransacao(true);
                    Sindicato sindicato = new Sindicato();
                    sindicato.lancarTaxaDeServico();
                    break;
                case 6:
                    Transacao.novaTransacao(true);
                    empresa.alterarEmpregado();
                    break;
                case 7: // Rodar folha de pagamento
                    Transacao.novaTransacao(true);
                    Empregado empregado = new Empregado();
                    empregado.rodarFolha();
                    break;
                case 8: // Desfazer
                    Transacao.desfazer();
                    break;
                case 9: // Refazer
                    Transacao.refazer();
                    break;
                case 10: // Agenda de pagamento
                    Agenda agenda = new Agenda();
                    agenda.selecionarAgenda();
                    break;
                case 11: // Criação de agenda
                    agenda = new Agenda();
                    agenda.criarAgenda();
                    break;
                case 12: // Listar todos os empregados
                    empresa.listarEmpregados();
                    break;
                case 0:
                    return;
            }

        }

    }

}
