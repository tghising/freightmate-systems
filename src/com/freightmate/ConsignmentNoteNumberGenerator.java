package com.freightmate;

/**
 * ConsignmentNoteNumberGenerator: Main class for Consignment Note Number generator
 *
 * @author Tikaraj Ghising
 */
public class ConsignmentNoteNumberGenerator {

    public static void main(String[] args) {
        // ConnoteNumber input pojo object creation
        ConnoteNumberDto connoteInput = new ConnoteNumberDto("FreightMateCourierCo", "123ABC", 10, 19604, 19000, 20000);
        System.out.println(connoteInput);

        ConnoteService connoteService = new ConnoteService(); // ConnoteService object creation
        String uniqueConnoteNumber = connoteService.generateConnoteNumber(connoteInput); // call generateConnoteNumber() method  for generate Connote Number of given previous Connote Number
        System.out.println("The new Consignment Note Number: " + uniqueConnoteNumber);
    }
}
