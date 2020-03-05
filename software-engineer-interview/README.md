# Asurint Software Engineer Exercise

## General Guidelines

- Quality matters more than quantity. Do not feel like this entire task needs to be completed in the alotted time.
- Feel free to consult google if necessary. No one has all the syntax memorized, and we don’t expect you to.
- Write tests or use TDD if you are familiar with working this way.
- In memory persistence is fine.

## Steps

1. **Generate url slugs based on a description**

   Create a function that can generate URL friendly "slugs" from a description of a website.

   Invalid characters should be removed, and words should be separated using a dash, eg.

   ```
   "Aunt Millie's & Co., Inc.” -> "aunt-millies-and-co-inc"
   ```

   This function should also replace the following special characters with their English counterparts:

   - & -> and
   - @ -> at
   - % -> percent

   All other special characters should be removed. The output should never result in multiple consecutive dash separators, or dash separators at the beginning or end of the result.

2. **Create an api that registers urls and slug versions of their descriptions**

   The goal of this step is to expose an endpoint through which a website's url and a description of the website can be saved. The description should be turned into a slug using the generation function written in step 1. and also stored with the url. We will use this data in step 3. to redirect GET requests for the slug to the registered url.

   - The api endpoint should accept both a description string (eg. “Aunt Millie's & Co., Inc.”) and the url for the site being described (eg. http://auntmilliescookieemporium.com) in a request.
   - The api should respond with the slug that was generated using the function built in part 1.
   - The url and the generated slug should somehow be peristed for future use - in memory persistence is sufficient

3. **Add an endpoint to the service that uses the slug to redirect to the registered website**

   Enhance the previously created api to host another endpoint that redirects requests for the generated slug to the url that was submitted with the description in Step 2.

   For example, if we first call the api with the description “Aunt Millie's & Co., Inc.” and url http://auntmilliescookieemporium.com, and we have generated the slug “aunt-millies-and-co-inc”, then a request sent to this endpoint with "aunt-millies-and-co-inc" will redirect the user's browser to http://auntmilliescookieemporium.com.

4. **Create a UI**

   Create a web page that allows a user to enter the description and URL, call the api, and render a link on the page to the generated redirect url.

   - Include fields for the description and URL
   - Submit the data asynchronously
   - Use the response to populate a link on the page
   - Add form validation
   - Handle error responses

5. **Dockerize it (Bonus)**

   Create a Dockerfile and run this/these service(s) in a container.
