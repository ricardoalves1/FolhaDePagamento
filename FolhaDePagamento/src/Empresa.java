import java.util.*;

public class Empresa {

    public static ArrayList<Empregado> empregados = new ArrayList<>();
    public static ArrayList<Agenda> agenda = new ArrayList<>();
    public static ArrayList<Integer> indice = new ArrayList<>();
    public static int dia = 1;
    public static int mes = 1;

    public void adicionarEmpregado() {

        Scanner input = new Scanner(System.in);
        System.out.println("\nAdicionar Empregado\nDados do novo empregado:");

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Endereço: ");
        String endereco = input.nextLine();

        int opc, percentual = 0;
        String tipo;
        Agenda agenda;
        while (true) {
            System.out.println("Tipo: (1) Horista \t (2) Assalariado \t (3) Comissionado");
            opc = input.nextInt();
            if (opc == 1) {
                tipo = "Horista";
                agenda = Empresa.agenda.get(0);
                break;
            } else if (opc == 2) {
                tipo = "Assalariado";
                agenda = Empresa.agenda.get(2);
                break;
            } else if (opc == 3) {
                tipo = "Comissionado";
                System.out.print("Comissão:\n\tPercentual de vendas (%): ");
                percentual = input.nextInt();
                agenda = Empresa.agenda.get(1);
                break;
            } else {
                System.out.println("--Opção inválida--");
            }
        }

        System.out.print("Salário: ");
        float salario = input.nextFloat();

        String metodoPagamento;
        while (true) {
            System.out.println("Método de pagamento:\n" +
                    "(1) Receber cheque pelos correios\n" +
                    "(2) Receber cheque em mãos\n" +
                    "(3) Depósito em conta bancária");
            opc = input.nextInt();

            if (opc == 1) {
                metodoPagamento = "correios";
                break;
            }
            else if (opc == 2) {
                metodoPagamento = "maos";
                break;
            }
            else if (opc == 3) {
                metodoPagamento = "conta";
                break;
            }
            else {
                System.out.println("--Opção inválida--");
            }
        }

        System.out.println(nome + " pertence ao sindicato?\n(1) Sim \t (2) Não");
        boolean pertenceSindicato = false, servico = false;

        if (input.nextInt() == 1) {
            pertenceSindicato = true;
            System.out.println("O sindicato oferece algum serviço ao empregado?\n(1) Sim \t (2) Não");
            if (input.nextInt() == 1) {
                servico = true;
            }
        }

        int numero = numeroEmpregado();
        indice.add(numero);
        Collections.sort(indice);

        int[] data = {dia, mes};
        Empregado novoEmpregado = new Empregado(numero, nome, endereco, tipo, salario, data, metodoPagamento, pertenceSindicato, agenda);

        // Comissionado
        if (percentual != 0) novoEmpregado.setComissao(percentual);

        // Adicionar empregado ao sindicato
        if (pertenceSindicato) {
            Sindicato sindicato = new Sindicato();
            sindicato.addMembroSindicato(novoEmpregado, servico);
        }

        empregados.add(novoEmpregado);
        System.out.println("Adicionado novo empregado");

    }

    public void removerEmpregado() {

        Scanner input = new Scanner(System.in);
        System.out.print("\nRemover Empregado\nNúmero do empregado: ");
        int numero = input.nextInt();

        for (Empregado i: empregados) {
            if (i.getNumero() == numero) {
                System.out.println("Deseja mesmo remover o empregado " + i.getNome() + "?\n(1) Sim \t (2) Não");
                if (input.nextInt() == 1) {
                    empregados.remove(i);
                    // Remover do sindicato
                    if (i.getSindicato()) {
                        for (MembroSindicato j : Sindicato.empregado) {
                            if (j.getEmpregado() == i) {
                                Sindicato.empregado.remove(j);
                                break;
                            }
                        }
                    }
                    indice.remove(numero);

                    System.out.println("Empregado removido");
                }
                return;
            }
        }

        System.out.println("Não foi encontrado nenhum empregado com esse número");

    }

    public void alterarEmpregado() {

        Scanner input = new Scanner(System.in);
        System.out.print("Alterar detalhes de um empregado\n\nNúmero do empregado: ");
        int numero = input.nextInt();

        for (Empregado i: empregados) {
            if (i.getNumero() == numero) {
                System.out.println("Alterar detalhes do empregado " + i.getNome() + "?\n(1) Sim \t (2) Não");

                if (input.nextInt() == 1) {

                    System.out.printf("Alterar:\n" +
                            "(1) Nome\n" +
                            "(2) Endereço\n" +
                            "(3) Tipo (Horista, Assalariado, Comissionado)\n" +
                            "(4) Método de pagamento\n" +
                            "(5) %s\n" +
                            "(6) Identificação no sindicato\n" +
                            "(7) Taxa sindical\n" +
                            "(0) Cancelar\n",
                            i.getSindicato() ? "Sair do sindicato" : "Entrar no sindicato");

                    int opc = input.nextInt();

                    if (opc == 1) {
                        System.out.print("Alterar Nome: ");
                        input.nextLine();
                        i.setNome(input.nextLine());
                        System.out.println("Nome alterado");
                    }
                    else if (opc == 2) {
                        System.out.print("Alterar Endereço: ");
                        input.nextLine();
                        i.setEndereco(input.nextLine());
                        System.out.println("Endereço alterado");
                    }
                    else if (opc == 3) {
                        int esc;
                        while (true) {
                            System.out.println("Alterar Tipo: (1) Horista \t (2) Assalariado \t (3) Comissionado");
                            esc = input.nextInt();
                            if (esc == 1) {
                                i.setTipoEmpregado("Horista");
                                i.getVenda().removeAll(i.getVenda());
                                break;
                            } else if (esc == 2) {
                                i.setTipoEmpregado("Assalariado");
                                i.getCartaoDePonto().removeAll(i.getCartaoDePonto());
                                i.getVenda().removeAll(i.getVenda());
                                break;
                            } else if (esc == 3) {
                                i.setTipoEmpregado("Comissionado");
                                System.out.print("Comissão\nPercentual de vendas (%): ");
                                i.setComissao(input.nextInt());
                                i.getCartaoDePonto().removeAll(i.getCartaoDePonto());
                                break;
                            } else {
                                System.out.println("--Opção inválida--");
                            }
                        }


                        System.out.print("Salário: ");
                        i.setSalario(input.nextFloat());

                        System.out.println("Tipo alterado");
                    }
                    else if (opc == 4){
                        int esc;
                        while (true) {
                            System.out.println("Alterar Método de Pagamento:\n" +
                                    "(1) Receber cheque pelos correios\n" +
                                    "(2) Receber cheque em mãos\n" +
                                    "(3) Depósito em conta bancária");
                            esc = input.nextInt();

                            if (esc == 1) {
                                i.setMetodoPagamento("correios");
                                break;
                            }
                            else if (esc == 2) {
                                i.setMetodoPagamento("maos");
                                break;
                            }
                            else if (esc == 3) {
                                i.setMetodoPagamento("conta");
                                break;
                            }
                            else {
                                System.out.println("--Opção inválida--");
                            }
                        }
                        System.out.println("Método de pagamento alterado");
                    }
                    else if (opc == 5) {
                        if (i.getSindicato()) {
                            System.out.println("Deseja remover empregado do sindicato?\n(1) Sim \t (2) Não");
                            if (input.nextInt() == 1) {
                                Sindicato sindicato = new Sindicato();
                                sindicato.removerMembroSindicato(i);
                                i.setSindicato(false);
                                System.out.println("Empregado removido do sindicato");
                            }
                        }
                        else {
                            System.out.println("Deseja adicionar empregado ao sindicato?\n(1) Sim \t (2) Não");
                            if (input.nextInt() == 1) {
                                Sindicato sindicato = new Sindicato();

                                System.out.println("O sindicato oferece algum serviço ao empregado?\n(1) Sim \t (2) Não");
                                boolean servico = false;
                                if (input.nextInt() == 1) {
                                    servico = true;
                                }
                                sindicato.addMembroSindicato(i, servico);
                                i.setSindicato(true);

                                System.out.println("Empregado adicionado ao sindicato");
                            }
                        }
                    }
                    else if (opc == 6) {
                        if (!i.getSindicato()) {
                            System.out.println("O empregado não pertence ao sindicato");
                            return;
                        }
                        System.out.print("Alterar identificação do empregado no sindicato\nId atual: ");
                        for (MembroSindicato j: Sindicato.empregado) {
                            if (j.getEmpregado() == i) {
                                System.out.println(j.getId());
                                System.out.print("Novo id: ");
                                j.setId(input.nextInt());
                                System.out.println("Identificação alterada");
                                break;
                            }
                        }

                    }
                    else if (opc == 7) {
                        if (!i.getSindicato()) {
                            System.out.println("O empregado não pertence ao sindicato");
                            return;
                        }
                        System.out.println("Alterar taxa sindical de " + i.getNome());
                        System.out.print("Nova taxa: ");
                        float taxa = input.nextFloat();
                        if (taxa < 0 || taxa > i.getSalario()) {
                            System.out.println("Valor inválido\nTaxa sindical não alterada");
                            return;
                        }
                        for (MembroSindicato j: Sindicato.empregado) {
                            if (j.getEmpregado() == i) {
                                j.setTaxaSindical(taxa);
                            }
                        }
                        System.out.println("Taxa sindical alterada");
                    }

                }
                return;
            }
        }

        System.out.println("Nenhum empregado foi encontrado com esse número");

    }

    public void listarEmpregados() {
        if (Empresa.empregados.size() == 0) {
            System.out.println("Não há empregados");
            return;
        }
        System.out.println("Empregados:");
        for (Empregado i: Empresa.empregados) {
            System.out.println("(" + i.getNumero() + ") - " + i.getNome());
        }

    }

    private int numeroEmpregado() {
        int ant = 0;
        for (int i : indice) {
            if (i != ant){
                return ant;
            }
            ant++;
        }
        return ant;
    }

}
