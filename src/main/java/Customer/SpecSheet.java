package Customer;

import Database.DBconnector;
import com.mongodb.BasicDBObject;
import org.bson.Document;

public class SpecSheet
{
    private String hand, oval, leftFinger, rightFinger, leftReverse, rightReverse, leftForward, rightForward,
            leftSideway, rightSideway, bridge, span, cutToCutSpan, thumb, thumbType, thumbForward, thumbReverse, thumbLeft, thumbRight,
            degreeOfOval, width, grip;

    public SpecSheet(String hand, String oval, String leftFinger, String rightFinger, String leftReverse, String rightReverse,
                     String leftForward, String rightForward, String leftSideway, String rightSideway, String bridge, String span,
                     String cutToCutSpan, String thumb, String thumbType, String thumbForward, String thumbReverse, String thumbLeft,
                     String thumbRight, String degreeOfOval, String width, String grip)
    {
        this.hand = hand;
        this. oval = oval;
        this. leftFinger = leftFinger;
        this.rightFinger = rightFinger;
        this.leftReverse = leftReverse;
        this.rightReverse = rightReverse;
        this.leftForward = leftForward;
        this.rightForward = rightForward;
        this.leftSideway = leftSideway;
        this.rightSideway = rightSideway;
        this.bridge = bridge;
        this.span = span;
        this.cutToCutSpan = cutToCutSpan;
        this. thumb = thumb;
        this.thumbType = thumbType;
        this.thumbForward = thumbForward;
        this.thumbReverse = thumbReverse;
        this.thumbLeft = thumbLeft;
        this.thumbRight = thumbRight;
        this.degreeOfOval = degreeOfOval;
        this.width = width;
        this.grip = grip;
    }

    public void createDocument(String firstName, String lastName)
    {
        DBconnector db = new DBconnector();
        Document temp = db.findDocument(firstName, lastName);
        db.remove(temp);
        temp.append("hand", hand).append("oval", oval).append("leftFinger", leftFinger).append("rightFinger", rightFinger).append("leftReverse", leftReverse)
                .append("rightReverse", rightReverse).append("leftForward", leftForward).append("rightForward", rightForward).append("leftSideway", leftSideway)
                .append("rightSideway", rightSideway).append("bridge", bridge).append("span", span).append("cutToCut", cutToCutSpan).append("thumb", thumb)
                .append("thumbType", thumbType).append("thumbForward", thumbForward).append("thumbReverse", thumbReverse).append("thumbLeft", thumbLeft)
                .append("thumbRight", thumbRight).append("degree", degreeOfOval).append("width", width).append("grip", grip);
        db.writeDocument(temp);
        db.closeConnection();
    }

}
