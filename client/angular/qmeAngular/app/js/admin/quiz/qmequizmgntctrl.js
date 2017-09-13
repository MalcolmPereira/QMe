(function () {

    'use strict';

    ngQMe

        .controller('qmeQuizManagementCtrl', QMeQuizManagementController);


        QMeQuizManagementController.$inject = ['$scope','$timeout','$state','$stateParams','qmeFlashService','qmeQuizService','qmeUserService','qmeCategoryService','qmePageSession','qmeModelSession','QME_CONSTANTS'];


        function QMeQuizManagementController($scope,$timeout,$state,$stateParams,qmeFlashService,qmeQuizService,qmeUserService,qmeCategoryService,qmePageSession,qmeModelSession,QME_CONSTANTS) {

            var qmeQuizManagement = this;

            qmeQuizManagement.quiz = undefined;
            qmeQuizManagement.quizcount = 0;
            qmeQuizManagement.currentpage = 0;
            qmeQuizManagement.sortasc = true;
            qmeQuizManagement.sortfields = "QUIZ";

            qmeQuizManagement.addQuizForm = undefined;
            qmeQuizManagement.updateQuizForm = undefined;
            qmeQuizManagement.quizId = undefined;

            qmeQuizManagement.category = [];
            qmeQuizManagement.quizID = undefined;
            qmeQuizManagement.categoryId = undefined;
            qmeQuizManagement.categoryName = undefined;
            qmeQuizManagement.quizName = undefined;
            qmeQuizManagement.quizDesc = undefined;
            qmeQuizManagement.quizQuestions = [];

            qmeQuizManagement.shownSelectQuestions = false;

            qmeQuizManagement.listQuiz = function() {
                if ($stateParams.sortasc === undefined || $stateParams.sortasc === null) {
                    qmeQuizManagement.sortasc = true;
                } else {
                    qmeQuizManagement.sortasc = $stateParams.sortasc;
                }
                if ($stateParams.sortfields && $stateParams.sortfields !== null) {
                    qmeQuizManagement.sortfields = $stateParams.sortfields;
                } else {
                    qmeQuizManagement.sortfields = "QUIZ";
                }

                if (qmeQuizManagement.quizcount === 0) {

                    qmeQuizService.countQuiz()
                        .then(
                            function (res) {
                                qmeQuizManagement.quizcount = res.data.content;
                            },
                            function (error) {
                                if (error && error.status && error.status == 403) {
                                    qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                                } else {
                                    qmeFlashService.Error("Oops.....Error from service for getting quiz count, please retry in some time.");
                                }
                                qmeQuizManagement.quizcount = -1;
                            }
                        );
                }

                qmeQuizService.listQuizPaged(0, qmeQuizManagement.sortasc, qmeQuizManagement.sortfields)
                    .then(
                        function(res){
                            qmeQuizManagement.quiz = res;
                            if($stateParams.currentpage &&  $stateParams.currentpage !== null){
                                qmeQuizManagement.pageQuiz($stateParams.currentpage);
                                qmeQuizManagement.setPageState($stateParams.currentpage);
                            }
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service getting quiz lists, please retry in some time.");
                            }
                        }
                    );

            };

            qmeQuizManagement.isSortAsc = function(field){
                return (qmeQuizManagement.sortasc &&  qmeQuizManagement.sortfields === field);
            };

            qmeQuizManagement.isSortDesc = function(field){
                return (!qmeQuizManagement.sortasc &&  qmeQuizManagement.sortfields === field);
            };

            qmeQuizManagement.sortAsc = function(field){
                qmeFlashService.Clear();
                qmeQuizManagement.sortasc = true;
                qmeQuizManagement.sortfields = field;
                qmePageSession.create(qmeQuizManagement.quizcount);
                qmeQuizManagement.pageQuiz(0);
            };

            qmeQuizManagement.sortDesc = function(field){
                qmeFlashService.Clear();
                qmeQuizManagement.sortasc = false;
                qmeQuizManagement.sortfields = field;
                qmePageSession.create(qmeQuizManagement.quizcount);
                qmeQuizManagement.pageQuiz(0);
            };

            qmeQuizManagement.setSortField = function(field){
                qmeFlashService.Clear();
                qmeQuizManagement.sortasc = true;
                qmeQuizManagement.sortfields = field;
                qmePageSession.create(qmeQuizManagement.quizcount);
                qmeQuizManagement.pageQuiz(0);
            };

            qmeQuizManagement.recordsLoaded = function(){
                return (qmeQuizManagement.quizcount > 0 );
            };

            qmeQuizManagement.totalRecords = function(){
                return qmeQuizManagement.quizcount;
            };

            qmeQuizManagement.pageQuiz = function(pageNumber){
                qmeQuizManagement.currentpage = pageNumber;
                qmeQuizService.listQuizPaged(pageNumber, qmeQuizManagement.sortasc, qmeQuizManagement.sortfields)
                    .then(
                        function(res){
                            qmeQuizManagement.quiz = res;
                        },
                        function(error){
                            if(error && error.status && error.status == 403) {
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else {
                                qmeFlashService.Error("Oops.....Error from service getting quiz lists, please retry in some time.");
                            }
                        }
                    );
            };

            qmeQuizManagement.loadCategories = function(){

                qmeFlashService.Clear();

                qmeCategoryService.listCategoryWithQuestions()
                    .then(
                        function(res){
                            for (var key in res) {
                                var category = res[key];
                                if (category.hasOwnProperty('categoryId')){
                                    qmeQuizManagement.category.push(category);
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

            qmeQuizManagement.clearQuestion = function(newValue, oldValue){
                qmeQuizManagement.shownSelectQuestions = false;
                qmeModelSession.destroy();

                if(qmeQuizManagement.quizQuestions && qmeQuizManagement.quizQuestions.length > 0 && newValue && oldValue && newValue !== oldValue){
                    $('#categoryChangeConfirm').modal('show');
                    var promise = qmeModelSession.modalShown();
                    promise.then(
                        function(data){
                            if(data){
                                qmeQuizManagement.quizQuestions = undefined;
                                qmeQuizManagement.quizQuestions = [];
                            }else {
                                for (var i in qmeQuizManagement.category) {
                                    var categoryObj = qmeQuizManagement.category[i];
                                    if ((oldValue && oldValue.categoryId && categoryObj.categoryId == oldValue.categoryId) || (oldValue && categoryObj.categoryId == oldValue)) {
                                        qmeQuizManagement.categoryId = categoryObj.categoryId;
                                    }
                                }
                            }
                        },
                        function(){
                        }
                    );
                }
            };

            qmeQuizManagement.clearAllQuestions = function(clear){
                if(clear){
                    qmeModelSession.create(true);
                }else{
                    qmeModelSession.create(false);
                }
            };

            qmeQuizManagement.addQuizQuestion = function() {
                qmeQuizManagement.shownSelectQuestions = true;
                qmeModelSession.destroy();
                $('#addQuestionsModal').modal('show');
                var promise = qmeModelSession.modalShown();
                promise.then(
                    function(data){
                        if(data){

                            for(var i in data){
                                var toAdd = true;
                                for(var j in qmeQuizManagement.quizQuestions){
                                    if(data[i].questionId == qmeQuizManagement.quizQuestions[j].questionId){
                                        toAdd = false;
                                        break;
                                    }
                                }
                                if(toAdd){
                                    qmeQuizManagement.quizQuestions.push(data[i]);
                                }
                            }
                        }
                        qmeQuizManagement.shownSelectQuestions = false;
                        qmeModelSession.destroy();
                        $('#addQuestionsModal').modal('hide');
                    },
                    function(){
                    }
                );
            };

            qmeQuizManagement.cancelQuestions = function() {
                qmeQuizManagement.shownSelectQuestions = false;
                $('#addQuestionsModal').modal('hide');
            };

            qmeQuizManagement.removeQuizQuestion = function(quizQuestionId) {
                console.log("got quizQuestionId ",quizQuestionId);
                for(var i in qmeQuizManagement.quizQuestions){
                    if(qmeQuizManagement.quizQuestions[i].questionId == quizQuestionId){
                        qmeQuizManagement.quizQuestions.splice(i,1);
                        break;
                    }
                }
            };

            qmeQuizManagement.submitAddQuiz = function(){
                var quiz = {
                    "categoryID": qmeQuizManagement.categoryId,
                    "quizName": qmeQuizManagement.quizName ,
                    "quizDesc": qmeQuizManagement.quizDesc ,
                    "quizMaxAttempts": 3,
                    "questionIdList": [],
                };
                for(var i in qmeQuizManagement.quizQuestions){
                    quiz.questionIdList.push(qmeQuizManagement.quizQuestions[i].questionId);
                }
                qmeQuizService
                    .createQuiz(quiz)
                    .then(
                        function(res){
                            qmeFlashService.Success("Quiz submitted successfully, .",true);
                            $state.go('listquizzes', {});
                        },
                        function(error){
                            if(error && error.status && error.status == 400){
                                qmeFlashService.Error("Oops.....Invalid request for submit quiz, please make sure all required fields are valid.");

                            }else if(error && error.status && error.status == 403){
                                qmeFlashService.Error("Oops.....User not authorized for function, please contact system administrator.");

                            }else if(error && error.status && error.status == 409){
                                qmeFlashService.Error("Oops.....Invalid request, quiz already exists or duplicated.");

                            }else{
                                qmeFlashService.Error("Oops.....Error adding new quiz, please retry in some time.");
                            }
                        }
                    );

            };

            qmeQuizManagement.updateQuiz = function(quiz){
                qmeQuizManagement.quizID = undefined;
                qmeQuizManagement.categoryId = undefined;
                qmeQuizManagement.categoryName = undefined;
                qmeQuizManagement.quizName = undefined;
                qmeQuizManagement.quizDesc = undefined;
                qmeQuizManagement.quizQuestions = [];
                qmeQuizService.getQuizById(quiz.quizID)
                    .then(
                        function(res){
                            $state.go('updatequiz',{
                                    currentQuiz:res,
                                    currentpage:qmeQuizManagement.currentpage,
                                    sortasc: qmeQuizManagement.sortasc,
                                    sortfields:qmeQuizManagement.sortfields
                                }
                            );
                        },
                        function(error){
                            if(error && error.status && error.status == 404){
                                qmeFlashService.Error("Oops.....Invalid quiz resource, quiz not found");

                            }else{
                                qmeFlashService.Error("Oops.....Error getting quiz detail, please retry in some time.");
                            }
                        }

                    );
            };

            qmeQuizManagement.selectedQuiz = function(){
                qmeQuizManagement.quizID = $stateParams.currentQuiz.quizID;
                qmeQuizManagement.categoryId = $stateParams.currentQuiz.categoryID;
                qmeQuizManagement.categoryName = $stateParams.currentQuiz.categoryName;
                qmeQuizManagement.quizName = $stateParams.currentQuiz.quizName;
                qmeQuizManagement.quizDesc = $stateParams.currentQuiz.quizDesc;
                qmeQuizManagement.quizQuestions = $stateParams.currentQuiz.questionIdList;
            };

            qmeQuizManagement.cancelAddQuiz = function(){
                $state.go('listquizzes', {});
            };

            qmeQuizManagement.submitUpdateQuiz = function(){

            };

            qmeQuizManagement.cancelUpdateQuiz = function(){
                $state.go('listquizzes', {});
            };

            qmeQuizManagement.deleteQuiz = function(){

            };
        }
})();