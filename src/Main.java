import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static final int MULTIPLICADOR = 1;

    public static ArrayList<Item> produtosBons = new ArrayList<Item>();
    public static ArrayList<Item> produtosRuins = new ArrayList<Item>();
    static long startTime;

    static Thread shutdownHook = new Thread(Main::imprimirEstatistica);

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        // creating buffer queue
        Esteira q = new Esteira();
        // starting consumer thread
        new Consumidor(q, "Consumidor 1");
        new Consumidor(q, "Consumidor 2");
        // starting producer thread
        new Produtor(q, "Produtor 1");
        new Produtor(q, "Produtor 2");
        new Produtor(q, "Produtor 3");

        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    static void imprimirEstatistica() {
        long endTime = System.currentTimeMillis();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Produtos Bons:");
        int totalEntrada = 0;
        int totalSaida = 0;
        for (Item i : produtosBons) {
            System.out.println(i);
            totalEntrada += i.tempoEntrada;
            totalSaida += i.tempoSaida;
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Produtos Ruins:");
        produtosRuins.forEach(t -> System.out.println(t));
        System.out.println("----------------------------------------------------");
        //Contabiliza e imprime tempo de execução
        System.out.println("Tempo total de entrada: " + totalEntrada);
        System.out.println("Tempo total de saida: " + totalSaida);
        long totalTime = endTime - startTime;
        System.out.println("Tempo total de execução: " + totalTime + "ms");

    }

    //Realiza triagem
    public static void controleQualidade(Item item) {
        if (item.qualidade >= 5)
            produtosBons.add(item);
        else
            produtosRuins.add(item);
    }

    //Gera numero aleatorio dentro ed intervalo
    public static int aleatorio(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    //Pausa a thread por tempo determinado
    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
