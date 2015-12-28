package com.sniff.main.base.log.strings;

/**
 * Created by ivan on 9.12.15.
 */
public class SniffActionStrings {
    public static String sPathCode = "#c.path#";

    public static String sFileWriteErrorBody = "ERR:     File:\n" +
            "#c.path#\n" +
            "writing error. Something goes wrong ... .";
    public static String sFileExistBody = "WARN:     File:\n" +
            "#c.path#\n" +
            "already exists. Was skipped.";
    public static String sFileCreated = "OK:     File:\n" +
            "#c.path#\n";


    public static String sFolderExistBody = "OK:     Folder:\n" +
            "#c.path#\n" +
            "already exists. Was skipped.";
    public static String sFolderCreated = "OK:     Folder:\n" +
            "#c.path#\n";

    public static String sProjectWasNotFound = "FATAL_ERR:     Project under address:\n" +
            "#c.path#\n" +
            "wasn't found. Learn Ctrl+C - Ctrl+V. Or teach author to code :D" +
            "";
}
