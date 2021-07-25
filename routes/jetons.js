var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());
var Jetons = require('../model/demandeJeton');
var User = require('../model/user');

function getDemandeJeton(req, res){
    Jetons.find({statut: false},(err, jetons) => {
        if(err){
            res.send(err)
        }
        res.send(jetons);
    });
}

function demandeJeton(req, res) {
    let jetons = new Jetons();
    jetons.iduser = req.body.iduser;

    User.findOne({ _id: jetons.iduser }, (err, user) => {
        if (err) {
            return res.status(400).json({
                status: 'error',
                error: 'req body cannot be empty',
            });
        }
        jetons.jetonsdemande = req.body.jetonsdemande;
        jetons.statut = req.body.statut;
        jetons.emailuser = user.email;
        console.log(jetons);
        jetons.save((err) => {
            if (err) {
                res.send("cant post jeton ", err);
            }
            res.status(200).send({ message: 'true' })
        });
    });
};
function updateStatutJeton(req, res) {
    var totalJetons = 0;
    Jetons.findByIdAndUpdate(
        req.body._id,
        req.body,
        { new: true },
        (err, demande) => {
            if (err) {
                console.log(err);
                res.send(err);
            } else {
                let iduser = demande.iduser;
                req.body._id = iduser;
                User.findOne({ _id: iduser }, (err, utilisateur) => {
                    if (err) {
                        return res.status(400).json({
                            status: 'error',
                            error: 'req body cannot be empty',
                        });
                    } else {
                        totalJetons = demande.jetonsdemande + utilisateur.jetons;
                        //console.log(demande.jetonsdemande + "+" + utilisateur.jetons + "=" + totalJetons);
                        req.body.jetons = totalJetons;
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
                });
            }
        }
    );
}
module.exports = {
    getDemandeJeton,
    demandeJeton,
    updateStatutJeton,
};
