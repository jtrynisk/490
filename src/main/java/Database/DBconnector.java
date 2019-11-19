package Database;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

public class DBconnector
{
    private MongoClientURI mongoClientURI;
    public MongoClient mongoClient;
    private MongoDatabase database;

    public DBconnector()
    {
        //TODO add this to the application.conf file
        this.mongoClientURI = new MongoClientURI("mongodb+srv://test:test@cluster0-c8ugg.mongodb.net/test?retryWrites=true&w=majority");
        this.mongoClient = new MongoClient(mongoClientURI);
        database = mongoClient.getDatabase("Lakeview");

    }

    public void writeDocument(Document d)
    {
        MongoCollection customers = database.getCollection("Customers");
        customers.insertOne(d);
    }

    public Document findDocument(String firstName, String lastName)
    {
        FindIterable temp = null;
        MongoCollection customer = database.getCollection("Customers");
        temp = customer.find(eq("lastName", lastName));
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
