# FastRadius-STLParser
# Overview
This system uses many different built tools to make an ETL pipeline

## Dependencies  
- Lombok: 1.18.4
- Jackson Codr

## New Config File 
- extractType: defines which type of endPoint to hit
- extractConfiguration: String-> Map-> string mapping. Used to define relative file path or in somecases DB 
connection parameters
- parseFormat: defines the string parser for the model to be built with respects
- statistics: list defining which stats to use analysis to perform
- loadType: defines the type of endpoints to using for the ETL pipeline
- loadConfiguration: extra parameters for the loader. This could include but not limited to;

