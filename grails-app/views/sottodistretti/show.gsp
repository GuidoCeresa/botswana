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
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'sottodistretti.label', default: 'Sottodistretti')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-sottodistretti" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-sottodistretti" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list sottodistretti">
			
				<g:if test="${sottodistrettiInstance?.csonome}">
				<li class="fieldcontain">
					<span id="csonome-label" class="property-label"><g:message code="sottodistretti.csonome.label" default="Csonome" /></span>
					
						<span class="property-value" aria-labelledby="csonome-label"><g:fieldValue bean="${sottodistrettiInstance}" field="csonome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="sottodistretti.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${sottodistrettiInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.nomeBreve}">
				<li class="fieldcontain">
					<span id="nomeBreve-label" class="property-label"><g:message code="sottodistretti.nomeBreve.label" default="Nome Breve" /></span>
					
						<span class="property-value" aria-labelledby="nomeBreve-label"><g:fieldValue bean="${sottodistrettiInstance}" field="nomeBreve"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.nomeLink}">
				<li class="fieldcontain">
					<span id="nomeLink-label" class="property-label"><g:message code="sottodistretti.nomeLink.label" default="Nome Link" /></span>
					
						<span class="property-value" aria-labelledby="nomeLink-label"><g:fieldValue bean="${sottodistrettiInstance}" field="nomeLink"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.distretto}">
				<li class="fieldcontain">
					<span id="distretto-label" class="property-label"><g:message code="sottodistretti.distretto.label" default="Distretto" /></span>
					
						<span class="property-value" aria-labelledby="distretto-label"><g:link controller="distretti" action="show" id="${sottodistrettiInstance?.distretto?.id}">${sottodistrettiInstance?.distretto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.maschi}">
				<li class="fieldcontain">
					<span id="maschi-label" class="property-label"><g:message code="sottodistretti.maschi.label" default="Maschi" /></span>
					
						<span class="property-value" aria-labelledby="maschi-label"><g:fieldValue bean="${sottodistrettiInstance}" field="maschi"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.femmine}">
				<li class="fieldcontain">
					<span id="femmine-label" class="property-label"><g:message code="sottodistretti.femmine.label" default="Femmine" /></span>
					
						<span class="property-value" aria-labelledby="femmine-label"><g:fieldValue bean="${sottodistrettiInstance}" field="femmine"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.totale}">
				<li class="fieldcontain">
					<span id="totale-label" class="property-label"><g:message code="sottodistretti.totale.label" default="Totale" /></span>
					
						<span class="property-value" aria-labelledby="totale-label"><g:fieldValue bean="${sottodistrettiInstance}" field="totale"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.villaggi}">
				<li class="fieldcontain">
					<span id="villaggi-label" class="property-label"><g:message code="sottodistretti.villaggi.label" default="Villaggi" /></span>
					
						<span class="property-value" aria-labelledby="villaggi-label"><g:fieldValue bean="${sottodistrettiInstance}" field="villaggi"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sottodistrettiInstance?.localita}">
				<li class="fieldcontain">
					<span id="localita-label" class="property-label"><g:message code="sottodistretti.localita.label" default="Localita" /></span>
					
						<span class="property-value" aria-labelledby="localita-label"><g:fieldValue bean="${sottodistrettiInstance}" field="localita"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${sottodistrettiInstance?.id}" />
					<g:link class="edit" action="edit" id="${sottodistrettiInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
