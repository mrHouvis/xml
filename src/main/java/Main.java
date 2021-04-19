import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class Main {

    public static final String path = "src/main/resources/grouplist.xml";
    public static final String verifiedPath = "src/main/resources/verifiedgrouplist.xml";

    public static void main(String[] args){

        DOMParser domParser = new DOMParser();
        List<Student> group = domParser.XMLReader(path);
        for(Student student : group){
            double rightAverage = 0;
            for(Subject subject : student.getSubject()){
                rightAverage += subject.getMark();
            }
            rightAverage = rightAverage/student.getSubject().size();
            rightAverage = Double.parseDouble(String.valueOf(new BigDecimal(rightAverage, new MathContext(2, RoundingMode.HALF_EVEN))));
            double average = Double.parseDouble(String.valueOf(new BigDecimal(student.getAverage(), new MathContext(2, RoundingMode.HALF_EVEN))));
            if(average != rightAverage){
                student.setAverage(rightAverage);
            }
        }
        domParser.XMLWriter(verifiedPath, group);
    }

}
