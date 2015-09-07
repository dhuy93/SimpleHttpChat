/**
 * 
 */
function doAjax() {
	var palEmailStr = document.getElementById('palEmailInput').value;
	var newContentStr = document.getElementById('buffer').value;
	$.ajax({
		url: 'refreshconversation.html',
		data: ({'palEmail':palEmailStr,
				'newContent':newContentStr}),
		dataType: 'json',
		success: function(data) {
			console.log(data);
			$('#items').empty();
			$.each(data, function(i, val) {
				var tr = document.getElementById("items").insertRow(-1);
				var time = tr.insertCell(-1);
				time.innerHTML = val.createdTime;
				var sender = tr.insertCell(-1);
				sender.innerHTML = val.sender;
				var msgContent = tr.insertCell(-1);
				msgContent.innerHTML = val.content;
			});
		}
	});
}

$(document).ready(function() {
	doAjax();
	setInterval(doAjax, 2000);
});

function onClickSendBtn() {
	var buffer = document.getElementById('buffer');
	var newContent = document.getElementById('newContent');
	buffer.value = newContent.value;
	newContent.value = "";
	doAjax();
	buffer.value = "";
}