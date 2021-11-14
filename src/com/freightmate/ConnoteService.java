package com.freightmate;

/**
 * ConnoteService: Service class for Consignment Note Number generator
 *
 * @author Tikaraj Ghising
 */

public class ConnoteService {
    // method to generate Consignment Note Number with parameter ConnoteNumberDto object
    public String generateConnoteNumber(ConnoteNumberDto connote) {
        int previousIndex = connote.getLastUsedIndex();
        int digits = connote.getDigits();
        String connoteNumber = "";
        if (previousIndex >= connote.getRangeStart() && previousIndex < connote.getRangeEnd()) {
            String prefix = getPrefix(connote.getCarrierName());
            int currentIndex = previousIndex + 1; //consignment index must be incremented from last used index
//        String consignmentTenIndex = String.format("%010d", consignmentIndex);
            String consignmentIndex = String.format("%0" + digits + "d", currentIndex); // padded 0's to make index as given index i.e. 10
            int checkSum = checkSum(consignmentIndex, digits);
            connoteNumber = prefix + connote.getAccountNumber() + consignmentIndex + String.valueOf(checkSum);
        } else {
            connoteNumber = "The given last Connote Number was not in Range " + connote.getRangeStart() + "-" + connote.getRangeEnd();
        }
        return connoteNumber;
    }

    // generate Carrier Name prefix of Capital letters only
    public String getPrefix(String carrierName) {
        char[] carrierNames = carrierName.toCharArray();
        String prefix = "";
        for (int i = 0; i < carrierNames.length; i++) {
            if (Character.isUpperCase(carrierNames[i])) {
                prefix += carrierNames[i];
            }
        }
        return prefix;
    }

    // method calculate checksum of given consignment index with number of digits
    public int checkSum(String consignmentIndex, int digit) {
        int checkSum = 0;
        int firstCheckSum = 0;
        int secondCheckSum = 0;
        char[] consignmentChar = consignmentIndex.toCharArray();

        // calculate checksum of every second number in the index from the right starting at the first element
        for (int i = consignmentChar.length - 1; i >= 0; i = i - 2) {
            firstCheckSum += Integer.parseInt(String.valueOf(consignmentChar[i]));
        }

        // calculate checksum of every second number in the index from the right starting at the second element
        for (int i = consignmentChar.length - 2; i >= 0; i = i - 2) {
            secondCheckSum += Integer.parseInt(String.valueOf(consignmentChar[i]));
        }

        /* multiply first and second check sum by 3 and 7 respectively and sum up
            and difference with digits multiple of 10
        */
        checkSum = Math.abs((digit * 10) - (firstCheckSum * 3 + secondCheckSum * 7));
        return checkSum;
    }
}
