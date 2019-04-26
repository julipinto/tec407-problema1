/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.execoes.PilotoNaoExisteException;
import servidor.model.Jogador;

/**
 *
 * @author Teeu Guima
 */
public class ControllerDeTratamento extends Thread {

    private ServidorFacade sf;

    private final ObjectOutputStream os;
    private final ObjectInputStream is;
    private final Socket recebido;

    public ControllerDeTratamento(Socket s, ObjectOutputStream os, ObjectInputStream is) {
        this.recebido = s;
        this.os = os;
        this.is = is;
        this.sf = ServidorFacade.getInstance();
    }

    public void trataMensagem() {

    }

    @Override
    public void run() {
        while (true) {
            try {

                // Ask user what he wants 
                Mensagem msg = (Mensagem) is.readObject();
                if (msg.getCommand().CadCarro == Command.CadCarro) {
                    String[] dados = (String[]) msg.getObject();
                    boolean status = sf.cadastrarCarro(dados[0], dados[1], dados[2]);
                    if (status == true) {
                        this.os.writeUTF("Carro Cadastrado");
                        this.os.flush();
                    }

                } else if (msg.getCommand().IterarCarros == Command.IterarCarros) {
                    Object arrayCarros = (Object) sf.getListaDeCarros();
                    if(arrayCarros != null){
                        this.os.writeObject(arrayCarros);
                        this.os.flush();
                    }

                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            break;
        }

        try {
            // closing resources 
            this.is.close();
            this.os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
