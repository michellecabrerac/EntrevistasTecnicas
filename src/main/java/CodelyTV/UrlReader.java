package CodelyTV;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UrlReader implements ResourceReader{
    private BufferedReader read;
    private Scanner in;
    private String [] cabecera;
    private String FIELD_EVALUATED = "language";

    public UrlReader(URL url) throws Exception {
        try{
            read = new BufferedReader(new InputStreamReader(url.openStream()));
            in = new Scanner(System.in);
            cabecera = read.readLine().split(",");
        }
        catch(Exception ex){
            read.close();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<String> getResult() throws Exception {
        try{
            String desiredFieldValue = askUserForTheValue();
            int numberOfResults = askUserForTheNumberOfResults();
            return processLines(desiredFieldValue, numberOfResults);
        }
        catch(Exception ex){
            read.close();
            throw new Exception(ex.getMessage());
        }
    }

    private List<String> processLines(String desiredFieldValue, int numberOfResults) throws Exception {
        String line;
        String [] separatedLine;
        ArrayList<String> result = new ArrayList();
        int headerIndex = getHeaderIndex(FIELD_EVALUATED, cabecera);
        if(headerIndex < 0) {
            throw new Exception("El campo " + FIELD_EVALUATED + " no está presente en el .csv");
        }else{
            while ((line = read.readLine()) != null){
                separatedLine = line.split(",");
                if(separatedLine[headerIndex].equals(desiredFieldValue)){ // Si la linea es del repositorio deseado la guardamos
                    result.add(line);
                    if(result.size() == numberOfResults){
                        break;
                    }
                }
            }
        }
        return result;
    }

     private int getHeaderIndex(String palabraBuscada, String[] cabecera) {
        int index = -1;
        for(int i= 0 ; i<cabecera.length; i++){
            if(cabecera[i].equals(palabraBuscada)){
                index = i;
                break;
            }
        }
        return index;
    }

    public String askUserForTheValue() {
        System.out.println("Escribe el valor del campo que quieres evaluar:");
        return in.nextLine();
    }

    public int askUserForTheNumberOfResults() {
        int numeroResultados = -1;
        do{
            System.out.println("Escribe el número de resultados que quieres que se te devuelva:");
            try{
                numeroResultados = in.nextInt();
            }catch(InputMismatchException ex){
                System.out.println("Lo que acabas de escribir no es un entero.");
            }
            finally {
                in.nextLine();
            }
        }while(numeroResultados<0);

        return numeroResultados;
    }
}
