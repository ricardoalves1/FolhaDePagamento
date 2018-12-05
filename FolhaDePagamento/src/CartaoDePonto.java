import java.util.Scanner;

public class CartaoDePonto {

    private int horasTrabalhadas;

    public CartaoDePonto() {}

    public CartaoDePonto(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public void lancarCartaoDePonto() {

        Scanner input = new Scanner(System.in);

        System.out.print("Lançar cartão de ponto\nNúmero do empregado: ");
        int numero = input.nextInt();

        for (Empregado i: Empresa.empregados) {
            if (i.getNumero() == numero) {
                if (i.getTipoEmpregado().equals("Horista")) {

                    System.out.print("Quantidade de horas trabalhadas: ");
                    CartaoDePonto novoCartao = new CartaoDePonto(input.nextInt());
                    i.setCartaoDePonto(novoCartao);

                    System.out.println("Cartão de ponto lançado");
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

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

}
