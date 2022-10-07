class Produtor implements Runnable {
    Esteira esteira;
    String nome;

    Produtor(Esteira esteira, String nome) {
        this.esteira = esteira;
        this.nome = nome;
        new Thread(this, "Produtor").start();
    }

    // Executa ao instancia objeto
    public void run() {
        for (int i = 0; i < Main.MULTIPLICADOR * 2; i++) {
            //Define tempo aleatorio para producao
            final int espera = Main.aleatorio(1500, 3000);
            //Cria Produto
            final Item item = new Item(
                    "Produto" + i + " (" + this.nome + ")",
                    Main.aleatorio(0, 10),
                    espera
            );
            //Espera
            Main.delay(espera);
            //Insere produto
            esteira.put(item, this.nome);
        }
    }
}