package com.poligran.service;

import com.poligran.implementation.IElementFormatter;
import com.poligran.repository.FileGeneratorRepository;
import com.poligran.repository.RouteRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Class for generating information files, including the creation of sellers and products.
 * It uses a custom format to generate elements within the files.
 */
public class GenerateInfoFiles {

    private static final FileGeneratorRepository fileRepository = new FileGeneratorRepository();
    private static final RouteRepository routeRepository = new RouteRepository();

    static Random random = new Random();

    /**
     * Initial configuration function that creates sellers and products.
     *
     * @throws IOException if an I/O error occurs while creating files.
     */
    public static void initialConfig() throws IOException {

        // Initial function that creates sellers
        createSalesManInfoFile(5);

        // Function that creates products
        createProductsFile(1000);


        /// Create files for sellers
        createSalesManInfoFile();

    }


    /**
     * Creates initial sellers.
     *
     * @param salesmanCount The number of sellers to create.
     * @throws IOException if an I/O error occurs while creating files.
     */
    private static void createSalesManInfoFile(int salesmanCount) throws IOException {

        createElementsOnFile(
                routeRepository.getBaseRoute(),
                "seller.txt",
                index -> "CC;" + (System.currentTimeMillis() / 1000 + index) + ";Seller" + index,
                salesmanCount
        );

        System.out.println("The sellers were generated");
    }


    /**
     * Creates initial products.
     *
     * @param productsCount The number of products to create.
     * @throws IOException if an I/O error occurs while creating files.
     */
    private static void createProductsFile(int productsCount) throws IOException{

        createElementsOnFile(routeRepository.getBaseRoute(),
                "products.txt",
                index -> (System.currentTimeMillis() / 1000 + index) + ";Product_" + index + ";$" + random.nextInt(1300),
                productsCount
        );

        System.out.println("The products were generated");
    }


    /**
     * Creates a file for each seller using their name and identification.
     * Reads seller information from the "seller.txt" file.
     * Each line in the file is expected to have the following format:
     * [Identification];[Name]
     * For each seller, it creates a file with the seller's identification and name as the file name.
     * @throws IOException if an I/O error occurs while reading or writing the files.
     */
    private static void createSalesManInfoFile() throws IOException {
        /// TODO: The entry of an integer that defines the random sales of each salesperson must be implemented.
        BufferedReader reader = new BufferedReader(new FileReader(routeRepository.getBaseRoute() + "seller.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] sellerInfo = line.split(";");
            if (sellerInfo.length >= 2) {
                String sellerIdentifier = sellerInfo[1]; // Identificador único del vendedor
                String sellerName = sellerInfo[2]; // Nombre del vendedor
                String sellerFileName = sellerIdentifier + "_" + sellerName + ".txt"; // Nombre del archivo para este vendedor
                createFileForSeller( routeRepository.getSalesRoute(), sellerFileName, sellerIdentifier);
            }
        }
        reader.close();
        System.out.println("Files for sellers were generated");
    }


    /**
     * Base function responsible for creating elements within a file with customizable structure.
     * This structure adheres to the Open And Close and Single Responsibility principles.
     *
     * @param route The route where the file will be created.
     * @param fileName The name of the file.
     * @param formatter The formatter interface to format elements.
     * @param quantity The quantity of elements to create.
     * @throws IOException if an I/O error occurs while creating files.
     */
    private static void createElementsOnFile(String route, String fileName, IElementFormatter formatter, int quantity) throws IOException {
        FileWriter file = fileRepository.createFile(route, fileName);
        for (int index = 1; index <= quantity; index++) {
            file.write(formatter.format(index) + "\n");
        }

        file.close();
    }


    /**
     * Creates a file for a specific seller.
     * Reads the sold products from the "products.txt" file and writes them into the seller's file.
     * @param route The directory route where the file will be created.
     * @param fileName The name of the file to be created for the seller.
     * @param sellerIdentifier The unique identifier of the seller.
     * @throws IOException if an I/O error occurs while reading or writing the files.
     */
    private static void createFileForSeller(String route, String fileName, String sellerIdentifier) throws IOException {
        // TODO: se debe crear un ciclo que lea los productos vendidos y escriba en el archivo los correspondientes al vendedor.
        FileWriter file = fileRepository.createFile(route, fileName);

        file.write(sellerIdentifier + "\n");

        file.close();
    }


}


