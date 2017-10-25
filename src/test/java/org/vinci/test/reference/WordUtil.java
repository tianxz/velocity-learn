package org.vinci.test.reference;

/**
 * Created by XizeTian on 2017/10/25.
 */
public class WordUtil {
    private String word = "";

    private WordUtil(String word) {
        this.word = word;
    }

    public static WordUtil of(String word) {
        return new WordUtil(word);
    }

    public WordUtil toUpper() {
        this.word = this.word.toUpperCase();
        return this;
    }

    public String out() {
        return word;
    }
}
