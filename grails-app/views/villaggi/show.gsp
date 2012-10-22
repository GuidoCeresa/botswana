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
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'villaggi.label', default: 'Villaggi')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-villaggi" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-villaggi" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list villaggi">
			
				<g:if test="${villaggiInstance?.csonome}">
				<li class="fieldcontain">
					<span id="csonome-label" class="property-label"><g:message code="villaggi.csonome.label" default="Csonome" /></span>
					
						<span class="property-value" aria-labelledby="csonome-label"><g:fieldValue bean="${villaggiInstance}" field="csonome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${villaggiInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="villaggi.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${villaggiInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${villaggiInstance?.sottodistretto}">
				<li class="fieldcontain">
					<span id="sottodistretto-label" class="property-label"><g:message code="villaggi.sottodistretto.label" default="Sottodistretto" /></span>
					
						<span class="property-value" aria-labelledby="sottodistretto-label"><g:link controller="sottodistretti" action="show" id="${villaggiInstance?.sottodistretto?.id}">${villaggiInstance?.sottodistretto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${villaggiInstance?.popolazione}">
				<li class="fieldcontain">
					<span id="popolazione-label" class="property-label"><g:message code="villaggi.popolazione.label" default="Popolazione" /></span>
					
						<span class="property-value" aria-labelledby="popolazione-label"><g:fieldValue bean="${villaggiInstance}" field="popolazione"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${villaggiInstance?.localita}">
				<li class="fieldcontain">
					<span id="localita-label" class="property-label"><g:message code="villaggi.localita.label" default="Localita" /></span>
					
						<span class="property-value" aria-labelledby="localita-label"><g:fieldValue bean="${villaggiInstance}" field="localita"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${villaggiInstance?.latitudine}">
				<li class="fieldcontain">
					<span id="latitudine-label" class="property-label"><g:message code="villaggi.latitudine.label" default="Latitudine" /></span>
					
						<span class="property-value" aria-labelledby="latitudine-label"><g:fieldValue bean="${villaggiInstance}" field="latitudine"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${villaggiInstance?.longitudine}">
				<li class="fieldcontain">
					<span id="longitudine-label" class="property-label"><g:message code="villaggi.longitudine.label" default="Longitudine" /></span>
					
						<span class="property-value" aria-labelledby="longitudine-label"><g:fieldValue bean="${villaggiInstance}" field="longitudine"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${villaggiInstance?.id}" />
					<g:link class="edit" action="edit" id="${villaggiInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
