cloudinary.setCloudName('library-codecool');

widget = cloudinary.createUploadWidget({ upload_preset: 'u2eniosg' },
    function(error, result) {
    console.log(result[0].url);
    let data = {
        "url": result[0].url,
    };

    $.ajax({
        url: '/uploadFile',
        type: 'POST',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(data),
        success: function (response) {
            location.reload(true);
        }
    })});

$('#uploadButton').on('click', function (event) {
    event.preventDefault();
    widget.open();
});
