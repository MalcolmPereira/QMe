(function () {

    'use strict';

    describe('Service: QMe Category Service', function() {

        var scope,
            state,
            qmeCategoryService,
            qmeUserSession,
            httpBackend,
            qmeContants,
            categoryEndPoint,
            categoryCountEndPoint,
            categoryByParentEndPoint,
            categorySearchEndPoint;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,$state, _qmeCategoryService_, _qmeUserSession_,$httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            state = $state;
            qmeCategoryService = _qmeCategoryService_;
            qmeUserSession = _qmeUserSession_;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            categoryEndPoint     = qmeContants.qmeservice+"/category";
            categoryCountEndPoint  = qmeContants.qmeservice+"/category/count";
            categoryByParentEndPoint = qmeContants.qmeservice+"/category/parent";
            categorySearchEndPoint = qmeContants.qmeservice+"/category/search";
            qmeUserSession.create("sometoken", 1 , "some", "some", "last", "someemail", "2015-14-11 19:31:32", ["USER","ADMIN"]);
        }));

        it('Should have a QMe Category Service', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should have a List Category By Parent', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var categoryList = [
                {
                    "parentCategoryId": 2,
                    "categoryName": "Child Category 1",
                    "categoryId": 11,
                    "categoryLikes": 0,
                    "createdDate": "2015-14-11 19:31:32",
                    "createdUser": 1,
                    "createdUserName": "",
                    "links": []
                },
                {
                    "parentCategoryId": 2,
                    "categoryName": "Child Category 2",
                    "categoryId": 12,
                    "categoryLikes": 0,
                    "createdDate": "2015-14-11 19:31:32",
                    "createdUser": 1,
                    "createdUserName": "",
                    "links": []
                }
            ];
            httpBackend.expectGET(categoryByParentEndPoint+"/1").respond(200,categoryList);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .listCategoryByParent(1)
                .then(
                    function(res){
                        expect(res).toBeDefined();
                        expect(res.length).toBe(2);
                    },
                    function(error){
                    }
                );
            httpBackend.flush();
        });

        it('Should Return valid Error for  List Category By Parent', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectGET(categoryByParentEndPoint+"/1").respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .listCategoryByParent(1)
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );
            httpBackend.flush();
        });

        it('Should Create Category', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var newCategory = {
                    "parentCategoryId": 0,
                    "categoryName": "New Category"
            };
            httpBackend.expectPOST(categoryEndPoint,newCategory).respond(200,newCategory);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .createCategory(newCategory)
                .then(
                    function(res){
                        expect(res).toBeDefined();
                    },
                    function(error){
                    }
                );
            httpBackend.flush();
        });

        it('Should Return valid error for Create Category', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var newCategory = {
                "parentCategoryId": 0,
                "categoryName": "New Category"
            };
            httpBackend.expectPOST(categoryEndPoint,newCategory).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .createCategory(newCategory)
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );
            httpBackend.flush();
        });

        it('Should Update Category', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var updateCategory = {
                "parentCategoryId": 0,
                "categoryId": 1,
                "categoryName": "New Category"
            };
            httpBackend.expectPUT(categoryEndPoint+"/1",updateCategory).respond(200,updateCategory);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .updateCategory(updateCategory)
                .then(
                    function(res){
                        expect(res).toBeDefined();
                    },
                    function(error){
                    }
                );
            httpBackend.flush();
        });
        it('Should Return valid error for Update Category', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var updateCategory = {
                "parentCategoryId": 0,
                "categoryId": 1,
                "categoryName": "New Category"
            };
            httpBackend.expectPUT(categoryEndPoint+"/1",updateCategory).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .updateCategory(updateCategory)
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );
            httpBackend.flush();
        });

        it('Should Delete Category', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectDELETE(categoryEndPoint+"/1").respond(200,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .deleteCategory(1)
                .then(
                    function(res){
                        expect(res).toBeDefined();
                    },
                    function(error){
                    }
                );
            httpBackend.flush();
        });
        it('Should Return valid error for Delete Category', function() {
            expect(qmeCategoryService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            httpBackend.expectDELETE(categoryEndPoint+"/1").respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeCategoryService
                .deleteCategory(1)
                .then(
                    function(res){
                    },
                    function(error){
                        expect(error).toBeDefined();
                    }
                );
            httpBackend.flush();
        });
    });

})();