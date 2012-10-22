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
  <g:set var="entityName" value="${message(code: 'sottodistretti.label', default: 'Sottodistretti')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-sottodistretti" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
  <ul>
	<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
      <li><g:link class="edit" action="importa">Import Iniziale</g:link></li>
  </ul>
</div>

<div id="list-sottodistretti" class="content scaffold-list" role="main">
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
	  
	  <g:sortableColumn property="id" title="${message(code: 'sottodistretti.id.label', default: 'Id')}"/>
	  
	  <g:sortableColumn property="csonome" title="${message(code: 'sottodistretti.csonome.label', default: 'Csonome')}"/>
	  
	  <g:sortableColumn property="nome" title="${message(code: 'sottodistretti.nome.label', default: 'Nome')}"/>
	  
	  <g:sortableColumn property="nomeBreve" title="${message(code: 'sottodistretti.nomeBreve.label', default: 'Nome Breve')}"/>
	  
	  <g:sortableColumn property="nomeLink" title="${message(code: 'sottodistretti.nomeLink.label', default: 'Nome Link')}"/>
	  
	  <th><g:message code="sottodistretti.distretto.label" default="Distretto"/></th>
	  
	  <g:sortableColumn property="maschi" title="${message(code: 'sottodistretti.maschi.label', default: 'Maschi')}"/>
	  
	  <g:sortableColumn property="femmine" title="${message(code: 'sottodistretti.femmine.label', default: 'Femmine')}"/>
	  
	  <g:sortableColumn property="totale" title="${message(code: 'sottodistretti.totale.label', default: 'Totale')}"/>
	  
	  <g:sortableColumn property="villaggi" title="${message(code: 'sottodistretti.villaggi.label', default: 'Villaggi')}"/>
	  
	</tr>
	</thead>
	<tbody>
	<g:each in="${sottodistrettiInstanceList}" status="i" var="sottodistrettiInstance">
	  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
		
		<td><g:link action="show" id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "id")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "csonome")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "nome")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "nomeBreve")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "nomeLink")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "distretto")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "maschi")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "femmine")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "totale")}</g:link></td>
		
		<td><g:link action="show"
					id="${sottodistrettiInstance.id}">${fieldValue(bean: sottodistrettiInstance, field: "villaggi")}</g:link></td>
		
	  </tr>
	</g:each>
	</tbody>
  </table>

  <div class="pagination">
	<g:paginate total="${sottodistrettiInstanceTotal}"/>
  </div>
</div>
</body>
</html>
