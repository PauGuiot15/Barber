
public class laBarberia {

    public static void main(String[] args) {

        // Número de sillas disponibles en la barbería
        final int nSillas = 4;

        // Número total de clientes que visitarán la barbería
        final int nClientes = 10;

        // Crear una instancia de la clase barberia con el número de sillas especificado
        barberia laBarberia = new barberia(nSillas);

        // Crear una instancia de la clase barbero y pasarle la barbería como parámetro
        barbero elBarbero = new barbero(laBarberia);

        // Crear un array para almacenar instancias de la clase cliente
        cliente[] losClientes = new cliente[10];

        // Iniciar el hilo del barbero
        elBarbero.start();

        //iniciar hilos para los clientes
        for (int i = 0; i < nClientes; i++) {
            // Crear un cliente con la barbería y un identificador único
            losClientes[i] = new cliente(laBarberia, i);

            // Iniciar el hilo del cliente
            losClientes[i].start();
        }
    }
}
