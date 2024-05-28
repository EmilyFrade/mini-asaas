const ATLAS_SELECTION_CARD_CHECK_EVENT_ID = "atlas-selection-card-check";
const ATLAS_SELECTION_CARD_UNCHECK_EVENT_ID = "atlas-selection-card-uncheck";

const getSelectionCardSection = (event) => {
  const id = event.target?.id;
  if (!id) return null;
  return document.getElementById(`${id}-section`);
};

const onCheckSelectionCard = (event) => {
  const section = getSelectionCardSection(event);
  if (section) {
    const fields = section.querySelectorAll("[data-field='true']");
    fields.forEach((field) => {
      field.removeAttribute("disabled");
      field.value = "";
    });
    section.classList.remove("hidden");
  }
};

const onUncheckSelectionCard = (event) => {
  const section = getSelectionCardSection(event);
  if (section) {
    const fields = section.querySelectorAll("[data-field='true']");
    fields.forEach((field) => {
      field.setAttribute("disabled", "");
      field.value = "";
    });
    section.classList.add("hidden");
  }
};

document.addEventListener("DOMContentLoaded", () => {
  const groups = document.querySelectorAll(".selection-card-group[data-radio='true']");
  if (!groups || !groups.length) return;
  for (const group of groups) {
    const defaultCardInGroup = group.getAttribute("data-default");
    for (const card of group.children) {

      card.addEventListener(ATLAS_SELECTION_CARD_CHECK_EVENT_ID, onCheckSelectionCard);
      card.addEventListener(ATLAS_SELECTION_CARD_UNCHECK_EVENT_ID, onUncheckSelectionCard);

      if (defaultCardInGroup === card.id) {
        card.checked = true;
        continue;
      }

      card.dispatchEvent(new CustomEvent(ATLAS_SELECTION_CARD_UNCHECK_EVENT_ID));
    }
  }
});