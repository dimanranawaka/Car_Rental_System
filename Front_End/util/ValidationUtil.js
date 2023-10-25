/** This can be used to validate user inputs by invoke below function */

function checkValidity(validationArray) {
    let errorCount = 0;
    for (let validationArrayElement of validationArray) {
        if (check(validationArrayElement.reg,validationArrayElement.field)){

        }else{
            errorCount++;

        }
    }
}

function check(regex, textField) {
    let inputValue = textField.val();
    return regex.test(inputValue) ?true : false;
}



function focusText(textField) {
    textField.focus();
}