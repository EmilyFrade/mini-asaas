<%@ page import="core.enums.AddressState" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mini Asaas - Criar Conta</title>
</head>

<body>
    <atlas-page class="js-atlas-page">
        <atlas-page-header page-name="Cadastro" slot="header"></atlas-page-header>
        <atlas-page-content slot="content" class="js-atlas-content">
            <atlas-form class="form" method="POST" action="${createLink(controller: "customer", action: "save")}">
                <atlas-row>
                    <atlas-input
                        label="Nome"
                        type="text"
                        name="name"
                        id="name"
                        value="${params.name}"
                        required >
                    </atlas-input>

                    <g:hasErrors bean="${errors}" field="name">
                        <span class="form--error">
                            <g:renderErrors bean="${errors}" field="name"/>
                        </span>
                    </g:hasErrors>
                </atlas-row>
                <atlas-row>
                    <atlas-masked-input
                        col="2"
                        mask-alias="email"
                        label="E-mail"
                        type="email"
                        name="email"
                        id="email"
                        value="${params.email}"
                        required >
                    </atlas-masked-input>
                    <g:hasErrors bean="${errors}" field="email">
                        <span class="form--error">
                            <g:renderErrors bean="${errors}" field="email"/>
                        </span>
                    </g:hasErrors>
                </atlas-row>
                <atlas-row>
                    <atlas-masked-input
                        mask-alias="cpf-cnpj"
                        label="CPF/CNPJ"
                        type="text"
                        name="cpfCnpj"
                        id="cpfCnpj"
                        value="${params.cpfCnpj}"
                        required >
                    </atlas-masked-input>
                    <g:hasErrors bean="${errors}" field="cpfCnpj">
                        <span class="form--error">
                            <g:renderErrors bean="${errors}" field="cpfCnpj"/>
                        </span>
                    </g:hasErrors>
                </atlas-row>
                <atlas-row>
                    <atlas-masked-input
                        mask-alias="phone"
                        label="Número de Telefone"
                        type="tel"
                        name="phoneNumber"
                        id="phoneNumber"
                        value="${params.phoneNumber}"
                        required >
                    </atlas-masked-input>
                    <g:hasErrors bean="${errors}" field="phoneNumber">
                        <span class="form--error">
                            <g:renderErrors bean="${errors}" field="phoneNumber"/>
                        </span>
                    </g:hasErrors>
                </atlas-row>
                <atlas-grid>
                    <atlas-row>
                        <atlas-col lg="3">
                            <atlas-postal-code
                                label="CEP"
                                type="text"
                                name="zipCode"
                                id="zipCode"
                                value="${params.zipCode}"
                                required>
                            </atlas-postal-code>
                        </atlas-col>
                        <atlas-col lg="4">
                            <atlas-input
                                class="postal-code-element"
                                label="Logradouro"
                                type="text"
                                name="street"
                                id="street"
                                value="${params.street}"
                                required >
                            </atlas-input>
                        </atlas-col>
                        <atlas-col lg="1">
                            <atlas-integer-input
                                class="postal-code-element"
                                label="Nº"
                                type="text"
                                name="number"
                                id="number"
                                min-value="1"
                                min-value-error-message="O valor deve ser positivo"
                                placeholder="S/N"
                                value="${params.number}" >
                            </atlas-integer-input>
                        </atlas-col>
                        <atlas-col lg="4">
                            <atlas-input
                                class="postal-code-element"
                                label="Complemento"
                                type="text"
                                name="complement"
                                id="complement"
                                value="${params.complement}" >
                            </atlas-input>
                        </atlas-col>
                    </atlas-row>
                    <atlas-row>
                        <atlas-col lg="4">
                            <atlas-input
                                class="postal-code-element"
                                label="Bairro"
                                type="text"
                                name="neighborhood"
                                id="neighborhood"
                                value="${params.neighborhood}"
                                required >
                            </atlas-input>
                        </atlas-col>
                        <atlas-col lg="4">
                            <atlas-input
                                class="postal-code-element"
                                label="Cidade"
                                type="text"
                                name="city"
                                id="city"
                                value="${params.city}"
                                required >
                            </atlas-input>
                        </atlas-col>
                        <atlas-col lg="4">
                            <atlas-select
                                class="postal-code-element"
                                label="Estado"
                                placeholder="Selecione um estado"
                                name="state"
                                id="state"
                                value="${params.state}"
                                required >
                                    <g:each in="${AddressState.values()}" var="state">
                                        <atlas-option label="${state.label}" value="${state.name()}"></atlas-option>
                                    </g:each>
                            </atlas-select>
                        </atlas-col>
                    </atlas-row>
                </atlas-grid>
                <atlas-button submit="true" description="Cadastrar" type="filled"></atlas-button>
            </atlas-form>
        </atlas-page-content>
    </atlas-page>
</body>
</html>