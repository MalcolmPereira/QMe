(function () {

    'use strict';

    ngQMe

        .controller('qmeQuestionManagementCtrl', QMeQuestionManagementController);

        QMeQuestionManagementController.$inject = ['$scope','$state','$stateParams','qmeFlashService','qmeQuestionService','qmeCategoryService','qmePageSession','qmeModelSession','QME_CONSTANTS'];

        function QMeQuestionManagementController($scope,$state,$stateParams,qmeFlashService,qmeQuestionService,qmeCategoryService, qmePageSession,qmeModelSession,QME_CONSTANTS) {

            var qmeQuestionManagement = this;

            qmeQuestionManagement.questions = undefined;
            qmeQuestionManagement.questioncount = 0;
            qmeQuestionManagement.currentpage = 0;
            qmeQuestionManagement.sortasc = true;
            qmeQuestionManagement.sortfields = "QUESTION";

            qmeQuestionManagement.addQuestionForm = undefined;

            qmeQuestionManagement.category = [];
            qmeQuestionManagement.categoryId = undefined;

            qmeQuestionManagement.answerReferenceMedia = [];

            qmeQuestionManagement.answerOptions = [];

            qmeQuestionManagement.listQuestions = function() {
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

                qmeQuestionService.listQuestionsPaged(0, qmeQuestionManagement.sortasc, qmeQuestionManagement.sortfields)
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
                qmeQuestionManagement.sortasc = true;
                qmeQuestionManagement.sortfields = field;
                qmePageSession.create(qmeQuestionManagement.questioncount);
                qmeQuestionManagement.pageUsers(0);
            };

            qmeQuestionManagement.isSortAsc = function(field){
                return (qmeQuestionManagement.sortasc &&  qmeQuestionManagement.sortfields === field);
            };

            qmeQuestionManagement.isSortDesc = function(field){
                return (!qmeQuestionManagement.sortasc &&  qmeQuestionManagement.sortfields === field);
            };

            qmeQuestionManagement.sortAsc = function(field){
                qmeQuestionManagement.sortasc = true;
                qmeQuestionManagement.sortfields = field;
                qmePageSession.create(qmeQuestionManagement.questioncount);
                qmeQuestionManagement.pageQuestions(0);
            };

            qmeQuestionManagement.sortDesc = function(field){
                qmeQuestionManagement.sortasc = false;
                qmeQuestionManagement.sortfields = field;
                qmePageSession.create(qmeQuestionManagement.questioncount);
                qmeQuestionManagement.pageQuestions(0);
            };

            qmeQuestionManagement.recordsLoaded = function(){
                return (qmeQuestionManagement.usercount > 0 );
            };

            qmeQuestionManagement.totalRecords = function(){
                return qmeQuestionManagement.questioncount;
            };

            qmeQuestionManagement.pageQuestions = function(pageNumber){
                qmeQuestionManagement.currentpage = pageNumber;
                qmeQuestionService.listQuestionsPaged(pageNumber, qmeQuestionManagement.sortasc, qmeQuestionManagement.sortfields)
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
                if( $scope.uploader.flow && $scope.uploader.flow.files){
                    $scope.uploader.flow.cancel();
                }
                $('#addMediaModal').modal('show');
                var promise = qmeModelSession.modalShown();
                promise.then(
                    function(data){
                       if(data && data.mediaType && data.mediaType.mediaTypeId && data.mediaType.mediaTypeId === 'IMAGE'){
                           qmeQuestionManagement.answerReferenceMedia.push(
                               {
                                   mediaType: data.mediaType,
                                   media: data.media.flowObj.files[0].name
                               }
                           );
                           $scope.uploader.main.answerref.flow.files.push(data.media.flowObj.files[0]);
                       }else if(data && data.mediaType && data.mediaType.mediaTypeId && data.mediaType.mediaTypeId === 'LINK'){
                           qmeQuestionManagement.answerReferenceMedia.push(
                               {
                                   mediaType: data.mediaType,
                                   media: data.media,
                                   mediaObj: {}
                               }
                           );
                       }
                    },
                    function(){
                    }
                );
            };

            qmeQuestionManagement.addAnswerOptions = function() {
                if ($scope.uploader.flow && $scope.uploader.flow.files) {
                    $scope.uploader.flow.cancel();
                }
                $('#addOptionsModal').modal('show');
                var promise = qmeModelSession.modalShown();
                promise.then(
                    function(data){
                        if(data && data.mediaType && data.mediaType.mediaTypeId && data.mediaType.mediaTypeId === 'IMAGE'){
                            qmeQuestionManagement.answerOptions.push(
                                {
                                    answerOption: data.answerOption,
                                    answerCorrect: data.answerCorrect,
                                    mediaType: data.mediaType,
                                    media: data.media.flowObj.files[0].name
                                }
                            );
                        }else if(data && data.mediaType && data.mediaType.mediaTypeId && data.mediaType.mediaTypeId === 'LINK'){
                            qmeQuestionManagement.answerOptions.push(
                                {
                                    answerOption: data.answerOption,
                                    answerCorrect: data.answerCorrect,
                                    mediaType: data.mediaType,
                                    media: data.media
                                }
                            );
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
                qmeQuestionManagement.answerReferenceMedia.splice(index,1);
                if( $scope.uploader.main.answerref.flow && $scope.uploader.main.answerref.flow.files && $scope.uploader.main.answerref.flow.files[index]){
                    $scope.uploader.main.answerref.flow.removeFile($scope.uploader.main.answerref.flow.files[index]);
                }
            };

            qmeQuestionManagement.removeAnswerOption = function(index){
                qmeQuestionManagement.answerOptions.splice(index,1);
            };

        }


})();