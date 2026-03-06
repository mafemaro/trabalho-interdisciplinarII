package main;
import java.util.List;
import exercicio02.Carro;
import java.util.Scanner;

import DAO.CarroDAO;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CarroDAO dao = new CarroDAO();

        int opcao;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Listar carros");
            System.out.println("2 - Inserir carro");
            System.out.println("3 - Atualizar carro");
            System.out.println("4 - Excluir carro");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    List<Carro> carros = dao.listar();

                    for (Carro c : carros) {
                        System.out.println(
                                c.getPlaca() + " | " +
                                c.getMarca() + " | " +
                                c.getModelo() + " | " +
                                c.getAno() + " | " +
                                c.getCor()
                        );
                    }

                    break;

                case 2:

                    Carro novo = new Carro();

                    System.out.print("Placa: ");
                    novo.setPlaca(sc.nextLine());

                    System.out.print("Marca: ");
                    novo.setMarca(sc.nextLine());

                    System.out.print("Modelo: ");
                    novo.setModelo(sc.nextLine());

                    System.out.print("Ano: ");
                    novo.setAno(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Cor: ");
                    novo.setCor(sc.nextLine());

                    dao.inserir(novo);

                    break;

                case 3:

                    System.out.print("Digite a placa do carro para atualizar: ");
                    String placaAtualizar = sc.nextLine();

                    Carro atualizado = new Carro();
                    atualizado.setPlaca(placaAtualizar);

                    System.out.print("Nova marca: ");
                    atualizado.setMarca(sc.nextLine());

                    System.out.print("Novo modelo: ");
                    atualizado.setModelo(sc.nextLine());

                    System.out.print("Novo ano: ");
                    atualizado.setAno(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Nova cor: ");
                    atualizado.setCor(sc.nextLine());

                    dao.atualizar(atualizado);

                    break;

                case 4:

                    System.out.print("Digite a placa do carro para excluir: ");
                    String placaExcluir = sc.nextLine();

                    dao.excluir(placaExcluir);

                    break;

                case 5:

                    System.out.println("Encerrando programa...");

                    break;

                default:

                    System.out.println("Opção inválida!");

            }

        } while (opcao != 5);

        sc.close();
    }
}