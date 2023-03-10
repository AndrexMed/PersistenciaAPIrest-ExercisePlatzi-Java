package com.mycompany.catsapp;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author giova
 */
public class GatosService {

    public static void verGatos() throws IOException {
        //Traemos los datos de la app
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
        Response response = client.newCall(request).execute();

        
        String elJson = response.body().string();
        
        //cortar los corchetes
        elJson = elJson.substring(0,elJson.length());
        elJson = elJson.substring(1,elJson.length()-1);
        
        //Crear un objeto de la clase Gson
        Gson gson = new Gson ();
        Gatos gatos = gson.fromJson(elJson, Gatos.class);
        
        //Redimensionar la img en caso de necesitarlo
        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);
            
            ImageIcon fondoGato = new ImageIcon(image);
            
            if (fondoGato.getIconWidth() > 800) {
                //Redimensionamos
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon (modificada);
            }
            
            String menu = "Opciones: \n"
                    + "1. Ver otra imagen \n"
                    + "2. Favorito \n"
                    + "3. Volver \n";
            
            String botones[] = {"Ver otra imagen","Favorito","Volver"};
            String id_gato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null,menu,id_gato,JOptionPane.INFORMATION_MESSAGE, fondoGato, botones,botones[0]);
          
            int seleccion = -1;
             //Validamos la opcion seleccionada
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    seleccion = i;
                }
            }
            
            switch (seleccion) {
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                default:
                    break;
            }
            
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("********* ERROR ********");
        }
        
    }
    
    public static void favoritoGato(Gatos gato){
        
    }
}
