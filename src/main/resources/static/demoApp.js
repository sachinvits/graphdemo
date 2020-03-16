var app = angular.module('app',[]);

app.controller('EmployeeController', ['$scope','EmployeeService', function ($scope,EmployeeService) {
	  
    $scope.updateEmployee = function () {
        EmployeeService.updateEmployee($scope.employee.empId, $scope.employee.name)
          .then(function success(response){
              $scope.message = response.data.message;
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = response.data.message;
              $scope.message = '';
          });
    }
    
    $scope.getEmployee = function () {
        var empId = $scope.employee.empId;
        EmployeeService.getEmployee(empId)
          .then(function success(response){
              $scope.employee = response.data.employees[0];
              //$scope.employee.empId = empId;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Employee not found! ' + response.data.message;
              }
              else {
                  $scope.errorMessage = "Error getting employee!";
              }
          });
    }
    
    $scope.addEmployee = function () {
        if ($scope.employee != null && $scope.employee.empId) {
            EmployeeService.addEmployee($scope.employee.empId, $scope.employee.name)
              .then (function success(response){
                  $scope.message = response.data.message;
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = response.data.message;
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please enter Employee Id!';
            $scope.message = '';
        }
    }
    
    $scope.deleteEmployee = function () {
        EmployeeService.deleteEmployee($scope.employee.empId)
          .then (function success(response){
              $scope.message = response.data.message;
              $scope.employees = null;
              $scope.employee = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = response.data.message;
              $scope.message='';
          })
    }
    
    $scope.deleteAllEmployees = function () {
        EmployeeService.deleteAllEmployees()
          .then (function success(response){
              $scope.message = response.data.message;
              $scope.employee = null;
              $scope.employees = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = response.data.message;
              $scope.message='';
          })
    }
    
    $scope.getAllEmployees = function () {
        EmployeeService.getAllEmployees()
          .then(function success(response){
              $scope.employees = response.data.employees;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = response.data.message;
          });
    }

}]);

app.service('EmployeeService',['$http', function ($http) {
	var basePath = '/graphdemo/v1/employee';
	
    this.getEmployee = function getEmployee(empId){
        return $http({
          method: 'GET',
          url: basePath + '/'+ empId
        });
	}
	
    this.addEmployee = function addEmployee(empId, name){
        return $http({
          method: 'POST',
          url: basePath + '/add-employee',
          data: {empId:empId, name:name}
        });
    }
	
    this.deleteEmployee = function deleteEmployee(empId){
        return $http({
          method: 'DELETE',
          url: basePath + '/delete/' + empId
        })
    }
    
    this.deleteAllEmployees = function deleteAllEmployees(){
        return $http({
          method: 'DELETE',
          url: basePath + '/delete-all'
        })
    }
	
    this.updateEmployee = function updateEmployee(empId, name){
        return $http({
          method: 'PUT',
          url: basePath + '/update-employee',
          data: {empId:empId, name:name}
        })
    }
	
    this.getAllEmployees = function getAllEmployees(){
        return $http({
          method: 'GET',
          url: basePath + '/all'
        });
    }

}]);