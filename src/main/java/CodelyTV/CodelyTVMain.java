package CodelyTV;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/*Tenemos un .csv en donde aparecen los repositorios más valorados de github.
Queremos un programa que devuelva los N repositios más valorados según el lenguaje de programación especificado por parametro */
public class CodelyTVMain {
    // 1 - Read CSV
    // 2 - Itera CSV recogiendo los registros de los repositorios con el lenguaje deseado hasta la N especificada por parámetro
    // 3- Devuelve resultado

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://raw.githubusercontent.com/EvanLi/Github-Ranking/master/Data/github-ranking-2022-06-01.csv");
        BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));

        /* Sabemos que la primera linea contiene la cabecera del CSV. La leemos y guardamos la información para poder discernir entre filas.
         por si en un futuro nos cambian de orden las filas*/
        String line = read.readLine();
        String [] cabecera = null;
        String [] contenido = null;
        String campoEvaluado = "language"; // TODO por el momento lo hacemos mock. Se tendría que coger esta info a partir de lo que especifique el usuario por consola
        int numeroDeResultadosDeseado= 5; // TODO por el momento lo hacemos mock.
        String tipoDelCampoEvaluadoDeseado = "JavaScript"; // TODO por el momento lo hacemos mock.
        ArrayList <String> resultado = new ArrayList<String>();
        if(line != null){ // First line
            cabecera = line.split(",");
        }
        int indexDelTipoDeseado = getIndex(campoEvaluado, cabecera);
        while ((line = read.readLine()) != null){
            System.out.println(line);
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
