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
import botswana.Distretti

class DistrettiController {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    // (in teoria)
    def distrettiService = new DistrettiService()

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }// fine della closure

    def importa() {
        flash.message = distrettiService.importa()
        redirect(action: "list", params: params)
    }// fine della closure

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [distrettiInstanceList: Distretti.list(params), distrettiInstanceTotal: Distretti.count()]
    }

    def create() {
        [distrettiInstance: new Distretti(params)]
    }

    def save() {
        def distrettiInstance = new Distretti(params)
        if (!distrettiInstance.save(flush: true)) {
            render(view: "create", model: [distrettiInstance: distrettiInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'districts.label', default: 'Districts'), distrettiInstance.id])
        redirect(action: "show", id: distrettiInstance.id)
    }

    def show() {
        def distrettiInstance = Distretti.get(params.id)
        if (!distrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'districts.label', default: 'Districts'), params.id])
            redirect(action: "list")
            return
        }

        [distrettiInstance: distrettiInstance]
    }

    def edit() {
        def distrettiInstance = Distretti.get(params.id)
        if (!distrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'districts.label', default: 'Districts'), params.id])
            redirect(action: "list")
            return
        }

        [distrettiInstance: distrettiInstance]
    }

    def update() {
        def distrettiInstance = Distretti.get(params.id)
        if (!distrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'districts.label', default: 'Districts'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (distrettiInstance.version > version) {
                distrettiInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'districts.label', default: 'Districts')] as Object[],
                        "Another user has updated this Districts while you were editing")
                render(view: "edit", model: [distrettiInstance: distrettiInstance])
                return
            }
        }

        distrettiInstance.properties = params

        if (!distrettiInstance.save(flush: true)) {
            render(view: "edit", model: [distrettiInstance: distrettiInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'districts.label', default: 'Districts'), distrettiInstance.id])
        redirect(action: "show", id: distrettiInstance.id)
    }

    def delete() {
        def distrettiInstance = Distretti.get(params.id)
        if (!distrettiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'districts.label', default: 'Districts'), params.id])
            redirect(action: "list")
            return
        }

        try {
            distrettiInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'districts.label', default: 'Districts'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'districts.label', default: 'Districts'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
