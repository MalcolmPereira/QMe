(function () {

	"use strict";

    ngQMe
        .service('qmeCategoryService',QMeCategoryService);

    QMeCategoryService.$inject = ['$q','qmeCategoryResource','qmeUserSession','QME_CONSTANTS'];

    function QMeCategoryService($q,qmeCategoryResource,qmeUserSession,QME_CONSTANTS) {

        var qmeCategoryService = this;

        qmeCategoryService.listCategory = function(){
            var listCategoryPromise = $q.defer();

            qmeCategoryResource.categoryResource(qmeUserSession.authtoken())
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

        qmeCategoryService.listCategoryWithQuestions = function(){
            var listCategoryPromise = $q.defer();

            qmeCategoryResource.categoryWithQuestionsResource(qmeUserSession.authtoken())
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

            qmeCategoryResource.categoryResource(qmeUserSession.authtoken())

                .save(category,function(res){
                   createCategoryPromise.resolve(res);
                },function(error){
                   createCategoryPromise.reject(error);
                   }
                 );

            return createCategoryPromise.promise;
        };

        qmeCategoryService.updateCategory = function(category){

            var updateCategoryPromise = $q.defer();

            qmeCategoryResource.categoryUpdateResource(qmeUserSession.authtoken(),category.categoryId)
                .updateCategory({}, category,function(res){
                        updateCategoryPromise.resolve(res);
                    },function(error){
                        updateCategoryPromise.reject(error);
                    }
                );
            return updateCategoryPromise.promise;
        };

        qmeCategoryService.deleteCategory = function(categoryId){

            var deleteCategoryPromise = $q.defer();

            qmeCategoryResource.categoryDeleteResource(qmeUserSession.authtoken(),categoryId)
                .deleteCategory({}, {},function(res){
                        deleteCategoryPromise.resolve(res);
                    },function(error){
                        deleteCategoryPromise.reject(error);
                    }
                );
            return deleteCategoryPromise.promise;
        };
    }
})();