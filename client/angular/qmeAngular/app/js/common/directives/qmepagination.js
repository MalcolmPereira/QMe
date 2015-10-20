(function () {

    'use strict';

    ngQMe

        .directive('qmePage', function (){
            return {
                restrict: 'E',
                templateUrl: 'js/common/directives/qmepagination.tmpl.html',
                controller: 'qmePageCtrl',
                controllerAs: 'qmePageCtrl',
                scope: {
                    qmeTotalcount: '=',
                    qmePagingfunction: '&'
                }
            };
        })

        .service('qmePageSession', function (QME_CONSTANTS) {

            var qmePageSession  = this;

            var _totalRecCount  = null;
            var _recPerPage     = null;
            var _currentPage    = null;
            var _lastPage       = null;

            qmePageSession.create = function (totalRecCount){
                _totalRecCount  = totalRecCount ;
                _recPerPage     = QME_CONSTANTS.rowsperpage;
                _currentPage    = 0;
                _lastPage       = Math.ceil(_totalRecCount / _recPerPage);
            };

            qmePageSession.destroy = function () {
                _totalRecCount  = null;
                _recPerPage     = null;
                _currentPage    = null;
                _lastPage       = null;
            }

            qmePageSession.pageList = function(){
                var pages = [];
                for (var i = 1; i <  _lastPage + 1; i++) {
                    pages.push(i);
                }
                return pages;
            }

            qmePageSession.getFirst = function(){
                if(! qmePageSession.isFirstPage() ){
                    _currentPage = 0;
                }
                return _currentPage;
            }

            qmePageSession.getLast = function(){
                if(! qmePageSession.isLastPage() ){
                    _currentPage = _lastPage - 1;
                }
                return _currentPage;
            }

            qmePageSession.setPrevious = function(){
                if(! qmePageSession.isFirstPage() ){
                    --_currentPage;
                }
            }

            qmePageSession.setNext = function(){
                if(! qmePageSession.isLastPage() ){
                    ++_currentPage;
                }
            }

            qmePageSession.getPage = function(){
                return _currentPage;
            }

            qmePageSession.setPage = function(pageNumber){
                return _currentPage = pageNumber;
            }

            qmePageSession.isCurrentPage = function(pageNumber){
                return (pageNumber === _currentPage );
            };

            qmePageSession.isFirstPage = function(){
                return (_currentPage === 0);
            }

            qmePageSession.isLastPage = function(){
                return ( _currentPage === _lastPage - 1);
            }

        })

        .controller('qmePageCtrl',QMePageController);
        QMePageController.$inject = ['$scope', 'qmePageSession'];
        function QMePageController($scope,qmePageSession) {
            var qmePage = this;

            qmePage.functionCall  = $scope.qmePagingfunction;

            qmePageSession.destroy();

            qmePageSession.create($scope.qmeTotalcount);

            qmePage.pageList = function(){
                return qmePageSession.pageList();
            }

            qmePage.goPage = function(pageNumber){
                qmePageSession.setPage(pageNumber);
                $scope.qmePagingfunction()(pageNumber);
            }

            qmePage.isCurrentPage = function(pageNumber){
                return qmePageSession.isCurrentPage(pageNumber);
            };

            qmePage.isFirstPage = function(){
                return qmePageSession.isFirstPage();
            }

            qmePage.isLastPage = function(){
                return  qmePageSession.isLastPage();
            }

            qmePage.goPrevious = function(){
                if(!qmePage.isFirstPage()){
                    qmePageSession.setPrevious();
                    $scope.qmePagingfunction()(qmePageSession.getPage());
                }
            }

            qmePage.goFirst = function(){
                if(!qmePage.isFirstPage()){
                    $scope.qmePagingfunction()(qmePageSession.getFirst());
                }
            }

            qmePage.goNext = function(){
                if(!qmePage.isLastPage()){
                    qmePageSession.setNext();
                    $scope.qmePagingfunction()(qmePageSession.getPage());
                }
            }

            qmePage.goLast = function(){
                if(!qmePage.isLastPage()){
                    $scope.qmePagingfunction()(qmePageSession.getLast());
                }
            }
        }
})();