(function () {

    'use strict';

    ngQMe

        .controller('qmeQuestionManagementCtrl', QMeQuestionManagementController);

        QMeQuestionManagementController.$inject = ['$scope','$timeout','$state','$stateParams','qmeFlashService','qmeQuestionService','qmeCategoryService','qmePageSession','qmeModelSession','QME_CONSTANTS'];

        function QMeQuestionManagementController($scope,$timeout,$state,$stateParams,qmeFlashService,qmeQuestionService,qmeCategoryService, qmePageSession,qmeModelSession,QME_CONSTANTS) {

            var qmeQuestionManagement = this;

            qmeQuestionManagement.questions = undefined;
            qmeQuestionManagement.questioncount = 0;
            qmeQuestionManagement.currentpage = 0;
            qmeQuestionManagement.sortasc = true;
            qmeQuestionManagement.sortfields = "QUESTION";

            qmeQuestionManagement.addQuestionForm = undefined;
            qmeQuestionManagement.updateQuestionForm = undefined;
            qmeQuestionManagement.questionId = undefined;

            qmeQuestionManagement.category = [];
            qmeQuestionManagement.categoryId = undefined;
            qmeQuestionManagement.categoryName = undefined;
            qmeQuestionManagement.questionPoint = undefined;
            qmeQuestionManagement.questionText = undefined;
            qmeQuestionManagement.answer = undefined;

            qmeQuestionManagement.uploaderAnswerOptionFlow = undefined;
            qmeQuestionManagement.uploaderAnswerReferenceFlow = undefined;

            qmeQuestionManagement.answerReferenceMedia = [];

            qmeQuestionManagement.answerOptions = [];

            qmeQuestionManagement.selectedQuestions = [];

            qmeQuestionManagement.selectedCategoryid = undefined;

            qmeQuestionManagement.listQuestions = function(selectedCategoryid) {
                qmeQuestionManagement.selectedCategoryid = selectedCategoryid;
                qmeQuestionManagement.selectedQuestions  = undefined;
                qmeQuestionManagement.selectedQuestions  = [];

                if ($stateParams.sortasc === undefined || $stateParams.sortasc === null) {
                    qmeQuestionManagement.sortasc = true;
                } else {
                    qmeQuestionManagement.sortasc = $stateParams.sortasc;
                }

                if ($stateParams.sortfields && $stateParams.sortfields !== null) {
                    qmeQuestionManagement.sortfields = $stateParams.sortfields;
                } else {
                    qmeQuestionManagement.sortfields = "QUESTION";
                }

                if (qmeQuestionManagement.questioncount === 0) {
                    qmeQuestionService.countQuestions()
                        .then(
                            function (res) {
                                qmeQuestionManagement.questioncount = res.data.content;
                            },
                            function (error) {
                                if (error && error.status && error.status == 403) {
                                    qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                                } else {
                                    qmeFlashService.Error("Oops.....Error from service for getting question count, please retry in some time.");
                                }
                                qmeQuestionManagement.questioncount = -1;
                            }
                        );
                }

                qmeQuestionService.listQuestionsPaged(0, qmeQuestionManagement.sortasc, qmeQuestionManagement.sortfields, qmeQuestionManagement.selectedCategoryid)
                    .then(

                        function(res){
                            qmeQuestionManagement.questions = res;
                            if($stateParams.currentpage &&  $stateParams.currentpage !== null){
                                qmeQuestionManagement.pageQuestions($stateParams.currentpage);
                                qmePageSession.setPageState($stateParams.currentpage);
                            }
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service getting question lists, please retry in some time.");
                            }
                        }
                     );
            };

            qmeQuestionManagement.setSortField = function(field){
                qmeFlashService.Clear();
                qmeQuestionManagement.sortasc = true;
                qmeQuestionManagement.sortfields = field;
                qmePageSession.create(qmeQuestionManagement.questioncount);
                qmeQuestionManagement.pageQuestions(0);
            };

            qmeQuestionManagement.isSortAsc = function(field){
                return (qmeQuestionManagement.sortasc &&  qmeQuestionManagement.sortfields === field);
            };

            qmeQuestionManagement.isSortDesc = function(field){
                return (!qmeQuestionManagement.sortasc &&  qmeQuestionManagement.sortfields === field);
            };

            qmeQuestionManagement.sortAsc = function(field){
                qmeFlashService.Clear();
                qmeQuestionManagement.sortasc = true;
                qmeQuestionManagement.sortfields = field;
                qmePageSession.create(qmeQuestionManagement.questioncount);
                qmeQuestionManagement.pageQuestions(0);
            };

            qmeQuestionManagement.sortDesc = function(field){
                qmeFlashService.Clear();
                qmeQuestionManagement.sortasc = false;
                qmeQuestionManagement.sortfields = field;
                qmePageSession.create(qmeQuestionManagement.questioncount);
                qmeQuestionManagement.pageQuestions(0);
            };

            qmeQuestionManagement.recordsLoaded = function(){
                return (qmeQuestionManagement.questioncount > 0 );
            };

            qmeQuestionManagement.totalRecords = function(){
                return qmeQuestionManagement.questioncount;
            };

            qmeQuestionManagement.pageQuestions = function(pageNumber){
                qmeQuestionManagement.currentpage = pageNumber;
                qmeQuestionService.listQuestionsPaged(pageNumber, qmeQuestionManagement.sortasc, qmeQuestionManagement.sortfields, qmeQuestionManagement.selectedCategoryid)
                    .then(
                        function(res){
                            qmeQuestionManagement.questions = res;
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service getting question lists, please retry in some time.");
                            }
                        }
                    );
            };

            qmeQuestionManagement.loadCategories = function(){

                qmeFlashService.Clear();

                qmeCategoryService.listCategory()
                    .then(
                        function(res){
                            for (var key in res) {
                                var category = res[key];
                                if (category.hasOwnProperty('categoryId')){
                                    qmeQuestionManagement.category.push(category);
                                }
                            }
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service getting category lists, please retry in some time.");
                            }
                        }
                    );
            };

            qmeQuestionManagement.addAnswerReferenceMedia = function(){
                $('#addMediaModal').modal('show');
                var promise = qmeModelSession.modalShown();
                promise.then(
                    function(data){
                       if(data && data.mediaType && data.mediaType.mediaTypeId && data.mediaType.mediaTypeId === 'IMAGE'){
                           qmeQuestionManagement.base64FileRefMedia(data);

                       }else if(data && data.mediaType && data.mediaType.mediaTypeId && data.mediaType.mediaTypeId === 'LINK'){
                           qmeQuestionManagement.answerReferenceMedia.push(
                               {
                                   mediaType : data.mediaType,
                                   media: data.media
                               }
                           );
                       }
                    },
                    function(){
                    }
                );
            };

            qmeQuestionManagement.addAnswerOptions = function() {
                $('#addOptionsModal').modal('show');
                var promise = qmeModelSession.modalShown();
                promise.then(
                    function(data){
                        if(data && data.mediaType && data.mediaType.mediaTypeId && data.mediaType.mediaTypeId === 'IMAGE'){
                            qmeQuestionManagement.base64FileOptions(data);

                        }else{
                            qmeQuestionManagement.answerOptions.push(
                                {
                                    answerOption: data.answerOption,
                                    answerCorrect: data.answerCorrect,
                                    mediaType: undefined,
                                    media: undefined
                                }
                             );
                        }
                    },
                    function(){
                    }
                );
            };
            qmeQuestionManagement.removeAnswerReferenceMedia = function(index){
                var answerReferenceMediaObj = qmeQuestionManagement.answerReferenceMedia[index];
                qmeQuestionManagement.answerReferenceMedia.splice(index,1);

                if( answerReferenceMediaObj && answerReferenceMediaObj.mediaIndex && qmeQuestionManagement.uploaderAnswerReferenceFlow && qmeQuestionManagement.uploaderAnswerReferenceFlow.files && qmeQuestionManagement.uploaderAnswerReferenceFlow.files[answerReferenceMediaObj.mediaIndex]){
                    qmeQuestionManagement.uploaderAnswerReferenceFlow.removeFile(qmeQuestionManagement.uploaderAnswerReferenceFlow.files[answerReferenceMediaObj.mediaIndex]);
                }
            };

            qmeQuestionManagement.removeAnswerOption = function(index){
                var answerOptionObj =  qmeQuestionManagement.answerOptions[index];
                qmeQuestionManagement.answerOptions.splice(index,1);

                if( answerOptionObj && answerOptionObj.mediaIndex && qmeQuestionManagement.uploaderAnswerOptionFlow && qmeQuestionManagement.uploaderAnswerOptionFlow.files && qmeQuestionManagement.uploaderAnswerOptionFlow.files[answerOptionObj.mediaIndex]){
                    qmeQuestionManagement.uploaderAnswerOptionFlow.removeFile(qmeQuestionManagement.uploaderAnswerOptionFlow.files[answerOptionObj.mediaIndex]);
                }
            };

            qmeQuestionManagement.noOptions = function(){
                if(qmeQuestionManagement.answerOptions && qmeQuestionManagement.answerOptions.length > 0){
                    return false;
                }
                return true;
            };

            qmeQuestionManagement.submitAddQuestion = function(){
                var question = {
                    "categoryId": qmeQuestionManagement.categoryId,
                    "questionText": qmeQuestionManagement.questionText ,
                    "answer": qmeQuestionManagement.answer,
                    "questionPoint": qmeQuestionManagement.questionPoint,
                    "answerOptionList": [],
                    "answerReferenceMediaList": []
                };

                if(qmeQuestionManagement.answerOptions && qmeQuestionManagement.answerOptions.length > 0){

                    qmeQuestionManagement.answerOptions.forEach(function (answerOptionElem){
                        if(answerOptionElem.mediaType && answerOptionElem.media){
                            question.answerOptionList.push(
                                {
                                    "optionText":answerOptionElem.answerOption,
                                    "correct":answerOptionElem.answerCorrect,
                                    "answerOptionMediaList":[
                                        {
                                            "mediaType":answerOptionElem.mediaTypeId,
                                            "media":answerOptionElem.media
                                        }
                                    ]
                                }
                            );
                        }else{
                            question.answerOptionList.push(
                                {
                                    "optionText":answerOptionElem.answerOption,
                                    "correct":answerOptionElem.answerCorrect,
                                    "answerOptionMediaList":[]
                                }
                            );
                        }
                     });
                }
                if(qmeQuestionManagement.answerReferenceMedia && qmeQuestionManagement.answerReferenceMedia.length > 0){
                    qmeQuestionManagement.answerReferenceMedia.forEach(function (addAnswerReferenceMediaElem){
                        if(addAnswerReferenceMediaElem.mediaType.mediaTypeId === "IMAGE"){
                            question.answerReferenceMediaList.push(
                                {
                                    "mediaType":addAnswerReferenceMediaElem.mediaTypeId,
                                    "media":addAnswerReferenceMediaElem.media
                                }
                            );
                        }else{
                            question.answerReferenceMediaList.push(
                                {
                                    "mediaType":addAnswerReferenceMediaElem.mediaType.mediaTypeMime,
                                    "media":btoa(addAnswerReferenceMediaElem.media)
                                }
                            );
                        }

                    });
                }
                qmeQuestionService
                    .createQuestion(question)
                    .then(
                        function(res){
                            qmeFlashService.Success("Question submitted successfully, .",true);
                            $state.go('listquestions', {});
                        },
                        function(error){
                            if(error && error.status && error.status == 400){
                                qmeFlashService.Error("Oops.....Invalid request for submit question, please make sure all required fields are valid.");

                            }else if(error && error.status && error.status == 403){
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else if(error && error.status && error.status == 409){
                                qmeFlashService.Error("Oops.....Invalid request, question already exists or duplicated.");

                            }else{
                                qmeFlashService.Error("Oops.....Error adding new question, please retry in some time.");
                            }
                        }
                    );
            };

            qmeQuestionManagement.updateQuestion = function(question){
                qmeQuestionManagement.questionId = undefined;
                qmeQuestionManagement.categoryId = undefined;
                qmeQuestionManagement.categoryName = undefined;
                qmeQuestionManagement.questionPoint = undefined;
                qmeQuestionManagement.questionText = undefined;
                qmeQuestionManagement.answer = undefined;
                qmeQuestionManagement.answerOptions = [];
                qmeQuestionManagement.answerReferenceMedia = [];
                qmeQuestionService.getQuestionById(question.questionId)
                    .then(
                        function(res){
                            $state.go('updatequestion',{
                                    currentQuestion:res,
                                    currentpage:qmeQuestionManagement.currentpage,
                                    sortasc: qmeQuestionManagement.sortasc,
                                    sortfields:qmeQuestionManagement.sortfields
                                }
                            );
                        },
                        function(error){
                            if(error && error.status && error.status == 404){
                                qmeFlashService.Error("Oops.....Invalid question resource, question not found");

                            }else{
                                qmeFlashService.Error("Oops.....Error getring question detail, please retry in some time.");
                            }
                        }
                    );
            };

            qmeQuestionManagement.selectedQuestion = function(){
                qmeQuestionManagement.questionId = $stateParams.currentQuestion.questionId;
                qmeQuestionManagement.categoryId = $stateParams.currentQuestion.categoryId;
                qmeQuestionManagement.categoryName = $stateParams.currentQuestion.categoryName;
                qmeQuestionManagement.questionPoint = $stateParams.currentQuestion.questionPoint;
                qmeQuestionManagement.questionText = $stateParams.currentQuestion.questionText;
                qmeQuestionManagement.answer = $stateParams.currentQuestion.answer;
            };

            qmeQuestionManagement.loadAnswerOptions = function(){
                if($stateParams && $stateParams.currentQuestion && $stateParams.currentQuestion.answerOptionList && $stateParams.currentQuestion.answerOptionList.length > 0){

                    qmeQuestionManagement.answerOptions = [];
                    var blobArr = [];
                    var fileCounter = 1;
                    var fileIndexCounter = 0;

                    $stateParams.currentQuestion.answerOptionList.forEach(function (answerOptionElem){
                        var answerOptionObj = {
                            "answerOption":answerOptionElem.optionText,
                            "answerCorrect":answerOptionElem.correct,
                            "answerOptionID": answerOptionElem.answerOptionID,
                            "questionID": answerOptionElem.questionID,
                            "answerOptionMediaID": undefined,
                            "mediaType" : {
                                "mediaTypeId":''
                            },
                            "media":''
                        };
                        answerOptionElem.answerOptionMediaList.forEach(function (answerOptionMediaElem){
                            if(answerOptionMediaElem.mediaType === 'image/png' || answerOptionMediaElem.mediaType === 'image/gif' || answerOptionMediaElem.mediaType === 'image/jpeg' || answerOptionMediaElem.mediaType === 'image/jpg'){

                                answerOptionObj.mediaType.mediaTypeId = "IMAGE";
                                answerOptionObj.mediaType.mediaTypeDesc = "Image";
                                answerOptionObj.media = answerOptionMediaElem.media;
                                answerOptionObj.answerOptionMediaID = answerOptionMediaElem.answerOptionMediaID;
                                answerOptionObj.mediaIndex = fileIndexCounter;

                                var raw  = atob(answerOptionMediaElem.media);
                                var byteNumbers = new Array(raw.length);
                                for (var i = 0; i < raw.length; i++) {
                                    byteNumbers[i] = raw.charCodeAt(i);
                                }
                                var byteArray = new Uint8Array(byteNumbers);

                                var blob = new Blob([byteArray], {type: answerOptionMediaElem.mediaType});
                                blob.name = 'file_'+fileCounter +'.png';
                                blobArr.push(blob);
                                fileCounter += 1;
                                fileIndexCounter += 1;

                            }else if(answerOptionMediaElem.mediaType === 'text/plain'){
                                answerOptionObj.mediaType.mediaTypeId = "LINK";
                                answerOptionObj.mediaType.mediaTypeDesc = "Http Link";
                                answerOptionObj.media = atob(answerOptionMediaElem.media);
                                answerOptionObj.answerOptionMediaID = answerOptionMediaElem.answerOptionMediaID;

                            }else{
                                answerOptionObj.mediaType.mediaTypeId = "NONE";
                                answerOptionObj.mediaType.mediaTypeDesc = "";
                                answerOptionObj.media = "";
                                answerOptionObj.answerOptionMediaID = answerOptionMediaElem.answerOptionMediaID;
                            }
                        });
                        qmeQuestionManagement.answerOptions.push(answerOptionObj);
                    });

                    $timeout(function(){
                        blobArr.forEach(function (blobFileObj){
                            qmeQuestionManagement.uploaderAnswerOptionFlow.addFile(blobFileObj);
                        });
                    },100);
                }
            };

            qmeQuestionManagement.loadAnswerReferenceMedia = function(){
                if($stateParams && $stateParams.currentQuestion && $stateParams.currentQuestion.answerReferenceMediaList && $stateParams.currentQuestion.answerReferenceMediaList.length > 0){
                    qmeQuestionManagement.answerReferenceMedia = [];
                    var blobArr = [];
                    var fileCounter = 1;
                    var fileIndexCounter = 0;
                    $stateParams.currentQuestion.answerReferenceMediaList .forEach(function (answerMediaElem){
                        var answerReferenceObj = {
                            "answerRefMediaID": answerMediaElem.answerRefMediaID,
                            "questionID": answerMediaElem.questionID,
                            "mediaType" : {
                                "mediaTypeId":'',
                                "mediaTypeDesc":''
                            },
                            "media":''
                        };
                        if(answerMediaElem.mediaType === 'image/png' || answerMediaElem.mediaType === 'image/gif' || answerMediaElem.mediaType === 'image/jpeg' || answerMediaElem.mediaType === 'image/jpg'){

                            answerReferenceObj.mediaType.mediaTypeId = "IMAGE";
                            answerReferenceObj.mediaType.mediaTypeDesc = "Image";
                            answerReferenceObj.media = answerMediaElem.media;
                            answerReferenceObj.mediaIndex = fileIndexCounter;

                            var raw  = atob(answerMediaElem.media);
                            var byteNumbers = new Array(raw.length);
                            for (var i = 0; i < raw.length; i++) {
                                byteNumbers[i] = raw.charCodeAt(i);
                            }
                            var byteArray = new Uint8Array(byteNumbers);

                            var blob = new Blob([byteArray], {type: answerMediaElem.mediaType});
                            blob.name = 'file_'+fileCounter +'.png';
                            blobArr.push(blob);
                            fileCounter += 1;
                            fileIndexCounter += 1;

                        }else if(answerMediaElem.mediaType === 'text/plain'){
                            answerReferenceObj.mediaType.mediaTypeId = "LINK";
                            answerReferenceObj.mediaType.mediaTypeDesc = "Http Link";
                            answerReferenceObj.media = atob(answerMediaElem.media);
                        }else{
                            answerReferenceObj.mediaType.mediaTypeId = "NONE";
                            answerReferenceObj.mediaType.mediaTypeDesc = "";
                            answerReferenceObj.media = "";
                        }
                        qmeQuestionManagement.answerReferenceMedia.push(answerReferenceObj);
                    });
                    $timeout(function(){
                        blobArr.forEach(function (blobFileObj){
                            qmeQuestionManagement.uploaderAnswerReferenceFlow.addFile(blobFileObj);
                        });
                    },100);
                }
            };

            qmeQuestionManagement.cancelUpdateQuestion = function(){
                $state.go('listquestions', {
                        currentpage:qmeQuestionManagement.currentpage,
                        sortasc:qmeQuestionManagement.sortasc,
                        sortfields:qmeQuestionManagement.sortfields
                    }
                );

            };

            qmeQuestionManagement.cancelAddQuestion = function(){
                $state.go('listquestions', {});
            };

            qmeQuestionManagement.submitUpdateQuestion = function () {
                var question = {
                    "questionId": qmeQuestionManagement.questionId,
                    "categoryId": qmeQuestionManagement.categoryId,
                    "questionText": qmeQuestionManagement.questionText ,
                    "answer": qmeQuestionManagement.answer,
                    "questionPoint": qmeQuestionManagement.questionPoint,
                    "answerOptionList": [],
                    "answerReferenceMediaList": []
                };
                if(qmeQuestionManagement.answerOptions && qmeQuestionManagement.answerOptions.length > 0){

                    qmeQuestionManagement.answerOptions.forEach(function (answerOptionElem){
                        if(answerOptionElem.mediaType && answerOptionElem.media){
                            var mediaTypeVal = "text/plain";
                            if(answerOptionElem.mediaType.mediaTypeId === "IMAGE"){
                                mediaTypeVal = "image/png";
                                question.answerOptionList.push(
                                    {
                                        "answerOptionID": answerOptionElem.answerOptionID,
                                        "questionID": answerOptionElem.questionID,
                                        "optionText":answerOptionElem.answerOption,
                                        "correct":answerOptionElem.answerCorrect,
                                        "answerOptionMediaList":[
                                            {
                                                "mediaType":mediaTypeVal,
                                                "media":answerOptionElem.media,
                                                "answerOptionMediaID": answerOptionElem.answerOptionMediaID,
                                                "answerOptionID": answerOptionElem.answerOptionID,
                                            }
                                        ]
                                    }
                                );
                            }
                            if(answerOptionElem.mediaType.mediaTypeId === "LINK"){
                                mediaTypeVal = "text/plain";
                                question.answerOptionList.push(
                                    {
                                        "answerOptionID": answerOptionElem.answerOptionID,
                                        "questionID": answerOptionElem.questionID,
                                        "optionText":answerOptionElem.answerOption,
                                        "correct":answerOptionElem.answerCorrect,
                                        "answerOptionMediaList":[
                                            {
                                                "mediaType":mediaTypeVal,
                                                "media":btoa(answerOptionElem.media),
                                                "answerOptionMediaID": answerOptionElem.answerOptionMediaID,
                                                "answerOptionID": answerOptionElem.answerOptionID,
                                            }
                                        ]
                                    }
                                );
                            }


                        }else{
                            question.answerOptionList.push(
                                {
                                    "answerOptionID": answerOptionElem.answerOptionID,
                                    "questionID": answerOptionElem.questionID,
                                    "optionText":answerOptionElem.answerOption,
                                    "correct":answerOptionElem.answerCorrect,
                                    "answerOptionMediaList":[]
                                }
                            );
                        }
                    });
                }
                if(qmeQuestionManagement.answerReferenceMedia && qmeQuestionManagement.answerReferenceMedia.length > 0){

                    qmeQuestionManagement.answerReferenceMedia.forEach(function (addAnswerReferenceMediaElem){

                        var mediaTypeVal = "text/plain";
                        if(addAnswerReferenceMediaElem.mediaType.mediaTypeId === "IMAGE"){
                            mediaTypeVal = "image/png";
                            question.answerReferenceMediaList.push(
                                {
                                    "answerRefMediaID": addAnswerReferenceMediaElem.answerRefMediaID,
                                    "questionID": addAnswerReferenceMediaElem.questionID,
                                    "mediaType":mediaTypeVal,
                                    "media":addAnswerReferenceMediaElem.media
                                }
                            );
                        }
                        if(addAnswerReferenceMediaElem.mediaType.mediaTypeId  === "LINK"){
                            mediaTypeVal = "text/plain";
                            question.answerReferenceMediaList.push(
                                {
                                    "answerRefMediaID": addAnswerReferenceMediaElem.answerRefMediaID,
                                    "questionID": addAnswerReferenceMediaElem.questionID,
                                    "mediaType":mediaTypeVal,
                                    "media":btoa(addAnswerReferenceMediaElem.media)
                                }
                            );
                        }
                    });
                }
                qmeQuestionService
                    .updateQuestion(question)
                    .then(
                        function(res){
                            qmeFlashService.Success("Question submitted successfully, .",true);
                            $state.go('listquestions', {});
                        },
                        function(error){
                            if(error && error.status && error.status == 400){
                                qmeFlashService.Error("Oops.....Invalid request for submit question, please make sure all required fields are valid.");

                            }else if(error && error.status && error.status == 403){
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else if(error && error.status && error.status == 409){
                                qmeFlashService.Error("Oops.....Invalid request, question already exists or duplicated.");

                            }else{
                                qmeFlashService.Error("Oops.....Error adding new question, please retry in some time.");
                            }
                        }
                    );
            };

            qmeQuestionManagement.deleteQuestion = function(){
                qmeQuestionService.deleteQuestion(qmeQuestionManagement.questionId)
                    .then(
                        function(res){
                            qmeFlashService.Success("Question Delete successful.",true);
                            $state.go('listquestions', {});
                        },
                        function(error){
                            if(error && error.status && error.status == 404){
                                qmeFlashService.Error("Oops.....Invalid request for question delete, question not found.");

                            }else if(error && error.status && error.status == 403){
                                qmeFlashService.Error("Oops.....User not authorized to delete question .");

                            }else if(error && error.status && error.status == 400){
                                qmeFlashService.Error("Oops.....Invalid request for delete question .");

                            }else{
                                qmeFlashService.Error("Oops.....Server Error deleting question , please contact administrator.");
                            }
                        }
                    );
            };


            qmeQuestionManagement.base64FileOptions = function getBase64Options(data){
                if(data && data.media && data.media.flowObj && data.media.flowObj.files && data.media.flowObj.files.length > 0 && data.media.flowObj.files[0]){
                    if(data.media.flowObj.files[0].file instanceof Blob || data.media.flowObj.files[0].file instanceof File){
                        var reader = new FileReader();
                        reader.onload = function (event) {
                            qmeQuestionManagement.answerOptions.push(
                                {
                                    answerOption: data.answerOption,
                                    answerCorrect: data.answerCorrect,
                                    mediaType: data.mediaType,
                                    mediaTypeId: data.media.flowObj.files[0].file.type,
                                    mediaIndex: qmeQuestionManagement.uploaderAnswerOptionFlow.files.length,
                                    media:event.target.result.substr(event.target.result.indexOf('base64')+7)
                                }
                            );
                            if(qmeQuestionManagement.uploaderAnswerOptionFlow && data.media.flowObj){
                                qmeQuestionManagement.uploaderAnswerOptionFlow.files.push(data.media.flowObj.files[0]);
                            }
                            $scope.$apply();
                        };
                        reader.onerror = function (error) {
                            qmeFlashService.Error("Oops.....Error reading file , please validate file upload.");
                        };
                        reader.readAsDataURL(data.media.flowObj.files[0].file);
                    }else{
                        qmeFlashService.Error("Oops.....Error reading file , please validate file upload.");
                    }
                }
            };
            qmeQuestionManagement.base64FileRefMedia = function getBase64RefMedia(data){

                if(data && data.media && data.media.flowObj && data.media.flowObj.files && data.media.flowObj.files.length > 0 && data.media.flowObj.files[0]){

                    if(data.media.flowObj.files[0].file instanceof Blob || data.media.flowObj.files[0].file instanceof File){
                        var reader = new FileReader();

                        reader.onload = function (event) {


                            qmeQuestionManagement.answerReferenceMedia.push(
                                {
                                    mediaType: data.mediaType,
                                    mediaTypeId: data.media.flowObj.files[0].file.type,
                                    mediaIndex: qmeQuestionManagement.uploaderAnswerReferenceFlow.files.length,
                                    media: event.target.result.substr(event.target.result.indexOf('base64')+7)
                                }
                            );

                            if(qmeQuestionManagement.uploaderAnswerReferenceFlow && data.media.flowObj){
                                qmeQuestionManagement.uploaderAnswerReferenceFlow.files.push(data.media.flowObj.files[0]);
                            }

                            $scope.$apply();
                        };

                        reader.onerror = function (error) {
                            qmeFlashService.Error("Oops.....Error reading file , please validate file upload.");
                        };

                        reader.readAsDataURL(data.media.flowObj.files[0].file);

                    }else{
                        qmeFlashService.Error("Oops.....Error reading file , please validate file upload.");
                    }
                }
            };

            qmeQuestionManagement.checkQuestion = function(event,question){
                if(event && event.target && event.target.checked){
                    for(var i in qmeQuestionManagement.selectedQuestions){
                        if(qmeQuestionManagement.selectedQuestions[i].questionId == question.questionId){
                            return;
                        }
                    }
                    qmeQuestionManagement.selectedQuestions.push(question);

                }else if(event && event.target && !event.target.checked){
                    for(var j in qmeQuestionManagement.selectedQuestions){
                        if(qmeQuestionManagement.selectedQuestions[j].questionId == question.questionId){
                            qmeQuestionManagement.selectedQuestions.splice(j , 1);
                            return;
                        }
                    }
                }
            };

            qmeQuestionManagement.setSelectedQuestions = function(){
                qmeModelSession.create(qmeQuestionManagement.selectedQuestions);
            };

        }
})();