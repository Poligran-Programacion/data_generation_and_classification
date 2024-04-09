package com.poligran.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class GenerateSalesReportFiles {
	
	public static int sellerReader (String args[]) {
	String path = "C:\\Data\\Files\\Programing\\"
			+ "data_generation_and_classification-main"
			+ "\\src\\main\\resources\\base\\seller.txt";
	String line = "";
	int sellers = 0;
	
	HashMap<Integer, String> sellerNames = new HashMap<>();
	
	try {
		BufferedReader br = new BufferedReader(new FileReader(path));
		while((line = br.readLine()) != null) {
			sellers += 1;
			String[] values = line.split(";");
			sellerNames.put(sellers,values[2]);
			}
		//System.out.println(sellerNames);//Tester
		} catch (FileNotFoundException e){
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
	return sellers;
	}
	
	public static void individualSalesReader (String args[], int sellers) {
	String line = "";
	int a = 0;
	int sumPrices = 0;
	HashMap<Integer, Integer> sellerSales = new HashMap<>();
	
	for (int i = 0; i<sellers; i++) {
		
		String path = "C:\\Data\\Files\\Programing\\"
				+ "data_generation_and_classification-main"
				+ "\\src\\main\\resources\\sales"
				+ "\\" + (1712618559+a) + "_Seller" + (1+a) + ".txt";
		a+=1;
			
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) != null) {
				String[] values = line.split(";");
				
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
	//System.out.println(sellerSales);//Tester

	Map <Integer, Integer> sortedSellerSales = sellerSales.entrySet().stream()
	.sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
			(e1, e2) -> e1, LinkedHashMap::new));
	HashMap<String, String> printSellerSales = new HashMap<>();
	sortedSellerSales.forEach((k, v) -> printSellerSales.put(k.toString(), v.toString()));
	
	//System.out.println(sortedSellerSales);//Tester
	try {
	      File myObj = new File("sellerSales.txt");
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        System.out.println("File already exists.");
	      }
	}catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	    }
	BufferedWriter bf = null;
    try {
    	bf = new BufferedWriter(new FileWriter("sellerSales.txt")); 
    	  
        for (Map.Entry<Integer, Integer> entry :
        	sortedSellerSales.entrySet()) { 
            bf.write(entry.getKey() + ","
                     + entry.getValue()); 
            bf.newLine(); 
        } 
        bf.flush(); 
    } 
    catch (IOException e) { 
        e.printStackTrace(); 
    } 
    finally { 
        try { 
            bf.close(); 
        } 
        catch (Exception e) { 
        } 
    } 
    }
}		
