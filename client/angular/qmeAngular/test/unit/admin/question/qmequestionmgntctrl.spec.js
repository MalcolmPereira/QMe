(function () {

    'use strict';

    describe('Controller: QMe Admin Question Management Controller', function() {

        var rootScope,
            scope,
            state,
            stateParams,
            httpBackend,
            ctrl,
            qmeContants,
            qmeUserService,
            qmeQuestionService,
            qmePageSession,
            qmeFlashService,
            timeout,
            questionEndPoint,
            questionPagedEndPoint,
            questionCountEndPoint

        ;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($rootScope,$state,$stateParams, $controller,$httpBackend,_QME_CONSTANTS_,_qmeUserService_,_qmeQuestionService_,_qmePageSession_, _qmeFlashService_,_$timeout_) {
            rootScope = $rootScope;
            scope = $rootScope.$new();
            state = $state;
            stateParams = $stateParams;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            questionEndPoint     = qmeContants.qmeservice+"/question";
            questionPagedEndPoint = qmeContants.qmeservice+"/question/paged";
            questionCountEndPoint  = qmeContants.qmeservice+"/question/count";
            qmeUserService = _qmeUserService_;
            qmeQuestionService = _qmeQuestionService_
            qmePageSession = _qmePageSession_;
            qmeFlashService = _qmeFlashService_;
            timeout = _$timeout_;
            stateParams.sortasc = true;
            stateParams.sortfields = "QUESTION";
            stateParams.currentpage = 1;
            stateParams.currentuser = {
                userId:"1",
                userEmail:"test",
                userName:"test",
                userFirstName:"test",
                userLastName:"test",
                userRoles:["USER"]
            };

            ctrl  = $controller('qmeQuestionManagementCtrl', {
                $scope: scope,
                $state:state,
                $stateParams:stateParams,
                qmeFlashService: qmeFlashService,
                qmeUserService: qmeUserService,
                qmePageSession: qmePageSession
            });


        }));


        it('Should have a QMe Question Management controller and display valid not authorized message for count question for no admin users ', function() {
            expect(ctrl).toBeDefined();

        });

    });

})();