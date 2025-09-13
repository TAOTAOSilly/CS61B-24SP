package ngrams;

import edu.princeton.cs.algs4.In;

import java.time.Year;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    TreeMap<String,TimeSeries> aWords = new TreeMap<>();
    TreeMap<Integer,Double> totalMap = new TreeMap<>();
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        readWordsHelper(wordsFilename);
        readTotalHelper(countsFilename);
    }

    private void readWordsHelper(String wordsFilename) {
        In in = new In(wordsFilename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] p = line.split("\t");
            String word = p[0];
            int year = Integer.parseInt(p[1]);
            double count = Double.parseDouble(p[2]);

            TimeSeries ts = aWords.get(word);
            if (ts == null) {
                ts = new TimeSeries();
                aWords.put(word,ts);
            }
            ts.put(year,count);
        }
    }

    private void readTotalHelper(String countsFilename) {
        In in = new In(countsFilename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] p = line.split(",");
            int year = Integer.parseInt(p[0]);
            double total = Double.parseDouble(p[1]);
            totalMap.put(year,total);
        }
    }
    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    private TimeSeries copy(TimeSeries ts){
        TimeSeries copy = new TimeSeries();
        for (Map.Entry<Integer,Double> entry : ts.entrySet()) {
            int year = entry.getKey();
            copy.put(year,ts.get(year));
        }
        return copy;
    }

    public TimeSeries countHistory(String word, int startYear, int endYear) {

        if (!aWords.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries ts = aWords.get(word);
        return new TimeSeries(ts, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {

        if (!aWords.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries copy = copy(aWords.get(word));
        return copy;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries ts = new TimeSeries();
        for (Map.Entry<Integer, Double> e : totalMap.entrySet()) {
            ts.put(e.getKey(), (double) e.getValue());
        }
        return ts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!aWords.containsKey(word)) {
            return new TimeSeries();
        }

        TimeSeries wordTS = new TimeSeries(aWords.get(word), startYear, endYear);

        TimeSeries totalTS = new TimeSeries(totalCountHistory(), startYear, endYear);

        // 相对频率 = 词频 ÷ 总词数
        return wordTS.dividedBy(totalTS);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!aWords.containsKey(word)) {
            return new TimeSeries();
        }

        TimeSeries wordTS = countHistory(word);

        TimeSeries totalTS = totalCountHistory();


        return wordTS.dividedBy(totalTS);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries sum = new TimeSeries();
        for (String w : words) {
            if (!aWords.containsKey(w)) continue;
            TimeSeries wts = new TimeSeries(aWords.get(w), startYear, endYear);
            TimeSeries tts = new TimeSeries(totalCountHistory(), startYear, endYear);
            sum = sum.plus(wts.dividedBy(tts));
        }
        return sum;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries ts = summedWeightHistory(words,MIN_YEAR,MAX_YEAR);
        return ts;
    }
}
