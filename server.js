
let express = require('express');
let app = express();
let bodyParser = require('body-parser');
let mongoose = require('mongoose');
mongoose.Promise = global.Promise;
let port = process.env.PORT || 8010;
let email = require('./routes/mail');
let user = require('./routes/user');


//connexion mongoDB
const uri = 'mongodb+srv://masterkoto:moncompte2@cluster0.8mqmg.mongodb.net/pariLocal?retryWrites=true&w=majority';
const options = {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useFindAndModify: false
};

mongoose.connect(uri, options)
  .then(() => {
    console.log("Connecté à la base MongoDB assignments dans le cloud !");
    console.log("at URI = " + uri);
    console.log("vérifiez with http://localhost:8010/pariBack/ que cela fonctionne")
  },
    err => {
      console.log('Erreur de connexion: ', err);
    });
// Pour accepter les connexions cross-domain (CORS)
app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
  next();
});
//Pour les formulaires
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// les routes
const prefix = '/pariBack';
app.route(prefix + '/mail')
  .get(email.mail);

app.route(prefix + '/auth/register')
  .post(user.register);

app.route(prefix + '/auth/login')
  .post(user.login);

app.route(prefix + '/auth/logout')
  .post(user.logout);

app.route(prefix + '/user')
  .put(user.updateUser)
  .get(user.getUserActive);

app.route(prefix + '/parier')
  .put(user.updateJetonUser);

app.route(prefix + '/user/:id')
  .get(user.getUserById)
  .delete(user.deleteUser);


// On démarre le serveur
app.listen(port, "0.0.0.0");
console.log('Serveur démarré sur http://localhost:' + port);
module.exports = app;


