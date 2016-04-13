(function () {

	"use strict";

    describe('Service: QMe Question Service', function() {

        var scope,
            state,
            qmeQuestionService,
            qmeUserSession,
            httpBackend,
            qmeContants,
            questionEndPoint,
            questionPagedEndPoint,
            questionCountEndPoint
            ;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,$state, _qmeQuestionService_, _qmeUserSession_,$httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            state = $state;
            qmeQuestionService = _qmeQuestionService_;
            qmeUserSession = _qmeUserSession_;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            questionEndPoint     = qmeContants.qmeservice+"/question";
            questionPagedEndPoint = qmeContants.qmeservice+"/question/paged";
            questionCountEndPoint  = qmeContants.qmeservice+"/question/count";
            qmeUserSession.create("sometoken", 1 , "some", "some", "last", "someemail", "2015-14-11 19:31:32", ["USER","ADMIN"]);
         }));

        it('Should have a QMe Question Service', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should handle valid count question request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            var countresponse =  {
                "content":"264",
                "links":[
                    {
                        "rel":"self",
                        "href":"http://localhost:8002/qme/question/count"
                    }
                ]
            };

            httpBackend.expectGET(questionCountEndPoint).respond(200,countresponse);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeQuestionService
                .countQuestions()
                .then(
                    function(res){
                        expect(res).toBeDefined();
                    },
                    function(error){
                    }
                );
            httpBackend.flush();

        });

        it('Should handle valid 500 Server Error for list questions request ', function() {
            expect(qmeQuestionService).toBeDefined();
            expect(scope.flash).not.toBeDefined();

            httpBackend.expectGET(questionEndPoint).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});

            qmeQuestionService
                .listQuestions()
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