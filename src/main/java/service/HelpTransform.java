package service;

import entity.ColumnName;
import logger.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HelpTransform<T> {

    public List<T> transfer(ResultSet result, Class<T> object) {
        List<T> list= new ArrayList<T>();
        Field[] fields = object.getDeclaredFields();
        try {
            while (result.next()) {
                Constructor ctor = object.getDeclaredConstructor();
                T obj = (T) ctor.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    Annotation[] annotations = field.getDeclaredAnnotations();
                    ColumnName annotation = (ColumnName) annotations[0];
                    field.set(obj, result.getObject(annotation.columnName()));
                }
                list.add(obj);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        Log.infoMessage("generic transform complete");
        return list;
    }
}
