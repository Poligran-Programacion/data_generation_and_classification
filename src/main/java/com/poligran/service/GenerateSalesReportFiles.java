package com.poligran.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class GenerateSalesReportFiles {
	
	public static void individualSalesReader (String args[]) {
		
		/*
		 * The path where the seller info file is specified.
		 */
		
		String pathSeller = "C:\\Data\\Files\\Programing\\"
			+ "data_generation_and_classification-main"
			+ "\\src\\main\\resources\\base\\seller.txt";
		String line1 = "";
		
		int sellers = 0;//"sellers" variable is created for counting the number of sellers.
		/*
		 * The hashmaps where the info for Names,
		 * Id types and Id's are created.
		 */
		
		HashMap<Integer, String> sellerNames = new HashMap<>();
		HashMap<Integer, String> sellerIds = new HashMap<>();
		HashMap<Integer, String> sellerIdType = new HashMap<>();
	
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathSeller));
			while((line1 = br.readLine()) != null) {
				sellers += 1;
				String[] values = line1.split(";");
				sellerNames.put(sellers,values[2]);
				sellerIds.put(sellers,values[1]);
				sellerIdType.put(sellers,values[0]);
				}
			//System.out.println(sellerNames);//Tester
			//System.out.println(sellerIds);//Tester
			//System.out.println(sellerIdType);//Tester
			} catch (FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		/*
		 * A loop is used to read the file and write to each hashmap the key
		 * (number corresponding the seller) and the value (Names,Id types,Id's).
		 */
		
		String line2 = "";
		int a = 0;//This variable is used to change the file name in order to account for "sellers" variable.
		int sumPrices = 0;
		HashMap<Integer, Integer> sellerSales = new HashMap<>();
		
		/*
		 * "sellerSales" hashmap is created, it will store the key (number of seller)
		 * and the value (total sales).
		 */
		
		for (int i = 0; i<sellers; i++) {
			
			/*
			 * The path where the seller sales file is specified,
			 * it changes for each seller.
			 */
			
			String pathSales = "C:\\Data\\Files\\Programing\\"
				+ "data_generation_and_classification-main"
				+ "\\src\\main\\resources\\sales"
				+ "\\" + (1712618559+a) + "_Seller" + (1+a) + ".txt";
			a+=1;
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(pathSales));
				while((line2 = br.readLine()) != null) {
					String[] values = line2.split(";");
				
					String prices = values[2];
					String pricesc1 = prices.replace("$", "");
					if (pricesc1.contains("-")) {
						System.out.println("Error, check for negative prices");
						System.exit(0);
					}
					int pricesfinal = Integer.valueOf(pricesc1);
					sumPrices = sumPrices + pricesfinal;
					}
				sellerSales.put(a,sumPrices);
				sumPrices = 0;
				} catch (FileNotFoundException e){
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		/*
		 * A loop is used to read the file and write to each hashmap.
		 */
		//System.out.println(sellerSales);//Tester

		Map <Integer, Integer> sortedSellerSales = sellerSales.entrySet().stream()
			.sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
			(e1, e2) -> e1, LinkedHashMap::new));
		
		//"sellerSales" hashmap is sorted to "sortedSellerSales".
		
		HashMap<Integer, String> printSellerSales = new HashMap<>();
		sellerSales.forEach((k, v) -> printSellerSales.put(k, v.toString()));
		
		/*
		 * A second hashmap named "printSellerSales" is created, it contains the
		 * same keys and values as "sellerSales" but changes the type of the value
		 * from Integer to String to be able to write it later.
		 */
		
		//System.out.println(sortedSellerSales);//Tester
		//System.out.println(printSellerSales);//Tester
		
		try (FileWriter csvWriter = new FileWriter("Total_sales_by_seller.csv")) {
			csvWriter.append("Name,Id Type,Id Number, Total Sales\n");// Write header row
			for (Map.Entry<Integer, Integer> entry : sortedSellerSales.entrySet()) {
				int key = entry.getKey();
				
				/*
				 * Using a loop the hashmap sortedSellerSales is read and the values are stored to
				 * "key" variable, in order to use it for reading the other hashmaps and write
				 * the CSV file.
				 */
				
				//System.out.println(key);//Tester
				csvWriter.append(sellerNames.get(key)).append(",");
				csvWriter.append(sellerIdType.get(key)).append(",");
				csvWriter.append(sellerIds.get(key)).append(",");
				csvWriter.append("$"+printSellerSales.get(key)).append("\n");
			}
			System.out.println("Total sales by seller file created");
			csvWriter.close();
		} catch (IOException e) {
			System.out.println("Error creating report");
			e.printStackTrace();
		}
	}	
}		
