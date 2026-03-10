public class Main {

    public static void main(String[] args) {

        System.out.println("Sistema de Monitoramento de Energia Residencial \n");

        SmartHome casa = new SmartHome(10);

     
        casa.cadastrarComodo("Sala", 5);
        casa.cadastrarComodo("Quarto", 3);
        casa.cadastrarComodo("Cozinha", 4);
        casa.cadastrarComodo("Banheiro", 3);

        System.out.println("\n Tentativa de cadastrar cômodo duplicado! Não iremos cadastrar novamente.");
        casa.cadastrarComodo("Sala", 5);

        Comodo sala = casa.buscarComodo("Sala");
        Comodo quarto = casa.buscarComodo("Quarto");
        Comodo cozinha = casa.buscarComodo("Cozinha");
        Comodo banheiro = casa.buscarComodo("Banheiro");

        System.out.println();
        Dispositivo tv = new Dispositivo("TV - Douglas - Polo Niterói", 0.15f);
        Dispositivo ventilador = new Dispositivo("Ventilador - Douglas - Polo Niterói", 0.06f);
        sala.adicionarDispositivo(tv);
        sala.adicionarDispositivo(ventilador);
        System.out.println("\n Tentativa de registrar duplicado!");
        sala.adicionarDispositivo(tv);

        System.out.println();
        Dispositivo arCondicionado = new Dispositivo("Ar Condicionado", 0.8f);
        Dispositivo luminaria = new Dispositivo("Luminária", 0.01f);
        quarto.adicionarDispositivo(arCondicionado);
        quarto.adicionarDispositivo(luminaria);
        System.out.println("\n Tentativa de registrar duplicado!");
        quarto.adicionarDispositivo(arCondicionado);

        Dispositivo geladeira = new Dispositivo("Geladeira", 0.04f);
        Dispositivo microondas = new Dispositivo("Micro-ondas", 0.1f);
        cozinha.adicionarDispositivo(geladeira);
        cozinha.adicionarDispositivo(microondas);
        System.out.println("\n Tentativa de registrar duplicado!");
        cozinha.adicionarDispositivo(geladeira);

        Dispositivo chuveiro = new Dispositivo("Chuveiro Elétrico", 0.5f);
        Dispositivo secador = new Dispositivo("Secador de Cabelo", 0.2f);
        banheiro.adicionarDispositivo(chuveiro);
        banheiro.adicionarDispositivo(secador);
        System.out.println("\n Tentativa de registrar duplicado!");
        banheiro.adicionarDispositivo(chuveiro);

        System.out.println();
        tv.ligar();
        arCondicionado.ligar();
        geladeira.ligar();
        chuveiro.ligar();

        System.out.println();
        tv.registrarTempoUso(4);
        arCondicionado.registrarTempoUso(8);
        geladeira.registrarTempoUso(12);
        chuveiro.registrarTempoUso(0.5f);
        tv.registrarTempoUso(2);

        System.out.println("\n Tentativa de registrar tempo de uso em um dispositivo desligado!");
        ventilador.registrarTempoUso(2);

        
        System.out.println("\n Tentativa de registrar tempo inválido (negativo)!");
        tv.registrarTempoUso(-1);

        
        casa.exibirResumoDia();

        
        casa.listarMaioresConsumidores();

        System.out.println("\n Iniciando Menu Interativo:");
        MenuSmartHome menu = new MenuSmartHome(casa);
        menu.exibirMenu();
    }
}