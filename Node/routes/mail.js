const nodemailer = require("nodemailer");

function mail(req, res) {
    var transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: 'r.kevinelliot@gmail.com',
        pass : 'moncompte2'
    }
    });
    var mailOptions = {
        from: 'r.kevinelliot@gmail.com',
        to: 'r.kevinelliot@yahoo.com',
        subject: 'Sending Email using Node.js',
        text: 'That was easy!'
    };

    transporter.sendMail(mailOptions, function(error, info){
    if (error) {
        console.log(error);
    } else {
        console.log('Email sent: ' + info.response);
    }
    });
}
//paramaetre google mila activena ilay ... cf https://mailtrap.io/blog/nodemailer-gmail/
module.exports = {mail};