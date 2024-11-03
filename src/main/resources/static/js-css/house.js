$(document).ready(function() {
    $('.table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).data('href');

        $.get(href, function(houseListing, status) {
            $('.myForm #id').val(houseListing.id);
            $('.myForm #propertyId').val(houseListing.propertyId);
            $('.myForm #propertyType').val(houseListing.propertyType);
            $('.myForm #address').val(houseListing.address);
            $('.myForm #price').val(houseListing.price);
        });

        $('#exampleModal').modal('show');
    });
});
