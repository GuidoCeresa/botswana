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
  <g:set var="entityName" value="${message(code: 'localita.label', default: 'Localita')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-localita" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
  <ul>
	<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
      <li><g:link class="edit" action="importa">Import Iniziale</g:link></li>
  </ul>
</div>

<div id="list-localita" class="content scaffold-list" role="main">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
  </g:if>
  <g:if test="${flash.messages}">
	<ul>
	  <g:each in="${flash.messages}">
		<div class="message" role="status">${it}</div>
	  </g:each>
	</ul>
  </g:if>
  <table>
	<thead>
	<tr>
	  
	  <g:sortableColumn property="id" title="${message(code: 'localita.id.label', default: 'Id')}"/>
	  
	  <g:sortableColumn property="csonome" title="${message(code: 'localita.csonome.label', default: 'Csonome')}"/>
	  
	  <g:sortableColumn property="nome" title="${message(code: 'localita.nome.label', default: 'Nome')}"/>
	  
	  <th><g:message code="localita.villaggio.label" default="Villaggio"/></th>
	  
	  <th><g:message code="localita.sottodistretto.label" default="Sottodistretto"/></th>
	  
	  <g:sortableColumn property="popolazione" title="${message(code: 'localita.popolazione.label', default: 'Popolazione')}"/>
	  
	</tr>
	</thead>
	<tbody>
	<g:each in="${localitaInstanceList}" status="i" var="localitaInstance">
	  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
		
		<td><g:link action="show" id="${localitaInstance.id}">${fieldValue(bean: localitaInstance, field: "id")}</g:link></td>
		
		<td><g:link action="show"
					id="${localitaInstance.id}">${fieldValue(bean: localitaInstance, field: "csonome")}</g:link></td>
		
		<td><g:link action="show"
					id="${localitaInstance.id}">${fieldValue(bean: localitaInstance, field: "nome")}</g:link></td>
		
		<td><g:link action="show"
					id="${localitaInstance.id}">${fieldValue(bean: localitaInstance, field: "villaggio")}</g:link></td>
		
		<td><g:link action="show"
					id="${localitaInstance.id}">${fieldValue(bean: localitaInstance, field: "sottodistretto")}</g:link></td>
		
		<td><g:link action="show"
					id="${localitaInstance.id}">${fieldValue(bean: localitaInstance, field: "popolazione")}</g:link></td>
		
	  </tr>
	</g:each>
	</tbody>
  </table>

  <div class="pagination">
	<g:paginate total="${localitaInstanceTotal}"/>
  </div>
</div>
</body>
</html>
