package com.selenium.Runner;

import static com.selenium.Utils.commonMethods.*;

import com.selenium.Utils.commonMethods;


public class ReportCreater {
    public static String ReportBatPath = System.getProperty("user.dir") + "/Reports/Report.bat";

    public static void main(String args[]) throws Exception {
        Runtime.getRuntime().exec("cmd /C start /wait " + ReportBatPath).waitFor();
        commonMethods.savingReport();
    }
}

