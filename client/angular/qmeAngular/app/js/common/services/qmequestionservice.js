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

    }

})();