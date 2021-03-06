(function () {

    'use strict';

    ngQMe
        .controller('qmeMediaModalCtrl', QMeMediaModalController);

    QMeMediaModalController.$inject = ['$scope','qmeModelSession'];

    function QMeMediaModalController($scope,qmeModelSession) {
        var qmeMediaReference = this;


        qmeMediaReference.uploaderAnswerOptionModalFlow = undefined;
        qmeMediaReference.uploaderAnswerReferenceModalFlow = undefined;


        qmeMediaReference.mediaForm = undefined;

        qmeMediaReference.optionForm  = undefined;
        qmeMediaReference.optionText = undefined;
        qmeMediaReference.optionCorrect = undefined;

        qmeMediaReference.selectedMediaType = undefined;
        qmeMediaReference.refLink  = undefined;
        qmeMediaReference.uploadedImage = undefined;
        qmeMediaReference.uploadError = undefined;


        qmeMediaReference.mediaType = [
            {mediaTypeId:"LINK",mediaTypeDesc:"Http Link", mediaTypeMime:"text/plain"},
            {mediaTypeId:"IMAGE",mediaTypeDesc:"Image", mediaTypeMime:"image/png"}
        ];

        qmeMediaReference.optionMediaType = [
            {mediaTypeId:"NONE",mediaTypeDesc:"", mediaTypeMime:"text/plain"},
            {mediaTypeId:"IMAGE",mediaTypeDesc:"Image", mediaTypeMime:"image/png"}
        ];

        qmeMediaReference.cancel = function(){
            $('#addMediaModal').modal('hide');
            qmeMediaReference.reset();
        };

        qmeMediaReference.cancelOptions = function(){
            $('#addOptionsModal').modal('hide');
            qmeMediaReference.reset();
        };

        qmeMediaReference.saveOptions = function(){
            var mediaObjType = undefined;
            var mediaObj     = undefined;

            if(qmeMediaReference.uploadedImage && qmeMediaReference.selectedMediaType && qmeMediaReference.selectedMediaType === 'IMAGE'){
                mediaObjType = {mediaTypeId:"IMAGE",mediaTypeDesc:"Image", mediaTypeMime:"image/png"};
                mediaObj     = qmeMediaReference.uploadedImage;
            }

            if(mediaObjType && mediaObj){
                qmeModelSession.create({
                        answerOption: qmeMediaReference.optionText,
                        answerCorrect: qmeMediaReference.optionCorrect,
                        mediaType: mediaObjType,
                        media: mediaObj
                    }
                );
            }else{
                qmeModelSession.create({
                        answerOption: qmeMediaReference.optionText,
                        answerCorrect: qmeMediaReference.optionCorrect,
                        mediaType: null,
                        media: null
                    }
                );
            }
            $('#addOptionsModal').modal('hide');
            qmeMediaReference.optionForm = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
            qmeMediaReference.optionText = undefined;
            qmeMediaReference.optionCorrect = undefined;
            qmeMediaReference.mediaForm = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
        };

        qmeMediaReference.save = function(){
            var mediaObjType = undefined;
            var mediaObj     = undefined;
            if(qmeMediaReference.uploadedImage && qmeMediaReference.selectedMediaType && qmeMediaReference.selectedMediaType === 'IMAGE'){
                mediaObjType = {mediaTypeId:"IMAGE",mediaTypeDesc:"Image", mediaTypeMime:"image/png"};
                mediaObj     = qmeMediaReference.uploadedImage;
            }else if(qmeMediaReference.selectedMediaType && qmeMediaReference.selectedMediaType === 'LINK'){
                mediaObjType = {mediaTypeId:"LINK",mediaTypeDesc:"HTTP/HTTPS Link", mediaTypeMime:"text/plain"};
                mediaObj     = qmeMediaReference.refLink ;
            }
            if(mediaObjType && mediaObj){
                qmeModelSession.create({
                        mediaType:mediaObjType,
                        media:mediaObj
                    }
                );
            }
            $('#addMediaModal').modal('hide');
            qmeMediaReference.mediaForm = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
            qmeMediaReference.optionForm = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
            qmeMediaReference.optionText = undefined;
            qmeMediaReference.optionCorrect = undefined;
        };

        qmeMediaReference.isInValidForm = function(){
            if(qmeMediaReference.uploadError){
                return true;
            }
            if(!qmeMediaReference.selectedMediaType){
                return true;
            }
            if( !qmeMediaReference.refLink && !qmeMediaReference.uploadedImage ){
                return true;
            }
            return false;
        };

        qmeMediaReference.isInValidOptionsForm = function(){
            if(qmeMediaReference.uploadError){
                return true;
            }
            if(qmeMediaReference.selectedMediaType == 'IMAGE' && !qmeMediaReference.uploadedImage){
                return true;
            }
            if(qmeMediaReference.optionCorrect === undefined){
                return true;
            }
            if(qmeMediaReference.optionText === undefined || qmeMediaReference.optionText.trim().length === 0){
                return true;
            }
            return false;
        };

        qmeMediaReference.handleFilesAdded = function(file, flow){
            const KB_SIZE  = 1000;
            const MAX_KB   = 40;
            const MAX_FILE = MAX_KB * KB_SIZE ;
            if (file.size > MAX_FILE ) {
                if(flow){
                    flow.cancel();
                }
                qmeMediaReference.uploadError = "File size "+(file.size/KB_SIZE)+" KB not acceptable, max file size supported  "+(MAX_FILE/KB_SIZE)+" KB";
                qmeMediaReference.uploadedImage = undefined;
            }else{
                qmeMediaReference.uploadError   = undefined;
                qmeMediaReference.uploadedImage = file;
            }
        };


        qmeMediaReference.reset = function(){
            qmeMediaReference.mediaForm = undefined;
            qmeMediaReference.optionForm  = undefined;
            qmeMediaReference.optionText = undefined;
            qmeMediaReference.optionCorrect = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
            qmeMediaReference.uploadedImage = undefined;
            qmeMediaReference.uploadError = undefined;
            qmeMediaReference.removeUploadedFile();
        };

        qmeMediaReference.removeUploadedFile = function(){
            qmeMediaReference.uploadedImage = undefined;
            qmeMediaReference.uploadError = undefined;
            if( qmeMediaReference.uploaderAnswerOptionModalFlow && qmeMediaReference.uploaderAnswerOptionModalFlow.files){
                qmeMediaReference.uploaderAnswerOptionModalFlow.cancel();
            }
            if( qmeMediaReference.uploaderAnswerReferenceModalFlow && qmeMediaReference.uploaderAnswerReferenceModalFlow.files){
                qmeMediaReference.uploaderAnswerReferenceModalFlow.cancel();
            }
        };

    }

})();