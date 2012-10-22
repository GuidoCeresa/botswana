<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="botswana.Distretti" %>



<div class="fieldcontain ${hasErrors(bean: distrettiInstance, field: 'nomeBreve', 'error')} ">
	<label for="nomeBreve">
		<g:message code="distretti.nomeBreve.label" default="Nome Breve" />
	</label>
	









<g:textField name="nomeBreve" value="${distrettiInstance?.nomeBreve}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: distrettiInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="distretti.nome.label" default="Nome" />
	</label>
	









<g:textField name="nome" value="${distrettiInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: distrettiInstance, field: 'nomeLink', 'error')} ">
	<label for="nomeLink">
		<g:message code="distretti.nomeLink.label" default="Nome Link" />
	</label>
	









<g:textField name="nomeLink" value="${distrettiInstance?.nomeLink}"/>
</div>

