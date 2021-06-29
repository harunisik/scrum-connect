var express = require('express');
const axios = require('axios');

var router = express.Router();

router
  .get('/', async function (req, res, next) {
    let userdataList = [];
    let errorMessage = null;

    try {
      const { data } = await axios.get('http://localhost:8080/api/userdata');
      userdataList = data.userdataList;
    } catch (error) {
      errorMessage = error.message;
    }

    let pageData = {
      layout: 'layout.njk',
      title: 'Government Service',
      userdataList,
      errorMessage,
    };

    res.render('index.njk', pageData);
  })
  .post('/formResult', async function (req, res, next) {
    const { name } = req.body;
    let errorMessage = null;

    try {
      const { data } = await axios.post('http://localhost:8080/api/userdata', req.body);
      console.log(data);
    } catch (error) {
      errorMessage = error.message;
    }

    let pageData = {
      layout: 'layout.njk',
      title: 'Application Result',
      name,
      errorMessage,
    };

    res.render('formResult.njk', pageData);
  });

module.exports = router;
