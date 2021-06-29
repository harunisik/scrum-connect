var express = require('express');
const axios = require('axios');

var router = express.Router();

const getAllUserdata = () => {};

router
  .get('/', async function (req, res, next) {
    axios
      .get('http://localhost:8080/api/userdata')
      .then(({ data }) => {
        console.log(data);

        let pageData = {
          layout: 'layout.njk',
          message: 'Hello world!',
          title: 'Nunjucks example',
          ...data,
        };

        res.render('index.njk', pageData);
      })
      .catch((error) => {
        console.log(error.message);
      });
  })
  .post('/', async function (req, res, next) {
    console.log(req.body);
  });

module.exports = router;
