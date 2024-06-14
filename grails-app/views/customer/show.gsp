<%@ page import="com.mini.asaas.utils.DateFormatUtils" %>
<%@ page import="com.mini.asaas.customer.CompanyType" %>
<%
    Map address = [
            zipCode      : customer.zipCode,
            address      : customer.address,
            addressNumber: customer.addressNumber,
            complement   : customer.complement,
            province     : customer.province,
            city         : customer.city,
            state        : customer.state,
    ]
%>

<html>
    <head>
        <title>Minha Conta</title>
        <meta name="layout" content="main">
    </head>

    <body>
        <g:if test="${flash.message}">
            <atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
        </g:if>

        <atlas-form-panel header="Informações da conta" description="" submit-button-label=""
                          action="${createLink(controller: "customer", action: "update")}"
                          method="post">
            <atlas-input
                    value="${customer.id}"
                    name="id"
                    hidden>
            </atlas-input>

            <g:if test="${user.isAdmin()}">
                <atlas-button slot="actions" description="Editar" icon="pencil" data-panel-start-editing="true"></atlas-button>
            </g:if>

            <atlas-grid>
                <atlas-row>
                    <atlas-col lg="6">
                        <atlas-layout gap="6">
                            <atlas-text size="md" bold="" underline="">Dados comerciais</atlas-text>

                            <atlas-row>
                                <atlas-col lg="12">
                                    <atlas-input
                                            label="Nome"
                                            name="name"
                                            required
                                            value="${customer.name}">
                                    </atlas-input>
                                </atlas-col>
                            </atlas-row>

                            <atlas-row>
                                <atlas-col lg="12">
                                    <atlas-masked-input
                                            label="Email"
                                            name="email"
                                            mask-alias="email"
                                            value="${customer.email}"
                                            required>
                                    </atlas-masked-input>
                                </atlas-col>
                            </atlas-row>

                            <atlas-row>
                                <atlas-col lg="6">
                                    <atlas-input
                                            label="Natureza Jurídica"
                                            name="personType"
                                            readonly
                                            value="${customer.personType.getLabel()}"
                                            required>
                                    </atlas-input>
                                </atlas-col>
                                <atlas-col lg="6">
                                    <atlas-masked-input
                                            mask-alias="cpf-cnpj"
                                            label="CPF/CNPJ"
                                            name="cpfCnpj"
                                            value="${customer.cpfCnpj}"
                                            required>
                                    </atlas-masked-input>
                                </atlas-col>
                            </atlas-row>

                            <atlas-row>
                                <atlas-col lg="12">
                                    <atlas-select
                                            label="Tipo da empresa"
                                            name="companyType"
                                            value="${customer.companyType}"
                                            required>
                                        <g:each in="${CompanyType.values()}" var="type">
                                            <atlas-option label="${type.getLabel()}" value="${type.name()}"></atlas-option>
                                        </g:each>
                                    </atlas-select>
                                </atlas-col>
                            </atlas-row>

                            <atlas-row>
                                <atlas-col lg="12">
                                    <atlas-datepicker
                                            label="Data de nascimento"
                                            value="${DateFormatUtils.format(customer.birthDate)}"
                                            name="birthDate"
                                            id="birthDate"
                                            required>
                                    </atlas-datepicker>
                                </atlas-col>
                            </atlas-row>

                            <atlas-row>
                                <atlas-col lg="12">
                                    <atlas-masked-input
                                            mask-alias="phone"
                                            label="Número de Telefone"
                                            type="tel"
                                            name="phoneNumber"
                                            id="phoneNumber"
                                            value="${customer.phoneNumber}"
                                            required>
                                    </atlas-masked-input>
                                </atlas-col>
                            </atlas-row>
                        </atlas-layout>
                    </atlas-col>

                    <atlas-col lg="6">
                        <atlas-layout gap="5">
                            <atlas-text size="md" bold="" underline="">Dados de endereço</atlas-text>
                            <g:render template="/templates/address-group" model="${[address: address, opened: true]}"/>
                        </atlas-layout>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
        </atlas-form-panel>
    </body>
</html>