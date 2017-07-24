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
            qmeQuizManagement.categoryId = undefined;
            qmeQuizManagement.categoryName = undefined;
            qmeQuizManagement.quizName = undefined;
            qmeQuizManagement.quizDesc = undefined;
            qmeQuizManagement.quizQuestions = [];

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

                qmeCategoryService.listCategory()
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
                $('#addQuestionsModal').modal('show');
            };

            qmeQuizManagement.cancelQuestions = function() {
                $('#addQuestionsModal').modal('hide');
            };

            qmeQuizManagement.removeQuizQuestion = function() {

            };


        }
})();