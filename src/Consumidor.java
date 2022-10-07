class Consumidor implements Runnable {
    Esteira esteira;
    String nome;

    Consumidor(Esteira esteira, String nome) {
        this.esteira = esteira;
        this.nome = nome;
        new Thread(this, "Consumidor").start();
    }

    // Executa ao instancia objeto
    public void run() {
        for (int i = 0; i < Main.MULTIPLICADOR * 3; i++) {
            //Define tempo aleatorio para consumo
            final int espera = Main.aleatorio(500, 1500);
            //Espera
            Main.delay(espera);
            //Obtem produto
            esteira.get(nome, espera);
        }
    }
}