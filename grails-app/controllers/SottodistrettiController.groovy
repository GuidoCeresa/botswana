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

import botswana.Sottodistretti

class SottodistrettiController {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def sottodistrettiService = new SottodistrettiService()

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }// fine della closure

    def importa() {
        flash.message = sottodistrettiService.importa()
        redirect(action: "list", params: params)
    }// fine della closure

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sottodistrettiInstanceList: Sottodistretti.list(params), sottodistrettiInstanceTotal: Sottodistretti.count()]
    }

    def create() {
        [sottodistrettiInstance: new Sottodistretti(params)]
    }

    def save() {
        def sottodistrettiInstance = new Sottodistretti(params)
        if (!sottodistrettiInstance.save(flush: true)) {
            render(view: "create", model: [sottodistrettiInstance: sottodistrettiInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'sottodistretti.label', default: 'SubDistricts'), sottodistrettiInstance.id])
        redirect(action: "show", id: sottodistrettiInstance.id)
    }

    def show() {
        def sottodistrettiInstance = Sottodistretti.get(params.id)
        if (!sottodistrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subDistricts.label', default: 'SubDistricts'), params.id])
            redirect(action: "list")
            return
        }

        [sottodistrettiInstance: sottodistrettiInstance]
    }

    def edit() {
        def sottodistrettiInstance = Sottodistretti.get(params.id)
        if (!sottodistrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subDistricts.label', default: 'SubDistricts'), params.id])
            redirect(action: "list")
            return
        }

        [sottodistrettiInstance: sottodistrettiInstance]
    }

    def update() {
        def sottodistrettiInstance = Sottodistretti.get(params.id)
        if (!sottodistrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subDistricts.label', default: 'SubDistricts'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (sottodistrettiInstance.version > version) {
                sottodistrettiInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'subDistricts.label', default: 'SubDistricts')] as Object[],
                        "Another user has updated this SubDistricts while you were editing")
                render(view: "edit", model: [subdistrettiInstance: sottodistrettiInstance])
                return
            }
        }

        sottodistrettiInstance.properties = params

        if (!sottodistrettiInstance.save(flush: true)) {
            render(view: "edit", model: [subdistrettiInstance: sottodistrettiInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'subDistricts.label', default: 'SubDistricts'), sottodistrettiInstance.id])
        redirect(action: "show", id: sottodistrettiInstance.id)
    }

    def delete() {
        def sottodistrettiInstance = Sottodistretti.get(params.id)
        if (!sottodistrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subDistricts.label', default: 'SubDistricts'), params.id])
            redirect(action: "list")
            return
        }

        try {
            sottodistrettiInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'subDistricts.label', default: 'SubDistricts'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'subDistricts.label', default: 'SubDistricts'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
