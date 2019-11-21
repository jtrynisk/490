package WorkOrder;

import Database.DBconnector;
import org.bson.Document;

public class WorkOrder
{
    private String firstName, lastName, ballName, email, notes;
    private DBconnector db = new DBconnector();

    public WorkOrder(String first, String last, String ball, String email, String notes)
    {
        this.firstName = first;
        this.lastName = last;
        this.ballName = ball;
        this.email = email;
        this.notes = notes;
    }

    public void sendToDb()
    {
        DBconnector db = new DBconnector();
        Document d = new Document("_id", firstName + " " + lastName + " " + ballName).append("firstName", firstName)
                                                                                     .append("lastName", lastName)
                                                                                     .append("ball", ballName)
                                                                                     .append("email", email)
                                                                                     .append("notes", notes);
        db.writeToWorkOrder(d);
        db.closeConnection();
    }
}
