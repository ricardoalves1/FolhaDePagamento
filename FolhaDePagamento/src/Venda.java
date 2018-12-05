import java.util.Scanner;

public class Venda {

    private float valor;
    private int[] data;

    public Venda() {}

    public Venda(float valor, int[] data) {
        this.valor = valor;
        this.data = data;
    }

    public void lancarResultadoDeVenda() {

        Scanner input = new Scanner(System.in);

        System.out.print("Lançar resultado de venda\nNúmero do empregado: ");
        int numero = input.nextInt();

        for (Empregado i: Empresa.empregados) {
            if (i.getNumero() == numero) {
                if (i.getTipoEmpregado().equals("Comissionado")) {
                    System.out.print("Valor da venda: ");
                    float valor = input.nextFloat();
                    int[] data = {Empresa.dia, Empresa.mes};

                    Venda venda = new Venda(valor, data);
                    i.setVenda(venda);
                    System.out.println("Resultado de venda lançado");
                    return;
                }
                else {
                    System.out.println("Operação inválida");
                    return;
                }
            }
        }

        System.out.println("Não foi encontrado empregado com esse número");

    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
