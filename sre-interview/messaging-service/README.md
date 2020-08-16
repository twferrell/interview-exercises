# Messaging Service

This is a node/express service written in ES6. The primary purpose is to build email messages to greet individuals based on their provided name.

The application responds to GET requests of the form `/message?name=Some%20Name` with json responses of the form:
```bash
{
    "from": "greetings@asurint.com",
    "to": "some.name@asurint.com",
    "body": "Hello Some Name! How are you?"
}
```
The environment variable DIRECTORY_SERVICE_URL is required to be defined in order to run the application.


## Running the App Locally

To run the application locally with the directory service running on port 9000, run:
```bash
DIRECTORY_SERVICE_URL=http://localhost:9000 npm start
```
This starts a local express server listening on port 3000.

The application also hosts a health endpoint, which is exposed at:
* http://localhost:3000/health


## Building the App for Production

To run the app in production mode, we must first transpile from ES6 to vanilla JS using:
```bash
npm run build
```
This produces transpiled javascript in the `./dist` directory.

To run the application against this transpiled source code, run:
```bash 
npm run start:prod
```