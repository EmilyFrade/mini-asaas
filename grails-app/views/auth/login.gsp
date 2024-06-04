<!doctype html>
<html>
	<head>
		<meta name="layout" content="auth" />
		<title>Mini Asaas | Login</title>

		<asset:stylesheet src="global.css" />
		<asset:stylesheet src="auth/login.css" />
	</head>

	<body>
		<header class="header">
			<g:render template="/templates/banner" />
		</header>
		<div class="container">
			<main>
				<section class="login-section">
					<atlas-layout gap="2">
						<atlas-heading size="h5">Acesso ao Asaas</atlas-heading>
						<atlas-text class="subtitle" theme="primary" muted="" size="xsm">
							Olá, use os campos abaixo para acessar sua conta Mini Asaas.
						</atlas-text>
					</atlas-layout>
					<atlas-form method="POST" action="${createLink(controller: "auth", action: "postLogin")}">
						<g:if test="${flash.message}">
							<atlas-alert type="error" message="${flash.message}"></atlas-alert>
						</g:if>
						<atlas-layout gap="4">
							<atlas-masked-input
								class="input"
								mask-alias="email"
								label="Email"
								name="email"
								type="email"
								size="sm"
								value="${params.email}"
								required="">
							</atlas-masked-input>
							<atlas-password-input
								label="Senha"
								name="password"
								type="password"
								size="sm"
								value="${params.password}"
								required="">
							</atlas-password-input>
						</atlas-layout>
						<atlas-button
							type="filled"
							size="sm"
							theme="success"
							description="Acessar conta"
							submit=""
							block="">
						</atlas-button>
					</atlas-form>
					<hr class="divider" />
					<atlas-layout gap="4">
						<atlas-text class="subtitle" theme="secondary" size="xsm">
							Ainda não possui uma conta?
						</atlas-text>
						<div>
							<atlas-button
								class="btn__link"
								type="outlined"
								size="sm"
								theme="secondary"
								description="Criar conta"
								href="/user/create">
							</atlas-button>
						</div>
					</atlas-layout>
				</section>
				<section class="image-section">
					<atlas-layout gap="2">
						<atlas-heading theme="primary" size="h2">Seja bem-vindo ao Mini Asaas!</atlas-heading>
						<atlas-text class="subtitle" theme="primary" muted="" size="xsm">
							A solução mais completa e segura em emissão de cobranças e serviços financeiros.
						</atlas-text>
					</atlas-layout>
					<asset:image src="login-asaas-preview.png" alt="Mini Asaas - Prévia" />
				</section>
			</main>
		</div>
	</body>