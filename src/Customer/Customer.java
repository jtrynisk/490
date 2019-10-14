package Customer;

public class Customer
{
    private String firstName, lastName;
    private String email, address, city, state, zip, phone;

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

    private SpecSheet specs;

    Customer(String firstName, String lastName, String email, SpecSheet specs)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specs = specs;
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
