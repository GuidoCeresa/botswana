import botswana.Distretti
import botswana.LibFile

class DistrettiService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service NON viene iniettato automaticamente
    def botswanaService = new BotswanaService()

    // importazione iniziale dei dati
    public String importa() {
        String messaggio = ''

        messaggio = this.reset()
        if (!messaggio) {
            messaggio = this.importaTavola()
        }// fine del blocco if

        // valore di ritorno
        return messaggio
    }// fine della closure

    // cancella tutto
    private String reset() {
        String messaggio = ''
        String query = 'delete Distretti'
        Distretti.executeUpdate(query)

        if (Distretti.count() > 0) {
            messaggio = 'Attenzione - Non sono riuscito a cancellare i distretti.'
        }// fine del blocco if

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // importa e crea tutta la tavola in maniera sincronizzata
    private String importaTavola() {
        String messaggio
        def nomeFile = botswanaService.dirPath + 'Distretti'
        def righe
        int recordsTotali = 0
        boolean registrato
        righe = LibFile.leggeCvs(nomeFile)

        righe?.each {
            registrato = this.creaRecord((LinkedHashMap) it)
            if (registrato) {
                recordsTotali++
            }// fine del blocco if
        } // fine del ciclo each

        if (recordsTotali > 0) {
            messaggio = 'Sono stati registrati ' + LibTesto.formatNum(recordsTotali.toString()) + ' distretti.'
        } else {
            messaggio = 'Non è stato registrato nessun distretto. Potrebbero esserci errori.'
        }// fine del blocco if-else

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // Crea un record con tutti i campi
    private boolean creaRecord(Map mappa) {
        boolean registrato = false
        Distretti distretto
        Distretti distrettoRegistrato

        distretto = new Distretti(
                nomeBreve: mappa.get('nomebreve'),
                nome: mappa.get('nome'),
                nomeLink: mappa.get('nomelink'))
        distrettoRegistrato = distretto.save()

        if (distrettoRegistrato) {
            registrato = true
        }// fine del blocco if

        // valore di ritorno
        return registrato
    }// fine del metodo

} // fine della service classe
