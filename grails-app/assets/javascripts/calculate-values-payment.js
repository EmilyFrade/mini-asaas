const originalValueInput = document.getElementById("originalValue");
const calculateButton = document.getElementById("calculateButton");

function calculateAndDisplayValues() {
    const originalValue = parseFloat(originalValueInput.value);
    const interestPercent = parseFloat(document.getElementById("interestPercentual").value);
    const discountPercent = parseFloat(document.getElementById("discountPercentual").value);

    const interestValue = (originalValue * interestPercent) / 100;
    const discountValue = (originalValue * discountPercent) / 100;
    const netValue = originalValue - discountValue;

    document.getElementById("interestValue").value = interestValue;
    document.getElementById("discountValue").value = discountValue;
    document.getElementById("netValue").value = netValue;
}

calculateButton.addEventListener("click", calculateAndDisplayValues);