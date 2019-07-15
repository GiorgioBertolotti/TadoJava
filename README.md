# TadoJava

[![Build Status](https://travis-ci.com/GiorgioBertolotti/TadoJava.svg?branch=master)](https://travis-ci.com/GiorgioBertolotti/TadoJava)

TadoJava is a Java wrapper library for [Tado](https://www.tado.com/en/)'s API.  
It allows you to control your Tado and get its status.

## Table of contents

   * [TadoJava](#tadojava)
   * [Table of contents](#table-of-contents)
   * [Getting started](#getting-started)
     * [Download](#download)
     * [Usage](#usage)
   * [Other](#other)
     * [Notes](#notes)
     * [Contact](#contact)

## Getting started

To use TadoJava you will need:

- A Tado (lol);
- A Tado account ([You can create it here](https://my.tado.com/webapp/));
- A client secret ([You can get yours here](https://my.tado.com/webapp/env.js)).

### Download

You can download the latest release of TadoJava [here](https://github.com/GiorgioBertolotti/TadoJava/releases).  
After downloading the *.jar* file, you can include it in your Java project.

### Usage

The *Example* folder contains an eclipse project with an example on how to use the library.  
In order to start using TadoJava you can declare a *TadoConnector* object as follows:

```java
TadoConnector connector = new TadoConnector("your@email.com", "Password123!");
//TadoConnector connector = new TadoConnector("your@email.com", "Password123!", "clientSecret");
```

The TadoConnector object will take care of performing HTTP request to Tado's API, it will also manage the bearer token in all the requests.  
The very first thing you want to do after declaring your *connector* is initializing it. This call will perform the requests to retrieve the *clientSecret* key and the *bearer token*.

```java
connector.initialize();
```

Now you're ready to go!  
Usually what you want to do is getting all the *homes* associated with your account and the relative *zones*. The following snippet will print all your homes and zones informations.

```java
for (TadoHome home : connector.getHomes()) {
  System.out.println(home.toString());
  System.out.println(home.getState(connector));
  for (TadoZone zone : home.getZones(connector)) {
    System.out.println(zone.toString());
    System.out.println(zone.getZoneState(connector));
  }
}
```

## Other

### Notes

To build this project I followed [this guide](https://shkspr.mobi/blog/2019/02/tado-api-guide-updated-for-2019/), which perfectly describes Tado's API. So I'd like to thanks [Terence Eden](https://shkspr.mobi/blog/) for his work!

### Contact

If there are any problems or you need any help feel free to contact me at [giorgio@bertolotti.dev](mailto:giorgio@bertolotti.dev).  
You can also reach me on [my website](https://bertolotti.dev/).