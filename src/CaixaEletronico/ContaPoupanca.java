package CaixaEletronico;

class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente titular, double saldo) {
        super(titular, saldo);
    }

    public void aplicarRendimento(double taxa) {
        double rendimento = getSaldo() * taxa;
        setSaldo((getSaldo()+rendimento));
        addInHistorico(5, taxa, rendimento);
    }
}
