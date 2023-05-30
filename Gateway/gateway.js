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


const app = express()
app.use(cors())
app.use(express.json())
app.use('/', router)


app.listen(PORT, () => {
    console.log('Server live on port:', PORT)
})