function checkValidity(validationArray) {
    let errorCount = 0;
    for (let validation of validationArray) {
        if (check(validation.reg, validation.field)) {
            textSuccess(validation.field, "");
        } else {
            errorCount = errorCount + 1;
            setTextError(validation.field, validation.error);
        }
    }
    setButtonState(errorCount);
}
// This function checks if a regular expression matches the value of a text field.
function check(regex, txtField) {
    let inputValue = txtField.val();
    return regex.test(inputValue) ? true : false;
}
// This function sets an error state for a text field and displays an error message.
function setTextError(txtField, error) {
    if (txtField.val().length <= 0) {
        defaultText(txtField, "");
    } else {
        txtField.css('border', '2.5px solid red');
        txtField.parent().children('span').text(error);
    }
}
// This function sets a success state for a text field and displays a success message
function textSuccess(txtField, error) {
    if (txtField.val().length <= 0) {
        defaultText(txtField, "");
    } else {
        txtField.css('border', '2.5px solid green');
        txtField.parent().children('span').text(error);
    }
}
// This function resets a text field to its default state
function defaultText(txtField, error) {
    txtField.css("border", "1.5px solid #ced4da");
    txtField.parent().children('span').text(error);
}
// This function focuses on a specified text field
function focusText(txtField) {
    txtField.focus();
}

// This function sets the state (enabled/disabled) of certain buttons based on a value
function setButtonState(value) {
    if (value > 0) {
        $("#btnSaveCustomer, #btnSaveDriver, #btnSaveCar").attr('disabled', true);
    } else {
        $("#btnSaveCustomer, #btnSaveDriver, #btnSaveCar").attr('disabled', false);
    }
}