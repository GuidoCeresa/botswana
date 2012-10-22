/* Created by Algos s.r.l. */
/* Date: mag 2012 */
/* Questo file è stato installato dal plugin AlgosBase */
/* Tipicamente NON verrà più sovrascritto dalle successive release del plugin */
/* in quanto POTREBBE essere personalizzato in questa applicazione */
/* Se vuoi che le prossime release del plugin sovrascrivano questo file, */
/* perdendo tutte le modifiche effettuate qui, */
/* regola a true il flag di controllo flagOverwrite© */
/* flagOverwrite = false */



import org.springframework.dao.DataIntegrityViolationException
import VillaggiService
import botswana.Villaggi

class VillaggiController {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def villaggiService = new VillaggiService()

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def importa() {
        flash.message = villaggiService.importa()
        redirect(action: "list", params: params)
    }// fine della closure

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [villaggiInstanceList: Villaggi.list(params), villaggiInstanceTotal: Villaggi.count()]
    }

    def create() {
        [villaggiInstance: new Villaggi(params)]
    }

    def save() {
        def villaggiInstance = new Villaggi(params)
        if (!villaggiInstance.save(flush: true)) {
            render(view: "create", model: [villaggiInstance: villaggiInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'villages.label', default: 'Villages'), villaggiInstance.id])
        redirect(action: "show", id: villaggiInstance.id)
    }

    def show() {
        def villaggiInstance = Villaggi.get(params.id)
        if (!villaggiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'villages.label', default: 'Villages'), params.id])
            redirect(action: "list")
            return
        }

        [villaggiInstance: villaggiInstance]
    }

    def edit() {
        def villaggiInstance = Villaggi.get(params.id)
        if (!villaggiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'villages.label', default: 'Villages'), params.id])
            redirect(action: "list")
            return
        }

        [villaggiInstance: villaggiInstance]
    }

    def update() {
        def villaggiInstance = Villaggi.get(params.id)
        if (!villaggiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'villages.label', default: 'Villages'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (villaggiInstance.version > version) {
                villaggiInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'villages.label', default: 'Villages')] as Object[],
                        "Another user has updated this Villages while you were editing")
                render(view: "edit", model: [villagesInstance: villaggiInstance])
                return
            }
        }

        villaggiInstance.properties = params

        if (!villaggiInstance.save(flush: true)) {
            render(view: "edit", model: [villagesInstance: villaggiInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'villages.label', default: 'Villages'), villaggiInstance.id])
        redirect(action: "show", id: villaggiInstance.id)
    }

    def delete() {
        def villaggiInstance = Villaggi.get(params.id)
        if (!villaggiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'villages.label', default: 'Villages'), params.id])
            redirect(action: "list")
            return
        }

        try {
            villaggiInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'villages.label', default: 'Villages'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'villages.label', default: 'Villages'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
