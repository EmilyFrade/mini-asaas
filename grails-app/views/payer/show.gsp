<%@ page import="com.mini.asaas.enums.address.AddressState; com.mini.asaas.utils.DateFormatUtils" %>
<html>
    <head>
        <title>Detalhes do Pagador</title>
        <meta name="layout" content="main">
    </head>

    <body>
        <g:if test="${flash.message}">
            <atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
        </g:if>
        <atlas-form-panel header="Detalhes do pagador" description="" submit-button-label=""
                          action="${createLink(controller: "payer", action: "update", params: [id: payer.id])}"
                          method="post">
            <atlas-input
                    value="${payer.id}"
                    name="id"
                    hidden>
            </atlas-input>

            <atlas-button slot="actions" description="Editar" icon="pencil"
                          data-panel-start-editing="true"></atlas-button>

            <atlas-grid>
                <atlas-row>
                    <atlas-col lg="6">
                        <atlas-input
                                label="Nome"
                                name="name"
                                required="true"
                                value="${payer.name}">
                        </atlas-input>
                    </atlas-col>

                    <atlas-col lg="6">
                        <atlas-masked-input
                                label="Email"
                                name="email"
                                mask-alias="email"
                                value="${payer.email}"
                                required="true">
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>

                <atlas-row>
                    <atlas-col lg="6">
                        <atlas-input
                                label="Natureza Jurídica"
                                name="personType"
                                readonly
                                value="${payer.personType.getLabel()}"
                                required="true">
                        </atlas-input>
                    </atlas-col>
                    <atlas-col lg="6">
                        <atlas-masked-input
                                mask-alias="cpf-cnpj"
                                label="CPF/CNPJ"
                                name="cpfCnpj"
                                value="${payer.cpfCnpj}"
                                required="true">
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>

                <atlas-row>
                    <atlas-col lg="4">
                        <atlas-datepicker
                                label="Data de nascimento"
                                value="${DateFormatUtils.format(payer.birthDate)}"
                                name="birthDate"
                                id="birthDate"
                                required="true">
                        </atlas-datepicker>
                    </atlas-col>
                    <atlas-col lg="4">
                        <atlas-masked-input
                                mask-alias="phone"
                                label="Número de Telefone"
                                type="tel"
                                name="phoneNumber"
                                id="phoneNumber"
                                value="${payer.phoneNumber}"
                                required="true">
                        </atlas-masked-input>
                    </atlas-col>
                    <atlas-col lg="4">
                        <atlas-postal-code
                                label="CEP"
                                name="zipCode"
                                value="${payer.zipCode}"
                                required="true"
                                mask="{}">
                        </atlas-postal-code>
                    </atlas-col>
                </atlas-row>

                <atlas-row>
                    <atlas-col lg="4">
                        <atlas-input
                                label="Rua"
                                name="address"
                                value="${payer.address}"
                                required="true">
                        </atlas-input>
                    </atlas-col>
                    <atlas-col lg="4">
                        <atlas-integer-input
                                class="postal-code-element"
                                label="Número"
                                name="addressNumber"
                                min-value="1"
                                min-value-error-message="O valor deve ser positivo"
                                value="${payer.addressNumber}"
                                required="true">
                        </atlas-integer-input>
                    </atlas-col>
                    <atlas-col lg="4">
                        <atlas-input
                                label="Complemento"
                                name="complement"
                                value="${payer.complement}">
                        </atlas-input>
                    </atlas-col>
                </atlas-row>

                <atlas-row>
                    <atlas-col lg="4">
                        <atlas-input
                                label="Bairro"
                                name="province"
                                value="${payer.province}"
                                required="true">
                        </atlas-input>
                    </atlas-col>
                    <atlas-col lg="4">
                        <atlas-input
                                label="Cidade"
                                name="city"
                                value="${payer.city}"
                                required="true">
                        </atlas-input>
                    </atlas-col>
                    <atlas-col lg="4">
                        <atlas-select
                                class="postal-code-element"
                                label="Estado"
                                name="state"
                                id="state"
                                value="${payer.state}"
                                required="true">
                            <g:each in="${AddressState.values()}" var="state">
                                <atlas-option label="${state.label}" value="${state.name()}"></atlas-option>
                            </g:each>
                        </atlas-select>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
        </atlas-form-panel>
    </body>
</html>