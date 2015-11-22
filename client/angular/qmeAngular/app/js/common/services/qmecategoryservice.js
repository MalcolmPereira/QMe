(function () {

	"use strict";

    ngQMe
        .service('qmeCategoryService',QMeCategoryService);

    QMeCategoryService.$inject = ['$q','qmeCategoryResource','qmeUserSession','QME_CONSTANTS'];

    function QMeCategoryService($q,qmeCategoryResource,qmeUserSession,QME_CONSTANTS) {

        var qmeCategoryService = this;

        qmeCategoryService.listCategoryByParent = function(parentId){
            var listCategoryPromise = $q.defer();

            qmeCategoryResource.categoryByParentResource(qmeUserSession.authtoken(),parentId)
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

        qmeCategoryService.createCategory = function(category){

            var createCategoryPromise = $q.defer();

            pleaseWait.showPleaseWait();

            qmeCategoryResource.categoryResource(qmeUserSession.authtoken())

                .save(category,function(res){
                        pleaseWait.hidePleaseWait();
                        createCategoryPromise.resolve(res);
                    },function(error){
                        pleaseWait.hidePleaseWait();
                        createCategoryPromise.reject(error);
                    }
                 );

            return createCategoryPromise.promise;
        };

        qmeCategoryService.updateCategory = function(category){

            var updateCategoryPromise = $q.defer();

            qmeCategoryResource.categoryUpdateResource(qmeUserSession.authtoken(),category.categoryId)
                .updateCategory({}, category,function(res){
                        pleaseWait.hidePleaseWait();
                        updateCategoryPromise.resolve(res);
                    },function(error){
                        pleaseWait.hidePleaseWait();
                        updateCategoryPromise.reject(error);
                    }
                );
            return updateCategoryPromise.promise;
        };

        qmeCategoryService.deleteCategory = function(categoryId){

            var deleteCategoryPromise = $q.defer();

            qmeCategoryResource.categoryDeleteResource(qmeUserSession.authtoken(),category.categoryId)
                .deleteCategory({}, {},function(res){
                        pleaseWait.hidePleaseWait();
                        deleteCategoryPromise.resolve(res);
                    },function(error){
                        pleaseWait.hidePleaseWait();
                        deleteCategoryPromise.reject(error);
                    }
                );
            return deleteCategoryPromise.promise;
        };
    }

})();