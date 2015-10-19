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

        .controller('qmePageCtrl',QMePageController);
        QMePageController.$inject = ['$scope', '$element', '$attrs'];
        function QMePageController($scope) {
            var qmePage = this;
            qmePage.functionCall  = $scope.qmePagingfunction;
            qmePage.totalRecCount = $scope.qmeTotalcount;
            qmePage.recPerPage    = 10;
            qmePage.currentPage   = 0;
            qmePage.lastPage      = Math.round(qmePage.totalRecCount / qmePage.recPerPage);

            qmePage.pageList = function(){
                var pages = [];
                for (var i = 1; i <  qmePage.lastPage + 1; i++) {
                    pages.push(i);
                }
                return pages;
            }

            qmePage.goPage = function(pageNumber){
                qmePage.currentPage = pageNumber;
                $scope.qmePagingfunction()(pageNumber);
            }

            qmePage.isCurrentPage = function(pageNumber){
                return (pageNumber === qmePage.currentPage );
            };

            qmePage.isFirstPage = function(){
                return (qmePage.currentPage == 0);
            }

            qmePage.goPrevious = function(){
                if(!qmePage.isFirstPage()){
                    --qmePage.currentPage;
                }
                $scope.qmePagingfunction()(qmePage.currentPage);
            }

            qmePage.isLastPage = function(){
                return ( qmePage.currentPage === qmePage.lastPage);
            }

            qmePage.goNext = function(){
                if(!qmePage.isLastPage()){
                    ++qmePage.currentPage;
                }
                $scope.qmePagingfunction()(qmePage.currentPage);
            }
        }
})();