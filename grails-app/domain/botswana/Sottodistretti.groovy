package botswana

class Sottodistretti {

    String csonome
    String nome
    String nomeBreve
    String nomeLink
    Distretti distretto
    int maschi
    int femmine
    int totale
    int villaggi
    int localita

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        csonome()
        nome()
        nomeBreve()
        nomeLink()
        distretto(nullable: true, blank: true)
        maschi()
        femmine()
        totale()
        villaggi()
        localita()
    } // end of static constraints

    static mapping = {
    } // end of static mapping

    // valore di testo restituito per una istanza della classe
    String toString() {
        nome
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * prima di cancellare un record
     */
    def beforeDelete = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo che il record è stato letto dal database e
     * le proprietà dell'oggetto sono state aggiornate
     */
    def onLoad = {
    } // end of def onLoad

} // fine della domain classe
