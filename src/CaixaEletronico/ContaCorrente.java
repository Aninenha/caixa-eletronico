package CaixaEletronico;

class ContaCorrente extends Conta {
    double limite;
    double taxa;

    public ContaCorrente(Cliente titular, double saldo, double limite) {
        super(titular, saldo);
        this.limite = limite;
        this.taxa = 15;
    }

    public void isThisEnough(double valor){
        if(valor+taxa<getSaldo()){
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    @Override
    public void sacar(double valor) {

        validarValor(valor);

        if (valor >= getSaldo() + limite) {
            System.out.println("Saque não realizado. Limite insuficiente.");
        } else {
            subtrair(valor);
            addInHistorico(2);
            System.out.println("Saque realizado.");
            getSaldo();
        }
    }

    @Override
    public void subtrair(double valor) {
        isThisEnough(valor);
        setSaldo(getSaldo()-valor);
    }

    @Override
    public void transferir(double valor, Conta destino) {
        if(destino==null){
            throw new IllegalArgumentException("Conta destino não existe.");
        }
        if (this == destino){
            throw new IllegalArgumentException("Não pode transferir para si mesmo!");
        }
        validarValor(valor);
        if (valor>=getSaldo()+limite) {
            subtrair(valor);
            destino.adicionar(valor);
            addInHistorico(4, destino);
            destino.addInHistorico(6, this);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

}
