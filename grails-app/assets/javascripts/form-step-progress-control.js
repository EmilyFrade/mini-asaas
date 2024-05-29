class AtlasFormStepProgressControl {

    static init(form) {
        return new AtlasFormStepProgressControl(form);
    }

    constructor(form) {
        this.CHANGE_STEP_EVENT_ID = "atlas-form-change-step";
        this.TRANSITION_DURATION_MS = 10;

        if (!form.id) return;
        this.progressArea = document.querySelector(`.progress-area[data-form-id=${form.id}]`);
        if (!this.progressArea) return;

        this.checkpoints = this.progressArea.querySelectorAll(".progress-checkpoint");
        this.progressBar = this.progressArea.querySelector(".progress-bar");
        if (!this.checkpoints || !this.progressBar) return;

        this.currentStep = null;
        this.previousStep = null;
        this.animation = null;

        form.addEventListener(this.CHANGE_STEP_EVENT_ID, this.onChangeStep.bind(this));
    }

    onChangeStep(event) {
        this.currentStep = this.getStep(event.detail.step);
        this.previousStep = this.getStep(event.detail.previousStep);

        if (!this.currentStep) return;

        this.animation = setInterval(this.startChangeStepAnimation.bind(this), this.TRANSITION_DURATION_MS);
    }

    getStep(stepElement) {
        return parseInt(stepElement?.getAttribute("data-step")) || null;
    };

    itsAStepForward() {
        return this.currentStep > this.previousStep;
    }

    getProgressBarValue() {
        return parseFloat(this.progressBar.value) || 0;
    }

    updateProgressBarValue() {
        const updatedValue = this.getProgressBarValue() + (this.itsAStepForward() ? 1 : -1);
        this.progressBar.value = updatedValue.toString();
    }

    updateCheckpointsActivationState() {
        this.checkpoints.forEach((checkpoint, index) => {
            if (index <= this.currentStep - 1) {
                checkpoint.setAttribute("data-active", "true");
            } else {
                checkpoint.removeAttribute("data-active");
            }
        });
    }

    animationShouldStop() {
        const progressValue = this.getProgressBarValue();
        const factor = 100 / (this.checkpoints.length - 1);
        const edgeLimit = factor * (this.currentStep - 1);
        return this.itsAStepForward() ? progressValue >= edgeLimit : progressValue <= edgeLimit;
    }

    stopChangeStepAnimation() {
        clearInterval(this.animation);
        this.updateCheckpointsActivationState();
    }

    startChangeStepAnimation() {
        if (this.animationShouldStop()) return this.stopChangeStepAnimation();
        this.updateProgressBarValue();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const formsWithSteps = document.querySelectorAll("[data-has-steps='true']");
    formsWithSteps.forEach(form => {
        if (form) {
            const startStep = form.getAttribute("data-start-step") || "1";
            if (startStep > 1) form.changeStep(startStep - 1);
            AtlasFormStepProgressControl.init(form);
        }
    });
});