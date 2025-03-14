package api.endpoints;

/*
  Swagger URI ="https://petstore.swagger.io/v2";
  
  Create user :https://petstore.swagger.io/v2/user
  Get user    :https://petstore.swagger.io/v2/user/{username}
  Update user :https://petstore.swagger.io/v2/user/{username}
  Delete user :https://petstore.swagger.io/v2/user/{username}
  */

//This class will contain only url
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//user module under swagger uri
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url =base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//store module
	
	  //here create store module url
	
	//Pet module
	
	     //here create pet module url
	

}
