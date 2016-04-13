(function () {

    'use strict';

    describe('Service: QMe Resource Service', function() {

        var scope, qmeUserResource, qmeCategoryResource, qmeQuestionResource, qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,
                                   _qmeUserResource_,
                                   _qmeCategoryResource_,
                                   _qmeQuestionResource_,
                                   _QME_CONSTANTS_) {
            scope = $rootScope.$new();
            qmeContants = _QME_CONSTANTS_;
            qmeUserResource = _qmeUserResource_;
            qmeCategoryResource = _qmeCategoryResource_;
            qmeQuestionResource = _qmeQuestionResource_;
        }));

        //User Resources
        it('Should have a QMe User Resource', function() {
            expect(qmeUserResource).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });
        it('Should have a User Resource Defined', function() {
            expect(qmeUserResource.userUpdateResource("sometoken","someid")).toBeDefined();
        });
        it('Should have a User Auth Resource Defined', function() {
            expect(qmeUserResource.userAuthResource()).toBeDefined();
        });

        it('Should have a Search User Resource Defined', function() {
            expect(qmeUserResource.userGetUserResource("someemail")).toBeDefined();
        });

        it('Should have a User Staging Resource Defined', function() {
            expect(qmeUserResource.userStageResource()).toBeDefined();
        });

        it('Should have a User Staging Confirm Resource Defined', function() {
            expect(qmeUserResource.userConfirmResource()).toBeDefined();
        });

        it('Should have a User Register Resource Defined', function() {
            expect(qmeUserResource.userRegisterResource()).toBeDefined();
        });

        it('Should have a User Forgot Password Resource Defined', function() {
            expect(qmeUserResource.userForgotPasswordResource("someemail")).toBeDefined();
        });

        it('Should have a User Submit Reset Password Resource Defined', function() {
            expect(qmeUserResource.userResetPasswordResource("someemail")).toBeDefined();
        });

        it('Should have a User Logout Resource Defined', function() {
            expect(qmeUserResource.logoutResource()).toBeDefined();
        });

        it('Should have a User Count Resource Defined', function() {
            expect(qmeUserResource.userCountResource("sometoken")).toBeDefined();
        });

        it('Should have a User List Resource Defined', function() {
            expect(qmeUserResource.userResource("sometoken")).toBeDefined();
        });

        it('Should have a User List Paged Resource Defined', function() {
            expect(qmeUserResource.userPagedResource("sometoken",1,50,true,"USER_NAME")).toBeDefined();
        });

        //Category Resources
        it('Should have a Category Resource Defined', function() {
            expect(qmeCategoryResource.categoryResource("sometoken")).toBeDefined();
        });

        it('Should have a Category Update Resource Defined', function() {
            expect(qmeCategoryResource.categoryUpdateResource("sometoken",1)).toBeDefined();
        });

        it('Should have a Category Delete Resource Defined', function() {
            expect(qmeCategoryResource.categoryDeleteResource("sometoken",1)).toBeDefined();
        });

        it('Should have a Category Count Resource Defined', function() {
            expect(qmeCategoryResource.categoryCountResource("sometoken")).toBeDefined();
        });

        it('Should have a Category By Parent Id Resource Defined', function() {
            expect(qmeCategoryResource.categoryByParentResource("sometoken",1)).toBeDefined();
        });

        it('Should have a Category By Search String Resource Defined', function() {
            expect(qmeCategoryResource.categorySearchResource("sometoken","search")).toBeDefined();
        });

        //Question Resources
        it('Should have a Question Resource Defined', function() {
            expect(qmeQuestionResource.questionResource("sometoken")).toBeDefined();
        });

        it('Should have a Question Count Resource Defined', function() {
            expect(qmeQuestionResource.questionCountResource("sometoken")).toBeDefined();
        });

        it('Should have a Question Paged Resource Defined', function() {
            expect(qmeQuestionResource.questionPagedResource("sometoken",1,50,true,"QUESTION")).toBeDefined();
        });

        it('Should have a Question Update Resource Defined', function() {
            expect(qmeQuestionResource.questionUpdateResource("sometoken",1)).toBeDefined();
        });

        it('Should have a Question Delete Resource Defined', function() {
            expect(qmeQuestionResource.questionDeleteResource("sometoken",1)).toBeDefined();
        });
    });

})();
