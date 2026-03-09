public class Main {
    public static void main(String[] args) {
        SmartHome smartHome = new SmartHome("Minha Casa Inteligente");
        MenuSmartHome menu = new MenuSmartHome();

        // Exemplo de uso
        smartHome.cadastrarComodo("Sala");
        smartHome.cadastrarComodo("Cozinha");
    }
}
