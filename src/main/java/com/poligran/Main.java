package com.poligran;

import com.poligran.service.GenerateInfoFiles;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try{

            /// Initial config
            GenerateInfoFiles.initialConfig();

            System.out.println("The initial element was created");

        }catch (IOException e){
            System.err.println(e.toString());
        }


    }

}


