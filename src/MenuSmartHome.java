import java.util.Scanner;
import java.util.Locale;

public class MenuSmartHome {
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    SmartHome smartHome;

    MenuSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    
    private int lerInteiroComErro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Erro: Digite um número inteiro válido.");
                scanner.nextLine();
            }
        }
    }
    
    private float lerFloatComErro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextFloat();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Erro: Digite um número decimal válido (use ponto como separador).");
                scanner.nextLine();
            }
        }
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
            System.out.println("8. Listar capacidades de cômodos");
            System.out.println("9. Sair");
            opcao = lerInteiroComErro("Escolha uma opção: ");
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
                    listarCapacidadesComodos();
                    break;
                case 9:
                    System.out.println("Saindo. Obrigado por usar o Smart Home!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 9);
    }
    
    void cadastrarComodo() {
        System.out.print("Nome do cômodo: ");
        String nome = scanner.nextLine();
        int capacidade = lerInteiroComErro("Capacidade máxima de dispositivos: ");
        scanner.nextLine();
        smartHome.cadastrarComodo(nome, capacidade);
    }
    
    void adicionarDispositivo() {
        System.out.print("Nome do cômodo: ");
        String nomeComodo = scanner.nextLine();
        System.out.print("Nome do dispositivo: ");
        String nomeDispositivo = scanner.nextLine();
        float consumo = lerFloatComErro("Consumo por hora (kW): ");
        scanner.nextLine();
        
        Comodo comodo = smartHome.buscarComodo(nomeComodo);
        if (comodo != null) {
            Dispositivo dispositivo = new Dispositivo(nomeDispositivo, consumo);
            boolean adicionado = comodo.adicionarDispositivo(dispositivo);
            if (!adicionado) {
                System.out.println("Falha ao adicionar dispositivo. Verifique a capacidade máxima do cômodo.");
            }
        } else {
            System.out.println("Cômodo '" + nomeComodo + "' não encontrado.");
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
        float tempoUso = lerFloatComErro("Tempo de uso (horas): ");
        scanner.nextLine();
        
        Comodo comodo = smartHome.buscarComodo(nomeComodo);
        if (comodo != null) {
            Dispositivo dispositivo = comodo.buscarDispositivo(nomeDispositivo);
            if (dispositivo != null) {
                dispositivo.registrarTempoUso(tempoUso);
            }
        }
    }
    
    void listarCapacidadesComodos() {
        smartHome.listarCapacidadesComodos();
    }
}
