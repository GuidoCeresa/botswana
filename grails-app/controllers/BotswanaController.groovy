

class BotswanaController {
    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def botswanaService = new BotswanaService()

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def distrettiService = new DistrettiService()

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def sottodistretti = new SottodistrettiService()

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def villaggiService = new VillaggiService()

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def localitaService = new LocalitaService()

    def index() {}

    def importaTutto() {
        distrettiService.importa()
        sottodistretti.importa()
        villaggiService.importa()
        localitaService.importa()
        redirect(action: "index", params: params)
    }// fine della closure

    def villaggiProva() {
        villaggiService.villaggiProva()
        redirect(action: "index", params: params)
    }// fine della closure

    def testVillaggi() {
        villaggiService.testVillaggi()
        redirect(action: "index", params: params)
    }// fine della closure

    def allVillaggi() {
        villaggiService.creaVillaggiSuWiki()
        redirect(action: "index", params: params)
    }// fine della closure

    def localitaProva() {
        localitaService.localitaProva()
        redirect(action: "index", params: params)
    }// fine della closure

    def testLocalita() {
        localitaService.testLocalita()
        redirect(action: "index", params: params)
    }// fine della closure

    def allLocalita() {
        localitaService.creaLocalitaSuWiki()
        redirect(action: "index", params: params)
    }// fine della closure

    def liste() {
        botswanaService.liste()
        redirect(action: "index", params: params)
    }// fine della closure
}
