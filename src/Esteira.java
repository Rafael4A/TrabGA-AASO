import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class Esteira {
    //Semaforo Cons inicia com 0 pois não tem o que consumir
    static Semaphore Cons = new Semaphore(0);
    //Semaforo Prod incia com 3 para que todos produtores possam produzir
    static Semaphore Prod = new Semaphore(3);
    Queue<Item> fila = new LinkedList<>();

    // Obtem do buffer
    void get(String nome, int espera) {
        try {
            //Obtem permissao para obter item
            Cons.acquire();
        } catch (InterruptedException e) {
            System.out.println("Processo Interrompido!!");
        }

        // Consome item
        Item currentItem = fila.remove();
        System.out.println(nome + " obteve: " + currentItem.toString() + " em " + espera + " milissegundos");
        currentItem.setTempoSaida(espera);
        Main.controleQualidade(currentItem);

        // Libera producao apos consumir
        Prod.release();
    }

    // Insere no buffer
    void put(Item item, String nome) {
        try {
            // Obtem permissao
            Prod.acquire();
        } catch (InterruptedException e) {
            System.out.println("Processo Interrompido!!");
        }

        // Produz item
        //this.item = item;
        fila.add(item);

        System.out.println(nome + " produziu: " + item.toString());

        // Libera para consumo
        Cons.release();
    }
}