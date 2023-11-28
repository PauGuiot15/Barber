public class barbero extends Thread {

    //Referencia a la barbería donde trabaja el barbero
    private barberia laBarberia;

    //Constructor
    public barbero(barberia laBarberia) {
        this.laBarberia = laBarberia;
    }

    //Método run que se ejecuta cuando se inicia el hilo del barbero
    public void run() {
        //Bucle infinito
        while (true) {
            try {
                //El barbero espera a que llegue un cliente
                laBarberia.esperarCliente();

                //Simulación del tiempo que tarda en cortar el pelo (5 segundos)
                Thread.sleep(5000);

                //Indicar a la barbería que el corte de pelo ha terminado
                laBarberia.acabarCorte();

                //Descansar un poco antes de atender al siguiente cliente
                Thread.sleep(2000);

            } catch (InterruptedException e) {
            }
        }
    }
}
