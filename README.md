Android Take-Home Assignment
This challenge will be graded as if it is a fully functional app. If for some reason, some part of your app is not working, it should be cited.
The Challenge
Develop an App, which would let the users search for Github profiles.
Requirements
1. Fetch Repositories:
Use the following GitHub API endpoint: GitHub API.
You must use Kotlin only in the entire Challenge.
For Network call you can use Retrofit , OkHttp etc .,
2. Data Model:
Create a data class GHRepo: Serializable to decode the repository data with these three properties
id (mapped to id )
name (mapped to name)
repoURL (mapped to html_url )
3. NetworkService:
Create a NetworkService class to perform the API request.
Implement a function to fetch data.
Handle errors and success responses.
4. Caching:
Design a Cacher class to cache network responses.
Implement caching logic to store and retrieve cached data.
Use Room or Realm data base system for storing data locally.
5. UI Implementation:
Use RecyclerView to display the list of repositories.
Implement ViewHolder pattern with a label to display the repository name, id, name of user in the item.
Implement on click of a item to open url in Webview .
Ensure proper cell reuse in RecyclerView
Implement local search functionality based on id or name
6. Additional Considerations:
Follow good coding practices, such as clear naming conventions and modularization.
Write clean, readable, and maintainable code.
Ensure the application handles network failures gracefully.
Include comments and documentation where necessary
