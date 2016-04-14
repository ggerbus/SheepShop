var sheepShopControllers = angular.module('sheepShopControllers', []);

/* ########################################################
 * 						CUSTOMER
 * ######################################################## */
sheepShopControllers.controller('customerController', ['$scope', '$http', function ($scope, $http) {
	$scope.customer=[];
	$scope.customer.order=[];
	$scope.day = 1;
	$scope.customer.name = "Pero";
	$scope.customer.order.milk = 0.0;
	$scope.customer.order.skins = 0;
	$scope.getOrder = function() {
		var url = "/sheep-shop/order/" + $scope.day;
		var parameter = JSON.stringify({customer:$scope.customer.name, order:{milk:$scope.customer.order.milk, skins:$scope.customer.order.skins}});
        $http.post(url,parameter)
        	.success(function (data, status, headers, config) {
        		if(status==201){
        			$scope.orderStatus="Successful order.";
        			$scope.orderData=data;
        		}else if(status == 206){
        			$scope.orderStatus="Partial order.";
        			$scope.orderData=data;
        		}else{
        			$scope.orderStatus="Unchecked status: " + status;
        			delete $scope.orderData;
        		}
        	})
        	.error(function (data, status, header, config) {
        		$scope.orderStatus="Unsuccessful order!";
        		delete $scope.orderData;
        	});
    }
  }]);

/* ########################################################
 * 						SHEPHERD
 * ######################################################## */
sheepShopControllers.controller('shepherdController', ['$scope', '$http', function($scope, $http){
	$scope.uploadFile = function(){
        var file = $scope.myFile;
        var uploadUrl = "/sheep-shop/initialize";
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
	    .success(function(){
	    	$scope.uploadStatus="Upload successful.";
	    })
	    .error(function(){
	    	$scope.uploadStatus="Upload unsuccessful.";
        });
    };
    $scope.getStockData = function(){
    	var url = "/sheep-shop/stock/" + $scope.day;
    	delete $scope.stockData;
    	delete $scope.error;
    	delete $scope.herdData;
    	$http.get(url)
		.success(function(data){
			$scope.stockData = data;
			delete $scope.error;
			delete $scope.herdData;
        })
        .error(function(){
        	$scope.error="Get stock - unsuccessful.";
        	delete $scope.stockData;
        	delete $scope.herdData;
        });
    };
    $scope.getHerdData = function(){
    	var url = "/sheep-shop/herd/" + $scope.day;
    	delete $scope.stockData;
    	delete $scope.error;
    	delete $scope.herdData;
    	$http.get(url)
    	.success(function(data) {
    		$scope.herdData = data;
    	})
    	.error(function(){
    		$scope.error="Get heard - unsuccessful.";
    	});
    };
	$scope.day = 1;
	$scope.uploadStatus="";
}]);

/* ########################################################
 * 						DIRECTIVE
 * ######################################################## */
sheepShopControllers.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);


