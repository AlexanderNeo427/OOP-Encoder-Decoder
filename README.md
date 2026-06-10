# Overengineered OOP Encoder/Decoder 

## Preamble
In a _"real"_ project, I would use OOP judiciously...as the complexity overhead can make code harder to reason about.

## Running the project
Running in IntelliJ Idea is best. But if you want to run via the cli, execute the following in project root
```bash
mvn clean package
java -jar target/Overengineered-Encoder-Decoder-1.0-SNAPSHOT.jar
```

## Overview 

### Short version
```Main``` contains an ```EncoderDecoder```

```EncoderDecoder``` contains a ```ReferenceTable```

```ReferenceTable``` has a ```ReferenceTableMapping```, based on the ```IReferenceTableMappingCreator``` injected

## Detailed version
The ```ReferenceTableMapping``` class maintains the 2-way mapping of a char to its index 
- Need to find what is the index of char 'ch'? indexToChar( ch )
- Need to find what is the char at index 'i'?  charToIndex( i )
- O(1)
- The contained mappings can be arbitrary. For example, in the future I might want to map ```A``` to ```7``` instead of ```0```, we can simply create a new instance of this class with that mapping.

There is an ```IReferenceTableMappingCreator``` interface, which has a method to return a ```ReferenceTableMapping``` instance.\
Concrete implementations will contain the business logic to populate the mapping however they wish.

We create a concrete ```DxcReferenceTableMappingCreator``` implementation to create the mapping stipulated in the assignment
  ```c
  'A' <-> 0
  'B' <-> 1 
  'C' <-> 2
  ... 
  ...
  '.' <-> 42
  '/' <-> 43
  ```
The ```ReferenceTable``` class 
- It will be injected with an instance of the ```IReferenceTableMappingCreator``` and use it internally to initialize their ```ReferenceTableMapping``` instance 
- Has methods to find out the result if you offset ("shift") a char by some amount, based on their ```ReferenceTableMapping``` instance
  
The ```EncoderDecoder``` class contains a ```ReferenceTable``` instance, and contains the actual `encode()` and `decode()` methods


## Assumption(s) made
During encoding/decoding, first char will NOT be involved. It will only used as the shift amount, and would be discarded after encoding/decoding.\
For example:
```py
BHELLO WORLD ----encode---> GDKKN VNQKC
```
```py
BGDKKN VNQKC ----decode---> HELLO WORLD
```
_(Note that the first char is removed)_
