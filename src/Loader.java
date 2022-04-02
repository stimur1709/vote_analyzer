import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        long usages = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        String fileName = "res/data-1M.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        DBConnection.executeMultiInsert();
        DBConnection.printVoterCounts();


        usages = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usages;
        System.out.println(usages);
        System.out.println("Выполнено за " + (System.currentTimeMillis() - start) + " мс");
    }
}
