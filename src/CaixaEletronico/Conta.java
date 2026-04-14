package CaixaEletronico;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

abstract class Conta {
    private String titular;
    private double saldo;
    private Cliente cliente;
    int tipo;
    private List<String> historico = new ArrayList<>();

    Conta(Cliente cliente, double saldo) {
        this.titular = cliente.nome;
        setSaldo(saldo);
        addInHistorico(1);
    }

    public void depositar(double valor) {
        validarValor(valor);
        adicionar(valor);
        addInHistorico(3);
        System.out.println("Depósito realizado.");
        getSaldo();
    }

    public void sacar(double valor) {

        validarValor(valor);

        if (valor > saldo) {
            System.out.println("Saque não realizado. Saldo insuficiente.");
        } else {
            subtrair(valor);
            addInHistorico(2);
            System.out.println("Saque realizado.");
            getSaldo();
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void getHistorico() {
        for ( String h : historico){
            System.out.println(h);
        }
    }

    public String formatar(double valor) {
        String stDouble;

        DecimalFormat df = new DecimalFormat("0.00");
        stDouble = df.format(valor);

        return stDouble;
    }

    public void transferir(double valor, Conta destino) {
        if(destino==null){
            throw new IllegalArgumentException("Conta destino não existe.");
        }
        if (this == destino){
            throw new IllegalArgumentException("Não pode transferir para si mesmo!");
        }
        validarValor(valor);
            if (valor>getSaldo()) {
            subtrair(valor);
            destino.adicionar(valor);
            addInHistorico(4, destino);
            destino.addInHistorico(6, this);
        } else {
                throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    public void addInHistorico(int tipo, Conta destino) {
        String st="";
        switch (tipo) {
            case (4):
                st = "\nTransferência realizada para " + destino + ". Saldo atual: "+ formatar(getSaldo());
                break;
            case(6):
                st = "\nTransferencia recebida de "+ destino+". Saldo atual: "+ formatar(getSaldo());
                break;
            default:
                addInHistorico(tipo);
        }
        historico.add(st);
    }

    public void addInHistorico(int tipo, double taxa, double rend) {
        String st = "";
        switch (tipo) {
            case (5):
                st = "\nAplicação de rendimentos: \nTaxa:" + taxa + "\nRendimento: " + formatar(rend) + "\nSaldo atual: " + formatar(saldo);
                break;
            default:
                addInHistorico(tipo);
        }
        historico.add(st);
    }

    public void addInHistorico(int tipo) {
        String st = "";
        switch (tipo) {
            case (1):
                st = "\nConta criada! Titular: " + titular + ". Saldo inicial: " + formatar(saldo) + ".";
                break;
            case (2):
                st = "\nSaque realizado! Saldo atual: " + formatar(saldo) + ".";
                break;
            case (3):
                st = "\nDepósito realizado! Saldo atual: " + formatar(saldo) + ".";
                break;

            default:
                break;
        }
        historico.add(st);
    }

    public void subtrair(double valor) {
        saldo -= valor;
    }

    public void adicionar(double valor) {
        saldo += valor;
    }

    public void validarValor(double valor){
        if (valor<=0){
            throw new IllegalArgumentException("Valor precisa ser acima de 0");
        }
    }


}
