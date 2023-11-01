/** This function can be used to validate user inputs by invoking the checkValidity function. */
function checkValidity(validationArray) {
    let errorCount = 0;
    for (let validationArrayElement of validationArray) {

        // Check each validation rule and handle success or error

        if (check(validationArrayElement.reg, validationArrayElement.field)) {
            textSuccess(validationArrayElement.field, "");
        } else {
            errorCount++;
            setTextError(validationArrayElement.field, validationArrayElement.error);
        }
    }
    // Enable or disable buttons based on the error count
    setButtonAvailable(errorCount);
}

/** Check if the input value matches the provided regex pattern. */
function check(regex, textField) {
    let inputValue = textField.val();
    return regex.test(inputValue);
}

/** Set an error state for a text field and display an error message. */
function setTextError(txtField, error) {
    if (txtField.val().length <= 0) {
        defaultText(txtField, "");
    } else {
        txtField.css("border", "2.5px solid red");
        txtField.parent().children('span').text(error);
    }
}

/** Focus on the provided text field. */
function focusText(textField) {
    textField.focus();
}

/** Set a success state for a text field and display a success message. */
function textSuccess(txtField, error) {
    if (txtField.val().length <= 0) {
        defaultText(txtField, "");
    } else {
        txtField.css("border", "1.5px solid green");
        txtField.parent().children('span').text(error);
    }
}

/** Reset a text field to its default state. */
function defaultText(txtField, error) {
    txtField.css("border", "1.5px solid #94c3f2");
    txtField.parent().children('span').text(error);
}

/** Enable or disable buttons based on the provided value. */
function setButtonAvailable(value) {
    if (value > 0) {
        $("#btnSaveCustomer, #btnSaveDriver, #btnSaveCar").attr('disabled', true);
    } else {
        $("#btnSaveCustomer, #btnSaveDriver, #btnSaveCar").attr('disabled', false);
    }
}
