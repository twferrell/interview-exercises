export default class MessageGenerator {
  constructor (directoryClient) {
    this.directoryClient = directoryClient
  }

  generateMessage (name) {
    return this.directoryClient.getEmailAddress(name)
      .then(emailAddress => this.formatMessage(name, emailAddress))
  }

  formatMessage (name, toEmailAddress) {
    return {
      from: 'greetings@asurint.com',
      to: toEmailAddress,
      body: `Hello, ${name}! How are you?`
    }
  }
}
