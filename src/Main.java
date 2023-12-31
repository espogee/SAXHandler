import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Get the DOM Builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Load and Parse the XML document
        //Object document contains the complete XML as a Tree.
        Document document = builder.parse(ClassLoader.getSystemResourceAsStream("Student.xml"));

        List<Student> stdList = new ArrayList<>();

        //Iterating through the nodes list and extracting the data.
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {

            //We have encountered an <Student> tag.
            Node node = nodeList.item(i);
            // Check if the current node is an instance of element
            if (node instanceof Element) {
                Student std = new Student();
                // Check if the current node is a student tag
                if (node.getNodeName().equals("student")) {
                    NamedNodeMap attr = node.getAttributes();
                    // get the student id & active attributes
                    std.id = attr.getNamedItem("id").getNodeValue();
                    std.active = attr.getNamedItem("active").getNodeValue();
                }
                //We get a list of the child nodes of student element
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);

                    //Identifying the child tag of Student encountered.
                    if (cNode instanceof Element) {
                        //We get the last child of the current tag,
                        //the last child will be the text node
                        String content = cNode.getLastChild().getNodeValue().trim();
                        switch (cNode.getNodeName()) {
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
                }
                stdList.add(std);
            }
        }

        //Printing the Student list populated.
        for (Student std : stdList) {
            System.out.println(std);
        }
    }
}