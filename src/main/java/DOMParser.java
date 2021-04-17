import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    public List<Student> XMLReader(String path){
        List<Student> group = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder =  factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));
            NodeList studentList = document.getElementsByTagName("student");
            for(int i = 0; i < studentList.getLength(); i++){
                Element student = (Element) studentList.item(i);
                String firstname = student.getAttribute("firstname");
                String lastname = student.getAttribute("lastname");
                String groupnumber = student.getAttribute("groupnumber");
                List<Subject> subjects = new ArrayList<>();
                NodeList subjectList = student.getElementsByTagName("subject");
                for(int j = 0; j < subjectList.getLength(); j++){
                    Element subject = (Element) subjectList.item(j);
                    String title = subject.getAttribute("title");
                    int mark = Integer.parseInt(subject.getAttribute("mark"));
                    subjects.add(new Subject(title, mark));
                }
                double average = Double.parseDouble(student.getElementsByTagName("average").item(0).getTextContent());
                group.add(new Student(firstname, lastname, groupnumber, subjects, average));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return group;
    }

    public void XMLWriter(String path, List<Student> groupList) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = builder.newDocument();
        Element group = document.createElement("group");
        document.appendChild(group);
        for (Student temp : groupList) {
            Element student = document.createElement("student");
            student.setAttribute("firstname", temp.getFirstname());
            student.setAttribute("lastname", temp.getLastname());
            student.setAttribute("groupnumber", temp.getGroupnumber());
            for (Subject subj : temp.getSubject()) {
                Element subject = document.createElement("subject");
                subject.setAttribute("title", subj.getTitle());
                subject.setAttribute("mark", String.valueOf(subj.getMark()));
                student.appendChild(subject);
            }
            Element average = document.createElement("average");
            average.setTextContent(String.valueOf(temp.getAverage()));
            student.appendChild(average);
            group.appendChild(student);
        }
        DOMSource source = new DOMSource(document);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult file = new StreamResult(new File(path));
            transformer.transform(source, file);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
