<%@ page import="com.mini.asaas.enums.address.AddressState" %>

<atlas-form-step id="create-customer-step-two" name="step-two" data-step="2">
	<atlas-section header="Dados principais" header-size="h5">
		<atlas-layout gap="4">
			<atlas-text muted size="xs">
				Informe os dados abaixo com atenção para que não haja alterações no decorrer do cadastro.
			</atlas-text>
			<atlas-layout gap="5">
				<atlas-input
						type="text"
						name="name"
						label="Nome completo (Igual ao documento)"
						value="${params.name}"
						required>
				</atlas-input>
				<atlas-masked-input
						mask-alias="email"
						type="email"
						name="email"
						label="Email"
						value="${params.email}"
						required>
				</atlas-masked-input>
				<atlas-password-input
						name="password"
						label="Senha"
						value="${params.password}"
						required>
				</atlas-password-input>
				<atlas-masked-input
						mask-alias="cell-phone"
						name="phoneNumber"
						label="Celular"
						value="${params.phoneNumber}"
						icon="phone"
						required>
				</atlas-masked-input>
			</atlas-layout>

			<div class="form-step-actions">
				<atlas-button theme="secondary" description="Voltar" data-atlas-form-previous-step></atlas-button>
				<atlas-button description="Avançar" data-atlas-form-next-step></atlas-button>
			</div>
		</atlas-layout>
	</atlas-section>
</atlas-form-step>