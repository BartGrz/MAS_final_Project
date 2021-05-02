package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.tools.Looper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SqlTools <T>{

   private final T t;

    public static String formatFieldsToInsert(List<String> fields) {

        StringBuilder stb = new StringBuilder();
        stb.append("(");

        for(int i = 0;i<fields.size();i++) {
            stb.append(fields.get(i));

            if ((i + 1) != fields.size()) {
                stb.append(",");
            }else {
                stb.append(")");
            }
        }

        return stb.toString();
    }

    public String formatFieldsToSelectQuery(List<?> columns ) {
        StringBuilder stb = new StringBuilder();

        Looper.forLoop(0,columns.size(),i -> {
            if(i+1 == columns.size()) {
                stb.append(getTableFirstLetter(t) +"."+columns.get(i).toString().toLowerCase());
            }else {
                stb.append(getTableFirstLetter(t) +"."+columns.get(i).toString().toLowerCase() + ",");

            }
        });
        System.out.println(" STB : " + stb.toString());
        return stb.toString();
    }

    public static String formatValuesToInsertQuery(List<Object> list) {


        StringBuilder stb = new StringBuilder();

        stb.append("(");
        for (int i = 0; i< list.size();i++) {

            if(list.get(i).getClass().getSimpleName().equals("String")) {
                stb.append("'"+list.get(i)+"'");
            }else {
                stb.append(list.get(i));
            }
            if(i+1 !=list.size()) {
                stb.append(",");
            }else {

            }


    }
        stb.append(")");

        return stb.toString();
    }
    public  String getTableFirstLetter(T t) {

        return t.getClass().getSimpleName().substring(0,1);
    }

}
