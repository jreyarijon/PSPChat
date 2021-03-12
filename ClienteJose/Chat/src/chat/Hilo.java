package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Josemolamazo
 */
public class Hilo extends Thread {

    Ventana v;
    Datos d = new Datos();
    ObjectInputStream datosOtroCliente;
    Socket socket;

    public Hilo(String str) {
        super(str);
    }

    @Override
    public void run() {

        try {
            Ventana v = new Ventana();
            v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ServerSocket recibirMns = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("192.168.0.19", 6666);
            recibirMns.bind(addr);

            while (true) {
                socket = recibirMns.accept();

                datosOtroCliente = new ObjectInputStream(socket.getInputStream());
                Datos datosRecibidos = (Datos) datosOtroCliente.readObject();

                v.getChat().append(datosRecibidos.getNickname() + " => " + datosRecibidos.getMns() + "\n");

            }
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                datosOtroCliente.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
