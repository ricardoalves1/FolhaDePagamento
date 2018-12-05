import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sindicato {

    public static ArrayList<MembroSindicato> empregado = new ArrayList<>();
    public static float taxaServico;
    public static ArrayList<Integer> indice = new ArrayList<>();


    public void addMembroSindicato(Empregado empregado, boolean servico) {

        MembroSindicato membro = new MembroSindicato();
        membro.setEmpregado(empregado);
        membro.setServicoSindicato(servico);

        int id = idMembro();
        indice.add(id);
        Collections.sort(indice);
        membro.setId(id);

        Sindicato.empregado.add(membro);

    }

    public void removerMembroSindicato(Empregado empregado) {

        for (MembroSindicato i: Sindicato.empregado) {
            if (i.getEmpregado() == empregado) {
                Sindicato.empregado.remove(i);
                return;
            }
        }

    }

    public void lancarTaxaDeServico() {

        Scanner input = new Scanner(System.in);

        System.out.print("Lançar taxa de serviço\nValor da taxa de serviço: ");
        taxaServico = input.nextFloat();

        System.out.println("Taxa de serviço lançada");
    }

    private int idMembro() {
        int aux = 0;
        for (int i : indice) {
            if (i != aux){
                return aux;
            }
            aux++;
        }
        return aux;
    }

}
