package CaixaEletronico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaixaEletronica {
    public static class Main {
        public static void main(String[] args) {

            List<Conta> contas = new ArrayList<>();
            contas.add(new ContaCorrente(new Cliente("Ana"),2000.80, 500));
            contas.add(new ContaPoupanca(new Cliente("Davi"),2000.80));
            contas.add(new ContaCorrente(new Cliente("Miguel"),2000.80, 300));
            contas.add(new ContaPoupanca(new Cliente("Suzana"),2000.80));
            Scanner sc = new Scanner(System.in);

            menuInterativo(contas, 0);

        }

        public static void menuInterativo(List<Conta> conta, int indexAtual){



            Scanner sc = new Scanner(System.in);
            int x = 6;
            double valor;
            while (x != 0) {
                Conta c = conta.get(indexAtual);
                switch (x) {
                    case (1):
                        System.out.println("Digite o valor a depositar:");
                        valor = sc.nextDouble();
                        c.depositar(valor);
                        break;
                    case (2):
                        System.out.println("Digite o valor a sacar:");
                        valor = sc.nextDouble();
                        c.sacar(valor);
                        break;
                    case (3):
                        System.out.println("Valor atual: R$" + c.formatar(c.getSaldo()));
                        break;
                    case(4):
                        c.getHistorico();
                        break;
                    case(5):
                        System.out.println("Digite o valor a transferir:");
                        valor = sc.nextDouble();
                        System.out.println("Escolha a conta destino:");
                        listarContas(conta);
                        int destino = sc.nextInt();
                        c.transferir(valor, conta.get(destino));
                        break;
                    case(6):
                        System.out.println("\n=== CAIXA ELETRONICO ===");
                        System.out.println("Titular: " + c.getTitular());
                        System.out.println("1 - Depositar");
                        System.out.println("2 - Sacar");
                        System.out.println("3 - Consultar saldo");
                        System.out.println("4 - Consultar extrato");
                        System.out.println("5 - Transferir");
                        System.out.println("6 - Voltar ao Menu");
                        System.out.println("0 - Sair");
                        System.out.println("Não é sua conta? digite 9");
                        break;
                    case(9):
                        System.out.println("Escolha a conta:");
                        listarContas(conta);
                        indexAtual=sc.nextInt();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                x = sc.nextInt();
            }
            System.out.println("Obrigada por usar nossos serviços!");
            sc.close();
        }


    }
    public static void listarContas(List<Conta> conta){
        for (int i = 0;i< conta.size();i++){
            System.out.println(i + " - "+conta.get(i).getTitular());
        }
    }
}

