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

    });

})();