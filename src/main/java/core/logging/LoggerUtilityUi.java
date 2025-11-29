package core.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class LoggerUtilityUi {


    // path unde voi salva fisierele de logs
    private static final String suiteLogsPath = "target/logs/suite/";


    // path unde voi genera fisierul mare cu toate logs
    private static final String regressionLogsPath = "target/logs/";


    private static final Logger logger = LogManager.getLogger();

    // am nevoie de metode care ma asigura ca:
    // 1. porneste un test
    public static synchronized void startTestCase(String testName) {
        ThreadContext.put("threadName", testName);
        logger.info("========== Execution started: " + testName + " ==========");
    }

    // 2. a terminat un test
    public static synchronized void endTestCase(String testName) {
        logger.info("========== Execution ended: " + testName + " ==========\n");
    }

    // 3. adauga un entry ca log
    public static synchronized void infoLog(String message) {
        logger.info(getCallInfo() + message);
    }

    // 4. logheaza o eroare
    public static synchronized void errorLog(String message) {
        logger.error(getCallInfo() + message);
    }

    // 5. scoate informatii despre executia curenta
    private static synchronized String getCallInfo() {
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        className = className.substring(className.lastIndexOf('.') + 1);

        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        return className + ": " + methodName + " ==> ";
    }

    // 6. ia toate logs individuale si le pune intr-un singur fisier
    public static void mergeLogFilesIntoOne() {
        File dir = new File(suiteLogsPath);
        String[] fileNames = dir.list();

        try {
            PrintWriter pw = new PrintWriter(regressionLogsPath + "RegressionLogs.log");

            for (String fileName : fileNames) {
                File f = new File(dir, fileName);
                BufferedReader br = new BufferedReader(new FileReader(f));
                pw.println("Contents of file " + fileName);
                String Line = br.readLine();
                while (Line != null) {
                    pw.println(Line);
                    Line = br.readLine();
                }
                pw.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}
