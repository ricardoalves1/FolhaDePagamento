import java.util.ArrayList;

public class Empregado {

    private int numero;
    private String nome;
    private String endereco;
    private String tipoEmpregado;
    private float salario;
    private int[] data = new int[2];
    private ArrayList<CartaoDePonto> cartaoDePonto = new ArrayList<>();
    private int comissao;
    private ArrayList<Venda> venda = new ArrayList<>();
    private String metodoPagamento;
    private boolean sindicato;
    private Agenda pagamento;

    public Empregado(int numero, String nome, String endereco, String tipoEmpregado, float salario, int[] data, String metodoPagamento, boolean sindicato, Agenda agenda) {
        this.numero = numero;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoEmpregado = tipoEmpregado;
        this.salario = salario;
        this.data = data;
        this.metodoPagamento = metodoPagamento;
        this.sindicato = sindicato;
        this.pagamento = agenda;
    }

    public Empregado() {}

    public void rodarFolha() {

        System.out.println("------ Folha de pagamento ------\n");

        for (Empregado i: Empresa.empregados) {
            for (int diaPagamento: i.getPagamento().getDiaPagamento()) {
                if (Empresa.dia == diaPagamento) { // Dia de pagamento na agenda do empregado

                    float salario = 0;

                    if (i.getData()[1] < Empresa.mes) {
                        i.setData(new int[]{i.getData()[0] - 20, i.getData()[1]});
                    }

                    // Verificar taxas
                    if (i.getSindicato()) {
                        for (MembroSindicato membro: Sindicato.empregado) {
                            if (membro.getEmpregado() == i) {
                                // Taxa sindial
                                salario -= membro.getTaxaSindical();

                                // Taxa de serviço
                                if (membro.getServicoSindicato()) {
                                    salario -= Sindicato.taxaServico;
                                }
                            }
                        }
                    }

                    // Verificar horas (horista)
                    if (i.getTipoEmpregado().equals("Horista")) {

                        int horaExtra = 0;
                        for (CartaoDePonto horas: i.getCartaoDePonto()) {
                            salario += i.getSalario() * horas.getHorasTrabalhadas();
                            if(horas.getHorasTrabalhadas() > 8) {
                                horaExtra += horas.getHorasTrabalhadas() - 8;
                            }
                        }
                        i.getCartaoDePonto().removeAll(i.getCartaoDePonto());
                        salario += horaExtra * (0.5 * i.getSalario());

                    }   // Comissão
                    else if (i.getTipoEmpregado().equals("Comissionado")) {

                        float comissao = 0;
                        salario += i.getSalario() * ((float) (diaPagamento - i.getData()[0]) / 10);
                        for (Venda vendas: i.getVenda()) {
                            comissao += vendas.getValor() * i.getComissao() / 100;
                        }
                        i.getVenda().removeAll(i.getVenda());
                        salario += comissao;

                    }
                    else {
                        // Salário mensal pelos dias trabalhados
                        salario += i.getSalario() * ((float) (diaPagamento - i.getData()[0]) / 20);
                    }


                    System.out.print("Empregado " + i.getNome() + ": R$ " + salario);
                    if (i.getMetodoPagamento().equals("correios")) {
                        System.out.println(" \t(receber cheque pelos correios)");
                    }
                    else if (i.getMetodoPagamento().equals("maos")) {
                        System.out.println(" \t(receber cheque em mãos)");
                    }
                    else {
                        System.out.println(" \t(depósito em conta bancária)");
                    }

                    i.setData(new int[]{Empresa.dia, Empresa.mes});

                }
            }
        }

        System.out.println("\n--------------------------------");
        Empresa.dia += 1;
        if (Empresa.dia == 21) {
            Empresa.dia = 1;
            Empresa.mes += 1;
        }
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoEmpregado() {
        return tipoEmpregado;
    }

    public void setTipoEmpregado(String tipo) {
        this.tipoEmpregado = tipo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public ArrayList<CartaoDePonto> getCartaoDePonto() {
        return cartaoDePonto;
    }

    public void setCartaoDePonto(CartaoDePonto cartaoDePonto) {
        this.cartaoDePonto.add(cartaoDePonto);
    }

    public int getComissao() {
        return comissao;
    }

    public void setComissao(int comissao) {
        this.comissao = comissao;
    }

    public ArrayList<Venda> getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda.add(venda);
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public boolean getSindicato() {
        return sindicato;
    }

    public void setSindicato(boolean sindicato) {
        this.sindicato = sindicato;
    }

    public Agenda getPagamento() {
        return pagamento;
    }

    public void setPagamento(Agenda pagamento) {
        this.pagamento = pagamento;
    }
}
