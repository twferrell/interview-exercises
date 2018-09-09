# Asurint Software Engineer Exercise

## General Guidelines

 * Quality matters more than quantity. Do not feel like this entire task needs to be completed in 2 hours.
 * Feel free to consult google if necessary. No one has all the syntax memorized, and we don’t expect you to.
 * Write tests or use TDD if you are familiar with working this way.
 * In memory persistence is fine.


## Steps

1. **Generate url slugs based on a description**  
    Create a function that can generate search engine friendly URL slugs from a description of a website. Invalid characters should be removed, and words should be separated using a dash, eg.  
    ```
    "Aunt Millie's & Co., Inc.” -> "aunt-millies-and-co-inc"
    ```  

    Your function should be able to replace the following special characters with their English counterparts:  
    * & -> and  
    * @ -> at  
    * % -> percent  

    All other special characters should be removed. The output should never result in multiple consecutive dash separators, or dash separators at the beginning or end of the result.

2. **Create a REST service to expose the slug generation function**  
    The api should accept a description string (eg. “Aunt Millie's & Co., Inc.”) and the url for the site being described (eg. http://auntmilliescookieemporium.com) in a request.

    The api should return a url that includes the generated slug using the function built in part 1. The url should be prefixed using the hostname and port of the currently running service, eg. http://localhost:3000/aunt-millies-and-co-inc if the service is running on http://localhost:3000
    
    We will expand the service to handle requests of this form in step 3. below.

3. **Add an endpoint to the REST service for redirects**  
    Add to the previously created api to redirect GET requests for the generated slug to the url that was submitted with the description in Step 2.

    For example, if we first call the api to generate the slug “aunt-millies-and-co-inc” for the url http://auntmilliescookieemporium.com, then our api should respond to requests for http://localhost:3000/aunt-millies-and-co-inc by redirecting to http://auntmilliescookieemporium.com.

4. **Dockerize it**  
    Create a Dockerfile and run this service in a container.