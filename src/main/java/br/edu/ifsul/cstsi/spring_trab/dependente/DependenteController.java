package br.edu.ifsul.cstsi.spring_trab.dependente;

import br.edu.ifsul.cstsi.spring_trab.socio.SocioService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;
@Controller
public class DependenteController {

    private static final Scanner input = new Scanner(System.in);
    private static DependenteService DependenteService;
    private static SocioService SocioService;

    public DependenteController(SocioService SocioService, DependenteService DependenteService) {
        DependenteController.SocioService = SocioService;
        DependenteController.DependenteService = DependenteService;
    }
    public static void main(String[] args) {

        int opcao;
        do {
            System.out.print("\n\"-------  MENU Dependente -------\"");
            System.out.print(
                    """
    
                        1. Inserir novo Dependente
                        2. Atualizar um Dependente
                        3. Excluir um Dependente
                        4. Listar todos os Dependente
                        5. Buscar Dependente pelo código
                        6. Buscar Dependente pelo nome
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectdependente();
                case 5 -> selectdependenteById();
                case 6 -> selectdependenteByNome();
                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opção 1
    private static void inserir() {
        Dependente dependente = new Dependente();
        System.out.println("\n++++++ Cadastro de novo Dependente: ++++++");
        System.out.print("Digite o nome do Dependente: ");
        dependente.setNomeDep(input.nextLine());
        System.out.print("\nDigite o Parentesco do Dependente: ");
        dependente.setParentesco(input.nextLine());
        System.out.print("\nDigite o Email do Dependente: ");
        dependente.setEmailDep(input.nextLine());
        System.out.print("\nDigite a codigo do Dependente: ");
        dependente.setSocioByCartaoSocio(SocioService.getSocioById(input.nextLong()));
        System.out.println("Dependente salvo com sucesso:" + DependenteService.insert(dependente));
    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar um Dependente ++++++");
        Dependente dependente;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do Dependente (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                dependente = DependenteService.getDependenteById(codigo);
                if (dependente == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + dependente.getNomeDep());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite o novo nome do dependente: ");
                        dependente.setNomeDep(input.nextLine());
                    }
                    System.out.println("Parentesco: " + dependente.getParentesco());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo Parentesco do dependente: ");
                        dependente.setParentesco(input.nextLine());
                    }
                    System.out.println("Email: " + dependente.getEmailDep());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo endereco do dependente: ");
                        dependente.setEmailDep(input.nextLine());
                    }
                    System.out.println("Socio: " + dependente.getSocioByCartaoSocio().getId());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo socio do dependente: ");
                        dependente.setSocioByCartaoSocio(SocioService.getSocioById(input.nextLong()));
                    }

                    if(DependenteService.update(dependente) != null) {
                        System.out.println("Dependente atualizado com sucesso. " + DependenteService.getDependenteById(dependente.getCartaoDep()));
                    } else {
                        System.out.println("Não foi possível atualizar este dependente. Por favor, contate o administrador.");
                    }

                    opcao = 1;
                }
            }
        } while (opcao != 0);
    }
    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir um Dependente ++++++");
        Dependente dependente;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do Dependente (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                dependente = DependenteService.getDependenteById(codigo);
                if (dependente == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(dependente);
                    System.out.print("Excluir este Dependente? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Tem certeza disso? (0-sim/1-não) ");
                        input.nextLine();
                        DependenteService.delete(dependente.getCartaoDep());
                        System.out.println("cliente excluído com sucesso:" + dependente);
                    }
                }
            } else {
                System.out.println("Digite um código válido!!!");
            }
        } while (opcao != 0);
    }

    //opção 4
    private static void selectdependente() {
        System.out.println("\nLista de dependente cadastrados no banco de dados:\n" + DependenteService.getDependente());
    }

    //opção 5
    private static void selectdependenteById() {
        System.out.print("\nDigite o código do dependente: ");
        Dependente dependente = DependenteService.getDependenteById(input.nextLong());
        input.nextLine();
        if (dependente != null) {
            System.out.println(dependente);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 6
    private static void selectdependenteByNome() {
        System.out.print("Digite o nome do dependente: ");
        String nome = input.next();
        System.out.println("Chave de pesquisa: " + nome);
        List<Dependente> dependentes = DependenteService.getDependenteByNome(nome + "%");
        if (dependentes.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + nome);
        } else {
            System.out.println(dependentes);
        }
    }
}
