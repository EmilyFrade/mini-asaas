const ATLAS_ZIP_CODE_CHANGE_EVENT_ID = "atlas-input-change";

class InvalidZipCodeError extends Error {
  constructor() {
    super("CEP inválido, digite um CEP válido!");
  }
}

class NotFoundZipCodeError extends Error {
  constructor() {
    super("Endereço não encontrado, digite um CEP existente!");
  }
}

const parseFromViaCEPApiResponse = (json) => {
  console.log(json);
  const { logradouro, localidade, bairro, uf } = json;
  return { address: logradouro, city: localidade, province: bairro, state: uf };
};

const fetchAddressByZipCode = async (zipCode) => {
  try {
    const response = await fetch(`https://viacep.com.br/ws/${zipCode}/json`);
    if (response.status === 400) throw new InvalidZipCodeError();
    const json = await response.json();

    if (json?.erro || response.status === 404) throw new NotFoundZipCodeError();

    return { success: true, data: parseFromViaCEPApiResponse(json) };
  } catch (error) {
    const defaultMessage = "Ocorreu um erro inesperado ao tentar localizar o endereço com base no CEP digitado.";
    return { success: false, message: error.message || defaultMessage };
  }
};

const getAddressGroup = () => {
  return document.getElementById("address-group");
};

const showAddressGroup = () => {
  const group = getAddressGroup();
  if (!group) return;
  group.removeAttribute("hidden");
};

const hideAddressGroup = () => {
  const group = getAddressGroup();
  if (!group) return;
  group.setAttribute("hidden", "");
};

const getAddressFields = () => {
  const group = getAddressGroup();
  if (!group) return;
  const addressFields = group.querySelectorAll("[data-autofill='true']");
  return Array.from(addressFields);
};

const fillAddressFields = (addressData) => {
  const addressFields = getAddressFields();
  console.log(addressFields);
  if (!addressFields) return;
  addressFields
    .filter(f => f.name in addressData)
    .forEach(f => f.value = addressData[f.name]);
};

const clearAddressFields = () => {
  const addressFields = getAddressFields();
  if (!addressFields) return;
  addressFields.forEach(f => f.value = "");
};

document.addEventListener("DOMContentLoaded", (e) => {
  const zipCodeField = document.querySelector("atlas-postal-code");

  zipCodeField.addEventListener(ATLAS_ZIP_CODE_CHANGE_EVENT_ID, async (e) => {
    if (zipCodeField.value.length !== 9) return;
    const response = await fetchAddressByZipCode(e.detail);
    if (response.success) {
      fillAddressFields(response.data);
      showAddressGroup();
    } else {
      hideAddressGroup();
      clearAddressFields();
      zipCodeField.value = "";
      alert(response.message);
    }
  });
});