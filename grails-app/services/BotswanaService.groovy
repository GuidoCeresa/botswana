import java.lang.reflect.Array
import liquibase.util.csv.opencsv.CSVReader
import botswana.Sottodistretti
import botswana.Distretti
import botswana.Villaggi
import botswana.Localita
import it.algos.algospref.Preferenze


class BotswanaService {
    public static int numeroMinimoAbitanti = 50
    public static boolean calcolaDifferenzaNumeroAbitantiNeiVillaggi = true
    public static boolean mostraAncheLocalitàSenzaVoce = true
    public static boolean usaTreColonne = true
    public static boolean usaCassetto = false

    public static String dirPath = '/Users/Gac/Documents/Botswana/'
    private static String txtSuffix = '.txt'
    private static String cvsSuffix = '.csv'

    public static String ast = '*'
    public static String pipe = '|'
    public static String spazio = ' '
    public static String aCapo = '\n'
    public static String par = '('

    // Crea una pagina di controllo con la lista di tutti i villaggi/località, suddivisi per sottodistretto
    public String liste() {
        String titoloBase = 'Utente:Gac/Botswana'
        String titolo = ''
        String nick = Preferenze.getStr('nick')
        String password = Preferenze.getStr('password')
        Login login = new Login('it', nick, password)
        assert login.isCollegato()
        Pagina.login = login
        Pagina pagina
        String testo = ''
        String testoElenco = ''
        def elencoSottodistretti = Sottodistretti.getAll()
        Sottodistretti sottoDistretto
        int num = 1
        def elencoVillaggi
        Villaggi villaggio
        def elencoLocalità
        Localita localita
        ArrayList listaVillaggi
        ArrayList listaLocalita

        elencoSottodistretti?.each {
            sottoDistretto = it
            //num++
            elencoVillaggi = Villaggi.findAllBySottodistretto(sottoDistretto, [sort: "nome", order: "asc"])
            elencoLocalità = Localita.findAllBySottodistretto(sottoDistretto, [sort: "nome", order: "asc"])
            listaVillaggi = new ArrayList()
            listaLocalita = new ArrayList()

            testo = 'Pagina provvisoria di controllo per i ' + elencoVillaggi.size() + ' villaggi e le ' + elencoLocalità.size() + ' località del ' + LibWiki.setQuadre(getNomeConPipe(sottoDistretto.nome))
            testo += aCapo
            testo += aCapo

            testo += '==Villaggi=='
            testo += aCapo
            elencoVillaggi?.each {
                villaggio = it
                listaVillaggi.add(LibWiki.setQuadre(getNomeBotswanaConPipe(villaggio.nome)))
            } // fine del ciclo each
            listaVillaggi = listaTreColonne(listaVillaggi)
            testoElenco = this.getTestoDaLista(listaVillaggi)
            if (usaCassetto) {
                testoElenco = this.getCassetto('Lista dei villaggi', testoElenco)
            }// fine del blocco if
            testo += testoElenco
            testo += aCapo
            testo += aCapo

            testo += '==Località=='
            testo += aCapo
            elencoLocalità?.each {
                localita = it
                if (localita.popolazione > numeroMinimoAbitanti) {
                    listaLocalita.add(LibWiki.setQuadre(getNomeBotswanaConPipe(localita.nome)))
                } else {
                    if (mostraAncheLocalitàSenzaVoce) {
                        listaLocalita.add("''" + localita.nome + "''")
                    }// fine del blocco if
                }// fine del blocco if-else
            } // fine del ciclo each
            listaLocalita = this.listaTreColonne(listaLocalita)
            testoElenco = this.getTestoDaLista(listaLocalita)
            if (usaCassetto) {
                testoElenco = this.getCassetto('Lista delle località', testoElenco)
            }// fine del blocco if
            testo += testoElenco
            testo += aCapo

            titolo = titoloBase + num
            pagina = new Pagina(titolo)
            pagina.scrive(testo)
            def stop
        } // fine del ciclo each
    }// fine della closure

    /**
     * Suddivide la lista in tre colonne.
     *
     * @param listaIn in ingresso
     * @return listaOut in uscita
     */
    public static listaTreColonne(ArrayList<String> listaIn) {
        // variabili e costanti locali di lavoro
        ArrayList<String> listaOut = (ArrayList<String>) listaIn.clone()
        String tagIni = '{{MultiCol}}'
        String tagColonna = '{{ColBreak}}'
        String tagEnd = '{{EndMultiCol}}'
        int dim
        int primoTag
        int secondoTag
        int resto

        if (listaIn) {
            dim = listaIn.size()
            primoTag = dim / 3
            resto += dim % 3
            switch (dim) {
                case 0:
                case 1:
                    return listaOut
                    break
                case 2:
                    primoTag = 1
                    listaOut.add(primoTag, tagColonna)
                    listaOut.add(0, tagIni)
                    listaOut.add(tagEnd)
                    return listaOut
                    break
                case 3:
                    primoTag = 1
                    secondoTag = 2
                    listaOut.add(secondoTag, tagColonna)
                    listaOut.add(primoTag, tagColonna)
                    listaOut.add(0, tagIni)
                    listaOut.add(tagEnd)
                    return listaOut
                    break
                default: // caso non definito
                    secondoTag = primoTag * 2
                    if (resto > 0) {
                        primoTag++
                        secondoTag++
                    }// fine del blocco if
                    if (resto > 1) {
                        secondoTag++
                    }// fine del blocco if
                    listaOut.add(secondoTag, tagColonna)
                    listaOut.add(primoTag, tagColonna)
                    listaOut.add(0, tagIni)
                    listaOut.add(tagEnd)
                    return listaOut
                    break
            } // fine del blocco switch
        }// fine del blocco if

        // valore di ritorno
        return listaOut
    } // fine del metodo

    public static String getCassetto(String titolo, String testoIn) {
        String testoOut = ''
        testoOut += aCapo
        testoOut += '{{cassetto'
        testoOut += aCapo
        testoOut += '|larghezza=100%'
        testoOut += aCapo
        testoOut += "|titolo= $titolo"
        testoOut += aCapo
        testoOut += '|testo='
        testoOut += aCapo
        testoOut += testoIn
        testoOut += aCapo
        testoOut += '}}'
        testoOut += aCapo
    } // fine del metodo

    /**
     * Espande la lista in un testo
     *
     * @param lista in ingresso
     * @return testo
     */
    public static getTestoDaLista(ArrayList<String> lista) {
        // variabili e costanti locali di lavoro
        String testo = ''

        lista?.each {
            testo += ast
            testo += it
            testo += aCapo
        } // fine del ciclo each

        // valore di ritorno
        return testo.trim()
    } // fine del metodo

    public String leggeFile(String fileName) {
        String testo = ''
        String filePath = dirPath + fileName + txtSuffix
        File file = new File(filePath)

        if (file.exists()) {
            file.eachLine {
                testo += it
            }
        }// fine del blocco if

        // valore di ritorno
        return testo
    }// fine della closure

    public ArrayList leggeRighe(String fileName) {
        ArrayList righe = null
        String filePath = dirPath + fileName + txtSuffix
        File file = new File(filePath)

        if (file.exists()) {
            righe = new ArrayList()
            file.eachLine {
                righe.add(it)
            }
        }// fine del blocco if

        // valore di ritorno
        return righe
    }// fine della closure

// Legge un file formattato csv
// Funziona se le singole righe sono lunghe come i titoli
// Crea una mappa (titolo=valore) per ogni riga
    public ArrayList leggeCvs(String fileName, ArrayList titoli) {
        ArrayList righe = null
        String filePath = dirPath + fileName + cvsSuffix
        File file = new File(filePath)
        HashMap mappa
        CSVReader reader
        List myEntries

        try { // prova ad eseguire il codice
            reader = new CSVReader(new FileReader(filePath));
        } catch (Exception unErrore) { // intercetta l'errore
            log.error 'Non ho trovato il file ' + filePath
        }// fine del blocco try-catch

        if (reader) {
            def a = reader.readNext()
            myEntries = reader.readAll()
            def stop
        }// fine del blocco if

        // valore di ritorno
        return righe
    }// fine della closure

    // Leva la scritta iniziale -distretto-
    // Leva la particella -di-
    // Leva il nome finale dello stato tra parentesi
    public String fixNomeDistretto(String nomeIn) {
        String nomeOut = nomeIn.trim()
        String tagDis = 'Distretto'
        String tagArt = 'di'
        int pos = 0

        if (nomeOut.startsWith(tagDis)) {
            nomeOut = LibTesto.levaTesta(nomeOut, tagDis)
        }// fine del blocco if

        if (nomeOut.startsWith(tagArt)) {
            nomeOut = LibTesto.levaTesta(nomeOut, tagArt)
        }// fine del blocco if

        nomeOut = this.getLevaParentesi(nomeOut)

        // valore di ritorno
        return nomeOut
    }// fine del metodo

    // Leva il nome finale dello stato tra parentesi
    public String getLevaParentesi(String nomeIn) {
        String nomeOut = nomeIn.trim()
        int pos = 0

        if (nomeOut.contains(par)) {
            pos = nomeOut.indexOf(par)
            nomeOut = nomeOut.substring(0, pos)
            nomeOut = nomeOut.trim()
        }// fine del blocco if

        // valore di ritorno
        return nomeOut
    }// fine del metodo

    public Sottodistretti getSottodistretto(Villaggi villaggio) {
        Sottodistretti sottodistretto = null

        if (villaggio) {
            sottodistretto = villaggio.sottodistretto
        }// fine del blocco if

        // valore di ritorno
        return sottodistretto
    }// fine del metodo

    public String getNomeSottodistretto(Villaggi villaggio) {
        String nomeSottodistretto = ''
        Sottodistretti sottodistretto = null

        if (villaggio) {
            sottodistretto = this.getSottodistretto(villaggio)
            if (sottodistretto) {
                nomeSottodistretto = sottodistretto.nome
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return nomeSottodistretto
    }// fine del metodo

    public String getNomeSottodistrettoLink(Villaggi villaggio) {
        String nomeSottodistretto = ''
        Sottodistretti sottodistretto = null

        if (villaggio) {
            sottodistretto = this.getSottodistretto(villaggio)
            if (sottodistretto) {
                nomeSottodistretto = sottodistretto.nomeLink
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return nomeSottodistretto
    }// fine del metodo

    public Distretti getDistretto(Villaggi villaggio) {
        Distretti distretto = null
        Sottodistretti sottodistretto = null

        if (villaggio) {
            sottodistretto = this.getSottodistretto(villaggio)
            if (sottodistretto) {
                distretto = sottodistretto.distretto
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return distretto
    }// fine del metodo

    public String getNomeDistretto(Villaggi villaggio) {
        String nomeDistretto = ''
        Distretti distretto = null

        if (villaggio) {
            distretto = this.getDistretto(villaggio)
            if (distretto) {
                nomeDistretto = distretto.nome
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return nomeDistretto
    }// fine del metodo

    public String getNomeDistrettoLink(Villaggi villaggio) {
        String nomeDistretto = ''
        Distretti distretto = null

        if (villaggio) {
            distretto = this.getDistretto(villaggio)
            if (distretto) {
                nomeDistretto = distretto.nomeLink
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return nomeDistretto
    }// fine del metodo

    public String getNomeDistrettoPerTemplate(Villaggi villaggio) {
        String nomeDistretto = ''

        if (villaggio) {
            nomeDistretto = this.getNomeDistretto(villaggio)
            if (nomeDistretto) {
                nomeDistretto = this.fixNomeDistretto(nomeDistretto)
            }// fine del blocco if
        }// fine del blocco if

        // valore di ritorno
        return nomeDistretto
    }// fine del metodo

    public String getNomeDistrettoPerLink(Villaggi villaggio) {
        String nomeDistretto = ''

        if (villaggio) {
            nomeDistretto = this.getNomeDistrettoLink(villaggio)
        }// fine del blocco if

        // valore di ritorno
        return nomeDistretto
    }// fine del metodo

    public String getNomeSottodistrettoPerTemplate(Villaggi villaggio) {
        String nomeSottodistretto = ''

        if (villaggio) {
            nomeSottodistretto = this.getNomeSottodistretto(villaggio)
            nomeSottodistretto = this.getLevaParentesi(nomeSottodistretto)
        }// fine del blocco if

        // valore di ritorno
        return nomeSottodistretto
    }// fine del metodo

    public String getNomeSottodistrettoPerLink(Villaggi villaggio) {
        String nomeSottodistretto = ''

        if (villaggio) {
            nomeSottodistretto = this.getNomeSottodistrettoLink(villaggio)
        }// fine del blocco if

        // valore di ritorno
        return nomeSottodistretto
    }// fine del metodo

    public String getNomeConPipe(String nomeIn) {
        String nomeOut = nomeIn

        if (nomeIn && nomeIn.contains(par)) {
            nomeOut = nomeIn + pipe
        }// fine del blocco if

        // valore di ritorno
        return nomeOut
    }// fine del metodo

    public String getNomeBotswanaConPipe(String nomeIn) {
        String nomeOut = nomeIn
        String tag = '(Botswana)'

        if (nomeIn && nomeIn.endsWith(tag)) {
            nomeOut = nomeIn + pipe
        }// fine del blocco if

        // valore di ritorno
        return nomeOut
    }// fine del metodo

    public String creaTestoBibliografia() {
        String testo = ''

        testo += aCapo
        testo += '==Bibliografia=='
        testo += aCapo
        testo += ast
        testo += "[http://www.cso.gov.bw/media/2011%20Census%20_Alphabetical%20Index%20of%20Districts.pdf ''2011 Census Alphabetical Index of Districts''] del Central Statistics Office del Botswana"
        testo += aCapo
        testo += ast
        testo += "[http://www.cso.gov.bw/media/2011%20Census%20_Alphabetical%20Index%20_Population%20of%20Villages.pdf ''2011 Census Alphabetical Index of Villages''] del Central Statistics Office del Botswana"
        testo += aCapo
        testo += ast
        testo += "[http://www.cso.gov.bw/media/2011%20Census%20_Alphabetical%20Index%20_Population%20of%20Localities.pdf ''2011 Census Alphabetical Index of Localities''] del Central Statistics Office del Botswana"
        testo += aCapo

        // valore di ritorno
        return testo
    }// fine del metodo

    public String creaTestoVociCorrelate() {
        String testo = ''

        testo += aCapo
        testo += '==Voci correlate=='
        testo += aCapo
        testo += ast
        testo += LibWiki.setQuadre('Suddivisioni del Botswana')
        testo += aCapo
        testo += ast
        testo += LibWiki.setQuadre('Distretti del Botswana')
        testo += aCapo
        testo += ast
        testo += LibWiki.setQuadre('Sottodistretti del Botswana')

        // valore di ritorno
        return testo
    }// fine del metodo


    public String creaTestoCollegamentiEsterni() {
        String testo = ''

        testo += aCapo
        testo += '==Collegamenti esterni=='
        testo += aCapo
        testo += ast
        testo += '[http://www.cso.gov.bw/ Ufficio centrale di statistiche del Botswana]'
        testo += aCapo

        // valore di ritorno
        return testo
    }// fine del metodo

    public String creaTestoTabella(HashMap mappaIn) {
        String testo = ''
        HashMap mappa
        mappa = this.regolaMappa(mappaIn)

        String nome = mappa.get('nome')
        String livello = mappa.get('livello')
        String nomeDistrettoTemplate = mappa.get('nomeDistrettoTemplate')
        String nomeDistrettoLink = mappa.get('nomeDistrettoLink')
        String nomeSottodistrettoTemplate = mappa.get('nomeSottodistrettoTemplate')
        String nomeSottodistrettoLink = mappa.get('nomeSottodistrettoLink')
        String nomeVillaggioTemplate = mappa.get('nomeVillaggioTemplate')
        String nomeVillaggioLink = mappa.get('nomeVillaggioLink')
        String latitudine = mappa.get('latitudine')
        String longitudine = mappa.get('longitudine')
        String popolazione = mappa.get('popolazione')

        testo += '{{Divisione amministrativa'
        testo += aCapo
        testo += '|Nome = '
        testo += nome
        testo += aCapo
        testo += '|Nome ufficiale ='
        testo += aCapo
        testo += '|Panorama ='
        testo += aCapo
        testo += '|Didascalia ='
        testo += aCapo
        testo += '|Bandiera ='
        testo += aCapo
        testo += '|Stemma ='
        testo += aCapo
        testo += '|Stato = BWA'
        testo += aCapo
        testo += '|Grado amministrativo = '
        testo += livello
        testo += aCapo
        testo += '|Divisione amm grado 1 = '
        testo += nomeDistrettoTemplate
        testo += aCapo
        testo += '|Voce divisione amm grado 1 = '
        testo += nomeDistrettoLink
        testo += aCapo
        testo += '|Divisione amm grado 2 = '
        testo += nomeSottodistrettoTemplate
        testo += aCapo
        testo += '|Voce divisione amm grado 2 = '
        testo += nomeSottodistrettoLink
        testo += aCapo
        testo += '|Divisione amm grado 3 ='
        testo += nomeVillaggioTemplate
        testo += aCapo
        testo += '|Voce divisione amm grado 3 = '
        testo += nomeVillaggioLink
        testo += aCapo
        testo += '|Divisione amm grado 4 ='
        testo += aCapo
        testo += '|Amministratore locale ='
        testo += aCapo
        testo += '|Partito ='
        testo += aCapo
        testo += '|Data elezione ='
        testo += aCapo
        testo += '|Data istituzione ='
        testo += aCapo
        testo += '|Data soppressione ='
        testo += aCapo
        testo += '|Latitudine decimale = '
        testo += this.setDecimale(latitudine)
        testo += aCapo
        testo += '|Longitudine decimale = '
        testo += this.setDecimale(longitudine)
        testo += aCapo
        testo += '|Latitudine gradi ='
        testo += aCapo
        testo += '|Latitudine minuti ='
        testo += aCapo
        testo += '|Latitudine secondi ='
        testo += aCapo
        testo += '|Latitudine NS = S'
        testo += aCapo
        testo += '|Longitudine gradi ='
        testo += aCapo
        testo += '|Longitudine minuti ='
        testo += aCapo
        testo += '|Longitudine secondi ='
        testo += aCapo
        testo += '|Longitudine EW = E'
        testo += aCapo
        testo += '|Altitudine ='
        testo += aCapo
        testo += '|Superficie ='
        testo += aCapo
        testo += '|Note superficie ='
        testo += aCapo
        testo += '|Abitanti = '
        testo += popolazione
        testo += aCapo
        testo += '|Note abitanti ='
        testo += aCapo
        testo += '|Aggiornamento abitanti = 2011'
        testo += aCapo
        testo += '|Sottodivisioni ='
        testo += aCapo
        testo += '|Divisioni confinanti ='
        testo += aCapo
        testo += '|Lingue ='
        testo += aCapo
        testo += '|Codice postale ='
        testo += aCapo
        testo += '|Prefisso ='
        testo += aCapo
        testo += '|Fuso orario ='
        testo += aCapo
        testo += '|Codice statistico ='
        testo += aCapo
        testo += '|Codice catastale ='
        testo += aCapo
        testo += '|Targa ='
        testo += aCapo
        testo += '|Nome abitanti ='
        testo += aCapo
        testo += '|Patrono ='
        testo += aCapo
        testo += '|Festivo ='
        testo += aCapo
        testo += '|Mappa ='
        testo += aCapo
        testo += '|Didascalia mappa ='
        testo += aCapo
        testo += '|Sito ='
        testo += aCapo
        testo += '}}'

        // valore di ritorno
        return testo
    }// fine del metodo

    private String setDecimale(String coordinataIn) {
        String coordinataOut = coordinataIn
        String sepVir = ','
        String sepPun = '.'

        if (coordinataIn.contains(sepVir)) {
            coordinataOut = coordinataIn.replace(sepVir, sepPun)
        }// fine del blocco if

        // valore di ritorno
        return coordinataOut
    }// fine del metodo

    public HashMap regolaMappa(HashMap mappaIn) {
        HashMap mappaOut = new HashMap()

        if (mappaIn.get('villaggio')) {
            Villaggi villaggio = (Villaggi) mappaIn.get('villaggio')
            Sottodistretti sottodistretto = villaggio.sottodistretto
            Distretti distretto = sottodistretto.distretto
            mappaOut.put('nome', villaggio.nome)
            mappaOut.put('livello', 3)
            mappaOut.put('nomeDistrettoTemplate', distretto.nomeBreve)
            mappaOut.put('nomeDistrettoLink', distretto.nomeLink)
            mappaOut.put('nomeSottodistrettoTemplate', sottodistretto.nomeBreve)
            mappaOut.put('nomeSottodistrettoLink', sottodistretto.nome)
            mappaOut.put('nomeVillaggioTemplate', '')
            mappaOut.put('nomeVillaggioLink', '')
            mappaOut.put('latitudine', setDecimale(villaggio.latitudine))
            mappaOut.put('longitudine', setDecimale(villaggio.longitudine))
            mappaOut.put('popolazione', villaggio.popolazione)
        }// fine del blocco if-else

        if (mappaIn.get('localita')) {
            Localita localita = (Localita) mappaIn.get('localita')
            Sottodistretti sottodistretto = localita.sottodistretto
            Distretti distretto = sottodistretto.distretto
            mappaOut.put('nome', localita.nome)
            mappaOut.put('livello', 3)
            mappaOut.put('nomeDistrettoTemplate', distretto.nomeBreve)
            mappaOut.put('nomeDistrettoLink', distretto.nomeLink)
            mappaOut.put('nomeSottodistrettoTemplate', sottodistretto.nomeBreve)
            mappaOut.put('nomeSottodistrettoLink', sottodistretto.nome)
            mappaOut.put('latitudine', 0)
            mappaOut.put('longitudine', 0)
            mappaOut.put('popolazione', localita.popolazione)

            if (localita.villaggio) {
                Villaggi villaggio = localita.villaggio
                mappaOut.put('livello', 4)
                mappaOut.put('nomeVillaggioTemplate', villaggio.nome)
                mappaOut.put('nomeVillaggioLink', villaggio.nome)
            } else {
                mappaOut.put('livello', 3)
                mappaOut.put('nomeVillaggioTemplate', '')
                mappaOut.put('nomeVillaggioLink', '')
            }// fine del blocco if-else

        }// fine del blocco if-else

        // valore di ritorno
        return mappaOut
    }// fine del metodo

} // fine della service classe
