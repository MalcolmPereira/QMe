(function () {

    'use strict';

    ngQMe
        .service('qmeQuizService',QMeQuizService);

    QMeQuizService.$inject = ['$q','qmeQuizResource','qmeUserSession','QME_CONSTANTS'];

    function QMeQuizService($q,qmeQuizResource,qmeUserSession,QME_CONSTANTS) {

        var qmeQuizService = this;

        qmeQuizService.countQuiz = function(){
            return qmeQuizResource.quizCountResource(qmeUserSession.authtoken());
        };

        qmeQuizService.listQuiz = function(){
            var listQuizPromise = $q.defer();

            qmeQuizResource.quizCountResource(qmeUserSession.authtoken())
                .query(
                    function(res){
                        listQuizPromise.resolve(res);
                    },
                    function(error){
                        listQuizPromise.reject(error);
                    }
                );
            return listQuizPromise.promise;
        };

        qmeQuizService.listQuizPaged = function(currentPage,sorttype,sortfields){
            var listQuizPagedPromise = $q.defer();

            qmeQuizResource.quizPagedResource(qmeUserSession.authtoken(),currentPage,QME_CONSTANTS.rowsperpage,sorttype,sortfields)
                .query(
                    function(res){
                        listQuizPagedPromise.resolve(res);
                    },
                    function(error){
                        listQuizPagedPromise.reject(error);
                    }
                );
            return listQuizPagedPromise.promise;
        };

        qmeQuizService.getQuizById = function(quizId){
            var quizPromise = $q.defer();

            qmeQuizResource.quizByIdResource(qmeUserSession.authtoken(),quizId)
                .get(
                    function(res){
                        quizPromise.resolve(res);
                    },
                    function(error){
                        quizPromise.reject(error);
                    }
                );
            return quizPromise.promise;
        };

        qmeQuizService.createQuiz = function(quiz){
            var createQuizPromise = $q.defer();

            qmeQuizResource.quizResource(qmeUserSession.authtoken())

                .save(quiz,function(res){
                        createQuizPromise.resolve(res);
                    },function(error){
                        createQuizPromise.reject(error);
                    }
                );

            return createQuizPromise.promise;
        };

        qmeQuizService.updateQuiz = function(quiz){

            var updateQuizPromise = $q.defer();

            qmeQuizResource.quizUpdateResource(qmeUserSession.authtoken(),quiz.quizID)
                .updateQuiz({}, quiz,function(res){
                    updateQuizPromise.resolve(res);
                    },function(error){
                    updateQuizPromise.reject(error);
                    }
                );

            return updateQuizPromise.promise;
        };

        qmeQuizService.deleteQuiz = function(quizID){

            var deleteQuizPromise = $q.defer();

            qmeQuizResource.quizDeleteResource(qmeUserSession.authtoken(),quizID)
                .deleteQuiz({}, {},function(res){
                    deleteQuizPromise.resolve(res);
                    },function(error){
                    deleteQuizPromise.reject(error);
                    }
                );

            return deleteQuizPromise.promise;
        };
     }

})();