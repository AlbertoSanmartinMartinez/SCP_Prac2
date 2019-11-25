
/**
 *
 * @author albertosanmartinmartinez
 */

package scp;

//import scp.*;

//import InvertedIndex;

public class Indexing {

    public static void main(String[] args) {

        InvertedIndex hash;

        // debug
        //System.out.println(args.length);
        //System.out.println(args);
        //System.out.println(args[0]);  //ficehro de entrada a indexar
        //System.out.println(args[1]);  //numero de hilos para la indxacion
        //System.out.println(args[2]);  //
        //System.out.println(args[3]);

        if (args.length < 2 || args.length > 4) {
            //System.err.println("Erro in Parameters. Usage: Indexing <TextFile> [<Key_Size>] [<Index_Directory>]");  //args error log for secuential
            System.err.println("Erro in Parameters. Usage: Indexing <TextFile> <Threads_Number> [<Key_Size>] [<Index_Directory>]");   //args error log for concurrent
        }
        // not optional arguments
        if (args.length < 2)
            hash = new InvertedIndex(args[0]);

        // optional arguments
        else
            hash = new InvertedIndex(args[0], Integer.parseInt(args[1]));

        System.exit(-1);

        //crea el indice invertido
        hash.BuildIndex();

        // guarda el fichero de los incides invertidos o lo muestra por pantalla
        if (args.length > 2)
            //hash.SaveIndex(args[2]);
            hash.SaveIndex2(args[2]);
        else
            hash.PrintIndex();
    }

}

