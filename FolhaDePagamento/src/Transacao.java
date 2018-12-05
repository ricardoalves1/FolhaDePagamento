import java.util.ArrayList;
import java.util.Stack;

public class Transacao implements Cloneable{

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

    public static void novaTransacao(boolean i) {
        ArrayList<Empregado> empregadoEmpresa = new ArrayList<>();
        empregadoEmpresa.addAll(Empresa.empregados);

        ArrayList<Integer> indiceEmpresa = new ArrayList<>();
        indiceEmpresa.addAll(Empresa.indice);

        ArrayList<MembroSindicato> empregadoSindicato = new ArrayList<>();
        empregadoSindicato.addAll(Sindicato.empregado);

        ArrayList<Integer> indiceSindicato = new ArrayList<>();
        indiceSindicato.addAll(Sindicato.indice);

        undo.push(new Transacao(empregadoEmpresa, indiceEmpresa, Empresa.dia, Empresa.mes, empregadoSindicato, indiceSindicato, Sindicato.taxaServico));

        while (i && !redo.empty()) {
            redo.pop();
        }

    }

    public static void desfazer() {

        if (Transacao.undo.empty()) {
            System.out.println("Não é possível desfazer");
            return;
        }

        Transacao undo = Transacao.undo.pop();
        cloneTransacao(2);

        Sindicato.empregado = undo.getEmpregadoSindicato();
        Sindicato.indice = undo.getIndiceSindicato();
        Sindicato.taxaServico = undo.getTaxaServico();
        Empresa.empregados = undo.getEmpregadoEmpresa();
        Empresa.indice = undo.getIndiceEmpresa();
        Empresa.dia = undo.getDia();
        Empresa.mes = undo.getMes();

    }

    public static void refazer() {

        if (Transacao.redo.empty()) {
            System.out.println("Não é possível refazer");
            return;
        }

        Transacao redo = Transacao.redo.pop();
        cloneTransacao(1);

        Empresa.empregados = redo.getEmpregadoEmpresa();
        Empresa.indice = redo.getIndiceEmpresa();
        Empresa.dia = redo.getDia();
        Empresa.mes = redo.getMes();
        Sindicato.empregado = redo.getEmpregadoSindicato();
        Sindicato.indice = redo.getIndiceEmpresa();
        Sindicato.taxaServico = redo.getTaxaServico();

    }

    public static void cloneTransacao(int i) {

        ArrayList<Empregado> empregadoEmpresa = new ArrayList<>();
        empregadoEmpresa.addAll(Empresa.empregados);

        ArrayList<Integer> indiceEmpresa = new ArrayList<>();
        indiceEmpresa.addAll(Empresa.indice);

        ArrayList<MembroSindicato> empregadoSindicato = new ArrayList<>();
        empregadoSindicato.addAll(Sindicato.empregado);

        ArrayList<Integer> indiceSindicato = new ArrayList<>();
        indiceSindicato.addAll(Sindicato.indice);

        if (i == 1) {
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
