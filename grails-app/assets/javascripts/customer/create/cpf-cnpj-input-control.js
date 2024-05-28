document.addEventListener("DOMContentLoaded", () => {
  const cpfCnpjField = document.querySelector("atlas-masked-input[name='cpfCnpj']");
  const cpfField = document.querySelector("atlas-masked-input[name='cpf']");
  const cnpjField = document.querySelector("atlas-masked-input[name='cnpj']");

  if (!cpfCnpjField || !cpfField || !cnpjField) return;

  const onInputChange = (event) => {
    const trimmedValue = event.detail?.trim();
    if (trimmedValue.length !== 14 && trimmedValue.length !== 18) return;
    cpfCnpjField.value = event.detail;
  };

  cpfField.addEventListener("atlas-input-change", onInputChange);
  cnpjField.addEventListener("atlas-input-change", onInputChange);
});