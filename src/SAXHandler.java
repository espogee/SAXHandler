import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

class SAXHandler extends DefaultHandler {

    List<Student> stdList;
    Student std = null;
    String content = null;

    public void startDocument() {
        stdList = new ArrayList<>();
    }

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        switch(qName){
            //Create a new Student object when the start tag is found
            case "student":
                std = new Student();
                std.id = attributes.getValue("id");
                std.active = attributes.getValue("active");
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch(qName){
            //Add the Student to list once end tag is found
            case "student":
                stdList.add(std);
                break;
            //For all other end tags the Student has to be updated.
            case "firstName":
                std.firstName = content;
                break;
            case "lastName":
                std.lastName = content;
                break;
            case "location":
                std.location = content;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content = String.copyValueOf(ch, start, length).trim();
    }

    public void endDocument() {
        //Printing the list of Students obtained from XML
        for (Student std : stdList){
            System.out.println(std);
        }
    }
}