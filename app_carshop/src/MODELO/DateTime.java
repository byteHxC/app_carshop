/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

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
       this.dateTime = dateTime;
    }
    public static  LocalDateTime getNow(){
       LocalDateTime dateTimeNow = LocalDateTime.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String text = dateTimeNow.format(formatter);
       LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
       return parsedDate;
    }
    public static String getDiaNow(){
       String [] days = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
       Date now = new  Date();
       int numDay = 0;
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(now);
       numDay = calendar.get(Calendar.DAY_OF_WEEK);
       return days[numDay-1];
    }
    
    public static  String getFechaNow(){
       LocalDateTime dateTimeNow = LocalDateTime.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
       String text = dateTimeNow.format(formatter);
       return text;
    }
    public static int getHour(){
       Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
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
