public class MembroSindicato {

    private int id;
    private boolean servicoSindicato;
    private float taxaSindical;
    private Empregado empregado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (Sindicato.indice.contains(id)) {
            System.out.println("Id já existente\nNão foi possível alterar o id do empregado");
            return;
        }
        this.id = id;
    }

    public boolean getServicoSindicato() {
        return servicoSindicato;
    }

    public void setServicoSindicato(boolean servicoSindicato) {
        this.servicoSindicato = servicoSindicato;
    }

    public float getTaxaSindical() {
        return taxaSindical;
    }

    public void setTaxaSindical(float taxaSindical) {
        this.taxaSindical = taxaSindical;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }
}
