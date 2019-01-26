# FastRadius-STLParser
## Dependencies  
- Lombok: 1.18.4
- Jackson Codr

## New Config File 
- extractEndPointType: defines which type of endPoint to hit
- extractConfiguration: String-> Map-> string mapping. Used to define relative file path or in somecases DB 
connection parameters
- parseFormat: defines the string parser for the model
- statistics: list defining which stats to use.
- loadType: Final Phase of ETL pipeline
- loadConfiguration: 