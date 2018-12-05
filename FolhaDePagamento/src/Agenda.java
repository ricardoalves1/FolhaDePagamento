import java.util.Arrays;
import java.util.Scanner;

public class Agenda {

    private String tipo;
    private int[] diaPagamento = new int[4];

    public Agenda() {}

    public Agenda(String tipo, int[] diaPagamento) {
        this.tipo = tipo;
        this.diaPagamento = diaPagamento;
    }

    public void criarAgenda() {

        Scanner input = new Scanner(System.in);

        System.out.print("Criação de Novas Agendas de Pagamento\nTipo de agenda: ");
        String tipo = input.nextLine();

        for (Agenda i: Empresa.agenda) {
            if (i.getTipo().equals(tipo)) {
                System.out.println("Já existe uma agenda de pagamento com esse nome\nCriação de nova agenda cancelada");
                return;
            }
        }

        System.out.println("Dia de pagamento:\n" +
                "(1) Mensalmente\n" +
                "(2) Bi-semanalmente\n" +
                "(3) Semanalmente\n" +
                "(0) Cancelar");
        int opc = input.nextInt();
        int[] diaPagamento = new int[4];

        if (opc == 1) {
            System.out.print("\nPagamento Mensal\nDia no mês (1 - 20): ");
            diaPagamento[0] = input.nextInt();
        }
        else if (opc == 2) {
            System.out.println("\nPagamento Bi-semanal\n" +
                    "(1) Segunda\n" +
                    "(2) Terça\n" +
                    "(3) Quarta\n" +
                    "(4) Quinta\n" +
                    "(5) Sexta\n" +
                    "(0) Cancelar");

            int esc = input.nextInt();
            if (esc < 1 || esc > 5) {
                System.out.println("Criação de nova agenda cancelada");
                return;
            }

            System.out.println("\n(1) Primeira e Terceira Semana\n(2) Segunda e Quarta Semana");
            int semana = input.nextInt();

            if (esc == 1) { // Segundas
                if (semana == 1) {
                    diaPagamento[0] = 1;
                    diaPagamento[1] = 11;
                }
                else if (semana == 2) {
                    diaPagamento[0] = 6;
                    diaPagamento[1] = 16;
                }
            }
            else if (esc == 2) { // Terças
                if (semana == 1) {
                    diaPagamento[0] = 2;
                    diaPagamento[1] = 12;
                }
                else if (semana == 2) {
                    diaPagamento[0] = 7;
                    diaPagamento[1] = 17;
                }
            }
            else if (esc == 3) { // Quartas
                if (semana == 1) {
                    diaPagamento[0] = 3;
                    diaPagamento[1] = 13;
                }
                else if (semana == 2) {
                    diaPagamento[0] = 8;
                    diaPagamento[1] = 18;
                }
            }
            else if (esc == 4) { // Quintas
                if (semana == 1) {
                    diaPagamento[0] = 4;
                    diaPagamento[1] = 14;
                }
                else if (semana == 2) {
                    diaPagamento[0] = 9;
                    diaPagamento[1] = 19;
                }
            }
            else { // Sextas
                if (semana == 1) {
                    diaPagamento[0] = 5;
                    diaPagamento[1] = 15;
                }
                else if (semana == 2) {
                    diaPagamento[0] = 10;
                    diaPagamento[1] = 20;
                }
            }
        }
        else if (opc == 3) {
            System.out.println("\nPagamento Semanal\n" +
                    "(1) Segundas\n" +
                    "(2) Terças\n" +
                    "(3) Quartas\n" +
                    "(4) Quintas\n" +
                    "(5) Sextas\n" +
                    "(0) Cancelar");

            int esc = input.nextInt();
            if (esc == 1) { // Segundas
                diaPagamento[0] = 1;
                diaPagamento[1] = 6;
                diaPagamento[2] = 11;
                diaPagamento[3] = 16;
            }
            else if (esc == 2) {    // Terças
                diaPagamento[0] = 2;
                diaPagamento[1] = 7;
                diaPagamento[2] = 12;
                diaPagamento[3] = 17;
            }
            else if (esc == 3) {    // Quartass
                diaPagamento[0] = 3;
                diaPagamento[1] = 8;
                diaPagamento[2] = 13;
                diaPagamento[3] = 18;
            }
            else if (esc == 4) {    // Quintas
                diaPagamento[0] = 4;
                diaPagamento[1] = 9;
                diaPagamento[2] = 14;
                diaPagamento[3] = 19;
            }
            else if (esc == 5) {    // Sextas
                diaPagamento[0] = 5;
                diaPagamento[1] = 10;
                diaPagamento[2] = 15;
                diaPagamento[3] = 20;
            }
            else {
                System.out.println("Criação de nova agenda cancelada");
            }
        }
        else {
            System.out.println("Criação de nova agenda cancelada");
            return;
        }

        for (Agenda i: Empresa.agenda) {
            if (Arrays.equals(i.getDiaPagamento(), diaPagamento)) {
                System.out.println("Já existe a agenda " + i.getTipo() + " com os mesmos dias\nCriação de nova agenda cancelada");
                return;
            }
        }

        Agenda novaAgenda = new Agenda(tipo, diaPagamento);
        if (Empresa.agenda.contains(novaAgenda)) {
            System.out.println("Já existe uma agenda igual a que você está criando\nCriação de nova agenda cancelada");
        }
        else {
            Empresa.agenda.add(novaAgenda);
            System.out.println("Agenda " + tipo + " criada");
        }

    }

    public void selecionarAgenda() {

        Scanner input = new Scanner(System.in);

        System.out.print("Selecionar Agenda de Pagamento\nNúmero do empregado: ");
        int numero = input.nextInt();

        for (Empregado i: Empresa.empregados) {
            if (i.getNumero() == numero) {
                System.out.println("\nSelecionar agenda:");
                int agenda = 1;
                for (Agenda j: Empresa.agenda) {
                    System.out.println("("+ agenda + ") " + j.getTipo());
                    agenda++;
                }
                System.out.println("(0) Cancelar");

                agenda = input.nextInt();
                if (agenda < 1 || agenda > Empresa.agenda.size()) {
                    System.out.println("Seleção de agenda cancelada");
                }
                else {
                    i.setPagamento(Empresa.agenda.get(agenda-1));
                    System.out.println("Agenda selecionada");
                }
                return;
            }
        }

        System.out.println("Não foi encontrado empregado com esse número");
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int[] getDiaPagamento() {
        return diaPagamento;
    }

    public void setDiaPagamento(int[] diaPagamento) {
        this.diaPagamento = diaPagamento;
    }
}
