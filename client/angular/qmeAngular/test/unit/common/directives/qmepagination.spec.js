(function () {

	"use strict";

    describe('Unit: QMe Pagination Directive', function () {

        var qmePagination,compile,rootScope,qmePageCtrl,qmePageSession, mockPageFunc;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($compile, $rootScope, $controller,_qmePageSession_) {
            compile = $compile;
            rootScope = $rootScope;
            qmePageSession = _qmePageSession_;
            qmePageCtrl  = $controller('qmePageCtrl', {
                $scope: rootScope,
                qmePageSession:qmePageSession
            });
            mockPageFunc - function(){};
        }));

        it('Should have a QMe Pagination Directive', function() {
            expect(compile).toBeDefined();
            expect(rootScope).toBeDefined();
            qmePagination = compile("<qme-page qme-totalcount='200' qme-pagingfunction='mockPageFunc'></qme-page>")(rootScope)
            rootScope.$digest();
            expect(qmePagination.html()).toContain("qmePageCtrl.goNext()");
        });

        //No Pagination
        it('Should have a QMe Page Session and return valid page and page group states when total records is 0', function() {
            expect(qmePageSession).toBeDefined();

            qmePageSession.create(0);
            expect(qmePageSession.requiresPagination()).toBe(false);
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPrevious();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setNext();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPage(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(false);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.getPreviousGroup()).toBe(0);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.getNextGroup()).toBe(0);
        });

        //No Pagination
        it('Should have a QMe Page Session and return valid page and page group states when total records is 5 ', function() {
            expect(qmePageSession).toBeDefined();

            qmePageSession.create(5);
            expect(qmePageSession.requiresPagination()).toBe(false);
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(1);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPrevious();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setNext();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            qmePageSession.setPage(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(true);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.getPreviousGroup()).toBe(0);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.getNextGroup()).toBe(0);
        });

        //Pagination 120 Records 50 Pere Page so 3 Pages (50/50/20)
        it('Should have a QMe Page Session and return valid page and page group states when total records is 120 ', function() {
            expect(qmePageSession).toBeDefined();

            qmePageSession.create(120);
            expect(qmePageSession.requiresPagination()).toBe(true);
            expect(qmePageSession.pages()).toBeDefined();
            expect(qmePageSession.pages().length).toBe(3);
            expect(qmePageSession.getPage()).toBe(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(2);
            expect(qmePageSession.getPage()).toBe(2);

            qmePageSession.getFirst();
            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(1);
            qmePageSession.setNext();
            expect(qmePageSession.getPage()).toBe(2);
            qmePageSession.getLast();
            expect(qmePageSession.getPage()).toBe(2);



            /*
            qmePageSession.setPrevious();
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);



            qmePageSession.setPage(0);
            expect(qmePageSession.getFirst()).toBe(0);
            expect(qmePageSession.getLast()).toBe(0);
            expect(qmePageSession.getPage()).toBe(0);

            expect(qmePageSession.isCurrentPage(0)).toBe(true);
            expect(qmePageSession.isFirstPage()).toBe(true);
            expect(qmePageSession.isLastPage()).toBe(true);
            expect(qmePageSession.showPreviousGroup()).toBe(false);
            expect(qmePageSession.getPreviousGroup()).toBe(0);
            expect(qmePageSession.showNextGroup()).toBe(false);
            expect(qmePageSession.getNextGroup()).toBe(0);
            */
        });

    });

})();