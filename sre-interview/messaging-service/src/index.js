import express from 'express'
import DirectoryServiceClient from './DirectoryServiceClient'
import MessageGenerator from './MessageGenerator'

var app = express()
var messageGenerator = new MessageGenerator(new DirectoryServiceClient())

app.get('/health', (_, res) => res.send())

app.get('/message', (req, res) => {
  var name = req.query.name
  messageGenerator.generateMessage(name)
    .then(message => res.send(message))
})

const port = process.env.PORT || 3000
app.listen(port, () => console.log(`messaging service started on port ${port}`))
