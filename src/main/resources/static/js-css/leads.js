$(document).ready(function() {
    $('.table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).data('href');

        $.get(href, function(leadRecords, status) {
            $('.myForm #id').val(leadRecord.id);
            $('.myForm #fullName').val(leadRecords.fullName);
            $('.myForm #phoneNumber').val(leadRecords.phoneNumber);
            $('.myForm #loanCap').val(leadRecords.loanCap);
            $('.myForm #interestedProperty').val(leadRecords.interestedProperty);
            $('.myForm #initialContactDate').val(leadRecords.initialContactDate);
        });

        $('#exampleModal').modal('show');
    });
});
