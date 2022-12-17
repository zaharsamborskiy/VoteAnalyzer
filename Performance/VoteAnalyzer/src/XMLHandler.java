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
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        int maxBatchSize = 5_000_000;
        if (qName.equals("voter") && batchSize < maxBatchSize)
        {
            String birtDay = (attributes.getValue("birthDay"));
            String name = attributes.getValue("name");
            voter = new Voter(name, birtDay);
        }
        else if (qName.equals("visit") && voter != null)
        {
            try {
                DBConnection.preparedStatement.setString(1, voter.getName());
                DBConnection.preparedStatement.setString(2, voter.getBirthDay());
                DBConnection.preparedStatement.addBatch();
                countVoters++;

                if (countVoters > 10_000)
                {
                    DBConnection.preparedStatement.executeBatch();
                    DBConnection.connection.commit();
                    countVoters = 0;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            batchSize++;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equals("voter"))
        {
            voter = null;
        }
    }
}
