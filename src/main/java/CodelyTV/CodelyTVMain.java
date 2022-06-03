package CodelyTV;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*Tenemos un .csv en donde aparecen los repositorios más valorados de github.
Queremos un programa que devuelva los N repositios más valorados según el lenguaje de programación especificado por el usuario */
public class CodelyTVMain {
    // 1 - Read CSV
    // 2 - Itera CSV recogiendo los registros de los repositorios con el lenguaje deseado hasta la N especificada por parámetro
    // 3- Devuelve resultado

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://raw.githubusercontent.com/EvanLi/Github-Ranking/master/Data/github-ranking-2022-06-01.csv");
        BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
        Scanner in = new Scanner(System.in);


        /* Sabemos que la primera linea contiene la cabecera del CSV. La leemos y guardamos la información para poder discernir entre filas.
         por si en un futuro nos cambian de orden las filas*/
        String line = read.readLine();
        String [] cabecera = null;
        String [] contenido = null;
        String campoEvaluado = preguntarUsuarioCampoAEvaluar(in);
        int numeroDeResultadosDeseado= preguntarUsuarioNumeroResultados(in);
        String tipoDelCampoEvaluadoDeseado = preguntarUsuarioValorDelTipo(in);
        ArrayList <String> resultado = new ArrayList<String>();
        if(line != null){ // First line
            cabecera = line.split(",");
        }
        int indexDelTipoDeseado = getIndex(campoEvaluado, cabecera);
        while ((line = read.readLine()) != null){
            contenido = line.split(",");
            if(contenido[indexDelTipoDeseado].equals(tipoDelCampoEvaluadoDeseado)){ // Si la linea es del repositorio deseado la guardamos
                resultado.add(line);
                if(resultado.size() == numeroDeResultadosDeseado){
                    break;
                }
            }
        }
        System.out.println(resultado);
        read.close();
    }

    private static String preguntarUsuarioValorDelTipo(Scanner in) {
        System.out.println("Escribe el valor del campo QUE quieres evaluar:");
        return in.nextLine();
    }

    private static int preguntarUsuarioNumeroResultados(Scanner in) {
        System.out.println("Escribe el número de resultados que quieres que se te devuelva:");
        int numeroResultados = in.nextInt();
        in.skip("\n");
        return numeroResultados;
    }

    private static String preguntarUsuarioCampoAEvaluar(Scanner in) {
        System.out.println("Escribe que campo quieres evaluar:");
        return in.nextLine();
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
