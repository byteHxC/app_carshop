/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author Byter
 */
public class DateTime {
    LocalDateTime dateTime;
    public DateTime(LocalDateTime dateTime){
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String text = dateTime.format(formatter);
       LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
       System.out.println(parsedDate);
       this.dateTime = dateTime;
    }
    public static  LocalDateTime getNow(){
       LocalDateTime dateTimeNow = LocalDateTime.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String text = dateTimeNow.format(formatter);
       LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
       System.out.println(parsedDate);
       return parsedDate;
    }
    public static  String getFechaNow(){
       LocalDateTime dateTimeNow = LocalDateTime.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
       String text = dateTimeNow.format(formatter);
       return text;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String text = dateTime.format(formatter);
       LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
       System.out.println(parsedDate);
        this.dateTime = dateTime;
    }
    
    
    
    
}
