package pl.pw.edu.elka.zpbd.wikiReader.config;

public class Config {

    private static String jvXml = "E:\\wiki\\jvwiki.xml";
    private static String enXml = "E:\\wiki\\enwiki.xml";
    private static String plXml = "E:\\wiki\\plwiki.xml";

    private static int logResulotion = 5000;

    public static String getJvXml() {
        return jvXml;
    }

    public static String getEnXml() {
        return enXml;
    }

    public static String getPlXml() {
        return plXml;
    }

    public static int getLogResulotion() {
        return logResulotion;
    }
}
