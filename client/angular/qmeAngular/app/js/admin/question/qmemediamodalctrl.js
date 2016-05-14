(function () {

    'use strict';

    ngQMe
        .controller('qmeMediaModalCtrl', QMeMediaModalController);

    QMeMediaModalController.$inject = ['qmeModelSession'];

    function QMeMediaModalController(qmeModelSession) {

        var qmeMediaReference = this;

        qmeMediaReference.uploader = {};

        qmeMediaReference.mediaForm = undefined;
        qmeMediaReference.selectedMediaType = undefined;
        qmeMediaReference.refLink  = undefined;
        qmeMediaReference.uploadedImage = undefined;

        qmeMediaReference.mediaType = [
            {mediaTypeId:"LINK",mediaTypeDesc:"Http Link"},
            {mediaTypeId:"IMAGE",mediaTypeDesc:"Image"}
        ];

        qmeMediaReference.save = function(){
            $('#addMediaModal').modal('hide');
        };

        qmeMediaReference.cancel = function(){
            $('#addMediaModal').modal('hide');
        };

        qmeMediaReference.isInValidForm = function(){
        };

        qmeMediaReference.handleFilesAdded = function(file, event, flow){
            qmeMediaReference.uploadedImage = file ;
        };

        qmeMediaReference.removeUploadedFile = function(){
            qmeMediaReference.uploadedImage = undefined;
        };
    }

})();