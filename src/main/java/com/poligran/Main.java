package com.poligran;

import com.poligran.service.GenerateInfoFiles;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try{

            GenerateInfoFiles.initialConfig();
            /// Inicializacion de configuracion inicial.

            System.out.println("Los elementos iniciales fueron generados con exito");

        }catch (IOException e){
            System.err.println(e.toString());
        }


    }

}


