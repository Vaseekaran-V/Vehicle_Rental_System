package com.OOP_CW;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnectDB {
    MongoClient vehiclesDatabase = new MongoClient("localhost", 27017);
    MongoDatabase db = vehiclesDatabase.getDatabase("RentVehicleDB");
    Morphia morphia = new Morphia();
    Datastore datastore = morphia.createDatastore(vehiclesDatabase, "RentVehicleDB");

}
