var mongoose = require('mongoose');  

let Schema = mongoose.Schema;

var historiqueSchema = Schema({  
  idUser: String,
  datePari: String,
  designationPari: String,
  typePari:String,
  etat:String,
  winRate:String,
  nbBut:String,
  cote:String,
  jeton:String,
  gain:String,
  resultatPari:String,
});
mongoose.model('historiquePari', historiqueSchema);

module.exports = mongoose.model('historiquePari',historiqueSchema);