function checkValidity(validationArray) {
    let errorCount = 0;
    for (let validationArrayElement of validationArray) {
        if (check(validationArrayElement.reg,validationArrayElement.field)){

        }else{
            errorCount++;

        }
    }
}



function focusText(textField) {
    textField.focus();
}