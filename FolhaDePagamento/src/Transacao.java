import java.util.ArrayList;
import java.util.Stack;

public class Transacao {

    // Empresa
    private ArrayList<Empregado> empregadoEmpresa = new ArrayList<>();
    private ArrayList<Integer> indiceEmpresa;
    private int dia;
    private int mes;
    // Sindicato
    private ArrayList<MembroSindicato> empregadoSindicato = new ArrayList<>();
    private ArrayList<Integer> indiceSindicato;
    private float taxaServico;

    public static Stack<Transacao> undo = new Stack<>();
    public static Stack<Transacao> redo = new Stack<>();

    private Transacao(ArrayList<Empregado> empregadoEmpresa, ArrayList<Integer> indiceEmpresa, int dia, int mes, ArrayList<MembroSindicato> empregadoSindicato, ArrayList<Integer> indiceSindicato, float taxaServico) {
        this.empregadoEmpresa = empregadoEmpresa;
        this.indiceEmpresa = indiceEmpresa;
        this.dia = dia;
        this.mes = mes;
        this.empregadoSindicato = empregadoSindicato;
        this.indiceSindicato = indiceSindicato;
        this.taxaServico = taxaServico;
    }

    public static void novaTransacao() {

        while (!redo.empty()) {
            redo.pop();
        }
        undoRedo(1);

    }

    public static void desfazer() {

        if (Transacao.undo.empty()) {
            System.out.println("Não é possível desfazer");
            return;
        }

        Transacao undo = Transacao.undo.pop();
        undoRedo(2);

        Empresa.empregados = (undo.getEmpregadoEmpresa());
        Sindicato.empregado = (undo.getEmpregadoSindicato());
        Empresa.indice = (undo.getIndiceEmpresa());
        Sindicato.indice = (undo.getIndiceEmpresa());
        Empresa.dia = undo.getDia();
        Empresa.mes = undo.getMes();
        Sindicato.taxaServico = undo.getTaxaServico();

    }

    public static void refazer() {

        if (Transacao.redo.empty()) {
            System.out.println("Não é possível refazer");
            return;
        }

        Transacao redo = Transacao.redo.pop();
        undoRedo(1);

        Empresa.empregados = (redo.getEmpregadoEmpresa());
        Empresa.indice = (redo.getIndiceEmpresa());
        Empresa.dia = redo.getDia();
        Empresa.mes = redo.getMes();
        Sindicato.empregado = (redo.getEmpregadoSindicato());
        Sindicato.indice = (redo.getIndiceEmpresa());
        Sindicato.taxaServico = redo.getTaxaServico();

    }

    public static void undoRedo(int opc) {

        ArrayList<Empregado> empregadoEmpresa = new ArrayList<>();
        for (Empregado i: Empresa.empregados) {
            Empregado empregado = new Empregado(i.getNumero(), i.getNome(), i.getEndereco(), i.getTipoEmpregado(), i.getSalario(), i.getData(), i.getMetodoPagamento(), i.getSindicato(), i.getPagamento());
            empregadoEmpresa.add(empregado);

            for (CartaoDePonto j: i.getCartaoDePonto()) {
                CartaoDePonto cartaoDePonto = new CartaoDePonto(j.getHorasTrabalhadas());
                empregado.setCartaoDePonto(cartaoDePonto);
            }

            empregado.setComissao(i.getComissao());
            for (Venda j: i.getVenda()) {
                Venda vendas = new Venda(j.getValor(), j.getData());
                empregado.setVenda(vendas);
            }

        }
        ArrayList<Integer> indiceEmpresa = new ArrayList<>();
        for (Integer i: Empresa.indice) {
            indiceEmpresa.add(i);
        }
        ArrayList<MembroSindicato> empregadoSindicato = new ArrayList<>();
        for (MembroSindicato i: Sindicato.empregado) {
            empregadoSindicato.add(new MembroSindicato(i.getId(), i.getServicoSindicato(), i.getTaxaSindical(), i.getEmpregado()));
        }
        ArrayList<Integer> indiceSindicato = new ArrayList<>();
        for (Integer i: Sindicato.indice) {
            indiceSindicato.add(i);
        }

        if (opc == 1) {
            undo.push(new Transacao(empregadoEmpresa, indiceEmpresa, Empresa.dia, Empresa.mes, empregadoSindicato, indiceSindicato, Sindicato.taxaServico));
        }
        else {
            redo.push(new Transacao(empregadoEmpresa, indiceEmpresa, Empresa.dia, Empresa.mes, empregadoSindicato, indiceSindicato, Sindicato.taxaServico));
        }

    }

    public ArrayList<Empregado> getEmpregadoEmpresa() {
        return empregadoEmpresa;
    }

    public void setEmpregadoEmpresa(ArrayList<Empregado> empregadoEmpresa) {
        this.empregadoEmpresa = empregadoEmpresa;
    }

    public ArrayList<Integer> getIndiceEmpresa() {
        return indiceEmpresa;
    }

    public void setIndiceEmpresa(ArrayList<Integer> indiceEmpresa) {
        this.indiceEmpresa = indiceEmpresa;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public ArrayList<MembroSindicato> getEmpregadoSindicato() {
        return empregadoSindicato;
    }

    public void setEmpregadoSindicato(ArrayList<MembroSindicato> empregadoSindicato) {
        this.empregadoSindicato = empregadoSindicato;
    }

    public ArrayList<Integer> getIndiceSindicato() {
        return indiceSindicato;
    }

    public void setIndiceSindicato(ArrayList<Integer> indiceSindicato) {
        this.indiceSindicato = indiceSindicato;
    }

    public float getTaxaServico() {
        return taxaServico;
    }

    public void setTaxaServico(float taxaServico) {
        this.taxaServico = taxaServico;
    }
}
