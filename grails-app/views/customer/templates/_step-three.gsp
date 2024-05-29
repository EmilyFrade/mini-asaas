<%
    // Componente responsável por renderizar a 3ª etapa do formulário de cadastro de cliente.

    // @param address - Map com os valores dos campos de endereço.
    // @param opened - Booleano que define se os campos estão visíveis, antes do autopreenchimento. (default: false).
%>

<%@ page import="com.mini.asaas.enums.address.AddressState" %>

<atlas-form-step id="create-customer-step-three" name="step-three" data-step="3">
    <atlas-section header="Endereço Principal" header-size="h5">
        <atlas-layout gap="4">
            <atlas-text muted size="xs">
                Informe os dados abaixo com atenção para que não haja alterações no decorrer do cadastro.
            </atlas-text>
            <atlas-layout gap="5">
                <g:render template="/templates/address-group" model="${[address: address, opened: opened]}" />
            </atlas-layout>

            <div class="form-step-actions">
                <atlas-button theme="secondary" description="Voltar" data-atlas-form-previous-step></atlas-button>
                <atlas-button description="Confirmar" data-atlas-form-submit=""></atlas-button>
            </div>
        </atlas-layout>
    </atlas-section>
</atlas-form-step>