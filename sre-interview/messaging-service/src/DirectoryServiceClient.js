import fetch from 'node-fetch'

export default class DirectoryServiceClient {
  constructor () {
    this.directoryServiceUrl = process.env.DIRECTORY_SERVICE_URL

    if (!this.directoryServiceUrl) {
      throw Error('environment variable DIRECTORY_SERVICE_URL is required')
    }
  }

  getEmailAddress (name) {
    return fetch(`${this.directoryServiceUrl}/email?name=${name}`)
      .then(res => res.text())
  }
}
