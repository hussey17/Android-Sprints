package com.example.lonelytwitter;
import java.util.Date;
public class ImportantTweet extends Tweet{
    public ImportantTweet(String message) {
        super(message);
    }
    public ImportantTweet(Date date, String message) {
        super(date, message); // Super calls parent constructor //
    }
    @Override
    public boolean isImportant() {
        return Boolean.TRUE;
    }
}
