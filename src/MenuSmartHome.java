import java.util.Scanner;

public class MenuSmartHome {
    Scanner scanner = new Scanner(System.in);
    SmartHome smartHome;

    MenuSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nMenu Smart Home:");
            System.out.println("1. Cadastrar cômodo");
            System.out.println("2. Adicionar dispositivo a um cômodo");
            System.out.println("3. Ligar dispositivo");
            System.out.println("4. Desligar dispositivo");
            System.out.println("5. Registrar tempo de uso de um dispositivo ligado");
            System.out.println("6. Exibir resumo do dia");
            System.out.println("7. Listar maiores consumidores");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarComodo();
                    break;
                case 2:
                    adicionarDispositivo();
                    break;
                case 3:
                    ligarDispositivo();
                    break;
                case 4:
                    desligarDispositivo();
                    break;
                case 5:
                    registrarTempoUso();
                    break;
                case 6:
                    smartHome.exibirResumoDia();
                    break;
                case 7:
                    smartHome.listarMaioresConsumidores();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 8);
    }
    
    void cadastrarComodo() {
        System.out.print("Nome do cômodo: ");
        String nome = scanner.nextLine();
        System.out.print("Capacidade máxima de dispositivos: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();
        smartHome.cadastrarComodo(nome, capacidade);
    }
    
    void adicionarDispositivo() {
        System.out.print("Nome do cômodo: ");
        String nomeComodo = scanner.nextLine();
        System.out.print("Nome do dispositivo: ");
        String nomeDispositivo = scanner.nextLine();
        System.out.print("Consumo por hora (kW): ");
        float consumo = scanner.nextFloat();
        scanner.nextLine();
        
        Comodo comodo = smartHome.buscarComodo(nomeComodo);
        if (comodo != null) {
            Dispositivo dispositivo = new Dispositivo(nomeDispositivo, consumo);
            comodo.adicionarDispositivo(dispositivo);
        }
    }
    
    void ligarDispositivo() {
        System.out.print("Nome do cômodo: ");
        String nomeComodo = scanner.nextLine();
        System.out.print("Nome do dispositivo: ");
        String nomeDispositivo = scanner.nextLine();
        
        Comodo comodo = smartHome.buscarComodo(nomeComodo);
        if (comodo != null) {
            Dispositivo dispositivo = comodo.buscarDispositivo(nomeDispositivo);
            if (dispositivo != null) {
                dispositivo.ligar();
            }
        }
    }
    
    void desligarDispositivo() {
        System.out.print("Nome do cômodo: ");
        String nomeComodo = scanner.nextLine();
        System.out.print("Nome do dispositivo: ");
        String nomeDispositivo = scanner.nextLine();
        
        Comodo comodo = smartHome.buscarComodo(nomeComodo);
        if (comodo != null) {
            Dispositivo dispositivo = comodo.buscarDispositivo(nomeDispositivo);
            if (dispositivo != null) {
                dispositivo.desligar();
            }
        }
    }
    
    void registrarTempoUso() {
        System.out.print("Nome do cômodo: ");
        String nomeComodo = scanner.nextLine();
        System.out.print("Nome do dispositivo: ");
        String nomeDispositivo = scanner.nextLine();
        System.out.print("Tempo de uso (horas): ");
        float tempoUso = scanner.nextFloat();
        scanner.nextLine();
        
        Comodo comodo = smartHome.buscarComodo(nomeComodo);
        if (comodo != null) {
            Dispositivo dispositivo = comodo.buscarDispositivo(nomeDispositivo);
            if (dispositivo != null) {
                dispositivo.registrarTempoUso(tempoUso);
            }
        }
    }
}
