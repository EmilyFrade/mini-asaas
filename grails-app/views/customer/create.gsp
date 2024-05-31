<%
	Map address = [
			zipCode      : params.zipCode,
			address      : params.address,
			addressNumber: params.addressNumber,
			complement   : params.complement,
			province     : params.province,
			city         : params.city,
			state        : params.state,
	]
%>

<!doctype html>
<html lang="pt-br">
	<head>
		<meta name="layout" content="basic" />
		<title>Mini Asaas | Criar Conta</title>
		<asset:stylesheet src="customer/create.css" />
	</head>

	<body>
		<header class="create-customer-header">
			<g:render template="/templates/banner" />
			<g:render
					template="/templates/form-step-progress"
					model="${[formId: 'create-customer-form', totalSteps: 3, startStep: step ?: 1]}" />
		</header>

		<div class="create-customer-container">
			<main class="create-customer-main">
				<div>
					<g:if test="${flash.status}">
						<atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
					</g:if>
				</div>
				<atlas-form
						data-start-step="${step ?: 1}"
						data-has-steps="true"
						id="create-customer-form"
						class="create-customer-form"
						method="post"
						action="${createLink(controller: "customer", action: "save")}">
					<g:render template="templates/step-one" />
					<g:render template="templates/step-two" />
					<g:render template="templates/step-three" model="${[address: address, opened: addressOpened]}" />
				</atlas-form>
			</main>
		</div>

		<asset:javascript src="atlas-radio-with-body-control.js" />
	</body>
</html>