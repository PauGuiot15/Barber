public class cliente extends java.lang.Thread {

    //Barbería donde el cliente intenta cortarse el pelo
    private barberia laBarberia;

    //Identificador único del cliente
    private int clienteId;

    //Indica si el cliente logró o no cortarse el pelo
    private boolean cortePelo = false;

    //Constructor que recibe la referencia a la barbería y el identificador del cliente
    public cliente(barberia laBarberia, int clienteId) {
        this.laBarberia = laBarberia;
        this.clienteId = clienteId;
    }

    //Método run que se ejecuta cuando se inicia el hilo del cliente
    public void run() {
        //El cliente intenta cortarse el pelo continuamente
        while (true) {
            try {
                //El cliente espera un tiempo antes de intentar entrar a la barbería nuevamente
                Thread.sleep(2000);

                //El cliente intenta entrar a la barbería para cortarse el pelo
                cortePelo = laBarberia.entrar(clienteId);

                //Si el cliente logró entrar, realiza la simulación del corte de pelo
                if (cortePelo) {
                    //Espera un tiempo hasta que le crezca el pelo (25 segundos)
                    Thread.sleep(25000);
                } else {
                    //Si no logró entrar, espera un tiempo antes de volver a intentarlo (4 segundos)
                    Thread.sleep(4000);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
