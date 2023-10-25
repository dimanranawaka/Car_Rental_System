
/** Saving Alert */

function saveAlert() {

    Swal.fire({
        position: 'top-end',
        icon:'success',
        title:'Successfully Saved !',
        showConfirmButton: false,
        timer: 1500

    });

}

/** Updating Alert */

function updateAlert() {

    Swal.fire({
        position: 'top-end',
        icon:'success',
        title:'Successfully Updated !',
        showConfirmButton: false,
        timer: 1500

    });

}

/** Deleting Alert */

function deleteAlert() {

    Swal.fire({
        position: 'top-end',
        icon:'success',
        title:'Successfully Deleted !',
        showConfirmButton: false,
        timer: 1500

    });

}

/** Error Alert */

function errorAlert(error) {

    Swal.fire({
        position: 'top-end',
        icon:'error',
        title:error,
        showConfirmButton: false,
        timer: 1500

    });

}