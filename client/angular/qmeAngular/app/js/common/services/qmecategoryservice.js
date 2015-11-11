(function () {

	"use strict";

    ngQMe
        .service('qmeCategoryService',QMeCategoryService);

    QMeCategoryService.$inject = ['$q','qmeCategoryResource','qmeUserSession','QME_CONSTANTS'];

    function QMeCategoryService($q,qmeCategoryResource,qmeUserSession,QME_CONSTANTS) {

        var qmeCategoryService = this;

        qmeCategoryService.listCategoryByParent = function(parentId){
            var listCategoryPromise = $q.defer();

            qmeCategoryResource.categoryByParentResource(qmeUserSession.authtoken())
                .query(
                    function(res){
                        listCategoryPromise.resolve(res);
                    },
                    function(error){
                        listCategoryPromise.reject(error);
                    }
                );
            return listCategoryPromise.promise;
        };
    }

})();