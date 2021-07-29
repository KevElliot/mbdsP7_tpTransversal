using System;
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
    public partial class Profil : Form
    {
        Model.Login user;
        ServiceNode serviceNode = new ServiceNode();
        List<Model.Pari> allPari;

        public Profil(Model.Login user, List<Model.Pari> allPari)
        {
            this.user = user;
            this.allPari = allPari;
            InitializeComponent();
        }

        private void Profil_Load(object sender, EventArgs e)
        {
            this.nomTextbox.Text = user.name;
            this.emailTextBox.Text = user.email;
        }

        private void annulerBtn_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private async void modifierBtn_Click(object sender, EventArgs e)
        {
            string id = this.user._id;
            string name = this.nomTextbox.Text;
            string password = this.passwordTextBox.Text;
            string role = this.user.role;
            string email = this.emailTextBox.Text;
            float jetons = this.user.jetons;
            string res = await serviceNode.UpdateUser(id, name, password, email, role, jetons);
            if (res == "900")
            {
                MessageBox.Show("Veuillez vous assurer que vous aviez bien rempli les champs demander!");
            }else if (res == "200")
            {
                this.user.name = name;
                this.user.password = password;
                this.user.email = email;
                MessageBox.Show("Votre profil a bien été modifier!");
                Hide();
                Home h = new Home(this.user, this.allPari);
                h.Show();
            }

        }
    }
}
