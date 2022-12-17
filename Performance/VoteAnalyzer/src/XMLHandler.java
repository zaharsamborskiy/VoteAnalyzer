import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;


public class XMLHandler extends DefaultHandler
{
    private Voter voter;
    private int countVoters = 0;
    private int batchSize;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
    {
        int maxBatchSize = 5_000_000;

        if (qName.equals("voter") && batchSize < maxBatchSize /*&& voter == null*/)
        {
            try {
                String birtDay = (attributes.getValue("birthDay"));
                String name = attributes.getValue("name");
//                voter = new Voter(name, birtDay);
                if (countVoters > 10_000)
                {
                    DBConnection.multiInsert();
                    countVoters = 0;
                }
                DBConnection.countVoter(name, birtDay);
                countVoters++;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            batchSize++;
        }
    }

//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException
//    {
//        if (qName.equals("voter"))
//        {
//            voter = null;
//        }
//    }
}
