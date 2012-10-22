<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="botswana.Villaggi" %>



<div class="fieldcontain ${hasErrors(bean: villaggiInstance, field: 'csonome', 'error')} ">
	<label for="csonome">
		<g:message code="villaggi.csonome.label" default="Csonome" />
	</label>
	









<g:textField name="csonome" value="${villaggiInstance?.csonome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: villaggiInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="villaggi.nome.label" default="Nome" />
	</label>
	









<g:textField name="nome" value="${villaggiInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: villaggiInstance, field: 'sottodistretto', 'error')} ">
	<label for="sottodistretto">
		<g:message code="villaggi.sottodistretto.label" default="Sottodistretto" />
	</label>
	









<g:select id="sottodistretto" name="sottodistretto.id" from="${botswana.Sottodistretti.list()}" optionKey="id" value="${villaggiInstance?.sottodistretto?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: villaggiInstance, field: 'popolazione', 'error')} required">
	<label for="popolazione">
		<g:message code="villaggi.popolazione.label" default="Popolazione" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="popolazione" required="" value="${fieldValue(bean: villaggiInstance, field: 'popolazione')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: villaggiInstance, field: 'localita', 'error')} required">
	<label for="localita">
		<g:message code="villaggi.localita.label" default="Localita" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="localita" required="" value="${fieldValue(bean: villaggiInstance, field: 'localita')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: villaggiInstance, field: 'latitudine', 'error')} ">
	<label for="latitudine">
		<g:message code="villaggi.latitudine.label" default="Latitudine" />
	</label>
	









<g:textField name="latitudine" value="${villaggiInstance?.latitudine}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: villaggiInstance, field: 'longitudine', 'error')} ">
	<label for="longitudine">
		<g:message code="villaggi.longitudine.label" default="Longitudine" />
	</label>
	









<g:textField name="longitudine" value="${villaggiInstance?.longitudine}"/>
</div>

