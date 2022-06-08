package CodelyTV;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*Tenemos un .csv en donde aparecen los repositorios más valorados de github.
Queremos un programa que devuelva los N repositios más valorados según el lenguaje de programación especificado por el usuario */
public class CodelyTVMain {
    // 1 - Read CSV
    // 2 - Itera CSV recogiendo los registros de los repositorios con el lenguaje deseado hasta la N especificada por parámetro
    // 3 - Devuelve resultado

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://raw.githubusercontent.com/EvanLi/Github-Ranking/master/Data/github-ranking-2022-06-01.csv");
        ResourceReader resourceReader = new UrlReader(url);
        for(String line : resourceReader.getResult()){
            System.out.println(line);
        }
    }


}
