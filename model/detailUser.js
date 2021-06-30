var mongoose = require('mongoose');

let Schema = mongoose.Schema;

var DetailUserSchema = Schema({
    _id: String,
    detail: String
});
mongoose.model('detailUser', DetailUserSchema);

module.exports = mongoose.model('detailUser', DetailUserSchema);
