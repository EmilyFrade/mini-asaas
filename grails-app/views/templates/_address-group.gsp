<%
    // Componente responsável por renderizar campos de formulário para endereço

    // @param address - Map com os valores dos campos de endereço.
    // @param opened - Booleano que define se os campos estão visíveis, antes do autopreenchimento. (default: false).
%>

<asset:stylesheet src="address-group" />

<div class="address-zip-code-area">
    <atlas-postal-code
        disable-search
        name="zipCode"
        label="CEP"
        value="${address.zipCode}"
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
<atlas-layout gap="3" id="address-group" opened="${opened ?: false}">
    <atlas-grid>
        <atlas-row>
            <atlas-col>
                <atlas-input
                    data-autofill="true"
                    type="text"
                    name="city"
                    label="Cidade"
                    value="${address.city}"
                    disabled
                    required>
                </atlas-input>
            </atlas-col>
        </atlas-row>
        <atlas-row>
            <atlas-col>
                <g:render
                    template="/templates/select-address-state"
                    model="[state: address.state, autofill: true, disabled: true]" />
            </atlas-col>
        </atlas-row>
        <atlas-row>
            <atlas-col>
                <atlas-input
                    data-autofill="true"
                    type="text"
                    name="address"
                    label="Rua"
                    value="${address.address}"
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
                    value="${address.province}"
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
                    value="${address.addressNumber}">
                </atlas-integer-input>
            </atlas-col>
            <atlas-col lg="8" md="4">
                <atlas-input
                    data-autofill="true"
                    type="text"
                    name="complement"
                    label="Complemento"
                    value="${address.complement}">
                </atlas-input>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-layout>

<asset:javascript src="address-autofill" />