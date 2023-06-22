package br.edu.ifsul.cstsi.spring_trab;

import br.edu.ifsul.cstsi.spring_trab.categoria.CategoriaController;
import br.edu.ifsul.cstsi.spring_trab.dependente.DependenteController;
import br.edu.ifsul.cstsi.spring_trab.mensalidade.Mensalidade;
import br.edu.ifsul.cstsi.spring_trab.mensalidade.MensalidadeController;
import br.edu.ifsul.cstsi.spring_trab.socio.SocioController;
import org.aspectj.bridge.Message;


import java.util.Scanner;

public class HomeController {

    private static final Scanner input = new Scanner(System.in);

    public static void main() {

        int opcao;
        do {
            System.out.print("\n\"-------  HOME -------\"");
            System.out.print(
                    """
                        
                        1. Socio Controller
                        2. Dependente Controller
                        3. Mensalidade Controller
                        4. Categoria Controller
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> SocioController.main(null);
                case 2 -> DependenteController.main(null);
                case 3 -> MensalidadeController.main(null);
                case 4 -> CategoriaController.main(null);
                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while(opcao != 0) ;
        System.out.println("\n\n!!!!!!!! Fim da aplicação !!!!!!!!");
        input.close(); //libera o recurso
    }

}//fim classe
