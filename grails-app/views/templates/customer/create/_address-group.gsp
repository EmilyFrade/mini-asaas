<%@ page contentType="text/html;charset=UTF-8" %>

<div class="address-zip-code-area">
  <atlas-postal-code
    disable-search
    name="zipCode"
    label="CEP"
    value="${params.zipCode}"
    required>
  </atlas-postal-code>
  <atlas-link
    size="sm"
    icon="info"
    external
    block
    content-width
    href="https://buscacepinter.correios.com.br/app/endereco/index.php"
    title="Buscar CEP">
    Não sei o meu CEP
  </atlas-link>
</div>
<atlas-layout gap="3" id="address-group" hidden>
  <atlas-grid>
    <atlas-row>
      <atlas-col>
        <atlas-input
          data-autofill="true"
          type="text"
          name="city"
          label="Cidade"
          value="${params.city}"
          disabled
          required>
        </atlas-input>
      </atlas-col>
    </atlas-row>
    <atlas-row>
      <atlas-col>
        <g:render
          template="/templates/address-state-select"
          model="[state: params.state, autofill: true, disabled: true]" />
      </atlas-col>
    </atlas-row>
    <atlas-row>
      <atlas-col>
        <atlas-input
          data-autofill="true"
          type="text"
          name="address"
          label="Rua"
          value="${params.address}"
          required>
        </atlas-input>
      </atlas-col>
    </atlas-row>
    <atlas-row>
      <atlas-col>
        <atlas-input
          data-autofill="true"
          type="text"
          name="province"
          label="Bairro"
          value="${params.province}"
          required>
        </atlas-input>
      </atlas-col>
    </atlas-row>
    <atlas-row>
      <atlas-col lg="4" md="2">
        <atlas-integer-input
          data-autofill="true"
          type="text"
          name="addressNumber"
          label="Nº"
          placeholder="S/N"
          value="${params.addressNumber}">
        </atlas-integer-input>
      </atlas-col>
      <atlas-col lg="8" md="4">
        <atlas-input
          data-autofill="true"
          type="text"
          name="complement"
          label="Complemento"
          value="${params.complement}">
        </atlas-input>
      </atlas-col>
    </atlas-row>
  </atlas-grid>
</atlas-layout>