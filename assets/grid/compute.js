function initiate() {
    var output = '';
    var match = input[0];
    words.every(function(word) {
        gridInterface.log("Hashing: " + word);
        if(md5(word) == match) {
            alert(word);
            output = word;
            return false;
        }
        return true;
    });

    gridInterface.computedData(JSON.stringify({output : output}));
}