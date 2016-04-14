var sheepShopApp = angular.module('sheepShopApp', ['ngRoute', 'sheepShopControllers']);

sheepShopApp.config(['$routeProvider','$locationProvider', function($routeProvider,$locationProvider) {
            $routeProvider.
            when('/customer',{
                templateUrl: 'partials/customer.html',
                controller: "customerController"
            }).
            when('/shepherd',{
                templateUrl: 'partials/shepherd.html',
                controller: "shepherdController"
            }).
            otherwise({
                redirectTo: 'customer'             
            });
        }]);
