import Downloads.Metro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String DATA_GET_DEPTH_1 = "C:\\SkillboxRepository\\dpo_java_basics\\FilesAndNetwork\\data\\2\\4\\depths-1.json";

    public static void main(String[] args) throws Exception {

        Metro metroInfo = new Metro();
        metroInfo.downloadJsonInfo();//загрузка в json файл


        List<File> fileList = new ArrayList<>();
        serchFiles(new File("C:\\SkillboxRepository\\dpo_java_basics\\FilesAndNetwork\\data"), fileList);
        for (File file : fileList){
            System.out.println("Найдено в -> " + file.getAbsolutePath());
        }
    }
    public static void serchFiles(File rootFile, List<File> fileList){
        if (rootFile.isDirectory()){
            System.out.println("Searching at " + rootFile.getAbsolutePath());
            File[] directory = rootFile.listFiles();
            if (directory != null){

                for (File file : directory){

                    if (file.isDirectory()){
                        serchFiles(file, fileList);
                    }
                    else {
                    }if (file.getName().toLowerCase().endsWith(".json")){
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }
