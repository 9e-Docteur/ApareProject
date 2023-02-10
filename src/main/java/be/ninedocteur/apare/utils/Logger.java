package be.ninedocteur.apare.utils;

import java.time.LocalDateTime;
import java.util.Date;

public class Logger {
    private static String tempString;
    public static void send(String message, Type type){
        Date date = new Date();
        /* NOT WORKING
        switch (type){
            case NORMAL:
                MessageUtils.send("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            case WARN:
                MessageUtils.sendWarn("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            case ERROR:
                MessageUtils.sendError("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            case SUCCESS:
                MessageUtils.sendSuccess("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
        }
         */

        if(type == Type.NORMAL && message != tempString){
            MessageUtils.send("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            tempString = "("+ date.getHours() + ":" + date.getMinutes() +") " + message;
        } else if(type == Type.WARN && message != tempString){
            MessageUtils.sendWarn("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            tempString = "("+ date.getHours() + ":" + date.getMinutes() +") " + message;
        } else if(type == Type.ERROR && message != tempString){
            MessageUtils.sendError("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            tempString = "("+ date.getHours() + ":" + date.getMinutes() +") " + message;
        } else {
            MessageUtils.sendSuccess("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            tempString = "("+ date.getHours() + ":" + date.getMinutes() +") " + message;
        }
    }

    public enum Type{
         NORMAL,
        SUCCESS,
        WARN,
        ERROR
    }
}
