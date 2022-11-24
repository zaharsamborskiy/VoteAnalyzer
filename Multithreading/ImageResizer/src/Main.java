import java.io.File;

public class Main {
    private static int newWidth = 300;
    private static int half = 2;

    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\Dematix\\Desktop\\from";
        String dstFolder = "C:\\Users\\Dematix\\Desktop\\to";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int middle = files.length/half;


        File[] files1 = new File[middle];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
        new Thread(resizer1).start();

        File[] files2 = new File[files1.length / half];
        System.arraycopy(files, middle, files2, 0, files2.length);
        ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder, start);
        new Thread(resizer2).start();

        File[] files3 = new File[files2.length / half];
        System.arraycopy(files, middle, files3, 0, files3.length);
        ImageResizer resizer3 = new ImageResizer(files3, newWidth, dstFolder, start);
        new Thread(resizer3).start();

        File[] files4 = new File[files3.length / half];
        System.arraycopy(files, middle, files4, 0, files4.length);
        ImageResizer resizer4 = new ImageResizer(files4, newWidth, dstFolder, start);
        new Thread(resizer4).start();

        File[] files5 = new File[files4.length * half];
        System.arraycopy(files, middle, files5, 0, files5.length);
        ImageResizer resizer5 = new ImageResizer(files5, newWidth, dstFolder, start);
        new Thread(resizer5).start();

        File[] files6 = new File[files5.length * half];
        System.arraycopy(files, middle, files6, 0, files6.length);
        ImageResizer resizer6 = new ImageResizer(files6, newWidth, dstFolder, start);
        new Thread(resizer6).start();
    }
}
