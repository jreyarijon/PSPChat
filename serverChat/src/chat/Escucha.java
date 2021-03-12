/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author oracle
 */
public class Escucha extends Thread {

    Socket clientes;
    Socket enviarMns;
    ObjectInputStream recibirDatos;
    ObjectOutputStream enviarDatos;

    public Escucha(String str) {
        super(str);
    }

    @Override
    public void run() {

        try {
            Ventana v = new Ventana();
            v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            v.gettArea().append("Preparando servidor...\n");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("192.168.0.27", 4444);
            serverSocket.bind(addr);

            v.gettArea().append("Aceptando conexiones...\n");
            while (true) {
                clientes = serverSocket.accept();

                recibirDatos = new ObjectInputStream(clientes.getInputStream());
                Datos datosRecibidos = (Datos) recibirDatos.readObject();

                Datos d = new Datos(datosRecibidos.getNickname(), datosRecibidos.getIP(), datosRecibidos.getPuerto(), datosRecibidos.getMns());
                v.gettArea().append(d.getNickname() + " => " + d.getMns() + "\n");

                System.out.println(d.getIP() + "     " + d.getPuerto());

                enviarMns = new Socket();
                InetSocketAddress addr1 = new InetSocketAddress(d.getIP(), d.getPuerto());
                enviarMns.connect(addr1);

                enviarDatos = new ObjectOutputStream(enviarMns.getOutputStream());

                enviarDatos.writeObject(datosRecibidos);

            }
        } catch (IOException ex) {
            Logger.getLogger(Escucha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escucha.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                enviarDatos.close();
                recibirDatos.close();
                enviarMns.close();
                clientes.close();
            } catch (IOException ex) {
                Logger.getLogger(Escucha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
