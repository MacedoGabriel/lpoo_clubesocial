package br.edu.ifsul.cstsi.spring_trab.categoria;

import org.springframework.stereotype.Controller;

import java.util.Scanner;


@Controller
public class CategoriaController   {

        private static final Scanner input = new Scanner(System.in);

        private static CategoriaService CategoriaService;
        public CategoriaController(CategoriaService CategoriaService) {
            CategoriaController.CategoriaService = CategoriaService;
        }

        public static void main(String[] args) {

            int opcao;
            do {
                System.out.print("\n\"-------  MENU Categoria -------\"");
                System.out.print(
                        """
                            
                            1. Cadastrar Categoria
                            2. Atualizar Categoria
                            3. Deletar Categoria
                            4. Mostrar Categorias
                            5. Pesquisar categoria por Codigo
                            Opção (Zero p/sair):\s""");
                opcao = input.nextInt();
                input.nextLine();
                switch (opcao) {
                    case 1 -> inserir();
                    case 2 -> atualizar();
                    case 3 -> excluir();
                    case 4 -> selectcategorias();
                    case 5 -> selectcategoriaById();
                    default -> {
                        if (opcao != 0) System.out.println("Opção inválida.");
                    }
                }
            } while(opcao != 0);
        }

        private static void inserir(){
            Categoria categoria = new Categoria();
            System.out.println("\n++++++ Cadastro da nova Categoria ++++++");
            System.out.print("Digite o nome do categoria: ");
            categoria.setDesCat(input.nextLine());
            System.out.println("categoria salva com sucesso:" + CategoriaService.insert(categoria));
        }

        private static void atualizar() {
            System.out.println("\n++++++ Alterar uma categoria ++++++");
            Categoria categoria;
            int opcao = 0;
            do {
                System.out.print("\nDigite o código da categoria (Zero p/sair): ");
                long codigo = input.nextLong();
                input.nextLine();
                if (codigo == 0) {
                    opcao = 0;
                } else {
                    categoria = CategoriaService.getCategortiaById(codigo);
                    if (categoria == null) {
                        System.out.println("Código inválido.");
                    } else {
                        System.out.println("Nome: " + categoria.getDesCat());
                        System.out.print("Alterar? (0-sim/1-não) ");
                        if(input.nextInt() == 0){
                            input.nextLine();
                            System.out.println("Digite o novo nome da categoria: ");
                            categoria.setDesCat(input.nextLine());
                        }
                        if(CategoriaService.update(categoria) != null) {
                            System.out.println("Categoria atualizada com sucesso. " + CategoriaService.getCategortiaById(categoria.getId()));
                        } else {
                            System.out.println("Não foi possível atualizar esta categoria. Por favor, contate o administrador.");
                        }

                        opcao = 1;
                    }
                }
            } while (opcao != 0);
        }

        private static void excluir() {
            System.out.println("\n++++++ Excluir um categoria ++++++");
            Categoria categoria;
            int opcao = 0;
            do {
                System.out.print("\nDigite o código da categoria (Zero p/sair): ");
                long codigo = input.nextLong();
                input.nextLine();
                if (codigo == 0) {
                    opcao = 0;
                } else if(codigo > 0){
                    categoria = CategoriaService.getCategortiaById(codigo);
                    if (categoria == null) {
                        System.out.println("Código inválido.");
                    } else {
                        System.out.println(categoria);
                        System.out.print("Excluir esta Categoria? (0-sim/1-não) ");
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.print("Tem certeza disso? (0-sim/1-não) ");
                            input.nextLine();
                            CategoriaService.delete(categoria.getId());
                            System.out.println("Categoria excluído com sucesso:" + categoria);
                        }
                    }
                } else {
                    System.out.println("Digite um código válido!!!");
                }
            } while (opcao != 0);
        }

        private static void selectcategorias() {
            System.out.println("\nLista de categoria cadastrados no banco de dados:\n" + CategoriaService.getCategoria());
        }

        private static void selectcategoriaById() {
            System.out.print("\nDigite o código da categoria: ");
            Categoria categoria = CategoriaService.getCategortiaById(input.nextLong());
            input.nextLine();
            if (categoria != null) {
                System.out.println(categoria);
            } else {
                System.out.println("Código não localizado.");
            }
        }


    }

