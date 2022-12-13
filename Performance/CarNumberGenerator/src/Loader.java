import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Loader {

    public static void main(String[] args) throws FileNotFoundException {
        ExecutorService service = Executors.newFixedThreadPool(6);
        long start = System.currentTimeMillis();
        for (int region = 1; region < 100; region++)
        {
            service.submit(new Generator(region));

        }
        service.shutdown();
        while (!service.isTerminated())
        {
            System.out.println("Затраченное время - " + (System.currentTimeMillis() - start) + " ms");
        }
    }
}
/**
 * Измерения:
 * 1.Задание без изменений,без использования многопоточности(для записи 1-го региона) = 8 секунд;
 *
 * 2.Задание с изменением String на StringBuilder как в методе, так и в основных циклах,
 * без использования многопоточности(для записи 1-го региона) ≈ 277 ms;
 *
 * 3.Задание с изменением String на StringBuilder и использованием многопоточности(для записи 1-го региона) - примерно 75 ms;
 * время может увеличиваться или уменьшаться в зависимости от выбранного метода:
 * newCachedThreadPool() - (100 файлов) ≈ 8893 ms (с исп. PrintWriter), ≈ 9421 ms (c исп. FileOutputStream)
 * newFixedThreadPool(6) - (100 файлов) ≈ 6977 ms (с исп. PrintWriter), ≈ 8416 ms (c исп. FileOutputStream)
 * newFixedThreadPool(12) - (100 файлов) ≈ 7786 ms (с исп. PrintWriter), ≈ 9177 ms (c исп. FileOutputStream)
 *
 * В рамках этого задания, при генерации потока на каждый регион(из 100), ускорение операции ≈ в (8000 / 75) = 105 раз.
 */
