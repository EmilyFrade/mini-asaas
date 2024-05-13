<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Mini Asaas - Lista de Clientes</title>
</head>

<body>
<atlas-page class="js-atlas-page">
    <atlas-page-header page-name="Meu Perfil" slot="header"></atlas-page-header>
    <atlas-page-content slot="content" class="js-atlas-content">
        <atlas-form-panel  header="Dados Pessoais" description="" submit-button-label="" action="${createLink(controller: "customer", action: "edit", params: [id: customer.id])}" method="post">
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
        <atlas-form-panel header="Meu Endereço" description="" submit-button-label="" action="${createLink(controller: "customer", action: "editAddress", params: [id: customer.id])}" method="post">
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
                                name="ufState"
                                id="ufState"
                                value="${customer.address.ufState}"
                                required >
                            <atlas-option label="AC" value="AC">AC</atlas-option>
                            <atlas-option label="AM" value="AM">AM</atlas-option>
                            <atlas-option label="AP" value="AP">AP</atlas-option>
                            <atlas-option label="PA" value="PA">PA</atlas-option>
                            <atlas-option label="RO" value="RO">RO</atlas-option>
                            <atlas-option label="RR" value="RR">RR</atlas-option>
                            <atlas-option label="TO" value="TO">TO</atlas-option>

                            <atlas-option label="AL" value="AL">AL</atlas-option>
                            <atlas-option label="BA" value="BA">BA</atlas-option>
                            <atlas-option label="CE" value="CE">CE</atlas-option>
                            <atlas-option label="MA" value="MA">MA</atlas-option>
                            <atlas-option label="PB" value="PB">PB</atlas-option>
                            <atlas-option label="PE" value="PE">PE</atlas-option>
                            <atlas-option label="PI" value="PI">PI</atlas-option>
                            <atlas-option label="RN" value="RN">RN</atlas-option>
                            <atlas-option label="SE" value="SE">SE</atlas-option>

                            <atlas-option label="DF" value="DF">DF</atlas-option>
                            <atlas-option label="GO" value="GO">GO</atlas-option>
                            <atlas-option label="MS" value="MS">MS</atlas-option>
                            <atlas-option label="MT" value="MT">MT</atlas-option>

                            <atlas-option label="ES" value="ES">ES</atlas-option>
                            <atlas-option label="MG" value="MG">MG</atlas-option>
                            <atlas-option label="RJ" value="RJ">RJ</atlas-option>
                            <atlas-option label="SP" value="SP">SP</atlas-option>

                            <atlas-option label="PR" value="PR"></atlas-option>
                            <atlas-option label="RS" value="RS"></atlas-option>
                            <atlas-option label="SC" value="SC"></atlas-option>
                        </atlas-select>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
        </atlas-form-panel>
    </atlas-page-content>
</atlas-page>

</body>
</html>