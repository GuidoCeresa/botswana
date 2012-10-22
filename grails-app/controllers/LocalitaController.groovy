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

import botswana.Localita

class LocalitaController {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def localitaService = new LocalitaService()

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def importa() {
        localitaService.importa()
        redirect(action: "list", params: params)
    }// fine della closure

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [localitaInstanceList: Localita.list(params), localitaInstanceTotal: Localita.count()]
    }

    def create() {
        [localitaInstance: new Localita(params)]
    }

    def save() {
        def localitaInstance = new Localita(params)
        if (!localitaInstance.save(flush: true)) {
            render(view: "create", model: [localitiesInstance: localitaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'localities.label', default: 'Localita'), localitaInstance.id])
        redirect(action: "show", id: localitaInstance.id)
    }

    def show() {
        def localitaInstance = Localita.get(params.id)
        if (!localitaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localities.label', default: 'Localita'), params.id])
            redirect(action: "list")
            return
        }

        [localitaInstance: localitaInstance]
    }

    def edit() {
        def localitaInstance = Localita.get(params.id)
        if (!localitaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localities.label', default: 'Localita'), params.id])
            redirect(action: "list")
            return
        }

        [localitaInstance: localitaInstance]
    }

    def update() {
        def localitaInstance = Localita.get(params.id)
        if (!localitaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localities.label', default: 'Localita'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (localitaInstance.version > version) {
                localitaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'localities.label', default: 'Localita')] as Object[],
                        "Another user has updated this Localita while you were editing")
                render(view: "edit", model: [localitiesInstance: localitaInstance])
                return
            }
        }

        localitaInstance.properties = params

        if (!localitaInstance.save(flush: true)) {
            render(view: "edit", model: [localitaInstance: localitaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'localities.label', default: 'Localita'), localitaInstance.id])
        redirect(action: "show", id: localitaInstance.id)
    }

    def delete() {
        def localitaInstance = Localita.get(params.id)
        if (!localitaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localities.label', default: 'Localita'), params.id])
            redirect(action: "list")
            return
        }

        try {
            localitaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'localities.label', default: 'Localita'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'localities.label', default: 'Localita'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
