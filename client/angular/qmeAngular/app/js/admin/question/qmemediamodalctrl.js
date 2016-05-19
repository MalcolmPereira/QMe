(function () {

    'use strict';

    ngQMe
        .controller('qmeMediaModalCtrl', QMeMediaModalController);

    QMeMediaModalController.$inject = ['$scope','qmeModelSession'];

    function QMeMediaModalController($scope,qmeModelSession) {

        var qmeMediaReference = this;

        qmeMediaReference.mediaForm = undefined;
        qmeMediaReference.selectedMediaType = undefined;
        qmeMediaReference.refLink  = undefined;
        qmeMediaReference.uploadedImage = undefined;
        qmeMediaReference.uploadError = undefined;


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
            qmeMediaReference.mediaForm = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
        };

        qmeMediaReference.cancel = function(){
            $('#addMediaModal').modal('hide');
            qmeMediaReference.reset();
        };

        qmeMediaReference.isInValidForm = function(){
            if(qmeMediaReference.uploadError){
                return true;
            }
            if(!qmeMediaReference.selectedMediaType){
                return true;
            }
            if( !qmeMediaReference.refLink && ! qmeMediaReference.uploadedImage ){
                return true;
            }
            return false;
        };

        qmeMediaReference.handleFilesAdded = function(file, event, flow){
            if (file.size > 7777) {
                if(flow.files[0]){
                    flow.files[0].cancel();
                }
                qmeMediaReference.uploadError = "File size not acceptable";
                qmeMediaReference.uploadedImage = undefined;
            }else{
                qmeMediaReference.uploadError = undefined;
            }
         };

        qmeMediaReference.reset = function(){
            qmeMediaReference.mediaForm = undefined;
            qmeMediaReference.selectedMediaType = undefined;
            qmeMediaReference.refLink  = undefined;
            qmeMediaReference.uploadError = undefined;
            qmeMediaReference.removeUploadedFile();
        };

        qmeMediaReference.removeUploadedFile = function(){
            qmeMediaReference.uploadedImage = undefined;
            qmeMediaReference.uploadError = undefined;
            if( $scope.uploader.flow && $scope.uploader.flow.files && $scope.uploader.flow.files[0]){
                $scope.uploader.flow.files[0].cancel();
            }
        };

    }

})();