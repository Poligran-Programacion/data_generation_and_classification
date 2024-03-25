package com.poligran.service;

import com.poligran.implementation.IElementFormatter;
import com.poligran.repository.FileGeneratorRepository;
import com.poligran.repository.RouteRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        createSalesManInfoFile(5);

        createProductsFile(1000);

        addRandomProductsToSellers();

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
     * Adds random products to each seller's file, selecting a random subset of products from the products list.
     * The number of products added to each seller's file is approximately 30% of the total products available.
     * The products are randomly selected from the list of available products.
     *
     * @throws IOException if an I/O error occurs while reading or writing files.
     */
    private static void addRandomProductsToSellers() throws IOException {
        // TODO: The responsibilities of this function must be factored and divided.
        // Retrieve the list of products
        List<String> productsList = getProductList();

        // Calculate total number of products and the count of random products (30% of total)
        int totalProductsCount = productsList.size();
        int randomProductsCount = (int) Math.ceil((30 / 100.0) * totalProductsCount);

        // Iterate through the sellers file and add random products to each seller
        BufferedReader sellersReader = new BufferedReader(new FileReader(routeRepository.getBaseRoute() + "seller.txt"));
        String sellerLine;
        while ((sellerLine = sellersReader.readLine()) != null) {
            String[] sellerInfo = sellerLine.split(";");
            if (sellerInfo.length >= 2) {
                String sellerIdentifier = sellerInfo[1];
                String sellerName = sellerInfo[2];
                String sellerFileName = sellerIdentifier + "_" + sellerName + ".txt";

                // Select random products
                List<String> randomProducts = new ArrayList<>();
                Collections.shuffle(productsList);
                for (int i = 0; i < randomProductsCount && i < productsList.size(); i++) {
                    randomProducts.add(productsList.get(i));
                }

                // Write random products to the seller's file
                FileWriter fileWriter = new FileWriter(routeRepository.getSalesRoute() + sellerFileName, true);
                for (String product : randomProducts) {
                    fileWriter.write(product + "\n");
                }
                fileWriter.close();
            }
        }

        sellersReader.close();
        System.out.println("Random products added to sellers' files");
    }

    /**
     * Retrieves the list of products from the "products.txt" file.
     *
     * @return A list containing each product entry from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    static List<String> getProductList() throws IOException {
        List<String> productsList = new ArrayList<>();
        BufferedReader productsReader = new BufferedReader(new FileReader(routeRepository.getBaseRoute() + "products.txt"));
        String productLine;
        while ((productLine = productsReader.readLine()) != null) {
            productsList.add(productLine);
        }
        productsReader.close();
        return productsList;
    }

}