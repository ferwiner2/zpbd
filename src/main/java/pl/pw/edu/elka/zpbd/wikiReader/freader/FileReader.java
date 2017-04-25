package pl.pw.edu.elka.zpbd.wikiReader.freader;


import pl.pw.edu.elka.zpbd.wikiReader.config.Config;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader implements AutoCloseable {

    private BufferedReader reader;

    private StringBuilder buffor;

    public FileReader(Mode mode) throws IOException {
        reader = new BufferedReader(new java.io.FileReader(getXmlName(mode)));
        buffor = new StringBuilder();
        skipFirstLines();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    private String getXmlName(Mode mode) {
        switch (mode) {
            case EN:
                return Config.getEnXml();
            case JV:
                return Config.getJvXml();
            case PL:
                return Config.getPlXml();
        }
        return "";
    }

    private void skipFirstLines() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("<page>")) {
                buffor.append(line).append("\n");
                break;
            }
        }
    }

    public String getNextPage() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            buffor.append(line).append("\n");
            if (line.contains("</page>")) {
                String result = buffor.append(line).toString();
                buffor = new StringBuilder();
                return result;
            }
        }
        throw new EndOfFileException();
    }

    public enum Mode {
        EN, JV, PL
    }

}
