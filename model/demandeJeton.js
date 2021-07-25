var mongoose = require('mongoose');  

let Schema = mongoose.Schema;

var DemandeJetonSchema = Schema({  
  iduser: String,
  emailuser: String,
  jetonsdemande: Number,
  statut: String
});
mongoose.model('demandeJeton', DemandeJetonSchema);

module.exports = mongoose.model('demandeJeton',DemandeJetonSchema);
