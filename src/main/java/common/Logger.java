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
    private boolean isConsolePrint;

    private static final String LOG_DIRECTORY = "log";
    private static final String LOG_FILE_NAME = "log.txt";
    private static final long MAX_SIZE = 10240; // 10KB로 설정
    public static final int LIMIT_FILE_COUT_TO_ZIP = 3; // 3개 이상은 ZIP 파일로 변경
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
    private static final SimpleDateFormat logTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 로그에 표시할 시간 포맷
    private static final int MAX_METHOD_NAME_LENGTH = 40; // 클래스명과 메소드 이름의 최대 길이

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
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            try {
                String callingClassName = stackTraceElements[3].getClassName();
                Class<?> callingClass = Class.forName(callingClassName);
                if ("CommandShell".equals(callingClass.getSimpleName())) {
                    instance.isConsolePrint = true;
                } else if ("Runner".equals(callingClass.getSimpleName())) {
                    instance.isConsolePrint = false;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                instance.isConsolePrint = false;
            }
        }
        return instance;
    }

    public void log(String message) {
        String currentTime = logTimeFormat.format(new Date());

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String methodName = stackTraceElements[2].getMethodName();
        String className = stackTraceElements[2].getClassName();
        className = className.substring(className.lastIndexOf('.') + 1);
        String fullMethodName = className + "." + methodName;

        String paddedMethodName = String.format("%-" + MAX_METHOD_NAME_LENGTH + "s", fullMethodName);

        String formattedMessage = currentTime + " " + paddedMethodName + ": " + message;

        try {
            checkAndRollFile();
            out.println(formattedMessage);
            out.flush(); // 실시간으로 파일에 기록하기 위해 flush 호출
            if (isConsolePrint) {
                System.out.println(formattedMessage);
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
                if (i >= LIMIT_FILE_COUT_TO_ZIP) { // 마지막 3개 파일보다 오래된 파일
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