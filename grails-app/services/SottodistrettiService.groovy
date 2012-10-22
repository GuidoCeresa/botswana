import botswana.Distretti
import botswana.LibFile

import botswana.Sottodistretti

class SottodistrettiService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service NON viene iniettato automaticamente
    def botswanaService = new BotswanaService()

    // importazione iniziale dei dati
    public String importa() {
        String messaggio = ''

        if (Distretti.count() == 0) {
            messaggio = 'È meglio se importi prima i distretti :-)'
        }// fine del blocco if

        if (!messaggio) {
            messaggio = this.reset()
        }// fine del blocco if

        if (!messaggio) {
            messaggio = this.importaTavola()
        }// fine del blocco if

        // valore di ritorno
        return messaggio
    }// fine della closure

    // cancella tutto
    private String reset() {
        String messaggio = ''
        String query = 'delete Sottodistretti'
        Sottodistretti.executeUpdate(query)

        if (Sottodistretti.count() > 0) {
            messaggio = 'Attenzione - Non sono riuscito a cancellare i subdistretti.'
        }// fine del blocco if

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // importa e crea tutta la tavola in maniera sincronizzata
    private String importaTavola() {
        String messaggio
        def nomeFile = botswanaService.dirPath + 'Sottodistretti'
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
            messaggio = 'Sono stati registrati ' + LibTesto.formatNum(recordsTotali.toString()) + ' sottodistretti.'
        } else {
            messaggio = 'Non è stato registrato nessun sottodistretti. Potrebbero esserci errori.'
        }// fine del blocco if-else

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // Crea un record con tutti i campi
    private boolean creaRecord(Map mappa) {
        boolean registrato = false
        Distretti distretto = null
        String nomeBreveDistretto
        Sottodistretti sottodistretto
        Sottodistretti sottodistrettooRegistrato

        // creazione link al subdistretto
        nomeBreveDistretto = mappa.get('distretto')
        if (nomeBreveDistretto) {
            distretto = Distretti.findByNomeBreve(nomeBreveDistretto)
            if (!distretto) {
                log.error 'manca il distretto corrispondente'
            }// fine del blocco if
        } else {
            log.error 'manca il nome del distretto nel foglio elettronico di: ' + mappa.get('nome')
        }// fine del blocco if-else

        sottodistretto = new Sottodistretti(
                csonome: mappa.get('csonome'),
                nome: mappa.get('nome'),
                nomeBreve: mappa.get('nomebreve'),
                nomeLink: mappa.get('nomelink'),
                distretto: distretto,
                maschi: mappa.get('maschi'),
                femmine: mappa.get('femmine'),
                totale: mappa.get('totale'),
                villaggi: mappa.get('villaggi'),
                localita: mappa.get('localita'))
        sottodistrettooRegistrato = sottodistretto.save()

        if (sottodistrettooRegistrato) {
            registrato = true
        }// fine del blocco if

        // valore di ritorno
        return registrato
    }// fine del metodo

} // fine della service classe
