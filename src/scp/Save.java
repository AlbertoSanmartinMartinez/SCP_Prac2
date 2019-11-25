
package scp;

class Save implements Runnable {

  private int start, end;
  private String outputDirectory;

  public Save(String _outputDirectory, int _start,int _end ){
      outputDirectory = _outputDirectory;
      start = _start;
      end = _end;
  }

  @Override
  public void run() {

      int numberOfFiles, remainingFiles;
      long remainingKeys=0, keysByFile=0;
      String key="";
      Charset utf8 = StandardCharsets.UTF_8;
      Set<String> keySet = Hash.keySet();
      int i = 1;
      
      // Calculamos el número de ficheros a crear en función del núemro de claves que hay en el hash.
      if (keySet.size()>DIndexMaxNumberOfFiles)
          numberOfFiles = DIndexMaxNumberOfFiles;
      else
          numberOfFiles = keySet.size();
      Iterator keyIterator = keySet.iterator();
      remainingKeys =  keySet.size();
      remainingFiles = numberOfFiles;
      // Bucle para recorrer los ficheros de indice a crear.
      for (;i<=start-1;i++)
      {
          keysByFile =  remainingKeys / remainingFiles;
          remainingKeys -= keysByFile;
          while (keyIterator.hasNext() && keysByFile>0) {
              key = (String) keyIterator.next();
              keysByFile--;
          }
          remainingFiles--;
      }
      for (;i<=end;i++)
      {
          try {
              File KeyFile = new File(outputDirectory + DIndexFilePrefix + String.format("%03d", i));
              FileWriter fw = new FileWriter(KeyFile);
              BufferedWriter bw = new BufferedWriter(fw);
              // Calculamos el número de claves a guardar en este fichero.
              keysByFile =  remainingKeys / remainingFiles;
              remainingKeys -= keysByFile;
              // Recorremos las claves correspondientes a este fichero.
              while (keyIterator.hasNext() && keysByFile>0) {
                  key = (String) keyIterator.next();
                  SaveIndexKey(key,bw);  // Salvamos la clave al fichero.
                  keysByFile--;
              }
              bw.close(); // Cerramos el fichero.
              remainingFiles--;
          } catch (IOException e) {
              System.err.println("Error opening Index file " + outputDirectory + "/IndexFile" + i);
              e.printStackTrace();
              System.exit(-1);
          }
      }
  }
  }
