<%@ page import="core.entities.Person; core.valueobjects.Address" %>
<html>
<head>
    <title>Detalhes do Pagador</title>
    <meta name="layout" content="main">
</head>
<body page-title="Detalhes do Pagador">
<atlas-form-panel header="Detalhes do pagador" description="" submit-button-label=""
                  action="${createLink(controller: "payer", action: "update", params: [id: payer.id])}" method="post">
    <atlas-input
            value="${payer.id}"
            name="id"
            hidden>
    </atlas-input>

    <atlas-button slot="actions" description="Editar" icon="pencil" data-panel-start-editing="true"></atlas-button>

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
            <atlas-col  lg="6">
                <atlas-input
                        label="CPF/CNPJ"
                        name="cpfCnpj"
                        value="${Person.formatCpfCnpj(payer.cpfCnpj)}"
                        required="true">
                </atlas-input>
            </atlas-col>
        </atlas-row>

        <atlas-row>
            <atlas-col lg="6">
                <atlas-input
                        label="Celular"
                        name="phoneNumber"
                        value="${payer.phoneNumber}"
                        required="true">
                </atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-input
                        label="CEP"
                        name="zipCode"
                        value="${Address.formatZipCode(payer.address.zipCode)}"
                        required="true">
                </atlas-input>
            </atlas-col>
        </atlas-row>

        <atlas-row>
            <atlas-col lg="4">
                <atlas-input
                        label="Rua"
                        name="street"
                        value="${payer.address.street}"
                        required="true">
                </atlas-input>
            </atlas-col>
            <atlas-col lg="4">
                <atlas-input
                        label="Número"
                        name="number"
                        value="${payer.address.number}"
                        required="true">
                </atlas-input>
            </atlas-col>
            <atlas-col lg="4">
                <atlas-input
                        label="Complemento"
                        name="complement"
                        value="${payer.address.complement}">
                </atlas-input>
            </atlas-col>
        </atlas-row>

        <atlas-row>
            <atlas-col lg="4">
                <atlas-input
                        label="Bairro"
                        name="neighborhood"
                        value="${payer.address.neighborhood}"
                        required="true">
                </atlas-input>
            </atlas-col>
            <atlas-col lg="4">
                <atlas-input
                        label="Cidade"
                        name="city"
                        value="${payer.address.city}"
                        required="true">
                </atlas-input>
            </atlas-col>
            <atlas-col lg="4">
                <atlas-input
                        label="Estado (UF)"
                        name="state"
                        value="${payer.address.state}"
                        required="true">
                </atlas-input>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-form-panel>
</body>
</html>