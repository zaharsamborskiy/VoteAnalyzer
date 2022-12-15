import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler
{
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private final HashMap<Voter, Byte> voterCounts;

    public XMLHandler()
    {
        this.voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

        try {
            if (qName.equals("voter") && voter == null)
            {
                String birtDay = (attributes.getValue("birthDay"));
                String name = attributes.getValue("name");
                voter = new Voter(name, birtDay);
            }
            else if (qName.equals("visit") && voter != null)
            {
                byte count = voterCounts.getOrDefault(voter, (byte) 0);
                voterCounts.put(voter, (byte) (count + 1));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    public void printDuplicatedVoters()
    {
        for (Voter v : voterCounts.keySet())
        {
            int count = voterCounts.get(v);
            if (count > 1) {
                System.out.println(v.toString() + " - " + count);
            }
        }
    }
}
