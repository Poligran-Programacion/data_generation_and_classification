# Data Generation and Classification Program

For the Module project, it is expected that each delivery be shared through a hyperlink to a repository, a program that takes as input a series of files with vendor information. There will be one plain text file per vendor, which will have the following format (one sale per line):

```
VendorDocumentType;VendorDocumentNumber
ProductID1;QuantitySoldProduct1;
ProductID2;QuantitySoldProduct2;
ProductID3;QuantitySoldProduct3;
```

The number of vendors is unknown, but as input, the program will have several (possibly many) plain files in a folder, each with the sales information of a vendor. All vendor files must be in the same project folder as the program to facilitate exploration of these files from the program.

Additionally, the program will take as input a file with vendor information. The plain text file will have the format described below, with one vendor per line.

Vendor Information File Format:

```
DocumentType;DocumentNumber;VendorName1;VendorLastName1
DocumentType;DocumentNumber;VendorName2;VendorLastName2
DocumentType;DocumentNumber;VendorName3;VendorLastName3
```

The program will also take as input a file with information about all available products. Each product must go with the ID and name.

# Tools
- Java
- Eclipse
