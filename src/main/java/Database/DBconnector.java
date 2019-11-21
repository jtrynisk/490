package Database;

import Logger.MyLogger;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static com.mongodb.client.model.Filters.eq;

public class DBconnector
{
    private MongoClientURI mongoClientURI;
    public MongoClient mongoClient;
    private MongoDatabase database;
    private Properties config;
    private MyLogger logger;

    public DBconnector()
    {
        config = new Properties();
        logger = new MyLogger();
        try
        {
            FileReader fr = new FileReader("/Users/jondntryniski/490/src/main/resources/application.conf");
            config.load(fr);
            this.mongoClientURI = new MongoClientURI(config.getProperty("clienturi"));
            this.mongoClient = new MongoClient(mongoClientURI);
            database = mongoClient.getDatabase("Lakeview");
        }catch(IOException e)
        {
            logger.makeLog(e.getMessage());
        }


    }

    public void writeDocument(Document d)
    {
        MongoCollection customers = database.getCollection("Customers");
        customers.insertOne(d);
    }

    public void writeToWorkOrder(Document d)
    {
        MongoCollection workOrders = database.getCollection("WorkOrders");
        workOrders.insertOne(d);

    }

    public void remove(Document d, String db)
    {
        MongoCollection collection = database.getCollection(db);
        collection.deleteOne(eq("_id", d.get("_id")));
    }

    public Document findDocument(String firstName, String lastName, String db)
    {
        FindIterable temp = null;
        MongoCollection collection = database.getCollection(db);
        temp = collection.find(eq("lastName", lastName));
        ArrayList<Document> documents = new ArrayList<>();
        temp.into(documents);

        for(int i = 0; i < documents.size(); i++)
        {
            if(documents.get(i).getString("firstName").equals(firstName))
                return documents.get(i);
        }
        return null;
    }


    public void closeConnection()
    {
        mongoClient.close();
    }
}