package pl.pw.edu.elka.zpbd.wikiReader;

@SuppressWarnings("WeakerAccess")
public class Timer {
    private long start = 0;
    private long stop = 0;


    public void start() {
        start = System.currentTimeMillis();
    }

    public Timer stop() {
        stop = System.currentTimeMillis();
        return this;
    }

    public void showTime() {
        System.out.println((stop - start) / 1000.0 + " s.");
    }

    public long getMilis() {
        if (stop == 0)
            return System.currentTimeMillis() - start;
        else
            return stop - start;
    }

    public long getMilisFromStart() {
        return System.currentTimeMillis() - start;
    }

}
