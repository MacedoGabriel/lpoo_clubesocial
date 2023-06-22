package br.edu.ifsul.cstsi.spring_trab.mensalidade;

import br.edu.ifsul.cstsi.spring_trab.socio.SocioService;
import org.springframework.stereotype.Controller;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

@Controller
public class MensalidadeController {
    private static final Scanner input = new Scanner(System.in);
    private static SocioService SocioService;
    private static MensalidadeService MensalidadeService;

    public MensalidadeController(SocioService SocioService, MensalidadeService MensalidadeService) {
        MensalidadeController.SocioService = SocioService;
        MensalidadeController.MensalidadeService = MensalidadeService;
    }
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.print("\n\"-------  MENU Mensalidade -------\"");
            System.out.print(
                    """
    
                        1. Inserir Nova Mensalidade
                        2. Atualizar uma Mensalidade
                        3. Excluir uma Mensalidade
                        4. Quitar Mensalidade
                        5. Listar todas as Mensalidades
                        6. Buscar Mensalidade pelo código
                        7. Buscar Mensalidade pelo socio
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> quitar();
                case 5 -> selectmensalidade();
                case 6 -> selectmensalidadeById();
                case 7 -> selectmensalidadeBySocio();
                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opção 1
    private static void inserir() {
        Mensalidade mensalidade = new Mensalidade();
        System.out.println("\n++++++ Cadastro de nova Mensalidade ++++++");
        System.out.println("Digite o codigo do Socio");
        mensalidade.setSocioByCartaoSocio(SocioService.getSocioById(input.nextLong()));
        input.nextLine();
        System.out.print("Digite a data da Mensalidade(YYYY-MM-DD): ");
        mensalidade.setDataMens(LocalDate.parse(input.nextLine()));
        System.out.print("\nDigite o Valor da mensalidade: ");
        mensalidade.setValorMens(input.nextDouble());
        System.out.print("\nDigite o Juros da mensalidade em %: ");
        mensalidade.setJurosMens(input.nextDouble());
        System.out.print("\nDigite o Valor pago da mensalidade em : ");
        mensalidade.setValorPago(input.nextDouble());
        System.out.println("\nEsta mensalidade ja foi quitada (0-sim/1-não)");
        if(input.nextInt() == 0) {
            input.nextLine();
            mensalidade.setQuitMens((byte) 1);
            System.out.print("Digite a data que foi paga a Mensalidade(YYYY-MM-DD): ");
            mensalidade.setDataMens(LocalDate.parse(input.nextLine()));
        }else {
            mensalidade.setQuitMens((byte) 0);
        }
        System.out.println("Mensalidade salva com sucesso:" + MensalidadeService.insert(mensalidade));
    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar uma Mensalidade ++++++");
        Mensalidade mensalidade;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código da Mensalidade (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                mensalidade = MensalidadeService.getMensalidadeById(codigo);
                if (mensalidade == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("data mensalidade: " + mensalidade.getDataMens());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite a nova data(YYYY-MM-DD): ");
                        mensalidade.setDataMens(LocalDate.parse(input.nextLine()));
                    }
                    System.out.println("valor: " + mensalidade.getValorMens());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo Valor: ");
                        mensalidade.setValorMens(input.nextDouble());
                    }
                    System.out.println("juros: " + mensalidade.getJurosMens() + "%");
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo juros em %: ");
                        mensalidade.setJurosMens(input.nextDouble());
                    }
                    System.out.println("valor pago: " + mensalidade.getValorPago());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o total pago: ");
                        mensalidade.setValorPago(input.nextDouble());
                    }
                    if(mensalidade.getQuitMens() == 0){
                        System.out.println("Mensalidade em aberto");
                    }else{
                        System.out.println("Mensalidade quitada");
                    }
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite se a mesalidade foi quitada (0-sim/1-não): ");
                        mensalidade.setQuitMens(input.nextByte());
                    }
                    if(mensalidade.getQuitMens() == 1){
                        System.out.println("data de pagamento mensalidade: " + mensalidade.getDataPgtoMens());
                        System.out.print("Alterar? (0-sim/1-não) ");
                        if(input.nextInt() == 0){
                            input.nextLine();
                            System.out.println("Digite a nova data de pagamento(YYYY-MM-DD): ");
                            mensalidade.setDataPgtoMens(LocalDate.parse(input.nextLine()));
                        }
                    }

                    if(MensalidadeService.update(mensalidade) != null) {
                        System.out.println("Mensalidade atualizado com sucesso. " + MensalidadeService.getMensalidadeById(mensalidade.getNumeroMens()));
                    } else {
                        System.out.println("Não foi possível atualizar este Mensalidade. Por favor, contate o administrador.");
                    }

                    opcao = 1;
                }
            }
        } while (opcao != 0);
    }
    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir uma Mensalidade ++++++");
        Mensalidade mensalidade;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código da mensalidade (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                mensalidade = MensalidadeService.getMensalidadeById(codigo);
                if (mensalidade == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(mensalidade);
                    System.out.print("Excluir esta Mensalidade? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Tem certeza disso? (0-sim/1-não) ");
                        input.nextLine();
                        MensalidadeService.delete(mensalidade.getNumeroMens());
                        System.out.println("Mensalidade excluída com sucesso:" + mensalidade);
                    }
                }
            } else {
                System.out.println("Digite um código válido!!!");
            }

        } while (opcao != 0);
    }

    //opção 4
    private static void quitar() {
        System.out.println("\n++++++ Quitar uma Mensalidade ++++++");
        Mensalidade mensalidade;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código da mensalidade (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                mensalidade = MensalidadeService.getMensalidadeById(codigo);
                if (mensalidade == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(mensalidade);
                    System.out.print("Quitar esta Mensalidade? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        mensalidade.setQuitMens((byte) 1);
                        System.out.print("Digite a data que foi paga a Mensalidade(YYYY-MM-DD): ");
                        mensalidade.setDataPgtoMens(LocalDate.parse(input.nextLine()));
                        System.out.println("Digite o valor Pago: ");
                        mensalidade.setValorPago(input.nextDouble());
                        MensalidadeService.update(mensalidade);
                        System.out.println("Mensalidade quitada com sucesso:" + mensalidade);
                    }
                }
            } else {
                System.out.println("Digite um código válido!!!");
            }

        } while (opcao != 0);
    }

    //opção 5
    private static void selectmensalidade() {
        System.out.println("\nLista de mensalidades cadastradas no banco de dados:\n" + MensalidadeService.getMensalidade());
    }

    //opção 6
    private static void selectmensalidadeById() {
        System.out.print("\nDigite o código do mensalidade: ");
        Mensalidade mensalidade = MensalidadeService.getMensalidadeById(input.nextLong());
        input.nextLine();
        if (mensalidade != null) {
            System.out.println(mensalidade);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 7
    private static void selectmensalidadeBySocio() {
        System.out.print("Digite o Codigo do socio: ");
        Long codigo = input.nextLong();
        System.out.println("Chave de pesquisa: " + codigo);
        List<Mensalidade> mensalidades = MensalidadeService.getMensalidadeByIdSocio(codigo);
        if (mensalidades.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + codigo);
        } else {
            System.out.println(mensalidades);
        }
    }
}
