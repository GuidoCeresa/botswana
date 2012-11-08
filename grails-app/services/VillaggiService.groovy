import botswana.Distretti
import botswana.LibFile
import botswana.Localita

import botswana.Sottodistretti
import botswana.Villaggi
import it.algos.algospref.Preferenze

class VillaggiService {
    private static int debug = 124

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service NON viene iniettato automaticamente
    def botswanaService = new BotswanaService()

    private static String ast = BotswanaService.ast
    private static String pipe = BotswanaService.pipe
    private static String spazio = BotswanaService.spazio
    private static String aCapo = BotswanaService.aCapo
    private static String par = BotswanaService.par

    // Controlla le coordinate di tutti i villaggi
    public void coordinate() {
        def villaggi
        Villaggi villaggio
        String titolo
        Pagina pagina
        String testoOld
        String testoNew
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login

        if (Villaggi.count() > 0) {
            villaggi = Villaggi.findAll([sort: "nome", order: "asc"])
            villaggi?.each {
                villaggio = it
                titolo = villaggio.nome
                pagina = new Pagina(titolo)
                testoOld = pagina.contenuto
                testoNew = testoOld
                testoNew = testoNew.replace('Latitudine decimale = 0', 'Latitudine decimale =')
                testoNew = testoNew.replace('Longitudine decimale = 0', 'Longitudine decimale =')
                if (!testoNew.equals(testoOld)) {
                    pagina.scrive(testoNew, 'fix coord zero')
                }// fine del blocco if
            }// fine del blocco if
        } // fine del ciclo each
    }// fine del metodo

    // importazione iniziale dei dati
    public String importa() {
        String messaggio = ''

        if (Sottodistretti.count() == 0) {
            messaggio = 'È meglio se importi prima i sottodistretti :-)'
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
        String query = 'delete Villaggi'
        Villaggi.executeUpdate(query)

        if (Villaggi.count() > 0) {
            messaggio = 'Attenzione - Non sono riuscito a cancellare i villaggi.'
        }// fine del blocco if

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // importa e crea tutta la tavola in maniera sincronizzata
    private String importaTavola() {
        String messaggio
        def nomeFile = botswanaService.dirPath + 'Villaggi'
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
            messaggio = 'Sono stati registrati ' + LibTesto.formatNum(recordsTotali.toString()) + ' villaggi.'
        } else {
            messaggio = 'Non è stato registrato nessun villaggio. Potrebbero esserci errori.'
        }// fine del blocco if-else

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // Crea un record con tutti i campi
    private boolean creaRecord(Map mappa) {
        boolean registrato = false
        Villaggi villaggio
        Villaggi villaggioRegistrato
        Sottodistretti sottodistretto = null
        String nomeSottodistretto
        def valueLat = 0
        def valueLong = 0

        // patch per valori mancanti
        if (mappa.get('latitudine')) {
            valueLat = mappa.get('latitudine')
        }// fine del blocco if
        if (mappa.get('longitudine')) {
            valueLong = mappa.get('longitudine')
        }// fine del blocco if

        // creazione link al subdistretto
        nomeSottodistretto = mappa.get('sottodistretto')
        if (nomeSottodistretto) {
            sottodistretto = Sottodistretti.findByCsonome(nomeSottodistretto)
            if (!sottodistretto) {
                log.error 'manca il sottodistretto corrispondente'
            }// fine del blocco if
        } else {
            log.error 'manca il nome del sottodistretto nel foglio elettronico'
        }// fine del blocco if-else

        villaggio = new Villaggi(
                csonome: mappa.get('csonome'),
                nome: mappa.get('nome'),
                sottodistretto: sottodistretto,
                popolazione: mappa.get('popolazione'),
                localita: mappa.get('localita'),
                latitudine: valueLat,
                longitudine: valueLong)
        villaggioRegistrato = villaggio.save()

        if (villaggioRegistrato) {
            registrato = true
        }// fine del blocco if

        // valore di ritorno
        return registrato
    }// fine del metodo

    // Test finale per controllo pagine villaggi esistenti
    // Cerca e scrive su wiki un elenco di pagine già esistenti
    def testVillaggi() {
        Villaggi villaggio = null
        def villaggi
        String titolo = 'Utente:Gac/Botswana2'
        boolean esistente
        ArrayList pagineEsistenti = new ArrayList()
        Pagina pagina
        String testo = ''
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login

        if (Villaggi.count() > 0) {
            villaggi = Villaggi.findAll([sort: "nome", order: "asc"])
            villaggi?.each {
                villaggio = it
                esistente = testPaginaVillaggio(villaggio)
                if (esistente) {
                    pagineEsistenti.add(villaggio.nome)
                }// fine del blocco if
            } // fine del ciclo each

            pagina = new Pagina(titolo)
            testo += 'Pagina provvisoria di controllo per i ' + pagineEsistenti.size() + ' villaggi esistenti'
            testo += aCapo
            testo += aCapo
            testo += '==Villaggi esistenti=='
            testo += aCapo

            pagineEsistenti?.each {
                testo += ast
                testo += LibWiki.setQuadre(it)
                testo += aCapo
            } // fine del ciclo each

            pagina.scrive(testo)
        } else {
            log.error 'non ci sono villaggi'
        }// fine del blocco if-else
    }// fine del metodo

    // Crea tutti i villaggi
    def creaVillaggiSuWiki() {
        Villaggi villaggio = null
        def villaggi
        boolean esistente
        ArrayList pagineEsistenti = new ArrayList()

        if (Villaggi.count() > 0) {
            villaggi = Villaggi.findAll([sort: "nome", order: "asc"])
            villaggi?.each {
                villaggio = it
                esistente = creaPaginaVillaggio(villaggio)
                if (esistente) {
                    pagineEsistenti.add(villaggio.nome)
                }// fine del blocco if
            } // fine del ciclo each

            pagineEsistenti?.each {
                log.error 'esiste già la pagina ' + villaggio.nome
            } // fine del ciclo each
        } else {
            log.error 'non ci sono villaggi'
        }// fine del blocco if-else
    }// fine del metodo

    // Controlla l'esistenza della pagina
    private boolean testPaginaVillaggio(Villaggi villaggio) {
        boolean esistente = true
        String titolo = villaggio.nome
        Pagina pagina
        Risultato risultato

        pagina = new Pagina(titolo)
        risultato = pagina.risultato
        if (risultato == Risultato.nonTrovata) {
            esistente = false
        } else {
            esistente = true
        }// fine del blocco if-else

        // valore di ritorno
        return esistente
    }// fine del metodo

    // Creo un villaggio definitivo
    // Se esiste già la pagina NON la sovrascrive
    // Prende nota del nome
    private boolean creaPaginaVillaggio(Villaggi villaggio) {
        boolean esistente = true
        String titolo = villaggio.nome
        String testo = this.creaTesto(villaggio)
        Pagina pagina
        Risultato risultato
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login

        if (testo) {
            pagina = new Pagina(titolo)
            risultato = pagina.risultato
            if (risultato == Risultato.nonTrovata) {
                esistente = false
                pagina.scrive(testo)
            } else {
                esistente = true
            }// fine del blocco if-else
        } else {
            log.error 'non sono riuscito a costruire il testo della voce: ' + villaggio.nome
        }// fine del blocco if-else

        // valore di ritorno
        return esistente
    }// fine del metodo

    // Creo un villaggio di prova nella mia pagina utente
    // Solo se esistono i records
    def villaggiProva() {
        Villaggi villaggio = null

        if (Villaggi.count() > 0) {
            villaggio = Villaggi.get(debug)
            if (villaggio) {
                this.villaggioProva(villaggio)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    // Creo un villaggio di prova nella mia pagina utente
    private villaggioProva(Villaggi villaggio) {
        String titolo = 'Utente:Gac/Botswana'
        String testo = this.creaTesto(villaggio)
        Pagina pagina
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login

        if (testo) {
            pagina = new Pagina(titolo)
            pagina.scrive(testo)
        }// fine del blocco if
    }// fine del metodo

    // Testo della pagina di prova
    private String creaTesto(Villaggi villaggio) {
        String testo = ''

        //     testo += creaTestoInit()
        testo += creaTestoTabella(villaggio)
        testo += creaTestoBody(villaggio)
        testo += creaTestoLocalita(villaggio)
        testo += botswanaService.creaTestoBibliografia()
        testo += botswanaService.creaTestoVociCorrelate()
        testo += botswanaService.creaTestoCollegamentiEsterni()
        testo += creaTestoFooter()

        // valore di ritorno
        return testo
    }// fine del metodo

    private String creaTestoInit() {
        String testo = ''

        testo += '{{S|città|Botswana}}'
        testo += aCapo
        testo += aCapo

        // valore di ritorno
        return testo
    }// fine del metodo


    private String creaTestoTabella(Villaggi villaggio) {
        String testo
        HashMap mappa = new HashMap()

        mappa.put('villaggio', villaggio)
        testo = botswanaService.creaTestoTabella(mappa)

        // valore di ritorno
        return testo
    }// fine del metodo


    private String creaTestoBody(Villaggi villaggio) {
        String testo = ''
        String nomeDistretto = botswanaService.getNomeDistrettoPerLink(villaggio)
        String nomeSottodistretto = botswanaService.getNomeSottodistrettoPerLink(villaggio)
        def abitanti = villaggio.popolazione
        abitanti = LibTesto.formatNum(abitanti.toString())

        testo += aCapo
        testo += LibWiki.setBold(villaggio.nome)
        testo += ' è un villaggio del [[Botswana]] situato nel '
        testo += LibWiki.setQuadre(nomeDistretto)
        testo += ', sottodistretto di '
        testo += LibWiki.setQuadre(nomeSottodistretto)
        testo += '. Il villaggio, secondo il censimento del [[2011]], conta '
        testo += abitanti
        testo += ' abitanti.'
        testo += aCapo

        // valore di ritorno
        return testo
    }// fine del metodo

    private String creaTestoLocalita(Villaggi villaggio) {
        String testo = ''
        String message
        def abitanti
        Localita singolaLocalita
        String nomeLocalita
        def localita = Localita.findAllByVillaggio(villaggio, [sort: "nome", order: "asc"])
        int sizeLocalita = localita.size()
        int numLocalita = villaggio.localita
        if (numLocalita != sizeLocalita) {
            message = 'il numero di località del villaggio '
            message += villaggio.nome
            message += ' sembra sbagliato'
            log.error message
        }// fine del blocco if

        if (localita) {
            testo += aCapo
            testo += '==Località=='
            testo += aCapo
            testo += 'Nel territorio del villaggio sono presenti le seguenti '
            testo += sizeLocalita
            testo += ' località:'

            localita.each {
                singolaLocalita = it
                abitanti = singolaLocalita.popolazione
                nomeLocalita = singolaLocalita.nome
                nomeLocalita = botswanaService.getNomeConPipe(nomeLocalita)

                testo += aCapo
                testo += ast
                if (abitanti > botswanaService.numeroMinimoAbitanti) {
                    testo += LibWiki.setQuadre(nomeLocalita)
                } else {
                    testo += "''"
                    testo += singolaLocalita.nome
                    testo += "''"
                }// fine del blocco if-else
                if (abitanti > 0) {
                    testo += ' di '
                    testo += LibTesto.formatNum(abitanti.toString())
                    testo += ' abitanti'
                }// fine del blocco if
                testo += ','
            } // fine del ciclo each

            if (testo.endsWith(',')) {
                testo = testo.substring(0, testo.length() - 1)
            }// fine del blocco if

            testo += aCapo
        }// fine del blocco if

        // valore di ritorno
        return testo
    }// fine del metodo


    private String creaTestoFooter() {
        String testo = ''

        testo += aCapo
        testo += '{{Portale|Africa|geografia}}'
        testo += aCapo
        testo += aCapo
        testo += '[[Categoria:Villaggi del Botswana]]'

        // valore di ritorno
        return testo
    }// fine del metodo


} // fine della service classe
