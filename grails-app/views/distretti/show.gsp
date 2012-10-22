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
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'distretti.label', default: 'Distretti')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-distretti" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-distretti" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list distretti">
			
				<g:if test="${distrettiInstance?.nomeBreve}">
				<li class="fieldcontain">
					<span id="nomeBreve-label" class="property-label"><g:message code="distretti.nomeBreve.label" default="Nome Breve" /></span>
					
						<span class="property-value" aria-labelledby="nomeBreve-label"><g:fieldValue bean="${distrettiInstance}" field="nomeBreve"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${distrettiInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="distretti.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${distrettiInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${distrettiInstance?.nomeLink}">
				<li class="fieldcontain">
					<span id="nomeLink-label" class="property-label"><g:message code="distretti.nomeLink.label" default="Nome Link" /></span>
					
						<span class="property-value" aria-labelledby="nomeLink-label"><g:fieldValue bean="${distrettiInstance}" field="nomeLink"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${distrettiInstance?.id}" />
					<g:link class="edit" action="edit" id="${distrettiInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
