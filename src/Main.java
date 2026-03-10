public class Main {

    public static void main(String[] args) {

        System.out.println("=== Sistema de Monitoramento de Energia Residencial ===\n");

        SmartHome casa = new SmartHome(10);

        // Cadastrar 4 cômodos
        casa.cadastrarComodo("Sala", 5);
        casa.cadastrarComodo("Quarto", 5);
        casa.cadastrarComodo("Cozinha", 5);
        casa.cadastrarComodo("Banheiro", 5);

        // Validação: tentativa de cadastrar cômodo com nome duplicado
        System.out.println("\n-- Tentativa de cadastrar cômodo duplicado --");
        casa.cadastrarComodo("Sala", 5);

        Comodo sala = casa.buscarComodo("Sala");
        Comodo quarto = casa.buscarComodo("Quarto");
        Comodo cozinha = casa.buscarComodo("Cozinha");
        Comodo banheiro = casa.buscarComodo("Banheiro");

        // Cadastrar dispositivos (mínimo 2 por cômodo, 8 no total)
        // Pelo menos 2 com nome personalizado: Douglas Neves - Polo Niterói
        System.out.println();
        Dispositivo tv = new Dispositivo("TV - Douglas Neves - Polo Niteroi", 0.15f);
        Dispositivo ventilador = new Dispositivo("Ventilador - Douglas Neves - Polo Niteroi", 0.06f);
        sala.adicionarDispositivo(tv);
        sala.adicionarDispositivo(ventilador);

        // Validação: tentativa de cadastrar dispositivo com nome duplicado no mesmo cômodo
        System.out.println("\n-- Tentativa de cadastrar dispositivo duplicado --");
        sala.adicionarDispositivo(new Dispositivo("TV - Douglas Neves - Polo Niteroi", 0.15f));

        System.out.println();
        Dispositivo arCondicionado = new Dispositivo("Ar Condicionado", 0.8f);
        Dispositivo luminaria = new Dispositivo("Luminária", 0.01f);
        quarto.adicionarDispositivo(arCondicionado);
        quarto.adicionarDispositivo(luminaria);

        Dispositivo geladeira = new Dispositivo("Geladeira", 0.04f);
        Dispositivo microondas = new Dispositivo("Micro-ondas", 0.1f);
        cozinha.adicionarDispositivo(geladeira);
        cozinha.adicionarDispositivo(microondas);

        Dispositivo chuveiro = new Dispositivo("Chuveiro Elétrico", 0.5f);
        Dispositivo secador = new Dispositivo("Secador de Cabelo", 0.2f);
        banheiro.adicionarDispositivo(chuveiro);
        banheiro.adicionarDispositivo(secador);

        // Ligar pelo menos 3 dispositivos (ventilador e luminária ficam desligados)
        System.out.println();
        tv.ligar();
        arCondicionado.ligar();
        geladeira.ligar();
        chuveiro.ligar();

        // Registrar tempo de uso para pelo menos 3 dispositivos
        System.out.println();
        tv.registrarTempoUso(4);
        arCondicionado.registrarTempoUso(8);
        geladeira.registrarTempoUso(12);
        chuveiro.registrarTempoUso(0.5f);

        // Acumular horas no mesmo dispositivo
        tv.registrarTempoUso(2);

        // Validação: tentativa de registrar uso em dispositivo desligado
        System.out.println("\n-- Tentativa de registrar uso em dispositivo desligado --");
        ventilador.registrarTempoUso(2);

        // Validação: tentativa de registrar tempo inválido (negativo)
        System.out.println("\n-- Tentativa de registrar tempo inválido (negativo) --");
        tv.registrarTempoUso(-1);

        // Exibir resumo do dia
        casa.exibirResumoDia();

        // Listar maiores consumidores em ordem decrescente
        casa.listarMaioresConsumidores();

        // Iniciar menu interativo
        System.out.println("\n=== Iniciando Menu Interativo ===");
        MenuSmartHome menu = new MenuSmartHome(casa);
        menu.exibirMenu();
    }
}