import botswana.Villaggi
import botswana.Localita
import botswana.LibFile
import botswana.Distretti

import botswana.Sottodistretti
import it.algos.algospref.Preferenze

class LocalitaService {
    private static int debug = 3

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service NON viene iniettato automaticamente
    def botswanaService = new BotswanaService()

    private static String ast = BotswanaService.ast
    private static String pipe = BotswanaService.pipe
    private static String spazio = BotswanaService.spazio
    private static String aCapo = BotswanaService.aCapo
    private static String par = BotswanaService.par

    // Controlla le coordinate di tutte le località
    public String coordinate() {
        int min = BotswanaService.numeroMinimoAbitanti
        def allLocalita
        Localita localita
        String titolo
        Pagina pagina
        String testoOld
        String testoNew
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login

        if (Localita.count() > 0) {
            allLocalita = Localita.findAllByPopolazioneGreaterThan(min, [sort: "nome", order: "asc"])
            allLocalita?.each {
                localita = it
                titolo = localita.nome
                pagina = new Pagina(titolo)
                if (pagina.isValida()) {
                    testoOld = pagina.contenuto
                    testoNew = testoOld
                    testoNew = testoNew.replace('Latitudine decimale = 0', 'Latitudine decimale =')
                    testoNew = testoNew.replace('Longitudine decimale = 0', 'Longitudine decimale =')
                    if (!testoNew.equals(testoOld)) {
                        pagina.scrive(testoNew, 'fix coord zero')
                    }// fine del blocco if
                }// fine del blocco if

                def stop
            }// fine del blocco if
        } // fine del ciclo each
    }// fine del metodo

    // importazione iniziale dei dati
    public String importa() {
        String messaggio = ''

        if (Villaggi.count() == 0) {
            messaggio = 'È meglio se importi prima i villaggi :-)'
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
        String query = 'delete Localita'
        Localita.executeUpdate(query)

        if (Localita.count() > 0) {
            messaggio = 'Attenzione - Non sono riuscito a cancellare le località.'
        }// fine del blocco if

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // importa e crea tutta la tavola in maniera sincronizzata
    private String importaTavola() {
        String messaggio
        def nomeFile = botswanaService.dirPath + 'Localita'
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
            messaggio = 'Sono state registrate ' + LibTesto.formatNum(recordsTotali.toString()) + ' località.'
        } else {
            messaggio = 'Non è stata registrata nessun località. Potrebbero esserci errori.'
        }// fine del blocco if-else

        // valore di ritorno
        return messaggio
    }// fine del metodo

    // Crea un record con tutti i campi
    private boolean creaRecord(Map mappa) {
        boolean registrato = false
        Localita localita
        Localita localitaRegistrata
        Villaggi villaggio = null
        Sottodistretti sottodistretto = null
        String nomeVillaggio
        String nomeSottodistretto
        def valueLat = 0
        def valueLong = 0

        // creazione link al villaggio
        nomeVillaggio = mappa.get('villaggio')
        if (nomeVillaggio) {
            villaggio = Villaggi.findByCsonome(nomeVillaggio)
            if (!villaggio) {
                log.error 'manca il villaggio corrispondente'
            }// fine del blocco if
        }// fine del blocco if

        // creazione link al distretto
        nomeSottodistretto = mappa.get('sottodistretto')
        if (nomeSottodistretto) {
            sottodistretto = Sottodistretti.findByCsonome(nomeSottodistretto)
            if (!sottodistretto) {
                log.error 'manca il sottodistretto corrispondente'
            }// fine del blocco if
        } else {
            log.error 'manca il nome del sottodistretto nel foglio elettronico'
        }// fine del blocco if-else

        localita = new Localita(
                csonome: mappa.get('csonome'),
                nome: mappa.get('nome'),
                villaggio: villaggio,
                sottodistretto: sottodistretto,
                popolazione: mappa.get('popolazione'))
        localitaRegistrata = localita.save()

        if (localitaRegistrata) {
            registrato = true
        }// fine del blocco if

        // valore di ritorno
        return registrato
    }// fine del metodo

    // Test finale per controllo pagine localita esistenti
    // Cerca e scrive su wiki un elenco di pagine già esistenti
    def testLocalita() {
        Localita localita = null
        def allLocalita
        String titolo = 'Utente:Gac/Botswana4'
        boolean esistente
        ArrayList pagineEsistenti = new ArrayList()
        ArrayList pagineDaCreare = new ArrayList()
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login
        Pagina pagina
        String testo = ''
        String testoAbitanti = ''

        if (Localita.count() > 0) {

            if (botswanaService.calcolaDifferenzaNumeroAbitantiNeiVillaggi) {
                int max = 100
                int min = 50
                testoAbitanti = "che hanno più di $min abitanti"
                allLocalita = Localita.findAllByPopolazioneGreaterThan(BotswanaService.numeroMinimoAbitanti, [sort: "nome", order: "asc"])
                allLocalita?.each {
                    localita = it
                    if (localita.popolazione < max) {
                        esistente = testPaginaLocalita(localita)
                        if (esistente) {
                            pagineEsistenti.add(localita.nome)
                        }// fine del blocco if
                    }// fine del blocco if
                } // fine del ciclo each
            } else {
                int min = BotswanaService.numeroMinimoAbitanti
                testoAbitanti = "che hanno più di $min abitanti"
                allLocalita = Localita.findAllByPopolazioneGreaterThan(min, [sort: "nome", order: "asc"])
                allLocalita?.each {
                    localita = it
                    esistente = testPaginaLocalita(localita)
                    if (esistente) {
                        pagineEsistenti.add(localita.nome)
                    } else {
                        pagineDaCreare.add(localita.nome)
                    }// fine del blocco if-else
                } // fine del ciclo each
            }// fine del blocco if-else

            pagina = new Pagina(titolo)
            testo += 'Pagina provvisoria di controllo per le ' + allLocalita.size() + ' località esistenti '
            testo += testoAbitanti
            testo += "; " + pagineEsistenti.size() + " pagine esistono già e '''non''' vanno create."
            testo += aCapo
            testo += aCapo
            testo += '==Località esistenti=='
            testo += aCapo

            pagineEsistenti?.each {
                testo += ast
                testo += LibWiki.setQuadre(it)
                testo += aCapo
            } // fine del ciclo each

            testo += aCapo
            testo += '==Pagine da creare=='
            testo += aCapo

            pagineDaCreare?.each {
                testo += ast
                testo += LibWiki.setQuadre(it)
                testo += aCapo
            } // fine del ciclo each

            pagina.scrive(testo)
        } else {
            log.error 'non ci sono località'
        }// fine del blocco if-else
    }// fine del metodo

    // Crea tutti i villaggi
    def creaLocalitaSuWiki() {
        Localita localita = null
        def allLocalita
        boolean esistente
        ArrayList pagineEsistenti = new ArrayList()
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login

        if (Localita.count() > 0) {
            allLocalita = Localita.findAllByPopolazioneGreaterThan(BotswanaService.numeroMinimoAbitanti, [sort: "nome", order: "asc"])
            allLocalita?.each {
                localita = it
                esistente = creaPaginaLocalita(localita)
                if (esistente) {
                    pagineEsistenti.add(localita.nome)
                }// fine del blocco if
            } // fine del ciclo each

            pagineEsistenti?.each {
                log.error 'esiste già la pagina ' + it
            } // fine del ciclo each
        } else {
            log.error 'non ci sono località'
        }// fine del blocco if-else
    }// fine del metodo

    // Controlla l'esistenza della pagina
    private boolean testPaginaLocalita(Localita localita) {
        boolean esistente = true
        String titolo = localita.nome
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

    // Creo una località definitivo
    // Se esiste già la pagina NON la sovrascrive
    // Prende nota del nome
    private boolean creaPaginaLocalita(Localita localita) {
        boolean esistente = true
        String titolo = localita.nome
        String testo = this.creaTesto(localita)
        Pagina pagina
        Risultato risultato

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
            log.error 'non sono riuscito a costruire il testo della voce: ' + localita.nome
        }// fine del blocco if-else

        // valore di ritorno
        return esistente
    }// fine del metodo

    // Creo una localita di prova nella mia pagina utente
    // Solo se esistono i records
    def localitaProva() {
        Localita localita = null

        if (Localita.count() > 0) {
            localita = Localita.get(debug)
            if (localita) {
                singolaLocalitaProva(localita)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    // Creo una localita di prova nella mia pagina utente
    private singolaLocalitaProva(Localita localita) {
        String titolo = 'Utente:Gac/Botswana3'
        String testo = this.creaTesto(localita)
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
    private String creaTesto(Localita localita) {
        String testo = ''

        //     testo += creaTestoInit()
        testo += creaTestoTabella(localita)
        testo += creaTestoBody(localita)
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

    private String creaTestoTabella(Localita localita) {
        String testo = ''
        HashMap mappa = new HashMap()

        mappa.put('localita', localita)
        testo = botswanaService.creaTestoTabella(mappa)

        // valore di ritorno
        return testo
    }// fine del metodo


    private String creaTestoBody(Localita localita) {
        String testo
        def abitanti = localita.popolazione
        abitanti = LibTesto.formatNum(abitanti.toString())

        if (localita.villaggio) {
            testo = this.creaTestoBodyVillaggio(localita, abitanti)
        } else {
            testo = this.creaTestoBodySenzaVillaggio(localita, abitanti)
        }// fine del blocco if-else

        // valore di ritorno
        return testo
    }// fine del metodo

    private String creaTestoBodyVillaggio(Localita localita, String abitanti) {
        String testo = ''
        Villaggi villaggio
        String nomeVillaggio
        Sottodistretti sottodistretto
        Distretti distretto
        String nomeDistretto
        String nomeSottodistretto

        villaggio = localita.villaggio
        nomeVillaggio = villaggio.nome

        nomeDistretto = botswanaService.getNomeDistrettoPerLink(villaggio)
        nomeSottodistretto = botswanaService.getNomeSottodistrettoPerLink(villaggio)

        testo += aCapo
        testo += LibWiki.setBold(localita.nome)
        testo += ' è una località del [[Botswana]] situata nel '
        testo += LibWiki.setQuadre(nomeDistretto)
        testo += ', sottodistretto di '
        testo += LibWiki.setQuadre(nomeSottodistretto)
        testo += '. Insieme ad altre località costituisce il villaggio '
        testo += LibWiki.setQuadre(nomeVillaggio)
        testo += ' e, secondo il censimento del [[2011]], conta '
        testo += abitanti
        testo += ' abitanti.'
        testo += aCapo

        // valore di ritorno
        return testo
    }// fine del metodo


    private String creaTestoBodySenzaVillaggio(Localita localita, String abitanti) {
        String testo = ''
        Villaggi villaggio
        String nomeVillaggio
        Sottodistretti sottodistretto
        String nomeSottodistretto
        Distretti distretto
        String nomeDistretto

        sottodistretto = localita.sottodistretto
        nomeSottodistretto = sottodistretto.nomeLink
        distretto = sottodistretto.distretto
        nomeDistretto = distretto.nomeLink

        testo += aCapo
        testo += LibWiki.setBold(localita.nome)
        testo += ' è una località del [[Botswana]] situata nel '
        testo += LibWiki.setQuadre(nomeDistretto)
        testo += ', sottodistretto di '
        testo += LibWiki.setQuadre(nomeSottodistretto)
        testo += '. La località, secondo il censimento del [[2011]], conta '
        testo += abitanti
        testo += ' abitanti.'
        testo += aCapo

        // valore di ritorno
        return testo
    }// fine del metodo

    private String creaTestoFooter() {
        String testo = ''

        testo += aCapo
        testo += '{{Portale|Africa|geografia}}'
        testo += aCapo
        testo += aCapo
        testo += '[[Categoria:Località del Botswana]]'

        // valore di ritorno
        return testo
    }// fine del metodo

} // fine della service classe
