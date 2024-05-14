<%@ page import="core.enums.AddressState" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mini Asaas - Detalhes da Conta</title>
</head>

<body>
<atlas-page class="js-atlas-page">
    <atlas-page-header page-name="Meu Perfil" slot="header"></atlas-page-header>
    <atlas-page-content slot="content" class="js-atlas-content">
        <atlas-form-panel header="Dados Pessoais" description="" submit-button-label="" action="${createLink(controller: "customer", action: "update", params: [id: customer.id])}" method="post">
            <atlas-button slot="actions" description="Editar" icon="pencil" data-panel-start-editing="true"></atlas-button>
            <atlas-layout gap="3">
                <atlas-input
                    type="text"
                    label="Nome"
                    name="name"
                    id="name"
                    required
                    value="${customer.name}">
                </atlas-input>
                <atlas-masked-input
                    mask-alias="email"
                    type="email"
                    label="email"
                    name="email"
                    id="email"
                    value="${customer.email}"
                    required >
                </atlas-masked-input>
                <atlas-masked-input
                    mask-alias="cpf-cnpj"
                    label="CPF/CNPJ"
                    type="text"
                    name="cpfCnpj"
                    id="cpfCnpj"
                    value="${customer.cpfCnpj}"
                    required >
                </atlas-masked-input>
                <atlas-masked-input
                    mask-alias="phone"
                    label="Número de Telefone"
                    type="tel"
                    name="phoneNumber"
                    id="phoneNumber"
                    value="${customer.phoneNumber}"
                    required >
                </atlas-masked-input>
                <atlas-input
                    type="text"
                    label="Natureza Jurídica"
                    name="personType"
                    id="personType"
                    required
                    readonly
                    value="${customer.personType.getLabel()}">
                </atlas-input>
            </atlas-layout>
        </atlas-form-panel>
        <atlas-form-panel header="Meu Endereço" description="" submit-button-label="" action="${createLink(controller: "customer", action: "updateAddress", params: [id: customer.id])}" method="post">
            <atlas-button slot="actions" description="Editar" icon="pencil" data-panel-start-editing="true"></atlas-button>
            <atlas-grid>
                <atlas-row>
                    <atlas-col lg="3">
                        <atlas-postal-code
                                label="CEP"
                                type="text"
                                name="zipCode"
                                id="zipCode"
                                value="${customer.address.zipCode}"
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
                                value="${customer.address.street}"
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
                                placeholder="S/N"
                                value="${customer.address.number}" >
                        </atlas-integer-input>
                    </atlas-col>
                    <atlas-col lg="4">
                        <atlas-input
                                class="postal-code-element"
                                label="Complemento"
                                type="text"
                                name="complement"
                                id="complement"
                                value="${customer.address.complement}" >
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
                                value="${customer.address.neighborhood}"
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
                                value="${customer.address.city}"
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
                                value="${customer.address.state}"
                                required >
                            <g:each in="${AddressState.values()}" var="state">
                                <atlas-option label="${state.label}" value="${state.name()}"></atlas-option>
                            </g:each>
                        </atlas-select>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
        </atlas-form-panel>
    </atlas-page-content>
</atlas-page>

</body>
</html>