public class barberia {

    //Número total de sillas de espera en la barbería
    private int nSillasEspera;

    //Número de sillas de espera actualmente ocupadas
    private int nSillasEsperaOcupadas = 0;

    //Indica si la silla del barbero está ocupada por un cliente
    private boolean sillaBarberoOcupada = false;

    //Indica si el corte de pelo ha terminado
    private boolean finCorte = false;

    //Indica si el barbero está dormido
    private boolean barberoDormido = false;

    //Constructor
    public barberia(int nSillasEspera) {
        this.nSillasEspera = nSillasEspera;
    }

    //Método para que un cliente entre a la barbería
    public synchronized boolean entrar(int clienteId) throws InterruptedException {
        //Si no hay sillas libres, el cliente se va sin cortarse el pelo
        if (nSillasEsperaOcupadas == nSillasEspera) {
            System.out.println("Cliente " + clienteId + " se va sin cortarse el pelo");
            return false;
        } else {
            //Si hay sillas libres, el cliente se sienta en la silla de espera
            nSillasEsperaOcupadas++;
            System.out.println("Cliente " + clienteId + " se sienta en la silla de espera");

            //Espera mientras la silla del barbero esté ocupada
            while (sillaBarberoOcupada) {
                wait();
            }

            //Desocupa la silla de espera
            nSillasEsperaOcupadas--;

            //Ocupa la silla del barbero y marca que el corte de pelo no ha terminado
            sillaBarberoOcupada = true;
            finCorte = false;

            //Si el barbero está dormido, lo despierta
            if (barberoDormido) {
                System.out.println(clienteId + " despierta al barbero!!");
                notifyAll();
            }

            //Espera a que el barbero le corte el pelo
            System.out.println("Cliente " + clienteId + " en la silla del barbero");
            while (!finCorte) {
                wait();
            }

            //Desocupa la silla del barbero y permite que el siguiente cliente entre
            sillaBarberoOcupada = false;
            notifyAll();

            System.out.println("Cliente " + clienteId + " se pudo cortar el pelo");
            return true;
        }
    }

    //Método para que el barbero espere a que llegue un cliente
    public synchronized void esperarCliente() throws InterruptedException {
        //El barbero espera a que llegue un cliente
        barberoDormido = true;
        while (!sillaBarberoOcupada) {
            System.out.println("Barbero mimiendo");
            wait();
        }
        barberoDormido = false;
        System.out.println("Barbero cortando el pelo");
    }

    //Método para indicar que el corte de pelo ha terminado
    public synchronized void acabarCorte() {
        finCorte = true;
        System.out.println("Barbero termina de cortar el pelo");
        notifyAll();
    }
}