require('dotenv').config()
const cors = require("cors");
const express = require("express");
const router = express.Router();
const axios = require('axios');

const PORT = process.env.PORT || 1312

// Instancier l'application express
// Configuer l'application avec les middlewares comme cors
// définir le type d'objet pour les communications (json)
// ajouter un Router. Router défini dans ./PathToRouterFile


router.get('/communities/get/:name', (req, res) => {
    axios.get('http://localhost:8081/communities/get/' + req.params.name).then((response) => {
        res.send(response.data)
    })
})

router.put('/communities/edit/:id', (req, res) => {
    axios.put(req, 'http://localhost:8081/communities/edit/' + req.params.id).then((response) => {
        res.send(response.data)
    })
})

// PUT
// /communities/edit/{id}
// Edits a community by its id

// POST
// /communities/add
// Adds a community do the database

// GET
// /communities/view/{id}
// Get a community by its id

// GET
// /communities/view/all
// Gets all communities

// GET
// /communities/get/{name}
// Gets a community by name

// DELETE
// /communities/delete/{id}
// Deletes a community by its id

// Schemas

const app = express()
app.use(cors())
app.use(express.json())
app.use('/', router)


app.listen(PORT, () => {
    console.log('Server live on port:', PORT)
})