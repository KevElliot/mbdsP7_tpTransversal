using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using winform_TpTransversal.Service;

namespace winform_TpTransversal.Vu
{
    public partial class Login : Form
    {
        ServiceNode serviceNode = new ServiceNode();
        Inscription signin = new Inscription();
        Home h;
        Model.Login user;
        public Login()
        {
            InitializeComponent();
        }

        private void inscriptionBtn_Click(object sender, EventArgs e)
        {
            Dispose();
            signin.Show();
        }

        private async void loginBtn_Click(object sender, EventArgs e)
        {
            Button btn = sender as Button;
            btn.Text = "En attente";
            btn.Enabled = false;
            UseWaitCursor = true;
            string email = this.emailTextbox.Text;
            string password = this.passwordTextbox.Text;
            List<Model.Pari> allPari = new List<Model.Pari>();
            var auth = await serviceNode.Login(email, password);
            if (auth.res == "valide")
            {
                UseWaitCursor = false;
                btn.Text = "Login";
                btn.Enabled = true;
                this.user = auth.utilisateur;
                h = new Home(user, allPari);
                h.Show();
                Hide();
            }
            else
            {
                UseWaitCursor = false;
                btn.Enabled = true;
                Cursor.Current = Cursors.Default;
                btn.Text = "Login";
                MessageBox.Show(auth.res);
            }
        }
    }
}
