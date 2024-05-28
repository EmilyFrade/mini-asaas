<%@ page import="com.mini.asaas.enums.PersonType" %>
<%@ page import="com.mini.asaas.customer.CompanyType" %>

<%
  String legalPerson = PersonType.LEGAL.name()
  String naturalPerson = PersonType.NATURAL.name()
  String defaultPersonType = params.personType ?: naturalPerson
%>

<atlas-form-step name="step-one" data-step="1">
  <atlas-section header="Dados principais" header-size="h5">
    <atlas-layout gap="4">
      <atlas-text muted size="xs">Para começar, nos informe como você trabalha.</atlas-text>
      <atlas-layout gap="3">
        <atlas-text class="selection-card-group-label" bold size="xs">Trabalho como</atlas-text>
        <div class="selection-card-group" data-radio="true" data-default="${defaultPersonType}">
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
        </div>
      </atlas-layout>
      <atlas-masked-input
        mask-alias="cpf-cnpj"
        hidden
        label="CPF/CNPJ"
        value="${params.cpfCnpj}"
        name="cpfCnpj"
        type="text">
      </atlas-masked-input>
      <section class="selection-card-group-area" id="${naturalPerson}-section">
        <atlas-masked-input
          data-field="true"
          mask-alias="cpf"
          name="cpf"
          label="CPF"
          value="${params.cpf}"
          required>
        </atlas-masked-input>
        <atlas-datepicker
          data-field="true"
          label="Data de nascimento"
          name="birthDate"
          value="${params.birthDate}"
          required>
        </atlas-datepicker>
      </section>
      <section class="selection-card-group-area" id="${legalPerson}-section">
        <atlas-masked-input
          data-field="true"
          mask-alias="cnpj"
          name="cnpj"
          label="CNPJ"
          value="${params.cnpj}"
          required>
        </atlas-masked-input>
        <atlas-select
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
      </section>
    </atlas-layout>
  </atlas-section>
  <atlas-button class="form-step-single-action" description="Avançar" data-atlas-form-next-step></atlas-button>
</atlas-form-step>
