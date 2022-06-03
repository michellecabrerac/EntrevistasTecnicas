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
        BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
        Scanner in = new Scanner(System.in);


        /* Sabemos que la primera linea contiene la cabecera del CSV. La leemos y guardamos la información para poder discernir entre filas.
         por si en un futuro nos cambian de orden las filas*/
        String line = read.readLine();
        String [] cabecera = null;
        String [] contenido = null;
        String campoEvaluado= "language";
        int indexDelTipoDeseado = -1;
        ArrayList <String> resultado = new ArrayList<String>();
        if(line != null){ // First line
            cabecera = line.split(",");
        }

        indexDelTipoDeseado = getIndex(campoEvaluado, cabecera);
        if(indexDelTipoDeseado<0) {
            System.out.println("El campo " + campoEvaluado + "no está presnete en el .csv");
        }else{
            String tipoDelCampoEvaluadoDeseado = preguntarUsuarioValorDelTipo(in);
            int numeroDeResultadosDeseado= preguntarUsuarioNumeroResultados(in);

            while ((line = read.readLine()) != null){
                contenido = line.split(",");
                if(contenido[indexDelTipoDeseado].equals(tipoDelCampoEvaluadoDeseado)){ // Si la linea es del repositorio deseado la guardamos
                    resultado.add(line);
                    if(resultado.size() == numeroDeResultadosDeseado){
                        break;
                    }
                }
            }
        }

        System.out.println(resultado);
        read.close();
    }

    private static String preguntarUsuarioValorDelTipo(Scanner in) {
        System.out.println("Escribe el valor del campo que quieres evaluar:");
        return in.nextLine();
    }

    private static int preguntarUsuarioNumeroResultados(Scanner in) {
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

    private static int getIndex(String palabraBuscada, String[] cabecera) {
        int index = -1;
        for(int i= 0 ; i<cabecera.length; i++){
            if(cabecera[i].equals(palabraBuscada)){
                index = i;
                break;
            }
        }
       return index;
    }
}
