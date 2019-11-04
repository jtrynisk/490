package Database;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBconnector
{
    private MongoClientURI mongoClientURI;
    public MongoClient mongoClient;
    private MongoDatabase database;

    public DBconnector()
    {
        this.mongoClientURI = new MongoClientURI("mongodb+srv://test:test@cluster0-c8ugg.mongodb.net/test?retryWrites=true&w=majority");
        this.mongoClient = new MongoClient(mongoClientURI);
        database = mongoClient.getDatabase("Lakeview");

    }

    public void writeDocument(Document d)
    {
        MongoCollection customers = database.getCollection("Customers");
        customers.insertOne(d);
    }

    public void closeConnection()
    {
        mongoClient.close();
    }
}
