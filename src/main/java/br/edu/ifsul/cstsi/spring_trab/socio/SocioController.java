package br.edu.ifsul.cstsi.spring_trab.socio;


import br.edu.ifsul.cstsi.spring_trab.categoria.CategoriaService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class SocioController {
    private static final Scanner input = new Scanner(System.in);
    private static SocioService SocioService;
    private static CategoriaService CategoriaService;

    public SocioController(SocioService SocioService, CategoriaService CategoriaService) {
        SocioController.SocioService = SocioService;
        SocioController.CategoriaService = CategoriaService;
    }
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.print("\n\"-------  MENU Socio -------\"");
            System.out.print(
                    """
    
                        1. Inserir novo Socio
                        2. Atualizar um Socio
                        3. Excluir um Socio
                        4. Listar todos os Socio
                        5. Buscar Socio pelo código
                        6. Buscar Socio pelo nome
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> atualizar();
                case 3 -> excluir();
                case 4 -> selectsocio();
                case 5 -> selectsocioById();
                case 6 -> selectsocioByNome();
                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);
    }

    //opção 1
    private static void inserir() {
        Socio socio = new Socio();
        System.out.println("\n++++++ Cadastro de novo Socio ++++++");
        System.out.print("Digite o nome do Socio: ");
        socio.setNomeSocio(input.nextLine());
        System.out.print("\nDigite o endereco do socio: ");
        socio.setEndSocio(input.nextLine());
        System.out.print("\nDigite o Telefone do socio: ");
        socio.setTelSocio(input.nextLine());
        System.out.print("\nDigite o Email do socio: ");
        socio.setEmailSocial(input.nextLine());
        System.out.print("\nDigite a categoria do socio: ");
        socio.setCategoriaByCategoria(CategoriaService.getCategortiaById(input.nextLong()));
        System.out.println("Socio salvo com sucesso:" + SocioService.insert(socio));
    }

    //opção 2
    private static void atualizar() {
        System.out.println("\n++++++ Alterar um Socio ++++++");
        Socio socio;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do Socio (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else {
                socio = SocioService.getSocioById(codigo);
                if (socio == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + socio.getNomeSocio());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.println("Digite o novo nome do Socio: ");
                        socio.setNomeSocio(input.nextLine());
                    }
                    System.out.println("endereco: " + socio.getEndSocio());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo endereco do socio: ");
                        socio.setEndSocio(input.nextLine());
                    }
                    System.out.println("Telefone: " + socio.getTelSocio());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo Telefone do socio: ");
                        socio.setTelSocio(input.nextLine());
                    }
                    System.out.println("Email: " + socio.getEmailSocial());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite o novo endereco do socio: ");
                        socio.setEmailSocial(input.nextLine());
                    }
                    System.out.println("Categoria: " + socio.getCategoriaByCategoria().getDesCat());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                        input.nextLine();
                        System.out.print("Digite a nova categoria do socio: ");
                        socio.setCategoriaByCategoria(CategoriaService.getCategortiaById(input.nextLong()));
                    }

                    if(SocioService.update(socio) != null) {
                        System.out.println("Socio atualizado com sucesso. " + SocioService.getSocioById(socio.getId()));
                    } else {
                        System.out.println("Não foi possível atualizar este Socio. Por favor, contate o administrador.");
                    }

                    opcao = 1;
                }
            }
        } while (opcao != 0);
    }
    //opção 3
    private static void excluir() {
        System.out.println("\n++++++ Excluir um Socio ++++++");
        Socio socio;
        int opcao = 0;
        do {
            System.out.print("\nDigite o código do Socio (Zero p/sair): ");
            long codigo = input.nextLong();
            input.nextLine();
            if (codigo == 0) {
                opcao = 0;
            } else if(codigo > 0){
                socio = SocioService.getSocioById(codigo);
                if (socio == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println(socio);
                    System.out.print("Excluir este Socio? (0-sim/1-não) ");
                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Tem certeza disso? (0-sim/1-não) ");
                        input.nextLine();
                        SocioService.delete(socio.getId());
                        System.out.println("Socio excluído com sucesso:" + socio);
                    }
                }
            } else {
                System.out.println("Digite um código válido!!!");
            }
        } while (opcao != 0);
    }

    //opção 4
    private static void selectsocio() {
        System.out.println("\nLista de clientes cadastrados no banco de dados:\n" + SocioService.getSocio());
    }

    //opção 5
    private static void selectsocioById() {
        System.out.print("\nDigite o código do Socio: ");
        Socio socio = SocioService.getSocioById(input.nextLong());
        input.nextLine();
        if (socio != null) {
            System.out.println(socio);
        } else {
            System.out.println("Código não localizado.");
        }
    }

    //opção 6
    private static void selectsocioByNome() {
        System.out.print("Digite o nome do Socio: ");
        String nome = input.next();
        System.out.println("Chave de pesquisa: " + nome);
        List<Socio> socios = SocioService.getSocioByNome(nome + "%");
        if (socios.isEmpty()) {
            System.out.println("Não há registros correspondentes para: " + nome);
        } else {
            System.out.println(socios);
        }
    }


}
