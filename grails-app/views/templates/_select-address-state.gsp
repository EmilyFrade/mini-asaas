<%
    // Componente responsável por renderizar um select com os estados do Brasil.

    // @param value: valor do estado selecionado
    // @param disabled: se o select está desabilitado (default: false)
    // @param autofill: se o select deve ser preenchido automaticamente (default: false)
%>

<%@ page import="com.mini.asaas.enums.address.AddressState" %>
<atlas-select
    data-autofill="${autofill ?: false}"
    disabled="${disabled ?: false}"
    label="Estado"
    placeholder="Selecione um estado"
    name="state"
    id="state"
    value="${value}"
    required>
    <g:each in="${AddressState.values()}" var="state">
        <atlas-option label="${state.name()}" value="${state.name()}"></atlas-option>
    </g:each>
</atlas-select>