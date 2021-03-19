About
=========

This is the library for fake records generation (with main aim to provide sample data for database and ML integration testing, etc)

In many cases it is crucial to provide accurate data for integration testing, as quite often the input data is validated before further processing.

For example, IBAN bank account number can be validated against checksum, length and format  

In modern distributed systems the geographical location in requested json can trigger the processing of this request on very specific node in cluster, 
as routes are quite often are federated on the basis of location, and so on.

Overview
=========

The project consists from two modules, ``code`` and ``datasource``

Core module includes low-level functionality, and basic data types.

The main features of core library (implemented through major classes from core module):

* Fully customizable data generator classes

* High-performance and memory-effective engine

* Accurate, realistic and human-readable generated data (currently US and UK - styled addresses are supported, different generators for surnames, and more)



The second module, ```datasource```, is a higher level wrapper which uses core functionality and introduces the concept of Random Datasource, which can be used to build a complex POJOs.

Main features:


* Annotation-based engine with ability to inject fake data source

* Rich configuration via annotations or through java code


Installation
=============

```
mvn clean install
```

Examples of generated data
===========================

Geographical location (state, city, zip code and location are real, the address line is generated)

```
[
  {
    "address": "146 Alhambra Street",
    "country": "us",
    "state": "ID",
    "city": "Boise",
    "zipCode": 83709,
    "location": {
      "latitude": 43.54,
      "longitude": -116.29
    }
  },
  {
    "address": "183 Carnival Boulevard",
    "country": "us",
    "state": "CA",
    "city": "Visalia",
    "zipCode": 93277,
    "location": {
      "latitude": 36.29,
      "longitude": -119.38
    }
  }
]
```

Personal data

```
[
  {
    "uid": "07255d42-84f2-4cc3-8514-5a226a5441c4",
    "firstName": "Clayton",
    "lastName": "Westanerick",
    "gender": "MALE",
    "age": 40,
    "email": "qoju634@gmail.com",
    "creditCard": {
      "type": "VISA",
      "number": "4071301235725643",
      "securityCode": "107",
      "expdate": "01/22"
    }
  },
  {
    "uid": "0845d3ef-2400-40cd-a5ba-8706d8f68433",
    "firstName": "Miriam",
    "lastName": "Arefield",
    "gender": "FEMALE",
    "age": 63,
    "email": "guxi155@gmail.com",
    "creditCard": {
      "type": "MASTERCARD",
      "number": "52707401119856489",
      "securityCode": "360",
      "expdate": "04/21"
    }
  }
]
```

# Example of using

See the org.datum.example.SimpleDemo class in datasource module and unit tests

