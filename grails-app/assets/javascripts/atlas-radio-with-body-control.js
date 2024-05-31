class AtlasRadioWithBodyControl {

    static allowedTags = new Set(["ATLAS-RADIO", "ATLAS-SELECTION-CARD"]);

    static init(radio, defaultSelectedId) {
        if (!radio) return null;
        if (!AtlasRadioWithBodyControl.allowedTags.has(radio.tagName)) return;
        return new AtlasRadioWithBodyControl(radio, defaultSelectedId);
    }

    constructor(radio, defaultSelectedId) {
        this.radio = radio;

        this.section = this.getAssociatedSection(this.radio.id);
        if (!this.section) return;

        this.fields = this.section.querySelectorAll("[data-field='true']");
        if (!this.fields?.length) return;

        if (this.radio.tagName === "ATLAS-RADIO") {
            this.checkEventId = "atlas-radio-check";
            this.uncheckEventId = "atlas-radio-uncheck";
        }

        if (this.radio.tagName === "ATLAS-SELECTION-CARD") {
            this.checkEventId = "atlas-selection-card-check";
            this.uncheckEventId = "atlas-selection-card-uncheck";
        }

        if (!this.checkEventId || !this.uncheckEventId) return;

        this.radio.addEventListener(this.checkEventId, this.onCheck.bind(this));
        this.radio.addEventListener(this.uncheckEventId, this.onUncheck.bind(this));

        if (defaultSelectedId.toLowerCase() === this.radio.id?.toLowerCase()) {
            this.radio.checked = true;
        }
    }

    showOrHideSection(show) {
        this.section.setAttribute("data-active", show ? "true" : "false");
    }

    clearFields() {
        this.fields.forEach(field => {
            field.value = "";
            field.checked = false;
            field.selectedIndex = 0;
        });
    }

    enableOrDisableFields(enable) {
        this.fields.forEach(field => {
            if (enable) return field.removeAttribute("disabled");
            field.setAttribute("disabled", "");
        });
    }

    onCheck(event) {
        this.enableOrDisableFields(true);
        this.showOrHideSection(true);
    }

    onUncheck(event) {
        this.enableOrDisableFields(false);
        this.showOrHideSection(false);
        this.clearFields();
    }

    getAssociatedSection(id) {
        return document.getElementById(`${id}-section`) || null;
    }

}

document.addEventListener("DOMContentLoaded", () => {
    const groups = document.querySelectorAll(".radio-group[data-has-body='true']");
    if (!groups?.length) return;
    for (const group of groups) {
        const defaultSelected = group.getAttribute("data-default");
        for (const radio of group.children) {
            AtlasRadioWithBodyControl.init(radio, defaultSelected);
        }
    }
});