(function () {

    'use strict';

    describe('Controller: QMe Admin Category Controller', function() {

        var rootScope,
            scope,
            state,
            httpBackend,
            ctrl,
            qmeContants,
            qmeCategoryService,
            qmeFlashService,
            categoryEndPoint,
            categoryCountEndPoint,
            categoryByParentEndPoint,
            categorySearchEndPoint,
            timeout
            ;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($rootScope,$state, $controller,$httpBackend,_QME_CONSTANTS_,_qmeCategoryService_,_qmeFlashService_,_$timeout_) {
            rootScope = $rootScope;
            scope = $rootScope.$new();
            state = $state;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            qmeFlashService = _qmeFlashService_;
            timeout = _$timeout_;
            qmeCategoryService = _qmeCategoryService_;
            categoryEndPoint     = qmeContants.qmeservice+"/category";
            categoryCountEndPoint  = qmeContants.qmeservice+"/category/count";
            categoryByParentEndPoint = qmeContants.qmeservice+"/category/parent";
            categorySearchEndPoint = qmeContants.qmeservice+"/category/search";
            ctrl  = $controller('qmeCategoryManagementCtrl', {
                $state:state,
                $scope:scope,
                qmeFlashService: qmeFlashService,
                qmeCategoryService: qmeCategoryService
            });
        }));

        it('Should have a QMe Category Management controller ', function() {
            expect(ctrl).toBeDefined();
        });

        it('Should handle error conditions for  list categoies ', function() {
            expect(ctrl).toBeDefined();
            var someValue = false;
            var someFunction = function(some){
                someValue = true;
            };
            httpBackend.expectGET(categoryByParentEndPoint+"/1").respond(403,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listCategories(someFunction,1);
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');

            httpBackend.expectGET(categoryByParentEndPoint+"/1").respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listCategories(someFunction,1);
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error from service getting category lists, please retry in some time.');
        });

        it('Should handle successful list categories ', function() {
            expect(ctrl).toBeDefined();
            var someValue = false;
            var someFunction = function(some){
                someValue = true;
            };
            var categoryList = [
                {
                  categoryId:"1",
                  parentId:"0",
                  categoryName:"test"
                },
                {
                    categoryId:"1",
                    parentId:0,
                    categoryName:"test"
                }
            ];
            httpBackend.expectGET(categoryByParentEndPoint+"/1").respond(200,categoryList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listCategories(someFunction,1);
            httpBackend.flush();
            expect(someValue).toBeDefined();
            expect(someValue).toBe(true);
        });

        it('Should select valid node', function() {
            var selectedNode = {
                data:{
                    categoryId:"1",
                    parentId:"0",
                    categoryName:"test"
                }
            };
            ctrl.selectNode(selectedNode);
        });

        it('Should add new Category controller ', function() {
            ctrl.addNewCategory();
        });

        it('Should cancel Category Updates ', function() {
            ctrl.cancelUpdates();
        });

        it('Should Check if Category Deletable ', function() {
            ctrl.addNew = true;
            expect(ctrl.isDeletable()).toBe(true);
            ctrl.addNew = false;
            expect(ctrl.isDeletable()).toBe(true);
        });

        it('Should handle error response for delete category request ', function() {
            httpBackend.expectDELETE(categoryEndPoint+"/1").respond(403,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.deleteCategory(1);
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');

            httpBackend.expectDELETE(categoryEndPoint+"/1").respond(404,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.deleteCategory(1);
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request Parent Category/Category invalid, not found.');

            httpBackend.expectDELETE(categoryEndPoint+"/1").respond(400,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.deleteCategory(1);
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, please make sure valid category name is provided.');

            httpBackend.expectDELETE(categoryEndPoint+"/1").respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.deleteCategory(1);
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error deleting category , please contact administrator.');
        });

        it('Should handle valid delete category request ', function() {
            ctrl.categoryParentsAll = [
                {
                    categoryId:9,
                    parentId:0,
                    categoryName:"test"
                },
                {
                    categoryId:10,
                    parentId:0,
                    categoryName:"test"
                },
                {
                    categoryId:11,
                    parentId:11,
                    categoryName:"test"
                }
            ];

            ctrl.categoryParents = [
                {
                    categoryId:9,
                    parentId:0,
                    categoryName:"test"
                },
                {
                    categoryId:10,
                    parentId:0,
                    categoryName:"test"
                },
                {
                    categoryId:11,
                    parentId:11,
                    categoryName:"test"
                }
            ];
            httpBackend.expectDELETE(categoryEndPoint+"/1").respond(200,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.deleteCategory(1);
            httpBackend.flush();
            httpBackend.expectDELETE(categoryEndPoint+"/11").respond(200,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.deleteCategory(11);
            httpBackend.flush();
        });

        it('Should error response for submit updates for add and update category request ', function() {
            ctrl.categoryName = "test";
            ctrl.addNew = true;
            var categoryObj = {
                "categoryName":"test"
            };
            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(403,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(404,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request Parent Category invalid,not found.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(400,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, please make sure valid category name is provided.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(409,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, category with name already exists, please use unique valid category name.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(500,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error addming new category, please retry in some time.');


            ctrl.categoryName = "test";
            ctrl.parentId = 1
            ctrl.addNew = true;
            var categoryObj = {
                "categoryName":"test",
                "parentCategoryId":1
            };
            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(403,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(404,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request Parent Category invalid,not found.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(400,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, please make sure valid category name is provided.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(409,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, category with name already exists, please use unique valid category name.');

            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(500,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error addming new category, please retry in some time.');


            ctrl.parentId = undefined;
            ctrl.categoryName = "test";
            ctrl.categoryId = 1;
            ctrl.addNew = false;
            var categoryObj = {
                "categoryId":1,
                "categoryName":"test"
            };
            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(403,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');


            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(404,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request Parent Category/Category invalid, not found.');

            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(400,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, please make sure valid category name is provided.');

            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(409,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, category with name already exists, please use unique valid category name.');

            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(500,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error updating category, please retry in some time.');

            ctrl.categoryId = 1;
            ctrl.categoryName = "test";
            ctrl.parentId = 1
            ctrl.addNew = false;
            var categoryObj = {
                "categoryId":1,
                "categoryName":"test",
                "parentCategoryId":1
            };
            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(404,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request Parent Category/Category invalid, not found.');

            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(400,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, please make sure valid category name is provided.');

            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(409,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request, category with name already exists, please use unique valid category name.');

            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(500,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error updating category, please retry in some time.');

        });

        it('Should handle valid submit updates for add and update category request ', function() {
            ctrl.categoryName = "test";
            ctrl.addNew = true;
            var categoryObj = {
                "categoryName":"test"
            };
            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(200,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();

            ctrl.categoryName = "test";
            ctrl.parentId = 1
            ctrl.addNew = true;
            var categoryObj = {
                "categoryName":"test",
                "parentCategoryId":1
            };
            httpBackend.expectPOST(categoryEndPoint,categoryObj).respond(200,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();

            ctrl.categoryName = "test";
            ctrl.categoryId = 1;
            ctrl.addNew = false;
            var categoryObj = {
                "categoryId":1,
                "categoryName":"test"
            };
            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(200,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();

            ctrl.categoryId = 1;
            ctrl.categoryName = "test";
            ctrl.parentId = 1
            ctrl.addNew = false;
            var categoryObj = {
                "categoryId":1,
                "categoryName":"test",
                "parentCategoryId":1
            };
            httpBackend.expectPUT(categoryEndPoint+"/1",categoryObj).respond(200,categoryObj);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdates();
            httpBackend.flush();
        });

    });

})();