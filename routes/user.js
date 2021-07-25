var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
var User = require('../model/user');

/**
 * Configure JWT
 */
var jwt = require('jsonwebtoken'); // used to create, sign, and verify tokens
var bcrypt = require('bcryptjs');
var config = require('./config'); // get config file

function login (req, res) {

  User.findOne({ email: req.body.email }, function (err, user) {
    if (err) return res.status(500).send('Error on the server.');
    if (!user) return res.status(401).send('No user found.');
    
    // Mijery ra marina ilay password
    var passwordIsValid = bcrypt.compareSync(req.body.password, user.password);
    if (!passwordIsValid) return res.status(401).send({ auth: false, token: null });

    // ra marina le mdp dia micreer token
    var token = jwt.sign({ id: user._id }, config.secret, {
      expiresIn: 86400
    });
    res.status(200).send({ auth: true,_id:user._id,name: user.name,jetons:user.jetons,role: user.role, token: token });
  });

};
function loginQr (req, res) {
  User.findOne({ email: req.body.email }, function (err, user) {
    if (err) return res.status(500).send('Error on the server.');
    if (!user) return res.status(401).send('No user found.');
    if (req.body.password==user.password) {
      var token = jwt.sign({ id: user._id }, config.secret, {
        expiresIn: 86400
      });
      res.status(200).send({ auth: true,_id:user._id,name: user.name,jetons:user.jetons,role: user.role, token: token });
    }else{
      return res.status(401).send({ auth: false, token: null });
    }
  });
};

function logout (req, res) {
  res.status(200).send({ auth: false, token: null });
};

function register (req, res) {
  var hashedPassword = bcrypt.hashSync(req.body.password, 8);
  let user= new User();
  user.name = req.body.name;
  user.password = hashedPassword;
  user.email = req.body.email;
  user.role = req.body.role;
  user.image = req.body.image;
  user.jetons = req.body.jetons;
  //console.log("POST user reçu :");
  //console.log(user);

  user.save((err) => {
    if (err) {
      res.send("cant post user ", err);
    }
    var token = jwt.sign({ id: user._id }, config.secret, {
      expiresIn: 86400 // expires in 24 hours
    });
    res.status(200).send({ auth: true, token: token })
  });
};
// Update d'un user (PUT)
function updateUser(req, res) {
  var hashedPassword = bcrypt.hashSync(req.body.password, 8);
  req.body.password = hashedPassword;
  console.log(req.body);
  User.findByIdAndUpdate(
    req.body._id,
    req.body,
    { new: true },
    (err, user) => {
      if (err) {
        console.log(err);
        res.send(err);
      } else {
        res.json({ message: "updated" });
      }
    }
  );
}
// Update jeton d'un user (PUT)
// function updateJetonUser(req, res) {
//   User.findByIdAndUpdate(
//     req.body._id,
//     req.body,
//     { new: true },
//     (err, user) => {
//       if (err) {
//         console.log(err);
//         res.send(err);
//       } else {
//         res.json({ message: "updated" });
//       }
//     }
//   );
// }
function updateJetonUser(req, res){
  console.log(req);
  res.send(req);
}
// suppression d'un User (DELETE)
function deleteUser(req, res) {
  //console.log(req.params.id);
  user.findByIdAndRemove(req.params.id, (err, user) => {
    if (err) {
      res.send(err);
    }
    res.json({ message: `${user.name} deleted` });
  });
}

// Récupérer un user par son id (GET)
function getUserById(req, res) {
  let userId = req.params.id;
  User.findOne({ _id: userId }, (err, user) => {
    if (err) {
      return res.status(400).json({
        status: 'error',
        error: 'req body cannot be empty',
      });
    }
    res.status(200).json(user)
  });
}

function getUserActive(req, res) {
  let userId = req.body._id;
  console.log(userId);
  User.findOne({ _id: userId }, (err, user) => {
    if (err) {
      res.send(err);
    }
    res.status(200).send({ user: user})
  });
}

function updateMultiple(req, res){
  var infoUser;
  for(var key in req.body) {
    var jsn=JSON.parse(JSON.stringify(req.body));
    if(jsn.hasOwnProperty(key)){
      User.findOne({ _id: jsn[key]._idclient }, (err, user) => {
        if (err) {
          res.send(err);
        }
        infoUser = user;
      infoUser.jetons=infoUser.jetons+jsn[key].capital
      infoUser.save();
    });
    }
  }
  res.json({ message: "update done" });
}

module.exports = {
  login,
  loginQr,
  register,
  logout,
  updateUser,
  updateJetonUser,
  deleteUser,
  getUserById,
  getUserActive,
  updateMultiple
};