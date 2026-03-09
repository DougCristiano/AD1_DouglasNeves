public class Main {

    public static void main(String[] args) {

        SmartHome casa = new SmartHome(10);

        casa.cadastrarComodo("Sala", 5);
        casa.cadastrarComodo("Quarto", 5);
        casa.cadastrarComodo("Cozinha", 5);
        casa.cadastrarComodo("Banheiro", 5);

        Comodo sala = casa.buscarComodo("Sala");

        Dispositivo tv = new Dispositivo("TV", 0.15f);
        Dispositivo ventilador = new Dispositivo("Ventilador Douglas Polo Niteroi", 0.1f);

        sala.adicionarDispositivo(tv);
        sala.adicionarDispositivo(ventilador);

        sala.buscarDispositivo("TV");

        tv.ligar();
        tv.registrarTempoUso(4);

        System.out.println("Consumo total: " + casa.calcularConsumoTotal());
        System.out.println("Custo total: " + casa.calcularCustoTotal());
    }
}