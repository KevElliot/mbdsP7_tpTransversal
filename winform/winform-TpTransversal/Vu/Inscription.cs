using System;
using System.Windows.Forms;
using winform_TpTransversal.Service;

namespace winform_TpTransversal.Vu
{
    public partial class Inscription : Form
    {
        ServiceNode serviceNode = new ServiceNode();
        public Inscription()
        {
            InitializeComponent();
        }

        private async void validerBtn_Click(object sender, EventArgs e)
        {
            Button btn = sender as Button;
            btn.Text = "En attente";
            btn.Enabled = false;
            UseWaitCursor = true;
            Model.Login user = new Model.Login();
            user.name = this.nomTextBox.Text;
            user.password = this.passwordTextBox.Text;
            user.email = this.emailTextbox.Text;
            user.jetons = float.Parse(this.jetonTextBox.Text);
            user.role = "user";
            string res = await serviceNode.Inscription(user);
            if (res=="200")
            {
                this.Hide();
                btn.Text = "Valider";
                btn.Enabled = true;
                UseWaitCursor = false;
                MessageBox.Show("Inscription reussi, Veuillez vous connecter.");
                Login login = new Login();
                login.Show();
            }
            else
            {
                btn.Text = "Valider";
                btn.Enabled = true;
                UseWaitCursor = false;
                Console.WriteLine(res);
            }
        }
    }
}
