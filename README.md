# Freightmate Systems : Consignment Note Number Generator

Within the freight industry every shipment of items has a Consignment Note Number (Connote Number). 
When we create a new consignment, we must generate an industry wide unique ID. 

## Consignment Note Number Generator
An unique ID is made up of multiple parts.\
**A Carrier prefix, an account number, a consignment index within an allowed range, digits and a checksum**.

For example a shipment sent with Freightmate Couriers Co may be: `FMCC123ABC00000196051` and these parts are as follows:
```
Prefix: FMCC
Account Number: 123ABC
Digits: 10
Consignment Index: 19605
Checksum: 1
Range 19000-20000
```
**Prefix** is calculated by extract the Capital letters of carrier name.\
**Consignment Index** is calculated by incremented from the last used index and padded with 0's to make the index of length of Digits (i.e. 10).
**Checksum is calculated as**
```
 - Adding every second number in the index from the right starting at the first element
  ( 5 + 6 + 1 + 0 + 0 = 12 )
 - Multiply that number by 3
  ( 12 * 3 = 36 )
 - Adding every second number in the index from the right starting at the second element
( 0 + 9 + 0 + 0 + 0 = 9 )
 - Multiply that number by 7
    ( 9 * 7 = 63)
 - Add the results of step 1 and step 2
  ( 63 + 36 = 99 )
 - Get the difference between that number and the next multiple of 10, this is your checksum
  ( 100 - 99 = 1 )
```

## Input
(POJO object is ConnoteNumberDto) as a parameter and generates the next connote number in that series.
```
{
  "carrierName":"FreightMateCourierCo",
  "accountNumber":"ABC123",
  "digits":10,
  "lastUsedIndex":19604,
  "rangeStart":19000,
  "rangeEnd":20000
}
```
## Output
**FMCC123ABC00000196051**

## Core Implementation
The Freightmate Systems is mainly developed for unique consignment note number generation of given input last used consignment index. 
The program has the mainly three classes:
- **ConnoteNumberDto**: DTO/POJO class for input Consignment Note Number 
- **ConnoteService**: Service class for Consignment Note Number generator which has generateConnoteNumber(), carrier getPrefix(), and checkSum() methods.
- **ConsignmentNoteNumberGenerator** : Main class for Consignment Note Number generator which initialize the ConnoteNumberDto POJO input, ConnoteService and called generateConnoteNumber() method.

## How to Run Program
Run main method of **ConsignmentNoteNumberGenerator** where you can set the ConnoteNumberDto POJO input as shown in above and called generateConnoteNumber() method of ConnoteService. \

**Output** \
The new Consignment Note Number: FMCC123ABC00000196051 if given last used index is in consignment index range \
otherwise ouput as "The given last Connote Number was not in Range 19000 - 20000"

