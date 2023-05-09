package be.ninedocteur.apare.utils;

import be.ninedocteur.apare.Apare;
import be.ninedocteur.apare.api.event.EventRegistry;
import be.ninedocteur.apare.event.LoggerEvent;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class Logger {
    private static String tempString;
    private static String message;
    public void send(String message, Type type){
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
            Logger.message = tempString;
        } else if(type == Type.WARN && message != tempString){
            MessageUtils.sendWarn("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            tempString = "("+ date.getHours() + ":" + date.getMinutes() +") " + message;
            Logger.message = tempString;
        } else if(type == Type.ERROR && message != tempString){
            MessageUtils.sendError("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            tempString = "("+ date.getHours() + ":" + date.getMinutes() +") " + message;
            Logger.message = tempString;
        } else {
            MessageUtils.sendSuccess("("+ date.getHours() + ":" + date.getMinutes() +") " + message);
            tempString = "("+ date.getHours() + ":" + date.getMinutes() +") " + message;
            Logger.message = tempString;
        }
        LoggerEvent event = new LoggerEvent(this);
    }

    public String getMessage() {
        return message;
    }

    public enum Type{
         NORMAL,
        SUCCESS,
        WARN,
        ERROR
    }
}
