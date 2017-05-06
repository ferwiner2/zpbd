package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

public class Parser {

    private String[] lines;

    public void loadPage(String pathXml) {
        lines = pathXml.split("\n");
    }

    public String getTitle() {
        String string = lines[1];
        return string.substring(11, string.length() - 8);
    }

    public int getId() {
        String string = lines[3];
        String result = string.substring(8, string.length() - 5);
        return Integer.parseInt(result);
    }

    public String getText() {
        StringBuilder buffor = new StringBuilder();
        boolean text = false;
        for (String str : lines) {
            if (str.contains("<text")) {
                text = true;
                str = str.replace("<text xml:space=\"preserve\">", "");
            }
            if (str.contains("</text>")) {
                text = false;
                str = str.replace("</text>", "");
                buffor.append(str).append("\n");
            }
            else if (text) {
                buffor.append(str).append("\n");
            }

        }
        return buffor.toString();
    }

}
