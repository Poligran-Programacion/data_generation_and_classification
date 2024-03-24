package com.poligran.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * TODO: Utilize FileGeneratorRepository and RouteRepository.
 * It is important to separate responsibilities for this class.
 */
class GenerateInfoFiles {
    public static void main(String[] args) {
        /// TODO: Bad route implementation
        String basePath = "C:\\Users\\drack\\Desktop\\taller de programacion\\eclipse\\GenerateInfoFilesProject\\src\\";

        // Generar archivos de prueba
        createSalesMenFile(5, basePath);
        createProductsFile(10, basePath);
        createSalesManInfoFile(5, basePath);
        System.out.println("Archivos generados exitosamente.");

        // Generar reporte de ventas
        generateSalesReport(5, basePath);
    }

    // Método para generar un archivo de ventas para cada vendedor
    public static void createSalesMenFile(int salesmenCount, String basePath) {
        try {
            for (int i = 1; i <= salesmenCount; i++) {
                FileWriter salesFileWriter = new FileWriter(basePath + "sales_Vendedor" + i + ".txt");
                Random rand = new Random();
                int salesCount = rand.nextInt(10) + 1; // Generar un número aleatorio de ventas entre 1 y 10
                for (int j = 0; j < salesCount; j++) {
                    int productId = rand.nextInt(10) + 1; // Generar un ID de producto aleatorio entre 1 y 10
                    int quantity = rand.nextInt(5) + 1; // Generar una cantidad aleatoria entre 1 y 5
                    salesFileWriter.write("IDProducto" + productId + ";" + quantity + "\n");
                }
                salesFileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de ventas: " + e.getMessage());
        }
    }

    // Método para generar el reporte de ventas
    public static void generateSalesReport(int salesmenCount, String basePath) {
        try {
            FileWriter salesReportWriter = new FileWriter(basePath + "sales_report.txt");
            for (int i = 1; i <= salesmenCount; i++) {
                int totalSales = 0;
                BufferedReader salesReader = new BufferedReader(new FileReader(basePath + "sales_Vendedor" + i + ".txt"));
                String salesLine;
                while ((salesLine = salesReader.readLine()) != null) {
                    totalSales++;
                }
                salesReader.close();
                salesReportWriter.write("Vendedor" + i + ": " + totalSales + " ventas\n");
            }
            salesReportWriter.close();
        } catch (IOException e) {
            System.err.println("Error al generar el reporte de ventas: " + e.getMessage());
        }
    }

    // Método para generar un archivo de información de vendedores
    public static void createSalesManInfoFile(int salesmanCount, String basePath) {
        try {
            FileWriter salesmenFileWriter = new FileWriter(basePath + "salesmen.txt");
            for (int i = 1; i <= salesmanCount; i++) {
                salesmenFileWriter.write("TipoDocumento" + i + ";NúmeroDocumento" + i + ";NombresVendedor" + i + ";ApellidosVendedor" + i + "\n");
            }
            salesmenFileWriter.close();
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de información de vendedores: " + e.getMessage());
        }
    }

    // Método para generar un archivo de información de productos
    public static void createProductsFile(int productsCount, String basePath) {
        try {
            FileWriter productsFileWriter = new FileWriter(basePath + "base/products.txt");
            for (int i = 1; i <= productsCount; i++) {
                productsFileWriter.write("IDProducto" + i + ";NombreProducto" + i + ";PrecioPorUnidadProducto" + i + "\n");
            }
            productsFileWriter.close();
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de información de productos: " + e.getMessage());
        }
    }
}
