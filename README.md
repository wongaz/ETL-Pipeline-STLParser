# FastRadius-STLParser
# Overview
This system uses many different built tools to make an ETL pipeline

## Dependencies  
- Lombok: 1.18.4
    - Used for maintaining POJO's and removing boilerplate code of getters and setters
    default constructors
- Jackson Code
    - YAML file parsing, used to make runtime configurations for the ETL pipeline.
- Apache Libraries
    - Extra utility and access to triples.

## New Config File 
- extractType: defines which type of endPoint to hit
- extractConfiguration: String-> Map-> string mapping. Used to define relative file path or in somecases DB 
connection parameters
- parseFormat: defines the string parser for the model to be built with respects
- statistics: list defining which stats to use analysis to perform
- loadType: defines the type of endpoints to using for the ETL pipeline
- loadConfiguration: extra parameters for the loader. This could include but not limited to;

## Design
![Test Image 3](images/flowDiagram.jpg "Flow Diagram")

This design feature the ability to feature multiple different ETL pipelines with different running 
configurations. Each pipeline can be build independent of each other thus if multiple files 
need to be processed then multiple pipelines can be built. 

