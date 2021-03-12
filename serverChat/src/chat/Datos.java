/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.Serializable;

/**
 *
 * @author oracle
 */
public class Datos implements Serializable {

    private String nickname;
    private String IP;
    private int puerto;
    private String mns;

    public Datos() {
    }

    public Datos(String nickname, String IP, int puerto, String mns) {
        this.nickname = nickname;
        this.IP = IP;
        this.puerto = puerto;
        this.mns = mns;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getMns() {
        return mns;
    }

    public void setMns(String mns) {
        this.mns = mns;
    }
}
