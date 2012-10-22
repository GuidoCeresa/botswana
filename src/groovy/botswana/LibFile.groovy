package botswana

import liquibase.util.csv.opencsv.CSVReader
import it.algos.algosbase.Lib
/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 27-9-12
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
class LibFile {
    private static String cvsSuffix = '.csv'

    // Legge un file formattato csv
    // Legge la prima riga dei titoli SOLO per creare le mappe
    // Crea una mappa (titolo=valore) per ogni riga (esclusi i titoli)
    // Titolo e valore sono SEMPRE stringhe
    // Restituisce, per ogni riga, una mappa con TUTTE le colonne
    // Eventualmente vuote
    public static ArrayList leggeCvs(String filePath) {
        ArrayList righe = null
        def titoli = null
        LinkedHashMap mappa
        CSVReader reader
        List listaRighe = null
        def singolaRiga

        // Controllo suffisso
        if (!filePath.endsWith(cvsSuffix)) {
            filePath += cvsSuffix
        }// fine del blocco if

        try { // prova ad eseguire il codice
            reader = new CSVReader(new FileReader(filePath));
        } catch (Exception unErrore) { // intercetta l'errore
            //log.error 'Non ho trovato il file '+filePath
        }// fine del blocco try-catch

        if (reader) {
            titoli = reader.readNext()
            listaRighe = reader.readAll()
        }// fine del blocco if

        if (listaRighe) {
            righe = new ArrayList()
            listaRighe.each {
                singolaRiga = it
                mappa = new LinkedHashMap()
                for (int k = 0; k < titoli.length; k++) {
                    mappa.put(titoli[k], singolaRiga[k])
                } // fine del ciclo for
                righe.add(mappa)
            } // fine del ciclo each
        }// fine del blocco if

        // valore di ritorno
        return righe
    }// fine della closure

} //fine della classe
