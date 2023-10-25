/** This can be used to validate user inputs by invoke below function */

function checkValidity(validationArray) {
    let errorCount = 0;
    for (let validationArrayElement of validationArray) {
        if (check(validationArrayElement.reg,validationArrayElement.field)){
            textSuccess(validationArrayElement.field, "");
        }else{
            errorCount++;
            setTextError(validationArrayElement.field,validationArrayElement.error);
        }
    }
    setButtonAvailable(errorCount);
}

function check(regex, textField) {
    let inputValue = textField.val();
    return regex.test(inputValue) ?true : false;
}

function setTextError(txtField,error){
    if (txtField.val().length <=0){
        defaultText(txtField,"");
    }else {
        txtField.css("border","2.5px solid red");
        txtField.parent().children('span').text(error);
    }
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

function setButtonAvailable(value) {
    if (value>0){
        $("#btnSaveCustomer, #btnSaveDriver , #btnSaveCar").attr('disabled',true);
    } else {
        $("#btnSaveCustomer, #btnSaveDriver , #btnSaveCar").attr('disabled',false);
    }
}