package helper;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindSubString {
    public static final String FIND_AUTHOR ="([A-Za-z\\s\\.\\D]).*?(?=\\\")";
    public  static final String FIND_FIRST_NUMBER = "^\\d+";
    public static final String FIND_LAST_NUMBER ="\\d{1,4}$";
    public static final String FIND_BETWEEN_QOUTES = "\\\"([^\\\"]*)\\\"";

    public String findString(String value,String pattern ){
        Pattern patter =  Pattern.compile(pattern);
        Matcher matcher = patter.matcher(value);
        String s = "";
        while(matcher.find()){
            s = matcher.group();
            break;
        }
        return s;
    }
    public String findString(String value, String pattern,int indexGroup){
        Pattern patter =  Pattern.compile(pattern);
        Matcher matcher = patter.matcher(value);
        String s = "";
        while(matcher.find()){
            s = matcher.group(indexGroup);
            break;
        }
        return s;
    }
}
