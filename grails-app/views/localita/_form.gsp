<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="botswana.Localita" %>



<div class="fieldcontain ${hasErrors(bean: localitaInstance, field: 'csonome', 'error')} ">
	<label for="csonome">
		<g:message code="localita.csonome.label" default="Csonome" />
	</label>
	









<g:textField name="csonome" value="${localitaInstance?.csonome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localitaInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="localita.nome.label" default="Nome" />
	</label>
	









<g:textField name="nome" value="${localitaInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localitaInstance, field: 'villaggio', 'error')} ">
	<label for="villaggio">
		<g:message code="localita.villaggio.label" default="Villaggio" />
	</label>
	









<g:select id="villaggio" name="villaggio.id" from="${botswana.Villaggi.list()}" optionKey="id" value="${localitaInstance?.villaggio?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localitaInstance, field: 'sottodistretto', 'error')} ">
	<label for="sottodistretto">
		<g:message code="localita.sottodistretto.label" default="Sottodistretto" />
	</label>
	









<g:select id="sottodistretto" name="sottodistretto.id" from="${botswana.Sottodistretti.list()}" optionKey="id" value="${localitaInstance?.sottodistretto?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localitaInstance, field: 'popolazione', 'error')} required">
	<label for="popolazione">
		<g:message code="localita.popolazione.label" default="Popolazione" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="popolazione" required="" value="${fieldValue(bean: localitaInstance, field: 'popolazione')}"/>
</div>

