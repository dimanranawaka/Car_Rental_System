/** This can be used to validate user inputs by invoke below function */

function checkValidity(validationArray) {
    let errorCount = 0;
    for (let validationArrayElement of validationArray) {
        if (check(validationArrayElement.reg,validationArrayElement.field)){
            textSuccess(validationArrayElement.field, "");
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

function textSuccess(txtField,error) {
    if (txtField.val().length <= 0){
        defaultText(txtField,"");
    }else{
        txtField.css("border","1.5px solid green");
        txtField.parent().children('span').text(error);
    }
}

function defaultText (txtField,error){
    txtField.css("border","1.5px solid #94c3f2");
    txtField.parent().children('span').text(error);
}