package common;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Logger {
    private static Logger instance;
    private File file;
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter out;
    private static final String LOG_DIRECTORY = "log";
    private static final String LOG_FILE_NAME = "log.txt";
    private static final long MAX_SIZE = 1024; // 1KB로 설정
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");

    private Logger() {
        try {
            Files.createDirectories(Paths.get(LOG_DIRECTORY));
            file = new File(LOG_DIRECTORY, LOG_FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message, boolean toConsole) {
        try {
            checkAndRollFile();
            out.println(message);
            out.flush(); // 실시간으로 파일에 기록하기 위해 flush 호출
            if (toConsole) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkAndRollFile() throws IOException {
        if (file.length() > MAX_SIZE) {
            // 파일을 닫고 새 파일로 롤오버
            out.close();
            bw.close();
            fw.close();

            File logDir = new File(LOG_DIRECTORY);
            File[] existingFiles = logDir.listFiles((dir, name) -> name.endsWith(".txt") || name.endsWith(".zip"));
            Arrays.sort(existingFiles, Comparator.comparingLong(File::lastModified).reversed());

            for (int i = existingFiles.length - 1; i >= 0; i--) {
                if (i >= 3) { // 마지막 3개 파일보다 오래된 파일
                    File oldFile = existingFiles[i];
                    if (!oldFile.getName().endsWith(".zip")) {
                        File newZipName = new File(logDir, oldFile.getName().replaceAll(".txt$", ".zip"));
                        oldFile.renameTo(newZipName);
                    }
                }
            }

            String newFileName = "until_" + dateFormat.format(new Date()) + ".txt";
            File newFile = new File(logDir, newFileName);
            file.renameTo(newFile);
            file = new File(logDir, LOG_FILE_NAME);
            file.createNewFile();

            // 새 파일에 대한 새로운 FileWriter, BufferedWriter, PrintWriter 생성
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
        }
    }
}


//import java.io.*;
//import java.nio.file.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class Logger {
//    private static Logger instance;
//    private File file;
//    private static final String LOG_DIRECTORY = "log";
//    private static final String LOG_FILE_NAME = "log.txt";
////    private static final long MAX_SIZE = 10240; // 10KB
//    private static final long MAX_SIZE = 1024; // 1KB
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
//
//    private Logger() {
//        try {
//            Files.createDirectories(Paths.get(LOG_DIRECTORY));
//            file = new File(LOG_DIRECTORY, LOG_FILE_NAME);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static synchronized Logger getInstance() {
//        if (instance == null) {
//            instance = new Logger();
//        }
//        return instance;
//    }
//
//    public void log(String message, boolean toConsole) {
//        try {
//            checkAndRollFile();
//            try (FileWriter fw = new FileWriter(file, true);
//                 BufferedWriter bw = new BufferedWriter(fw);
//                 PrintWriter out = new PrintWriter(bw)) {
//                 out.println(message);
//            }
//            if (toConsole) {
//                System.out.println(message);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void checkAndRollFile() throws IOException {
//        if (file.length() > MAX_SIZE) {
//            File logDir = new File(LOG_DIRECTORY);
//            File[] existingFiles = logDir.listFiles((dir, name) -> name.endsWith(".txt") || name.endsWith(".zip"));
//            Arrays.sort(existingFiles, Comparator.comparingLong(File::lastModified).reversed());
//
//            for (int i = existingFiles.length - 1; i >= 0; i--) {
//                if (i >= 3) { // Older than the last 3 files
//                    File oldFile = existingFiles[i];
//                    if (!oldFile.getName().endsWith(".zip")) {
//                        File newZipName = new File(logDir, oldFile.getName().replaceAll(".txt$", ".zip"));
//                        oldFile.renameTo(newZipName);
//                    }
//                }
//            }
//
//            String newFileName = "until_" +dateFormat.format(new Date()) + ".txt";
//            File newFile = new File(logDir, newFileName);
//            file.renameTo(newFile);
//            file = new File(logDir, LOG_FILE_NAME);
//            file.createNewFile();
//        }
//    }
//}