<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="botswana.Sottodistretti" %>



<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'csonome', 'error')} ">
	<label for="csonome">
		<g:message code="sottodistretti.csonome.label" default="Csonome" />
	</label>
	









<g:textField name="csonome" value="${sottodistrettiInstance?.csonome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="sottodistretti.nome.label" default="Nome" />
	</label>
	









<g:textField name="nome" value="${sottodistrettiInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'nomeBreve', 'error')} ">
	<label for="nomeBreve">
		<g:message code="sottodistretti.nomeBreve.label" default="Nome Breve" />
	</label>
	









<g:textField name="nomeBreve" value="${sottodistrettiInstance?.nomeBreve}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'nomeLink', 'error')} ">
	<label for="nomeLink">
		<g:message code="sottodistretti.nomeLink.label" default="Nome Link" />
	</label>
	









<g:textField name="nomeLink" value="${sottodistrettiInstance?.nomeLink}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'distretto', 'error')} ">
	<label for="distretto">
		<g:message code="sottodistretti.distretto.label" default="Distretto" />
	</label>
	









<g:select id="distretto" name="distretto.id" from="${botswana.Distretti.list()}" optionKey="id" value="${sottodistrettiInstance?.distretto?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'maschi', 'error')} required">
	<label for="maschi">
		<g:message code="sottodistretti.maschi.label" default="Maschi" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="maschi" required="" value="${fieldValue(bean: sottodistrettiInstance, field: 'maschi')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'femmine', 'error')} required">
	<label for="femmine">
		<g:message code="sottodistretti.femmine.label" default="Femmine" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="femmine" required="" value="${fieldValue(bean: sottodistrettiInstance, field: 'femmine')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'totale', 'error')} required">
	<label for="totale">
		<g:message code="sottodistretti.totale.label" default="Totale" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="totale" required="" value="${fieldValue(bean: sottodistrettiInstance, field: 'totale')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'villaggi', 'error')} required">
	<label for="villaggi">
		<g:message code="sottodistretti.villaggi.label" default="Villaggi" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="villaggi" required="" value="${fieldValue(bean: sottodistrettiInstance, field: 'villaggi')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sottodistrettiInstance, field: 'localita', 'error')} required">
	<label for="localita">
		<g:message code="sottodistretti.localita.label" default="Localita" /><span class="required-indicator">*</span>
	</label>
	









<g:field type="number" name="localita" required="" value="${fieldValue(bean: sottodistrettiInstance, field: 'localita')}"/>
</div>

