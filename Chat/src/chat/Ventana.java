/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Josemolamazo
 */
public class Ventana extends JFrame implements ActionListener {

    private JTextField mns;
    private JButton bEnviar;
    private JPanel panel;
    private JTextArea chat;
    Datos d;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    ObjectOutputStream w;
    Socket socket;

    public Ventana() {

        setBounds(100, 100, 330, 430);

        panel = new JPanel();
        chat = new JTextArea(18, 25);
        mns = new JTextField(25);
        bEnviar = new JButton("Enviar");
        bEnviar.addActionListener(this);

        panel.add(chat);
        panel.add(mns);
        panel.add(bEnviar);
        add(panel);
        setVisible(true);
    }

    public JTextArea getChat() {
        return chat;
    }

    public void setChat(JTextArea chat) {
        this.chat = chat;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        d = new Datos("Juan", "192.168.0.19", 6666, mns.getText());
        mns.setText("");
        try {
            socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("192.168.0.27", 4444);
            socket.connect(addr);

            w = new ObjectOutputStream(socket.getOutputStream());

            chat.append(d.getNickname() + " => " + d.getMns() + "\n");
            w.writeObject(d);

        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                w.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
