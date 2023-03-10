package com.mycompany.catsapp;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author giova
 */
public class Main {

    public static void main(String[] args) throws IOException {
        
        int opcionMenu = -1;
        String botones[] = {" 1. Ver gatos", "2. Salir"};

        do {
            //Main Menu
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos java", "Menu Principal", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
            
            //Validamos la opcion seleccionada
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    opcionMenu = i;
                }
            }

            switch (opcionMenu) {
                case 0:
                    GatosService.verGatos();
                    break;
                default:
                    break;
            }

        } while (opcionMenu != 1);
    }
}
