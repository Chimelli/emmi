var stompClient = null;

function setConnected(connected) {

}

function connect(pictureid) {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/comments/' + pictureid, function(greeting){
            showComment(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function showComment(comment) {
	console.log(comment);
    var commentsList = $(".comments ul");
    commentsList.append(
    		"<li><strong><span>" + escapeHtml(comment.name) + ": </span></strong>" +
    		"<span>" + escapeHtml(comment.comment) + "</span></li>");
    scrollCommentsToBottom();
}

var entityMap = {
"&": "&amp;",
"<": "&lt;",
">": "&gt;",
'"': '&quot;',
"'": '&#39;',
"/": '&#x2F;'
};

function escapeHtml(string) {
	return String(string).replace(/[&<>"'\/]/g, function (s) {
		return entityMap[s];
	});
}

function scrollCommentsToBottom() {
	var comments = $(".comments");
	comments.scrollTop(comments[0].scrollHeight);
}
  
$(function(){
	scrollCommentsToBottom();
})