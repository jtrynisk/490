package Customer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;

public class Customer
{
    private String firstName, lastName;
    private String email, address, city, state, zip, phone;
    private SpecSheet specs;

    public Customer(String firstName, String lastName, String email, String address, String city, String state, String zip, String phone, SpecSheet specs)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.specs = specs;
    }

    public Customer(String firstName, String lastName, String email, String address, String city, String state, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
    }

    public Customer(String firstName, String lastName, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Document createDocument()
    {
        Document object = new Document("_id:", firstName + " " + lastName)
                                            .append("firstName", firstName)
                                            .append("lastName", lastName)
                                            .append("email", email)
                                            .append("address", new BasicDBObject("street", address)
                                                                        .append("city", city)
                                                                        .append("state", state)
                                                                        .append("zip", zip))
                                            .append("phone", phone);
        return object;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SpecSheet getSpecs() {
        return specs;
    }

    public void setSpecs(SpecSheet specs) {
        this.specs = specs;
    }

}
