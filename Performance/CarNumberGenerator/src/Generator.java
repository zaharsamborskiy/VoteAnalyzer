import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Generator implements Runnable
{
//    FileOutputStream printWriter;
    public StringBuilder stringBuilder;
    public int region;
    public PrintWriter printWriter;

    char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public Generator(int region) throws FileNotFoundException
    {
        printWriter = new PrintWriter("CarNumberGenerator/res/numbers" + region + ".txt");
        this.region = region;
        stringBuilder = new StringBuilder();

    }
    @Override
    public void run()
    {
        for (int number = 1; number < 1000; number++) {
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        stringBuilder
                            .append(firstLetter)
                            .append(padNumber(number,3))
                            .append(secondLetter)
                            .append(thirdLetter)
                            .append(padNumber(region, 2))
                            .append(" ");
                    }
                }
            }
        }
        printWriter.write(stringBuilder.toString());
        printWriter.flush();
        printWriter.close();
    }

    private static StringBuilder padNumber(int number, int numberLength) {
        StringBuilder stringBuilder = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - stringBuilder.length();
        for (int i = 0; i < padSize; i++)
        {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder;
    }
}
