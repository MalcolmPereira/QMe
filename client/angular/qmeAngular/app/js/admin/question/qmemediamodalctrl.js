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

            var mediaObjType = undefined;
            var mediaObj     = undefined;
            if(qmeMediaReference.selectedMediaType && qmeMediaReference.selectedMediaType === 'IMAGE'){
                mediaObjType = {mediaTypeId:"IMAGE",mediaTypeDesc:"Image"};
                mediaObj     = qmeMediaReference.uploadedImage;
            }else  if(qmeMediaReference.selectedMediaType && qmeMediaReference.selectedMediaType === 'LINK'){
                mediaObjType = {mediaTypeId:"LINK",mediaTypeDesc:"Http Link"};
                mediaObj     = qmeMediaReference.refLink ;
            }

            if(mediaObjType && mediaObj){
                qmeModelSession.create({
                        mediaType:mediaObjType,
                        media:mediaObj
                    }
                );
            }
        };


        qmeMediaReference.cancel = function(){
            $('#addMediaModal').modal('hide');
            qmeMediaReference.mediaForm = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
            qmeMediaReference.uploadedImage = undefined;
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