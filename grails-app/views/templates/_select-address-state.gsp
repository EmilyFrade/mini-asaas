<%@ page import="com.mini.asaas.enums.address.AddressState" %>
<atlas-select
    data-autofill="${autofill ?: false}"
    disabled="${disabled}"
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