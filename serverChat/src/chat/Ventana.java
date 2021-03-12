/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import javax.swing.*;

/**
 *
 * @author oracle
 */
public class Ventana extends JFrame {

    private JTextArea tArea;
    private JPanel panel;

    public Ventana() {

        setBounds(1000, 250, 300, 400);

        panel = new JPanel();
        tArea = new JTextArea(30,25);

        panel.add(tArea);
        add(panel);

        setVisible(true);

    }

    public JTextArea gettArea() {
        return tArea;
    }

    public void settArea(JTextArea tArea) {
        this.tArea = tArea;
    }
}
