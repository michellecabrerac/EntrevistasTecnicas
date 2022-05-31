import java.util.HashMap;
/*
Entrevista técnica BetaTech:
Dado una cadena de texto, tenemos que sacar el número de veces que se repite este texto
*/
public class BetaTechMain {
    public static void main(String args[]){
        String cadenaAEvaluar = "hola hola hola!!! como como  estáis? yo yo yo yo yo estoy muy bien! y tú ?";
        contadorPalabras(cadenaAEvaluar);
    }
    public static void contadorPalabras(String cadenaAEvaluar){
        cadenaAEvaluar = limpiarCadena(cadenaAEvaluar);
        HashMap<String, Integer> contadorPalabras = new HashMap <String, Integer> ();
        String [] cadenaTexto = cadenaAEvaluar.split(" ");
        Integer contador;
        for(String cadena : cadenaTexto){
            if(contadorPalabras.containsKey(cadena)){
                contador = contadorPalabras.get(cadena);
                contador++;
                contadorPalabras.put(cadena, contador);
            }
            else{
                contadorPalabras.put(cadena, 1);
            }
        }
        System.out.println(contadorPalabras);
    }

    private static String limpiarCadena(String cadenaAEvaluar) {
        return cadenaAEvaluar.replaceAll("[^a-zA-Z ]", "");
    }

}
