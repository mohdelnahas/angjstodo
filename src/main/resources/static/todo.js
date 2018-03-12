angular.module('todoApp', [])
  .controller('TodoListController', function($scope,$http) {
	  
	  $http({method:'GET',url:'/api/todoList'}).then(function(response) {
          $scope.mtodoList = response.data;
          console.log( $scope.mtodoList);
      });
	
    var todoList = this;
    todoList.todos =$scope.mtodoList;
   
    todoList.addTodo = function() {
    	
	    	 console.log(todoList.title);
	    	 var todoItem=JSON.stringify({title:todoList.title});
	    	 console.log(todoItem);
	    	 $http.post('/api/todoList', todoItem).then(function(response) {
	             $scope.mtodoList.push(response.data);
	             console.log( $scope.mtodoList);
	       });
    	 
    };
    
    todoList.done = function() {
    	
    
    	 	angular.forEach($scope.mtodoList, function(todo) {
    		 console.log(todo.id);
    		 console.log(todo.completed);
    		 var todoItem=JSON.stringify({id:todo.id,completed:todo.completed,title:todo.title});
    		 $http.put('/api/todoList/'+todo.id, todoItem).then(function(response) {
              
              
         });
    	 	});
    	 
   	 
	 
    };

  

   
  });
