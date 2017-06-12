(function () {

    'use strict';

    ngQMe
        .service('qmeQuestionService',QMeQuestionService);

    QMeQuestionService.$inject = ['$q','qmeQuestionResource','qmeUserSession','QME_CONSTANTS'];

    function QMeQuestionService($q,qmeQuestionResource,qmeUserSession,QME_CONSTANTS) {

        var qmeQuestionService = this;

        qmeQuestionService.countQuestions = function(){
            return qmeQuestionResource.questionCountResource(qmeUserSession.authtoken());
        };

        qmeQuestionService.listQuestions = function(){
            var listQuestionPromise = $q.defer();

            qmeQuestionResource.questionResource(qmeUserSession.authtoken())
                .query(
                    function(res){
                        listQuestionPromise.resolve(res);
                    },
                    function(error){
                        listQuestionPromise.reject(error);
                    }
                );
            return listQuestionPromise.promise;
        };

        qmeQuestionService.listQuestionsPaged = function(currentPage,sorttype,sortfields){
            var listQuestionPagedPromise = $q.defer();

            qmeQuestionResource.questionPagedResource(qmeUserSession.authtoken(),currentPage,QME_CONSTANTS.rowsperpage,sorttype,sortfields)
                .query(
                    function(res){
                        listQuestionPagedPromise.resolve(res);
                    },
                    function(error){
                        listQuestionPagedPromise.reject(error);
                    }
                );
            return listQuestionPagedPromise.promise;
        };

        qmeQuestionService.getQuestionById = function(questionId){
            var questionPromise = $q.defer();

            qmeQuestionResource.questionByIdResource(qmeUserSession.authtoken(),questionId)
                .get(
                    function(res){
                        questionPromise.resolve(res);
                    },
                    function(error){
                        questionPromise.reject(error);
                    }
                );
            return questionPromise.promise;
        };

        qmeQuestionService.createQuestion = function(question){
            var createQuestionPromise = $q.defer();

            qmeQuestionResource.questionResource(qmeUserSession.authtoken())

                .save(question,function(res){
                         createQuestionPromise.resolve(res);
                    },function(error){
                         createQuestionPromise.reject(error);
                    }
                );

            return createQuestionPromise.promise;
        };

        qmeQuestionService.updateQuestion = function(question){

            var updateQuestionPromise = $q.defer();

            qmeQuestionResource.questionUpdateResource(qmeUserSession.authtoken(),question.questionId)
                .updateQuestion({}, question,function(res){
                        updateQuestionPromise.resolve(res);
                    },function(error){
                        updateQuestionPromise.reject(error);
                    }
                );

            return updateQuestionPromise.promise;
        };

        qmeQuestionService.deleteQuestion = function(questionId){

            var deleteQuestionPromise = $q.defer();

            qmeQuestionResource.questionDeleteResource(qmeUserSession.authtoken(),questionId)
                .deleteQuestion({}, {},function(res){
                        deleteQuestionPromise.resolve(res);
                    },function(error){
                        deleteQuestionPromise.reject(error);
                    }
                );

            return deleteQuestionPromise.promise;
        };
    }

})();