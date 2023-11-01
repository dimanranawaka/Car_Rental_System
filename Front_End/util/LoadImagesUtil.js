/** This one for loading selected images on the site */

function loadSelectedImage(divId) {
    // When the input field with the specified 'divId' changes (a new file is selected)
    $(divId).on("change", function (e) {
        // Get the selected file(s)
        let file = e.target.files;

        // Check if the FileReader API is supported, and there is at least one file selected
        if (FileReader && file && file.length) {
            // Create a new FileReader instance
            let reader = new FileReader();

            // Define a callback function to execute when the file is read
            reader.onload = function () {
                // Change the background of the parent element (container of the input field)
                $(divId).parent().children(":eq(0)").css({
                    "background": `url(${reader.result})`,  // Set the background image using the file's data URL
                    "background-size": "cover",            // Adjust the background size to cover the container
                    "background-position": "center"        // Center the background image
                });
            }

            // Read the selected file as a data URL (base64-encoded)
            reader.readAsDataURL(file[0]);
        }
    });
}
