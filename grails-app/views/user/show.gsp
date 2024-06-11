<%@ page import="com.mini.asaas.user.RoleAuthority" %>
<%@ page import="com.mini.asaas.user.UserRole" %>
<%@ page import="com.mini.asaas.enums.address.AddressState" %>
<%@ page import="com.mini.asaas.utils.DateFormatUtils" %>

<html>
	<head>
		<meta name="layout" content="main">
		<title>Mini Asaas - Perfil</title>
	</head>

	<body>
		<g:if test="${flash.section == "update"}">
			<atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
		</g:if>
		<atlas-form-panel
			id="update-user-form"
			header="Dados do usuário"
			description=""
			submit-button-label=""
			action="${createLink(controller: "user", action: "update")}"
			method="post">
			<atlas-button
				slot="actions"
				description="Editar"
				icon="pencil"
				data-panel-start-editing="true">
			</atlas-button>

			<atlas-grid>
				<atlas-row>
					<atlas-col lg="6">
						<atlas-input
							label="Nome"
							name="name"
							required
							value="${user.name}">
						</atlas-input>
					</atlas-col>

					<atlas-col lg="6">
						<atlas-masked-input
							label="Email"
							name="email"
							mask-alias="email"
							value="${user.email}"
							required>
						</atlas-masked-input>
					</atlas-col>
				</atlas-row>
				<g:if test="${user.isAdminButNotOnlyOne()}">
					<atlas-row>
						<atlas-col lg="12">
							<atlas-select
								${user.isAdminButNotOnlyOne() ? "" : "disabled=''"}
								label="Função do Usuário"
								placeholder="Selecione uma função"
								name="roleAuthority"
								id="roleAuthority"
								value="${user.getRoleAuthority().name()}"
								required>
								<g:each in="${RoleAuthority.values()}" var="authority">
									<atlas-option
										label="${authority.getLabel()}"
										value="${authority.name()}">
									</atlas-option>
								</g:each>
							</atlas-select>
						</atlas-col>
					</atlas-row>
				</g:if>
			</atlas-grid>
		</atlas-form-panel>

		<g:if test="${flash.section == "update-password"}">
			<atlas-alert type=${flash.status} message="${flash.message}"></atlas-alert>
		</g:if>
		<atlas-form-panel
			header="Atualizar Senha"
			description="Para atualizar a senha, informa a senha atual e a nova senha."
			submit-button-label=""
			action="${createLink(controller: "user", action: "updatePassword")}"
			method="post">
			<atlas-button
				slot="actions"
				description="Editar"
				icon="pencil"
				data-panel-start-editing="true">
			</atlas-button>

			<atlas-grid>
				<atlas-row>
					<atlas-col lg="6">
						<atlas-password-input
							label="Senha Atual"
							name="currentPassword"
							type="currentPassword"
							size="sm"
							value="${params.currentPassword}"
							required="">
						</atlas-password-input>
					</atlas-col>

					<atlas-col lg="6">
						<atlas-password-input
							label="Nova Senha"
							name="newPassword"
							type="newPassword"
							size="sm"
							value="${params.newPassword}"
							required="">
						</atlas-password-input>
					</atlas-col>
				</atlas-row>
			</atlas-grid>
		</atlas-form-panel>
	</body>
</html>