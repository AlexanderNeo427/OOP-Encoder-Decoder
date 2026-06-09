# OOP Encoder/Decoder 

## High-level Overview
- The ```ReferenceTableMapping``` class maintains the 2-way mapping of a char to its index _(and vice-versa)_
- The ```ReferenceTable``` class will be injected with an instance of the mapping _(As the mappings can be arbitrary)_\
  _e.g Maybe in the future, I want to map ```A``` to ```7``` instead of ```0```_
- We have an ```IReferenceTableMappingCreator``` interface, which has a method to return a ```ReferenceTable``` instance.
- We create a concrete ```DxcReferenceTableMappingCreator``` implementation to create the mapping stipulated in the assignment

Note: In a real project, I would dial-back on the excessive OOP, as it makes code harder to reason about.

## Assumptions made
During encoding/decoding, the first char will NOT be involved...only used as the shift amount. For example:
```c
BHELLO WORLD ----encode---> GDKKN VNQKC
```
```c
BGDKKN VNQKC ----decode---> HELLO WORLD
```
(Note that the first char is removed)
