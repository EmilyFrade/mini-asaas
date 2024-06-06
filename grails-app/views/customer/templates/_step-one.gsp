<%@ page import="com.mini.asaas.enums.PersonType; com.mini.asaas.customer.CompanyType" %>

<%
    PersonType initialPersonType = PersonType.fromString(params.personType as String) ?: PersonType.NATURAL

    String initialPerson = initialPersonType.name().toLowerCase()
    String legalPerson = PersonType.LEGAL.name().toLowerCase()
    String naturalPerson = PersonType.NATURAL.name().toLowerCase()
%>

<atlas-form-step name="step-one" data-step="1">
    <atlas-section header="Dados principais" header-size="h5">
        <atlas-layout gap="4">
            <atlas-text muted size="xs">Para começar, nos informe como você trabalha.</atlas-text>
            <atlas-layout gap="3">
                <atlas-text class="selection-card-group-label" bold size="xs">Trabalho como</atlas-text>
                <atlas-layout gap="3" class="radio-group" data-has-body="true" data-default="${initialPerson}">
                    <atlas-selection-card
                        label="${PersonType.NATURAL.getLabel()}"
                        type="radio"
                        name="personType"
                        id=${naturalPerson}
                        value="${naturalPerson}"
                        required>
                        Autônomo sem CNPJ
                    </atlas-selection-card>
                    <atlas-selection-card
                        label="${PersonType.LEGAL.getLabel()}"
                        type="radio"
                        name="personType"
                        id=${legalPerson}
                        value="${legalPerson}"
                        required>
                        MEI, LTDA, S/A, SS
                    </atlas-selection-card>
                </atlas-layout>
            </atlas-layout>
            <atlas-layout
                gap="3"
                class="controlled-section"
                id="${naturalPerson}-section"
                data-active="${initialPersonType.isNatural()}">
                <atlas-masked-input
                    ${initialPersonType.isNatural() ?: 'disabled'}
                    data-field="true"
                    mask-alias="cpf"
                    name="cpf"
                    label="CPF"
                    value="${params.cpf}"
                    type="text"
                    required>
                </atlas-masked-input>
                <atlas-datepicker
                    ${initialPersonType.isNatural() ?: 'disabled'}
                    data-field="true"
                    label="Data de nascimento"
                    name="birthDate"
                    value="${params.birthDate}"
                    required>
                </atlas-datepicker>
            </atlas-layout>
            <atlas-layout
                gap="3"
                class="controlled-section"
                id="${legalPerson}-section"
                data-active="${initialPersonType.isLegal()}">
                <atlas-masked-input
                    ${initialPersonType.isLegal() ?: 'disabled'}
                    data-field="true"
                    mask-alias="cnpj"
                    name="cnpj"
                    label="CNPJ"
                    value="${params.cnpj}"
                    type="text"
                    required>
                </atlas-masked-input>
                <atlas-select
                    ${initialPersonType.isLegal() ?: 'disabled'}
                    data-field="true"
                    label="Qual o tipo da empresa?"
                    placeholder="Selecione um tipo"
                    name="companyType"
                    id="companyType"
                    value="${params.companyType}"
                    required>
                    <g:each in="${CompanyType.values()}" var="type">
                        <atlas-option label="${type.getLabel()}" value="${type.name()}"></atlas-option>
                    </g:each>
                </atlas-select>
            </atlas-layout>
        </atlas-layout>
    </atlas-section>
    <atlas-button class="form-step-single-action" description="Avançar" data-atlas-form-next-step></atlas-button>
</atlas-form-step>