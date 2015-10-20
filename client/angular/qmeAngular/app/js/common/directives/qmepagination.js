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
            var _pagesPerPage   = null;
            var _currentPage    = null;
            var _lastPage       = null;
            var _currentGroup   = null;
            var _pages         = [];

            qmePageSession.create = function (totalRecCount){
                _totalRecCount  = totalRecCount ;
                _recPerPage     = QME_CONSTANTS.rowsperpage;
                _pagesPerPage   = QME_CONSTANTS.pagesperpage;
                _currentPage    = 0;
                _lastPage       = Math.ceil(_totalRecCount / _recPerPage);
                _pages          = [];

                if (_lastPage <= _pagesPerPage) {
                    for (var i = 1; i < _lastPage + 1; i++) {
                        _pages.push(i);
                    }
                    _currentGroup = undefined;
                } else {
                    for (var j = 1; j < _pagesPerPage + 1; j++) {
                        _pages.push(j);
                    }
                    _currentGroup = _pagesPerPage;
                }
                return qmePageSession;
            };

            qmePageSession.destroy = function () {
                _totalRecCount  = null;
                _recPerPage     = null;
                _currentPage    = null;
                _lastPage       = null;
                _pages          = [];
                return undefined;
            };

            qmePageSession.pages = function(){
                return _pages;
            };

            qmePageSession.getFirst = function(){
                if(! qmePageSession.isFirstPage() ){
                    _currentPage = 0;
                }
                return _currentPage;
            };

            qmePageSession.getLast = function(){
                if(! qmePageSession.isLastPage() ){
                    _currentPage  = _lastPage - 1;
                    _currentGroup = _lastPage - 1;
                    var lastPage  = (_currentGroup - Math.ceil(_currentGroup  % _pagesPerPage)) + 1;
                    _pages = [];
                    for (var i = lastPage; i < _currentGroup + 1; i++) {
                        _pages.push(i);
                    }
                }
                console.log("_pages",_pages);
                console.log("_currentPage",_currentPage);
                return _currentPage;
            };

            qmePageSession.setPrevious = function(){
                if(! qmePageSession.isFirstPage() ){
                    --_currentPage;
                }
            };

            qmePageSession.setNext = function(){
                if(! qmePageSession.isLastPage() ){
                    ++_currentPage;
                }
            };

            qmePageSession.getPage = function(){
                return _currentPage;
            };

            qmePageSession.setPage = function(pageNumber){
                return _currentPage = pageNumber;
            };

            qmePageSession.isCurrentPage = function(pageNumber){
                return (pageNumber === _currentPage );
            };

            qmePageSession.isFirstPage = function(){
                return (_currentPage === 0);
            };

            qmePageSession.isLastPage = function(){
                return ( _currentPage === _lastPage - 1);
            };

            qmePageSession.isExceedPagesPerPage = function(){
                return (_lastPage > QME_CONSTANTS.pagesperpage)
            };

            qmePageSession.showPreviousGroup = function(){
                return ((!qmePageSession.isFirstPage()) && (_currentGroup) && (_currentGroup > _pagesPerPage))
            };

            qmePageSession.getPreviousGroup = function(){
                if(_currentGroup  === _lastPage - 1){
                    _currentGroup = _currentGroup + (_pagesPerPage - _pages.length);
                }
                _currentGroup        = _currentGroup  - _pagesPerPage;
                if(_currentGroup < 0 ){
                    _currentGroup = _pagesPerPage;
                }
                var previousPage     = _currentGroup  - _pagesPerPage + 1;
                if(previousPage  <= 0 ){
                    previousPage = 1;
                }
                _pages = [];
                for (var i = previousPage; i < _currentGroup + 1; i++) {
                    _pages.push(i);
                }
                _currentPage = _pages[0] - 1;
                return _currentPage;
            };

            qmePageSession.showNextGroup = function(){
                return ((!qmePageSession.isLastPage()) && (_currentGroup) && (_currentGroup > 0 && _currentGroup < _lastPage -1))
            };

            qmePageSession.getNextGroup = function(){
                var lastPage    = _currentGroup  + 1;
                _currentGroup   = _currentGroup  + _pagesPerPage ;
                if(_currentGroup > _lastPage - 1){
                    _currentGroup = _lastPage - 1;
                }
                _pages = [];
                for (var i = lastPage; i < _currentGroup + 1; i++) {
                    _pages.push(i);
                }
                _currentPage = _pages[0] - 1;
                return _currentPage;
            };
        })

        .controller('qmePageCtrl',QMePageController);
        QMePageController.$inject = ['$scope', 'qmePageSession'];
        function QMePageController($scope,qmePageSession) {
            var qmePage = this;

            qmePage.functionCall  = $scope.qmePagingfunction;

            qmePage.qmePageSession = qmePageSession.destroy();

            qmePage.qmePageSession = qmePageSession.create($scope.qmeTotalcount);

            qmePage.goPage = function(pageNumber){
                qmePage.qmePageSession.setPage(pageNumber);
                $scope.qmePagingfunction()(pageNumber);
            };

            qmePage.isCurrentPage = function(pageNumber){
                return qmePage.qmePageSession.isCurrentPage(pageNumber);
            };

            qmePage.isFirstPage = function(){
                return qmePage.qmePageSession.isFirstPage();
            };

            qmePage.isLastPage = function(){
                return  qmePage.qmePageSession.isLastPage();
            };

            qmePage.goPrevious = function(){
                if(!qmePage.isFirstPage()){
                    qmePage.qmePageSession.setPrevious();
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPage());
                }
            };

            qmePage.goFirst = function(){
                if(!qmePage.isFirstPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getFirst());
                }
            };

            qmePage.goNext = function(){
                if(!qmePage.isLastPage()){
                    qmePage.qmePageSession.setNext();
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPage());
                }
            };

            qmePage.goLast = function(){
                if(!qmePage.isLastPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getLast());
                }
            };

            qmePage.isExceedPagesPerPage = function(){
                return qmePage.qmePageSession.isExceedPagesPerPage();
            };

            qmePage.showPreviousGroup = function(){
                return qmePage.qmePageSession.showPreviousGroup();
            };

            qmePage.getPreviousGroup = function(){
                if(!qmePage.isFirstPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getPreviousGroup());
                }
            };

            qmePage.showNextGroup = function(){
                return qmePage.qmePageSession.showNextGroup ();
            };

            qmePage.getNextGroup = function(){
                if(!qmePage.isLastPage()){
                    $scope.qmePagingfunction()(qmePage.qmePageSession.getNextGroup());
                }
            };
        }
})();