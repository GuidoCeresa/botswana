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
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'localita.label', default: 'Localita')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-localita" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-localita" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list localita">
			
				<g:if test="${localitaInstance?.csonome}">
				<li class="fieldcontain">
					<span id="csonome-label" class="property-label"><g:message code="localita.csonome.label" default="Csonome" /></span>
					
						<span class="property-value" aria-labelledby="csonome-label"><g:fieldValue bean="${localitaInstance}" field="csonome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${localitaInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="localita.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${localitaInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${localitaInstance?.villaggio}">
				<li class="fieldcontain">
					<span id="villaggio-label" class="property-label"><g:message code="localita.villaggio.label" default="Villaggio" /></span>
					
						<span class="property-value" aria-labelledby="villaggio-label"><g:link controller="villaggi" action="show" id="${localitaInstance?.villaggio?.id}">${localitaInstance?.villaggio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${localitaInstance?.sottodistretto}">
				<li class="fieldcontain">
					<span id="sottodistretto-label" class="property-label"><g:message code="localita.sottodistretto.label" default="Sottodistretto" /></span>
					
						<span class="property-value" aria-labelledby="sottodistretto-label"><g:link controller="sottodistretti" action="show" id="${localitaInstance?.sottodistretto?.id}">${localitaInstance?.sottodistretto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${localitaInstance?.popolazione}">
				<li class="fieldcontain">
					<span id="popolazione-label" class="property-label"><g:message code="localita.popolazione.label" default="Popolazione" /></span>
					
						<span class="property-value" aria-labelledby="popolazione-label"><g:fieldValue bean="${localitaInstance}" field="popolazione"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${localitaInstance?.id}" />
					<g:link class="edit" action="edit" id="${localitaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
