const ATLAS_FORM_CHANGE_STEP_EVENT_ID = "atlas-form-change-step";
const TRANSITION_PROGRESS_BAR_CYCLE_DURATION_MS = 10;

const getStep = (stepElement) => {
  return parseInt(stepElement.getAttribute("data-step")) || null;
};

const getCurrentAndPreviousStep = (event) => {
  const previousStep = event.detail.previousStep;
  const step = event.detail.step;
  return { previousStep: getStep(previousStep), currentStep: getStep(step) };
};

const updateProgressBarValue = (progressBar, itsAStepForward) => {
  if (!progressBar) return;
  const currentProgressValue = parseFloat(progressBar.value);
  const newProgressValue = itsAStepForward ? currentProgressValue + 1 : currentProgressValue - 1;
  progressBar.value = newProgressValue.toString();
};

const updateActivationStatusOfCheckpoints = (checkpoints, currentStep) => {
  for (let i = 0; i < checkpoints.length; i++) {
    const checkpoint = checkpoints[i];
    if (i <= currentStep - 1) {
      checkpoint.setAttribute("data-active", "true");
    } else {
      checkpoint.removeAttribute("data-active");
    }
  }
};

const onChangeFormStep = (event) => {
  const checkpoints = document.querySelectorAll(".progress--checkpoint");
  const progressBar = document.querySelector(".progress--bar");
  const { previousStep, currentStep } = getCurrentAndPreviousStep(event);

  if (!currentStep || !checkpoints || !progressBar) return;

  const factor = 100 / (checkpoints.length - 1);
  const itsAStepForward = currentStep > previousStep;

  const interval = setInterval(() => {

    const progressValue = parseFloat(progressBar.value);
    const edgeLimit = factor * (currentStep - 1);

    const breakCondition = itsAStepForward ? progressValue >= edgeLimit : progressValue <= edgeLimit;

    if (breakCondition) {
      clearInterval(interval);
      updateActivationStatusOfCheckpoints(checkpoints, currentStep);
      return;
    }

    updateProgressBarValue(progressBar, itsAStepForward);
  }, TRANSITION_PROGRESS_BAR_CYCLE_DURATION_MS);
};

document.addEventListener("DOMContentLoaded", () => {
  const createCustomerForm = document.getElementById("create-customer-form");
  createCustomerForm.dispatchEvent(new CustomEvent(ATLAS_FORM_CHANGE_STEP_EVENT_ID, { step: createCustomerForm.getCurrentStep() }));
  createCustomerForm.addEventListener(ATLAS_FORM_CHANGE_STEP_EVENT_ID, onChangeFormStep);
});